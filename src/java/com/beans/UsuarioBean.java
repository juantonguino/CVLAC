/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controllers.UsuarioJpaController;
import com.entities.Usuario;
import com.utils.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juandiego
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Usuario usuarioIngreso;
    
    private Usuario usuarioAgregar;
    
    private Usuario usuarioModificar;
    
    private static List<Usuario> usuarios;
    
    private UsuarioJpaController controlador;
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        usuarioIngreso= new Usuario("", "", false);
        usuarioAgregar= new Usuario("", "", false);
        usuarioModificar=new Usuario("", "", false);
        usuarios= new ArrayList<>();
        controlador= new UsuarioJpaController(JPAUtil.getEntityManagerFactory());
    }

    public Usuario getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(Usuario usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    public Usuario getUsuarioAgregar() {
        return usuarioAgregar;
    }

    public void setUsuarioAgregar(Usuario usuarioAgregar) {
        this.usuarioAgregar = usuarioAgregar;
    }

    public Usuario getUsuarioModificar() {
        return usuarioModificar;
    }

    public void setUsuarioModificar(Usuario usuarioModificar) {
        this.usuarioModificar = usuarioModificar;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void verificarIngreso(){
        Usuario temp=controlador.findUsuario(usuarioIngreso.getNombre());
        if(temp!=null&&temp.getContrasenia().equals(usuarioIngreso.getContrasenia())){
            if(temp.getEsadministrador()==true){
                try{
                    FacesContext contex= FacesContext.getCurrentInstance();
                    contex.getExternalContext().redirect("admin.xhtml");
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                try{
                    FacesContext contex= FacesContext.getCurrentInstance();
                    contex.getExternalContext().redirect("faces/client/inicio.xhtml?identificacion="+temp.getInvestigadorIdentificacion().getIdentificacion());
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Por favor verifique sus credenciales"));
        }
    }
}
