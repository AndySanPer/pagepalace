package Procesos;

import javax.swing.*;
import java.sql.*;
import DAO.conexion;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ds.desktop.notify.*;

public class Lectores {

    JLabel ID_Lector;
    JLabel Nombre;
    JLabel ApellidoPa;
    JLabel ApellidoMa;
    JLabel FechaNacimiento;
    JLabel Edad;
    JLabel Direccion;
    JLabel Codigopostal;
    JLabel Telefono;
    JLabel Correo;

    JTextField txtID_Lector;
    JTextField txtNombre;
    JTextField txtApellidoPa;
    JTextField txtApellidoMa;
    JTextField txtFechaNacimiento;
    JTextField txtEdad;
    JTextField txtDireccion;
    JTextField txtCodigopostal;
    JTextField txtTelefono;
    JTextField txtCorreo;

    JFrame FormularioLectores;

    JPanel panelID_Lector;
    JPanel panelNombre;
    JPanel panelApellidoPa;
    JPanel panelApellidoMa;
    JPanel panelFechaNacimiento;
    JPanel panelEdad;
    JPanel panelDireccion;
    JPanel panelCodigopostal;
    JPanel panelTelefono;
    JPanel panelCorreo;
    JPanel panelBotones;
    JButton btnnuevo, btnguardar, btnmodificar, btnborrar, btnconsultar, btnsalir;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    int sw = 0;
    String sql = "";
    conexion mycon = new conexion();

    Lectores() {
        con = mycon.conecta();

        FormularioLectores = new JFrame("Registro");
        ID_Lector = new JLabel("ID_Lector");
        Nombre = new JLabel("Nombre");
        ApellidoPa = new JLabel("Apellido Paterno");
        ApellidoMa = new JLabel("Apellido Materno");
        FechaNacimiento = new JLabel("Fecha de nacimiento");
        Edad = new JLabel("Edad");
        Direccion = new JLabel("Dirección");
        Codigopostal = new JLabel("Codigo Postal");
        Telefono = new JLabel("Telefono");
        Correo = new JLabel("Correo");

        txtID_Lector = new JTextField(25);
        txtNombre = new JTextField(25);
        txtApellidoPa = new JTextField(25);
        txtApellidoMa = new JTextField(25);
        txtFechaNacimiento = new JTextField(25);
        txtEdad = new JTextField(25);
        txtDireccion = new JTextField(25);
        txtCodigopostal = new JTextField(25);
        txtCorreo = new JTextField(25);
        txtTelefono = new JTextField(25);

        btnnuevo = new JButton("NUEVO");
        btnguardar = new JButton("GUARDAR");
        btnmodificar = new JButton("MODIFICAR");
        btnborrar = new JButton("BORRAR");
        btnconsultar = new JButton("CONSULTAR");
        btnsalir = new JButton("REGRESAR");

        panelID_Lector = new JPanel();
        panelNombre = new JPanel();
        panelApellidoPa = new JPanel();
        panelApellidoMa = new JPanel();
        panelFechaNacimiento = new JPanel();
        panelEdad = new JPanel();
        panelDireccion = new JPanel();
        panelCodigopostal = new JPanel();
        panelTelefono = new JPanel();
        panelCorreo = new JPanel();
        panelBotones = new JPanel();
    }

