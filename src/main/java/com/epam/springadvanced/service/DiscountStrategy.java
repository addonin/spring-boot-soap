package com.epam.springadvanced.service;

import com.epam.springadvanced.domain.entity.User;

public interface DiscountStrategy {

    float calculate(User user);

}
