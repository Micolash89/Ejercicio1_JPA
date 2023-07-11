/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author JAVIER ESPINDOLA
 */
public class InterfazGrafica {
    
      public static String mensajeMenu(String mensaje, String titulo) {

        ImageIcon icono = new ImageIcon("src/images/icono.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(80, 100, java.awt.Image.SCALE_SMOOTH));
        
        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    public static String mensajeIngreso(String cadena, String TITULO) {

        ImageIcon icono = new ImageIcon("src/images/question.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        return (String) JOptionPane.showInputDialog(null, cadena, TITULO, JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    // este metodo sirve para mostrar mensajes en pantalla
    public static void mensajeMostrar(String cadena, String titulo) {

        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.PLAIN_MESSAGE);

    }

    // este metodo sirve para mostrar mensajes en pantalla
    public static void mensajeExito(String cadena, String titulo) {

        ImageIcon icono = new ImageIcon("src/images/check.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.INFORMATION_MESSAGE, icono);

    }

    public static void mensajeCancelar(String cadena, String titulo) {

        ImageIcon icono = new ImageIcon("src/images/cancel.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.ERROR_MESSAGE, icono);

    }

    public static void mensajeAdvertencia(String cadena, String titulo) {

        ImageIcon icono = new ImageIcon("src/images/warning2.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.WARNING_MESSAGE, icono);

    }

    public static void mensajeProfe(String cadena, String titulo) {

        String fotos[]={"src/images/bruno.png","src/images/camila.png","src/images/coti.png","src/images/aron.png","src/images/yo.png","src/images/seba.png","src/images/diego.png","src/images/diegoDark.png","src/images/sebastian.png","src/images/sabri.png"};
        
        ImageIcon icono = new ImageIcon(fotos[(int)(Math.random()*fotos.length)]);
        icono = new ImageIcon(icono.getImage().getScaledInstance(150, 170, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.WARNING_MESSAGE, icono);

    }

    static String mensajeMenu(String mensaje, String titulo, String logo) {

         ImageIcon icono = new ImageIcon(logo);
        icono = new ImageIcon(icono.getImage().getScaledInstance(80, 100, java.awt.Image.SCALE_SMOOTH));
        
        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, icono, null, null);


    }
    
    
}
