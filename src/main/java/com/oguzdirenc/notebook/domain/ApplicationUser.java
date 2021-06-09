package com.oguzdirenc.notebook.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Document
@Setter
@Getter
public class ApplicationUser {
//1
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @Field
    private String applicationUserId;

    @NotBlank(message = "Username cannot be blank")
    @Email(message = "Username must be an e-mail")
    @Field
    private String username;

    @NotBlank(message = "Fullname cannot be blank")
    @Field
    private String fullName;

    @NotBlank(message = "Password field cannot be blank")
    @Field
    private String password;

    @Transient
    private String confirmPassword;


    @Field
    private Set<String> TodoIdList = new HashSet<>();

    public ApplicationUser() {
    }

    public ApplicationUser(String applicationUserId,
                           String username,
                           String fullName,
                           String password,
                           String confirmPassword,
                           Set<String> todoIdList) {
        this.applicationUserId = applicationUserId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        TodoIdList = todoIdList;
    }
}
