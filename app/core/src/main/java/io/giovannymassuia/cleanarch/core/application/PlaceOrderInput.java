package io.giovannymassuia.cleanarch.core.application;

import java.util.List;

public record PlaceOrderInput(
        String cpf,
        String zipcode,
        List<Item> items,
        String coupon) {

    public record Item(String itemId, int quantity) {
    }
}
