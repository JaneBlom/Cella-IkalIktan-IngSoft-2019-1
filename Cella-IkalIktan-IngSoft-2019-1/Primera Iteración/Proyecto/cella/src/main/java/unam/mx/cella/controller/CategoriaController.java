/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage; 
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Categoria;
import unam.mx.cella.modelo.controller.CategoriaJpaController;


/**
 *
 * @author Janeth
 */
@ManagedBean
@RequestScoped
public class CategoriaController {

    private final EntityManagerFactory emf;
    private Categoria categoria;
    private String descripcion;
    private String nombrecategoria;

    /**
     * Creates a new instance of CategoriaController
     */
    public CategoriaController() {
         emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.categoria = new Categoria();
        nombrecategoria = "";
        descripcion = "";
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public Categoria setCategoria(Categoria nueva){
        return categoria = nueva;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String nueva){
        descripcion = nueva;
    }
    public String getNombrecategoria(){
        return nombrecategoria;
    }
    public void setNombrecategoria(String nombrecategoria){
        this.nombrecategoria = nombrecategoria;
    }

    public String addCategoria() {
       
            CategoriaJpaController cjc = new CategoriaJpaController(emf);
            Categoria categ = new Categoria();
            categ.setNombrecategoria(nombrecategoria);
            categ.setDescripcion(descripcion);
            cjc.create(categ);
  
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "El registro se ha realizado correctamente", ""));
         
        return null;
    }
}

