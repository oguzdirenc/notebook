package com.oguzdirenc.notebook.request;

import com.oguzdirenc.notebook.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TodoListItemsRequest {

    private String todoListHeader;
    private String todoListDescription;
    private List<Item> todoListItems;

    public TodoListItemsRequest(String todoListHeader, String todoListDescription, List<Item> todoListItems) {
        this.todoListHeader = todoListHeader;
        this.todoListDescription = todoListDescription;
        this.todoListItems = todoListItems;
    }
}
