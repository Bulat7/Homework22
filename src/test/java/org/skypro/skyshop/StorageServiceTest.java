package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {

    @Mock(name = "productMap")
    Map<UUID, Product> productMap;

    @Mock(name = "articleMap")
    Map<UUID, Article> articleMap;


    StorageService storageService;


    @BeforeEach
    void setUp() {
        this.storageService = new StorageService(productMap, articleMap);
    }

    @Test
    void getProductByIDTest() {
        UUID uuidProduct = UUID.randomUUID();
        Product expectedProduct = new SimpleProduct("Potato", uuidProduct, 50);
        Mockito.when(productMap.get(uuidProduct)).thenReturn(expectedProduct);
        Product actualProduct = storageService.getProductById(uuidProduct);
        Assertions.assertEquals(expectedProduct, actualProduct);
        Mockito.verify(productMap, Mockito.times(1)).get(uuidProduct);
    }

    @Test
    void getArticleBiIDTest() {
        UUID uuidArticles = UUID.randomUUID();
        Article expectedArticle = new Article(uuidArticles, "Magazine", "Fashion");
        Mockito.when(articleMap.get(uuidArticles)).thenReturn(expectedArticle);
        Article actualArticle = storageService.getArticleById(uuidArticles);
        Assertions.assertEquals(expectedArticle, actualArticle);
        Mockito.verify(articleMap, Mockito.times(1)).get(uuidArticles);
    }

    @Test
    void getAllSearchableTest() {
        UUID idProduct = UUID.randomUUID();
        Product testProduct = new SimpleProduct("Orange", idProduct, 100);

        UUID idArticle = UUID.randomUUID();
        Article testArticle = new Article(idArticle, "Gazette", "News");

        Mockito.when((productMap.values())).thenReturn(List.of(testProduct));
        Mockito.when((articleMap.values())).thenReturn(List.of(testArticle));

        Collection <Searchable> actualResult = storageService.getAllSearcheble();

        Assertions.assertEquals(2, actualResult.size());
        Assertions.assertTrue(actualResult.contains(testProduct));
        Assertions.assertTrue(actualResult.contains(testArticle));

        Mockito.verify(productMap, Mockito.times(1)).values();

        Mockito.verify(articleMap, Mockito.times(1)).values();
    }



    @Test
    void getAllSearchable_WhenStorageEmpty() {
        Mockito.when(productMap.values()).thenReturn(List.of());
        Mockito.when(articleMap.values()).thenReturn(List.of());

        Collection <Searchable> result = storageService.getAllSearcheble();

        Assertions.assertTrue(result.isEmpty());

        Mockito.verify(productMap, Mockito.times(1)).values();
        Mockito.verify(articleMap, Mockito.times(1)).values();
    }
}
