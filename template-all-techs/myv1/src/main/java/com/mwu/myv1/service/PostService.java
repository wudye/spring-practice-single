package com.mwu.myv1.service;

import com.mwu.myv1.exception.PostNotFoundException;
import com.mwu.myv1.pojo.Post;

public interface PostService {

    Post getLastPost() throws PostNotFoundException;

    void testActiveMQ(String queueName);
}
