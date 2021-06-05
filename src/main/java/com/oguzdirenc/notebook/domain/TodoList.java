package com.oguzdirenc.notebook.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.UUID;

@Getter
@Setter
@Document
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private UUID todoListId;

    private String todoListName;



}
