package edu.usta.infraestructure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * la classe DatabaseConnection implementa el patron de diseño creacional
 * llamado Sigleton, esto permite que solo se cree una instancia de conexiom
 * a la base de datos, la cual sera usada todo el tiempo mientras la aplicacion
 * este activa.
 * 
 * Esto reduce problemas de memoria o llamadas excesivas a la base
 * de datos en cada petición.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final String url, user, password;

    private DatabaseConnection() {
        var dotenv = Dotenv.load();
        url = dotenv.get("DB_URL");
        user = dotenv.get("DB_USER");
        password = dotenv.get("DB_PASSWORD");
    }

/**
 * SE retorna la instancia de conexiona la base de datos
 *  que esta funcionando en la aplicacion , en caso de que 
 * no haya una,la crea y la retorna.
 *  
 * @return una instancia Singleton de la calse DatabaseConnection.
 */
public static synchronized DatabaseConnection getInstance() {
    if (instance == null) {
        instance = new DatabaseConnection();
    }
    return instance;
}
    
    /**
     * Se obtiene la conexion a la base de datos mediante url,
     * usuario y password.
     * @return La conexion establecida con la base de datos.
     * @throws SQLException Algo ha fallado en la conexion.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
