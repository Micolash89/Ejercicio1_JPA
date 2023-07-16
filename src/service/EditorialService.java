/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entidades.Autor;
import entidades.Editorial;
import entidades.InterfazGrafica;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistencia.EditorialDAO;

/**
 *
 * @author JAVIER ESPINDOLA
 */
public class EditorialService {

    private EditorialDAO ed = new EditorialDAO();

    public Editorial ingresarEditorial() {

        Editorial a = new Editorial();

        a.setNombre(InterfazGrafica.mensajeIngreso("ingrese nombre de Editorial", "Editorial"));

        a.setAlta(true);
        try {
            ed.guardarEditorial(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al ingresar un Editorial " + e.getMessage(), "ERROR - Editorial");
        }

        return a;
    }

    public void mostrarEditoriales() {

        //InterfazGrafica.mensajeMostrar(listarEditoriales(), "LISTA DE EditorialES");
        try {
            if (ed.noHayEditorial()) {
                InterfazGrafica.mensajeCancelar("No hay registros en el sistema", "ERROR AL MOSTRAR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion base de la datos " + e.getMessage(), "ERROR - EXCEPTION");
        }

        try {
            InterfazGrafica.mensajeMostrarTabla(ListadoEnTabla(ed.listarTodosEditoriales()), "LISTA DE AUTORES");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion de la base de datos " +e.getMessage(), "LISTA DE AUTORES - TABLA");
        }

    }

    public void buscarEditorial() {
        try {

            if (ed.noHayEditorial()) {
                InterfazGrafica.mensajeCancelar("no hay Editoriales reguistrados en el sistema", "BUSCAR Editorial");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Editoriales X id " + e.getMessage(), "Error buscar Editorial - if");
        }

        Integer id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarEditoriales() + "\ningrese ingrese ID del Editorial", "Editorial"));
        Editorial a = null;
        try {

            a = ed.buscarEditorialXId(id);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al vuscar el Editorial con el id: " + id, "error ID");
            return;
        }

        if (a != null) {
            InterfazGrafica.mensajeMostrar(a.toString(), "LISTA DE EditorialES");
        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el Editorial con el id: " + id, "ERROR AL BUSCAR Editorial");
        }
    }

    public void buscarEditorialXnombre() {

        try {

            if (ed.noHayEditorial()) {
                InterfazGrafica.mensajeCancelar("no hay Editoriales reguistrados en el sistema", "BUSCAR Nombre");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Editoriales X id " + e.getMessage(), "Error buscar X nombre - if");
            return;
        }

        String nombre = InterfazGrafica.mensajeIngreso(listarEditoriales() + "\ningrese ingrese nombre del Editorial", "NOMBRE");
        if (nombre == null) {
            return;
        }
        List<Editorial> a = null;
        try {

            a = ed.BuscarEditorial("nombre", nombre);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el Editorial con el nombre: " + nombre, "error nombre");
            return;
        }

        InterfazGrafica.mensajeMostrar(listarEditoriales(a), "LISTA DE EditorialES");

    }

    public void eliminarXid() {

        Editorial a = null;
        Integer id;

        try {

            if (ed.noHayEditorial()) {
                InterfazGrafica.mensajeCancelar("no hay Editoriales reguistrados en el sistema", "BUSCAR Editorial");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Editoriales X id " + e.getMessage(), "Error buscar Editorial - if");
        }

        id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarEditoriales() + "\ningrese ingrese ID del Editorial a eliminar", "Editorial"));
        try {

            a = ed.buscarEditorialXId(id);
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Editoriales X id " + e.getMessage(), "Error buscar Editorial - if");
            return;
        }
        if (a != null) {
            a.setAlta(false);
        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el Editorial con el id: " + id, "ERROR ELIMINAR POR ID");
        }
        try {

            ed.editarEditorial(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el Editorial con el id: " + id, "error ID");
            return;
        }
        InterfazGrafica.mensajeMostrar("se elimino el Editorial: " + a, "eliminar");
    }

    /**
     * pasar la lista en una cadena para mostrar
     *
     * @return
     */
    private String listarEditoriales() {
        List<Editorial> a = null;
        try {

            a = ed.listarTodosEditoriales();

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error " + e.getMessage(), "error al mostrar Editoriales");
            return "";
        }
        String c = "";

        for (Editorial Editorial : a) {
            c += Editorial.getId() + " " + Editorial.getNombre() + espacios(Editorial.getNombre().length()) + "\n";
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

    private String listarEditoriales(List<Editorial> a) {

        String c = "";
        int i = 0;
        for (Editorial Editorial : a) {
            c += Editorial.getId() + " " + Editorial.getNombre() + espacios(Editorial.getNombre().length()) + "\n";
        }
        return c;

    }

    private JScrollPane ListadoEnTabla(List<Editorial> a) {

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
///////////////////////////////stranger/////////////////////////
    
      public void eliminarTodosEditorial() throws Exception {
        ed.eliminarTodos("Editorial");
    }
    public void guardarEditorial(Editorial a){
        
        try {
            ed.guardarEditorial(a);
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al guardar en la base de datos", "ERROR - AUTOR - SERVICE");
        }
    
    }

}
