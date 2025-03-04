/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.setting;

import dao.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Setting;

@WebServlet(name = "SettingDetailController", urlPatterns = {"/adminsettingdetail"})
public class SettingDetailController extends HttpServlet {

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
            out.println("<title>Servlet SettingDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingDetailController at " + request.getContextPath() + "</h1>");
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
        String setting_id = request.getParameter("setting_id"); // Nhận setting_id từ JSP gưi xuống
        SettingDAO d = new SettingDAO(); 
        Setting settingdetail = d.getSettingDetail(setting_id); // Gọi hàm lấy thông tin chi tiết của 1 setting
        List<String> listtype = d.getAllType(); // Gọi hàm lấy danh sách type của setting
        request.setAttribute("listtype", listtype); // Lưu để gửi lên JSP
        request.setAttribute("settingdetail", settingdetail); // Lưu để gửi lên JSP
        request.getRequestDispatcher("landing/SettingDetail.jsp").forward(request, response); // Ánh xạ và truyền dữ liệu tới JSP
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
        String setting_id = request.getParameter("setting_id"); // Nhận dữ liệu từ 
        String setting_name = request.getParameter("setting_name"); // Nhận dữ liệu từ 
        String setting_type = request.getParameter("setting_type"); // Nhận dữ liệu từ 
        String setting_description = request.getParameter("setting_description"); // Nhận dữ liệu từ 
        String setting_value = request.getParameter("setting_value"); // Nhận dữ liệu từ 
        String setting_status = request.getParameter("setting_status"); // Nhận dữ liệu từ 

        SettingDAO d = new SettingDAO();
        boolean check = d.updateSetting(setting_id, setting_name, setting_type, setting_description, setting_value, setting_status); // Gọi hàm update thông tin setting
        if (!check) { // Kiểm tra
            response.sendRedirect(request.getContextPath() + "/error"); // Hàm chạy thất bại chuyển hướng tới trang lỗi
        } else { // Thành công
            HttpSession session = request.getSession(); // Khởi tại session
            session.setAttribute("update", "Setting update successfully"); //Tạo session lưu trữ thông báo cho ng dùng
            response.sendRedirect(request.getContextPath() + "/adminsettingdetail?setting_id=" +setting_id); // Load lại trang Detail hiện tại
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
