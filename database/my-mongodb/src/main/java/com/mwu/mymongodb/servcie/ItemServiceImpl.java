package com.mwu.mymongodb.servcie;

import com.mongodb.client.result.UpdateResult;
import com.mwu.mymongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    // secod way to write query in repository
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);

    }

    @Override
    public User findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("name").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public long updateUser(User user) {

        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update()
                .set("name", user.getName())
                .set("passWord", user.getPassWord());
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        if(result!=null)
            return result.getMatchedCount();
        return 0;
    }

    @Override
    public void deleteUserById(Long id) {

        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);

    }



}
