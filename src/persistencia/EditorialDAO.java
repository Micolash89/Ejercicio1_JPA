/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Autor;
import entidades.Editorial;
import entidades.Editorial;
import java.util.List;

/**
 *
 * @author JAVIER ESPINDOLA
 */
public class EditorialDAO extends DAO<Editorial>{
    
    public void guardarEditorial(Editorial a) throws Exception {

        guardar(a);

    }

    public void eliminarLibro(Integer id) throws Exception {

        eliminar(buscarEditorialXId(id));

    }

    public List<Editorial> listarTodosEditoriales() {

        return listarTodos("select a from Editorial a");

    }

    public Editorial buscarEditorialXId(Integer id) throws Exception {

        conectar();
        Editorial a = em.find(Editorial.class, id);
        desconectar();
        return a;

    }


    public List<Editorial> BuscarEditorial(String columna,String dato) throws Exception{
    
        return consultaGenerica("Editorial", columna , dato);
    
    }
    
     public boolean noHayEditorial()throws Exception{
    
    return noHayRegistros("select a from Editorial a");
    
    }
     
      public void editarEditorial(Editorial a)throws Exception{
        
        editar(a);
    
    }

     
}
