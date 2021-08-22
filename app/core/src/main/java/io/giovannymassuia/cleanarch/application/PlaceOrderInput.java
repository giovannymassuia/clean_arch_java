package io.giovannymassuia.cleanarch.application;

import java.util.List;

public class PlaceOrderInput {

    private final String cpf;
    private final String zipcode;
    private final List<PlaceOrderInput.Item> items;
    private final String coupon;

    public PlaceOrderInput(String cpf, String zipcode, List<PlaceOrderInput.Item> items, String coupon) {
        this.cpf = cpf;
        this.zipcode = zipcode;
        this.items = items;
        this.coupon = coupon;
    }

    public String getCpf() {
        return cpf;
    }

    public String getZipcode() {
        return zipcode;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getCoupon() {
        return coupon;
    }

    public static class Item {
        private final String itemId;
        private final int quantity;

        public Item(String itemId, int quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }

        public String getItemId() {
            return itemId;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
