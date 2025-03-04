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

@WebServlet(name = "AddSettingController", urlPatterns = {"/adminaddsetting"})
public class AddSettingController extends HttpServlet {

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
            out.println("<title>Servlet AddSettingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSettingController at " + request.getContextPath() + "</h1>");
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
        SettingDAO d = new SettingDAO(); 
        List<Setting> listsetting = d.get3Setting(); // Hàm lấy 3 setting để hiển thị
        List<String> listtype = d.getAllType(); // Lấy tất cả các type cho ng dùng chọn
        request.setAttribute("listtype", listtype); // Lưu để đẩy lên 
        request.setAttribute("listsetting", listsetting); // Lưu để đẩy lên 
        request.getRequestDispatcher("landing/AddSetting.jsp").forward(request, response); // Ánh xạ và truyền dữ liệu tới trang Add Setting
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
        String setting_name = request.getParameter("setting_name"); // Nhận thông tin từ JSP
        String setting_type = request.getParameter("setting_type"); // Nhận thông tin từ JSP
        String custom_type = request.getParameter("custom_type"); // Nhận thông tin từ JSP
        String setting_description = request.getParameter("setting_description"); // Nhận thông tin từ JSP
        String setting_value = request.getParameter("setting_value"); // Nhận thông tin từ JSP
        String setting_status = request.getParameter("setting_status"); // Nhận thông tin từ JSP

        if (setting_type.equalsIgnoreCase("Other")) { // Kiểm tra xem người dùng chọn Type từ danh sách có sẵn hay tạo Type mới
            setting_type = custom_type; // Nếu tạo type mới thì gán setting_type =  type mới
        }
        SettingDAO d = new SettingDAO(); 
        Boolean check = d.insertSetting(setting_name, setting_type, setting_description, setting_value, setting_status); // Gọi hàm tạo Setting mới

        if (!check) { // Kiểm tra 
            response.sendRedirect(request.getContextPath() + "/error"); // Chạy lỗi chạy tới trang lỗi
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("add","Setting added successfully"); // Chạy đc lưu thông báo để hiển thị
            response.sendRedirect(request.getContextPath() + "/adminaddsetting"); // Load lại trang
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
