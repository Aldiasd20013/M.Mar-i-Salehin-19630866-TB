/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Glora
 */
public class mstr_penjualan {
    
    Connection _Cnn;
    public String nofaktur, nama, id_cust, totalbeli;
    public String _Akses = "";
    
    public void hapusdata(String kode) {
        System.out.println("pesan kode" + kode);
        this.nofaktur = kode;
        try {
            _Cnn = logic_koneksi.getConnection();
            String _sql = "DELETE FROM `pratikum2020`.`penjualan` WHERE `penjualan`.`nofaktur` ='" + nofaktur + "'";
            System.out.println(_sql);
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.executeUpdate();
            System.out.println(_sql);
            prs.close();
        } catch (SQLException ex) {
            Logger.getLogger(logic_koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simpandata() {
        try {
            String _sql = "";
            _Cnn = logic_koneksi.getConnection();
            _sql = "INSERT INTO `penjualann`(`nofaktur`, `nama`, `id_cust`, `totalbeli`) VALUES (?,?,?,?,?)";
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.setString(1, this.nofaktur);
            prs.setString(2, this.nama);
            prs.setString(3, this.id_cust);
            prs.setString(4, this.totalbeli);
            prs.executeUpdate();
            System.out.println(_sql);
            _Cnn = logic_koneksi.getConnection();
            prs.close();
        } catch (Exception err) {
            System.out.println("" + err);
        }   
    }
    
    public void Cari(String nofaktur) {
        String pilih_nofaktur = nofaktur;
        try {
            _Akses = "";
            _Cnn = logic_koneksi.getConnection();
            String SQL = "SELECT * "
                    + "FROM `penjualan` "
                    + "WHERE `nofaktur` = '" + pilih_nofaktur + "'";
            Statement st = _Cnn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                _Akses = "-";
                this.nofaktur = rs.getString(1);
                this.nama = rs.getString(2);
                this.id_cust = rs.getString(3);
                this.totalbeli = rs.getString(4);
                System.out.println(SQL);
                _Cnn = logic_koneksi.getConnection();
            }
            st.close();
            System.out.println("_" + st.toString());
        } catch (Exception err) {
            System.out.println("_ERROR" + err.toString());
        }
    }
    
    public void updatedata() {
        boolean result = false;
        String _sql = "";
        try {
            _Cnn = logic_koneksi.getConnection();
            Statement st = _Cnn.createStatement();
            _sql = "UPDATE `pratikum2020`.`penjualan` SET"
                    + "`nofaktur` = '" + String.valueOf(nofaktur) + "', "
                    + "`nama` = '" + String.valueOf(nama) + "',"
                    + "`id_cust` = '" + String.valueOf(id_cust) + "',"
                    + "`totalbeli` = '" + String.valueOf(totalbeli) + "'"
                    + " WHERE `penjualan`.`nofaktur` = '" + String.valueOf(nofaktur) + "'";
            int status = logic_koneksi.executestatement(_sql);
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            if (status == 1) {
                prs.setString(1, this.nofaktur);
                prs.setString(2, this.nama);
                prs.setString(3, this.id_cust);
                prs.setString(4, this.totalbeli);
                prs.executeUpdate();
                System.out.println(_sql);
                _Cnn = logic_koneksi.getConnection();
                prs.close();
            }
        } catch (Exception err) {
            System.out.println("ERROR " + err + " " + _Cnn + " " + _sql);
        }
    }
    
    public void simpanORupdate() {
        try {
            String _sql;
            _Cnn = logic_koneksi.getConnection();
            if (_Akses.equals("")) {
                _sql = "INSERT INTO `pratikum2020`.`penjualan` (`nofaktur`, `nama`, `id_cust`, `totalbeli`) VALUES ('?', '?', '?', '?', '?')";
                System.out.println(_sql);
            } else {
                String nofakktur = null;
                _sql = "UPDATE `pratikum2020`.`penjualan` SET `nofaktur` = '" + nofakktur + "', `nama` = '" + nama + "', `id_cust` = '" + id_cust + "', `totalbeli` = '" + totalbeli + "' WHERE `penjualan`.`nofaktur` = '" + nofaktur +"'";
                System.out.println(_sql);
            }
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.setString(1, this.nofaktur);
            prs.setString(2, this.nama);
            prs.setString(3, this.id_cust);
            prs.setString(4, this.totalbeli);
            prs.executeUpdate();
            System.out.println(_sql);
            prs.close();
        } catch (Exception err) {
            System.out.println("BB ERROR : " + err);
        }
    }
}
