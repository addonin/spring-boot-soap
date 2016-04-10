package com.epam.springadvanced.service.impl;

import com.epam.springadvanced.domain.entity.Auditorium;
import com.epam.springadvanced.repository.AuditoriumRepository;
import com.epam.springadvanced.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dimon
 * @since 27/03/16.
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public Auditorium getAuditorium(int id) {
        return auditoriumRepository.getById(id);
    }
}
