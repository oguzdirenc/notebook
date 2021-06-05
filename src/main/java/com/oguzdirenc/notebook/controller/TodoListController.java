package com.oguzdirenc.notebook.controller;

import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;
import com.oguzdirenc.notebook.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/todoList")
public class TodoListController {

    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/save")
    public ResponseEntity<TodoList> saveTodoList(@Valid @RequestBody TodoListSaveRequest todoListSaveRequest){
        return new ResponseEntity<>(todoListService.saveTodoList(todoListSaveRequest), HttpStatus.CREATED);
    }
}
