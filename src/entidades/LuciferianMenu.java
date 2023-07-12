package entidades;

import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class LuciferianMenu {

    private static Clip clip;

    public static void hideMenu() {

        reproducirSonido("src/songs/metalica.WAV");
        int i;

        do {
            i = contraMenu();

            switch (i) {
                case 1:
                    //eliminar tod0s libros
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
                case -1:
                case -2:
                    //salir
                    break;
                default:
                    InterfazGrafica.mensajeContraCancelar("OPCION INCORRECTA", "666");
            }

        } while (i != 7 && i != (-2) && i != (-1));

    }

    private static int contraMenu() {

        String opc = InterfazGrafica.mensajeContraMenu("******CONTRA MENU******\n"
                + "\n1 - vaciar libros"
                + "\n2 - vaciar autores"
                + "\n3 - vaciar editoriales"
                + "\n4 - leer libros"
                + "\n5 - leer autores"
                + "\n6 - leer editoriales"
                + "\n7 - salir",
                "666", "src/images/diablo.png");

        if (opc == null) {
            return -2;
        }

        try {
            return Integer.valueOf(opc);
        } catch (NumberFormatException e) {
            return -1;
        }

    }

    public static void reproducirSonido(String ruta) {
        if (clip != null) {
            clip.stop();
        }
        try {

            Scanner leer = new Scanner(System.in);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ruta).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // detener la musica
            // clip.stop();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


}
