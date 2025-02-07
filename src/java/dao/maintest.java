/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;
import java.util.ArrayList;
import java.util.List;
import model.Service;
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
        
        List<Service> av = svd.getActiveService(0, 2, "a", 1, "s.service_title", "ASC");
        for(int i = 0 ; i<av.size() ;i++){
            System.out.println(av.get(i).getServiceImage());

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
          List<Service> svc = svd.getActiveCategory();
          for(int i = 0;i<svc.size();i++){
              System.out.println(svc.get(i).getCategoryTitle());
              System.out.println(svc.get(i).getCategoryIcon());
              System.out.println(svc.get(i).getCategoryId());
          }
          
          
          
   }
    
}
