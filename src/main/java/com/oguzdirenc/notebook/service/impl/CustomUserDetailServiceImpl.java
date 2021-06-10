package com.oguzdirenc.notebook.service.impl;


import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.repositories.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       ApplicationUser user = applicationUserRepository.findByUsername(s);
        if(user == null)  throw new  UsernameNotFoundException("User not found");
        return user;
    }

    @Transactional
    public ApplicationUser loadUserById(String userId){
        ApplicationUser user = applicationUserRepository.getByApplicationUserId(userId);
        if(user == null)  throw new  UsernameNotFoundException("User not found");
        return user;
    }
}
