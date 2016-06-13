/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SessionBean.UserFacade;
import entities.User;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author belsh
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginManagedBean {
        @EJB
    private UserFacade userFacade;

    /**
     * 
     */
    private static final long serialVersionUID = 2951813936936766650L;

    public LoginManagedBean() {
        System.out.println("LoginBean()");
    }

    private String username;
    private String password;
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        System.out.println("getUsername() returning: " + this.username);
        return this.username;
    }

    public void setUsername(String username) {
        System.out.println("setUserName(" + username + ")");
        this.username = username;
    }

    public String getPassword() {
        System.out.println("getPassword() returning: " + this.password);
        return this.password;
    }

    public void setPassword(String password) {
        System.out.println("setPassword(" + password + ")");
        this.password = password;
    }

    public String Login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        User user = userFacade.FindUser(username, md5(password));
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            return "/index?face-redirect=true";
        }
        else{
            FacesMessage facemsg= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên tài khoản hoặc mật khẩu không chính xác", "Tên tài khoản hoặc mật khẩu không chính xác");
            context.addMessage(null, facemsg);
        }
        return "/login?face-redirect=true";
    }
    
    public String Register() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if(userFacade.IsUserAccountExist(username)){
            if(!password.equals(confirmPassword)){
                FacesMessage facemsg= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mật khẩu và mật khẩu xác nhận không khớp", "Mật khẩu và mật khẩu xác nhận không khớp");
                context.addMessage(null, facemsg);
                return "/login?face-redirect=true#signup";
            }
            if(userFacade.Register(username, md5(password))){
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                return "/index?face-redirect=true";
            }
            else{
                FacesMessage facemsg= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi xảy ra khi thực hiện yêu cầu của bạn", "Có lỗi xảy ra khi thực hiện yêu cầu của bạn");
                context.addMessage(null, facemsg);
            }
        }
        else{
                FacesMessage facemsg= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên tài khoản đã tồn tai", "Tên tài khoản đã tồn tai");
                context.addMessage(null, facemsg);
        }
        return "/login?face-redirect=true#signup";
    }

    public String Logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return "/login?face-redirect=true";
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
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
