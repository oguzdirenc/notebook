package com.oguzdirenc.notebook.repositories;

import com.oguzdirenc.notebook.domain.TodoList;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.UUID;

public interface TodoListRepository extends CouchbaseRepository<TodoList, UUID> {
}
