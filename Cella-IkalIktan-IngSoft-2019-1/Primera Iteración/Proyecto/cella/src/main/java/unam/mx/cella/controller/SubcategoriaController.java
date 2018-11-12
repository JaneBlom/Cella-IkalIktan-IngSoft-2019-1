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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Subcategorias;
import unam.mx.cella.modelo.controller.SubcategoriasJpaController;
import unam.mx.cella.modelo.Categoria;
import unam.mx.cella.modelo.controller.CategoriaJpaController;

/**
 *
 * @author Janeth
 */
@ManagedBean
@RequestScoped
public class SubcategoriaController {

    private final EntityManagerFactory emf;
    private Subcategorias subcategoria;
    private Categoria categoria;
    private String nombresubcategoria;
    private String nombrecategoria;
    //private String descripcion;
 
    
    /**
     * Creates a new instance of SubcategoriaController
     */
    public SubcategoriaController() {
        emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.subcategoria = new Subcategorias();
        this.categoria = new Categoria();
        //descripcion = "nada";
        nombresubcategoria = "";
        nombrecategoria = "";
    }
    public Subcategorias getSubcategoria(){
        return subcategoria;
    }
    
    public void setSubcategoria(Subcategorias nueva){
        subcategoria = nueva;
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public Categoria setCategoria(Categoria nueva){
        return categoria = nueva;
    }
    public String getNombresubcategoria(){
        return nombresubcategoria;
    }
    public void setNombresubcategoria(String nombresubcategoria){
        this.nombresubcategoria = nombresubcategoria;
    }
    public String getNombrecategoria(){
        return nombrecategoria;
    }
    public void setNombrecategoria(String nombrecategoria){
        this.nombrecategoria = nombrecategoria;
    }
    /*public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String nueva){
        descripcion = nueva;
    }*/

    
    public String addSubcategoria() {
       
            SubcategoriasJpaController sjc = new SubcategoriasJpaController(emf);
            Subcategorias subcateg = new Subcategorias();
            CategoriaJpaController cjc = new CategoriaJpaController(emf);
            Categoria categ = new Categoria();
            
            if(cjc.findCategoria(nombrecategoria) == null){
                categ.setNombrecategoria(nombrecategoria);
               // categ.setDescripcion(descripcion);
                cjc.create(categ);
            }
            categ = cjc.findCategoria(nombrecategoria);
            subcateg.setNombrecategoria(nombrecategoria);
            subcateg.setNombresubcategoria(nombresubcategoria);
            subcateg.setIdCategoria(categ);
            sjc.create(subcateg);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "El registro se ha realizado correctamente", ""));
         
        return null;
    }
   /*
    SubcategoriasJpaController sjc = new SubcategoriasJpaController(emf);
            Subcategorias subcateg = new Subcategorias();
        
            if(cjc.findCategoria(nombrecategoria) == null){
                categ.setNombrecategoria(categoria.getNombrecategoria());
                categ.setDescripcion(categoria.getDescripcion());
                cjc.create(categ);
            }
            categ = cjc.findCategoria(nombrecategoria);
            subcateg.setNombrecategoria(nombrecategoria);
            subcateg.setNombresubcategoria(nombresubcategoria);
            subcateg.setIdCategoria(categ);
            sjc.create(subcateg);

    */
    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}