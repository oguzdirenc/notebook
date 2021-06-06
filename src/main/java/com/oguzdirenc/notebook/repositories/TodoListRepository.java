package com.oguzdirenc.notebook.repositories;

import com.oguzdirenc.notebook.domain.TodoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TodoListRepository extends CrudRepository<TodoList, String> {

}
