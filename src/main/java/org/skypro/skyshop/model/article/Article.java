package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String name;
    private final String article;
    private final UUID id;

    public Article(UUID id,String name, String article) {
        this.id = id;
        this.name = name;
        this.article = article;
    }

    @Override
    public String toString() {
        return name + "\n" + article;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String searchTerm() {
        return name + "\n" + article;
    }
    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof Article)) return false;
        return Objects.equals(name, ((Article)obj).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getArticle() {
        return article;
    }
}
