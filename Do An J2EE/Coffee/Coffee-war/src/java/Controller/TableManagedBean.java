/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.DetailreceiptFacade;
import SessionBean.EmployeeFacade;
import SessionBean.ProductFacade;
import SessionBean.ReceiptFacade;
import SessionBean.TablecoffeeFacade;
import entities.Detailreceipt;
import entities.DetailreceiptPK;
import entities.Employee;
import entities.Product;
import entities.Receipt;
import entities.Tablecoffee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private EmployeeFacade employeeFacade;

    @EJB
    private ReceiptFacade receiptFacade;

    @EJB
    private DetailreceiptFacade detailreceiptFacade;

    @EJB
    private ProductFacade productFacade;

    
    @EJB
    private TablecoffeeFacade tablecoffeeFacade;

   public List<Tablecoffee> ListAllTable;
   public Tablecoffee _table = new Tablecoffee();
   public int page=1;

   private List<Product> listProductOrder = new ArrayList<Product>();
   private Receipt _receipt = new Receipt();
   private List<Detailreceipt> listDetailReceipt = new ArrayList<Detailreceipt>();
   private int _numberProductOrder;
   private long _totalPrice = 0;
   private List<Employee> listEmployee = new ArrayList<Employee>();
   private Employee _employee = new Employee();

    public Employee getEmployee() {
        return _employee;
    }

    public void setEmployee(Employee _employee) {
        this._employee = _employee;
    }

    public List<Employee> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }
   

   
    public Receipt getReceipt() {
        return _receipt;
    }

    public void setReceipt(Receipt _receipt) {
        this._receipt = _receipt;
    }

    public List<Detailreceipt> getListDetailReceipt() {
        return listDetailReceipt;
    }

    public void setListDetailReceipt(List<Detailreceipt> listDetailReceipt) {
        this.listDetailReceipt = listDetailReceipt;
    }

    public int getNumberProductOrder() {
        return _numberProductOrder;
    }

    public void setNumberProductOrder(int _numberProductOrder) {
        this._numberProductOrder = _numberProductOrder;
    }
   

    public List<Product> getListProductOrder() {
        return listProductOrder;
    }

    public void setListProductOrder(List<Product> listProductOrder) {
        this.listProductOrder = listProductOrder;
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
    
    public void InitOrder(Tablecoffee table)
    {

        if(table.getReceiptId()==null)
        {
            _receipt = new Receipt();
           _receipt.setCustomerName("Nguyen Van A");
           _receipt.setDate(new Date());
           _receipt.setStatus(false);
           _receipt.setTotalPrice(_totalPrice);
           _receipt.setEmployeeId(employeeFacade.find(1));
             receiptFacade.create(_receipt);
             _totalPrice = 0;
        }
        else
        {
            _receipt = receiptFacade.find(table.getReceiptId().getReceiptId());
            _totalPrice = 0;
        }
            
     
       
       _table= table;
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
        try   
        {
             Detailreceipt _detailReceipt = new Detailreceipt();
            DetailreceiptPK _detailReceiptPk = new DetailreceiptPK();
      
            _detailReceipt.setProduct(product);
            _totalPrice = _totalPrice + product.getPrice()*_numberProductOrder;
            _detailReceiptPk .setProductId(product.getProductId());
            
            _detailReceipt.setDetailreceiptPK(_detailReceiptPk);
                 
            _detailReceiptPk.setReceiptId(_receipt.getReceiptId());
            
            if(detailreceiptFacade.GetAllDetailByReceiptID(_receipt.getReceiptId(),product.getProductId()).size()==0) //Nếu chưa có chi tiết hóa đơn thì tạo
            {
                   _detailReceipt.setCount(_numberProductOrder);
                   detailreceiptFacade.create(_detailReceipt);
            }
            else //nếu có rồi mà bấm đặt món thì sẽ cộng thêm số lượng món đã có và món mới đặt lại
            {
               _detailReceipt =  detailreceiptFacade.GetAllDetailByReceiptID(_receipt.getReceiptId(),product.getProductId()).get(0);
               _detailReceipt.setCount(_detailReceipt.getCount()+ _numberProductOrder);
               detailreceiptFacade.edit(_detailReceipt);
            }

            //update status
            product.setCount(product.getCount()-_numberProductOrder);
            
            if(product.getCount()==0)
            {
                product.setStatus(false);
            }
            productFacade.edit(product);
            
            _table.setStatus(false);
           tablecoffeeFacade.edit(_table);

            listDetailReceipt.add(_detailReceipt);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      
    }
    
    public void RemoveOrder(Product product)
    { 
    
        for(int i=0;i<listDetailReceipt.size();i++)
        {
            if(listDetailReceipt.get(i).getProduct().getProductId() == product.getProductId())
            {
               if(listDetailReceipt.get(i).getCount() >_numberProductOrder )
               {
                   listDetailReceipt.get(i).setCount(listDetailReceipt.get(i).getCount() -_numberProductOrder );
                   
                   _numberProductOrder = listDetailReceipt.get(i).getCount();
                   product.setCount(product.getCount()+_numberProductOrder);
                   if(product.getStatus()==false &&product.getCount() >0)
                   {
                       product.setStatus(true);
                       
                   }
                   productFacade.edit(product);
                  detailreceiptFacade.edit(listDetailReceipt.get(i));
                   
                   break;
               }
               else
               {
                    product.setCount(product.getCount()+_numberProductOrder);
                   if(product.getStatus()==false &&product.getCount() >0)
                   {
                       product.setStatus(true);
                       
                   }
                   productFacade.edit(product);
                    detailreceiptFacade.remove(listDetailReceipt.get(i));
                   
                   listDetailReceipt.remove(i);
                  
                    break;
               }
               
            }
        }
    }
    
    public void EditListOrder()
    {
        try{
            
          if(listDetailReceipt.size()==0)
          {
              for(int i=0;i<detailreceiptFacade.findAll().size();i++)
              {
                 if(detailreceiptFacade.findAll().get(i).getReceipt().getReceiptId() == receiptFacade.find(tablecoffeeFacade.find(_table.getTableId()).getReceiptId()).getReceiptId());
                 {
                     detailreceiptFacade.remove(detailreceiptFacade.findAll().get(i));
                 }
              }
            
          }
          else
          {
              for(int i=0;i<listDetailReceipt.size();i++)
              {
                  detailreceiptFacade.edit(listDetailReceipt.get(i));
              }
          }
          
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void EditTable()
    {
          try
        {
            if(_receipt.getReceiptId()!=null)
            {
                 _receipt.setTotalPrice(_totalPrice);
                receiptFacade.edit(_receipt);

               _table.setReceiptId(_receipt);
            }
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
    
    public List<Employee> GetAllEmployee()
    {
        listEmployee = employeeFacade.findAll();
        
        return listEmployee;
    }
    
   public void SaveReceipt()
   {
       _employee = employeeFacade.find(_employee.getEmployeeId());
       _receipt.setEmployeeId(_employee);
       _table.setReceiptId(null);
       tablecoffeeFacade.edit(_table);
       
      receiptFacade.edit(_receipt);
   }

    public String GetPrice()
    {
        return _receipt.getTotalPrice()+" VNĐ";
    }
    
    
            
    public void GetListProductOrder(Tablecoffee table)
    {
        try     
        {
           listDetailReceipt.clear();
          _table = table;
          _receipt = table.getReceiptId();
        
         listDetailReceipt = detailreceiptFacade.GetAllDetailByReceiptID(_receipt.getReceiptId());
           _receipt.setTotalPrice(detailreceiptFacade.TotalPrice(listDetailReceipt));
           
          receiptFacade.edit(_receipt);
         
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
       
        
        /*String productids =  _table.getProductIds();
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
        }*/
    }
}
