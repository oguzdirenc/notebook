package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.repositories.TodoListRepository;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;
import com.oguzdirenc.notebook.response.UserTodoListResponse;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import com.oguzdirenc.notebook.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public TodoList saveTodoList(TodoListSaveRequest todoListSaveRequest,String username) {

        //todo todolist Id user icerisine eklenecek

        //    *
        List<String> todoUserIdList = applicationUserService.getIdListForUsernames(todoListSaveRequest.getUsernameList());
        ApplicationUser user = applicationUserService.getUserByUsername(username);
        TodoList todoList = TodoList.builder()
                .todoListName(todoListSaveRequest.getTodoListName())
                .todoListDescription(todoListSaveRequest.getTodoListDescription())
                .userIdList(todoUserIdList)
                .build();

        TodoList savedTodoList =todoListRepository.save(todoList);
        user.getTodoIdList().add(savedTodoList.getTodoListId());
        applicationUserService.saveApplicationUser(user);
        return savedTodoList;
    }

    //todo kullaniciya göre özellestir
    //todo itemCount alanini guncelle
    @Override
    public List<UserTodoListResponse> getUserLists(String username) {
        //     *
        List<String> todoIdList = applicationUserService.getTodoIdListForUsername(username);
        List<UserTodoListResponse> userTodoListResponseList = new ArrayList<>();

        //    *
        for(String todoId :todoIdList){
            Optional<TodoList> todo = todoListRepository.findById(todoId);
            if(todo.isPresent()){
                UserTodoListResponse userTodoListResponse= UserTodoListResponse.builder()
                        .todoListHeader(todo.get().getTodoListName())
                        .todoListDescription(todo.get().getTodoListDescription())
                        .listItemCount(1)
                        .usernameList(applicationUserService.getUsernameListByIdList(todo.get().getUserIdList()))
                        .build();
                userTodoListResponseList.add(userTodoListResponse);

            }
        }

        return userTodoListResponseList;
    }
}
