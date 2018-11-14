/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.controller.exceptions.IllegalOrphanException;
import unam.mx.cella.modelo.controller.exceptions.NonexistentEntityException;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.controller.MaterialJpaController;
import unam.mx.cella.modelo.controller.UnidadmaterialJpaController;

/**
 *
 * @author rossa
 */
@ManagedBean
@RequestScoped
public class EliminarmaterialController {

    /**
     * Creates a new instance of EliminarmaterialController
     */
    private final EntityManagerFactory emf;
    private Unidadmaterial unidadmaterial;
    private String nombrematerial;
    public EliminarmaterialController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.unidadmaterial = new Unidadmaterial();
        this.nombrematerial = "";
        
    }
    
    public Unidadmaterial getUnidadMaterial(){
        return unidadmaterial;
    }
    
    public void setUnidadMatrial(Unidadmaterial unidadmaterial){
        this.unidadmaterial = unidadmaterial;
    }
    
    public String getNombrematerial(){
        return nombrematerial;
    }
    
    public void setNombrematerial(String nombrematerial){
        this.nombrematerial = nombrematerial;
    }
   
    public String removeUnidadMaterial() throws IllegalOrphanException, NonexistentEntityException, unam.mx.cella.modelo.exceptions.IllegalOrphanException, unam.mx.cella.modelo.exceptions.NonexistentEntityException{
        MaterialJpaController mjpa = new MaterialJpaController(emf);
        UnidadmaterialJpaController umjpa = new UnidadmaterialJpaController(emf);
        Unidadmaterial umt = umjpa.findMaterial(nombrematerial);
        umjpa.destroy(umt.getId());
        Material mt = mjpa.findMaterial(nombrematerial);
        mjpa.destroy(mt.getId());
        
        FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "Felicidades, el material se ha eliminado correctamente" , ""));
        return null;
    }
}
