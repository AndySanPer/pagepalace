package Procesos;

import DAO.conexion;
import ds.desktop.notify.DesktopNotify;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registrarse {

    JFrame formularioregistrarse;

    JLabel idusuario;
    JLabel nombre;
    JLabel apellidopate;
    JLabel apellidomate;
    JLabel usuario;
    JLabel contraseña;
    JLabel confirmarcontraseña;
    JLabel rol;
    JLabel textoregist;

    JTextField txtidusuario;
    JTextField txtnombre;
    JTextField txtapellidopate;
    JTextField txtapellidomate;
    JTextField txtusuario;
    JTextField txtcontraseña;
    JTextField txtconfirmarcontraseña;

    JComboBox<String> rolcomboBox = new JComboBox<>();

    JButton btnregistrase;
    JButton btnregresar;

    JPanel paneltexto;
    JPanel panelidusuario;
    JPanel panelnombre;
    JPanel panelapellidopate;
    JPanel panelapellidomate;
    JPanel panelusuario;
    JPanel panelcontraseña;
    JPanel panelconfirmarcontraseña;
    JPanel panelrol;
    JPanel panelBotones;

    Connection con;
    PreparedStatement stmt;

    String sql = "";
    int sw = 0;
    conexion mycon = new conexion();

    Registrarse() {
        con = mycon.conecta();

        formularioregistrarse = new JFrame("Registrate");

        idusuario = new JLabel("ID_Usuario");
        nombre = new JLabel("Nombre");
        apellidopate = new JLabel("Apellido Paterno");
        apellidomate = new JLabel("Apellido Materno");
        rol = new JLabel("Rol que ejerces");
        usuario = new JLabel("Usuario");
        contraseña = new JLabel("Contraseña");
        confirmarcontraseña = new JLabel("Confirmar contraseña");
        textoregist = new JLabel("REGISTRARSE");

        rolcomboBox.addItem(null);
        rolcomboBox.addItem("administrador");
        rolcomboBox.addItem("bibliotecario");

        txtidusuario = new JTextField(25);
        txtnombre = new JTextField(25);
        txtapellidopate = new JTextField(20);
        txtapellidomate = new JTextField(20);
        txtusuario = new JTextField(25);
        txtcontraseña = new JTextField(23);
        txtconfirmarcontraseña = new JTextField(18);

        btnregistrase = new JButton("REGISTRAR");
        btnregresar = new JButton("REGRESAR");

        paneltexto = new JPanel();
        panelidusuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelnombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelapellidopate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelapellidomate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelusuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelcontraseña = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelconfirmarcontraseña = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelrol = new JPanel();
        panelBotones = new JPanel();
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
            // Verifica si el número es negativo
            return d >= 0;
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

    private boolean existeNombreUsuario(String nombreUsuario) {
        try {
            String query = "SELECT count(*) FROM usuarios WHERE nombreusuario = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    private boolean existeIDUsuario(String idUsuario) {
        try {
            String query = "SELECT count(*) FROM usuarios WHERE id_usuario = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    public void darforma() {
        formularioregistrarse.setLayout(new GridLayout(10, 1));
        paneltexto.add(textoregist);

        panelidusuario.add(idusuario);
        panelidusuario.add(txtidusuario);

        panelnombre.add(nombre);
        panelnombre.add(txtnombre);

        panelapellidopate.add(apellidopate);
        panelapellidopate.add(txtapellidopate);

        panelapellidomate.add(apellidomate);
        panelapellidomate.add(txtapellidomate);

        panelusuario.add(usuario);
        panelusuario.add(txtusuario);

        panelrol.add(rol);
        panelrol.add(rolcomboBox);

        panelcontraseña.add(contraseña);
        panelcontraseña.add(txtcontraseña);

        panelconfirmarcontraseña.add(confirmarcontraseña);
        panelconfirmarcontraseña.add(txtconfirmarcontraseña);

        panelBotones.add(btnregistrase);
        panelBotones.add(btnregresar);

        formularioregistrarse.add(paneltexto);
        formularioregistrarse.add(panelidusuario);
        formularioregistrarse.add(panelnombre);
        formularioregistrarse.add(panelapellidopate);
        formularioregistrarse.add(panelapellidomate);
        formularioregistrarse.add(panelusuario);
        formularioregistrarse.add(panelrol);
        formularioregistrarse.add(panelcontraseña);
        formularioregistrarse.add(panelconfirmarcontraseña);
        formularioregistrarse.add(panelBotones);
        formularioregistrarse.setVisible(true);
        formularioregistrarse.pack();
        formularioregistrarse.setLocationRelativeTo(null);

        txtidusuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idUsuarioText = txtidusuario.getText();
                if (idUsuarioText.equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL USUARIO ES OBLIGATORIO", DesktopNotify.ERROR, 3000);
                } else if (!isNumeric(idUsuarioText) || Integer.parseInt(idUsuarioText) < 0) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL USUARIO DEBE SER UN NÚMERO NO NEGATIVO", DesktopNotify.ERROR, 3000);
                } else if (existeIDUsuario(idUsuarioText)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL ID DEL USUARIO YA EXISTE, POR FAVOR INGRESE UN ID ÚNICO", DesktopNotify.ERROR, 3000);
                    txtidusuario.setText(""); // Limpia el campo si el ID ya existe
                } else {
                    txtnombre.requestFocusInWindow();
                }
            }
        });

        txtnombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreText = txtnombre.getText();
                if (nombreText.equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL NOMBRE DEL USUARIO ES OBLIGATORIO", DesktopNotify.ERROR, 3000);
                } else if (!isTextOnly(nombreText)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL NOMBRE DEL USUARIO DEBE CONTENER SOLO TEXTO Y ESPACIOS", DesktopNotify.ERROR, 3000);
                } else {
                    txtapellidopate.requestFocusInWindow();
                }
            }
        });

        txtapellidopate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String apellidoPaternoText = txtapellidopate.getText();
                if (apellidoPaternoText.equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL APELLIDO PATERNO DEL USUARIO ES OBLIGATORIO", DesktopNotify.ERROR, 3000);
                } else if (!isTextOnly(apellidoPaternoText)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL APELLIDO PATERNO DEL USUARIO DEBE CONTENER SOLO TEXTO Y ESPACIOS", DesktopNotify.ERROR, 3000);
                } else {
                    txtapellidomate.requestFocusInWindow();
                }
            }
        });

        txtapellidomate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String apellidoMaternoText = txtapellidomate.getText();
                if (apellidoMaternoText.equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL APELLIDO MATERNO DEL USUARIO ES OBLIGATORIO", DesktopNotify.ERROR, 3000);
                } else if (!isTextOnly(apellidoMaternoText)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL APELLIDO MATERNO DEL USUARIO DEBE CONTENER SOLO TEXTO Y ESPACIOS", DesktopNotify.ERROR, 3000);
                } else {
                    txtusuario.requestFocusInWindow();
                }
            }
        });

        txtusuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuarioText = txtusuario.getText();
                if (usuarioText.equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "USUARIO OBLIGATORIO", DesktopNotify.ERROR, 3000);
                } else if (!isTextAndSpecialCharsOnly(usuarioText)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL USUARIO DEBE CONTENER SOLO TEXTO Y CARACTERES ESPECIALES, SIN NÚMEROS", DesktopNotify.ERROR, 3000);
                } else if (existeNombreUsuario(usuarioText)) {
                    DesktopNotify.showDesktopMessage("ERROR", "EL NOMBRE DE USUARIO YA ESTÁ EN USO, POR FAVOR ELIGE OTRO", DesktopNotify.ERROR, 3000);
                } else {
                    rolcomboBox.requestFocusInWindow();
                }
            }
        });

        txtcontraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtcontraseña.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "CONTRASEÑA OBLIGATORIA ES OBLIGATORIO", 1, 3000);
                } else {
                    txtconfirmarcontraseña.requestFocusInWindow();

                }
            }
        });

        txtconfirmarcontraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtconfirmarcontraseña.getText().equals("")) {
                    DesktopNotify.showDesktopMessage("ERROR", "CONFIRMAR CONTRASEÑA ES OBLIGATORIO", 1, 3000);
                } else {
                    btnregistrase.requestFocusInWindow();

                }
            }
        });

        btnregresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formularioregistrarse.dispose();
                IngresarSistema myingresis = new IngresarSistema();
                myingresis.darforma();
            }
        });

        btnregistrase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
    }

    public void nuevo() {
        String vacio = " ";
        txtidusuario.setText(vacio);

    }

    public void guardar() {
        // Verificar si todos los campos están llenos
        if (txtidusuario.getText().isEmpty()
                || txtnombre.getText().isEmpty()
                || txtapellidopate.getText().isEmpty()
                || txtapellidomate.getText().isEmpty()
                || txtusuario.getText().isEmpty()
                || txtcontraseña.getText().isEmpty()
                || txtconfirmarcontraseña.getText().isEmpty()
                || rolcomboBox.getSelectedItem() == null) {

            DesktopNotify.showDesktopMessage("ERROR", "POR FAVOR, COMPLETA TODOS LOS CAMPOS ANTES DE ENVIALOS", DesktopNotify.ERROR, 3000);
            return; // Salir del método si algún campo está vacío
        }

        // Verificar si las contraseñas coinciden
        if (!txtcontraseña.getText().equals(txtconfirmarcontraseña.getText())) {
            DesktopNotify.showDesktopMessage("ERROR", "LA CONTRASEÑA NO COINCIDE VUELVE A INTENTAR", DesktopNotify.ERROR, 3000);
            return;
        }

        // Construir la consulta SQL
        sql = "insert into usuarios values (";
        sql += "\"" + txtidusuario.getText() + "\" , ";
        sql += "\"" + txtnombre.getText() + "\" , ";
        sql += "\"" + txtapellidopate.getText() + "\" , ";
        sql += "\"" + txtapellidomate.getText() + "\" , ";
        sql += "\"" + txtconfirmarcontraseña.getText() + "\" , ";
        sql += "\"" + txtusuario.getText() + "\" , ";
        sql += "\"" + rolcomboBox.getSelectedItem().toString() + "\" ) ";
        System.out.println(sql);

        // Intentar ejecutar la consulta SQL
        try {
            stmt = con.prepareStatement(sql);
            sw = stmt.executeUpdate();
            if (sw != 0) {
                DesktopNotify.showDesktopMessage("ÉXITO", "REGISTRO DADO DE ALTA CON EXITO", DesktopNotify.SUCCESS, 3000);
                limpiar();
                
                IngresarSistema obj = new IngresarSistema();
                obj.darforma();
                formularioregistrarse.dispose();
            }
        } catch (SQLException e) {
            // Mostrar mensaje de error genérico si falla la consulta SQL
            DesktopNotify.showDesktopMessage("ERROR", "ERROR AL REGISTRARSE POR FAVOR VERIFIQUE LA INFORMACION PROPORCIONADA", DesktopNotify.ERROR, 3000);
        }
    }

    public void limpiar() {
        String vacio = "";
        txtidusuario.setText(vacio);
        txtnombre.setText(vacio);
        txtapellidopate.setText(vacio);
        txtapellidomate.setText(vacio);
        txtusuario.setText(vacio);
        txtcontraseña.setText(vacio);
        txtconfirmarcontraseña.setText(vacio);
        rolcomboBox.setSelectedItem(null);
        txtidusuario.requestFocusInWindow();
    }

    public static void main(String[] args) {
        Registrarse myregistrase = new Registrarse();
        myregistrase.darforma();
    }
}
