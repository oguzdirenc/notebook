package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserService {
    ApplicationUser saveApplicationUser(ApplicationUser user);
    List<ApplicationUser> getAllUsers();
    List<String> getIdListForUsernames(List<String> usernames);
    List<String> getTodoIdListForUsername(String username);
    ApplicationUser getUserByUsername(String username);
    List<String> getUsernameListByIdList(List<String> userIdList);
    ApplicationUser getUserByUserId(String userId);


    void setUserTodoIdList(List<String> userIdList,String todoId);
}
