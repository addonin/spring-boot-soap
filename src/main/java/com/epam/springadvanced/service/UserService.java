package com.epam.springadvanced.service;

import com.epam.springadvanced.domain.entity.Ticket;
import com.epam.springadvanced.domain.entity.User;

import java.time.LocalDate;
import java.util.Collection;

public interface UserService {

    User create(String name, String email, LocalDate birthDay);

    User register(User user);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    Collection<Ticket> getBookedTickets(long userId);

    Collection<User> getAll();

}
