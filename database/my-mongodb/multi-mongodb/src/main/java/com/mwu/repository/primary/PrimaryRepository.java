package com.mwu.repository.primary;

import com.mwu.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {
}
