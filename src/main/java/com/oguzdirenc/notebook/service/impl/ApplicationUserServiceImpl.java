package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.ApplicationUser;
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
        return applicationUserRepository.findAll();
    }

    @Override
    public List<UUID> getIdListForUsernames(List<String> usernames) {
        List<UUID> userIdList = new ArrayList<>();

        for(String username : usernames){
            Optional<ApplicationUser> user = applicationUserRepository.findByUsername(username);
            user.ifPresent((userFound)-> userIdList.add(userFound.getApplicationUserId()));
        }
        return userIdList;
    }
}
