/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author CHRIST
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "Coffee-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em==null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Coffee-ejbPU");
            em = emf.createEntityManager();
        }
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public boolean IsUserAccountExist(String userAccount){
        Query query = getEntityManager().createNamedQuery("User.findByUserAccount");
        query.setParameter("userAccount", userAccount);
        List<User> result = query.getResultList();
        if (result.size() != 0) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public User FindUser(String userAccount, String password) {
        Query query = getEntityManager().createNamedQuery("User.findByUserAccount");
        query.setParameter("userAccount", userAccount);
        List<User> result = query.getResultList();
        if (result.size() != 0) {
            return result.get(0).getPassword().equals(password) ? result.get(0) : null;
        } else {
            return null;
        }
    }

    public boolean Register(String userAccount, String password) {
        try {
            User user = new User();
            user.setUserAccount(userAccount);
            user.setPassword(password);
            super.create(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
