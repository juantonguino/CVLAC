/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controllers.EventoJpaController;
import com.controllers.exceptions.IllegalOrphanException;
import com.controllers.exceptions.NonexistentEntityException;
import com.entities.Evento;
import com.utils.JPAUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
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
@ApplicationScoped
public class EventoBean {
    
    private Evento eventoAgregar;
    
    private Evento eventoModificar;
    
    private static List<Evento> eventos;
    
    private EventoJpaController controlador;
    
    private static int identificacion;
    
    /**
     * Creates a new instance of EventoBean
     */
    public EventoBean() {
        eventoAgregar= new Evento(0, "", new Date(), new Date(), "", "");
        eventoModificar= new Evento(0, "", new Date(), new Date(), "", "");
        eventos= new ArrayList<>();
        controlador= new EventoJpaController(JPAUtil.getEntityManagerFactory());
        eventos=controlador.findEventoEntities();
        
        String temp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("identificacion");
        if(temp!=null){
            identificacion=Integer.parseInt(temp);
        }
    }

    public Evento getEventoAgregar() {
        return eventoAgregar;
    }

    public void setEventoAgregar(Evento eventoAgregar) {
        this.eventoAgregar = eventoAgregar;
    }

    public Evento getEventoModificar() {
        return eventoModificar;
    }

    public void setEventoModificar(Evento eventoModificar) {
        this.eventoModificar = eventoModificar;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        EventoBean.eventos = eventos;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    
    public void agregarEvento(){
        try{
            controlador.create(eventoAgregar);
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("evento.xhtml?identificacion="+identificacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXELENTE!", "Se ha agregado una ponencia"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede eliminar Vehiculo"));
        }
    }
    
    public String activarDialogoAgregar(){
        FacesContext context= FacesContext.getCurrentInstance();
        EventoBean eventoBean= context.getApplication().evaluateExpressionGet(context, "#{eventoBean}", EventoBean.class);
        eventoBean.setIdentificacion(this.identificacion);
        return "PF('agregar').show();";
    }
    
    public void eliminarEvento(Evento evento){
        try{
            controlador.destroy(evento.getId());
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("evento.xhtml?identificacion="+identificacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXELENTE!", "Se ha eliminado un evento"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede eliminar Evento"));
        }
    }
    
    public String modificarEvento(Evento evento){
        this.eventoModificar=null;
        this.eventoModificar=evento;
        return "PF('modificar').show();";
    }
    
    public void modificarEventoDialogo(){
        try{
            controlador.edit(eventoModificar);
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("evento.xhtml?identificacion="+identificacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXELENTE!", "Se ha agregado una ponencia"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede eliminar Vehiculo"));
        }
    }
}