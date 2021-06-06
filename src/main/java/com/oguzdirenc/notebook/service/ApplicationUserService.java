package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.ApplicationUser;

import java.util.List;
import java.util.UUID;

public interface ApplicationUserService {
    ApplicationUser saveApplicationUser(ApplicationUser user);
    List<ApplicationUser> getAllUsers();
    //1    *
    List<String> getIdListForUsernames(List<String> usernames);
    //2    *
    List<String> getTodoIdListForUsername(String username);
    ApplicationUser getUserByUsername(String username);
    //3                                         *
    List<String> getUsernameListByIdList(List<String> userIdList);
}
