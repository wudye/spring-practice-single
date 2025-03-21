package com.mwu.myv1.model.mongoDB;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mwu.myv1.constant.AppConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "demo_log")
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DemoLog {

    @Field("request_id")
    @Indexed(name = "request_id_index")
    private String requestId;

    @CreatedDate
    @Indexed(name = "created_index", expireAfter = AppConstants.DEMO_LOG_TTL)
    private String created;

}
