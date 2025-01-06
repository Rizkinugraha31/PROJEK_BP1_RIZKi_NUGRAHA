package Projek_rizki_nugraha;

import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JSpinner;
import java.io.File;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import net.sf.jasperreports.view.JasperViewer;

public class Konversi extends javax.swing.JFrame {
    
    koneksi1 koneksi;
    Statement st;
    ResultSet rs;
    private JSpinner startTimeSpinner;
    private JSpinner endTimeSpinner;
    JasperReport jr;
    JasperPrint jp;
    Map p = new HashMap();
    JasperDesign js;
    
    public Konversi() {
        koneksi = new koneksi1();
        initComponents();
    }
    
    public void rubahUang(){
        try {
                Connection con = koneksi.con;
                
                double jumlah = Double.parseDouble(inputJumlah.getText());
                String asal = comboAsal.getSelectedItem().toString();
                String tujuan = comboTujuan.getSelectedItem().toString();
                double hasil = 0;
                
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda ingin mengkonversi mata uang " + asal + " ke mata uang "+ tujuan,
                    "Konfirmasi Konversi",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION){
                // Nilai tukar contoh
                    double usdToIdr = 15000.0;
                    double eurToIdr = 16500.0;
                    double usdToEur = 0.9;

                    if (asal.equals("USD") && tujuan.equals("IDR")) {
                            hasil = jumlah * usdToIdr;
                        } else if (asal.equals("EURO") && tujuan.equals("IDR")) {
                            hasil = jumlah * eurToIdr;
                        } else if (asal.equals("IDR") && tujuan.equals("USD")) {
                            hasil = jumlah / usdToIdr;
                        } else if (asal.equals("IDR") && tujuan.equals("EURO")) {
                            hasil = jumlah / eurToIdr;
                        } else if (asal.equals("USD") && tujuan.equals("EURO")) {
                            hasil = jumlah * usdToEur;
                        } else if (asal.equals("EUR") && tujuan.equals("USD")) {
                            hasil = jumlah / usdToEur;
                        } else {
                            hasil = jumlah; // Jika mata uang sama
                    }
                }
                String insertQuery = "INSERT INTO uang (jumlah, asal, tujuan, hasil) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
                    ps.setDouble(1, jumlah);
                    ps.setString(2, asal);
                    ps.setString(3, tujuan);
                    ps.setDouble(4, hasil);
                    ps.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan ke database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                JHasil.setText(hasil + " " + tujuan);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Masukkan jumlah yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    public void load() {
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM uang";
            rs = st.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new String[]{"jumlah", "asal", "tujuan", "hasil"}, 0);
            int totalHarga = 0;
            while (rs.next()) {
                double jumlah = rs.getDouble("jumlah");
                String asal = rs.getString("asal");
                String tujuan = rs.getString("tujuan");
                double hasil = rs.getDouble("hasil");

                // Menambahkan data ke dalam model tabel
                model.addRow(new Object[]{jumlah, asal, tujuan, hasil});

            }
            JTABLE.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data pesanan: " + e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputJumlah = new javax.swing.JTextField();
        comboAsal = new javax.swing.JComboBox<>();
        comboTujuan = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        JHasil = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("APLIKASI KONVERSI MATA UANG");

        jLabel2.setText("Jumlah Nilai Rupiah");

        jLabel3.setText("Dari nilai");

        jLabel4.setText("Ke nilai");

        inputJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputJumlahActionPerformed(evt);
            }
        });

        comboAsal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDR", "USD", "EURO" }));
        comboAsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAsalActionPerformed(evt);
            }
        });

        comboTujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDR", "USD", "EURO" }));

        jButton1.setText("Konversi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Hasil");

        JHasil.setEditable(false);
        JHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JHasilActionPerformed(evt);
            }
        });

        JTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTABLE);

        jLabel6.setText("HISTORY KONVERSI");

        jButton2.setText("REPORT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(comboAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(186, 186, 186)
                                    .addComponent(comboTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(197, 197, 197)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2))
                                    .addComponent(JHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(128, 128, 128)
                                .addComponent(inputJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(JHasil, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputJumlahActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        rubahUang();
        load();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboAsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAsalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAsalActionPerformed

    private void JHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JHasilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JHasilActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            File file = new File("C:\\Users\\rizki\\OneDrive\\Documents\\NetBeansProjects\\Projek_rizki_nugraha\\src\\iReport.jrxml");
            js = JRXmlLoader.load(file);
            p.clear();
            jr = JasperCompileManager.compileReport(js);
            jp = JasperFillManager.fillReport(jr, p, koneksi.con);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Konversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Konversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Konversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Konversi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Konversi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JHasil;
    private javax.swing.JTable JTABLE;
    private javax.swing.JComboBox<String> comboAsal;
    private javax.swing.JComboBox<String> comboTujuan;
    private javax.swing.JTextField inputJumlah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
