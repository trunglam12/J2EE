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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import org.richfaces.JsfVersion;
import org.richfaces.model.Filter;


@ManagedBean
@SessionScoped
public class ProductManagedBean {

    @EJB
    private UnitFacade unitFacade;

    @EJB
    private GroupproductFacade groupproductFacade;

    @EJB
    private ProductFacade productFacade;

    Product product;
    Product _insProduct = new Product();
    int page = 1;
    boolean productStatus;
    String productName;

    private Part File;
    private String imagePath;
    
    Unit _unit = new Unit();
    Groupproduct _groupProduct = new Groupproduct();

    List<Product> listAllProduct;
    List<Groupproduct> listFilter = new ArrayList<Groupproduct>();

    Groupproduct filterGroup;

      public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Part getFile() {
        return File;
    }

    public void setFile(Part File) {
        this.File = File;
    }
   
    public String getProductName() {
       
        return productName;
        
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public Groupproduct getFilterGroup() {
        return filterGroup;
    }

    public void setFilterGroup(Groupproduct filterGroup) {
        this.filterGroup = filterGroup;
    }

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
       
        
        Groupproduct group = new Groupproduct();
        group.setGroupName("Lấy tất cả");
        group.setGroupId(0);
        filterGroup  = group;
      productName = "";
       productStatus = true;
    }

    public List<Product> GetAllProduct() {
        listAllProduct = productFacade.findAll();

        return listAllProduct;
    }

    public void EditProduct() {
        try {
            if (product.getCount() > 0) {
                product.setStatus(true);
            } else {
                product.setStatus(false);
            }

            productFacade.edit(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void AddProduct() {
        try {

            if (_insProduct.getCount() > 0) {
                _insProduct.setStatus(true);
            } else {
                _insProduct.setStatus(false);
            }
            
            _insProduct.setImage(imagePath);
            _insProduct.setUnitId(_unit);
            _insProduct.setGroupId(_groupProduct);

            productFacade.create(_insProduct);

            _insProduct = new Product();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Groupproduct> GetAllGroupProduct() {
        return groupproductFacade.findAll();
    }

    public List<Groupproduct> GetAllGroupFilter() {
        listFilter.clear();

        Groupproduct group = new Groupproduct();
        group.setGroupName("Lấy tất cả");
        group.setGroupId(0);

        listFilter.add(group);

        for (int i = 0; i < groupproductFacade.findAll().size(); i++) {
            listFilter.add(groupproductFacade.findAll().get(i));
        }
        return listFilter;
    }

    public void ResetFilter() {
        Groupproduct group = new Groupproduct();
        group.setGroupName("Lấy tất cả");
        group.setGroupId(0);
        filterGroup  = group;
        
        productStatus = true;
    }

    public List<Unit> GetAllUnit() {
        return unitFacade.findAll();
    }

    public void GetProduct(Product product) {
        this.product = product;
        _unit = this.product.getUnitId();
        _groupProduct = this.product.getGroupId();
    }

    public void DeleteProduct() {
        
        productFacade.remove(product);

    }
    
    public Filter<?> getGroupFilter() {
        return (Product item) -> {
            Groupproduct groupFilter = getFilterGroup();
            if ( groupFilter.getGroupId()== 0 ||groupFilter.getGroupId()==null|| groupFilter.getGroupId()== item.getGroupId().getGroupId()) {
                return true;
            }
            return false;
        };
    }
    
        
    public Filter<?> getProductNameFilter() {
        return (Product item) -> {
            String productName = getProductName();
            if ( productName.equals("") || productName==null|| item.getProductName().toLowerCase().contains(productName.toLowerCase())||
                   item.getProductName().equalsIgnoreCase(productName) ) {
                return true;
            }
            return false;
        };
        
    }
   public void upload()
    {
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("\\resources\\images\\Product");
        int endIndex = path.indexOf("\\Coffee", 0);
        
        path = path.substring(0, endIndex);
        path = path + "\\Coffee\\Coffee-war\\web\\resources\\images\\Product\\";
       
        try{
            InputStream in = File.getInputStream();
            
            byte[] data = new byte[in.available()];
            in.read(data);
             String name = getFilename(File);
            imagePath ="/Product/"+ name;
            
           if(product!=null)
           {
               product.setImage(imagePath);
             
           }
            FileOutputStream out = new FileOutputStream(new File(path+name));
            out.write(data);
            in.close();
            out.close();
            
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    public void validateFile(FacesContext ctx,
                         UIComponent comp,
                         Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part)value;
        
        if (!"image/jpeg".equals(file.getContentType()) && !"image/png".equals(file.getContentType())
               && !"image/bmp".equals(file.getContentType()) && !"image/gif".equals(file.getContentType()) ) {
          msgs.add(new FacesMessage("Không phải file ảnh!"));
        }
        if (file.getSize() > 1048546) {
          msgs.add(new FacesMessage("File quá lớn"));
        }
        
        if (!msgs.isEmpty()) {
          throw new ValidatorException(msgs);
        }
        
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
