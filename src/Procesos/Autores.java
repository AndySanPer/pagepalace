package Procesos;

import javax.swing.*;
import java.sql.*;
import DAO.conexion;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ds.desktop.notify.*;

public class Autores {

    JFrame formularioautores;

    JLabel ID_Autor;
    JLabel Nombre;
    JLabel Apellido;
    JLabel FechaNacimiento;
    JLabel Nacionalidad;

    JTextField txtID_Autor;
    JTextField txtNombre;
    JTextField txtApellido;
    JTextField txtFechaNacimiento;
    JTextField txtNacionalidad;

    JPanel panelID_Autor;
    JPanel panelNombre;
    JPanel panelApellido;
    JPanel panelFechaNacimiento;
    JPanel panelNacionalidad;
    JPanel panelBotones;

    JButton btnnuevo, btnguardar, btnmodificar, btnborrar, btnconsultar, btnsalir;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    int sw = 0;
    String sql = "";
    conexion mycon = new conexion();

    Autores() {
        con = mycon.conecta();

        formularioautores = new JFrame("Autores");

        ID_Autor = new JLabel("ID_Autor");
        Nombre = new JLabel("Nombre");
        Apellido = new JLabel("Apellidos");
        FechaNacimiento = new JLabel("Fecha de nacimiento");
        Nacionalidad = new JLabel("Nacionalidad");

        txtID_Autor = new JTextField(25);
        txtNombre = new JTextField(25);
        txtApellido = new JTextField(25);
        txtFechaNacimiento = new JTextField(25);
        txtNacionalidad = new JTextField(25);

        btnnuevo = new JButton("NUEVO");
        btnguardar = new JButton("GUARDAR");
        btnmodificar = new JButton("MODIFICAR");
        btnborrar = new JButton("BORRAR");
        btnconsultar = new JButton("CONSULTAR");
        btnsalir = new JButton("REGRESAR");

        panelID_Autor = new JPanel();
        panelNombre = new JPanel();
        panelApellido = new JPanel();
        panelFechaNacimiento = new JPanel();
        panelNacionalidad = new JPanel();
        panelBotones = new JPanel();

    }

    public void darforma() {
        formularioautores.setLayout(new GridLayout(6, 1));

        panelID_Autor.add(ID_Autor);
        panelID_Autor.add(txtID_Autor);

        panelNombre.add(Nombre);
        panelNombre.add(txtNombre);

        panelApellido.add(Apellido);
        panelApellido.add(txtApellido);

        panelFechaNacimiento.add(FechaNacimiento);
        panelFechaNacimiento.add(txtFechaNacimiento);

        panelNacionalidad.add(Nacionalidad);
        panelNacionalidad.add(txtNacionalidad);

        panelBotones.add(btnnuevo);
        panelBotones.add(btnguardar);
        panelBotones.add(btnmodificar);
        panelBotones.add(btnborrar);
        panelBotones.add(btnconsultar);
        panelBotones.add(btnsalir);

        formularioautores.add(panelID_Autor);
        formularioautores.add(panelNombre);
        formularioautores.add(panelApellido);
        formularioautores.add(panelFechaNacimiento);
        formularioautores.add(panelNacionalidad);
        formularioautores.add(panelBotones);

        formularioautores.setVisible(true);
        formularioautores.pack();
        formularioautores.setLocationRelativeTo(null);//coloca el jframe en el centro de la pantalla

        //ESCUCHAS DE LAS CAJAS DE TEXTO
        txtID_Autor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Autor.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL ID DEL AUTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtNombre.requestFocusInWindow();
                }
            }
        });

        txtNombre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtNombre.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL NOMBRE DEL AUTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtApellido.requestFocusInWindow();
                }
            }
        });
        txtApellido.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtApellido.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "EL O LOS APELLIDOS DEL AUTOR SON REQUERIDOS", 1, 3000);
                } else {
                    txtFechaNacimiento.requestFocusInWindow();
                }
            }
        });

        txtFechaNacimiento.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtFechaNacimiento.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "LA FECHA DE NACIMIENTO DEL AUTOR ES REQUERIDA", 1, 3000);
                } else {
                    txtNacionalidad.requestFocusInWindow();
                }
            }
        });

        txtNacionalidad.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtNacionalidad.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("Error", "LA NACIONALIDAD DEL AUTOR ES REQUERIDA", 1, 3000);
                } else {
                    btnnuevo.requestFocusInWindow();
                }
            }
        });

        btnnuevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nuevo();

            }
        });

        btnguardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardar();

            }
        });

        btnmodificar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                modificar();

            }
        });

        btnborrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                borrar();

            }
        });
        btnconsultar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                consultar();

            }
        });

        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                salir();

            }
        });

    }

    public void nuevo() {
        String vacio = "";
        txtID_Autor.setText(vacio);
        txtNombre.setText(vacio);
        txtApellido.setText(vacio);
        txtFechaNacimiento.setText(vacio);
        txtNacionalidad.setText(vacio);
        txtID_Autor.requestFocusInWindow();

    }
//insert into autores values
//(001,' Bram','Stoker','1847-11-08','Irlandesa');

    public void guardar() {
        sql = "insert into autores values(";
        sql += "'" + txtID_Autor.getText() + "',";
        sql += "'" + txtNombre.getText() + "',";
        sql += "'" + txtApellido.getText() + "',";
        sql += "'" + txtFechaNacimiento.getText() + "',";
        sql += "'" + txtNacionalidad.getText() + "')";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);
            sw = stmt.executeUpdate();
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO DADO DEL ALTA CON EXITO", 7, 3000);
                nuevo();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void consultar() {
        //select ID_Autor from autores
        //where id_autor =1;
        sql = "select * from autores where id_autor = " + txtID_Autor.getText();
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtNombre.setText(tabla.getString(2));
                txtApellido.setText(tabla.getString(3));
                txtFechaNacimiento.setText(tabla.getString(4));
                txtNacionalidad.setText(tabla.getString(5));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "REGISTRO INEXISTENTE", 8, 3000);
        }

    }

    public void modificar() {
        //update autores set 
        //nombre = 'lola', apellido = 'sanchez', fechanaci = '2008-09-05', nacionalidad='rusa'
        //where id_autor = 123;
        sql = "update autores set";
        sql += " nombre='" + txtNombre.getText() + "',apellido='" + txtApellido.getText() + "',fechanaci='" + txtFechaNacimiento.getText() + "',nacionalidad='" + txtNacionalidad.getText() + "'";
        sql += " where id_autor=" + txtID_Autor.getText();

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            sw = stmt.executeUpdate();//metodoupdtae
            if (sw != 0) {

                DesktopNotify.showDesktopMessage("ESO", "REGISTRO MODIFICADO CON EXITO", 6, 3000);
                nuevo();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void borrar() {
        //delete from autores 
        //where id_autor =123;
        sql = "delete from autores where id_autor=" + txtID_Autor.getText();
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            sw = stmt.executeUpdate();//metodoupdtae
            if (sw != 0) {

                DesktopNotify.showDesktopMessage("ESO", "REGISTRO BORRADO CON EXITO", 7, 3000);
            }
            nuevo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void salir() {
        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                formularioautores.dispose();
                Menuprincipal mymenu = new Menuprincipal();
                mymenu.darformamenu();
            }
        });
    }

    public static void main(String[] args) {
        Autores myautor = new Autores();
        myautor.darforma();
    }
}
