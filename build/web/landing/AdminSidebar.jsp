<%-- 
    Document   : AdminNavbar
    Created on : Feb 8, 2025, 1:14:19 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
            <a href="${pageContext.request.contextPath}/nav/dashboard">
                <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png?v=<%= System.currentTimeMillis() %>" height="70" class="logo-light-mode" alt="">
                <img src="<%= request.getContextPath() %>/assets/images/logo-light.png?v=<%= System.currentTimeMillis() %>" height="70" class="logo-dark-mode" alt="">
            </a>
        </div>

        <ul class="sidebar-menu pt-3">
            <li><a href="${pageContext.request.contextPath}/nav/dashboard"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>
           
            <li><a href="${pageContext.request.contextPath}/manager/managerlistBlog"><i class="uil uil-apps me-2 d-inline-block"></i>Post Management</a></li>

            <li><a href="${pageContext.request.contextPath}/manager/sliders"><i class="uil uil-apps me-2 d-inline-block"></i>Slider Management</a></li>

            <li><a href="${pageContext.request.contextPath}/manager/ServiceList"><i class="uil uil-apps me-2 d-inline-block"></i>Service Management</a></li>

            <li><a href="${pageContext.request.contextPath}/home"><i class="uil uil-window me-2 d-inline-block"></i>Home page</a></li>
        </ul>
        <!-- sidebar-menu  -->
    </div>
    <!-- sidebar-content  -->
    <ul class="sidebar-footer list-unstyled mb-0">
        <li class="list-inline-item mb-0 ms-1">
            <a href="#" class="btn btn-icon btn-pills btn-soft-primary">
                <i class="uil uil-comment icons"></i>
            </a>
        </li>
    </ul>
</nav>
