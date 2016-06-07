/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.GroupproductFacade;
import SessionBean.ProductFacade;
import SessionBean.UnitFacade;
import entities.Groupproduct;
import entities.Product;
import entities.Unit;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.richfaces.JsfVersion;

@ManagedBean
@SessionScoped
public class ProductManagedBean {

    @EJB
    private UnitFacade unitFacade;

    @EJB
    private GroupproductFacade groupproductFacade;

    @EJB
    private ProductFacade productFacade;
    
    Product product ;
    Product _insProduct = new Product();
    int page = 1;

   
    Unit _unit = new Unit();
    Groupproduct _groupProduct = new Groupproduct();
   
    List<Product> listAllProduct;
   
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    public Product getInsProduct() {
           return _insProduct;
       }

    public void setInsProduct(Product _insProduct) {
        this._insProduct = _insProduct;
    }
   

    public Unit getUnit() {
        return _unit;
    }

    public void setUnit(Unit _unit) {
        this._unit = _unit;
    }

    public Groupproduct getGroupProduct() {
        return _groupProduct;
    }

    public void setGroupProduct(Groupproduct _groupProduct) {
        this._groupProduct = _groupProduct;
    }
  

    public List<Product> getListAllProduct() {
        return listAllProduct;
    }

    public void setListAllProduct(List<Product> listAllProduct) {
        this.listAllProduct = listAllProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public ProductManagedBean() {
       
    }
    
    public List<Product> GetAllProduct()
    {
        listAllProduct = productFacade.findAll();
        
        return  listAllProduct;
    }
    
    public void EditProduct()
    {
        try
        {
            productFacade.edit(product);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
       
    }
    
    public void AddProduct()
    { 
        try{
            _insProduct.setStatus(true);
        _insProduct.setUnitId(_unit);
        _insProduct.setGroupId(_groupProduct);

        productFacade.create(_insProduct);
       
        _insProduct = new Product(); 
        }
         catch(Exception e)
      {
          e.printStackTrace();
      }
        
      
    }
    
    public List<Groupproduct> GetAllGroupProduct()
    {
        return groupproductFacade.findAll();
    }
    
    public List<Unit> GetAllUnit()
    {
        return unitFacade.findAll();
    }
    
   public void GetProduct(Product product)
    {
        this.product = product;
       _unit=this.product.getUnitId();
       _groupProduct = this.product.getGroupId();
    }
   
   public void DeleteProduct()
   {
       productFacade.remove(product);
      
   }

    public void resetValues() {
        // reset input fields to prevent stuck values after a validation failure
        // not necessary in JSF 2.2+ (@resetValues on a4j:commandButton)
        if (!JsfVersion.getCurrent().isCompliantWith(JsfVersion.JSF_2_2)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            UIComponent comp = fc.getViewRoot().findComponent("form:updateProductGrid");
 
            ((EditableValueHolder) comp.findComponent("form:productName")).resetValue();
            ((EditableValueHolder) comp.findComponent("form:price")).resetValue();
            ((EditableValueHolder) comp.findComponent("form:count")).resetValue();
        }
    }
}
