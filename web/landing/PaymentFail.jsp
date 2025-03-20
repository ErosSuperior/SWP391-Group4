<%-- 
    Document   : PaymentFail
    Created on : Mar 20, 2025, 1:51:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Failed</title>
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
            ._failed{
                border-bottom: solid 4px red !important;
            }
            ._failed i{
                color:red !important;
            }

            ._success {
                box-shadow: 0 15px 25px #00000019;
                padding: 45px;
                width: 100%;
                text-align: center;
                margin: 40px auto;
                border-bottom: solid 4px #28a745;
            }

            ._success i {
                font-size: 55px;
                color: #28a745;
            }

            ._success h2 {
                margin-bottom: 12px;
                font-size: 40px;
                font-weight: 500;
                line-height: 1.2;
                margin-top: 10px;
            }

            ._success p {
                margin-bottom: 0px;
                font-size: 18px;
                color: #495057;
                font-weight: 500;
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
        
        
        <div class="container" style=" margin-top: 200px; margin-bottom: 200px">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="message-box _success _failed">
                        <i class="fa fa-times-circle" aria-hidden="true"></i>
                        <h2> Your payment failed </h2>
                        <p>Error: ${status}</p> 

                    </div> 
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
