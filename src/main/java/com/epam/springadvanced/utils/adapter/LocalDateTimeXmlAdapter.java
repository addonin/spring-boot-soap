package com.epam.springadvanced.utils.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author dimon
 * @since 11/04/16.
 */
public class LocalDateTimeXmlAdapter extends XmlAdapter<Date, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(Date date) throws Exception {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public Date marshal(LocalDateTime localDateTime) throws Exception {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}