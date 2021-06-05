package com.oguzdirenc.notebook.repositories;

import com.oguzdirenc.notebook.domain.ApplicationUser;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends CouchbaseRepository<ApplicationUser, UUID> {
 Optional<ApplicationUser> findByUsername(String username);
}
