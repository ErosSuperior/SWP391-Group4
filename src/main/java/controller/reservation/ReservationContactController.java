/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reservation;

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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.ReservationDetail;
import model.children;
import model.reservation;
import model.user;

/**
 *
 * @author win
 */
public class ReservationContactController extends HttpServlet {

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
            out.println("<title>Servlet ReservationContactController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservationContactController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null || session.getAttribute("admin") != null) {
            user user = (user) session.getAttribute("admin");
            if (session.getAttribute("account") != null) {
                user = (user) session.getAttribute("account");
            }
            int user_id = user.getUser_id();
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");

            if (carts != null) {
                float totalPrice = 0;
                for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                    Integer productId = entry.getKey();
                    Cart cart = entry.getValue();
                    totalPrice += cart.getService().getService_price() * cart.getQuantity() * cart.getNum_of_person();
                }
                session.setAttribute("total_order", totalPrice);
            }

            //List Doctor
            UserDAO userDAO = new UserDAO();
            List<user> doctorList = userDAO.getAllUserByRoleId(2);
            request.setAttribute("doctorList", doctorList);
            //List Nurse
            List<user> nurseList = userDAO.getAllUserByRoleId(3);
            request.setAttribute("nurseList", nurseList);
            //List children
            List<children> listChildren = new ChildrenDAO().getAllChildrenByID(user_id);
            request.setAttribute("listChildren", listChildren);
            //Number of Children
            int n = listChildren.size();
            request.setAttribute("n", n);

            session.setAttribute("carts", carts);

        }
        request.getRequestDispatcher("Reservation_Contact.jsp").forward(request, response);
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
        try {
            long millis = System.currentTimeMillis();
            Date datee = new Date(millis);
            HttpSession session = request.getSession(true);
            //List children
            List<children> listChildrenx = new ChildrenDAO().getAllChildrenByID(47);
            request.setAttribute("listChildrenx", listChildrenx);
            //Number of Children
            int num = listChildrenx.size();
            request.setAttribute("num", num);
            String[] noc = request.getParameterValues("num_of_person");
            String[] id = request.getParameterValues("id");
            //List doctor
            List<Integer> listDoctor = new ArrayList<>();
            String[] doctor = request.getParameterValues("doctor");
            for (int i = 0; i < doctor.length; i++) {
                int n = Integer.parseInt(doctor[i]);
                listDoctor.add(n);
            }
            //List children
            List<Integer> listChildren = new ArrayList<>();
            String[] children = request.getParameterValues("children");
            for (int i = 0; i < children.length; i++) {
                int n = Integer.parseInt(children[i]);
                listChildren.add(n);
            }

            List<Date> listDate = new ArrayList<>();
            String[] date = request.getParameterValues("date");
            for (int i = 0; i < date.length; i++) {
                Date a = datee.valueOf(date[i]);
                if (a.compareTo(datee) > 0) {
                    listDate.add(a);
                } else {
                    throw new Exception();
                }

            }
            //List nurse
            List<Integer> listNurse = new ArrayList<>();
            String[] nurse = request.getParameterValues("nurse");
            for (int i = 0; i < nurse.length; i++) {
                int n = Integer.parseInt(nurse[i]);
                listNurse.add(n);
            }
            //List slot
            List<Integer> listSlot = new ArrayList<>();
            String[] slot = request.getParameterValues("time");
            for (int i = 0; i < slot.length; i++) {
                int n = Integer.parseInt(slot[i]);
                listSlot.add(n);
            }

            //Get User ID
            int a_id = 0;
            user a = (user) session.getAttribute("account");
            if (session.getAttribute("admin") != null) {
                a = (user) session.getAttribute("admin");
            }
            if (a != null) {
                a_id = a.getUser_id();
            }
            String note = "";
            if (request.getParameter("note") != null) {
                note = request.getParameter("note");
            }

            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            int loop = 0;
            if (carts != null) {

                float totalPrice = 0;
                //This loop to add Nurse - Doctor - Slot - Date - List Children
                for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                    Integer productId = entry.getKey();
                    Cart cart = entry.getValue();
                    totalPrice += cart.getService().getService_price() * cart.getQuantity() * cart.getNum_of_person();
                    cart.setDoctor_id(listDoctor.get(loop));
                    cart.setNurse_id(listNurse.get(loop));
                    cart.setSlot(listSlot.get(loop));
                    cart.setChildren_id(listChildren);
                    cart.setBegin_time(listDate.get(loop));
                    loop = loop + 1;
                }

                //Create a new order
                reservation reser = new reservation(a_id, totalPrice, note, 1);
                ReservationDAO reservationDAO = new ReservationDAO();
                int n = reservationDAO.CreateReservationAndReturnId(reser);
                System.out.println(n + "YOUR RESERVATION ID ============");

                //Save to order detail
                for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                    Integer productId = entry.getKey();
                    Cart cart = entry.getValue();
                    for (int i = 0; i < cart.getNum_of_person(); i++) {
                        ReservationDetail reservationDetail = new ReservationDetail(n, entry.getKey(), cart.getService().getService_price(), cart.getQuantity(), cart.getNum_of_person(), cart.getService().getCategory_id(), cart.getDoctor_id(), cart.getNurse_id(), cart.getBegin_time(), cart.getSlot(), cart.getChildren_id().get(i));
                        //Save all order to Reservation Detail
//                    System.out.println(n);
//                    System.out.println("Service ID" + reservationDetail.getService_id());
//                    System.out.println("Price" + reservationDetail.getPrice());
//                    System.out.println("Quantiy" + reservationDetail.getQuantity());
//                    System.out.println("num of person" + reservationDetail.getNumOfPerson());
//                    System.out.println("category id" + reservationDetail.getCategory_id());
//                    System.out.println("doctor id" + reservationDetail.getDoctor_id());
//                    System.out.println("nurde ID" + reservationDetail.getNurse_id());
//                    System.out.println("slot ID" + reservationDetail.getSlot());
//                    System.out.println("Begin time: " + reservationDetail.getBegin_time());
//                    System.out.println("children id  " + reservationDetail.getChildren_id());
                        reservationDAO.SaveOrderDetail(n, reservationDetail);
                    }
                }
                // Update User Information
                String name = request.getParameter("fullname");
                boolean gender = true;
                if (request.getParameter("gender").equals("0")) {
                    gender = false;
                }
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                UserDAO userDAO = new UserDAO();
                user user = new user(a_id, name, gender, address, a.getUser_password(), a.getUser_email(), phone, a.getUser_role(), a.isUser_status(), a.getUser_image());
                session.removeAttribute("account");
                session.removeAttribute("admin");
                if (user.getUser_role() == 1) {
                    session.setAttribute("account", user);
                } else {
                    session.setAttribute("admin", user);
                }
                new UserDAO().EditUser(user);
                session.removeAttribute("carts");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("LOi NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE:"+e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

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
