/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.GroupproductFacade;
import entities.Groupproduct;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lam
 */
@ManagedBean
@SessionScoped
public class GroupProductManagedBean {

    @EJB
    private GroupproductFacade groupproductFacade;
    
    public List<Groupproduct> ListAllGroupProduct;
    public Groupproduct _groupProduct = new Groupproduct();
    int page =1;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Groupproduct getGroupProduct() {
        return _groupProduct;
    }

    public void setGroupProduct(Groupproduct _groupProduct) {
        this._groupProduct = _groupProduct;
    }

    public List<Groupproduct> getListAllGroupProduct() {
        return ListAllGroupProduct;
    }

    public void setListAllGroupProduct(List<Groupproduct> ListAllGroupProduct) {
        this.ListAllGroupProduct = ListAllGroupProduct;
    }
  
    public List<Groupproduct> GetAllGroupProduct()
    {
        ListAllGroupProduct= groupproductFacade.findAll();
        
        return  ListAllGroupProduct;
    }
    
    public void GetGroupProduct(Groupproduct group)
    {
        _groupProduct = group;
    }
    
    public GroupProductManagedBean() {
    }
    
    public void RemoveGroup()
    {
        groupproductFacade.remove(_groupProduct);
    }
    
    public void EditGroup()
    {
          try
        {
            groupproductFacade.edit(_groupProduct);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public void AddGroup()
    {
         try
        {
            groupproductFacade.create(_groupProduct);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}

