package com.epam.springadvanced.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum Role {

    REGISTERED_USER(0, "Registered user"),
    BOOKING_MANAGER(1, "Booking manager");

    private static Map<Integer, Role> roleById = new HashMap<>();

    static {
        for (Role role : Role.values()) {
            if (roleById.put(role.getId(), role) != null) {
                throw new IllegalArgumentException("duplicate id: " + role.getId());
            }
        }
    }

    private int id;
    private String desc;

    Role(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    Role() {}

    public static Role getRoleById(int id) {
        return roleById.get(id);
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

}
