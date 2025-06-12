/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author MODERN
 */

import model.Barang;
import util.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {

    public int hitungTotal(int harga, int jumlah) {
        return harga * jumlah;
    }

    public int hitungKembalian(int bayar, int total) {
        return bayar - total;
    }

    public List<Barang> getDaftarBarang() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Barang barang = new Barang(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                );
                list.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

