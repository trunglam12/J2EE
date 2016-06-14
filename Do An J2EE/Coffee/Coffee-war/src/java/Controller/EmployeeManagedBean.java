/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.EmployeeFacade;
import entities.Employee;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author belsh
 */
@ManagedBean(name = "employeeBean")
@SessionScoped
public class EmployeeManagedBean {

    @EJB
    private EmployeeFacade employeeFacade;

    Employee employee;
    
    Employee newEmployee;

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    int page =1;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Creates a new instance of EmployeeManagedBean
     */
    public EmployeeManagedBean() {
        newEmployee = new Employee();
    }

    public List<Employee> GetAllEmployee() {
        return employeeFacade.findAll();
    }

    public void GetEmployee(Employee employee) {
        this.employee = employee;
    }

    public void RemoveEmployee()
    {
        employeeFacade.remove(employee);
    }
    
    public void EditEmployee()
    {
          try
        {
            employeeFacade.edit(employee);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public void AddEmployee()
    {
         try
        {
            employeeFacade.create(newEmployee);
            newEmployee = new Employee();
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}
