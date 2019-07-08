package com.luo.Dao;

import com.luo.Model.UserEntity;

import java.util.List;

public interface UserDao {
    public List<UserEntity> getUser();

    public UserEntity getUserbyId(Integer id);
}
