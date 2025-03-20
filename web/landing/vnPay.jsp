<%-- 
    Document   : MyReservation
    Created on : Feb 17, 2025, 10:07:26 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Children Care - Doctor Appointment Booking System</title>
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
        <!-- Select2 -->
        <link href="<%= request.getContextPath() %>/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/flatpickr.min.css">
        <link href="<%= request.getContextPath() %>/assets/css/jquery.timepicker.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

        <style>
            th span {
                color: #aaa;
                font-size: 0.8em;
                margin-left: 5px;
            }
        </style>
    </head>
    <body>
        <!-- Loader -->

        <!-- Loader -->

        <!-- Navbar STart -->
        <jsp:include page="CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Start Hero -->
        <section class="bg-dashboard">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5 col-12">
                        <jsp:include page="ProfileSidebar.jsp"/>
                    </div>

                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <h5 class="mb-0 pb-2">Payment</h5>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp1v7T287-ikP1m7dEUbs2n1SbbLEqkMd1ZA&s"/>
                        <form action="vnpay" id="frmCreateOrder" method="post">
                            <input type="text" value="${totalFinal*25000}" name="amount" hidden/>
                            <div class="rounded shadow mt-4">
                                <div class="p-4 border-bottom">
                                    <h5 class="mb-0">Total Amount: ${totalFinal}US / ${totalFinal*25000}VND</h5>
                                </div>
                            </div>

                            <div class="rounded shadow mt-4">
                                <div class="p-4 border-bottom">
                                    <h5 class="mb-0">First method:</h5>
                                    <h7 class="mb-0">Redirect to VNPAY Gateway to select a payment method.</h7>
                                </div>

                                <div class="p-4">
                                    <div class="d-flex justify-content-between pb-4">
                                        <h6 class="mb-0 fw-normal">VNPAY Gateway</h6>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="customSwitch5" name="bankCode" value="">
                                            <label class="form-check-label" for="customSwitch5"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="rounded shadow mt-4">
                                <div class="p-4 border-bottom">
                                    <h5 class="mb-0">Second method:</h5>
                                    <h7 class="mb-0">Separate the payment methods on the partner's site.</h7>
                                </div>

                                <div class="p-4">
                                    <div class="d-flex justify-content-between pb-4">
                                        <h6 class="mb-0 fw-normal">Pay with VNPAYQR-supported app</h6>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="customSwitch5" name="bankCode" value="VNPAYQR">
                                            <label class="form-check-label" for="customSwitch5"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-between py-4 border-top">
                                        <h6 class="mb-0 fw-normal">Pay via ATM card/Domestic account</h6>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="customSwitch6" name="bankCode" value="VNBANK">
                                            <label class="form-check-label" for="customSwitch6"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-between py-4 border-top">
                                        <h6 class="mb-0 fw-normal">Pay via International card</h6>
                                        <div class="form-check"> 
                                            <input type="radio" class="form-check-input" id="customSwitch7" name="bankCode" value="INTCARD">
                                            <label class="form-check-label" for="customSwitch7"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="rounded shadow mt-4">
                                <div class="p-4 border-bottom">
                                    <h5 class="mb-0">Language:</h5>
                                    <h7 class="mb-0">Choose language at the payment interface:</h7>
                                </div>

                                <div class="p-4">
                                    <div class="d-flex justify-content-between pb-4">
                                        <h6 class="mb-0 fw-normal">English</h6>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="customSwitch5" name="language" value="en">
                                            <label class="form-check-label" for="customSwitch5"></label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-between py-4 border-top">
                                        <h6 class="mb-0 fw-normal">Tiếng Việt</h6>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="customSwitch6" name="language" value="vn">
                                            <label class="form-check-label" for="customSwitch6"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="rounded shadow mt-4">
                                <div class="p-4 border-bottom">
                                    <button class="w-100 btn btn-primary" type="submit">Continue to checkout</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section><!-- End Hero -->

        <jsp:include page="Footer.jsp"/>

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Select2 -->
        <script src="<%= request.getContextPath() %>/assets/js/select2.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/select2.init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>

        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>

        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script>       
    </body>
</html>
