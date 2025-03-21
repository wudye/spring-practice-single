package com.mwu.repository.secondary;

import com.mwu.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {
}
