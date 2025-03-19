<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
        </div>

        <ul class="sidebar-menu pt-3">
            <li><a href="index.html"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>

            <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-user me-2 d-inline-block"></i>Customers Manager</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="customercontroller">Customers</a></li>
                        <li><a href="customeraction">Add Customer</a></li>
                    </ul>
                </div>
            </li>

            <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Authentication</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="login.html">Login</a></li>
                        <li><a href="signup.html">Signup</a></li>
                        <li><a href="forgot-password.html">Forgot Password</a></li>
                        <li><a href="lock-screen.html">Lock Screen</a></li>
                    </ul>
                </div>
            </li>

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
