package Procesos;

import DAO.conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

public class Menuprincipal {

    JFrame formulariomenu;

    JMenu Catalogos;
    JMenu Procesos;

    JMenuItem cataLibros;

    JMenuItem cataSalir;

    JMenuItem proPrestamos;

    JSeparator js1;

    JMenuBar jbar;

    JLabel etiquetaimagen;
    ImageIcon imagen;

    int sw = 0;

    public Menuprincipal() {

        formulariomenu = new JFrame("Menu principal");

        imagen = new ImageIcon("E:\\respaldo andrea pc monche\\BD RICARDO\\java\\pagepalace\\pagepalace\\menu.JPG");
        etiquetaimagen = new JLabel();

        jbar = new JMenuBar();

        jbar = new JMenuBar();
        js1 = new JSeparator();

        //opciones
        Catalogos = new JMenu("CATALOGOS");
        Procesos = new JMenu("PROCESOS");

        //opciones catalogos
        cataLibros = new JMenuItem("Libros");

        cataSalir = new JMenuItem("Salir");

        //opciones procesos
        proPrestamos = new JMenuItem("Prestamos");

       
        int nuevoAnchoMenu = 120;
        int nuevoAltoMenu = 30;  


        Font nuevaFuenteMenu = new Font("ARIAL", Font.BOLD, 14); // Puedes cambiar el tipo de fuente y el tamaño


        cataLibros.setPreferredSize(new Dimension(nuevoAnchoMenu, nuevoAltoMenu));
        cataLibros.setFont(nuevaFuenteMenu);

        cataSalir.setPreferredSize(new Dimension(nuevoAnchoMenu, nuevoAltoMenu));
        cataSalir.setFont(nuevaFuenteMenu);

        proPrestamos.setPreferredSize(new Dimension(nuevoAnchoMenu, nuevoAltoMenu));
        proPrestamos.setFont(nuevaFuenteMenu);

        cataSalir.setBackground(new Color(255, 0, 0)); // rojo


        int nuevoAnchoJMenu = 120; 
        int nuevoAltoJMenu = 40;   


        Font nuevaFuenteJMenu = new Font("ARIAL", Font.BOLD, 16); 


        Catalogos.setPreferredSize(new Dimension(nuevoAnchoJMenu, nuevoAltoJMenu));
        Catalogos.setFont(nuevaFuenteJMenu);

        Procesos.setPreferredSize(new Dimension(nuevoAnchoJMenu, nuevoAltoJMenu));
        Procesos.setFont(nuevaFuenteJMenu);

        Catalogos.setForeground(new Color(255, 255, 255));
        Procesos.setForeground(new Color(255, 255, 255));


    }

    public void darformamenu() {

        Catalogos.add(cataLibros);

        Catalogos.add(js1);//separador
        Catalogos.add(cataSalir);
        cataSalir.setBorder(BorderFactory.createLineBorder(Color.red));

        Procesos.add(proPrestamos);

        jbar.add(Catalogos);
        jbar.add(Procesos);
        jbar.setBackground(new Color(170, 163, 204));
        int nuevoAncho = 200; // Puedes cambiar este valor
        int nuevoAlto = 40;   // Puedes cambiar este valor
        jbar.setPreferredSize(new Dimension(nuevoAncho, nuevoAlto));

        etiquetaimagen.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(490, 290, Image.SCALE_SMOOTH)));
        formulariomenu.add(etiquetaimagen);

        formulariomenu.setJMenuBar(jbar);
        formulariomenu.setVisible(true);
        formulariomenu.setSize(500, 300);
        formulariomenu.setLocationRelativeTo(null);

        cataLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Libros mylibro = new Libros();
                mylibro.darforma();
                formulariomenu.dispose();
            }
        });

        cataSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sw = JOptionPane.showConfirmDialog(null, "En verdad deseas salir de tu sesión?");
                if (sw == 0) {
                    IngresarSistema myingresis = new IngresarSistema();
                    myingresis.darforma();
                    formulariomenu.dispose();

                }

            }
        });

        proPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prestamos mylector = new Prestamos();
                mylector.darforma();
                formulariomenu.dispose();
            }
        });

        actualizarImagen(formulariomenu.getWidth(), formulariomenu.getHeight());

        formulariomenu.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent componentEvent) {
                actualizarImagen(formulariomenu.getWidth(), formulariomenu.getHeight());
            }
        });

    }

    private void actualizarImagen(int ancho, int alto) {
        // Asegúrate de dejar espacio para otros componentes o usa un LayoutManager adecuado
        ImageIcon iconoRedimensionado = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        etiquetaimagen.setIcon(iconoRedimensionado);
        etiquetaimagen.revalidate();
        etiquetaimagen.repaint();
    }

    public static void main(String[] args) {
        Menuprincipal mymenu = new Menuprincipal();
        mymenu.darformamenu();
    }
}
