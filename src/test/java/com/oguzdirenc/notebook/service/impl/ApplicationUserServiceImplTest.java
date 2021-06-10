package com.oguzdirenc.notebook.service.impl;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.repositories.ApplicationUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ApplicationUserServiceImplTest {

    @Mock
    ApplicationUserRepository mockUserRepository;

    @Mock
    BCryptPasswordEncoder mockPasswordEncoder;

    @InjectMocks
    ApplicationUserServiceImpl applicationUserService;


    @Test
    @DisplayName("User save test")
    void saveUser() {

        MockitoAnnotations.openMocks(this);

        ApplicationUser user = ApplicationUser.builder()
                .fullName("fullName")
                .username("user@user.com")
                .password("password")
                .build();

        when(mockPasswordEncoder.encode(user.getPassword())).thenReturn("123456");
        when(mockUserRepository.save(user)).thenReturn(user);

        ApplicationUser newUser = applicationUserService.saveUser(user);

        assertEquals("123456",newUser.getPassword());

    }



    @Test
    @DisplayName("Username list empty test")
    void getIdListForUsernamesWhenInputEmpty() {
        MockitoAnnotations.openMocks(this);
        List<String> todoIdList = new ArrayList<>();
        ApplicationUser user = new ApplicationUser();
        when(mockUserRepository.findByUsername("")).thenReturn(user);

        Set<String> result = applicationUserService.getIdListForUsernames(todoIdList);

        assertEquals(0,result.size());
    }

    @Test
    @DisplayName("Get user id list test")
    void getIdListForUsernames() {
        MockitoAnnotations.openMocks(this);
        List<String> todoIdList = Arrays.asList("user1","user2");

        ApplicationUser user1 = ApplicationUser.builder()
                .username("user1")
                .build();

        when(mockUserRepository.findByUsername("user1")).thenReturn(user1);
        Set<String> result = applicationUserService.getIdListForUsernames(todoIdList);

        assertEquals(1,result.size());
    }


}