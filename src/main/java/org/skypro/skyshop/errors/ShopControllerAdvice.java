package org.skypro.skyshop.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {

@ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductHandler(NoSuchProductException e) {
    ShopError shopError = new ShopError("PRODUCT_NOT_FOUND!", e.getMessage());
    return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
}

}
