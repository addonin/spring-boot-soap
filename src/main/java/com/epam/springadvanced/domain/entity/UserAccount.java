package com.epam.springadvanced.domain.entity;

/**
 * @author dmytro_adonin
 * @since 4/1/2016.
 */
public class UserAccount {

    private long id;
    private User user;
    private float amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
