package Procesos;

import javax.swing.*;
import java.sql.*;
import DAO.conexion;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ds.desktop.notify.*;
import java.awt.Color;
import javax.swing.JComboBox;

public class Prestamos {

    JFrame Formularioprestamos;

    JLabel ID_Prestamo;
    JLabel ID_Libro;
    JLabel ID_Lector;
    JLabel fecInicioPrestamo;
    JLabel FecDevolucion;
    JLabel Estado;

    JTextField txtID_Prestamo;

    JTextField txtID_Libro;
    JTextField txtnombrelibro;

    JTextField txtID_Lector;
    JTextField txtnombrelector;

    JTextField txtfecInicioPrestamo;
    JTextField txtFecDevolucion;

    JComboBox<String> EstadocomboBox = new JComboBox<>();

    JPanel panelID_Prestamo;
    JPanel panelID_Libro;
    JPanel panelID_Lector;
    JPanel panelfecInicioPrestamo;
    JPanel panelFecDevolucion;
    JPanel panelEstado;
    JPanel panelBotones;

    JButton btnnuevo, btnguardar, btnmodificar, btnborrar, btnconsultar, btnsalir, btnactivar;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    int sw = 0, swlec = 0, swlib = 0;
    String sql = "";
    conexion mycon = new conexion();

    Prestamos() {
        con = mycon.conecta();

        Formularioprestamos = new JFrame("Prestamos");

        ID_Prestamo = new JLabel("ID_Prestamos");
        ID_Libro = new JLabel("ID_Libro");
        ID_Lector = new JLabel("ID_Lector");
        fecInicioPrestamo = new JLabel("Fecha de inicio");
        FecDevolucion = new JLabel("Fecha de termino");
        Estado = new JLabel("Estado");

        txtID_Prestamo = new JTextField(25);
        txtID_Libro = new JTextField(8);
        txtnombrelibro = new JTextField(25);
        txtID_Lector = new JTextField(8);
        txtnombrelector = new JTextField(25);
        txtfecInicioPrestamo = new JTextField(25);
        txtFecDevolucion = new JTextField(25);
        txtfecInicioPrestamo = new JTextField("2023-11-28");
        txtFecDevolucion = new JTextField("2023-12-03");
        txtfecInicioPrestamo.setEditable(false);
        txtFecDevolucion.setEditable(false);

        EstadocomboBox.addItem(null);
        EstadocomboBox.addItem("Prestado");
        EstadocomboBox.addItem("Devuelto");
        EstadocomboBox.addItem("Vencido");

        btnnuevo = new JButton("NUEVO");
        btnguardar = new JButton("GUARDAR");
        btnmodificar = new JButton("MODIFICAR");
        btnborrar = new JButton("BORRAR");
        btnconsultar = new JButton("CONSULTAR");
        btnsalir = new JButton("REGRESAR");
        btnactivar = new JButton("HABILITAR");

        panelID_Prestamo = new JPanel();
        panelID_Libro = new JPanel();
        panelID_Lector = new JPanel();
        panelfecInicioPrestamo = new JPanel();
        panelFecDevolucion = new JPanel();
        panelEstado = new JPanel();
        panelBotones = new JPanel();

    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
            // Verifica si el número es negativo
            return d >= 1;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isValidDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(regex);
    }

