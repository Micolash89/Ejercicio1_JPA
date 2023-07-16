package service;

import entidades.Autor;
import entidades.InterfazGrafica;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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

        //  InterfazGrafica.mensajeMostrar(listarAutores(), "LISTA DE AUTORES");
        try {
            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("No hay registros en el sistema", "ERROR AL MOSTRAR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion base de la datos " + e.getMessage(), "ERROR - EXCEPTION");
        }

        try {
            InterfazGrafica.mensajeMostrarTabla(ListadoEnTabla(ad.listarTodosAutores()), "LISTA DE AUTORES");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion de la base de datos", "LISTA DE AUTORES - TABLA");
        }

    }

    public void buscarAutor() {

        String input = null;
        Integer id = -1;

        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
        }

        //input=InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor", "AUTOR") ;
        try {
            input = InterfazGrafica.mensajeMostrarTablaIngreso(ListadoEnTabla(ad.listarTodosAutores()), "BUSCAR AUTOR");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar todos los autores", "BUSCAR - AUTOR");
        }

        if (input == null) {
            return;
        }

        try {
            id = Integer.valueOf(input);

        } catch (NumberFormatException e) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - FORMAT");
            buscarAutor();
            return;
        } catch (Exception j) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - EXCEPTION");
            buscarAutor();
            return;
        }

        Autor a = null;
        try {

            a = ad.buscarAutorXId(id);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el autor con el id: " + id, "error ID");
            return;
        }

        if (a != null) {
            InterfazGrafica.mensajeMostrar(a.toString(), "LISTA DE AUTORES");
        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el autor con el id: " + id, "ERROR AL BUSCAR AUTOR");
        }

    }

    public void buscarAutorXnombre() {

        String nombre = null;
        List<Autor> a = null;

        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR Nombre");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar X nombre - if");
            return;
        }

        //String nombre = InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese nombre del Autor", "NOMBRE");
        try {
            nombre = InterfazGrafica.mensajeMostrarTablaIngreso(ListadoEnTabla(ad.listarTodosAutores()), "BUSCAR X NOMBRE");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion de la base de datos", "LISTA DE AUTORES - TABLA");
        }

        if (nombre == null) {
            return;
        }
        if (nombre.trim().equals("")) {
            InterfazGrafica.mensajeCancelar("Ingrese algun caracter", "ERROR - INGRESO");
            buscarAutorXnombre();
            return;
        }

        try {

            a = ad.BuscarAutor("nombre", nombre);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el autor con el nombre: " + nombre, "ERROR NOMBRE");
            return;
        }

        try {
            if (!a.isEmpty()) {
                InterfazGrafica.mensajeMostrarTabla(ListadoEnTabla(a), "LISTA DE AUTORES");
            } else {
                InterfazGrafica.mensajeCancelar("No se encontro el autor: " + nombre, "ERROR - BUSCAR X NOMBRE");
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion de la base de datos", "LISTA DE AUTORES - TABLA");
        }

    }

    public void eliminarXid() {

        Autor a = null;
        Integer id;
        String input = null;

        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "BUSCAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
        }

        //id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor a eliminar", "AUTOR"));
        try {
            input = InterfazGrafica.mensajeMostrarTablaIngreso(ListadoEnTabla(ad.listarTodosAutores()), "ELIMINAR AUTOR");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar todos los autores", "BUSCAR - AUTOR");
        }

        if (input == null) {
            return;
        }

        try {
            id = Integer.valueOf(input);

        } catch (NumberFormatException e) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - FORMAT");
            eliminarXid();
            return;
        } catch (Exception j) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - EXCEPTION");
            eliminarXid();
            return;
        }

        try {

            a = ad.buscarAutorXId(id);
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error buscar Autor - if");
            return;
        }
        if (a != null) {
            a.setAlta(false);
        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el autor con el id: " + id, "ERROR ELIMINAR POR ID");
        }

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
            InterfazGrafica.mensajeAdvertencia("error " + e.getMessage(), "error al mostrar autores");
            return "";
        }
        String c = "";

        for (Autor autor : a) {
            c += autor.getId() + " " + autor.getNombre() + espacios(autor.getNombre().length(), 50) + ((autor.isAlta()) ? "alta" : "baja") + "\n";
        }
        return c;

    }

    private String espacios(int n1, int n2) {

        String e = "";

        for (int i = 0; i < n2 - n1; i++) {
            e += " ";
        }

        return e;
    }

    private String listarAutores(List<Autor> a) {

        String c = "";
        int i = 0;
        for (Autor autor : a) {
            c += autor.getId() + " " + autor.getNombre() + espacios(autor.getNombre().length(), 50) + ((autor.isAlta()) ? "alta" : "baja") + "\n";
        }
        return c;

    }

    public void editarAutor() {

        Integer id = null;
        String input = null;
        Autor a = null;

        try {

            if (ad.noHayAutor()) {
                InterfazGrafica.mensajeCancelar("no hay Autores reguistrados en el sistema", "EDITAR AUTOR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de autores X id " + e.getMessage(), "Error editar Autor - if");
        }

        //  Integer id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarAutores() + "\ningrese ingrese ID del Autor", "AUTOR"));
        try {
            input = InterfazGrafica.mensajeMostrarTablaIngreso(ListadoEnTabla(ad.listarTodosAutores()), "ELIMINAR AUTOR");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar todos los autores", "BUSCAR - AUTOR");
        }

        if (input == null) {
            return;
        }

        try {
            id = Integer.valueOf(input);

        } catch (NumberFormatException e) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - FORMAT");
            editarAutor();
            return;
        } catch (Exception j) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - EXCEPTION");
            editarAutor();
            return;
        }

        try {

            a = ad.buscarAutorXId(id);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el autor con el id: " + id, "error ID");
            return;
        }

        if (a != null) {

            a.setNombre(InterfazGrafica.mensajeIngresoEditar("Ingrese nuevo nombre", "EDITAR AUTOR", a.getNombre()));

            if (!a.getAlta()) {
                a.setAlta(true);
            }

            try {
                ad.editarAutor(a);
            } catch (Exception e) {
                InterfazGrafica.mensajeCancelar("error al tratar de editar un autor" + e.getMessage(), "ERROR AL EDITAR");
            }

        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el autor con el id: " + id, "ERROR AL BUSCAR AUTOR");
        }

    }

    private JScrollPane ListadoEnTabla(List<Autor> a) {

        DefaultTableModel miTabla = new DefaultTableModel();
        miTabla.addColumn("Id");
        miTabla.addColumn("Nombre");
        miTabla.addColumn("Alta");

        for (int i = 0; i < a.size(); i++) {
            String fila[] = {"", "", ""};

            fila[0] = a.get(i).getId().toString();
            fila[1] = a.get(i).getNombre();
            fila[2] = (a.get(i).getAlta()) ? "Alta" : "Baja";

            miTabla.addRow(fila);
        }
        JTable table = new JTable(miTabla);//creo un objeto tabla

        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.createVerticalScrollBar();
        scrollPane.createHorizontalScrollBar();

        return scrollPane;
    }

    ///////////////////////////////////////////////
    //////////////////Stranger dao/////////////////
    
       public void eliminarTodosAutor() throws Exception {
        ad.eliminarTodos("Autor");
    }
    
    public void guardarAutor(Autor a){
        
        try {
            ad.guardarAutor(a);
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al guardar en la base de datos", "ERROR - AUTOR - SERVICE");
        }
    
    }
}
