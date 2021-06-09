package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.ApplicationUser;

import java.util.List;
import java.util.Set;

public interface ApplicationUserService {
    ApplicationUser saveApplicationUser(ApplicationUser user);
    List<ApplicationUser> getAllUsers();
    Set<String> getIdListForUsernames(List<String> usernames);
    Set<String> getTodoIdListForUsername(String username);
    ApplicationUser getUserByUsername(String username);
    Set<String> getUsernameListByIdList(Set<String> userIdList);
    ApplicationUser getUserByUserId(String userId);
    void setUserTodoIdList(Set<String> userIdList,String todoId);
}
