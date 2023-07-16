/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entidades.ArchivoGestor;
import entidades.Autor;
import entidades.InterfazGrafica;
import entidades.Libro;
import java.util.List;


/**
 *
 * @author JAVIER ESPINDOLA
 */
public class StrangerService {
 
    AutorService as = new AutorService();
    EditorialService es = new EditorialService();
    LibroService ls = new LibroService();
    
    ArchivoGestor archivo = new ArchivoGestor();
    public void eliminarAutores(){
        
        
        try {
            
        ls.eliminarTodosLibro();
        as.eliminarTodosAutor();
        
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al eliminar Autor/libros " +e.getMessage(), "ERROR  - ELIMINAR AUTORES");
        }
        
    }
    public void eliminarEditorial(){
        
        
        try {
            
        ls.eliminarTodosLibro();
        es.eliminarTodosEditorial();
        
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al eliminar Autor/libros " +e.getMessage(), "ERROR  - ELIMINAR AUTORES");
        }
        
    }
    public void eliminarLibros(){
        
        
        try {
            
        ls.eliminarTodosLibro();
        
        
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al eliminar Autor/libros " +e.getMessage(), "ERROR  - ELIMINAR AUTORES");
        }
        
    }
    
    
    public void leerAutores(){
    
        try {
            
            List <Autor> a = archivo.leerAutor();
            
            for (Autor aux : a) {
                
                as.guardarAutor(aux);
                
            }
            
        } catch (Exception e) {
        }
    
    }
    public void leerLibros(){
    
        try {
            
            List <Libro> l = archivo.leerLibro();
            
          
            
            for (Libro aux : l) {
              
                as.guardarAutor(aux.getAutor());
                es.guardarEditorial(aux.getEditorial());
                ls.guardarLibro(aux);
                
            }
            
        } catch (Exception e) {
            System.out.println("error");
        }
    
    }
    
    
}
