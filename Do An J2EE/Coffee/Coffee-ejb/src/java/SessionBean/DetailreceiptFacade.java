/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.Detailreceipt;
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
public class DetailreceiptFacade extends AbstractFacade<Detailreceipt> {
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

    public DetailreceiptFacade() {
        super(Detailreceipt.class);
    }
    
    public List<Detailreceipt> GetDetailByReceiptIdAndProductId(int receiptId, int productId){
        Query query = getEntityManager().createNamedQuery("Detailreceipt.findByReceiptIdAndProductId");
        query.setParameter("receiptId", receiptId);
        query.setParameter("productId", productId);
        List<Detailreceipt> result = query.getResultList();
        return result;
    }
}
