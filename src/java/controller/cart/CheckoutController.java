/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import dao.ShopCartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import model.ReservationDetail;
import model.User;

@WebServlet(name = "ServiceController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

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
            out.println("<title>Servlet CheckoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
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
        User account = (User) request.getSession().getAttribute("account"); // Lấy session để kiểm tra xem đăng nhập chưa
        if (account == null) { // Nếu chưa đăng nhập
            response.sendRedirect(request.getContextPath() + "/home"); // Đẩy về trang home
            return; // Dừng thực hiện các lệnh tiếp theo
        }
        // Tiếp tục nếu đã đăng nhập
        int user_id = account.getUser_id(); // Lấy user_id
        ShopCartDAO d = new ShopCartDAO(); // Tạo đối tượng để sử dụng hàm của nó
        int totalservice = d.totalService(user_id); // Gọi hàm để tính tổng service khách chọn

        if (totalservice <= 0) { // Nếu không có service
            response.sendRedirect(request.getContextPath() + "/mycart"); // Đẩy về trang cart không cho checkout
            return;
        }
        int reservationID = d.getReservationID(user_id); // Lấy reservationID của người dùng để lấy danh sách service người dùng chọn
        List<ReservationDetail> listreservation = d.getReservationDetail(reservationID); // Lấy dánh sách service
        request.setAttribute("listreservation", listreservation); // Lưu vào resquest để đẩy lên JSP
        request.setAttribute("totalservice", totalservice); // Lưu tổng số service vào để đẩy lên jsp
        request.getRequestDispatcher("landing/Checkout.jsp").forward(request, response); //Tham chiếu tới trang Checkout.jsp
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
        String name = request.getParameter("name"); // Lấy dữ liệu từ JSP gửi xuống 
        String phone = request.getParameter("phone"); // Lấy dữ liệu từ JSP gửi xuống 
        String email = request.getParameter("email"); // Lấy dữ liệu từ JSP gửi xuống 
        String address = request.getParameter("address"); // Lấy dữ liệu từ JSP gửi xuống 
        String paymentMethod = request.getParameter("paymentMethod"); // Lấy dữ liệu từ JSP gửi xuống 
        String total = request.getParameter("total"); // Lấy dữ liệu từ JSP gửi xuống 
        String note = request.getParameter("note"); // Lấy dữ liệu từ JSP gửi xuống 

        if(note==null || note.isEmpty()){
            note = "(default note)";
        }
        
        User account = (User) request.getSession().getAttribute("account"); // Lấy session để kiểm tra xem đăng nhập chưa
        int user_id = account.getUser_id(); // Lấy user_id

        ShopCartDAO d = new ShopCartDAO(); // Tạo đối tượng để sử dụng hàm của nó

        int reservationID = d.getReservationID(user_id); // Lấy reservationID của người dùng để lấy danh sách service người dùng chọn
        List<ReservationDetail> listreservation = d.getReservationDetail(reservationID); // Lấy dánh sách service     
        
        boolean checkTime = d.checkReservationTime(reservationID); // Gọi hàm kiểm tra thời gian 
        
        if (checkTime) { // Nếu có service đã hết hạn
            response.sendRedirect(request.getContextPath() + "/error"); // Đẩy tới trang lỗi 
            return; // Ngăn chặn hành động 
        }
        
        if (paymentMethod.equalsIgnoreCase("cod")) { // Nếu ko có service hết hạn và phương thức thanh toán COD

            boolean result = d.checkoutService(total, note, name, phone, email, address, user_id); // Gọi hàm để cập nhật thông tin cho kahcs hàng

            if (result) { // Kiểm tra xem cập nhật thành công hay k 
                response.sendRedirect(request.getContextPath() + "/home"); // Neu có chuyển đến trang hoàn thành
            } else {
                response.sendRedirect(request.getContextPath() + "/error"); // Không chuyển đến trang lỗi
            }
        } else {
// Phương thức thanh toán ONL
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
