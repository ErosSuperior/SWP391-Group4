package controller.login;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.UserRegister;
import model.User;

@WebServlet(name = "VerifyRegisterController", urlPatterns = {"/verifyRegister"})
public class VerifyRegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDAO userDao = new UserDAO();

        String formId = request.getParameter("formid");
        User existingUser = userDao.checkAccountExit((String) session.getAttribute("username" + formId));
        
        if (existingUser != null) {
            request.setAttribute("error", "Account already exists!");
            request.getRequestDispatcher("landing/verifyRegister.jsp").forward(request, response);
            return;
        }

        UserRegister userRegister = (UserRegister) session.getAttribute("authcode" + formId);
        String verificationCode = request.getParameter("authcode1") + request.getParameter("authcode2") +
                                  request.getParameter("authcode3") + request.getParameter("authcode4") +
                                  request.getParameter("authcode5") + request.getParameter("authcode6");

        if (userRegister != null && verificationCode.equals(userRegister.getCode())) {
            String username = (String) session.getAttribute("username" + formId);
            Boolean gender = (Boolean) session.getAttribute("gender" + formId);
            String address = (String) session.getAttribute("address" + formId);
            String email = (String) session.getAttribute("useremail" + formId);
            String phone = (String) session.getAttribute("phone" + formId);
            String password = (String) session.getAttribute("password" + formId);

            userDao.register(username, gender, address, password, email, phone, 4, true, phone);
            request.setAttribute("success", "Registration successful! You can now log in.");
        } else {
            request.setAttribute("error", "Incorrect verification code!");
        }
        
        request.setAttribute("formid", formId);
        request.getRequestDispatcher("landing/verifyRegister.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
