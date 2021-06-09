package com.oguzdirenc.notebook.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdNullExceptionResponse {

    @JsonProperty
    private String message;

    public IdNullExceptionResponse(String message) {
        this.message = message;
    }
}
