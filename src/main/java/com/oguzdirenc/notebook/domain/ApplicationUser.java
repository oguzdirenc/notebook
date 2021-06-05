package com.oguzdirenc.notebook.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Document
@Setter
@Getter
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private UUID applicationUserId;

    @NotBlank(message = "Username cannot be blank")
    @Email(message = "Username must be an e-mail")
    private String username;

    @NotBlank(message = "Fullname cannot be blank")
    private String fullName;

    @NotBlank(message = "Password field cannot be blank")
    private String password;

    @Transient
    private String confirmPassword;

    public ApplicationUser() {
    }

    public ApplicationUser(UUID applicationUserId, String username, String fullName,String password, String confirmPassword) {
        this.applicationUserId = applicationUserId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
