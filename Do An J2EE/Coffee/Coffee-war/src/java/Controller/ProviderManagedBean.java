/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.ProviderFacade;
import entities.Provider;
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
public class ProviderManagedBean {
    @EJB
    private ProviderFacade providerFacade;
    private List<Provider> listProviders;
    private Provider provider = new Provider();
    private int page = 1;
    
    public ProviderManagedBean(){
    }
    
    public List<Provider> getListProviders(){
        return listProviders;
    }
    public void setListProviders(List<Provider> listProviders){
        this.listProviders = listProviders;
    }
    
    public List<Provider> getAllProviders(){
        List<Provider> providers = providerFacade.findAll();
        
        return providers;
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

    public void addProvider(){
        try{
            providerFacade.create(provider);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void editProvider(){
        try{
            providerFacade.edit(provider);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void removeProvider(){
        try{
            providerFacade.remove(provider);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
