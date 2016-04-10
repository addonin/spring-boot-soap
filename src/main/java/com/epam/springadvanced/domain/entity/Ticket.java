package com.epam.springadvanced.domain.entity;

import com.epam.springadvanced.domain.enums.TicketState;

public class Ticket {

    private Long id;
    private float price;
    private Seat seat;
    private Event event;
    private TicketState state;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

}
