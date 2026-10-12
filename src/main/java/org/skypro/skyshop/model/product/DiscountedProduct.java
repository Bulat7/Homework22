package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discount;

    public DiscountedProduct(String name, UUID id, int basePrice, int discount) {
        super(name, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Введена некорректная цена.");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Введена некорректная скидка.");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return basePrice - (basePrice *discount / 100);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " ( " + discount + " %)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}

