package com.epam.springadvanced.service;

import com.epam.springadvanced.domain.entity.UserAccount;

/**
 * @author dmytro_adonin
 * @since 4/1/2016.
 */
public interface UserAccountService {

    UserAccount getUserAccount(long userId);

    void changeAmount(long userId, float amount);

}
