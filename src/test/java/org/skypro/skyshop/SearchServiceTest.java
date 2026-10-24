package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    StorageService storageService;

    @InjectMocks
    SearchService searchService;


    @Test
    void searchShouldReturnEmpty_WhenItemDontSuite(){
        Mockito.when(storageService.getAllSearcheble()).thenReturn(List.of());
        Collection<SearchResult> searchResults = searchService.search("Something");
        Assertions.assertTrue(searchResults.isEmpty());
        Mockito.verify(storageService).getAllSearcheble();

    }

    @Test
    void searchShouldReturnItem_WhenQueryMatch() {
        UUID itemID = UUID.randomUUID();
        String itemName = "Cheri";
        String itemType = "PRODUCT";

        Searchable searchableItem = Mockito.mock(Searchable.class);
        Searchable nonSearchableItem = Mockito.mock(Searchable.class);

        Mockito.when(searchableItem.getName()).thenReturn(itemName);
        Mockito.when(searchableItem.getContentType()).thenReturn(itemType);
        Mockito.when(searchableItem.getId()).thenReturn(itemID);

        Mockito.when(nonSearchableItem.getName()).thenReturn("Tomato");
        Mockito.when(storageService.getAllSearcheble()).thenReturn(List.of(searchableItem, nonSearchableItem));
        Collection<SearchResult> searchResults = searchService.search("Cheri");
        Assertions.assertEquals(1, searchResults.size());
        SearchResult result = searchResults.iterator().next();
        Assertions.assertEquals(itemName, result.getName());
    }

    @Test
    void searchShouldReturnNothing_WhenItemsDontExists() {
        String expectedItem = "Orange";
        Searchable searchableItem = Mockito.mock(Searchable.class);
        Mockito.when(searchableItem.getName()).thenReturn(expectedItem);
        Mockito.when(storageService.getAllSearcheble()).thenReturn(List.of(searchableItem));
        Collection <SearchResult> searchResults = searchService.search("Tomato");
        Assertions.assertEquals(0, searchResults.size());
    }






}
