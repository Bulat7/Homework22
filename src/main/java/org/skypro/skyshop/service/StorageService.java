package org.skypro.skyshop.service;

import org.skypro.skyshop.errors.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService () {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();

        initData();
    }

    private void initData () {
        Product fixedBread = new FixPriceProduct("Bread",UUID.randomUUID());
        productMap.put(fixedBread.getId(), fixedBread);
        Product simpleApple = new SimpleProduct("Apple", UUID.randomUUID(), 20);
        productMap.put(simpleApple.getId(), simpleApple);
        Product discountedRise = new DiscountedProduct("Rise", UUID.randomUUID(), 100,20 );
        productMap.put(discountedRise.getId(), discountedRise);
        Product simpleGreenApple = new SimpleProduct("GreenApple", UUID.randomUUID(), 15);
        productMap.put(simpleGreenApple.getId(), simpleGreenApple);
        Product simpleRedApple = new SimpleProduct("RedApple", UUID.randomUUID(),25);
        productMap.put(simpleRedApple.getId(), simpleRedApple);


        Article news1 = new Article(UUID.randomUUID(), "Вести", "Итоговые новости");
        articleMap.put(news1.getId(),news1);
        Article news2 = new Article(UUID.randomUUID(), "РенТВ", "Вечерние новости");
        articleMap.put(news2.getId(),news2);
        Article news3 = new Article(UUID.randomUUID(), "НТВ", "Дневные новости");
        articleMap.put(news3.getId(),news3);

    }

    public Collection<Product> getAllProduct() {
        return this.productMap.values();
    }

    public Collection<Article> getAllArticles() {
        return this.articleMap.values();
    }

    public Collection<Searchable> getAllSearcheble() {
        List<Searchable> allSrc = new ArrayList<>();
        allSrc.addAll(productMap.values());
        allSrc.addAll(articleMap.values());
        return allSrc;

    }

    public Product getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id))
                .orElseThrow(NoSuchProductException::new);
    }
}
