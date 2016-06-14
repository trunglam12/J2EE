/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.Receiptnote;
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
public class ReceiptnoteFacade extends AbstractFacade<Receiptnote> {
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

    public ReceiptnoteFacade() {
        super(Receiptnote.class);
    }
    
        public List<Receiptnote> GetRecipeNoteByDate(Date date){
        Query query = getEntityManager().createNamedQuery("Receiptnote.findByDate");
        query.setParameter("date", date);
        List<Receiptnote> result = query.getResultList();
        return result;
    }
    
    public List<Receiptnote> GetRecipeNoteByMonth(int month, int year){
        Query query = getEntityManager().createNamedQuery("Receiptnote.findByMonth");
        query.setParameter("month", month);
        query.setParameter("year", year);
        List<Receiptnote> result = query.getResultList();
        return result;
    }
}
