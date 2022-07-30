package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Categories;
import models.Users;


public class CategoryDB {
    
    public List<Categories> getAllCategories() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Categories> categoryList = em.createNamedQuery("Categories.findAll",Categories.class).getResultList();
            return categoryList;
        }finally{
            em.close();
        }
    }

    public Categories get(int categoryId) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Categories category = em.find(Categories.class, categoryId);
            return category;
        }finally{
            em.close();
        }
    }
}
