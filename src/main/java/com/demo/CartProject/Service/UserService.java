package com.demo.CartProject.Service;

import com.demo.CartProject.Common.BaseResponse;
import com.demo.CartProject.Entity.User;

import java.util.List;

public interface UserService {

    public void addUser(User param);

    public User getUserById(Long id);

    public List<User> getUsers();

    public User updateUser(Long id, User param);

    void deleteUser(Long id);

}
