package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Categories;
import models.Items;
import models.Users;


public class ItemDB {
    
    public List<Items> getAll(String owner) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Users user = em.find(Users.class,owner);
            List<Items> itemList = user.getItemsList();
            return itemList;
        }finally{
            em.close();
        }
    }
    
    public Items get(int itemId) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Items item = em.find(Items.class, itemId);
            return item;
        }finally{
            em.close();
        }
    }
    
    public void insert(Items item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            Users user = item.getOwner();
            user.getItemsList().add(item);
            Categories category = item.getCategory();
            category.getItemsList().add(item);
            trans.begin();
            em.persist(item);
            em.merge(user);
            em.merge(category);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void delete(Items item) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
           Users user = item.getOwner();
           user.getItemsList().remove(item);
           Categories category = item.getCategory();
           category.getItemsList().remove(item);
           trans.begin();
           em.remove(em.merge(item));
           em.merge(user);
           em.merge(category);
           trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
}