    public void darforma() {
        FormularioLectores.setLayout(new GridLayout(11, 1));

        panelID_Lector.add(ID_Lector);
        panelID_Lector.add(txtID_Lector);

        panelNombre.add(Nombre);
        panelNombre.add(txtNombre);

        panelApellidoPa.add(ApellidoPa);
        panelApellidoPa.add(txtApellidoPa);

        panelApellidoMa.add(ApellidoMa);
        panelApellidoMa.add(txtApellidoMa);

        panelFechaNacimiento.add(FechaNacimiento);
        panelFechaNacimiento.add(txtFechaNacimiento);

        panelEdad.add(Edad);
        panelEdad.add(txtEdad);

        panelDireccion.add(Direccion);
        panelDireccion.add(txtDireccion);

        panelCodigopostal.add(Codigopostal);
        panelCodigopostal.add(txtCodigopostal);

        panelTelefono.add(Telefono);
        panelTelefono.add(txtTelefono);

        panelCorreo.add(Correo);
        panelCorreo.add(txtCorreo);

        panelBotones.add(btnnuevo);
        panelBotones.add(btnguardar);
        panelBotones.add(btnmodificar);
        panelBotones.add(btnborrar);
        panelBotones.add(btnconsultar);
        panelBotones.add(btnsalir);

        FormularioLectores.add(panelID_Lector);
        FormularioLectores.add(panelNombre);
        FormularioLectores.add(panelApellidoPa);
        FormularioLectores.add(panelApellidoMa);
        FormularioLectores.add(panelFechaNacimiento);
        FormularioLectores.add(panelEdad);
        FormularioLectores.add(panelDireccion);
        FormularioLectores.add(panelCodigopostal);
        FormularioLectores.add(panelTelefono);
        FormularioLectores.add(panelCorreo);
        FormularioLectores.add(panelBotones);
        FormularioLectores.setVisible(true);
        FormularioLectores.pack();
        FormularioLectores.setLocationRelativeTo(null);//coloca el jframe en el centro de la pantalla

        //ESCUCHAS DE LAS CAJAS DE TEXTO
        txtID_Lector.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtID_Lector.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtNombre.requestFocusInWindow();
                }
            }
        });

        txtNombre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtNombre.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL NOMBRE DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtApellidoPa.requestFocusInWindow();
                }

            }
        });

        txtApellidoPa.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtApellidoPa.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL APELLIDO PATERNO DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtApellidoMa.requestFocusInWindow();
                }
            }
        });

        txtApellidoMa.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtApellidoMa.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL APELLIDO MATERNO DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtFechaNacimiento.requestFocusInWindow();

                }
            }
        });

        txtFechaNacimiento.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtFechaNacimiento.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DE NACIMIENTO DEL LECTOR ES REQUERIDA", 1, 3000);
                } else {
                    txtEdad.requestFocusInWindow();

                }
            }
        });

        txtEdad.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtEdad.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA EDAD DEL LECTOR ES REQUERIDA", 1, 3000);
                } else {
                    txtDireccion.requestFocusInWindow();

                }
            }
        });

        txtDireccion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtDireccion.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA DIRECCION DEL LECTOR ES REQUERIDA", 1, 3000);
                } else {
                    txtCodigopostal.requestFocusInWindow();

                }
            }
        });

        txtCodigopostal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtCodigopostal.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL CODIGO POSTAL DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtTelefono.requestFocusInWindow();

                }
            }
        });

        txtTelefono.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtTelefono.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL TELEFONO DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    txtCorreo.requestFocusInWindow();

                }
            }
        });

        txtCorreo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtCorreo.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL CORREO DEL LECTOR ES REQUERIDO", 1, 3000);
                } else {
                    btnnuevo.requestFocusInWindow();

                }
            }
        });

        //CAJAS DE ESCUCHA DE LOS BOTONES 
        btnnuevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nuevo();
            }
        });

        btnguardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guadar();
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

        txtID_Lector.setText(vacio);
        txtNombre.setText(vacio);
        txtApellidoPa.setText(vacio);
        txtApellidoMa.setText(vacio);
        txtFechaNacimiento.setText(vacio);
        txtEdad.setText(vacio);
        txtDireccion.setText(vacio);
        txtCodigopostal.setText(vacio);
        txtTelefono.setText(vacio);
        txtCorreo.setText(vacio);
        txtID_Lector.requestFocusInWindow();

    }

    public void guadar() {

        sql = "insert into lectores values (";
        sql += "\"" + txtID_Lector.getText() + "\" , ";
        sql += "\"" + txtNombre.getText() + "\" , ";
        sql += "\"" + txtApellidoPa.getText() + "\" , ";
        sql += "\"" + txtApellidoMa.getText() + "\" , ";
        sql += "\"" + txtFechaNacimiento.getText() + "\" , ";
        sql += "\"" + txtEdad.getText() + "\" , ";
        sql += "\"" + txtDireccion.getText() + "\" , ";
        sql += "\"" + txtCodigopostal.getText() + "\" , ";
        sql += "\"" + txtTelefono.getText() + "\" , ";
        sql += "\"" + txtCorreo.getText() + "\" ) ";
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

    public void modificar() {
        sql = "update lectores set ";
        sql += "nombre='" + txtNombre.getText() + "', ";
        sql += "apellipate='" + txtApellidoPa.getText() + "', ";
        sql += "apellimate='" + txtApellidoMa.getText() + "', ";
        sql += "fecnac='" + txtFechaNacimiento.getText() + "', ";
        sql += "edad='" + txtEdad.getText() + "', ";
        sql += "direccion='" + txtDireccion.getText() + "', ";
        sql += "codpos='" + txtCodigopostal.getText() + "', ";
        sql += "telefono='" + txtTelefono.getText() + "', ";
        sql += "correo='" + txtCorreo.getText() + "' ";
        sql += "where id_lector='" + txtID_Lector.getText() + "'";
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
        sql = "delete from lectores ";
        sql += " where id_LECTOR='" + txtID_Lector.getText() + "'";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            sw = stmt.executeUpdate();//metodoupdtae
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO BORRADO CON EXITO", 6, 3000);
            }
            nuevo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void consultar() {
        sw = 0;
        sql = "select * from lectores ";
        sql += "where id_lector='" + txtID_Lector.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtNombre.setText(tabla.getString(2));
                txtApellidoPa.setText(tabla.getString(3));
                txtApellidoMa.setText(tabla.getString(4));
                txtFechaNacimiento.setText(tabla.getString(5));
                txtEdad.setText(tabla.getString(6));
                txtDireccion.setText(tabla.getString(7));
                txtCodigopostal.setText(tabla.getString(8));
                txtTelefono.setText(tabla.getString(9));
                txtCorreo.setText(tabla.getString(10));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "REGISTRO INEXISTENTE", 8, 3000);
        }

    }

    public void salir() {
        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FormularioLectores.dispose();
                Menuprincipal mymenu = new Menuprincipal();
                mymenu.darformamenu();
            }
        });
    }

    public static void main(String[] args) {
        
        Barradecarga mybarrita = new Barradecarga();
        mybarrita.darformabarra();
    }

}
