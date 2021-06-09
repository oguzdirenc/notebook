package com.oguzdirenc.notebook.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ItemSaveRequest {

    @NotBlank(message = "Item description cannot be blank")
    private String itemDescription;

    private String todoListId;

    public ItemSaveRequest() {
    }

    public ItemSaveRequest(String itemDescription, String todoListId) {
        this.itemDescription = itemDescription;
        this.todoListId = todoListId;
    }
}
