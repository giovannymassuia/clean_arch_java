package io.giovannymassuia.cleanarch.core.application;

import java.time.LocalDate;
import java.util.List;

public record PlaceOrderInput(
        String cpf,
        String zipcode,
        List<Item> items,
        String coupon,
        LocalDate issueDate) {

    public PlaceOrderInput(String cpf, String zipcode, List<Item> items, String coupon) {
        this(cpf, zipcode, items, coupon, LocalDate.now());
    }

    public record Item(String itemId, int quantity) {
    }
}
