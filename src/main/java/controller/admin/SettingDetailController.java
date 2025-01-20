/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import DAO.SettingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.setting;

/**
 *
 * @author win
 */
public class SettingDetailController extends BaseAuthenticationAdminController {
 
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status_raw = request.getParameter("status");
        boolean status = true;
        if (Integer.parseInt(status_raw) == 0) {
            status = false;
        }
        setting setting = new SettingDAO().getsettingById(1);
        setting.setSetting_status(status);
        new SettingDAO().updateSetting(setting);
        response.sendRedirect("/project_swp391/Admin/setting-list");
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setting setting = new SettingDAO().getsettingById(1);
        request.setAttribute("setting", setting);
        request.getRequestDispatcher("/Admin/SettingDeatil.jsp").forward(request, response); 
    }

}
