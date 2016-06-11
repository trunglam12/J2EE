/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.ProductFacade;
import SessionBean.TablecoffeeFacade;
import entities.Product;
import entities.Tablecoffee;
import java.util.ArrayList;
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
public class TableManagedBean {

    @EJB
    private ProductFacade productFacade;

    @EJB
    private TablecoffeeFacade tablecoffeeFacade;

   public List<Tablecoffee> ListAllTable;
   public Tablecoffee _table = new Tablecoffee();
   public int page=1;
   private String productIds="";
   private List<Product> listProductOrder = new ArrayList<Product>();

    public List<Product> getListProductOrder() {
        return listProductOrder;
    }

    public void setListProductOrder(List<Product> listProductOrder) {
        this.listProductOrder = listProductOrder;
    }
  

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public List<Tablecoffee> getListAllTable() {
        return ListAllTable;
    }

    public void setListAllTable(List<Tablecoffee> ListAllTable) {
        this.ListAllTable = ListAllTable;
    }

    public Tablecoffee getTable() {
        return _table;
    }

    public void setTable(Tablecoffee _table) {
        this._table = _table;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
   
    public TableManagedBean() {
    }
    
    public List<Tablecoffee> GetAllTable()
    {
        ListAllTable = tablecoffeeFacade.findAll();
        
        return ListAllTable;
    }
    
    public void GetTable(Tablecoffee table)
    {
        _table= table;
    }
    
    public void RemoveTable()
    {
        tablecoffeeFacade.remove(_table);
    }
    
    public void Order(Product product)
    { 
        if(productIds.equals("")) productIds =_table.getProductIds().toString();
        
        if(productIds.equals(""))
        {
            productIds = product.getProductId().toString();
        }
        else
        productIds = productIds+ " " + product.getProductId();
        
        _table.setProductIds(productIds);
    }
    
    public void RemoveOrder(Product product)
    { 
        for(int i=0;i<listProductOrder.size();i++)
        {
            if(listProductOrder.get(i).getProductId()==product.getProductId())
            {
                listProductOrder.remove(listProductOrder.get(i));
                break;
            }
        }
        
        productIds ="";
        
         for(int i=0;i<listProductOrder.size();i++)
        {
            productIds = productIds+ " " + listProductOrder.get(i).getProductId();
        }
        
         productIds = productIds.trim();
        
         _table.setProductIds(productIds);
    }
    
    public void EditTable()
    {
          try
        {
            tablecoffeeFacade.edit(_table);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public void AddTable()
    {
         try
        {
            tablecoffeeFacade.create(_table);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public String GetStatus(boolean status)
    {
        if(status==true) return "Bàn Trống";
        else 
            return "Bàn đã có người ngồi!";
    }
    
    public void GetListProductOrder(Tablecoffee table)
    {
        listProductOrder.clear();
        _table = table;
        
        String productids =  _table.getProductIds();
        String id;
        
        while( !productids.equals(""))
        {
           if(productids.indexOf(" ")!= -1)
           {
                 id = productids.substring(0,productids.indexOf(" "));
                  productids = productids.substring(productids.indexOf(" "), productids.length()).trim();
           }
           else 
           {
                 id = productids;
                 productids="";
           }

           listProductOrder.add(productFacade.find(Integer.parseInt(id)));
        }
    }
}
