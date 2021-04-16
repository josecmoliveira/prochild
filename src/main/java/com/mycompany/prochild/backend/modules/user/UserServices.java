/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.user;

import com.mycompany.prochild.backend.models.User;
import java.util.List;

/**
 *
 * @author ruibraga
 */
public class UserServices {
    
    private UserRepository user = new UserRepository();
    
    public List<User> findAllUsers() {
        return user.findAllUsers();
    }
    
    /*public User findUser() {
        return user.findUser();
    }*/
    
    public int insertUser(User new_user) {
        return user.insertUser(new_user);
    }
}
