package com.epam.springadvanced.utils.editor;

import com.epam.springadvanced.domain.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dimon
 * @since 27/03/16.
 */
@Component
public class AuditoriumEditor extends PropertyEditorSupport {

    private Map<Integer, Auditorium> auditoriumMap = new HashMap<>();

    @Autowired
    private List<Auditorium> auditoriums;

    @PostConstruct
    public void init() {
        auditoriums.stream().forEach(auditorium -> auditoriumMap.put(auditorium.getId(), auditorium));
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setValue(auditoriumMap.get(Integer.parseInt(id)));
    }
}
