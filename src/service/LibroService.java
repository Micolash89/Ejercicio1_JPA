/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entidades.Editorial;
import entidades.Libro;
import entidades.InterfazGrafica;
import java.util.List;
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

        InterfazGrafica.mensajeMostrar(listarLibros(), "LISTA DE LibroES");

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

        try {

            if (ld.noHayLibros()) {
                InterfazGrafica.mensajeCancelar("no hay Libroes reguistrados en el sistema", "BUSCAR Nombre");
                return;
            }

        } catch (Exception e) {
            InterfazGrafica.mensajeCancelar("Error al buscar la lista de Libroes X id " + e.getMessage(), "Error buscar X nombre - if");
            return;
        }

        String nombre = InterfazGrafica.mensajeIngreso(listarLibros() + "\ningrese ingrese nombre del Libro", "NOMBRE");
        if (nombre == null) {
            return;
        }
        List<Libro> a = null;
        try {

            a = ld.BuscarLibro("nombre", nombre);

        } catch (Exception e) {
            InterfazGrafica.mensajeAdvertencia("error al buscar el Libro con el nombre: " + nombre, "error nombre");
            return;
        }

        InterfazGrafica.mensajeMostrar(listarLibros(a), "LISTA DE LibroES");

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
}
