/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * hola como estas
 */
package service;

import entidades.Autor;
import entidades.InterfazGrafica;
import java.util.List;
import persistencia.AutorDAO;

/**
 *  service autor
 * @author JAVIER ESPINDOLA
 */
public class AutorService {

    private AutorDAO ad = new AutorDAO();

    public void ingresarAutor() {

        Autor a = new Autor();

        a.setNombre(InterfazGrafica.mensajeIngreso("ingrese nombre de Autor", "AUTOR"));

        a.setAlta(true);
        try {
            ad.guardarAutor(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al ingresar un autor " + e.getMessage(), "ERROR - AUTOR");
        }

    }

    public void mostrarAutores() {

        InterfazGrafica.mensajeMostrar(listarAutores(), "LISTA DE AUTORES");

    }

    public void buscarAutor() {
        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
        }

        Integer id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor", "AUTOR")) ;
        Autor a = null;
        try {

            a = ad.buscarAutorXId(id);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al vuscar el autor con el id: " + id, "error ID");
            return;
        }
        
        if(a!=null)
            InterfazGrafica.mensajeMostrar(a.toString(), "LISTA DE AUTORES");
        else
            InterfazGrafica.mensajeMostrar("no se encontro el autor con el id: " + id, "ERROR AL BUSCAR AUTOR");
    }

    public void buscarAutorXnombre() {

        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR Nombre");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar X nombre - if");
            return;
        }

        String nombre = InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese nombre del Autor", "NOMBRE");
        if(nombre==null)
            return;
        List<Autor> a = null;
        try {

            a = ad.BuscarAutor("nombre", nombre);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el autor con el nombre: " + nombre, "error nombre");
            return;
        }

        InterfazGrafica.mensajeMostrar(listarAutores(a), "LISTA DE AUTORES");

    }
    
        public void eliminarXid() {
          
            Autor a=null;
            Integer id;
            
             try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
        }

        id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor a eliminar", "AUTOR"));
            try {
                
        a= ad.buscarAutorXId(id);
            } catch (Exception e) {
               InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
               return;
            }
        if(a!=null)
            a.setAlta(false);
        else
            InterfazGrafica.mensajeMostrar("no se encontro el autor con el id: "+id, "ERROR ELIMINAR POR ID");
        try {

            ad.editarAutor(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el autor con el id: " + id, "error ID");
            return;
        }
        InterfazGrafica.mensajeMostrar("se elimino el autor: " + a, "eliminar");
    }

    /**
     * pasar la lista en una cadena para mostrar
     *
     * @return
     */
    private String listarAutores() {
        List<Autor> a = null;
        try {
           
            a = ad.listarTodosAutores();
            
        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error "+e.getMessage(), "error al mostrar autores");
            return "";
        }
        String c = "";
       
        for (Autor autor : a) {
            c += autor.getId()+ " " + autor.getNombre() + espacios(autor.getNombre().length()) + "\n";
        }
        return c;

    }

    private String espacios(int n) {

        String e = "";

        for (int i = 0; i < 20 - n; i++) {
            e += " ";
        }

        return e;
    }

    private String listarAutores(List<Autor> a) {

        String c = "";
        int i = 0;
        for (Autor autor : a) {
            c += autor.getId()+ " " + autor.getNombre() + espacios(autor.getNombre().length()) + "\n";
        }
        return c;

    }


}
