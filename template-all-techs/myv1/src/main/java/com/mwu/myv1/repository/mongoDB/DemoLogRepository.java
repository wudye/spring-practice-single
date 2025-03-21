package com.mwu.myv1.repository.mongoDB;

import com.mwu.myv1.model.mongoDB.DemoLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoLogRepository extends MongoRepository<DemoLog, String>{
}
