package util;

/**
 *
 * @author MODERN
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost/penjualan"; 
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection connection = null;
        try { 
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database berhasil."); 
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }
      
        return connection;
    }

}
