package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.ApplicationUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserService {
    ApplicationUser saveApplicationUser(ApplicationUser user);
    List<ApplicationUser> getAllUsers();
    List<UUID> getIdListForUsernames(List<String> usernames);
}
