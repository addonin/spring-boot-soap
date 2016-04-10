package com.epam.springadvanced.repository;

import com.epam.springadvanced.domain.entity.Ticket;
import com.epam.springadvanced.domain.entity.User;

import java.util.Collection;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    Ticket get(long id);

    Collection<Ticket> getAll();

    Collection<Ticket> getFreeTickets(long eventId);

    Collection<Ticket> getBookedTickets(long eventId);


    Collection<Ticket> getBookedTicketsByUserId(long userId);

    void saveBookedTicket(User user, Ticket ticket);

    void deleteBookedTicketByUserId(long userId);

}
