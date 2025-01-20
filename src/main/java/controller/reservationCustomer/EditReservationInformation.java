/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reservationCustomer;

import DAO.ChildrenDAO;
import DAO.ReservationDAO;
import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.children;
import model.user;

/**
 *
 * @author truon
 */
public class EditReservationInformation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditReservationInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditReservationInformation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rid = request.getParameter("rid");
        int ridd = 0;
        HttpSession session = request.getSession();
        ReservationDAO reservationDAO = new ReservationDAO();
        if (rid != null) {
            ridd = Integer.parseInt(rid);
            model.EditReservationInformation reservationedit = reservationDAO.getInformationByReservationIdedit(ridd);
            request.setAttribute("reservationedit", reservationedit);
            user user = (user) session.getAttribute("account");
            if (session.getAttribute("admin") != null) {
                user = (user) session.getAttribute("admin");
            }
            UserDAO userDAO = new UserDAO();
            ChildrenDAO childrenDAO = new ChildrenDAO();
            List<user> doctorList = userDAO.getAllUserByRoleId(2);
            request.setAttribute("doctorList", doctorList);
            List<user> nurseList = userDAO.getAllUserByRoleId(3);
            request.setAttribute("nurseList", nurseList);
            List<children> childrenList = ChildrenDAO.getAllChildrenByID(user.getUser_id());
            request.setAttribute("childrenList", childrenList);

            request.getRequestDispatcher("EditReservationInformation.jsp").forward(request, response);
        }
        request.getRequestDispatcher("EditReservationInformation.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationDAO reservationDAO = new ReservationDAO();
        HttpSession session = request.getSession();
        String rid = request.getParameter("reservation_detail_id");
        int reservation_id = 0;
        int quantity = 0;
        String qua = request.getParameter("quantity");
        int slot = 0;
        String slo = request.getParameter("slot");
        String begin_date = request.getParameter("begin_date");
        int doctor = Integer.parseInt(request.getParameter("doctor"));
        int nurse = Integer.parseInt(request.getParameter("nurse"));
        int children = Integer.parseInt(request.getParameter("children"));
        System.out.println(children);
        if (rid != null && qua != null && slo != null && !rid.isEmpty() && !qua.isEmpty()) {
            reservation_id = Integer.parseInt(rid);
            quantity = Integer.parseInt(qua);
            slot = Integer.parseInt(slo);
            reservationDAO.editReservatonInformation(quantity, begin_date, children, doctor, nurse, slot, reservation_id);
            session.setAttribute("quantity", quantity);
            session.setAttribute("begin_date", begin_date);
            session.setAttribute("children", children);
            session.setAttribute("doctor", doctor);
            session.setAttribute("nurse", nurse);
            session.setAttribute("slot", slot);
         //   request.setAttribute("res_id", reservation_id);

            request.setAttribute("mess", "Your edit revertion information successfull!");
            //  response.sendRedirect("Reservationinformation");
            request.getRequestDispatcher("EditReservationInformation.jsp").forward(request, response);
             
        request.getRequestDispatcher("Reservationinformation").forward(request, response);

        }
               request.getRequestDispatcher("Reservationinformation").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


