/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import DAO.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.setting;

public class SettingListControllers extends BaseAuthenticationAdminController {

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = Integer.parseInt(request.getParameter("status"));
        int id = Integer.parseInt(request.getParameter("id"));
        boolean st = Boolean.parseBoolean(request.getParameter("status"));
        AdminDAO settingDAO = new AdminDAO();
        if (status == 0) {
            settingDAO.setStatus(1, id);
        } else {
            settingDAO.setStatus(0, id);
        }

        response.sendRedirect("/project_swp391/Admin/setting-list");
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDAO settingDAO = new AdminDAO();
        HttpSession session = request.getSession();
        List<setting> listSetting = settingDAO.getSettingList();
        session.setAttribute("listSetting", listSetting);
        request.setAttribute("listSetting", listSetting);
        request.getRequestDispatcher("/Admin/settinglist.jsp").forward(request, response);
    }

}
