/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import DAO.UserDAO;
import SendEmail.SendEmailRegister;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import model.UserRegister;
import model.user;

/**
 *
 * @author admin
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        // processRequest(request, response);
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        Boolean gender = Boolean.valueOf("gender");
        String address = request.getParameter("address");
        String useremail = request.getParameter("useremail");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repeatpassword = request.getParameter("repeatpassword");
        
        SendEmailRegister sm = new SendEmailRegister();
        String code = sm.getRandom();
        HttpSession session = request.getSession();
        session.setAttribute("username"+code, username);
        session.setAttribute("gender"+code, gender);
        session.setAttribute("address"+code, address);
        session.setAttribute("useremail"+code, useremail);
        session.setAttribute("phone"+code, phone);
        session.setAttribute("password"+code, password);
        session.setAttribute("repeatpassword"+code, repeatpassword);

        Pattern p = Pattern.compile("/((09|03|07|08|05)+([0-9]{8})\\b)/g");
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        //dung
        UserDAO accountDao = new UserDAO();
        user account =(user) accountDao.checkAccountExit(useremail);
        if (account != null) {
            request.setAttribute("r", "Account have exist!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!phone.matches(regex)) {
            request.setAttribute("r", "Invalid phone number!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            if (!password.equals(repeatpassword)) {
                request.setAttribute("r", "Pass not equal Repeatpassword!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
//                try {

              //  accountDao.register(username, gender, address, password, useremail, phone);

                //create instance object of the SendEmail Class
                
                //get the 6-digit code
               

                //craete new user using all information
                UserRegister user = new UserRegister(username, useremail, code);
                String text = "Hello "+ user.getName() +". You have successfully registered. Your code is: " + user.getCode()
                    + ". Please use this code to authenticate to our website!";
                //call the send email method
                boolean test = sm.sendEmail(user,text);

                //check if the email send successfully
                if (test) {
                    //session.sessionsetAttribute("authcode", user); main_20032023
                    //response.sendRedirect("verifyRegister.jsp"); main_20032023
                    
                    //test area 
                    session.setAttribute("authcode"+code, user);
                    request.setAttribute("formid",code);
                    request.getRequestDispatcher("verifyRegister.jsp").forward(request, response);
                    //test area
                } else {
                    out.println("Failed to send verification email!");
                }
                //response.sendRedirect("login");
//                } catch (Exception e) {
//                }
            }

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