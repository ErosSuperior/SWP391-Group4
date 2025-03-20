<%-- 
    Document   : Success
    Created on : Mar 16, 2025, 6:40:23 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
        <title>Success</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- SLIDER -->
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/tiny-slider.css"/>
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <style>
            body.custom-body {
                text-align: center;
                padding: 40px 0;
                background: #EBF0F5 url('https://childmind.org/wp-content/uploads/2016/02/What-to-Do-and-Not-Do-When-Children-Are-Anxious-1-e1677173213235.png') no-repeat center center;
                background-size: cover;
            }
            h1.custom-title {
                color: #473dd4;
                font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
                font-weight: 900;
                font-size: 40px;
                margin-bottom: 10px;
            }
            p.custom-text {
                color: #404F5E;
                font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
                font-size: 20px;
                margin: 0;
            }
            i.custom-checkmark {
                color: #473dd4;
                font-size: 100px;
                line-height: 200px;
                margin-left: -15px;
            }
            .custom-card {
                background: white;
                padding: 60px;
                border-radius: 4px;
                box-shadow: 0 2px 3px #C8D0D8;
                display: inline-block;
                margin: 0 auto;
            }
            .custom-button {
                background-color: #473dd4;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                font-size: 18px;
                margin: 10px 5px;
                transition: background-color 0.2s;
            }
            .custom-button:hover {
                background-color: #362bb8;
            }
        </style>
    </head>

    <body>
        <!-- Loader -->
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>

        <!-- Navbar Start -->
        <jsp:include page="CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <div class="d-flex justify-content-center" style="margin-top: 200px; margin-bottom: 200px">
            <div class="custom-card d-flex flex-column align-items-center text-center">
                <div style="border-radius: 200px; height: 200px; width: 200px; background: #F8FAF5; margin: 0 auto; display: flex; align-items: center; justify-content: center;">
                    <i class="custom-checkmark">✓</i>
                </div>
                <h1 class="custom-title">Success</h1>
                <c:if test="${empty successmess}">
                <p class="custom-text">We received your purchase request;<br/> we'll be in touch shortly!</p>
                </c:if>
                <c:if test="${not empty successmess}">
                <p class="custom-text">Payment Success<br/> thank you for using our service</p>
                </c:if>
                <div class="mt-4">
                    <a href="<%= request.getContextPath() %>/home" class="custom-button">Go to Home</a>
                    <a href="<%= request.getContextPath() %>/customer/myreservationlist" class="custom-button">View Orders</a>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"/>
        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider-init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
    </body>
</html>

