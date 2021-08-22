package io.giovannymassuia.cleanarch.domain.entity;

import java.math.BigDecimal;

public class Item {

    private final String id;
    private final String description;
    private final BigDecimal price;
    private final double width;
    private final double height;
    private final double length;
    private final double weight;

    public Item(String id, String description, BigDecimal price, double width, double height, double length, double weight) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
    }

    public double getVolume() {
        return this.width / 100 * this.height / 100 * this.length / 100;
    }

    public double getDensity() {
        return this.weight / this.getVolume();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
