package org.skypro.skyshop.service;

import org.skypro.skyshop.model.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        return storageService.getAllSearcheble().stream()
                .filter(src -> src.getName().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}
