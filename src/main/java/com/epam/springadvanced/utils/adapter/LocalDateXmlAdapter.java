package com.epam.springadvanced.utils.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author dimon
 * @since 11/04/16.
 */
public class LocalDateXmlAdapter extends XmlAdapter<Date, LocalDate> {

    @Override
    public LocalDate unmarshal(Date date) throws Exception {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public Date marshal(LocalDate localDate) throws Exception {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

}
