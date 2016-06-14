/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.Detailreceipt;
import entities.Detailreceiptnote;
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
public class DetailreceiptnoteFacade extends AbstractFacade<Detailreceiptnote> {
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

    public DetailreceiptnoteFacade() {
        super(Detailreceiptnote.class);
    }
    
    public List<Detailreceiptnote> GetDetailByReceiptIdAndProductId(int receiptnoteId, int productId){
        Query query = getEntityManager().createNamedQuery("Detailreceiptnote.findByReceiptnoteIdAndProductId");
        query.setParameter("receiptnoteId", receiptnoteId);
        query.setParameter("productId", productId);
        List<Detailreceiptnote> result = query.getResultList();
        return result;
    }
}
