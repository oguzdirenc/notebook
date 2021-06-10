package com.oguzdirenc.notebook.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserValidationExceptionResponse {

    @JsonProperty
    private String message;

    public UserValidationExceptionResponse(String message) {
        this.message = message;
    }
}
