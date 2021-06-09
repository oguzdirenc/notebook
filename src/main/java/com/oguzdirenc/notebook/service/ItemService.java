package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.Item;
import com.oguzdirenc.notebook.request.ItemSaveRequest;
import com.oguzdirenc.notebook.request.TodoListItemsRequest;

public interface ItemService {

    Item saveItem(ItemSaveRequest itemSaveRequest);
    TodoListItemsRequest getTodoListItems(String todoListId);
    Item getItemById(String itemId);
    String deleteItem(String itemId);
    Item updateItem(Item item);
}
