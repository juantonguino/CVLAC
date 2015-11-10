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
import javax.faces.application.FacesMessage;
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

    public static int getIdentificacion() {
        return identificacion;
    }

    public static void setIdentificacion(int identificacion) {
        EditorialBean.identificacion = identificacion;
    }
    
    public void agregarEditorial(){
        try{
            controlador.create(editorialAgregar);
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("editorial.xhtml?identificacion="+identificacion);
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede agregar editorial"));
        }
    }
    
    public void modoficarEditorial(){
        try{
            if(editorialModificar.getLibroList()==null){
                editorialModificar.setLibroList(new ArrayList<>());
            }
            controlador.edit(editorialModificar);
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("editorial.xhtml?identificacion="+identificacion);
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede modificar Editorial"));
        }
    }
    
    public void eliminarEditorial(Editorial editorial){
        try{
            controlador.destroy(editorial.getId());
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("editorial.xhtml?identificacion="+identificacion);
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede eliminar Editorial"));
        }
    }
}
