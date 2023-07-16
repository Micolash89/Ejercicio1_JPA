package entidades;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class InterfazGrafica {

    public static String mensajeMenu(String mensaje, String titulo) {

        UIManager UI = new UIManager();
        UI.put("OptionPane.background", Color.BITMASK);
        UI.put("Panel.background", Color.BITMASK);
        UI.put("OptionPane.messageForeground", Color.BLACK);

        UIManager.put("OptionPane.okButtonText", "Aceptar");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        ImageIcon icono = new ImageIcon("src/images/icono.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(80, 100, java.awt.Image.SCALE_SMOOTH));

        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    public static String mensajeIngreso(String cadena, String TITULO) {

        ImageIcon icono = new ImageIcon("src/images/question.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        return (String) JOptionPane.showInputDialog(null, cadena, TITULO, JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    public static String mensajeIngresoEditar(String cadena, String TITULO, String variable) {

        ImageIcon icono = new ImageIcon("src/images/question.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        //return (String) JOptionPane.showInputDialog(null, cadena, TITULO, JOptionPane.QUESTION_MESSAGE, icono, null, null);
        return (String) JOptionPane.showInputDialog(null, cadena, TITULO, JOptionPane.QUESTION_MESSAGE, icono, null, variable);

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
    public static void mensajeContraCancelar(String cadena, String titulo) {

        ImageIcon icono = new ImageIcon("src/images/lilith.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(200, 250, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.ERROR_MESSAGE, icono);

    }

    public static void mensajeAdvertencia(String cadena, String titulo) {

        ImageIcon icono = new ImageIcon("src/images/warning2.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.WARNING_MESSAGE, icono);

    }

    public static void mensajeProfe(String cadena, String titulo) {

        String fotos[] = {"src/images/bruno.png", "src/images/camila.png", "src/images/coti.png", "src/images/aron.png", "src/images/yo.png", "src/images/seba.png", "src/images/diego.png", "src/images/diegoDark.png", "src/images/sebastian.png", "src/images/sabri.png", "src/images/pablo.png", "src/images/agus.png","src/images/maxi.png","src/images/javier.png","src/images/leonel.png"};

        ImageIcon icono = new ImageIcon(fotos[(int) (Math.random() * fotos.length)]);
        icono = new ImageIcon(icono.getImage().getScaledInstance(150, 170, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.WARNING_MESSAGE, icono);

    }

    public static String mensajeMenu(String mensaje, String titulo, String logo) {

        ImageIcon icono = new ImageIcon(logo);
        icono = new ImageIcon(icono.getImage().getScaledInstance(80, 100, java.awt.Image.SCALE_SMOOTH));

        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    public static String mensajeContraMenu(String mensaje, String titulo, String logo) {

        UIManager UI = new UIManager();
        UI.put("OptionPane.background", Color.BLACK);
        UI.put("Panel.background", Color.BLACK);
        
        UI.put("OptionPane.messageForeground", Color.WHITE);
        
        UIManager.put("OptionPane.buttonBackground", Color.BLACK);

        ImageIcon icono = new ImageIcon(logo);
        icono = new ImageIcon(icono.getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));

        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    public static void mensajeMostrarTabla(JScrollPane ListadoEnTabla, String titulo) {

        //JOptionPane.showMessageDialog(null, ListadoEnTabla(), "Listado de profesores de Codo a Codo", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, ListadoEnTabla, titulo, JOptionPane.PLAIN_MESSAGE);

    }

    public static String mensajeMostrarTablaIngreso(JScrollPane ListadoEnTabla, String titulo) {

        //numProfesorString = JOptionPane.showInputDialog(null,ListadoEnTabla(),"Ingrese el número de profesor que desea eliminar",JOptionPane.PLAIN_MESSAGE);    
        return JOptionPane.showInputDialog(null, ListadoEnTabla, titulo, JOptionPane.PLAIN_MESSAGE);

    }

}
