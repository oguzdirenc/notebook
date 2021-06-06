package com.oguzdirenc.notebook.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class TodoListSaveRequest {

    @NotBlank(message = "Todo list name cannot be blank")
    String todoListName;

    String todoListDescription;
    List<String> usernameList;

    public TodoListSaveRequest() {
    }

    public TodoListSaveRequest( String todoListName, String todoListDescription, List<String> usernameList) {
        this.todoListName = todoListName;
        this.todoListDescription = todoListDescription;
        this.usernameList = usernameList;
    }
}
