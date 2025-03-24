/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.Service;
import model.Blog;
import model.Feedback;
import model.Reservation;
import model.ReservationDetail;
import model.User;

/**
 *
 * @author thang
 */
public class maintest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, Exception {

        Service sv = new Service();
        ServiceDAO svd = new ServiceDAO();
        Blog bg = new Blog();
        BlogDAO bgd = new BlogDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        UserDAO userDAO = new UserDAO();
        ShopCartDAO scd = new ShopCartDAO();
        FeedbackDAO fbd = new FeedbackDAO();
        
//        int aa = reservationDAO.calculateNetRevenue();
//        System.out.println(aa);
        int bcc = userDAO.countUser("", 3, -1);
        reservationDAO.updateReservationStatus(1, 2);
        System.out.println(bcc);
        
//        List<Feedback> f = fbd.getAllServiceFeedback(0, 10, "", 1, -1, -1, "feedback_id", "ASC");
//        
//        
//        for(Feedback a : f){
//            System.out.println(a.getServiceTitle());
//            System.out.println(a.getName());
//            System.out.println(a.getUserImage());
//            System.out.println(a.getFeedbackId());
//        }
//        
        
    }

}
