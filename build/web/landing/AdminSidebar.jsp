

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="model.User" %>
<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
            <a href="${pageContext.request.contextPath}/nav/dashboard">
                <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png?v=<%= System.currentTimeMillis() %>" height="70" class="logo-light-mode" alt="">
                <img src="<%= request.getContextPath() %>/assets/images/logo-light.png?v=<%= System.currentTimeMillis() %>" height="70" class="logo-dark-mode" alt="">
            </a>
        </div>

        <ul class="sidebar-menu pt-3">
            
            <c:if test="${sessionScope.account.role_id == 3}">
            <li><a href="${pageContext.request.contextPath}/nav/dashboard"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>
            </c:if>
            
            <c:if test="${sessionScope.account.role_id == 2}">
            <li><a href="${pageContext.request.contextPath}/ManagerDashboardController"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>    
                
            <li><a href="${pageContext.request.contextPath}/manager/managerlistBlog"><i class="uil uil-apps me-2 d-inline-block"></i>Post Management</a></li>

            <li><a href="${pageContext.request.contextPath}/manager/sliders"><i class="uil uil-apps me-2 d-inline-block"></i>Slider Management</a></li>

            <li><a href="${pageContext.request.contextPath}/manager/ServiceList"><i class="uil uil-apps me-2 d-inline-block"></i>Service Management</a></li>
            
            <li><a href="${pageContext.request.contextPath}/manager/feedbackList"><i class="uil uil-apps me-2 d-inline-block"></i>Feedback Management</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/reservationList"><i class="uil uil-apps me-2 d-inline-block"></i>Reservation Management</a></li>
            <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-user me-2 d-inline-block"></i>Customers Manager</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/customercontroller">Customers</a></li>
                        <li><a href="${pageContext.request.contextPath}/customeraction">Add Customer</a></li>
                    </ul>
                </div>
            </li>
            </c:if>
            
            <c:if test="${sessionScope.account.role_id == 1}">
            <li><a href="${pageContext.request.contextPath}/nav/dashboard"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>    
            <li><a href="${pageContext.request.contextPath}/admin/adminList"><i class="uil uil-apps me-2 d-inline-block"></i>User Management</a></li>
            <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-user me-2 d-inline-block"></i>Settings</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/adminsettinglist">Settings</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/categoryList">Category Management</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/roleList">Role Management</a></li>
                    </ul>
                </div>
            </li>            
            </c:if>
            <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Authentication</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="login.html">Profile Setting</a></li>
                        <li><a href="signup.html">Logout</a></li>
                    </ul>
                </div>
            </li>
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
