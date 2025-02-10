/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Service;
import model.Blog;
/**
 *
 * @author thang
 */
public class maintest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Service sv = new Service();
        ServiceDAO svd = new ServiceDAO();
        Blog bg = new Blog();
        BlogDAO bgd = new BlogDAO();
            
//        int abc = bgd.countBlog("", 2, -1);
//        System.out.println(abc);
        List<Service> av = svd.getActiveService(0, 10, "",-1 , "s.service_id", "ASC", 101,160);
        for(int i = 0 ; i<av.size() ;i++){
            System.out.println(av.get(i).getServiceId());
            System.out.println(av.get(i).getServicePrice());
            System.out.println(av.get(i).getServiceTitle());
        }
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
