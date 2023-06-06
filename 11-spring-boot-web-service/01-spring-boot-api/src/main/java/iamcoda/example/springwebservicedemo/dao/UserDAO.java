package iamcoda.example.springwebservicedemo.dao;

import iamcoda.example.springwebservicedemo.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> findALL();

    public User save();

    public User findById();
}
