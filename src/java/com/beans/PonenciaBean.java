/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controllers.EventoJpaController;
import com.controllers.PonenciaAutorJpaController;
import com.controllers.PonenciaJpaController;
import com.controllers.TrabajoInvestigacionJpaController;
import com.entities.Evento;
import com.entities.Ponencia;
import com.entities.PonenciaAutor;
import com.entities.TrabajoInvestigacion;
import com.utils.JPAUtil;
import java.util.ArrayList;
import java.util.Date;
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
public class PonenciaBean {

    private Ponencia ponenciaModificar;
    
    private Ponencia ponenciaAgregar;
    
    private static List<Ponencia> ponencias;
    
    private PonenciaJpaController controladorPonencia;
    
    private PonenciaAutorJpaController controlador;
    
    private int identificacionInvestigador;
    
    private EventoJpaController controladorEvento;
    
    private TrabajoInvestigacionJpaController controladorTrabajoInvestigacion;
    
    private static List<Evento> eventos;
    
    private static List<TrabajoInvestigacion> trabajos;
    
    /**
     * Creates a new instance of PonenciaBean
     */
    public PonenciaBean() {
        controlador= new PonenciaAutorJpaController(JPAUtil.getEntityManagerFactory());
        controladorPonencia= new PonenciaJpaController(JPAUtil.getEntityManagerFactory());
        controladorEvento= new EventoJpaController(JPAUtil.getEntityManagerFactory());
        controladorTrabajoInvestigacion= new TrabajoInvestigacionJpaController(JPAUtil.getEntityManagerFactory());
        
        ponenciaModificar= new Ponencia(0, "", new Date());
        ponenciaAgregar= new Ponencia(0, "", new Date());
        ponencias= new ArrayList<>();
        eventos= new ArrayList<>();
        trabajos= new ArrayList<>();
        String temp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("identificacion");
        identificacionInvestigador=Integer.parseInt(temp);
        
        eventos= controladorEvento.findEventoEntities();
        trabajos=controladorTrabajoInvestigacion.findTrabajoInvestigacionEntities();
        List<PonenciaAutor> listaPonenciaAutor = controlador.findPonenciaAutorEntities();
        for(PonenciaAutor pa: listaPonenciaAutor){
            if(pa.getInvestigadorIdentificacion().getIdentificacion()== identificacionInvestigador){
                ponencias.add(pa.getPonenciaId());
            }
        }
    }

    public Ponencia getPonenciaModificar() {
        return ponenciaModificar;
    }

    public void setPonenciaModificar(Ponencia ponenciaModificar) {
        this.ponenciaModificar = ponenciaModificar;
    }

    public Ponencia getPonenciaAgregar() {
        return ponenciaAgregar;
    }

    public void setPonenciaAgregar(Ponencia ponenciaAgregar) {
        this.ponenciaAgregar = ponenciaAgregar;
    }

    public List<Ponencia> getPonencias() {
        return ponencias;
    }

    public void setPonencias(List<Ponencia> ponencias) {
        PonenciaBean.ponencias = ponencias;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        PonenciaBean.eventos = eventos;
    }

    public List<TrabajoInvestigacion> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<TrabajoInvestigacion> trabajos) {
        PonenciaBean.trabajos = trabajos;
    }
    
    public void agregarPonencia(){
        try{
            controladorPonencia.create(ponenciaAgregar);
            FacesContext contex= FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("ponencia.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXELENTE!", "Se ha agregado una ponencia"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "No se puede eliminar Vehiculo"));
        }
    }
}