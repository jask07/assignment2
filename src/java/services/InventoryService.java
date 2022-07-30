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
public class InventoryService {
    
    public List<Items> getAll(String email) throws Exception {
        ItemDB itemdb = new ItemDB();
        List<Items> items = itemdb.getAll(email);
        return items;
    }
    
    public void insert(int itemId, int categoryId, String itemName, double price, String owner ) throws Exception{
        Items item = new Items(itemId,itemName,price);
        UserDB userdb = new UserDB();
        Users user = userdb.get(owner);
        item.setOwner(user);
        CategoryDB catdb = new CategoryDB();
        Categories category = catdb.get(categoryId);
        item.setCategory(category);
        ItemDB itemdb = new ItemDB();
        itemdb.insert(item);
    }
    
    public void delete(int itemId, String owner) throws Exception{
        ItemDB itemdb = new ItemDB();
        Items item = itemdb.get(itemId);
        if (item.getOwner().getUsername().equals(owner)){
            itemdb.delete(item);
        }
    }
}