    private boolean idPrestamoExiste(String idPrestamo) {
        try {
            String query = "SELECT count(*) FROM prestamos WHERE id_prestamo = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, idPrestamo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public void darforma() {
        Formularioprestamos.setLayout(new GridLayout(7, 1));

        panelID_Prestamo.add(ID_Prestamo);
        panelID_Prestamo.add(txtID_Prestamo);

        panelID_Libro.add(ID_Libro);
        panelID_Libro.add(txtID_Libro);
        panelID_Libro.add(txtnombrelibro);

        panelID_Lector.add(ID_Lector);
        panelID_Lector.add(txtID_Lector);
        panelID_Lector.add(txtnombrelector);

        panelfecInicioPrestamo.add(fecInicioPrestamo);
        panelfecInicioPrestamo.add(txtfecInicioPrestamo);

        panelFecDevolucion.add(FecDevolucion);
        panelFecDevolucion.add(txtFecDevolucion);

        panelEstado.add(Estado);
        panelEstado.add(EstadocomboBox);

        panelBotones.add(btnnuevo);
        panelBotones.add(btnguardar);
        panelBotones.add(btnmodificar);
        panelBotones.add(btnborrar);
        panelBotones.add(btnconsultar);
        panelBotones.add(btnactivar);
        panelBotones.add(btnsalir);

        Formularioprestamos.add(panelID_Prestamo);
        Formularioprestamos.add(panelID_Libro);
        Formularioprestamos.add(panelID_Lector);
        Formularioprestamos.add(panelfecInicioPrestamo);
        Formularioprestamos.add(panelFecDevolucion);
        Formularioprestamos.add(panelEstado);
        Formularioprestamos.add(panelBotones);

        panelID_Prestamo.setBackground(new Color(236, 223, 219));
        panelID_Libro.setBackground(new Color(236, 223, 219));
        panelID_Lector.setBackground(new Color(236, 223, 219));
        panelfecInicioPrestamo.setBackground(new Color(236, 223, 219));
        panelFecDevolucion.setBackground(new Color(236, 223, 219));
        panelEstado.setBackground(new Color(236, 223, 219));
        panelBotones.setBackground(new Color(236, 223, 219));

        btnnuevo.setBackground(new Color(245, 135, 118));
        btnguardar.setBackground(new Color(245, 135, 118));
        btnmodificar.setBackground(new Color(245, 135, 118));
        btnborrar.setBackground(new Color(245, 135, 118));
        btnconsultar.setBackground(new Color(245, 135, 118));
        btnactivar.setBackground(new Color(245, 135, 118));
        btnsalir.setBackground(new Color(245, 135, 118));

        Formularioprestamos.setVisible(true);
        Formularioprestamos.pack();
        Formularioprestamos.setLocationRelativeTo(null);

        //ESCUCHAS DE LAS CAJAS DE TEXTO
        txtID_Prestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idPrestamo = txtID_Prestamo.getText();

                if (idPrestamo.equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL PRÉSTAMO ES REQUERIDO", DesktopNotify.ERROR, 3000);
                } else if (!isNumeric(idPrestamo)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL PRÉSTAMO DEBE SER UN NÚMERO POSITIVO", DesktopNotify.ERROR, 3000);
                    txtID_Prestamo.setText(""); // Limpia el campo si el ID no es un número positivo
                } else if (idPrestamoExiste(idPrestamo)) { // Verificar si el ID del préstamo ya existe
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL PRÉSTAMO YA EXISTE", DesktopNotify.ERROR, 3000);
                    txtID_Prestamo.setText(""); // Limpia el campo si el ID ya existe
                } else {
                    txtID_Libro.requestFocusInWindow(); // Pasa el foco al siguiente campo si todo es correcto
                }
            }
        });

        txtID_Libro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtID_Libro.getText().equals("")) {

                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO ES REQUERIDO", DesktopNotify.ERROR, 3000);

                } else if (!isNumeric(txtID_Libro.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO DEBE SER UN NÚMERO POSITIVO", DesktopNotify.ERROR, 3000);
                    txtID_Libro.setText(""); // Limpia el campo si no es un número positivo
                } else {

                    consultarnombrelibro();

                }
            }
        });

        txtID_Lector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtID_Lector.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LECTOR ES REQUERIDO", DesktopNotify.ERROR, 3000);
                } else if (!isNumeric(txtID_Lector.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LECTOR DEBE SER UN NÚMERO POSITIVO", DesktopNotify.ERROR, 3000);
                    txtID_Lector.setText(""); // Limpia el campo si no es un número positivo
                } else {
                    consultarnombrelector();
                }
            }
        });

        txtfecInicioPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtfecInicioPrestamo.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DEL PRESTAMO ES REQUERIDA", 1, 3000);
                } else if (!isValidDate(txtfecInicioPrestamo.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DEL PRESTAMO NO ES VÁLIDA (FORMATO AÑO-MES-DÍA)", 1, 3000);
                    txtfecInicioPrestamo.setText(""); // Limpia el campo si la fecha no es válida
                } else {
                    txtFecDevolucion.requestFocusInWindow(); // Pasa el foco si todo es correcto
                }
            }
        });

        txtFecDevolucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtFecDevolucion.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DE DEVOLUCIÓN DEL PRESTAMO ES REQUERIDA", 1, 3000);
                } else if (!isValidDate(txtFecDevolucion.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA FECHA DE DEVOLUCIÓN NO ES VÁLIDA (FORMATO AÑO-MES-DÍA)", 1, 3000);
                    txtFecDevolucion.setText(""); // Limpia el campo si la fecha no es válida
                } else {
                    EstadocomboBox.requestFocusInWindow(); // Pasa el foco si todo es correcto
                }
            }
        });

        btnactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setCamposEditables(true);
            }
        });

        //CAJAS DE ESCUCHA DE LOS BOTONES 
        btnnuevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nuevo();
                setCamposEditables(true);
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
                setCamposEditables(false);
            }
        });

        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        btnactivar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnactivar.setBackground(new Color(253, 253, 150));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnactivar.setBackground(new Color(245, 135, 118));
            }
        });

        btnnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnuevo.setBackground(new Color(198, 225, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnuevo.setBackground(new Color(245, 135, 118));
            }
        });

        btnguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnguardar.setBackground(new Color(198, 225, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnguardar.setBackground(new Color(245, 135, 118));
            }
        });

        btnmodificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodificar.setBackground(new Color(198, 225, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodificar.setBackground(new Color(245, 135, 118));
            }
        });

        btnborrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnborrar.setBackground(new Color(198, 225, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnborrar.setBackground(new Color(245, 135, 118));
            }
        });

        btnconsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnconsultar.setBackground(new Color(198, 225, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnconsultar.setBackground(new Color(245, 135, 118));
            }
        });

        btnsalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsalir.setBackground(new Color(198, 225, 241));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsalir.setBackground(new Color(245, 135, 118));
            }
        });

    }

    public void nuevo() {
        String vacio = "";

        txtID_Prestamo.setText(vacio);
        txtID_Libro.setText(vacio);
        txtnombrelibro.setText(vacio);
        txtID_Lector.setText(vacio);
        txtnombrelector.setText(vacio);
       
        EstadocomboBox.setSelectedItem(null);
        txtID_Prestamo.requestFocusInWindow();

    }

    public void nuevoespecifico1() {
        String vacios = "";
        txtID_Libro.setText(vacios);
        txtnombrelibro.setText(vacios);

    }

    public void nuevoespecifico2() {
        String vacioss = "";

        txtID_Lector.setText(vacioss);
        txtnombrelector.setText(vacioss);

    }

    public void guardar() {
        // Verificar si todos los campos están llenos
        if (txtID_Prestamo.getText().isEmpty()
                || txtID_Libro.getText().isEmpty()
                || txtID_Lector.getText().isEmpty()
                || txtfecInicioPrestamo.getText().isEmpty()
                || txtFecDevolucion.getText().isEmpty()
                || EstadocomboBox.getSelectedItem() == null) {

            // Mostrar mensaje de error si algún campo está vacío
            DesktopNotify.showDesktopMessage(
                    "ERROR",
                    "POR FAVOR, COMPLETE TODOS LOS CAMPOS ANTES DE GUARDAR",
                    DesktopNotify.ERROR,
                    5000);
            return;
        }

        // Construir la consulta SQL
        sql = "insert into prestamos values (";
        sql += "\"" + txtID_Prestamo.getText() + "\" , ";
        sql += "\"" + txtID_Libro.getText() + "\" , ";
        sql += "\"" + txtID_Lector.getText() + "\" , ";
        sql += "\"" + txtfecInicioPrestamo.getText() + "\" , ";
        sql += "\"" + txtFecDevolucion.getText() + "\" , ";
        sql += "\"" + EstadocomboBox.getSelectedItem().toString() + "\" ) ";
        System.out.println(sql);

        // Intentar ejecutar la consulta SQL
        try {
            stmt = con.prepareStatement(sql);
            sw = stmt.executeUpdate();
            if (sw != 0) {
                DesktopNotify.showDesktopMessage(
                        "ÉXITO",
                        "REGISTRO DADO DE ALTA CON ÉXITO",
                        DesktopNotify.SUCCESS,
                        3000);
                nuevo();
            }
        } catch (SQLException e) {
            // Mostrar mensaje de error si falla la consulta SQL
            DesktopNotify.showDesktopMessage(
                    "EXITO",
                    "ERROR REGISTRO NO GUARDADO. VERIFIQUE QUE LA INFORMACIÓN SEA CORRECTA",
                    DesktopNotify.ERROR,
                    5000);
        }
    }

    public void consultar() {
        String idPrestamo = txtID_Prestamo.getText().trim(); // Obtén el texto y elimina espacios en blanco

        if (idPrestamo.isEmpty()) {
            DesktopNotify.showDesktopMessage("ERROR", "INGRESA EL ID DEL PRESTAMO PARA LA CONSULTA", 1, 3000);
            return; // Sale del método si no hay un ID para la consulta
        }
        sw = 0;
        sql = "select * from prestamos ";
        sql += "where id_prestamo='" + txtID_Prestamo.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtID_Libro.setText(tabla.getString(2));
                txtID_Lector.setText(tabla.getString(3));
                txtfecInicioPrestamo.setText(tabla.getString(4));
                txtFecDevolucion.setText(tabla.getString(5));
                EstadocomboBox.setSelectedItem(tabla.getString(6));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {

            nuevo();
        }
        sw = 0;
        sql = "select * from libros ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtnombrelibro.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        sw = 0;
        sql = "select * from lectores ";
        sql += "where id_lector='" + txtID_Lector.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtnombrelector.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "REGISTRO INEXISTENTE", 8, 3000);
            nuevo();
        }
    }

    public void consultarnombrelibro() {

        sw = 0;
        sql = "select * from libros ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swlib = 1;
                txtnombrelibro.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "LIBRO NO EXISTENTE", 8, 3000);
            txtID_Libro.requestFocusInWindow();
            nuevoespecifico1();
        }

    }

    public void consultarnombrelector() {
        sw = 0;
        sql = "select * from lectores ";
        sql += "where id_lector='" + txtID_Lector.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swlec = 1;
                txtnombrelector.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "LECTOR NO EXISTENTE", 8, 3000);
            txtID_Lector.requestFocusInWindow();
            nuevoespecifico2();

        }

    }

    public void modificar() {
        // Check if all fields are filled
        if (txtID_Libro.getText().isEmpty() || txtID_Lector.getText().isEmpty()
                || txtfecInicioPrestamo.getText().isEmpty() || txtFecDevolucion.getText().isEmpty() || EstadocomboBox.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("ERROR", "VERIFICA QUE TODOS LOS CAMPOS ESTÉN LLENOS Y LA INFORMACIÓN SEA CORRECTA", DesktopNotify.ERROR, 3000);
            return; // Exit the method if any field is empty
        }

        sql = "update prestamos set ";
        sql += "ID_Libro='" + txtID_Libro.getText() + "', ";
        sql += "ID_Lector='" + txtID_Lector.getText() + "', ";
        sql += "FecInicioPrestamo='" + txtfecInicioPrestamo.getText() + "', ";
        sql += "FecDevolucion='" + txtFecDevolucion.getText() + "', ";
        sql += "Estado='" + EstadocomboBox.getSelectedItem().toString() + "' ";
        sql += "where id_prestamo='" + txtID_Prestamo.getText() + "'";
        System.out.println(sql);

        try {
            stmt = con.prepareStatement(sql); // Prepare the statement
            sw = stmt.executeUpdate(); // Execute the update
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO MODIFICADO CON EXITO", 6, 3000);
                nuevo();
            }
        } catch (SQLException e) {
            DesktopNotify.showDesktopMessage("ERROR AL MODIFICAR", "POR FAVOR REVISER QUE LA INFORMACION SEA CORRECTA", DesktopNotify.ERROR, 3000);

        }
    }

    public void borrar() {
        sql = "delete from prestamos ";
        sql += " where id_prestamo='" + txtID_Prestamo.getText() + "'";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            sw = stmt.executeUpdate();//metodoupdtae
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("EXITO", "REGISTRO BORRADO", 7, 3000);
            }
            nuevo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void salir() {
        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Formularioprestamos.dispose();
                Menuprincipal mymenu = new Menuprincipal();
                mymenu.darformamenu();
            }
        });
    }

    private void setCamposEditables(boolean editable) {

        txtID_Prestamo.setEditable(editable);
        txtID_Libro.setEditable(editable);
        txtnombrelibro.setEditable(editable);
        txtID_Lector.setEditable(editable);
        txtnombrelector.setEditable(editable);

        EstadocomboBox.setEditable(editable);
        txtID_Prestamo.setEditable(editable);
        btnguardar.setEnabled(editable);
        btnborrar.setEnabled(editable);
        btnconsultar.setEnabled(editable);
        btnmodificar.setEnabled(editable);
        btnnuevo.setEnabled(editable);
    }

    public static void main(String[] args) {
        Prestamos myprestamo = new Prestamos();
        myprestamo.darforma();
    }
}
