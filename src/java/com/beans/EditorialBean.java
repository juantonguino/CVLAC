/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controllers.EditorialJpaController;
import com.entities.Editorial;
import com.utils.JPAUtil;
import java.util.ArrayList;
import java.util.List;
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
public class EditorialBean {
    
    private Editorial editorialAgregar;
    
    private Editorial editorialModificar;
    
    private static List<Editorial> editoriales;
    
    private EditorialJpaController controlador;
    
    private static int identificacion;
    /**
     * Creates a new instance of EditorialBean
     */
    public EditorialBean() {
        editorialAgregar= new Editorial(0, "", "", "");
        editorialModificar= new Editorial(0, "", "", "");
        editoriales= new ArrayList<>();
        controlador= new EditorialJpaController(JPAUtil.getEntityManagerFactory());
        editoriales= controlador.findEditorialEntities();
        
        String temp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("identificacion");
        if(temp!=null){
            identificacion=Integer.parseInt(temp);
        }
    }

    public Editorial getEditorialAgregar() {
        return editorialAgregar;
    }

    public void setEditorialAgregar(Editorial editorialAgregar) {
        this.editorialAgregar = editorialAgregar;
    }

    public Editorial getEditorialModificar() {
        return editorialModificar;
    }

    public void setEditorialModificar(Editorial editorialModificar) {
        this.editorialModificar = editorialModificar;
    }

    public List<Editorial> getEditoriales() {
        return editoriales;
    }

    public void setEditoriales(List<Editorial> editoriales) {
        EditorialBean.editoriales = editoriales;
    }
    
    
}
