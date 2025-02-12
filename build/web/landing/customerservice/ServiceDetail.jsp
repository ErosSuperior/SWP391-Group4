<%-- 
    Document   : ServiceDetail
    Created on : Feb 5, 2025, 3:45:40 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Service Detail</title>
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
        <!-- SLIDER -->
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/tiny-slider.css"/>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/slick.css"/>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/slick-theme.css"/>
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <style>
            .shop-image img {
                height: 300px;
                width: 100%;
                object-fit: cover; /* This will crop the image while maintaining aspect ratio */
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
        <!-- Loader -->

        <!-- Navbar STart -->
        <jsp:include page="../CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Hero Start -->
        <section class="bg-half-170 d-table w-100 bg-light" style="background:url('<%= request.getContextPath() %>/assets/images/bg/pharm01.jpg') center center;>
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">${highlightedService.serviceTitle}</h3>
                            <p class="para-desc mx-auto text-muted">We provide great service for baby and ensure their quality to the best</p>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-light rounded mb-0 bg-transparent">
                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li class="breadcrumb-item"><a href="pharmacy.html">Service List</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Service Detail</li>
                                </ul>
                            </nav>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <!--end section-->
        <div class="position-relative">
            <div class="shape overflow-hidden text-white">
                <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
                </svg>
            </div>
        </div>
        <!-- Hero End -->

        <section class="section">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-5">
                        <div class="slider slider-for">
                            <c:forEach var="image" items="${serviceImages}">
                                <div><img src="${pageContext.request.contextPath}/${image}" class="img-fluid rounded" alt="Service Image"></div>
                            </c:forEach>
                        </div>

                        <div class="slider slider-nav">
                            <c:forEach var="image" items="${serviceImages}">
                                <div><img src="${pageContext.request.contextPath}/${image}" class="img-fluid" alt="Service Thumbnail"></div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="col-md-7 mt-4 mt-sm-0 pt-2 pt-sm-0">
                        <div class="section-title ms-md-4">
                            <h4 class="title">${highlightedService.serviceTitle}</h4>
                            <h5 class="text-muted">
                                    <s class="text-danger">$${highlightedService.servicePrice}</s><br>
                                    <span class="text-danger"> $${highlightedService.serviceDiscount} Discount</span><br>
                                    <span class="text-success"> $${highlightedService.servicePrice - highlightedService.serviceDiscount}</span>
                            </h5>

                            <ul class="list-unstyled text-warning h5 mb-0">
                                <c:forEach var="i" begin="1" end="5">
                                    <li class="list-inline-item">
                                        <i class="mdi ${i <= highlightedService.serviceRateStar ? 'mdi-star' : 'mdi-star-outline'}"></i>
                                    </li>
                                </c:forEach>
                                <li class="list-inline-item me-2 h6 text-muted">${highlightedService.serviceRateStar}(${highlightedService.serviceVote} Ratings)</li>
                            </ul>

                            <h5 class="mt-4 py-2">Overview:</h5>
                            <p class="text-muted">${highlightedService.serviceDetail}</p>

                            <div class="mt-4 pt-2">
                                <a href="#" class="btn btn-primary">Order Now</a>
                                <a href="#" class="btn btn-soft-primary ms-2">Add to Cart</a>
                                <a href="#" class="btn btn-soft-primary ms-2">Feedback & Rating</a>
                            </div>
                        </div>
                    </div>
                </div><!--end row-->
            </div><!--end container-->

            <div class="container mt-100 mt-60">
                <div class="row">
                    <div class="col-12">
                        <h5 class="mb-0">Related Products:</h5>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12 mt-4 pt-2">
                        <div class="slider-range-four">
                            <c:forEach var="service" items="${relatedServices}">
                                <div class="tiny-slide">
                                    <div class="card shop-list border-0">
                                        <div class="shop-image position-relative overflow-hidden">
                                            <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}">
                                                <img src="${pageContext.request.contextPath}/${service.serviceImage}" class="img-fluid" alt="${service.serviceTitle}">
                                            </a>
                                        </div>
                                        <div class="card-body content pt-4 p-2">
                                            <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}" class="text-dark product-name h6">
                                                ${service.serviceTitle}
                                            </a>
                                            <div class="d-flex justify-content-between mt-1">
                                                <h6 class="text-muted small font-italic mb-0 mt-1">
                                                    <s class="text-danger">$${service.servicePrice}</s>  <!-- Strikethrough original price -->
                                                    <span class="text-success">$${service.servicePrice - service.serviceDiscount}</span> <!-- Final Price -->
                                                </h6>
                                                <ul class="list-unstyled text-warning mb-0">
                                                    <c:forEach var="i" begin="1" end="5">
                                                        <li class="list-inline-item">
                                                            <i class="mdi ${i <= service.serviceRateStar ? 'mdi-star' : 'mdi-star-outline'}"></i>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

        </section><!--end section-->

        <!-- Start -->
        <jsp:include page="../Footer.jsp"/>
        <!--end footer-->
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- Offcanvas Start -->
        <!-- Offcanvas End -->

        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider-init.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/slick.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/slick.init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
    </body>
</html>
