
package service;

import entidades.Autor;
import entidades.InterfazGrafica;
import java.util.List;
import persistencia.AutorDAO;


public class AutorService {

    private AutorDAO ad = new AutorDAO();

    public Autor ingresarAutor() {

        Autor a = new Autor();

        a.setNombre(InterfazGrafica.mensajeIngreso("ingrese nombre de Autor", "AUTOR"));

        a.setAlta(true);
        try {
            ad.guardarAutor(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al ingresar un autor " + e.getMessage(), "ERROR - AUTOR");
        }

        return a;
        
    }

    public void mostrarAutores() {

        InterfazGrafica.mensajeMostrar(listarAutores(), "LISTA DE AUTORES");

    }

    public void buscarAutor() {
        
        String input;
        Integer id=-1;
        
        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
        }

        //Integer id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor", "AUTOR")) ;
        
        input=InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor", "AUTOR") ;
        
        if(input==null)
            return;
        
        
        try {
            id = Integer.valueOf(input);
            
        } catch ( NumberFormatException  e) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - FORMAT");
            buscarAutor();
            return;
        }catch(Exception j){
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - EXCEPTION");
            buscarAutor();
            return;
        }
        
        
        
        
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

    public void editarAutor() {
        
         try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "EDITAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error editar Autor - if");
        }

        Integer id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor", "AUTOR")) ;
        Autor a = null;
        try {

            a = ad.buscarAutorXId(id);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el autor con el id: " + id, "error ID");
            return;
        }
        
        if(a!=null){    
            
            a.setNombre( InterfazGrafica.mensajeIngresoEditar("Ingrese nuevo nombre","EDITAR AUTOR",a.getNombre()));
            
            if(!a.getAlta())
                a.setAlta(true);
            
            try {
                ad.editarAutor(a);
            } catch (Exception e) {
                InterfazGrafica.mensajeCancelar("error al tratar de editar un autor" + e.getMessage(), "ERROR AL EDITAR");
            }
            
        }
        else
            InterfazGrafica.mensajeMostrar("no se encontro el autor con el id: " + id, "ERROR AL BUSCAR AUTOR");

    }

}
