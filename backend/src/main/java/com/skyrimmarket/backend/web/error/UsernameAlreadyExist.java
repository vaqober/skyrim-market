package com.skyrimmarket.backend.web.error;

import static java.lang.String.format;

public class UsernameAlreadyExist extends BadRequestException {
    public UsernameAlreadyExist(String username) {
        super(format("User already exist: %s", username));
    }
}
