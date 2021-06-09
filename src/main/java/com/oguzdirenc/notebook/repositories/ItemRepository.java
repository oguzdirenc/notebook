package com.oguzdirenc.notebook.repositories;

import com.oguzdirenc.notebook.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,String> {
}
