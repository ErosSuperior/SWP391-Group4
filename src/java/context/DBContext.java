package context;

import Utills.Constance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTIPLE SQL SERVER INSTANCE(s)*/
    /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://" + Constance.SERVERNAME + ":" + Constance.PORTNUMBER
                + "/" + Constance.DATABASENAME
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; // Optional params
        Class.forName("com.mysql.cj.jdbc.Driver"); // Correct MySQL driver
        return DriverManager.getConnection(url, Constance.USER_ACCOUNT_DATABASE, Constance.USER_PASS_DATABASE);
    }

    public static void main(String[] args) {
        try {
            DBContext dBContext = new DBContext();
            if (dBContext.getConnection() != null) {
                System.out.println("Kết nối thành công"); // Connection successful
            } else {
                System.out.println("Kết nối thất bại"); // Connection failed
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
