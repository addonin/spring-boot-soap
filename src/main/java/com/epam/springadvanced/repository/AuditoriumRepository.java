package com.epam.springadvanced.repository;

import com.epam.springadvanced.domain.entity.Auditorium;
import com.epam.springadvanced.domain.entity.Seat;

import java.util.Collection;
import java.util.List;

public interface AuditoriumRepository {

    Auditorium getById(int id);

    List<Auditorium> getAuditoriums();

    long getSeatsNumber(int auditoriumId);

    Collection<Seat> getSeats(int auditoriumId);

    Seat getSeatByAuditoriumIdAndNumber(int auditoriumId, int number);

}
