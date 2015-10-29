/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controllers.InvestigadorTrabajoInvestigacionJpaController;
import com.controllers.TrabajoInvestigacionJpaController;
import com.entities.InvestigadorTrabajoInvestigacion;
import com.entities.TrabajoInvestigacion;
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
public class TrabajoInvestigacionBean {

    private TrabajoInvestigacion trabajoModificar;
    
    private TrabajoInvestigacion trabajoAgregar;
    
    private static List<TrabajoInvestigacion> trabajos;
    
    private TrabajoInvestigacionJpaController controladorTrabajoInvestigacion;
    
    private InvestigadorTrabajoInvestigacionJpaController controlador;
    
    private int identificacionInvestigador;
    
    /**
     * Creates a new instance of TrabajoInvestigacion
     */
    public TrabajoInvestigacionBean() {
        controlador= new InvestigadorTrabajoInvestigacionJpaController(JPAUtil.getEntityManagerFactory());
        controladorTrabajoInvestigacion= new TrabajoInvestigacionJpaController(JPAUtil.getEntityManagerFactory());
        trabajoModificar= new TrabajoInvestigacion();
        trabajoAgregar= new TrabajoInvestigacion();
        trabajos= new ArrayList<>();
        String temp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("placa");
        identificacionInvestigador=Integer.parseInt(temp);
        
        List<InvestigadorTrabajoInvestigacion> listaTrabajosInvestigador = controlador.findInvestigadorTrabajoInvestigacionEntities();
        for(InvestigadorTrabajoInvestigacion it: listaTrabajosInvestigador){
            if(it.getInvestigadorIdentificacion().getIdentificacion()== identificacionInvestigador){
                trabajos.add(it.getTrabajoInvestigacionId());
            }
        }
    }
    
}
