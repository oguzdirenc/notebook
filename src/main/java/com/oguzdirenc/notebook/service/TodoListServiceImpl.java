package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    @Autowired
    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoList saveTodoList(TodoList todoList) {

        return todoListRepository.save(todoList);
    }

    @Override
    public List<TodoList> getAllTodoLists() {
        return todoListRepository.findAll();
    }
}
