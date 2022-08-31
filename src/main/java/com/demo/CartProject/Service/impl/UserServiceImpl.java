package com.demo.CartProject.Service.impl;

import com.demo.CartProject.Entity.User;
import com.demo.CartProject.Repository.UserRepository;
import com.demo.CartProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User param){
        param.setCreatedBy("User");
        param.setIsDeleted(0);
        Date today = Date.from(Instant.now());
        param.setCreatedDate(today);
        userRepository.save(param);
    }

    @Override
    public User getUserById(Long id) {
       return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User param) {
        //Cari User berdasarkan id
        User userId = userRepository.findById(id).get();
        userId.setIsDeleted(0);
        userId.setName(param.getName());
        userId.setEmail(param.getEmail());
        userId.setPassword(param.getPassword());
        return userRepository.save(userId);
    }

    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }
}
