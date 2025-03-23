/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.VnPay;

import dao.ReservationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author admin
 */
@WebServlet(name = "ReturnResult", urlPatterns = {"/result"})
public class ReturnResult extends HttpServlet {

    ReservationDAO rsdao = new ReservationDAO();

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
            out.println("<title>Servlet ReturnResult</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReturnResult at " + request.getContextPath() + "</h1>");
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
        String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
        int status = Integer.parseInt(vnp_TransactionStatus);

        String errorMessage;
        switch (status) {
            case 7:
                errorMessage = "Payment deducted successfully, but the transaction is suspected of fraud or unusual activity.";
                break;
            case 9:
                errorMessage = "Transaction failed: The customer's card/account has not registered for Internet Banking services.";
                break;
            case 10:
                errorMessage = "Transaction failed: The customer entered incorrect card/account authentication information more than 3 times.";
                break;
            case 11:
                errorMessage = "Transaction failed: Payment timeout expired. Please try again.";
                break;
            case 12:
                errorMessage = "Transaction failed: The customer's card/account has been locked.";
                break;
            case 13:
                errorMessage = "Transaction failed: Incorrect OTP entered. Please try again.";
                break;
            case 24:
                errorMessage = "Transaction failed: The customer canceled the transaction.";
                break;
            case 51:
                errorMessage = "Transaction failed: Insufficient balance in the account.";
                break;
            case 65:
                errorMessage = "Transaction failed: The account has exceeded the daily transaction limit.";
                break;
            case 75:
                errorMessage = "Transaction failed: The payment bank is under maintenance.";
                break;
            case 79:
                errorMessage = "Transaction failed: Incorrect payment password entered too many times. Please try again.";
                break;
            case 99:
                errorMessage = "Transaction failed: Other errors (not listed).";
                break;
            default:
                errorMessage = "Transaction failed: Unknown error.";
        }

        if (status == 0) {
            int reservationId = 0;

            Object reservationIdObj = request.getSession().getAttribute("reservationId");
            if (reservationIdObj != null) {
                reservationId = (int) reservationIdObj;
            }
            if (reservationId != 0) {
                rsdao.updateReservationPaymentStatus(reservationId, 1);
                request.setAttribute("successmess", "1");
                request.getRequestDispatcher("landing/Success.jsp").forward(request, response);
            }else{
                request.setAttribute("status", errorMessage);
                request.getRequestDispatcher("landing/PaymentFail.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("status", errorMessage);
            request.getRequestDispatcher("landing/PaymentFail.jsp").forward(request, response);
        }

    }

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static String formatNumber(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###,###.###");
        return formatter.format(number);
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
