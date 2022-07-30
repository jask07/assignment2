package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Categories;
import models.Users;


public class UserDB {
    
    public List<Users> getAllUsers() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Users> usersList = em.createNamedQuery("Users.findAll",Users.class).getResultList();
            return usersList;
        }finally{
            em.close();
        }
    }
    
    public Users get(String username) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Users user = em.find(Users.class, username);
            return user;
        }finally{
            em.close();
        }
    }
    
    public void insert(Users user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.persist(user);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void update(Users user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(user);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void delete(Users user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
}
