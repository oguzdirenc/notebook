package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserService {
    ApplicationUser saveApplicationUser(ApplicationUser user);
    List<ApplicationUser> getAllUsers();
}
