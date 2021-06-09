package com.oguzdirenc.notebook.controller;

import com.oguzdirenc.notebook.domain.Item;
import com.oguzdirenc.notebook.request.ItemSaveRequest;
import com.oguzdirenc.notebook.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public ResponseEntity<Item> saveTodoListItem(@Valid @RequestBody ItemSaveRequest itemSaveRequest){
        return new ResponseEntity<>(itemService.saveItem(itemSaveRequest), HttpStatus.CREATED);
    }
}
