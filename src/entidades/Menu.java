package entidades;

import service.AutorService;
import service.EditorialService;
import service.LibroService;

public class Menu {

    AutorService as = new AutorService();
    LibroService ls = new LibroService();
    EditorialService es = new EditorialService();

    public void menu() {

        int opc;

        do {
            opc = opcionMenu();
            switch (opc) {
                case 1:
                    menuLibro();
                    break;
                case 2:
                    menuAutor();
                    break;
                case 3:
                    menuEditorial();
                    break;
                case 4:
                    InterfazGrafica.mensajeProfe("Gracias por usar el sistema de libreria de egg\n  HAVE A NICE DAY ♥ ", "SALIR");
                    break;
                default:
                    InterfazGrafica.mensajeAdvertencia("Opcion incorrecta", "OPCION INCORRECTA");

            }
        } while (opc != 4);

    }

    public int opcionMenu() {

        String opc = InterfazGrafica.mensajeMenu(
                "*****MENU PRINCIPAL******\n" 
                + "\n1 - Libros"
                + "\n2 - Autor"
                + "\n3 - Editorial"
                + "\n4 - salir", "MENU PRINCIPAL");

        if (opc == null) {
            InterfazGrafica.mensajeProfe("Gracias por usar el sistema de libreria de egg\n  HAVE A NICE DAY ♥ ", "SALIR");
            System.exit(0);
        }

        try {
            return Integer.valueOf(opc);
        } catch (Exception e) {
            return -1;
        }

    }

    ///aca va todo lo de libro service
    public void menuLibro() {

        int opcion;
        do {
            opcion = opcionLibro();

            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                default:
                    InterfazGrafica.mensajeAdvertencia("opcion incorrecta", "OPCION INCORRECTA");
            }

        } while (opcion != 10);

    }

    public int opcionLibro() {

        String opc = InterfazGrafica.mensajeMenu("******LIBROS******\n"
                + "\n1 - Mostrar todos los libros"
                + "\n2 - Ingresar nuevo Libro"
                + "\n3 - Buscar libro por ISBN"
                + "\n4 - Buscar libro por titulo"
                + "\n5 - Buscar libros por autor"
                + "\n6 - Buscar libros por editorial"
                + "\n7 - Modificar un libro"
                + "\n8 - Eliminar por ISBN"
                + "\n9 - Eliminar por titulo"
                + "\n10 - salir",
                "MENU LIBROS", "src/images/libro.png");

        if (opc == null) {
            System.exit(1);
        }

        try {
            return Integer.valueOf(opc);
        } catch (Exception e) {
            return -1;
        }

    }

    public void menuAutor() {

        int opcion;

        do {
            opcion = opcionAutor();
            switch (opcion) {
                case 1:
                    as.ingresarAutor();
                    break;
                case 2:
                    as.mostrarAutores();
                    break;
                case 3:
                    as.buscarAutor();
                    break;
                case 4:
                    as.buscarAutorXnombre();
                    break;
                case 5:
                    as.eliminarXid();
                    break;
                case 6:
                    //editar
                    break;
                case -2:
                case 7:
                    //salir
                    break;
                default:
                    InterfazGrafica.mensajeAdvertencia("opcion incorrecta", "OPCION INCORRECTA");
            }

        } while (opcion != 7 && opcion != (-2));

    }

    public int opcionAutor() {

        String opc = InterfazGrafica.mensajeMenu("******LIBROS******\n"
                + "\n1 - Crear Autor"
                + "\n2 - Mostrar los autores"
                + "\n3 - Buscar Autor por ID"
                + "\n4 - Buscar Autor pot nombre"
                + "\n5 - Eliminar Autor"
                + "\n6 - editar autor"
                + "\n7 - salir",
                "MENU LIBROS", "src/images/autor.png");

        if (opc == null) {
            return -2;
        }

        try {
            return Integer.valueOf(opc);
        } catch (Exception e) {
            return -1;
        }

    }
    public void menuEditorial() {

        int opcion;

        do {
            opcion = opcionEditorial();
            switch (opcion) {
                case 1:
                    es.ingresarEditorial();
                    break;
                case 2:
                    es.mostrarEditoriales();
                    break;
                case 3:
                    es.buscarEditorial();
                    break;
                case 4:
                    es.buscarEditorialXnombre();
                    break;
                case 5:
                    es.eliminarXid();
                    break;
                case 6:
                    //editar
                    break;
                case -2:
                case 7:
                    //salir
                    break;
                default:
                    InterfazGrafica.mensajeAdvertencia("opcion incorrecta", "OPCION INCORRECTA");
            }

        } while (opcion != 7 && opcion != (-2));

    }

    public int opcionEditorial() {

        String opc = InterfazGrafica.mensajeMenu("******LIBROS******\n"
                + "\n1 - Crear Edotorial"
                + "\n2 - Mostrar los Editorales"
                + "\n3 - Buscar Edotiral por ID"
                + "\n4 - Buscar Editorial pot nombre"
                + "\n5 - Eliminar Editorial"
                + "\n6 - editar editorial"
                + "\n7 - salir",
                "MENU LIBROS", "src/images/editorial.png");

        if (opc == null) {
            return -2;
        }

        try {
            return Integer.valueOf(opc);
        } catch (Exception e) {
            return -1;
        }

    }

}
