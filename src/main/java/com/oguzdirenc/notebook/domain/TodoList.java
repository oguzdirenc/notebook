package com.oguzdirenc.notebook.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
@Builder
public class TodoList {


//1
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String todoListId;

    @NotBlank(message = "Todo list name cannot be blank")
    @Field
    private String todoListName;

    @Field
    private String todoListDescription;

    //2
    @Field
    private List<String> userIdList =new ArrayList<>();


    public TodoList(String todoListId,String todoListName, String todoListDescription, List<String> userIdList) {
        this.todoListId = todoListId;
        this.todoListName = todoListName;
        this.todoListDescription = todoListDescription;
        this.userIdList = userIdList;
    }

    public TodoList() {
    }
}
