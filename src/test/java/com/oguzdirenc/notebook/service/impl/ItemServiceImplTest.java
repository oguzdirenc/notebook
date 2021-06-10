package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.Item;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.repositories.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest {

    @Mock
    ItemRepository mockRepository;

    @InjectMocks
    ItemServiceImpl mockItemService ;

    @Test
    @DisplayName("Item not found exception test")
    void updateItem() {
        MockitoAnnotations.openMocks(this);

        Item item = Item.builder()
                .itemDescription("Description")
                .itemId("123345")
                .itemStatus(2)
                .build();

        Optional<Item> object = Optional.empty();

        when(mockRepository.findById(item.getItemId())).thenReturn(object);
        when(mockRepository.save(item)).thenReturn(item);

        assertThrows(NotFoundException.class,() ->mockItemService.updateItem(item));

    }
}