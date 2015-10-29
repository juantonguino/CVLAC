/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author juandiego
 */
@ManagedBean
@RequestScoped
public class ClienteBean {

    private String identificacion;
    
    /**
     * Creates a new instance of ClienteBean
     */
    public ClienteBean() {
        identificacion= ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("identificacion");
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
}
