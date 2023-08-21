/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Libro(0String titulo, 1Integer anio, 2Integer ejemplares, 3Integer prestados,
 * 4Integer restantes, Boolean alta,5 Autor autor, 6Editorial editorial) public
 * Autor(String nombre, Boolean alta) Editorial(String nombre, Boolean alta)
 *
 * @author JAVIER ESPINDOLA
 */
public class ArchivoGestor {

    private static final String FILE_PATH_A = "src/file/autor.txt";
    private static final String FILE_PATH_E = "src/file/editorial.txt";
    private static final String FILE_PATH_L = "src/file/libro.txt";

    public List<Autor> leerAutor() {
        List<Autor> listaAutores = new ArrayList<>();
        try (BufferedReader leer = new BufferedReader(new FileReader(FILE_PATH_A))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                listaAutores.add(new Autor(linea, true));
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo" + e.getMessage());
        }
        return listaAutores;
    }

    public List<Libro> leerLibro() {
        List<Libro> listaLibros = new ArrayList<>();
        try (BufferedReader leer = new BufferedReader(new FileReader(FILE_PATH_L))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
               
                String[] registroLibro = linea.split(",");
                Libro l = new Libro(registroLibro[0], Integer.valueOf(registroLibro[1]), (int) (Math.random() * 5 + 1), (int) (Math.random() * 5 + 1), (int) (Math.random() * 5 + 1), true, new Autor(registroLibro[2], true), new Editorial(registroLibro[3], true));
                
                listaLibros.add(new Libro(registroLibro[0], Integer.valueOf(registroLibro[1]), (int) (Math.random() * 5 + 1), (int) (Math.random() * 5 + 1), (int) (Math.random() * 5 + 1), true, new Autor(registroLibro[2], true), new Editorial(registroLibro[3], true)));
               
            }
             
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo" + e.getMessage());
        }
       
        return listaLibros;
    }

    public List<Editorial> leerEditorial() {
         List<Editorial> listaEditoriales = new ArrayList<>();
        try (BufferedReader leer = new BufferedReader(new FileReader(FILE_PATH_E))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                listaEditoriales.add(new Editorial(linea, true));
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo" + e.getMessage());
        }
        return listaEditoriales; 
    }

}
