package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.repositories.TodoListRepository;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import com.oguzdirenc.notebook.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final ApplicationUserService applicationUserService;


    @Autowired
    public TodoListServiceImpl(TodoListRepository todoListRepository, ApplicationUserService applicationUserService) {
        this.todoListRepository = todoListRepository;
        this.applicationUserService = applicationUserService;
    }

    @Override
    public TodoList saveTodoList(TodoListSaveRequest todoListSaveRequest) {

        //todo todolist Id user icerisine eklenecek

        List<UUID> userIdList = applicationUserService.getIdListForUsernames(todoListSaveRequest.getUsernameList());

        TodoList todoList = TodoList.builder()
                .todoListName(todoListSaveRequest.getTodoListName())
                .todoListDescription(todoListSaveRequest.getTodoListDescription())
                .userIdList(userIdList)
                .build();

        return todoListRepository.save(todoList);
    }
}
