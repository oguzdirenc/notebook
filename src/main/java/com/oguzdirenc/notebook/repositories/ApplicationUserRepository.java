package com.oguzdirenc.notebook.repositories;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, String> {
 ApplicationUser findByUsername(String username);
 ApplicationUser getByApplicationUserId(String applicationUserId);
}
