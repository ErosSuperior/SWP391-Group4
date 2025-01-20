/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import DAO.CategoryDAO;
import DAO.ReservationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.category;
import model.reservation_counts;
import model.user;

/**
 *
 * @author win
 */
public class AdminController extends BaseAuthenticationAdminController {

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("?????");
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. Reservations Success
        ReservationDAO reserDAO = new ReservationDAO();
        int countReserSuccess = reserDAO.CountReservationByStatus(1);
        request.setAttribute("countReserSuccess", countReserSuccess);
        //2. Reservation Cancelled
        int countReserCencelled = reserDAO.CountReservationByStatus(2);
        request.setAttribute("countReserCencelled", countReserCencelled);
        //3. Reservation Submited 
        int countReserSubmited = reserDAO.CountReservationByStatus(3);
        request.setAttribute("countReserSubmited", countReserSubmited);
        //4. Revenues All
        float RevenuesAll = reserDAO.RevenuesAll();
        request.setAttribute("RevenuesAll", RevenuesAll);
        //5. Revenues By Category

        CategoryDAO cateDAO = new CategoryDAO();
        List<category> cateList = cateDAO.getAllCategory();
        List<Float> revenuesOfCate = reserDAO.RevenuesByCategoryID();
        request.setAttribute("cateList", cateList);
        request.setAttribute("revenuesOfCate", revenuesOfCate);

        //6. The trend of reservation counts (success, all) by day for the last 7 days 
        List<reservation_counts> listSuccessRes = reserDAO.getAllReservationSuccess7days();
        List<reservation_counts> listAllRes = reserDAO.getAllReservationSuccess7days();
        request.setAttribute("listSuccessRes", listSuccessRes);
        request.setAttribute("listAllRes", listAllRes);

        request.getRequestDispatcher("/Admin/AdminDashBoard.jsp").forward(request, response);
    }

}
