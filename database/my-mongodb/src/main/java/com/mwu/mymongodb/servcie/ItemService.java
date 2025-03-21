package com.mwu.mymongodb.servcie;

import com.mwu.mymongodb.model.User;

public interface ItemService {
    public void saveUser(User user);

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserById(Long id);

}
