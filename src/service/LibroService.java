/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entidades.Editorial;
import entidades.Libro;
import entidades.InterfazGrafica;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistencia.LibroDAO;

/**
 *
 * @author JAVIER ESPINDOLA
 */
public class LibroService {

    private LibroDAO ld = new LibroDAO();

    public void ingresarLibro() {

        Libro a = new Libro();

        String input;

        a.setTitulo(InterfazGrafica.mensajeIngreso("ingrese nombre de Libro", "Libro"));
        if (a.getTitulo() == null) {
            return;
        }

        do {
            try {

                input = InterfazGrafica.mensajeIngreso("ingrese anio", "ANIO");
                if (input == null) {
                    return;
                }
                a.setAnio(Integer.valueOf(input));

            } catch (NumberFormatException e) {
                InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - ANIO");
                a.setAnio(-1);
            }
        } while (a.getAnio() < 0);

        do {
            try {

                input = InterfazGrafica.mensajeIngreso("ingrese Ejemplares", "EJEMPLARES");
                if (input == null) {
                    return;
                }

                a.setEjemplares(Integer.valueOf(input));

            } catch (NumberFormatException e) {
                InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - EJEMPLARES");
                a.setEjemplares(-1);
            }
        } while (a.getEjemplares() < 0);

        do {
            try {

                input = InterfazGrafica.mensajeIngreso("ingrese ejemplares prestados", "EJEMPLARES PRESTADOS");
                if (input == null) {
                    return;
                }

                a.setPrestados(Integer.valueOf(input));

            } catch (NumberFormatException e) {
                InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - EJEMPLARES PRESTADOS");
                a.setPrestados(-1);
            }
        } while (a.getPrestados() < 0);

        do {
            try {

                input = InterfazGrafica.mensajeIngreso("ingrese ejemplares restantes", "EJEMPLARES RESTANTES");
                if (input == null) {
                    return;
                }

                a.setRestantes(Integer.valueOf(input));

            } catch (NumberFormatException e) {
                InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - EJEMPLARES RESTANTES");
                a.setRestantes(-1);
            }
        } while (a.getRestantes() < 0);

        a.setAutor(new AutorService().ingresarAutor());

        a.setEditorial(new EditorialService().ingresarEditorial());

        a.setAlta(true);

        try {
            ld.guardarLibro(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al ingresar un Libro " + e.getMessage(), "ERROR - Libro");
        }

    }

    public void mostrarLibros() {

        //InterfazGrafica.mensajeMostrar(listarLibros(), "LISTA DE LibroES");
        
         try {
            if (ld.noHayLibros()) {
                InterfazGrafica.mensajeCancelar("No hay registros en el sistema", "ERROR AL MOSTRAR");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion base de la datos " + e.getMessage(), "ERROR - EXCEPTION");
        }

        try {
            InterfazGrafica.mensajeMostrarTabla(ListadoEnTabla(ld.listarTodosLibros()), "LISTA DE LIBROS");

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error en la conexion de la base de datos " , "LISTA DE LIBROS - TABLA");
        }

    }

    public void buscarLibro() {

        String input;
        Long id = -1l;

        try {

            if (ld.noHayLibros()) {
                InterfazGrafica.mensajeCancelar("no hay Libroes reguistrados en el sistema", "BUSCAR Libro");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Libroes X id " + e.getMessage(), "Error buscar Libro - if");
        }

        //Integer id = Integer.valueOf(InterfazGrafica.mensajeIngreso(listarLibroes() + "\ningrese ingrese ID del Libro", "Libro")) ;
        input = InterfazGrafica.mensajeIngreso(listarLibros() + "\ningrese ingrese ID del Libro", "Libro");
        //input = InterfazGrafica.mensajeMostrarTablaIngreso(ListadoEnTabla, input)
        if (input == null) {
            return;
        }

        try {
            id = Long.valueOf(input);

        } catch (ArithmeticException e) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - ARITMETIC");
            buscarLibro();
            return;
        } catch (Exception j) {
            InterfazGrafica.mensajeCancelar("ingrese numeros", "ERROR BUSCAR - EXCEPTION");
            buscarLibro();
            return;
        }

        Libro a = null;
        try {

            a = ld.buscarLibroXId(id);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al vuscar el Libro con el id: " + id, "error ID");
            return;
        }

        if (a != null) {
            InterfazGrafica.mensajeMostrar(a.toString(), "LISTA DE LibroES");
        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el Libro con el id: " + id, "ERROR AL BUSCAR Libro");
        }
    }

    public void buscarLibroXnombre() {
        
        String input=null;
        
        try {

            if (ld.noHayLibros()) {
                InterfazGrafica.mensajeCancelar("no hay Libroes reguistrados en el sistema", "BUSCAR Nombre");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Libroes X id " + e.getMessage(), "Error buscar X nombre - if");
            return;
        }

        input = InterfazGrafica.mensajeIngreso(listarLibros() + "\ningrese ingrese nombre del Libro", "NOMBRE");
        if (input == null) {
            return;
        }
        List<Libro> a = null;
        try {

            a = ld.BuscarLibro("titulo", input);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el Libro con el titulo: " + input, "error nombre");
            return;
        }

        InterfazGrafica.mensajeMostrar(listarLibros(a), "LISTA DE Libros");

    }

    public void eliminarXid() {

        Libro a = null;
        Long id;

        try {

            if (ld.noHayLibros()) {
                InterfazGrafica.mensajeCancelar("no hay Libroes reguistrados en el sistema", "BUSCAR Libro");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Libroes X id " + e.getMessage(), "Error buscar Libro - if");
        }

        id = Long.valueOf(InterfazGrafica.mensajeIngreso(listarLibros() + "\ningrese ingrese ID del Libro a eliminar", "Libro"));
        try {

            a = ld.buscarLibroXId(id);
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Libroes X id " + e.getMessage(), "Error buscar Libro - if");
            return;
        }
        if (a != null) {
            a.setAlta(false);
        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el Libro con el id: " + id, "ERROR ELIMINAR POR ID");
        }
        try {

            ld.editarLibro(a);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el Libro con el id: " + id, "error ID");
            return;
        }
        InterfazGrafica.mensajeMostrar("se elimino el Libro: " + a, "eliminar");
    }

    /**
     * pasar la lista en una cadena para mostrar
     *
     * @return
     */
    private String listarLibros() {
        List<Libro> a = null;
        try {

            a = ld.listarTodosLibros();

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error " + e.getMessage(), "error al mostrar Libroes");
            return "";
        }
        String c = "";

        for (Libro Libro : a) {
            c += Libro.getId() + " " + Libro.getTitulo() + espacios(Libro.getTitulo().length(), 30) + "\n";
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

    private String listarLibros(List<Libro> a) {

        String c = "";
        for (Libro Libro : a) {
            c += Libro.getId() + " " + Libro.getTitulo() + espacios(Libro.getTitulo().length(), 30) + "\n";
        }
        return c;

    }

    public void editarLibro() {

        String input;
        try {

            if (ld.noHayLibros()) {
                InterfazGrafica.mensajeCancelar("no hay Libroes reguistrados en el sistema", "EDITAR Libro");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Libroes X id " + e.getMessage(), "Error editar Libro - if");
        }

        input = InterfazGrafica.mensajeIngreso(listarLibros() + "\nIngrese ingrese ID del Libro", "LIBRO");
        Libro a = null;
        try {

            a = ld.buscarLibroXId(Long.valueOf(input));

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el Libro con el id: " + input, "error ID");
            return;
        }

        if (a != null) {

            a.setTitulo(InterfazGrafica.mensajeIngresoEditar("Ingrese nuevo nombre", "EDITAR Libro", a.getTitulo()));

            if (a.getTitulo() == null) {
                return;
            }

            input = InterfazGrafica.mensajeIngresoEditar("ingrese anio", "ANIO", a.getAnio().toString());
            if (input == null) {
                return;
            }

            do {
                try {

                    a.setAnio(Integer.valueOf(input));

                } catch (NumberFormatException e) {
                    InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - ANIO");
                    a.setAnio(-1);
                }
            } while (a.getAnio() < 0);

            input = InterfazGrafica.mensajeIngresoEditar("ingrese Ejemplares", "EJEMPLARES", a.getEjemplares().toString());
            if (input == null) {
                return;
            }

            do {
                try {

                    a.setEjemplares(Integer.valueOf(input));

                } catch (NumberFormatException e) {
                    InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - EJEMPLARES");
                    a.setEjemplares(-1);
                }
            } while (a.getEjemplares() < 0);

            input = InterfazGrafica.mensajeIngresoEditar("ingrese ejemplares prestados", "EJEMPLARES PRESTADOS", a.getPrestados().toString());
            if (input == null) {
                return;
            }

            do {
                try {

                    a.setPrestados(Integer.valueOf(input));

                } catch (NumberFormatException e) {
                    InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - EJEMPLARES PRESTADOS");
                    a.setPrestados(-1);
                }
            } while (a.getPrestados() < 0);

            input = InterfazGrafica.mensajeIngresoEditar("ingrese ejemplares restantes", "EJEMPLARES RESTANTES", a.getRestantes().toString());
            if (input == null) {
                return;
            }

            do {
                try {

                    a.setRestantes(Integer.valueOf(input));

                } catch (NumberFormatException e) {
                    InterfazGrafica.mensajeCancelar("ingrese numeros: " + e.getMessage(), "ERROR LIBRO - EJEMPLARES RESTANTES");
                    a.setRestantes(-1);
                }
            } while (a.getRestantes() < 0);

            if (!a.getAlta()) {
                a.setAlta(true);
            }

            try {
                ld.editarLibro(a);
            } catch (Exception e) {
                InterfazGrafica.mensajeCancelar("error al tratar de editar un Libro" + e.getMessage(), "ERROR AL EDITAR");
            }

        } else {
            InterfazGrafica.mensajeMostrar("no se encontro el Libro con el id: " + input, "ERROR AL BUSCAR Libro");
        }

    }

    public void buscarXautor() {

        String input;

        List<Libro> a = null;

        input = InterfazGrafica.mensajeMenu("ingrese autor a buscar", "BUSCAR X AUTOR", "src/images/autor.png");

        try {
            a = ld.consultaGenerica("Libro", "autor.nombre", input);
        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al consular los libros x autor "+e.getMessage(), "ERROR - BUSCAR X AUTOR");
        }
        InterfazGrafica.mensajeMostrar(listarLibros(a), "LIBROS DEL AUTOR " + input);
    }
    
    
    private JScrollPane ListadoEnTabla(List<Libro> a) {

        DefaultTableModel miTabla = new DefaultTableModel();
        miTabla.addColumn("Id");
        miTabla.addColumn("Titulo");
        miTabla.addColumn("Anio");
        miTabla.addColumn("Ejemplares");
        miTabla.addColumn("Alta");
        miTabla.addColumn("Autor");
        miTabla.addColumn("Editorial");

        for (int i = 0; i < a.size(); i++) {
            String fila[] = {"", "", "","","","",""};
//public Libro(String titulo, Integer anio, Integer ejemplares, Integer prestados, Integer restantes, Boolean alta, Autor autor, Editorial editorial) {
            fila[0] = a.get(i).getId().toString();
            fila[1] = a.get(i).getTitulo();
            fila[2] = a.get(i).getAnio().toString();
            fila[3] = a.get(i).getEjemplares().toString();
            fila[4] = (a.get(i).getAlta()) ? "Alta" : "Baja";
            fila[5] = a.get(i).getAutor().getNombre();
            fila[6] = a.get(i).getEditorial().getNombre();

            miTabla.addRow(fila);
        }
        JTable table = new JTable(miTabla);//creo un objeto tabla

        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.setPreferredScrollableViewportSize(new Dimension(760, 250));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.createVerticalScrollBar();
        scrollPane.createHorizontalScrollBar();

        return scrollPane;
    }
    
    
    //////////////////stranger//////////////////////
    
       public void eliminarTodosLibro() throws Exception {
        ld.eliminarTodos("Libro");
    }

    
   public void guardarLibro(Libro aux) {
        
        try {
            
            
            ld.guardarLibro(aux);

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("error al ingresar un Libro " + e.getMessage(), "ERROR - Libro");
        }
        
    }
    
    
    
}
