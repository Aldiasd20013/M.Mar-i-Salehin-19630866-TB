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
public class mstr_user {
    
    Connection _Cnn;
    public String user_id, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, password;
    public String _Akses = "";
    
    public void hapusdata(String kode) {
        System.out.println("pesan kode" + kode);
        this.user_id = kode;
        try {
            _Cnn = logic_koneksi.getConnection();
            String _sql = "DELETE FROM `pratikum2020`.`mstr_user` WHERE `mstr_user`.`user_id` ='" 
                    + user_id + "'";
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
        try{
            String _sql = "";
            _Cnn = logic_koneksi.getConnection();
            _sql = "INSERT INTO `mstr_user`(`user_id`, `nama`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir` ,`alamat`,`password`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            prs.setString(1, this.user_id);
            prs.setString(2, this.nama);
            prs.setString(3, this.jenis_kelamin);
            prs.setString(4, this.tempat_lahir);
            prs.setString(5, this.tanggal_lahir);
            prs.setString(6, this.alamat);
            prs.setString(7, this.password);
            prs.executeUpdate();
            System.out.println(_sql);
            _Cnn = logic_koneksi.getConnection();
            prs.close();
        } catch (Exception err) {
            System.out.println("" + err);
        }
    }
    
    public void Cari(String user_id) {
        String pilih_user_id = user_id;
        try {
            _Akses = "";
            _Cnn = logic_koneksi.getConnection();
            String SQL = "SELECT * "
                    + "FROM `mstr_user` "
                    + "WHERE `user_id` = '" + pilih_user_id + "'";
            Statement st = _Cnn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                _Akses = "-";
                this.user_id = rs.getString(1);
                this.nama = rs.getString(2);
                this.jenis_kelamin = rs.getString(3);
                this.tempat_lahir = rs.getString(4);
                this.tanggal_lahir = rs.getString(5);
                this.alamat = rs.getString(6);
                this.password = rs.getString(7);
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
            _sql = "UPDATE `pratikum2020`.`mstr_user` SET"
                    + "`nama` = '" + String.valueOf(nama) + "',"
                    + "`jenis_kelamin` = '" + String.valueOf(jenis_kelamin) + "',"
                    + "`tempat_lahir` = '" + String.valueOf(tempat_lahir) + "',"
                    + "`tanggal_lahir` = '" + String.valueOf(tanggal_lahir) + "',"
                    + "`alamat` = '" + String.valueOf(alamat) + "',"
                    + "`password` = '" + String.valueOf(password) + "'"
                    + "WHERE `mstr_user`.`user_id` = '" + String.valueOf(user_id) + "'";
            int status = logic_koneksi.executestatement(_sql);
            PreparedStatement prs = _Cnn.prepareStatement(_sql);
            if (status == 1) {
                prs.setString(1, this.user_id);
                prs.setString(2, this.nama);
                prs.setString(3, this.jenis_kelamin);
                prs.setString(4, this.tempat_lahir);
                prs.setString(5, this.tanggal_lahir);
                prs.setString(6, this.alamat);
                prs.setString(7, this.password);
                
                prs.executeUpdate();
                System.out.println(_sql);
                _Cnn = logic_koneksi.getConnection();
                prs.close();
            }  
        }
        catch (Exception err) {
                    System.out.println("ERROR " + err + " " + _Cnn + " " + _sql);
        }
    }
    
    public void simpanORupdate() {
            try {
                String _sql;
                _Cnn = logic_koneksi.getConnection();
                if (_Akses.equals("")) {
                    _sql = "INSERT INTO `pratikum2020`.`mstr_user`(`user_id`, `nama`, `jenis_kelamin`, `tampat_lahir`, `tanggal_lahir`,`alamat`,`password`) VALUES ('?','?','?','?','?','?','?')";
                    System.out.println(_sql);
                } else {
                    _sql = "UPDATE `pratikum2020`.`mstr_user` SET `user_id` = '" + user_id + "', `nama` = '" + nama + "', `jenis_kelamin` = '" + jenis_kelamin +
                            "', `tempat_lahir` = '" + tempat_lahir + "', `tanggal_lahir` = '" + tanggal_lahir + "', `alamat` = '" + alamat + "', `password` = '" + password + "' WHERE `mstr_user`.`user_id` = '" + user_id + "'";
                    System.out.println(_sql);
                }
                PreparedStatement prs = _Cnn.prepareStatement(_sql);
                prs.setString(1, this.user_id);
                prs.setString(2, this.nama);
                prs.setString(3, this.jenis_kelamin);
                prs.setString(4, this.tempat_lahir);
                prs.setString(5, this.tanggal_lahir);
                prs.setString(6, this.alamat);
                prs.setString(7, this.password);
                prs.executeUpdate();
                System.out.println(_sql);
                prs.close();
            } catch (Exception err) {
                System.out.println("BB ERROR : " + err);
        }
    }
}
