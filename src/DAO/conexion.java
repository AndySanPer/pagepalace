package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexion {

    static Connection con;
    static String cadena, driver;

    public Connection conecta() {
        //public static void main(String[] args) {
        cadena = "jdbc:mysql://bqjxuzognepf73o4sfqd-mysql.services.clever-cloud.com:3306/bqjxuzognepf73o4sfqd";//"mysql://bw9y83pet8vjwmulqsjw-mysql.services.clever-cloud.com:3306/bw9y83pet8vjwmulqsjw";
        driver = "com.mysql.jdbc.Driver";
        String username = "u6iuuuace9pmwxaw";//"ulmerfulzswjrhr2";
        String password = "TZFQtXvX6sohyEWi6qyX";//"cUVwngk21QDamECGO8OF";

        try {
            Class.forName(driver);
            System.out.println(cadena);
            con = DriverManager.getConnection(cadena, username, password);
            System.out.println("CONEXION EXITOSA");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        conexion myconexion = new conexion();
        myconexion.conecta();
    }
}