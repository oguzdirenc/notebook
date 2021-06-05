package com.oguzdirenc.notebook.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoListSaveRequest {
    String todoListName;
    String todoListDescription;
    List<String> usernameList;
}
