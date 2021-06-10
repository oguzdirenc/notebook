package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.exception.UsernameAlreadyExistsException;
import com.oguzdirenc.notebook.repositories.ApplicationUserRepository;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ApplicationUser saveUser(ApplicationUser newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword("");
            return applicationUserRepository.save(newUser);
        }catch (Exception err) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already registered ");
        }
    }



    @Override
    public Set<String> getIdListForUsernames(List<String> usernames) {

        Set<String> userIdList = new HashSet<>();
        for(String username : usernames){
            ApplicationUser user = applicationUserRepository.findByUsername(username);
            if(user != null){userIdList.add(user.getApplicationUserId());}

        }
        return userIdList;
    }


    @Override
    public Set<String> getTodoIdListForUsername(String username) {
        ApplicationUser user = getUserByUsername(username);
        return user.getTodoIdList();
    }

    @Override
    public ApplicationUser getUserByUsername(String username){
       ApplicationUser user = applicationUserRepository.findByUsername(username);
        if (Objects.nonNull(user))return user;
        else throw new NotFoundException("User not found");
    }

    @Override
    public ApplicationUser getUserByUserId(String userId){
        Optional<ApplicationUser> user = applicationUserRepository.findById(userId);
        if(user.isPresent()) return user.get();
        else throw new NotFoundException("User not found");
    }

    @Override
    public Set<String> getUsernameListByIdList(Set<String> userIdList){
        Set<String> usernameList = new HashSet<>();


        for(String userId: userIdList){
            Optional<ApplicationUser> user = applicationUserRepository.findById(userId);
            if(user.isPresent()){
            String username = user.get().getUsername();
            usernameList.add(username);
        }}
        return usernameList;
    }

    @Override
    public void setUserTodoIdList(Set<String> usernameIdList, String todoId) {
        Set<ApplicationUser> userList = new HashSet<>();
        for(String userId : usernameIdList){
            userList.add(getUserByUserId(userId));
        }
        for(ApplicationUser user : userList){
            user.getTodoIdList().add(todoId);
            applicationUserRepository.save(user);
        }
    }

  /*  @Override
    public void deleteTodoListFromUser(String userId,String todoListId) {
        ApplicationUser user = getUserByUserId(userId);
        user.setTodoIdList( user.getTodoIdList().stream()
                .filter(savedTodoId-> !savedTodoId.equals(todoListId))
                .collect(Collectors.toSet()));
        applicationUserRepository.save(user);
    }*/
}
