package com.epam.springadvanced.utils.editor;

import com.epam.springadvanced.domain.entity.Auditorium;
import com.epam.springadvanced.domain.enums.Rating;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author dimon
 * @since 28/03/16.
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), false));
        registry.registerCustomEditor(Auditorium.class, new AuditoriumEditor());
        registry.registerCustomEditor(Rating.class, new RatingEditor());
    }

}
