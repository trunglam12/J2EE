/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import entities.Detailreceipt;
import java.util.Date;
import java.util.ArrayList;
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

    public List<Detailreceipt> GetAllDetailByReceiptID(int idReceipt, int idProduct)
    {
        List<Detailreceipt> listdetail = new ArrayList<Detailreceipt>();
        
        for(int i=0;i<findAll().size();i++)
        {
            if(findAll().get(i).getDetailreceiptPK().getReceiptId()==idReceipt && findAll().get(i).getDetailreceiptPK().getProductId()==idProduct )
            {
                listdetail.add(findAll().get(i));
            }
        }
        return listdetail;
    }
    
     public List<Detailreceipt> GetAllDetailByReceiptID(int idReceipt)
    {
        List<Detailreceipt> listdetail = new ArrayList<Detailreceipt>();
        
        for(int i=0;i<findAll().size();i++)
        {
            if(findAll().get(i).getDetailreceiptPK().getReceiptId()==idReceipt)
            {
                listdetail.add(findAll().get(i));
            }
        }
        return listdetail;
    }
     
     public long TotalPrice(List<Detailreceipt> list)
     {
         long price= 0;
         for(int i=0;i<list.size();i++)
        {
            price = price+ list.get(i).getCount() * list.get(i).getProduct().getPrice();
        }
         return price;
     }
}
