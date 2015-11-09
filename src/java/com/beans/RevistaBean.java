/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controllers.RevistaJpaController;
import com.entities.Revista;
import com.utils.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author andrezz
 */
@ManagedBean
@RequestScoped
public class RevistaBean {
    
    private Revista revistaAgregar;
    
    private Revista revistaModificar;
    
    private static List<Revista> revistas;
    
    private RevistaJpaController controlador;
    
    private static int identificacion;
    
    /**
     * Creates a new instance of EventoBean
     */
    public RevistaBean() {
        revistaAgregar= new Revista("", true);
        revistaAgregar.setCategorizacion("");
        revistaModificar= new Revista("", true);
        revistaModificar.setCategorizacion("");
        revistas= new ArrayList<>();
        controlador= new RevistaJpaController(JPAUtil.getEntityManagerFactory());
        revistas= controlador.findRevistaEntities();
        
        String temp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("identificacion");
        if(temp!=null){
            identificacion=Integer.parseInt(temp);
        }
    }

    public Revista getRevistaAgregar() {
        return revistaAgregar;
    }

    public void setRevistaAgregar(Revista revistaAgregar) {
        this.revistaAgregar = revistaAgregar;
    }

    public Revista getRevistaModificar() {
        return revistaModificar;
    }

    public void setRevistaModificar(Revista revistaModificar) {
        this.revistaModificar = revistaModificar;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(List<Revista> revistas) {
        this.revistas = revistas;
    }

    public static int getIdentificacion() {
        return identificacion;
    }

    public static void setIdentificacion(int identificacion) {
        RevistaBean.identificacion = identificacion;
    }
    
}
