package com.oguzdirenc.notebook.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundExceptionResponse {

    public NotFoundExceptionResponse(String message) {
        this.message = message;
    }

    @JsonProperty
    private String message;
}
