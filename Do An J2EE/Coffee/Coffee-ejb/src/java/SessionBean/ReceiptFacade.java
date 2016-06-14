/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.Receipt;
import entities.User;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
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
public class ReceiptFacade extends AbstractFacade<Receipt> {
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

    public ReceiptFacade() {
        super(Receipt.class);
    }
    

    public List<Receipt> GetRecipeByDate(Date date){
        Query query = getEntityManager().createNamedQuery("Receipt.findByDate");
        query.setParameter("date", date);
        List<Receipt> result = query.getResultList();
        return result;
    }
    
    public List<Receipt> GetRecipeByMonth(int month, int year){
        Query query = getEntityManager().createNamedQuery("Receipt.findByMonth");
        query.setParameter("month", month);
        query.setParameter("year", year);
        List<Receipt> result = query.getResultList();
        return result;
    }
}
