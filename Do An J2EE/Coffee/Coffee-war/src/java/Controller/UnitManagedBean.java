/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.UnitFacade;
import entities.Unit;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lam
 */
@ManagedBean
@SessionScoped
public class UnitManagedBean {

    @EJB
    private UnitFacade unitFacade;
    
    public List<Unit> ListAllUnit;
    public Unit _unit = new Unit();
    public int page=1;

    public List<Unit> getListAllUnit() {
        return ListAllUnit;
    }

    public void setListAllUnit(List<Unit> ListAllUnit) {
        this.ListAllUnit = ListAllUnit;
    }

    public Unit getUnit() {
        return _unit;
    }

    public void setUnit(Unit _unit) {
        this._unit = _unit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    public UnitManagedBean() {
    }
    
    public List<Unit> GetAllUnit()
    {
        ListAllUnit = unitFacade.findAll();
        return ListAllUnit;
    }
    
    public void GetUnit(Unit unit)
    {
        _unit = unit;
    }
    
     public void RemoveUnit()
    {
        unitFacade.remove(_unit);
    }
    
    public void EditUnit()
    {
          try
        {
            unitFacade.edit(_unit);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public void AddUnit()
    {
         try
        {
            unitFacade.create(_unit);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}
