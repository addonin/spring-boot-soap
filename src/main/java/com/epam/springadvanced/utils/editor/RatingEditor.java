package com.epam.springadvanced.utils.editor;

import com.epam.springadvanced.domain.enums.Rating;

import java.beans.PropertyEditorSupport;

/**
 * @author dimon
 * @since 28/03/16.
 */
public class RatingEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String value) throws IllegalArgumentException {
        setValue(Rating.getRating(Integer.parseInt(value)));
    }
}
