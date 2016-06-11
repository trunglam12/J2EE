/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.ProviderFacade;
import SessionBean.ReceiptnoteFacade;
import entities.Provider;
import entities.Receiptnote;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author CHRIST
 */
@ManagedBean
@SessionScoped
public class ReceiptnoteManagedBean {
    @EJB
    private ReceiptnoteFacade receiptnoteFacade;
    private List<Receiptnote> listReceiptnotes;
    private Receiptnote receiptnote = new Receiptnote();
    private int page = 1;
    
    @EJB
    private ProviderFacade providerFacade;
    private Provider provider = new Provider();
    
    public ReceiptnoteManagedBean(){
    }
    
    public List<Receiptnote> getListReceiptnotes(){
        return listReceiptnotes;
    }
    public void setListReceiptnotes(List<Receiptnote> listReceiptnotes){
        this.listReceiptnotes = listReceiptnotes;
    }
    
    public List<Receiptnote> getAllReceiptnotes(){
        this.listReceiptnotes = receiptnoteFacade.findAll();
        
        return listReceiptnotes;
    }
    
    public Receiptnote getReceiptnote(){
        return receiptnote;
    }
    public void setReceiptnote(Receiptnote receiptnote){
        this.receiptnote = receiptnote;
    }
    
    public Provider getProvider(){
        return provider;
    }
    public void setProvider(Provider provider){
        this.provider = provider;
    }
    
    public int getPage(){
        return this.page;
    }
    public void setPage(int page){
        this.page = page;
    }
    
    public void addReceiptnote(){
        try{
            receiptnote.setProviderId(provider);
            receiptnoteFacade.create(receiptnote);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void editReceiptnote(){
        try{
            provider = providerFacade.find(provider.getProviderId());
            receiptnote.setProviderId(provider);
            receiptnoteFacade.edit(receiptnote);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void removeReceiptnote(){
        try{
            receiptnoteFacade.remove(receiptnote);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public List<Provider> getAllProviders(){
        return providerFacade.findAll();
    }
}
