/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

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
        if(em==null)
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Coffee-ejbPU");
            em = emf.createEntityManager();
        }
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
