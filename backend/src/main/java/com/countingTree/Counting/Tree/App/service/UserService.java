package com.countingTree.Counting.Tree.App.service;

import java.util.List;
import com.countingTree.Counting.Tree.App.dto.UserDTO;
import com.countingTree.Counting.Tree.App.model.User;

public interface UserService {

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();
    
    void addUser(User newUser);

    UserDTO updateUser(Long userId, User user);

    void deleteUser(Long userId);

}
