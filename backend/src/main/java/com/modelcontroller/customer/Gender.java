package com.modelcontroller.customer;

import java.util.Random;

public enum Gender {
    Male,
    Female;

    public static Gender random() {
        Gender[] values = Gender.values();
        return values[new Random().nextInt(values.length)];
    }
}
