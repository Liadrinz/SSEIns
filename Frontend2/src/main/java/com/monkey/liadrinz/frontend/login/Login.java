/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.liadrinz.frontend.login;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 李阿俊
 */

@Named
@SessionScoped
public class Login implements Serializable {
    protected Integer sId;
    protected String token;
    
    @EJB
    LoginBean loginBean;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
     public String loginout(){
        loginBean.setsId(-1);
        loginBean.setToken("");
        return "logout.xhtml";
    }
    public String action() {
        loginBean.setsId(sId);
        loginBean.setToken(token);
        return "index.xhtml";
    }
}
