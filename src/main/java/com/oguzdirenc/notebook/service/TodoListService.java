package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;
import com.oguzdirenc.notebook.response.UserTodoListResponse;

import java.util.List;

public interface TodoListService {

    TodoList saveTodoList(TodoListSaveRequest todoListSaveRequest,String username);
    List<UserTodoListResponse> getUserLists(String username);
    TodoList getTodoListByID(String todoListId);
    void updatedTodoListSave(TodoList todoList);
   // String deleteTodoList(String todoListId);
}
