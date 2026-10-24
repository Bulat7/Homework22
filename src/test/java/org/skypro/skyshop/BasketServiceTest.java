package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.errors.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    StorageService storageService;

    @Mock
    ProductBasket productBasket;

    @InjectMocks
    BasketService basketService;


    @Test

    void addProduct_TrowExceptionIfProductDoesntExist() {
        UUID id = UUID.randomUUID();
        Mockito.when(storageService.getProductById(id)).thenThrow(new NoSuchProductException());
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void addProduct_ShouldCallBasket_WhenProductExist() {
        UUID id = UUID.randomUUID();
        Product someProduct = Mockito.mock(Product.class);
        Mockito.when(storageService.getProductById(id)).thenReturn(someProduct);
        basketService.addProduct(id);
        Mockito.verify(productBasket).addProduct(id);
    }

    @Test
    void getUserBasket_ShouldReturnEmptyBasket_WhenProductBasketEmpty() {
        Mockito.when(productBasket.getAllProducts()).thenReturn(Map.of());
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertTrue(userBasket.getItems().isEmpty());
        Assertions.assertEquals(0, userBasket.getTotal());
    }

    @Test
    void getUserBasket_WhenUsersBasketIsNotEmpty() {
        UUID uuid = UUID.randomUUID();
        Product product = Mockito.mock(Product.class);
        Mockito.when(productBasket.getAllProducts()).thenReturn(Map.of(uuid, 1));
        Mockito.when(storageService.getProductById(uuid)).thenReturn(product);
        Mockito.when(product.getPrice()).thenReturn(100);

        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertEquals(1, userBasket.getItems().size());
        Assertions.assertEquals(100, userBasket.getTotal());

        BasketItem basketItem = userBasket.getItems().get(0);
        Assertions.assertEquals(product, basketItem.getProduct());
        Assertions.assertEquals(1, basketItem.getQuantity());
    }

}
