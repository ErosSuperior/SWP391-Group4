/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserProfile;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.Instant;
import model.User;

@WebServlet(name = "UserProfile", urlPatterns = {"/userprofile"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class UserProfile extends HttpServlet {

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
            out.println("<title>Servlet EmployeeProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeProfile at " + request.getContextPath() + "</h1>");
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
//        loginnavigation
        User account = (User) request.getSession().getAttribute("account"); // Lấy session để kiểm tra xem đăng nhập chưa
        if (account == null) { // Nếu chưa đăng nhập
            response.sendRedirect(request.getContextPath() + "/loginnavigation"); // Đẩy về trang login
            return; // Dừng thực hiện các lệnh tiếp theo
        }
        // Khởi tạo đối tượng UserDAO để lấy thông tin người dùng từ database
        UserDAO d = new UserDAO();
        User user = d.getUserDetail(account.getUser_id());

// Gửi thông tin người dùng đến request để hiển thị trên giao diện
        request.setAttribute("user", user);

// Lấy tham số "status" từ request (nếu có) để kiểm tra trạng thái hoạt động trước đó
        String status = request.getParameter("status");

        if (status != null && !status.isEmpty()) {
            // Kiểm tra giá trị của "status" và gán thông báo phù hợp
            if (status.equalsIgnoreCase("000")) {
                request.setAttribute("uploadmess", "Profile update completed successfully!"); // Cập nhật hồ sơ thành công

            } else if (status.equalsIgnoreCase("001")) {
                request.setAttribute("passmess", "Password has been changed successfully!"); // Đổi mật khẩu thành công

            } else if (status.equalsIgnoreCase("404")) {
                request.setAttribute("passmess", "Please check your old password and try again!"); // Lỗi mật khẩu cũ không đúng
            }
        }

// Kiểm tra quyền của tài khoản
        if (account.getRole_id() != 4) {
            // Nếu tài khoản KHÔNG phải khách hàng (role_id khác 4), chuyển hướng đến trang hồ sơ nhân viên
            request.getRequestDispatcher("landing/CustomerProfile.jsp").forward(request, response);
        } else {
            // Nếu tài khoản là khách hàng (role_id = 4), chuyển hướng đến trang hồ sơ khách hàng
            request.getRequestDispatcher("landing/CustomerProfile.jsp").forward(request, response);
        }

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
        User account = (User) request.getSession().getAttribute("account"); // Lấy session để kiểm tra xem đăng nhập chưa
        if (account == null) { // Nếu chưa đăng nhập
            response.sendRedirect(request.getContextPath() + "/loginnavigation"); // Đẩy về trang login
            return; // Dừng thực hiện các lệnh tiếp theo
        }

        // Lấy thông tin người dùng từ request (các giá trị nhập vào từ form)
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

// Khởi tạo đối tượng UserDAO để thao tác với cơ sở dữ liệu
        UserDAO d = new UserDAO();

// Lấy thông tin chi tiết của người dùng từ database dựa trên user_id
        User user = d.getUserDetail(account.getUser_id());

// Lấy dữ liệu file avatar từ request
        Part filePart = request.getPart("avatar");

// Mặc định giữ nguyên đường dẫn avatar cũ
        String fileUrl = user.getUser_image();

// Xác định thư mục upload ảnh trên server
        String uploadDir = getServletContext().getRealPath("") + File.separator + "assets/images/avatars/";

// Kiểm tra nếu thư mục chưa tồn tại thì tạo mới
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

// Kiểm tra nếu người dùng có tải lên ảnh mới
        if (filePart != null && filePart.getSize() > 0) {
            // Lấy tên gốc của file
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Lấy phần mở rộng (đuôi file) từ tên file
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // Đặt tên file mới bằng timestamp để tránh trùng lặp
            String newFileName = Instant.now().getEpochSecond() + fileExtension;

            // Xác định đường dẫn đầy đủ để lưu file
            String uploadPath = uploadDir + File.separator + newFileName;

            // Cập nhật đường dẫn mới cho avatar
            fileUrl = "assets/images/avatars/" + newFileName;

            // Kiểm tra nếu người dùng đã có avatar trước đó và không phải là link từ web
            if (user.getUser_image() != null && !user.getUser_image().isEmpty()) {
                if (!user.getUser_image().startsWith("http://") && !user.getUser_image().startsWith("https://")) {
                    // Xóa avatar cũ trong thư mục để tránh file rác
                    File oldFile = Paths.get(uploadDir, user.getUser_image()).toFile();
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }
            }

            // Lưu file ảnh mới vào thư mục chỉ định
            filePart.write(uploadPath);
        }

// Cập nhật thông tin người dùng trong database với dữ liệu mới
        boolean check = d.updateProfile(name, gender, phone, address, fileUrl, email);

        if (check) {
            // Nếu cập nhật thành công, lấy lại thông tin người dùng mới
            User usernew = d.getUserDetail(account.getUser_id());

            // Cập nhật lại session với thông tin mới
            request.getSession().setAttribute("account", usernew);

            // Chuyển hướng đến trang profile với trạng thái cập nhật thành công
            response.sendRedirect("userprofile?status=000");
        } else {
            // Nếu cập nhật thất bại, chuyển hướng đến trang báo lỗi
            response.sendRedirect("error");
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
