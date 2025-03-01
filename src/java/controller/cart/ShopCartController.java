/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import dao.ShopCartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ReservationDetail;
import model.User;

public class ShopCartController extends HttpServlet {

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
            out.println("<title>Servlet ShopCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopCartController at " + request.getContextPath() + "</h1>");
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
        int reservationID = d.getReservationID(user_id); // Lấy reservationID của người dùng để lấy danh sách service người dùng chọn

        List<ReservationDetail> listreservation = d.getReservationDetail(reservationID); // Lấy dánh sách service
        
        request.setAttribute("listreservation", listreservation); // Lưu vào resquest để đẩy lên JSP
        request.getRequestDispatcher("landing/ShopCart.jsp").forward(request, response); //Tham chiếu tới trang ShopCart.jsp để truyền dữ liệu qua
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
        String service_id = request.getParameter("service_id"); // Lấy dữ liệu từ JSP gửi về 
        String quantity = request.getParameter("quantity"); // Lấy dữ liệu từ JSP gửi về 
        String delete_id = request.getParameter("delete_id"); // Lấy dữ liệu từ JSP gửi về 

        User account = (User) request.getSession().getAttribute("account");  // Lấy session để kiểm tra xem đăng nhập chưa
        ShopCartDAO d = new ShopCartDAO(); // Tạo đối tượng để sử dụng hàm của nó

        int user_id = account.getUser_id(); // Lấy user_id
        if (delete_id != null && !delete_id.isEmpty()) { // Kiểm tra xem có delete_id gửi về hay k. Nếu có
            boolean nhom = d.deleteService(delete_id, user_id); // Gọi hàm xóa service khỏi giỏ hàng
            if (nhom) { //Xóa thành công
                response.sendRedirect(request.getContextPath() + "/mycart"); // Load lại trang cart
            } else {
                response.sendRedirect(request.getContextPath() + "/error"); // Xóa ko thành công chuyển tới trang lỗi
            }
            return; // Ngăn chặn hành động tiếp theo
        }
        
        boolean result = d.updateQuantity(quantity, service_id, user_id); // Update số lượng

        if (result) { // Update thành công
            response.setContentType("application/json"); // Xác định loại thông báo
            response.getWriter().write("{\"status\":\"1\"}"); // Gửi thông báo về client
        } else {
            response.setContentType("application/json"); // Xác định loại thông báo 
            response.getWriter().write("{\"status\":\"0\"}"); // Gửi thông báo về client
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
