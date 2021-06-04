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

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz")
    @Email(message = "Kullanıcı adı e-mail adresi olmalıdır")
    private String username;

    @NotBlank(message = "Lütfen adınızı giriniz ")
    private String fullName;

    @NotBlank(message = "Şifre alanı boş bırakılamaz")
    private String password;

    @Transient
    private String confirmPassword;
}
