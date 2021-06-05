package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;

import java.util.List;

public interface TodoListService {

    TodoList saveTodoList(TodoListSaveRequest todoListSaveRequest);

}
