package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO<T> {

    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ejercicio1JPAPU");
    protected EntityManager em = EMF.createEntityManager();

    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }

    }

    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }

    }

    protected void guardar(T obj) {

        conectar();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        desconectar();

    }

    protected void editar(T obj) {
        conectar();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
        desconectar();

    }

    protected void eliminar(T obj) {

        conectar();
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
        desconectar();

    }

    protected List<T> listarTodos(String sql) {

        conectar();

        List<T> obj = em.createQuery(sql).getResultList();
        desconectar();
       
        return obj;

    }

    public List<T> consultaGenerica(String entidad, String atributo, String variable) {

        conectar();
        return em.createQuery("SELECT e FROM " + entidad + " e WHERE e." + atributo + " LIKE :variable").setParameter("variable", "%" + variable + "%").getResultList();
        //return em.createQuery("SELECT e FROM Libro e WHERE e.autor.nombre LIKE :variable").setParameter("variable", "%" + variable + "%").getResultList();
        //SELECT e FROM Libro  e where e.autor.nombre LIKE : variable
    }

    public List<T> consultaGenericaLiteral(String entidad, String atributo, String variable) {

        conectar();
        //return em.createQuery("SELECT e FROM " + entidad + " e WHERE e." + atributo + " LIKE : " + variable).getResultList();
        return em.createQuery("SELECT e FROM Libro e WHERE e.autor.nombre LIKE :variable").setParameter("variable", "%" + variable + "%").getResultList();
        //return em.createQuery("SELECT e FROM Libro e WHERE e.autor.nombre LIKE : " + variable).getResultList();
        //SELECT e FROM Libro  e where e.autor.nombre LIKE : variable
    }

    public T consultaGenericaLiteralNombre(String entidad, String atributo, String variable) {

        conectar();
        //return em.createQuery("SELECT e FROM " + entidad + " e WHERE e." + atributo + " LIKE : " + variable).getResultList();
        //return (T) em.createQuery("SELECT e FROM Libro e WHERE e.autor.nombre LIKE : variable ").setParameter("variable", variable ).getSingleResult();
        return (T) em.createQuery("SELECT e FROM Libro e WHERE e.autor.nombre LIKE : " + variable ).getSingleResult();
        //return em.createQuery("SELECT e FROM Libro e WHERE e.autor.nombre LIKE : " + variable).getResultList();
        //SELECT e FROM Libro  e where e.autor.nombre LIKE : variable
    }

    public boolean noHayRegistros(String sql) {
        conectar();
        return listarTodos(sql).isEmpty();
    }

    public void eliminarTodos(String entidad) {

        conectar();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM " + entidad).executeUpdate();
        em.getTransaction().commit();
        desconectar();
        // 
    }

}
