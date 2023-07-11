/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Autor;
import entidades.Libro;
import entidades.Libro;
import java.util.List;

/**
 *
 * @author JAVIER ESPINDOLA
 */
public class LibroDAO extends DAO<Libro> {

    public void guardarLibro(Libro a) throws Exception {

        guardar(a);

    }

    public void eliminarLibro(Long id) throws Exception {

        eliminar(buscarLibroXId(id));

    }

    public List<Libro> listarTodosLibros() {

        return listarTodos("select a from Libro a");

    }

    public Libro buscarLibroXId(Long id) throws Exception {

        conectar();
        Libro a = em.find(Libro.class, id);
        desconectar();
        return a;

    }

    public List<Libro> BuscarLibro(String columna, String dato) throws Exception {

        return consultaGenerica("Libro", columna, dato);

    }

    public List<Libro> buscarLibroXAutor(String entidad, String atributo, String variable) throws Exception{

        return em.createQuery("SELECT l FROM Libro l join Autor a  WHERE l.autor=a.id where a.nombre LIKE :variable").setParameter("variable", variable + "%").getResultList();

    }
    
    public boolean noHayLibros()throws Exception{
    
    return noHayRegistros("select a from Libro a");
    
    }
    
     public void editarLibro(Libro a)throws Exception{
        
        editar(a);
    
    }

}
