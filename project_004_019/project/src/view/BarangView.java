package view;

import controller.BarangController;
import model.Barang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BarangView extends JFrame {
    private JTextField txtId, txtNama, txtHarga, txtStok;
    private JButton btnTambah, btnHapus, btnUpdate, btnClear;
    private JButton btnKembaliMenuTransaksi; 
    private JTable table;
    private DefaultTableModel tableModel;
    private BarangController controller;

    public BarangView() {
        controller = new BarangController();

        setTitle("Edit Data Barang Toko");
        setSize(650, 490); 
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID Barang");
        lblId.setBounds(30, 20, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150, 20, 200, 25);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblNama = new JLabel("Nama Barang");
        lblNama.setBounds(30, 60, 100, 25);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150, 60, 200, 25);
        add(txtNama);

        JLabel lblHarga = new JLabel("Harga Barang");
        lblHarga.setBounds(30, 100, 100, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(150, 100, 200, 25);
        add(txtHarga);

        JLabel lblStok = new JLabel("Stok");
        lblStok.setBounds(30, 140, 100, 25);
        add(lblStok);

        txtStok = new JTextField();
        txtStok.setBounds(150, 140, 200, 25);
        add(txtStok);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(370, 20, 130, 30); 
        add(btnTambah);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(370, 60, 130, 30); 
        add(btnUpdate);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(370, 100, 130, 30); 
        add(btnHapus);
        
        btnClear = new JButton("Clear Form");
        btnClear.setBounds(370, 140, 130, 30); 
        add(btnClear);

        
        btnKembaliMenuTransaksi = new JButton("Menu Transaksi");
        btnKembaliMenuTransaksi.setBounds(370, 180, 130, 30); 
        add(btnKembaliMenuTransaksi);

        
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Harga", "Stok"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBounds(30, 220, 580, 220); 
        add(scrollPane);

        loadTableData();

        btnTambah.addActionListener(e -> {
            try {
                String nama = txtNama.getText().trim();
                String hargaStr = txtHarga.getText().trim();
                String stokStr = txtStok.getText().trim();

                if (nama.isEmpty() || hargaStr.isEmpty() || stokStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nama, Harga, dan Stok tidak boleh kosong!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int harga = Integer.parseInt(hargaStr);
                int stok = Integer.parseInt(stokStr);

                if (harga < 0 || stok < 0) {
                     JOptionPane.showMessageDialog(this, "Harga dan Stok tidak boleh negatif!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controller.tambahBarang(nama, harga, stok);
                clearForm();
                loadTableData();
                JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Pilih barang yang akan diupdate dari tabel.", "Update Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(txtId.getText());
                String nama = txtNama.getText().trim();
                String hargaStr = txtHarga.getText().trim();
                String stokStr = txtStok.getText().trim();

                if (nama.isEmpty() || hargaStr.isEmpty() || stokStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nama, Harga, dan Stok tidak boleh kosong!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int harga = Integer.parseInt(hargaStr);
                int stok = Integer.parseInt(stokStr);
                
                if (harga < 0 || stok < 0) {
                     JOptionPane.showMessageDialog(this, "Harga dan Stok tidak boleh negatif!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controller.updateBarang(id, nama, harga, stok);
                clearForm();
                loadTableData();
                JOptionPane.showMessageDialog(this, "Barang berhasil diupdate!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID, Harga, dan Stok harus berupa angka valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnHapus.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Pilih barang yang akan dihapus dari tabel.", "Hapus Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(txtId.getText());
                
                int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus barang ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.hapusBarang(id);
                    clearForm();
                    loadTableData();
                    JOptionPane.showMessageDialog(this, "Barang berhasil dihapus!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID barang tidak valid.", "Hapus Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnClear.addActionListener(e -> {
            clearForm();
        });

        btnKembaliMenuTransaksi.addActionListener(e -> {
            new TransaksiView().setVisible(true);
            BarangView.this.dispose(); 
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) { 
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtNama.setText(tableModel.getValueAt(row, 1).toString());
                    txtHarga.setText(tableModel.getValueAt(row, 2).toString());
                    txtStok.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        setVisible(true);
    }

    private void loadTableData() {
        tableModel.setRowCount(0); 
        List<Barang> list = controller.getAllBarang();
        if (list.isEmpty()){
            System.out.println("Tidak ada data barang untuk ditampilkan."); 
        }
        for (Barang b : list) {
            tableModel.addRow(new Object[]{
                    b.getId(), b.getNama(), b.getHarga(), b.getStok()
            });
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtNama.requestFocus(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BarangView();
            }
        });
    }
}
