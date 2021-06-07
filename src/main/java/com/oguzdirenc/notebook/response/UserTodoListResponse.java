package com.oguzdirenc.notebook.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserTodoListResponse {

    String todoListId;
    String todoListHeader;
    String todoListDescription;
    List<String> usernameList;
    Integer listItemCount;

    public UserTodoListResponse() {
    }

    public UserTodoListResponse(String todoListId, String todoListHeader, String todoListDescription, List<String> usernameList, Integer listItemCount) {
        this.todoListId=todoListId;
        this.todoListHeader = todoListHeader;
        this.todoListDescription = todoListDescription;
        this.usernameList = usernameList;
        this.listItemCount = listItemCount;
    }
}