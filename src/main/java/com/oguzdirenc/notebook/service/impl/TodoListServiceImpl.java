package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.domain.TodoList;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.repositories.TodoListRepository;
import com.oguzdirenc.notebook.request.TodoListSaveRequest;
import com.oguzdirenc.notebook.response.UserTodoListResponse;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import com.oguzdirenc.notebook.service.ItemService;
import com.oguzdirenc.notebook.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        Set<String> todoUserIdList = applicationUserService.getIdListForUsernames(todoListSaveRequest.getUsernameList());
        ApplicationUser user = applicationUserService.getUserByUsername(username);
        todoUserIdList.add(user.getApplicationUserId());
        TodoList todoList = TodoList.builder()
                .todoListName(todoListSaveRequest.getTodoListName())
                .todoListDescription(todoListSaveRequest.getTodoListDescription())
                .userIdList(todoUserIdList)
                .build();

        TodoList savedTodoList =todoListRepository.save(todoList);
        applicationUserService.setUserTodoIdList(todoUserIdList,savedTodoList.getTodoListId());
        return savedTodoList;
    }



    @Override
    public List<UserTodoListResponse> getUserLists(String username) {

        Set<String> todoIdList = applicationUserService.getTodoIdListForUsername(username);
        List<UserTodoListResponse> userTodoListResponseList = new ArrayList<>();


     for(String todoId :todoIdList){
            Optional<TodoList> todo = todoListRepository.findById(todoId);
            if(todo.isPresent()){
                UserTodoListResponse userTodoListResponse= UserTodoListResponse.builder()
                        .todoListId(todo.get().getTodoListId())
                        .todoListHeader(todo.get().getTodoListName())
                        .todoListDescription(todo.get().getTodoListDescription())
                        .listItemCount(todo.get().getItemIdList().size())
                        .usernameList(applicationUserService.getUsernameListByIdList(todo.get().getUserIdList()))
                        .build();
                userTodoListResponseList.add(userTodoListResponse);

            }
        }

        return userTodoListResponseList;
    }

    @Override
    public TodoList getTodoListByID(String todoListId) {
        Optional<TodoList> todoList= todoListRepository.findById(todoListId);
        if(todoList.isEmpty()) throw new NotFoundException("Todo list not found");
        return todoList.get();
    }

    @Override
    public void updatedTodoListSave(TodoList todoList) {
        todoListRepository.save(todoList);
    }

  /*  @Override
    public String deleteTodoList(String todoListId) {
        TodoList todoList = getTodoListByID(todoListId);
        Set<String> userIdList = todoList.getUserIdList();
        Set<String> itemIdList = todoList.getItemIdList();

        itemIdList.forEach(itemService::deleteItem);
        userIdList.forEach(userId-> applicationUserService.deleteTodoListFromUser(userId,todoListId));

        return null;
    }*/
}
