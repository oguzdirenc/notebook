package com.oguzdirenc.notebook.controller;

import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;
import com.oguzdirenc.notebook.response.UserTodoListResponse;
import com.oguzdirenc.notebook.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
    public ResponseEntity<TodoList> saveTodoList(@Valid @RequestBody TodoListSaveRequest todoListSaveRequest, Principal principal){
        return new ResponseEntity<>(todoListService.saveTodoList(todoListSaveRequest, principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserTodoListResponse>> getUserTodoLists(Principal principal){
        return new ResponseEntity<>(todoListService.getUserLists(principal.getName()),HttpStatus.OK);
    }


}