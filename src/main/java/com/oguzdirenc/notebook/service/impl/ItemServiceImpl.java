package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.Item;
import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.repositories.ItemRepository;
import com.oguzdirenc.notebook.repositories.TodoListRepository;
import com.oguzdirenc.notebook.request.ItemSaveRequest;
import com.oguzdirenc.notebook.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {


    private final ItemRepository itemRepository;
    private final TodoListRepository todoListRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, TodoListRepository todoListRepository) {
        this.itemRepository = itemRepository;
        this.todoListRepository = todoListRepository;
    }

    @Override
    public Item saveItem(ItemSaveRequest itemSaveRequest) {

        Optional<TodoList> todoList = todoListRepository.findById(itemSaveRequest.getTodoListId());
        if(todoList.isEmpty()) throw new NotFoundException("Todo list not found");

        Item newItem = Item.builder()
                .itemDescription(itemSaveRequest.getItemDescription())
                .todoListId(itemSaveRequest.getTodoListId())
                .itemStatus(Item.ItemStatus.NOTSTARTED)
                .build();

        Item savedItem = itemRepository.save(newItem);

        todoList.get().getItemIdList().add(savedItem.getItemId());
        todoListRepository.save(todoList.get());

        return savedItem;
    }
}
