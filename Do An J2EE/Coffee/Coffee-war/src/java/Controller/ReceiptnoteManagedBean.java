/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.DetailreceiptnoteFacade;
import SessionBean.ProductFacade;
import SessionBean.ProviderFacade;
import SessionBean.ReceiptnoteFacade;
import entities.Detailreceiptnote;
import entities.DetailreceiptnotePK;
import entities.Product;
import entities.Provider;
import entities.Receiptnote;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.resource.NotSupportedException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author CHRIST
 */
@ManagedBean(name = "receiptnoteBean")
@SessionScoped
@TransactionManagement(value = TransactionManagementType.BEAN)
public class ReceiptnoteManagedBean {

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private ReceiptnoteFacade receiptnoteFacade;
    private List<Receiptnote> listReceiptnotes;
    private Receiptnote receiptnote = new Receiptnote();
    private int page = 1;

    @EJB
    private DetailreceiptnoteFacade detailreceiptnoteFacade;
    private Detailreceiptnote detailReceiptnote = new Detailreceiptnote();

    @EJB
    private ProviderFacade providerFacade;
    private Provider provider = new Provider();

    @EJB
    private ProductFacade productFacade;

    public ReceiptnoteManagedBean() {
    }

    public List<Receiptnote> getListReceiptnotes() {
        return listReceiptnotes;
    }

    public void setListReceiptnotes(List<Receiptnote> listReceiptnotes) {
        this.listReceiptnotes = listReceiptnotes;
    }

    public List<Receiptnote> getAllReceiptnotes() {
        this.listReceiptnotes = receiptnoteFacade.findAll();

        return listReceiptnotes;
    }

    public Receiptnote getReceiptnote() {
        return receiptnote;
    }

    public void setReceiptnote(Receiptnote receiptnote) {
        this.receiptnote = receiptnote;
    }

    public Detailreceiptnote getDetailreceiptnote() {
        return this.detailReceiptnote;
    }

