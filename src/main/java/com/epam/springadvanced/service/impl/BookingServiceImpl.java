package com.epam.springadvanced.service.impl;

import com.epam.springadvanced.domain.entity.*;
import com.epam.springadvanced.domain.enums.Rating;
import com.epam.springadvanced.domain.enums.TicketState;
import com.epam.springadvanced.repository.AuditoriumRepository;
import com.epam.springadvanced.repository.TicketRepository;
import com.epam.springadvanced.service.*;
import com.epam.springadvanced.service.exception.EventNotAssignedException;
import com.epam.springadvanced.service.exception.TicketAlreadyBookedException;
import com.epam.springadvanced.service.exception.TicketWithoutEventException;
import com.epam.springadvanced.service.exception.UserNotRegisteredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final int VIP_PRICE_COEF = 2;
    private static final float HIGH_EVENT_PRICE_COEF = 1.2f;

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public float getTicketPrice(Event event, LocalDateTime dateTime, Collection<Integer> seatNumbers, User user) throws
            UserNotRegisteredException, EventNotAssignedException {
        float price = 0;
        Optional.ofNullable(user)
                .flatMap(u -> Optional.ofNullable(userService.getUserByName(u.getName())))
                .orElseThrow(UserNotRegisteredException::new);

        Optional.ofNullable(event)
                .flatMap(e -> Optional.ofNullable(e.getAuditorium()))
                .orElseThrow(EventNotAssignedException::new);

        Auditorium auditorium = event.getAuditorium();
        Collection<Seat> auditoriumSeats = auditoriumRepository.getSeats(auditorium.getId());
        for (int number : seatNumbers) {
            Seat seat = auditoriumRepository.getSeatByAuditoriumIdAndNumber(auditorium.getId(), number);
            if (auditoriumSeats.contains(seat)) {
                price = (seat.isVip() ? event.getTicketPrice() * VIP_PRICE_COEF : event.getTicketPrice());
            }
            price = price * (event.getRating() == Rating.HIGH ? HIGH_EVENT_PRICE_COEF : 1);
            float discount = discountService.getDiscount(user, event, dateTime);
            price = price - (100 * discount);
        }
        return price;
    }

    @Override
    @Transactional
    public void createTickets(Event event) {
        Auditorium auditorium = event.getAuditorium();
        Collection<Seat> seats = auditorium.getSeats();
        for (Seat seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setSeat(seat);
            ticket.setState(TicketState.FREE);
            ticketRepository.save(ticket);
        }
    }

    @Override
    public Ticket getTicket(long id) {
        return ticketRepository.get(id);
    }

    @Override
    @Transactional
    public void bookTicket(User user, Ticket ticket, float price) throws UserNotRegisteredException, TicketAlreadyBookedException,
            TicketWithoutEventException {
        //if users is null or not registered then throw exception
        Optional.ofNullable(user)
                .flatMap(u -> Optional.ofNullable(userService.getUserByName(u.getName())))
                .orElseThrow(UserNotRegisteredException::new);

        //if ticket is null or without event then throw exception
        Optional.ofNullable(ticket)
                .flatMap(t -> Optional.ofNullable(t.getEvent()))
                .orElseThrow(TicketWithoutEventException::new);

        long userId = user.getId();
        float amount = userAccountService.getUserAccount(userId).getAmount();
        boolean enoughMoney = amount >= price;

        //false if ticket already booked for specified event and seat
        boolean notBooked = ticketRepository.getBookedTickets(ticket.getEvent().getId()).stream()
                .noneMatch(t -> t.getEvent().getName().equals(ticket.getEvent().getName()) &&
                        t.getSeat().getNumber() == ticket.getSeat().getNumber()
                );
        if (enoughMoney && notBooked) {
            ticket.setPrice(price);
            userAccountService.changeAmount(userId, amount - price);
            ticketRepository.saveBookedTicket(user, ticket);
            log.info(String.format("User <%s> booked ticket with seat number %d for event <%s>",
                    user.getName(),
                    ticket.getSeat().getNumber(),
                    ticket.getEvent().getName()));
        } else {
            throw new TicketAlreadyBookedException(ticket.getSeat().getNumber());
        }
    }

    @Override
    public Collection<Ticket> getFreeTickets(long eventId) {
        return ticketRepository.getFreeTickets(eventId).stream().collect(Collectors.toList());
    }

    @Override
    public Collection<Ticket> getBookedTickets(long eventId) {
        return ticketRepository.getBookedTickets(eventId).stream().collect(Collectors.toList());
    }

}
