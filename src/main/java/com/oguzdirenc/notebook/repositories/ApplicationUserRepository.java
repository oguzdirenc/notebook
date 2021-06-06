package com.oguzdirenc.notebook.repositories;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, String> {
 Optional<ApplicationUser> findByUsername(String username);
}
