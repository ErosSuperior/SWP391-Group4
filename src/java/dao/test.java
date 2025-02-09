/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;
import dao.*;
import model.*;
/**
 *
 * @author Admin
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserDAO acc = new UserDAO();
        System.out.println(acc.login("lucnbhe187315@fpt.edu.vn", "Lucnbhe187315").getUser_email());
        
    }
    
}
