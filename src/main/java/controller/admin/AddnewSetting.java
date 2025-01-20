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

public class AddnewSetting extends BaseAuthenticationAdminController {

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int value = Integer.parseInt(request.getParameter("value"));
        boolean status = true;
        int status_raw = Integer.parseInt(request.getParameter("status"));
        if (status_raw == 0) {
            status = false;
        }
        String des = request.getParameter("description");
        System.out.println(name + "-------------------------");
        System.out.println(value + "-------------------------");
        System.out.println(status + "-------------------------");
        System.out.println(des + "-------------------------");
        setting setting = new setting(1, name, value, des, status);
        new SettingDAO().addNewSetting(setting);
        response.sendRedirect("/project_swp391/Admin/setting-list");
    }

}
