/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author MODERN
 */


import controller.BarangController;
import model.Barang;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TransaksiView extends JFrame {
    private JComboBox<String> comboBarang = new JComboBox<>();
    private JTextField hargaField = new JTextField();
    private JTextField jumlahField = new JTextField();
    private JTextField totalField = new JTextField();
    private JTextField bayarField = new JTextField();
    private JTextField kembalianField = new JTextField();
    private JButton hitungBtn = new JButton("Hitung Jumlah Barang");
    private JButton bayarBtn = new JButton("Bayar");
    private JButton editBtn = new JButton("Edit Data");

    private List<Barang> barangList;

    public TransaksiView() {
        setTitle("Aplikasi Kasir");
        setSize(400, 350);
        setLayout(null);

        BarangController barangController = new BarangController();
        barangList = barangController.getAllBarang();

        if (barangList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data barang kosong!");
            hitungBtn.setEnabled(false);
            bayarBtn.setEnabled(false);
        }

        for (Barang b : barangList) {
            comboBarang.addItem(b.getNama());
        }

        JLabel namaLabel = new JLabel("Nama Barang");
        JLabel hargaLabel = new JLabel("Harga Barang");
        JLabel jumlahLabel = new JLabel("Jumlah Beli");
        JLabel totalLabel = new JLabel("Jumlah Harga");
        JLabel bayarLabel = new JLabel("Jumlah Bayar");
        JLabel kembaliLabel = new JLabel("Jumlah Kembalian");

        namaLabel.setBounds(30, 20, 120, 25);
        comboBarang.setBounds(150, 20, 150, 25);
        hargaLabel.setBounds(30, 50, 120, 25);
        hargaField.setBounds(150, 50, 150, 25);
        jumlahLabel.setBounds(30, 80, 120, 25);
        jumlahField.setBounds(150, 80, 150, 25);
        totalLabel.setBounds(30, 110, 120, 25);
        totalField.setBounds(150, 110, 150, 25);
        bayarLabel.setBounds(30, 140, 120, 25);
        bayarField.setBounds(150, 140, 150, 25);
        kembaliLabel.setBounds(30, 170, 120, 25);
        kembalianField.setBounds(150, 170, 150, 25);
        hitungBtn.setBounds(150, 200, 150, 25);
        bayarBtn.setBounds(150, 230, 150, 25);
        editBtn.setBounds(150, 260, 150, 25);

        hargaField.setEditable(false);
        totalField.setEditable(false);
        kembalianField.setEditable(false);

        comboBarang.addActionListener(e -> {
            int index = comboBarang.getSelectedIndex();
            if (index >= 0 && index < barangList.size()) {
                Barang selected = barangList.get(index);
                hargaField.setText(String.valueOf(selected.getHarga()));
            }
        });

        hitungBtn.addActionListener(e -> {
            try {
                if (jumlahField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Jumlah beli harus diisi!");
                    return;
                }

                int harga = Integer.parseInt(hargaField.getText());
                int jumlah = Integer.parseInt(jumlahField.getText());
                int total = harga * jumlah;
                totalField.setText(String.valueOf(total));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Isi jumlah dengan angka!");
            }
        });

        bayarBtn.addActionListener(e -> {
            try {
                if (totalField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Hitung total harga terlebih dahulu!");
                    return;
                }

                if (bayarField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Jumlah bayar harus diisi!");
                    return;
                }

                int total = Integer.parseInt(totalField.getText());
                int bayar = Integer.parseInt(bayarField.getText());
                int kembali = bayar - total;

                if (kembali < 0) {
                    JOptionPane.showMessageDialog(this, "Uang tidak cukup untuk membayar!");
                    return;
                }

                kembalianField.setText(String.valueOf(kembali));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Isi jumlah bayar dengan angka!");
            }
        });

        editBtn.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin edit data barang?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                new BarangView();
            }
        });

        add(namaLabel); add(comboBarang);
        add(hargaLabel); add(hargaField);
        add(jumlahLabel); add(jumlahField);
        add(totalLabel); add(totalField);
        add(bayarLabel); add(bayarField);
        add(kembaliLabel); add(kembalianField);
        add(hitungBtn); add(bayarBtn); add(editBtn);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resetForm() {
        comboBarang.setSelectedIndex(0);
        hargaField.setText("");
        jumlahField.setText("");
        totalField.setText("");
        bayarField.setText("");
        kembalianField.setText("");
    }
}
