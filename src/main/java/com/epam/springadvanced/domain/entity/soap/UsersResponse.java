package com.epam.springadvanced.domain.entity.soap;

import com.epam.springadvanced.domain.entity.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
public class UsersResponse {

    private Collection<User> users;

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    public Collection<User> getUsers() {
        return users;
    }

}
