package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data(staticConstructor = "create")
public class UserDto {

    private final Long id;
}
