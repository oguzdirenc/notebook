package com.oguzdirenc.notebook.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotBlank;
import java.util.*;

@Getter
@Setter
@Document
@Builder
public class TodoList {



    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String todoListId;

    @NotBlank(message = "Todo list name cannot be blank")
    @Field
    private String todoListName;

    @Field
    private String todoListDescription;

    @Field
    private Set<String> userIdList =new HashSet<>();

    @Field
    private Set<String> itemIdList =new HashSet<>();

    @CreatedDate
    @Field
    private Long createdAt = System.currentTimeMillis();

    @LastModifiedDate
    @Field
    private Long updatedAt = System.currentTimeMillis();

    public TodoList(String todoListId,
                    String todoListName,
                    String todoListDescription,
                    Set<String> userIdList,
                    Set<String> itemIdList,
                    Long createdAt,
                    Long updatedAt) {

        this.todoListId = todoListId;
        this.todoListName = todoListName;
        this.todoListDescription = todoListDescription;
        this.userIdList = userIdList;
        this.itemIdList = itemIdList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TodoList() {
    }
}
