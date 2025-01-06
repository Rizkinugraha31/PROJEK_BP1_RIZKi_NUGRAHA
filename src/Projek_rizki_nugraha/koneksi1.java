package Projek_rizki_nugraha;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi1 {
    Connection con;
    
    public koneksi1() {
        String id, pass, driver, url;
        id="root";
        pass="";
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/db_konversi";
        
        try 
        {
            Class.forName(driver).newInstance();
            con=DriverManager.getConnection(url,id,pass);
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        }
        catch(Exception e)
        {
            System.out.println(""+e.getLocalizedMessage());
        }
    }
    public static void main(String[] args) {
        koneksi1 k = new koneksi1();
    }
}