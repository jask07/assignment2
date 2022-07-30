/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Categories;
import models.Items;
import models.Users;

/**
 *
 * @author jerom
 */
public class UserService {
    
    public List<Users> getAllUsers() throws Exception {
        UserDB userdb = new UserDB();
        List<Users> users = userdb.getAllUsers();
        return users;
    }
    
    public Users get(String username) throws Exception{
        UserDB userdb = new UserDB();
        Users user = userdb.get(username);
        return user;
    }
    
    public void insert(String username, String password,String email ,String firstName, String lastName) throws Exception{
        UserDB userdb = new UserDB();
        Users user = new Users(username,password,email,firstName,lastName,true,false);
        userdb.insert(user);
    }
    
    public void update(String oldUsername, String newUsername, String password, String email, String firstName, String lastName) throws Exception{
        UserDB userdb = new UserDB();
        Users user = userdb.get(oldUsername);
        user.setUsername(newUsername);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userdb.update(user);
    }
    
    public void delete(String username) throws Exception{
        UserDB userdb = new UserDB();
        Users user = userdb.get(username);
        userdb.delete(user);
    }
}
