m<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="<%= request.getContextPath() %>/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
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
        <!-- Loader -->

        <!-- Navbar STart -->
        <jsp:include page="CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Hero Start -->

        <div class="position-relative">
            <div class="shape overflow-hidden text-white">
                <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
                </svg>
            </div>
        </div>
        <!-- Hero End -->

        <!-- Start -->
        <section class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-6 mt-4 pt-2">
                        <a href="/SWP391-Group4/mycart" class="btn btn-sm btn-primary" style="margin-bottom:20px">
                            Back
                        </a>
                    </div>
                    <div class="col-md-5 col-lg-4 order-md-last">
                        <div class="card rounded shadow p-4 border-0">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <span class="h5 mb-0">Your cart</span>
                                <span class="badge bg-primary rounded-pill">
                                    <a href="${pageContext.request.contextPath}/mycart" class="text-white">${totalservice}</a>
                                </span>
                            </div>    
                            <ul class="list-group mb-3 border">
                                <!--                                Set giá trị total = 0 để cộng vào -->
                                <c:set var="total" value="0" />
                                <c:forEach var="r" items="${listreservation}">
                                    <c:set var="total" value="${total + (r.price * r.quantity)}" />
                                    <li class="d-flex justify-content-between lh-sm p-3 border-bottom">
                                        <div>
                                            <h6 class="my-0">
                                                <!--                                                Title của service và các thuộc tính khác -->
                                                <span style="font-weight: bold; color: #333;">${r.service_title}</span>
                                            </h6>
                                            <small style="color: #333; font-weight: bold; margin-right: 10px;">Qty: ${r.quantity}</small>
                                            <small style="color: #007bff; font-weight: bold;">|</small>
                                            <small style="color: #333; font-weight: bold; margin-left: 10px;">$${r.price}</small>

                                        </div>
                                        <span class="text-muted">$ ${r.price*r.quantity}</span> 
                                        <!--                                        Total sản phâm-->
                                    </li>
                                </c:forEach>
                                <li class="d-flex justify-content-between bg-light p-3 border-bottom">
                                    <div class="text-success">
                                        <h5 class="my-0">Total (USD)</h5>
                                    </div>
                                    <span class="text-success"><h5>$${total}</h5></span>
                                    <!--                                    Tổng tất cả sản phẩm -->
                                </li>
                            </ul>
                        </div>
                    </div><!--end col-->

                    <div class="col-md-7 col-lg-8">
                        <div class="card rounded shadow p-4 border-0">
                            <h5 class="mb-3">Billing address</h5>
                            <form class="needs-validation" action="checkout" method="POST" novalidate>
                                <div class="row g-3">
                                    <div class="col-sm-12">
                                        <label for="firstName" class="form-label">Name</label>
                                        <input type="text" name="name" class="form-control" id="firstName" placeholder="First Name" value="${sessionScope.account.user_fullname}"
                                               required>
                                        <div class="invalid-feedback">
                                            Valid name is required.
                                        </div>
                                    </div>
                                    <input type="hidden" name="total" value="${total}">
                                    <div class="col-12">
                                        <label for="phone" class="form-label">Phone Number</label>
                                        <div class="input-group has-validation">
                                            <input type="tel" name="phone" pattern="\+?[0-9\- ]{7,15}" class="form-control" id="phone" placeholder="Phone Number" value="${sessionScope.account.user_phone}"required>
                                            <div class="invalid-feedback"> Your phone number is required. </div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="email" class="form-label">Email </label>
                                        <input type="email" name="email" class="form-control" id="email" placeholder="you@example.com" value="${sessionScope.account.user_email}" required>
                                        <div class="invalid-feedback">
                                            Please enter a valid email address .
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="address" class="form-label">Address</label>
                                        <input type="text" name="address" class="form-control" id="address" placeholder="1234 Main St" value="${sessionScope.account.user_address}"
                                               required>
                                        <div class="invalid-feedback">
                                            Please enter your address.
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <label for="address" class="form-label">Note</label>
                                        <textarea type="text" name="note" class="form-control" id="note" placeholder="..."></textarea>
                                    </div>
                                </div>
                                <h5 class="mb-3 mt-4 pt-4 border-top">Payment</h5>

                                <div class="my-3">
                                    
                                </div>
                                <button class="w-100 btn btn-primary" type="submit">Continue to checkout</button>
                            </form>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <!-- End -->
        <jsp:include page="Footer.jsp"/>
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>
</html>
