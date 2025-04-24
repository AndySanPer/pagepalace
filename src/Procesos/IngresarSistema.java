package Procesos;

import DAO.conexion;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javafx.scene.control.PasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class IngresarSistema {

    JFrame formularioingresar;

    JLabel etiquetainiciasesion;

    JLabel nombreusuario;
    JLabel contraseña;
    JLabel etiquetaregistrate;
    JLabel etiquetaregistrate2;

    JTextField txtnombreusuario;
    JPasswordField txtcontraseña;

    JButton btningresar;
    JButton btnsalir;
    JButton btnregistrarse;

    JPanel paneliniciasesion;
    JPanel panelnombreusuario;
    JPanel panelcontraseña;
    JPanel panelBotoningresar;
    JPanel panelBotonregistrarse;
    JPanel paneletiquetaregistrate;
    JPanel paneletiquetaregistrate2;
    JPanel panelregistrarse;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    String sql = "";
    int sw = 0;
    conexion mycon = new conexion();

    IngresarSistema() {
        con = mycon.conecta();
        formularioingresar = new JFrame("Ingresar al sistema");
        etiquetainiciasesion = new JLabel("INICIAR SESIÓN");
        nombreusuario = new JLabel("Usuario");
        contraseña = new JLabel("Contraseña");
        txtnombreusuario = new JTextField(15);
        txtcontraseña = new JPasswordField(13);
        btnregistrarse = new JButton("REGISTRARSE");
        btningresar = new JButton("INGRESAR");
        btnsalir = new JButton("SALIR");

        etiquetaregistrate = new JLabel("Aun no te has registrado?");
        etiquetaregistrate2 = new JLabel("    Registrate para que puedas iniciar sesión");
        paneliniciasesion = new JPanel();
        panelnombreusuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelcontraseña = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotoningresar = new JPanel();
        paneletiquetaregistrate = new JPanel();
        panelBotonregistrarse = new JPanel();
        panelregistrarse = new JPanel();
        paneletiquetaregistrate2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

    }

    public void darforma() {

        formularioingresar.setLayout(new GridLayout(8, 1));
        paneliniciasesion.add(etiquetainiciasesion);
        panelnombreusuario.add(nombreusuario);
        panelnombreusuario.add(txtnombreusuario);
        panelcontraseña.add(contraseña);
        panelcontraseña.add(txtcontraseña);
        panelBotoningresar.add(btningresar);
        panelBotoningresar.add(btnsalir);
        paneletiquetaregistrate.add(etiquetaregistrate);
        paneletiquetaregistrate2.add(etiquetaregistrate2);
        panelregistrarse.add(btnregistrarse);

        formularioingresar.add(paneliniciasesion);
        formularioingresar.add(panelnombreusuario);
        formularioingresar.add(panelcontraseña);
        formularioingresar.add(panelBotoningresar);
        formularioingresar.add(paneletiquetaregistrate);
        formularioingresar.add(etiquetaregistrate2);
        formularioingresar.add(panelregistrarse);

       
        formularioingresar.setVisible(true);
        formularioingresar.pack();
        formularioingresar.setLocationRelativeTo(null);
        formularioingresar.setSize(280, 300);

        txtnombreusuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtnombreusuario.getText().equals("")) {
                    //JOptionPane.showMessageDialog(null, "El Id del usuarios es obligatorio *****");
                    DesktopNotify.showDesktopMessage("Error", "El usuario es obligatorio", 1, 3000);
                } else {
                    txtcontraseña.requestFocusInWindow();

                }
            }
        });
        txtcontraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtcontraseña.getText().equals("")) {
                    //JOptionPane.showMessageDialog(null, "El Id del usuarios es obligatorio *****");
                    DesktopNotify.showDesktopMessage("Error", "La contraseña es obligatoria", 1, 3000);
                } else {
                    btningresar.requestFocusInWindow();

                }
            }
        });

        btningresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                validar();

            }
        });

        btnsalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sw = JOptionPane.showConfirmDialog(null, "En verdad deseas salir?");
                if (sw == 0) {
                    formularioingresar.dispose();

                }
            }
        });

        btnregistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registrarse myregistrase = new Registrarse();
                myregistrase.darforma();
            }
        });

    }

    public void validar() {
        sql = "select * from usuarios where nombreusuario='" + txtnombreusuario.getText();
        sql += "' and contrasena ='" + txtcontraseña.getText() + "'";
        System.out.println(sql);
        sw = 0;

        try {
            stmt = con.prepareStatement(sql);
            tabla = stmt.executeQuery();
            while (tabla.next()) {
                sw = 1;
                if (tabla.getString(5).equals(txtcontraseña.getText())) {
                    String nombre = txtnombreusuario.getText();
                    DesktopNotify.showDesktopMessage("BIENVENID@", nombre, 7, 3000);

                    formularioingresar.dispose();
                    Menuprincipal mymenu = new Menuprincipal();
                    mymenu.darformamenu();

                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        if (sw == 0) {
            //JOptionPane.showMessageDialog(null, "Usuario Inexistente");
            DesktopNotify.showDesktopMessage("Error", "USUARIO O CONTRASEÑA INCORRECTOS", 3, 3000);
        }

    }


    public static void main(String[] args) {
        IngresarSistema myingresis = new IngresarSistema();
        myingresis.darforma();
    }

}
