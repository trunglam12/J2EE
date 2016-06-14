/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.EmployeeFacade;
import SessionBean.UserFacade;
import entities.Employee;
import entities.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author belsh
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserManagedBean {

    @EJB
    private EmployeeFacade employeeFacade;

    @EJB
    private UserFacade userFacade;

    private User user;
    
    private User newUser;
    
    private String confirmPassword;
    
    private int newUserEmployeeId;

    public int getNewUserEmployeeId() {
        return newUserEmployeeId;
    }

    public void setNewUserEmployeeId(int newUserEmployeeId) {
        this.newUserEmployeeId = newUserEmployeeId;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public User getUser() {
        return user;
    }
    
    public void GetUser(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
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
    public UserManagedBean() {
        newUser = new User();
    }

    public List<User> GetAllUser() {
        return userFacade.findAll();
    }

    public SelectItem[] getListEmployee() {
        List<Employee> employees =  employeeFacade.findAll();
        SelectItem[] items = new SelectItem[employees.size()];
        for(int i = 0; i < employees.size(); i++){
            items[i] = new SelectItem(employees.get(i).getEmployeeId(), employees.get(i).getEmployeeName());
        }
        return items;
    }

    public void RemoveUser()
    {
        userFacade.remove(user);
    }
    
    public void EditUser()
    {
        try
        {
            user.setPassword(md5(user.getPassword()));
            userFacade.edit(user);
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public void AddUser()
    {
        try
        {
            if(!userFacade.IsUserAccountExist(newUser.getUserAccount())){
                newUser.setPassword(md5(newUser.getPassword()));
                newUser.setEmployeeId(employeeFacade.find(newUserEmployeeId));
                userFacade.create(newUser);
                newUser = new User();
            }
            else{
                FacesMessage facemsg= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên tài khoản đã tồn tai", "Tên tài khoản đã tồn tai");
                throw new ValidatorException(facemsg);
            }
        }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    public void validateSamePassword() {
        if (!confirmPassword.equals(newUser.getPassword())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", "Passwords do not match!");
            throw new ValidatorException(message);
        }
    }
    
    public String md5(String data)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            
            byte byteData[] = md.digest();
            
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
