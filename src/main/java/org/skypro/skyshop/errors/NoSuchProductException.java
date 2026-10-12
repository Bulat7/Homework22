package org.skypro.skyshop.errors;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException() {
        super("Товар не найден!");
    }



}
