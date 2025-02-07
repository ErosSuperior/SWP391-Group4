<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<header id="topnav" class="defaultscroll sticky">
    <div class="container">
        <!-- Logo container-->
        <a class="logo" href="index.html">
                    <span class="logo-light-mode">
                        <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png" class="l-dark" height="24" alt="">
                        <img src="<%= request.getContextPath() %>/assets/images/logo-light.png" class="l-light" height="24" alt="">
                    </span>
            <img src="<%= request.getContextPath() %>/assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
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
                <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-primary" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
                    <i class="uil uil-search"></i>
                </a>
            </li>

            <li class="list-inline-item mb-0 ms-1">
                <div class="dropdown dropdown-primary">
                    <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="<%= request.getContextPath() %>/assets/images/doctors/01.jpg" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                    <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                        <a class="dropdown-item d-flex align-items-center text-dark" href="doctor-profile.html">
                            <img src="<%= request.getContextPath() %>/assets/images/doctors/01.jpg" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                            <div class="flex-1 ms-2">
                                <span class="d-block mb-1">Calvin Carlo</span>
                                <small class="text-muted">Orthopedic</small>
                            </div>
                        </a>
                        <a class="dropdown-item text-dark" href="doctor-dashboard.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-dashboard align-middle h6"></i></span> Dashboard</a>
                        <a class="dropdown-item text-dark" href="doctor-profile-setting.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-setting align-middle h6"></i></span> Profile Settings</a>
                        <div class="dropdown-divider border-top"></div>
                        <a class="dropdown-item text-dark" href="login.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Logout</a>
                    </div>
                </div>
            </li>
        </ul>
        <!-- Start Dropdown -->

        <div id="navigation">
            <!-- Navigation Menu-->
            <ul class="navigation-menu nav-left nav-light">
                <li class="has-submenu parent-menu-item"><a href="javascript:void(0)">Home</a><span class="menu-arrow"></span>
                    <ul class="submenu">
                        <li><a href="${pageContext.request.contextPath}/customer/customerlistService" class="sub-menu-item">Index One</a></li>
                        <li><a href="index-two.html" class="sub-menu-item">Index Two</a></li>
                        <li><a href="index-three.html" class="sub-menu-item">Index Three</a></li>
                    </ul>
                </li>

                <li class="has-submenu parent-parent-menu-item">
                    <a href="javascript:void(0)">Doctors</a><span class="menu-arrow"></span>
                    <ul class="submenu">
                        <li class="has-submenu"><a href="javascript:void(0)">Dashboard</a><span class="submenu-arrow"></span>
                            <ul class="submenu">
                                <li><a href="doctor-dashboard.html">Dashboard</a></li>
                                <li><a href="doctor-appointment.html">Appointment</a></li>
                                <li><a href="patient-list.html">Patients</a></li>
                                <li><a href="doctor-schedule.html">Schedule Timing</a></li>
                                <li><a href="invoices.html">Invoices</a></li>
                                <li><a href="patient-review.html">Reviews</a></li>
                                <li><a href="doctor-messages.html">Messages</a></li>
                                <li><a href="doctor-profile.html">Profile</a></li>
                                <li><a href="doctor-profile-setting.html">Profile Settings</a></li>
                                <li><a href="doctor-chat.html">Chat</a></li>
                                <li><a href="login.html">Login</a></li>
                                <li><a href="signup.html">Sign Up</a></li>
                                <li><a href="forgot-password.html">Forgot Password</a></li>
                            </ul>
                        </li>
                        <li><a href="doctor-team-one.html">Doctors One</a></li>
                        <li><a href="doctor-team-two.html">Doctors Two</a></li>
                        <li><a href="doctor-team-three.html">Doctors Three</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/doctor-with-map-one.html">Doctors Four</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/doctor-with-map-two.html">Doctors Five</a></li>
                    </ul>
                </li>

                <li class="has-submenu parent-menu-item">
                    <a href="javascript:void(0)">Patients</a><span class="menu-arrow"></span>
                    <ul class="submenu">
                        <li><a href="patient-dashboard.html" class="sub-menu-item">Dashboard</a></li>
                        <li><a href="patient-profile.html" class="sub-menu-item">Profile</a></li>
                        <li><a href="booking-appointment.html" class="sub-menu-item">Book Appointment</a></li>
                        <li><a href="patient-invoice.html" class="sub-menu-item">Invoice</a></li>
                    </ul>
                </li>

                <li class="has-submenu parent-menu-item">
                    <a href="javascript:void(0)" class="menu-item">Pharmacy</a><span class="menu-arrow"></span>
                    <ul class="submenu">
                        <li><a href="pharmacy.html" class="sub-menu-item">Pharmacy</a></li>
                        <li><a href="pharmacy-shop.html" class="sub-menu-item">Shop</a></li>
                        <li><a href="pharmacy-product-detail.html" class="sub-menu-item">Medicine Detail</a></li>
                        <li><a href="pharmacy-shop-cart.html" class="sub-menu-item">Shop Cart</a></li>
                        <li><a href="pharmacy-checkout.html" class="sub-menu-item">Checkout</a></li>
                        <li><a href="pharmacy-account.html" class="sub-menu-item">Account</a></li>
                    </ul>
                </li>

                <li class="has-submenu parent-parent-menu-item"><a href="javascript:void(0)">Pages</a><span class="menu-arrow"></span>
                    <ul class="submenu">
                        <li><a href="aboutus.html"> About Us</a></li>
                        <li><a href="departments.html">Departments</a></li>
                        <li><a href="faqs.html">FAQs</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/review.html">Reviews</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/pricing.html">Pricing</a></li>
                        <li class="has-submenu"><a href="javascript:void(0)"> Blogs </a><span class="submenu-arrow"></span>
                            <ul class="submenu">
                                <li><a href="blogs.html">Blogs</a></li>
                                <li><a href="blog-detail.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/tears.html">Terms & Policy</a></li>
                        <li><a href="privacy.html">Privacy Policy</a></li>
                        <li><a href="error.html">404 !</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/blank-page.html">Blank Page</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/comingsoon.html">Comingsoon</a></li>
                        <li><a href="https://shreethemes.in/doctris/layouts/landing/maintenance.html">Maintenance</a></li>
                        <li><a href="contact.html">Contact</a></li>
                    </ul>
                </li>
            </ul><!--end navigation menu-->
        </div><!--end navigation-->
    </div><!--end container-->
</header><!--end header-->
