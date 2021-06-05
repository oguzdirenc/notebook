package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.TodoList;

import java.util.List;

public interface TodoListService {

    TodoList saveTodoList(TodoList todoList);

    List<TodoList> getAllTodoLists();
}
