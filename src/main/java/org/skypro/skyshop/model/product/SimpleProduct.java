package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {

    private int price;

    public SimpleProduct(String name, UUID id, int price) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Введена некорректная цена.");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + " : " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
