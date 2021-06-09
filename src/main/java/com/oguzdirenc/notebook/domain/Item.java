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


@Document
@Getter
@Setter
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @Field
    private String itemId;

    @Field
    @NotBlank(message = "Item description cannot be blank")
    private String itemDescription;

    @Field
    private String todoListId;

    @Field
    private int itemStatus;

    @CreatedDate
    @Field
    private Long createdAt = System.currentTimeMillis();

    @LastModifiedDate
    @Field
    private Long updatedAt = System.currentTimeMillis();


    public Item() {
    }

    public Item(String itemId,
                String itemDescription,
                String todoListId,
                int itemStatus,
                Long createdAt,
                Long updatedAt) {

        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.todoListId = todoListId;
        this.itemStatus = itemStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
