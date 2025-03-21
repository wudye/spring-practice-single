package com.mwu.mymongodb.repository;

import com.mwu.mymongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<User, String> {

    @Query("{name: '?0'}")
    User findItemByName(String name);

    @Query(value = "{name: '?0'}", fields = "{'name': 1, 'passWord': 1}")
    List<User> findAll(String name);

    public long count();

}
