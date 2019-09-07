/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.liadrinz.frontend.login;

import javax.ejb.Stateless;

/**
 *
 * @author 李阿俊
 */

@Stateless
public class LoginBean {
    private Integer sId;
    private String token;

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
}
