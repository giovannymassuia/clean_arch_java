package io.giovannymassuia.cleanarch.core.domain.entity;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class OrderCode {

    private final String value;

    public OrderCode(LocalDate issueDate, Integer sequence) {
        this.value = issueDate.getYear() + StringUtils.leftPad(sequence.toString(), 8, "0");
    }

    public String getValue() {
        return value;
    }
}
