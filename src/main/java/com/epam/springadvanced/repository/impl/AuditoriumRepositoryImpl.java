package com.epam.springadvanced.repository.impl;

import com.epam.springadvanced.domain.entity.Auditorium;
import com.epam.springadvanced.domain.entity.Seat;
import com.epam.springadvanced.repository.AuditoriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Repository
public class AuditoriumRepositoryImpl implements AuditoriumRepository {

    @Autowired
    public AuditoriumRepositoryImpl(List<Auditorium> auditoriumList) {
        auditoriums = auditoriumList.stream().collect(toMap(Auditorium::getId, identity()));
    }

    private Map<Integer,Auditorium> auditoriums;

    @Override
    public Auditorium getById(int id) {
        return auditoriums.get(id);
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return new ArrayList<>(auditoriums.values());
    }

    @Override
    public long getSeatsNumber(int auditoriumId) {
        return getById(auditoriumId).getNumberOfSeats();
    }

    @Override
    public Collection<Seat> getSeats(int auditoriumId) {
        return getById(auditoriumId).getSeats();
    }

    @Override
    public Seat getSeatByAuditoriumIdAndNumber(int auditoriumId, int number) {
        return getSeats(auditoriumId).stream().filter(s -> s.getNumber() == number).findFirst().orElse(null);
    }

}
