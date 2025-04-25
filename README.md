# PagePalace

## Descripción del Proyecto

PagePalace es un sistema de gestión de bibliotecas desarrollado para facilitar el control de entradas y salidas de libros, mantener un registro detallado de los usuarios (lectores) y proporcionar a los bibliotecarios una herramienta centralizada para la administración de diversas operaciones. Esto incluye el seguimiento de préstamos y devoluciones, la gestión del catálogo de libros (altas, bajas, edición de fichas), y la administración de autores.

## Propósito del Proyecto

El objetivo principal de PagePalace es resolver los desafíos que enfrentan las bibliotecas en la gestión manual de sus recursos y usuarios. El sistema busca optimizar los procesos de préstamo y devolución, mantener un inventario preciso de los libros, facilitar la gestión de la información de los lectores y proporcionar a los bibliotecarios un registro completo de todas las actividades realizadas dentro del sistema.

## Características Principales

* **Autenticación de Usuarios:**
    * Formulario de inicio de sesión para bibliotecarios.
    * Formulario de registro para nuevos bibliotecarios.
* **Panel de Control para Bibliotecarios:**
    * Interfaz centralizada con acceso a todas las funcionalidades del sistema a través de un menú intuitivo.
* **Gestión de Catálogos:**
    * **Libros:**
        * Alta de nuevos libros al sistema.
        * Edición de la información de las fichas de los libros existentes.
        * Baja (eliminación) de libros del catálogo.
    * **Autores:**
        * Agregar nuevos autores al sistema.
        * Eliminar autores del sistema.
* **Gestión de Lectores:**
    * Registro de nuevos lectores en el sistema.
    * Mantenimiento de la información de los lectores registrados.
* **Procesos de Préstamo y Devolución:**
    * Registro de préstamos de libros a los lectores.
    * Registro de la fecha de devolución esperada para cada préstamo.
    * Proceso para registrar la devolución de libros.
* **Generación de Reportes:**
    * Reporte de libros disponibles.
    * Reporte de autores registrados.
    * Reporte de lectores registrados.
    * Reporte de libros prestados.
* **Consultas e Informes:**
    * Consulta de préstamos activos.
    * Consulta de préstamos vencidos.
    * Consulta de información detallada de los lectores.
    * Consulta de libros por idioma.
    * Consulta de libros por género.
    * Consulta de la obra de un autor específico.
* **Ayuda y Soporte:**
    * Opción de ayuda integrada (posiblemente con documentación o accesos directos).
    * Sección "Acerca de..." con información sobre el sistema.

## Tecnologías Utilizadas

* **Lenguaje de Programación:** JAVA (POO - Programación Orientada a Objetos)
* **Librerías:**
    * AbsoluteLayout.jar
    * commons-collections-3.2.jar
    * commons-digester-2.1.jar
    * commons-logging-1.1.1-1.0.0.jar
    * commons-logging-1.2.jar
    * DS_Desktop_Notify.jar
    * groovy-all.jar
    * jasperreports-6.0.0.jar (Para la generación de reportes)
    * mysql-connector-java-5.1.47.jar (Para la conexión a la base de datos MySQL)
    * pdfbox-app-3.0.0-beta1.jar (Posiblemente para la generación de reportes en PDF)

## Instalación y Ejecución

Este sistema se distribuye como un proyecto de NetBeans. Para ejecutarlo, se requiere tener NetBeans IDE instalado. La base de datos MySQL utilizada por el sistema se encuentra alojada externamente en `console.clever-cloud.com`. La conexión a esta base de datos está configurada dentro de la clase `DAO.conexion` del proyecto, con los siguientes parámetros:

```java
package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexion {

    static Connection con;
    static String cadena, driver;

    public Connection conecta() {
        cadena = "jdbc:mysql://[bqjxuzognepf73o4sfqd-mysql.services.clever-cloud.com:3306/bqjxuzognepf73o4sfqd](https://bqjxuzognepf73o4sfqd-mysql.services.clever-cloud.com:3306/bqjxuzognepf73o4sfqd)";
        driver = "com.mysql.jdbc.Driver";
        String username = "u6iuuuace9pmwxaw";
        String password = "TZFQtXvX6sohyEWi6qyX";

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
