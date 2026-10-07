package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getName();
    String searchTerm();
    String getContentType();
    default String getStringRepresentation(){
        return searchTerm() + " - " + getContentType();
    }
    UUID getId();

}
