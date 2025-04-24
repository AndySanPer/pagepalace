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

public class Libros {

    JFrame Formulariolibros;

    JLabel ID_Libro;
    JLabel Titulo;
    JLabel ID_Autor;
    JLabel Editorial;
    JLabel Genero;
    JLabel AñoPublicacion;
    JLabel NoEjemplares;
    JLabel Idioma;

    JComboBox<String> IdiomascomboBox = new JComboBox<>();

    JTextField txtID_Libro;
    JTextField txtTitulo;
    JTextField txtID_Autor;
    JTextField txtnombreautor;
    JTextField txtapellidoautor;
    JTextField txtEditorial;
    JTextField txtGenero;
    JTextField txtAñoPublicacion;
    JTextField txtNoEjemplares;

    JPanel panelID_Libro;
    JPanel panelTitulo;
    JPanel panelID_Autor;
    JPanel panelEditorial;
    JPanel panelGenero;
    JPanel panelAñoPublicacion;
    JPanel panelNoEjemplares;
    JPanel panelIdiomascomboBox;
    JPanel panelBotones;

    JButton btnnuevo, btnguardar, btnmodificar, btnborrar, btnconsultar, btnsalir, btnactivar;

    Connection con;
    PreparedStatement stmt;
    ResultSet tabla;
    int sw = 0, swnom = 0, swape = 0;
    String sql = "";
    conexion mycon = new conexion();

    Libros() {
        con = mycon.conecta();

        Formulariolibros = new JFrame("Libros");

        ID_Libro = new JLabel("ID_Libro");
        Titulo = new JLabel("Titulo");
        ID_Autor = new JLabel("ID_Autor");
        Editorial = new JLabel("Editorial");
        Genero = new JLabel("Genero");
        AñoPublicacion = new JLabel("Año de Publicación");
        NoEjemplares = new JLabel("No.Ejemplares");
        Idioma = new JLabel("Idioma");

        txtID_Libro = new JTextField(25);
        txtTitulo = new JTextField(25);
        txtID_Autor = new JTextField(8);
        txtnombreautor = new JTextField(8);
        txtapellidoautor = new JTextField(8);
        txtEditorial = new JTextField(25);
        txtGenero = new JTextField(25);
        txtAñoPublicacion = new JTextField(25);
        txtNoEjemplares = new JTextField(25);

        IdiomascomboBox.addItem(null);
        IdiomascomboBox.addItem("Español");
        IdiomascomboBox.addItem("IdiomaOriginal");
        IdiomascomboBox.addItem("Ambos");

        btnnuevo = new JButton("NUEVO");
        btnguardar = new JButton("GUARDAR");
        btnmodificar = new JButton("MODIFICAR");
        btnborrar = new JButton("BORRAR");
        btnconsultar = new JButton("CONSULTAR");
        btnsalir = new JButton("REGRESAR");
        btnactivar = new JButton("HABILITAR");

        panelID_Libro = new JPanel();
        panelTitulo = new JPanel();
        panelID_Autor = new JPanel();
        panelEditorial = new JPanel();
        panelGenero = new JPanel();
        panelAñoPublicacion = new JPanel();
        panelNoEjemplares = new JPanel();
        panelIdiomascomboBox = new JPanel();
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

    private boolean isTextOnly(String str) {
        // La expresión regular aquí permite letras y espacios en blanco, pero puedes ajustarla según tus necesidades
        String regex = "^[a-zA-Z\\s]+$";
        return str != null && str.matches(regex);
    }

    private boolean isTextAndSpecialCharsOnly(String str) {
        // La expresión regular permite solo texto y caracteres especiales, excluyendo números
        String regex = "^[a-zA-Z\\p{Punct}\\s]+$";
        return str != null && str.matches(regex);
    }

    private boolean idLibroExiste(String idLibro) {
        try {
            String query = "SELECT count(*) FROM libros WHERE id_libro = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, idLibro);
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
        Formulariolibros.setLayout(new GridLayout(9, 1));

        panelID_Libro.add(ID_Libro);
        panelID_Libro.add(txtID_Libro);

        panelTitulo.add(Titulo);
        panelTitulo.add(txtTitulo);

        panelID_Autor.add(ID_Autor);
        panelID_Autor.add(txtID_Autor);
        panelID_Autor.add(txtnombreautor);
        panelID_Autor.add(txtapellidoautor);

        panelEditorial.add(Editorial);
        panelEditorial.add(txtEditorial);

        panelGenero.add(Genero);
        panelGenero.add(txtGenero);

        panelAñoPublicacion.add(AñoPublicacion);
        panelAñoPublicacion.add(txtAñoPublicacion);

        panelNoEjemplares.add(NoEjemplares);
        panelNoEjemplares.add(txtNoEjemplares);

        panelIdiomascomboBox.add(Idioma);
        panelIdiomascomboBox.add(IdiomascomboBox);

        panelBotones.add(btnnuevo);
        panelBotones.add(btnguardar);
        panelBotones.add(btnmodificar);
        panelBotones.add(btnborrar);
        panelBotones.add(btnconsultar);
        panelBotones.add(btnactivar);
        panelBotones.add(btnsalir);
        

        Formulariolibros.add(panelID_Libro);
        Formulariolibros.add(panelTitulo);
        Formulariolibros.add(panelID_Autor);
        Formulariolibros.add(panelEditorial);
        Formulariolibros.add(panelGenero);
        Formulariolibros.add(panelAñoPublicacion);
        Formulariolibros.add(panelNoEjemplares);
        Formulariolibros.add(panelIdiomascomboBox);
        Formulariolibros.add(panelBotones);

        // Add these lines in your Libros class constructor or after creating the panels
        panelID_Libro.setBackground(new Color(236, 223, 219));
        panelTitulo.setBackground(new Color(236, 223, 219));
        panelID_Autor.setBackground(new Color(236, 223, 219));
        panelEditorial.setBackground(new Color(236, 223, 219));
        panelGenero.setBackground(new Color(236, 223, 219));
        panelAñoPublicacion.setBackground(new Color(236, 223, 219));
        panelNoEjemplares.setBackground(new Color(236, 223, 219));
        panelIdiomascomboBox.setBackground(new Color(236, 223, 219));
        panelBotones.setBackground(new Color(236, 223, 219));

        btnnuevo.setBackground(new Color(245, 135, 118));
        btnguardar.setBackground(new Color(245, 135, 118));
        btnmodificar.setBackground(new Color(245, 135, 118));
        btnborrar.setBackground(new Color(245, 135, 118));
        btnconsultar.setBackground(new Color(245, 135, 118));
        btnsalir.setBackground(new Color(245, 135, 118));
        btnactivar.setBackground(new Color(245, 135, 118));

        Formulariolibros.pack();
        Formulariolibros.setLocationRelativeTo(null);
        Formulariolibros.setVisible(true);

        //ESCUCHAS DE LAS CAJAS DE TEXTO
        txtID_Libro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idLibro = txtID_Libro.getText();

                if (idLibro.isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO ES REQUERIDO", DesktopNotify.ERROR, 3000);
                } else if (!isNumeric(idLibro)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO DEBE SER UN NÚMERO POSITIVO", DesktopNotify.ERROR, 3000);
                    txtID_Libro.setText("");
                } else if (idLibroExiste(idLibro)) { // Verificar si el ID del libro ya existe
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL LIBRO YA EXISTE", DesktopNotify.ERROR, 3000);
                    txtID_Libro.setText(""); // Limpia el campo si el ID ya existe
                } else {
                    txtTitulo.requestFocusInWindow(); // Pasa el foco al siguiente campo si todo es correcto
                }
            }
        });

        txtTitulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtTitulo.getText().isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL TITULO DEL LIBRO ES REQUERIDO", DesktopNotify.ERROR, 3000);
                } else if (!isTextOnly(txtTitulo.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL TÍTULO DEL LIBRO NO DEBE CONTENER NÚMEROS NI CARACTERES ESPECIALES", DesktopNotify.ERROR, 3000);
                    txtTitulo.setText("");
                } else {
                    txtID_Autor.requestFocusInWindow();
                }
            }
        });

        txtID_Autor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtID_Autor.getText().isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL AUTOR ES REQUERIDO", DesktopNotify.ERROR, 3000);

                } else if (!isNumeric(txtID_Autor.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL AUTOR DEBE SER UN NÚMERO POSITIVO", DesktopNotify.ERROR, 3000);
                    txtID_Autor.setText("");
                } else {
                    // Se asume que consultarnombre() y consultarapellido() realizan las validaciones necesarias
                    txtEditorial.requestFocusInWindow();
                    consultarnombre();
                    consultarapellido();

                }
            }
        });

// Y así sucesivamente para el resto de los campos...
        txtEditorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtEditorial.getText().isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA EDITORIAL ES REQUERIDA", DesktopNotify.ERROR, 3000);
                } else if (!isTextAndSpecialCharsOnly(txtEditorial.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "LA EDITORIAL NO DEBE CONTENER NÚMEROS", DesktopNotify.ERROR, 3000);
                    txtEditorial.setText("");
                } else {
                    txtGenero.requestFocusInWindow();
                }
            }
        });

        txtGenero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtGenero.getText().isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL GÉNERO DEL LIBRO ES REQUERIDO", DesktopNotify.ERROR, 3000);
                } else if (!isTextOnly(txtGenero.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL GÉNERO NO DEBE CONTENER NÚMEROS NI CARACTERES ESPECIALES", DesktopNotify.ERROR, 3000);
                    txtGenero.setText("");
                } else {
                    txtAñoPublicacion.requestFocusInWindow();
                }
            }
        });

        txtAñoPublicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String añoPublicacionText = txtAñoPublicacion.getText();
                if (añoPublicacionText.isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL AÑO DE PUBLICACIÓN ES REQUERIDO", DesktopNotify.ERROR, 3000);
                    txtAñoPublicacion.setText("");
                } else if (!isNumeric(añoPublicacionText) || Integer.parseInt(añoPublicacionText) <= 1000) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL AÑO DE PUBLICACIÓN DEBE SER UN NÚMERO ENTERO MAYOR A 1000", DesktopNotify.ERROR, 3000);
                    txtAñoPublicacion.setText("");
                } else {
                    txtNoEjemplares.requestFocusInWindow();
                }
            }
        });

        txtNoEjemplares.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtNoEjemplares.getText().isEmpty()) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL NÚMERO DE EJEMPLARES ES REQUERIDO", DesktopNotify.ERROR, 3000);
                } else if (!isNumeric(txtNoEjemplares.getText())) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL NÚMERO DE EJEMPLARES DEBE SER UN NÚMERO", DesktopNotify.ERROR, 3000);
                    txtNoEjemplares.setText("");
                } else {
                    // Aquí no hay un siguiente campo JTextField, por lo que no usamos requestFocusInWindow.
                }
            }
        });
        
        btnactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
              setCamposEditables(true);
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

        //CAJAS DE ESCUCHA DE LOS BOTONES 
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
                setCamposEditables(false);
            }
        });

        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                salir();
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

        txtID_Libro.setText(vacio);
        txtTitulo.setText(vacio);
        txtID_Autor.setText(vacio);
        txtnombreautor.setText(vacio);
        txtapellidoautor.setText(vacio);
        txtEditorial.setText(vacio);
        txtGenero.setText(vacio);
        txtAñoPublicacion.setText(vacio);
        txtNoEjemplares.setText(vacio);
        IdiomascomboBox.setSelectedItem(null);
        txtID_Libro.requestFocusInWindow();

    }

    public void nuevoespecifico1() {
        String vacios = "";
        txtID_Autor.setText(vacios);
        txtnombreautor.setText(vacios);
        txtapellidoautor.setText(vacios);

    }

    public void guardar() {
        // Validar si todas las casillas están llenas
        if (!validarCampos()) {
            return; // Salir si la validación falla
        }

        // Resto del código para insertar en la base de datos
        sql = "insert into libros values (";
        sql += "\"" + txtID_Libro.getText() + "\" , ";
        sql += "\"" + txtTitulo.getText() + "\" , ";
        sql += "\"" + txtID_Autor.getText() + "\" , ";
        sql += "\"" + txtEditorial.getText() + "\" , ";
        sql += "\"" + txtGenero.getText() + "\" , ";
        sql += "\"" + txtAñoPublicacion.getText() + "\" , ";
        sql += "\"" + txtNoEjemplares.getText() + "\" , ";
        sql += "\"" + IdiomascomboBox.getSelectedItem().toString() + "\" ) ";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);
            sw = stmt.executeUpdate();
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO DADO DEL ALTA CON EXITO", 7, 3000);
                nuevo();
            }
        } catch (SQLException e) {
            // Mostrar mensaje específico si hay un error en la base de datos
            DesktopNotify.showDesktopMessage("ERROR", "VERIFICA QUE TODOS LOS CAMPOS ESTÉN LLENOS Y LA INFORMACIÓN SEA CORRECTA", DesktopNotify.ERROR, 3000);

        }
    }

    private boolean validarCampos() {
        // Validar que todas las casillas estén llenas
        if (txtID_Libro.getText().isEmpty() || txtTitulo.getText().isEmpty()
                || txtID_Autor.getText().isEmpty() || txtEditorial.getText().isEmpty()
                || txtGenero.getText().isEmpty() || txtAñoPublicacion.getText().isEmpty()
                || txtNoEjemplares.getText().isEmpty() || IdiomascomboBox.getSelectedItem() == null) {
            // Mostrar mensaje de error si algún campo está vacío
            DesktopNotify.showDesktopMessage("ERROR", "TODOS LOS CAMPOS DEBEN ESTAR LLENOS", DesktopNotify.ERROR, 3000);
            return false; // La validación falla
        }

        return true; // Todos los campos están llenos
    }

    public void consultar() {

        String idLibro = txtID_Libro.getText().trim(); // Obtén el texto y elimina espacios en blanco

        if (idLibro.isEmpty()) {
            DesktopNotify.showDesktopMessage("ERROR", "INGRESA EL ID DEL LIBRO PARA LA CONSULTA", 1, 3000);
            return; // Sale del método si no hay un ID para la consulta
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
                txtTitulo.setText(tabla.getString(2));
                txtID_Autor.setText(tabla.getString(3));
                txtEditorial.setText(tabla.getString(4));
                txtGenero.setText(tabla.getString(5));
                txtAñoPublicacion.setText(tabla.getString(6));
                txtNoEjemplares.setText(tabla.getString(7));
                IdiomascomboBox.setSelectedItem(tabla.getString(8));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtnombreautor.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                txtapellidoautor.setText(tabla.getString(3));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "REGISTRO INEXISTENTE", 8, 3000);
        }

    }

    public void consultarnombre() {

        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swnom = 1;
                txtnombreautor.setText(tabla.getString(2));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (sw == 0) {
            DesktopNotify.showDesktopMessage("LO SENTIMOS", "AUTOR INEXISTENTE", 8, 3000);
            txtID_Autor.requestFocusInWindow();
            nuevoespecifico1();
        }

    }

    public void consultarapellido() {
        sw = 0;
        sql = "select * from autores ";
        sql += "where id_autor='" + txtID_Autor.getText() + "'";

        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);//prepara la instruccion
            tabla = stmt.executeQuery();//metodoupdtae
            while (tabla.next()) {
                sw = 1;
                swape = 1;
                txtapellidoautor.setText(tabla.getString(3));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void modificar() {
        // Check if all fields are filled
        if (txtTitulo.getText().isEmpty() || txtID_Autor.getText().isEmpty()
                || txtEditorial.getText().isEmpty() || txtGenero.getText().isEmpty()
                || txtAñoPublicacion.getText().isEmpty() || txtNoEjemplares.getText().isEmpty() || IdiomascomboBox.getSelectedItem() == null) {
            DesktopNotify.showDesktopMessage("ERROR", "VERIFICA QUE TODOS LOS CAMPOS ESTÉN LLENOS Y LA INFORMACIÓN SEA CORRECTA", DesktopNotify.ERROR, 3000);
            return; // Exit the method if any field is empty
        }

        sql = "update libros set ";
        sql += "Titulo='" + txtTitulo.getText() + "', ";
        sql += "ID_Autor='" + txtID_Autor.getText() + "', ";
        sql += "Editorial='" + txtEditorial.getText() + "', ";
        sql += "Genero='" + txtGenero.getText() + "', ";
        sql += "AñoPublicacion='" + txtAñoPublicacion.getText() + "', ";
        sql += "NoEjemplareson='" + txtNoEjemplares.getText() + "', ";
        sql += "Idioma='" + IdiomascomboBox.getSelectedItem().toString() + "' ";
        sql += "where id_libro='" + txtID_Libro.getText() + "'";
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
        sql = "delete from libros where id_libro='" + txtID_Libro.getText() + "'";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql); // prepara la instrucción
            sw = stmt.executeUpdate(); // método update
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ESO", "REGISTRO BORRADO CON EXITO", 7, 3000);
            }
            nuevo();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) { // El código de error 1451 corresponde a una violación de clave foránea en MySQL
                DesktopNotify.showDesktopMessage(null, "ERRORr: NO PUEDE BORRAR LIBRO PRESTADOS", 8, 3000);
            } else {
                DesktopNotify.showDesktopMessage(null, e.getMessage()); // Otros errores de SQL
            }
        }
    }

    public void salir() {
        btnsalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Formulariolibros.dispose();
                Menuprincipal mymenu = new Menuprincipal();
                mymenu.darformamenu();
            }
        });
    }

    private void setCamposEditables(boolean editable) {

        txtID_Libro.setEditable(editable);
        txtTitulo.setEditable(editable);
        txtID_Autor.setEditable(editable);
        txtnombreautor.setEditable(editable);
        txtapellidoautor.setEditable(editable);
        txtEditorial.setEditable(editable);
        txtGenero.setEditable(editable);
        txtAñoPublicacion.setEditable(editable);
        txtNoEjemplares.setEditable(editable);
        btnguardar.setEnabled(editable);
        btnborrar.setEnabled(editable);
        btnconsultar.setEnabled(editable);
        btnmodificar.setEnabled(editable);
        btnnuevo.setEnabled(editable);
        

        // Otros campos...
        // También podrías deshabilitar o habilitar el combo box
        IdiomascomboBox.setEnabled(editable);
    }

    public static void main(String[] args) {
        Libros mylibro = new Libros();
        mylibro.darforma();

    }
}
