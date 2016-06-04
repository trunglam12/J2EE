/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.TablecoffeeFacade;
import entities.Tablecoffee;
import java.util.List;
import javax.ejb.EJB;
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
    private TablecoffeeFacade tablecoffeeFacade;

   public List<Tablecoffee> ListAllTable;
   public Tablecoffee _table = new Tablecoffee();
   public int page=1;

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
}
