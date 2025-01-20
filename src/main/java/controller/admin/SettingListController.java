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

public class SettingListController extends BaseAuthenticationAdminController {

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int value = Integer.parseInt(request.getParameter("value"));
        String description = request.getParameter("desciption");
        String status_raw = request.getParameter("status");
        boolean status = true;
        if (Integer.parseInt(status_raw) == 0) {
            status = false;
        }
        setting setting = new setting(id, name, value, description, status);
        new SettingDAO().updateSetting(setting);
        response.sendRedirect("/project_swp391/Admin/setting-list");
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int setting_id = Integer.parseInt(request.getParameter("setting_id"));
        setting setting = new SettingDAO().getsettingById(setting_id);
        request.setAttribute("setting", setting);
        request.getRequestDispatcher("/Admin/SettingDeatil.jsp").forward(request, response);
    }

}
