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
public class mstr_produk {
    
    Connection _Cnn;
    public String prd_id, nama, alamat, umur, No_HP;
    public String _Akses = "";
    
    public void hapusdata(String kode) {
        System.out.println("pesan kode" + kode);
        this.prd_id = kode;
        try {
            _Cnn = logic_koneksi.getConnection();
            String _sql = "DELETE FROM `pratikum2020`.`mstr_produk` WHERE `mstr_produk`.`prd_id` ='" + prd_id + "'";
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
            _sql = "INSERT INTO `mstr_produk`(`prd_id`, `nama`, `alamat`, `umur`, `No_HP`) VALUES (?,?,?,?,?)";
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.setString(1, this.prd_id);
            prs.setString(2, this.nama);
            prs.setString(3, this.alamat);
            prs.setString(4, this.umur);
            prs.setString(5, this.No_HP);
            prs.executeUpdate();
            System.out.println(_sql);
            _Cnn = logic_koneksi.getConnection();
            prs.close();
        } catch (Exception err) {
            System.out.println("" + err);
        }   
    }
    
    public void Cari(String prd_id) {
        String pilih_prod_id = prd_id;
        try {
            _Akses = "";
            _Cnn = logic_koneksi.getConnection();
            String SQL = "SELECT * "
                    + "FROM `mstr_produk` "
                    + "WHERE `prd_id` = '" + pilih_prod_id + "'";
            Statement st = _Cnn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                _Akses = "-";
                this.prd_id = rs.getString(1);
                this.nama = rs.getString(2);
                this.alamat = rs.getString(3);
                this.umur = rs.getString(4);
                this.No_HP = rs.getString(5);
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
            _sql = "UPDATE `pratikum2020`.`mstr_produk` SET"
                    + "`nama` = '" + String.valueOf(nama) + "', "
                    + "`alamat` = '" + String.valueOf(alamat) + "',"
                    + "`umur` = '" + String.valueOf(umur) + "',"
                    + "`N0_HP` = '" + String.valueOf(No_HP) + "'"
                    + " WHERE `mstr_produk`.`prd_id` = '" + String.valueOf(prd_id) + "'";
            int status = logic_koneksi.executestatement(_sql);
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            if (status == 1) {
                prs.setString(1, this.prd_id);
                prs.setString(2, this.nama);
                prs.setString(3, this.alamat);
                prs.setString(4, this.umur);
                prs.setString(5, this.No_HP);
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
                _sql = "INSERT INTO `pratikum2020`.`mstr_produk` (`prd_id`, `nama`, `alamat`, `umur`, `No_HP`) VALUES ('?', '?', '?', '?', '?')";
                System.out.println(_sql);
            } else {
                _sql = "UPDATE `pratikum2020`.`mstr_produk` SET `prd_id` = '" + prd_id + "', `nama` = '" + nama + "', `alamat` = '" + alamat + "', `umur` = '" + umur + "', `No_HP` = '" + No_HP + "' WHERE `mstr_produk`.`prd_id` = '" + prd_id +"'";
                System.out.println(_sql);
            }
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.setString(1, this.prd_id);
            prs.setString(2, this.nama);
            prs.setString(3, this.alamat);
            prs.setString(4, this.umur);
            prs.setString(5, this.No_HP);
            prs.executeUpdate();
            System.out.println(_sql);
            prs.close();
        } catch (Exception err) {
            System.out.println("BB ERROR : " + err);
        }
    }
}
