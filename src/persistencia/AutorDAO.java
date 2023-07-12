package persistencia;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import entidades.Autor;
import java.util.List;
import javax.persistence.Query;

public class AutorDAO extends DAO<Autor> {

    public void guardarAutor(Autor a) throws Exception {

        guardar(a);

    }

    public void eliminarLibro(Integer id) throws Exception {

        eliminar(buscarAutorXId(id));

    }

    public List<Autor> listarTodosAutores() throws Exception {

        return listarTodos("SELECT a FROM Autor a");

    }

    public Autor buscarAutorXId(Integer id) throws Exception {

        conectar();
        Autor a = em.find(Autor.class, id);
        desconectar();
        return a;

    }

    public List<Autor> BuscarAutor(String columna, String dato) throws Exception {

        return consultaGenerica("Autor", columna, dato);

    }

    public boolean noHayAutor() throws Exception {

        return noHayRegistros("select a from Autor a");

    }

    public void editarAutor(Autor a) throws Exception {

        editar(a);

    }

}
