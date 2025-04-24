package Procesos;

import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Barradecarga {

    JFrame barra;
    JProgressBar porcentajedecarga;
    JLabel imagenLabel;

    Barradecarga() {
        barra = new JFrame();
        porcentajedecarga = new JProgressBar();
        imagenLabel = new JLabel();
    }

    public void darformabarra() {
        barra.setUndecorated(true);
        barra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        barra.setSize(320, 230); 
        barra.setLocationRelativeTo(null);

       
        porcentajedecarga.setStringPainted(true);
        porcentajedecarga.setForeground(new Color(0x21e136));
        porcentajedecarga.setBorder(BorderFactory.createLineBorder(new Color(0x00000), 2));

     
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Andrea Sanchez\\Documents\\ANDREA ARCHIVOS\\BD RICARDO\\pagepalace\\portada.PNG");
        
        Image image = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(image));

       
        barra.setLayout(null);
        imagenLabel.setBounds(10, 10, 300, 200); 
        porcentajedecarga.setBounds(10, 210, 300, 20); // Colocar la barra de progreso justo debajo de la imagen
        barra.add(imagenLabel);
        barra.add(porcentajedecarga);
        barra.setVisible(true);

        // Proceso para que funcione el progreso de carga
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            porcentajedecarga.setValue(i);
        }

       
        IngresarSistema myingresis = new IngresarSistema();
        myingresis.darforma();
        barra.dispose();
    }

    public static void main(String[] args) {
        Barradecarga mybarrita = new Barradecarga();
        mybarrita.darformabarra();
    }
}
