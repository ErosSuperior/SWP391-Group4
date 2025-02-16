<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<header id="topnav" class="defaultscroll sticky">
            <div class="container">
                <!-- Logo container-->
                <a class="logo" href="${pageContext.request.contextPath}/nav/homepage">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png?v=<%= System.currentTimeMillis() %>" height="70" class="logo-light-mode" alt="">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-light.png?v=<%= System.currentTimeMillis() %>" height="70" class="logo-dark-mode" alt="">
                </a>                
                <!-- Logo End -->
                
                <!-- Start Mobile Toggle -->
                <div class="menu-extras">
                    <div class="menu-item">
                        <!-- Mobile menu toggle-->
                        <a class="navbar-toggle" id="isToggle" onclick="toggleMenu()">
                            <div class="lines">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </a>
                        <!-- End mobile menu toggle-->
                    </div>
                </div>
                <!-- End Mobile Toggle -->

                <!-- Start Dropdown -->
                <ul class="dropdowns list-inline mb-0">
                    <li class="list-inline-item mb-0">
                        <a href="javascript:void(0)" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
                            <div class="btn btn-icon btn-pills btn-primary"><i data-feather="settings" class="fea icon-sm"></i></div>
                        </a>
                    </li>

                    <li class="list-inline-item mb-0 ms-1">
                        <div class="dropdown dropdown-primary">
                            <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/assets/images/doctors/01.jpg" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                            <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                                <a class="dropdown-item d-flex align-items-center text-dark" href="doctor-profile.html">
                                    <img src="${pageContext.request.contextPath}/assets/images/doctors/01.jpg" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                    <div class="flex-1 ms-2">
                                        <span class="d-block mb-1">Calvin Carlo</span>
                                        <small class="text-muted">Orthopedic</small>
                                    </div>
                                </a>
                                <a class="dropdown-item text-dark" href="doctor-dashboard.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-dashboard align-middle h6"></i></span> Dashboard</a>
                                <a class="dropdown-item text-dark" href="doctor-profile-setting.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-setting align-middle h6"></i></span> Profile Settings</a>
                                <div class="dropdown-divider border-top"></div>
                                <c:if test="${not empty sessionScope.account.user_id}">
                                    <a class="dropdown-item text-dark" href="<c:url value='/logout' />">
                                        <span class="mb-0 d-inline-block me-1">
                                            <i class="uil uil-sign-out-alt align-middle h6"></i>
                                        </span> Logout
                                    </a>
                                </c:if>
                                <c:if test="${empty sessionScope.account.user_id}">
                                    <a class="dropdown-item text-dark" href="<c:url value='/loginnavigation' />">
                                        <span class="mb-0 d-inline-block me-1">
                                            <i class="uil uil-sign-in-alt align-middle h6"></i>
                                        </span> Login   
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </li>
                </ul>
                <!-- Start Dropdown -->
        
                <div id="navigation">
                    <!-- Navigation Menu-->   
                    <ul class="navigation-menu nav-left">
                        <li class="has-submenu parent-menu-item">
                            <a href="${pageContext.request.contextPath}/home">Home</a><span class="sub-menu-item"></span>
                        </li>

                        <li class="has-submenu parent-menu-item">
                            <a href="#">Blogs</a><span class="sub-menu-item"></span>
                        </li>

                        <li class="has-submenu parent-menu-item">
                            <a href="${pageContext.request.contextPath}/customer/customerlistService">Services</a><span class="sub-menu-item"></span>
                        </li>
                        
                        <li class="has-submenu parent-menu-item">
                            <a href="#">Your Cart</a><span class="sub-menu-item"></span>
                        </li>
        
                        <li><a href="${pageContext.request.contextPath}/nav/dashboard" class="sub-menu-item"">Admin</a></li>
                    </ul><!--end navigation menu-->
                </div><!--end navigation-->
            </div><!--end container-->
        </header>