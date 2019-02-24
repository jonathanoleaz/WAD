package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url;
    private static String user;
    private static String password;
    
    public static synchronized Connection getConexion() {//metodo para obtener una conexión a la BD
        Connection cn = null;
        //LookForProps();//se buscan atributos de conexion en el archivo
        url = "jdbc:mysql://localhost:3306/instituto";
                user = "root";
                password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");//se crea referencia al manejador para la conexion a la BD
            cn = DriverManager.getConnection(url, user, password); //se crea la conexión dados los atributos leidos
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        } finally {
            return cn; //se devuelve la conexion
        }
    }
    
}
