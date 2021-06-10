package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.Item;
import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.enums.ItemStatus;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.repositories.ItemRepository;
import com.oguzdirenc.notebook.request.ItemSaveRequest;
import com.oguzdirenc.notebook.request.TodoListItemsRequest;
import com.oguzdirenc.notebook.service.ItemService;
import com.oguzdirenc.notebook.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {


    private final ItemRepository itemRepository;
    private final TodoListService todoListService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, TodoListService todoListService) {
        this.itemRepository = itemRepository;
        this.todoListService = todoListService;
    }

    @Override
    public Item saveItem(ItemSaveRequest itemSaveRequest) {

       TodoList todoList = todoListService.getTodoListByID(itemSaveRequest.getTodoListId());

        Item newItem = Item.builder()
                .itemDescription(itemSaveRequest.getItemDescription())
                .todoListId(itemSaveRequest.getTodoListId())
                .itemStatus(ItemStatus.NOTSTARTED.getValue())
                .build();

        Item savedItem = itemRepository.save(newItem);

        todoList.getItemIdList().add(savedItem.getItemId());
        todoListService.updatedTodoListSave(todoList);

        return savedItem;
    }

    @Override
    public TodoListItemsRequest getTodoListItems(String todoListId) {

        TodoList todoList = todoListService.getTodoListByID(todoListId);

        Set<String> itemIdList = todoList.getItemIdList();
        List<Item> itemList = new ArrayList<>();
        itemIdList.forEach(itemId -> {
            Item item = getItemById(itemId);
            itemList.add(item);
        });

        return TodoListItemsRequest.builder()
                .todoListItems(itemList)
                .todoListHeader(todoList.getTodoListName())
                .todoListDescription(todoList.getTodoListDescription())
                .build();
    }

    @Override
    public Item getItemById(String itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty()) throw new NotFoundException("Item not found");
        return item.get();
    }

    @Override
    public String deleteItem(String itemId) {

        Optional<Item> deletedItem = itemRepository.findById(itemId);
        if(deletedItem.isEmpty()) throw new NotFoundException("Item not found");

        String todoListId = deletedItem.get().getTodoListId();
        itemRepository.delete(deletedItem.get());

        TodoList todoList = todoListService.getTodoListByID(todoListId);
        Set<String> newItemIdList = todoList.getItemIdList().stream()
                .filter(id -> !id.equals(itemId))
                .collect(Collectors.toSet());
        todoList.setItemIdList(newItemIdList);
        todoListService.updatedTodoListSave(todoList);
        return itemId;
    }

    @Override
    public Item updateItem(Item item) {
        Optional<Item> updatedItem = itemRepository.findById(item.getItemId());
        if(updatedItem.isEmpty()) throw new NotFoundException("Item not found");

        updatedItem.get().setItemDescription(item.getItemDescription());
        updatedItem.get().setItemStatus(item.getItemStatus());

        return  itemRepository.save(updatedItem.get());
    }
}
