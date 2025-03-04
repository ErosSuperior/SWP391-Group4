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
    public static void main(String[] args) throws ParseException {

        Service sv = new Service();
        ServiceDAO svd = new ServiceDAO();
        Blog bg = new Blog();
        BlogDAO bgd = new BlogDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        UserDAO userDAO = new UserDAO();
        ShopCartDAO scd = new ShopCartDAO();
        
//        List<ReservationDetail> s= scd.getReservationDetail(1);
//        
//        for(ReservationDetail c : s){
//            System.out.println(c.getPrice());
//            System.out.println(c.getImage_link());
//        }
          
//        List<Reservation> r = reservationDAO.getReservation(0, 10, "", 1, 30, -1, -1, "reservation_id", "ASC");
//        
//        for(Reservation a : r){
//            System.out.println(a.getCreated_date());
//        }
        
//        boolean cartExists = reservationDAO.checkCartExist(1);
//
//        if (cartExists) {
//            System.out.println("Cart exists for user ID: " + 1);
//        } else {
//            System.out.println("No cart found for user ID: " + 1);
//        }
//        
//        boolean addcart = reservationDAO.addCart(2);
//        System.out.println(addcart);


//        String dateString = "2025-02-17"; // Your date as a string
//
//        try {
//            // Convert string to java.util.Date
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date utilDate = format.parse(dateString);
//
//            // Convert java.util.Date to java.sql.Date
//            Date beginTime = new Date(utilDate.getTime());
//            System.out.println(beginTime);
//            boolean a = reservationDAO.addToCart(3, 1, (float) 40.0, 2, beginTime);
//            System.out.println(a);
//        }catch(Exception e){
//            
//        }

//        List<User> user = userDAO.getAllStaffNotBusy();
//        for(User u :user){
//            System.out.println(u.getUser_fullname());
//            System.out.println(u.getUser_id());
//        }

//          List<Reservation> r = rsvd.getIdtoCompare();
//          for(Reservation a : r){
//              System.out.println(a.getUser_id());
//              System.out.println(a.getReservation_id());
//              System.out.println(a.getStaff_id());
//              System.out.println(a.getDetail_id());
//              System.out.println("");
//          }
//          
//        int abc = bgd.countBlog("", 2, -1);
//        System.out.println(abc);
//        int serviceaaa = svd.countActiveService("a");
//        System.out.println(serviceaaa);
//          List<String> serviceImage = svd.getAllServiceImages(1);
//          for(String im : serviceImage){
//              System.out.println(im);
//          }
//          
//          Service s = svd.getServicebyId(1);
//          System.out.println(s.getServiceTitle());
//          System.out.println(s.getServicePrice());
//          int a = svd.findServiceCategory(1);
//          System.out.println(a);
//          System.out.println("");
//          List<Service> av = svd.getAllServicebyCategory(1);
//          for(int i = 0; i<av.size();i++){
//              System.out.println(av.get(i).getServiceTitle());
//              System.out.println(av.get(i).getServiceId());
//              System.out.println(av.get(i).getServiceImage());
//              System.out.println(av.get(i).getServicePrice());
//          }
//            bgd.updateStatus(1, 0);
    }

}
