package com.skyrimmarket.backend.web.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeOrderForm {
    private final String comment;
    private final String droppoint;
}
