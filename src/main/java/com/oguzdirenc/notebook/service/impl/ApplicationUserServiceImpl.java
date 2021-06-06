package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.repositories.ApplicationUserRepository;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public ApplicationUser saveApplicationUser(ApplicationUser user) {
        return applicationUserRepository.save(user);
    }


    @Override
    public List<ApplicationUser> getAllUsers() {
        return (List<ApplicationUser>) applicationUserRepository.findAll();
    }

    //1           *
    @Override
    public List<String> getIdListForUsernames(List<String> usernames) {

        //2   *
        List<String> userIdList = new ArrayList<>();

        for(String username : usernames){
            Optional<ApplicationUser> user = applicationUserRepository.findByUsername(username);
            user.ifPresent(applicationUser -> userIdList.add(applicationUser.getApplicationUserId()));
        }
        return userIdList;
    }

    //3          *
    @Override
    public List<String> getTodoIdListForUsername(String username) {
        ApplicationUser user = getUserByUsername(username);
        return user.getTodoIdList();
    }

    @Override
    public ApplicationUser getUserByUsername(String username){
        Optional<ApplicationUser> user = applicationUserRepository.findByUsername(username);
        if (user.isPresent())return user.get();
        else throw new NotFoundException("User not found");
    }

    //2                                               *
    public List<String> getUsernameListByIdList(List<String> userIdList){
        List<String> usernameList = new ArrayList<>();

        //2   *
        for(String userId: userIdList){
            Optional<ApplicationUser> user = applicationUserRepository.findById(userId);
            if(user.isPresent()){
            String username = user.get().getUsername();
            usernameList.add(username);
        }}
        return usernameList;
    }
}
