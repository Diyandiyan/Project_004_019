package util;

/**
 *
 * @author MODERN
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost/penjualan"; // pastikan nama database 'penjualan'
    private static final String USER = "root";
    private static final String PASSWORD = ""; // sesuaikan jika password Anda berbeda

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Pastikan driver sudah terdaftar. Untuk MySQL Connector/J 8.x ke atas,
            // Class.forName() tidak wajib jika driver ada di classpath.
            // Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database berhasil."); // Tambahkan ini untuk debugging
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }
        // Jangan catch Exception umum di sini jika hanya SQLException yang relevan
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        //     System.err.println("Driver MySQL tidak ditemukan: " + e.getMessage());
        // }
        return connection;
    }

    // // Opsional: Metode main untuk menguji koneksi secara langsung
    // public static void main(String[] args) {
    //     Connection conn = getConnection();
    //     if (conn != null) {
    //         System.out.println("Tes koneksi berhasil!");
    //         try {
    //             conn.close();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     } else {
    //         System.out.println("Tes koneksi gagal.");
    //     }
    // }
}