    public void setDetailreceiptnote(Detailreceiptnote detailReceiptnote) {
        this.detailReceiptnote = detailReceiptnote;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void createNewReceiptnote() {
        receiptnote = new Receiptnote();
        receiptnote.setDate(Calendar.getInstance().getTime());
    }

    public void createNewDetailReceiptnote() {
        detailReceiptnote = new Detailreceiptnote();
    }

    public void setProductForDetailReceiptnote(Product product) {
        detailReceiptnote.setProduct(product);
    }

    public void addReceiptnote() {
        try {
            receiptnote.setProviderId(provider);
            receiptnoteFacade.create(receiptnote);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editReceiptnote() {
        try {
            provider = providerFacade.find(provider.getProviderId());
            receiptnote.setProviderId(provider);
            receiptnoteFacade.edit(receiptnote);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeReceiptnote() {
        try {
            if (receiptnote.getDetailreceiptnoteList() == null 
                    || receiptnote.getDetailreceiptnoteList().size() == 0)
                receiptnoteFacade.remove(receiptnote);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDetailReceiptnote() {
        detailReceiptnote = new Detailreceiptnote();
        detailReceiptnote.setProduct(new Product());
    }

    public void addDetailReceiptnote() {
        try {
            //chi thuc hien khi da chon product
            if (detailReceiptnote.getProduct().getProductId() != null) {
                int totalCount1 = receiptnote.getTotalCount();
                int totalCount2 = totalCount1 + detailReceiptnote.getCount();
                int productCount1 = detailReceiptnote.getProduct().getCount();
                int productCount2 = productCount1 + detailReceiptnote.getCount();
                boolean addSucceed = false;
                
                if (detailReceiptnote.getDetailreceiptnotePK() == null)
                    detailReceiptnote.setDetailreceiptnotePK(new DetailreceiptnotePK());
                detailReceiptnote.getDetailreceiptnotePK().setProductId(detailReceiptnote.getProduct().getProductId());
                detailReceiptnote.getDetailreceiptnotePK().setReceiptNoteId(receiptnote.getReceiptNoteId());
                addSucceed = receiptnote.getDetailreceiptnoteList().add(detailReceiptnote);
                receiptnote.setTotalCount(totalCount2);
                detailReceiptnote.getProduct().setCount(productCount2);
                detailReceiptnote.getProduct().setStatus(productCount2==0?false:true);
                
                try {
                    userTransaction.begin();
                    productFacade.edit(detailReceiptnote.getProduct());
                    receiptnoteFacade.edit(receiptnote);                   
                    userTransaction.commit();
                } catch (javax.transaction.NotSupportedException ex) {
                    userTransaction.rollback();

                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    userTransaction.rollback();
                    
                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RollbackException ex) {
                    userTransaction.rollback();

                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HeuristicMixedException ex) {
                    userTransaction.rollback();
                    
                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HeuristicRollbackException ex) {
                    userTransaction.rollback();

                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    userTransaction.rollback();

                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    userTransaction.rollback();

                    detailReceiptnote.getProduct().setCount(productCount1);
                    detailReceiptnote.getProduct().setStatus(productCount1==0?false:true);
                    receiptnote.setTotalCount(totalCount1);
                    if (addSucceed) {
                        receiptnote.getDetailreceiptnoteList().remove(detailReceiptnote);
                    }
                    Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editDetailReceiptnote() {
        try {
            int totalCount1 = receiptnote.getTotalCount();
            int totalCount2 = 0;
            for (Detailreceiptnote i : receiptnote.getDetailreceiptnoteList()) {
                totalCount2 += i.getCount();
            }
            int change = totalCount2 - totalCount1;
            int productTotalCount1 = detailReceiptnote.getProduct().getCount();
            int productTotalCount2 = productTotalCount1 + change;
            try {
                //so luong cap nhat < so luong nhap ban dau
                //do san pham vua duoc nhap da duoc ban, nen khong the cap nhat lai
                if (productTotalCount2 < 0) {
                    detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                } else {
                    detailReceiptnote.getProduct().setCount(productTotalCount2);
                    detailReceiptnote.getProduct().setStatus(productTotalCount2==0?false:true);
                    receiptnote.setTotalCount(totalCount2);

                    userTransaction.begin();
                    receiptnoteFacade.edit(receiptnote);
                    productFacade.edit(detailReceiptnote.getProduct());
                    userTransaction.commit();
                }
            } catch (javax.transaction.NotSupportedException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                userTransaction.rollback();
                
                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                userTransaction.rollback();
                
                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount1);
                detailReceiptnote.getProduct().setStatus(productTotalCount1==0?false:true);
                detailReceiptnote.setCount(detailReceiptnote.getCount() - change);
                receiptnote.setTotalCount(totalCount1);
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeDetailReceiptnote() {
        try {
            int totalCount1 = receiptnote.getTotalCount();
            int totalCount2 = totalCount1 - detailReceiptnote.getCount();
            int removeCount = totalCount1 - totalCount2;
            int productTotalCount = detailReceiptnote.getProduct().getCount();
            boolean removeSucceed = false;
            Detailreceiptnote removeItem = null;
            try {
                //chi thuc hien xoa khi so luong xoa <= so luong hien co trong kho
                if (removeCount <= productTotalCount) {
                    detailReceiptnote.getProduct().setCount(productTotalCount - removeCount);
                    detailReceiptnote.getProduct().setStatus((productTotalCount - removeCount)==0?false:true);
                    receiptnote.setTotalCount(totalCount2);
                    for (Detailreceiptnote i : receiptnote.getDetailreceiptnoteList()) {
                        if (i.getDetailreceiptnotePK().equals(detailReceiptnote.getDetailreceiptnotePK())) {
                            removeItem = i;
                            removeSucceed = receiptnote.getDetailreceiptnoteList().remove(i);
                            break;
                        }
                    }
                    //if remove succeed then commit to db
                    if (removeSucceed) {
                        userTransaction.begin();
                        productFacade.edit(detailReceiptnote.getProduct());
                        receiptnoteFacade.edit(receiptnote);
                        detailreceiptnoteFacade.remove(removeItem);
                        userTransaction.commit();
                    } //else roll back
                    else {
                        detailReceiptnote.getProduct().setCount(productTotalCount);
                        detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                        receiptnote.setTotalCount(totalCount1);
                    }
                }
            } catch (javax.transaction.NotSupportedException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                userTransaction.rollback();

                detailReceiptnote.getProduct().setCount(productTotalCount);
                detailReceiptnote.getProduct().setStatus(productTotalCount==0?false:true);
                receiptnote.setTotalCount(totalCount1);
                if (removeSucceed && removeItem != null) {
                    receiptnote.getDetailreceiptnoteList().add(removeItem);
                }
                Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReceiptnoteManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Provider> getAllProviders() {
        return providerFacade.findAll();
    }

    public List<Product> getAllProducts() {
        return productFacade.findAll();
    }
}
