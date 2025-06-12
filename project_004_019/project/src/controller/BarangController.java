package controller;

import model.Barang;
import util.Koneksi; 

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangController {

    public BarangController() {
        
    }

    public void tambahBarang(String nama, int harga, int stok) {
        
        String sql = "INSERT INTO barang (nama_barang, harga, stok) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            if (conn == null) {
                System.err.println("Koneksi ke database gagal saat tambah barang.");
                return;
            }

            pstmt.setString(1, nama); 
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.executeUpdate();
            System.out.println("Data barang berhasil ditambahkan.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saat menambahkan barang: " + e.getMessage());
        }
    }

    public void hapusBarang(int id) {
        
        String sql = "DELETE FROM barang WHERE id_barang = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.err.println("Koneksi ke database gagal saat hapus barang.");
                return;
            }

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data barang dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("Data barang dengan ID " + id + " tidak ditemukan.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saat menghapus barang: " + e.getMessage());
        }
    }

    public void updateBarang(int id, String nama, int harga, int stok) {
        // SQL diubah untuk menggunakan 'nama_barang' dan 'id_barang'
        String sql = "UPDATE barang SET nama_barang = ?, harga = ?, stok = ? WHERE id_barang = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.err.println("Koneksi ke database gagal saat update barang.");
                return;
            }

            pstmt.setString(1, nama); // Parameter pertama untuk nama_barang
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setInt(4, id);     // Parameter keempat untuk id_barang
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data barang dengan ID " + id + " berhasil diperbarui.");
            } else {
                System.out.println("Data barang dengan ID " + id + " tidak ditemukan untuk diperbarui.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saat memperbarui barang: " + e.getMessage());
        }
    }

    public List<Barang> getAllBarang() {
        List<Barang> daftarBarang = new ArrayList<>();
        // SQL diubah untuk mengambil 'id_barang' dan 'nama_barang'
        String sql = "SELECT id_barang, nama_barang, harga, stok FROM barang";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null) {
                System.err.println("Koneksi ke database gagal saat mengambil semua barang.");
                return daftarBarang; 
            }

            while (rs.next()) {
                // Saat membuat objek Barang, ambil dari kolom yang benar
                Barang barang = new Barang(
                        rs.getInt("id_barang"),    // Mengambil dari kolom id_barang
                        rs.getString("nama_barang"), // Mengambil dari kolom nama_barang
                        rs.getInt("harga"),
                        rs.getInt("stok")
                );
                daftarBarang.add(barang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saat mengambil semua barang: " + e.getMessage());
        }
        return daftarBarang;
    }
}   
