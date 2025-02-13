<%-- 
    Document   : ServiceList
    Created on : Jan 27, 2025, 4:56:29 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Service List</title>
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
            .shop-image img {
                width: 100%; /* Ensures the image fills the container */
                height: 300px; /* Fixed height for all images */
                object-fit: cover; /* Ensures the image keeps its aspect ratio without stretching */
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

        <!-- Start Hero -->
        <section class="home-slider position-relative">
            <div id="carouselExampleInterval" class="carousel slide carousel-fade" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="bg-half-170 d-table align-items-center w-100" style="background:url('<%= request.getContextPath() %>//assets/images/bg/pharm01.jpg') center center;">
                            <div class="bg-overlay bg-overlay-dark"></div>
                            <div class="container">
                                <div class="row mt-5">
                                    <div class="col-lg-12">
                                        <div class="heading-title">
                                            <h1 class="fw-bold mb-4">Baby Service <br> Best Service</h1>
                                            <p class="para-desc mb-0">We provide great service for baby and ensure their quality to the best</p>

                                        </div>
                                    </div><!--end col-->
                                </div><!--end row-->
                            </div><!--end container-->
                        </div>
                    </div>
                </div>
            </div>
        </section><!--end section-->
        <!-- End Hero -->

        <!-- Start -->
        <section class="section">
            <!-- Start Most Viewed Products -->

            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h5 class="mb-0">Browse Your Service</h5>
                    </div><!--end col-->
                </div><!--end row-->
                <form action="${pageContext.request.contextPath}/customer/customerlistService" method="get">
                    <div class="col-md-4">
                        <div class="mt-3">
                            <label class="form-label">Search</label>
                            <div class="input-group">
                                <input type="text" name="nameOrId" class="form-control"
                                       placeholder="Search by name or ID" value="${param.nameOrId}">
                                <button class="btn btn-primary" type="submit">Search</button>
                                <button class="btn btn-primary ms-2" type="button"
                                        onclick="window.location.href = '${pageContext.request.contextPath}/customer/customerlistService'">
                                    <i class="uil uil-redo"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mt-3">
                            <label class="form-label">Find by Price (Original Price)</label>
                            <div class="input-group">
                                <input type="number" id="minPrice" name="minPrice" class="form-control" placeholder="Min Price" value="${param.minPrice}">
                                <span class="input-group-text">to</span>
                                <input type="number" id="maxPrice" name="maxPrice" class="form-control" placeholder="Max Price" value="${param.maxPrice}">
                                <button class="btn btn-primary" type="submit">Apply</button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach var="service" items="${requestScope.allservices}">
                            <div class="col-lg-3 col-md-6 col-12 mt-4 pt-2">
                                <div class="card shop-list border-0">
                                    <div class="shop-image position-relative overflow-hidden rounded shadow">
                                        <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}">
                                            <img src="${service.serviceImage}" class="img-fluid" alt="${service.serviceTitle}">
                                        </a>
                                        <ul class="list-unstyled shop-icons">
                                            <li class="mt-2"><a href="#" class="btn btn-icon btn-pills btn-soft-warning"><i data-feather="message-circle" class="icons"></i></a></li>
                                            <li class="mt-2">
                                                <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-soft-primary" 
                                                   data-bs-toggle="modal" data-bs-target="#exampleModal"
                                                   data-detail="${service.serviceDetail}">
                                                    <i data-feather="eye" class="icons"></i>
                                                </a>
                                            </li>
                                            <li class="mt-2"><a href="#" class="btn btn-icon btn-pills btn-soft-warning"><i data-feather="shopping-cart" class="icons"></i></a></li>
                                        </ul>                                
                                    </div>
                                    <div class="card-body content pt-4 p-2">
                                        <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}" class="text-dark product-name h6">${service.serviceTitle}</a>
                                        <div class="d-flex justify-content-between mt-1">
                                            <h6 class="text-muted small font-italic mb-0 mt-1">
                                                <s class="text-danger">$${service.servicePrice}</s>  <!-- Strikethrough original price -->
                                                <span class="text-success">$${service.servicePrice - service.serviceDiscount}</span> <!-- Final Price -->
                                            </h6>
                                            <ul class="list-unstyled text-warning mb-0">
                                                <!-- Full Stars -->
                                                <c:forEach var="i" begin="1" end="${service.serviceRateStar - (service.serviceRateStar % 1)}">
                                                    <li class="list-inline-item"><i class="mdi mdi-star"></i></li>
                                                    </c:forEach>

                                                <!-- Half Star (if rating has decimal part >= 0.5) -->
                                                <c:if test="${service.serviceRateStar % 1 >= 0.5}">
                                                    <li class="list-inline-item"><i class="mdi mdi-star-half-full"></i></li>
                                                    </c:if>

                                                <!-- Empty Stars -->
                                                <c:forEach var="i" begin="1" end="${5 - (service.serviceRateStar - (service.serviceRateStar % 1)) - (service.serviceRateStar % 1 >= 0.5 ? 1 : 0)}">
                                                    <li class="list-inline-item"><i class="mdi mdi-star-outline"></i></li>
                                                    </c:forEach>
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div><!--end row-->

                    <div class="d-md-flex align-items-center text-center justify-content-between" style="margin-top: 25px">
                        <span class="text-muted me-3">Showing ${pageNo * pageSize + 1}
                            - ${totalElements.intValue() < ((pageNo + 1) * pageSize) ? totalElements.intValue() : ((pageNo + 1) * pageSize)} out of
                            ${totalElements.intValue()}</span>
                        <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                            <c:set var="totalPages" value="${(totalElements + pageSize - 1) div pageSize}" />
                            <li class="page-item ${pageNo <= 0 ? 'disabled' : ''}">
                                <button type="submit" name="pageNo" value="0" class="page-link">First</button>
                            </li>
                            <c:choose>
                                <c:when test="${pageNo >= 1}">
                                    <li class="page-item">
                                        <button type="submit" name="pageNo" value="${pageNo - 1}" class="page-link">${pageNo}</button>
                                    </li>
                                </c:when>
                            </c:choose>
                            <li class="page-item active">
                                <button type="submit" name="pageNo" value="${pageNo}" class="page-link" disabled>${pageNo + 1}</button>
                            </li>
                            <c:choose>
                                <c:when test="${(pageNo + 1).intValue() < totalPages.intValue()}">
                                    <li class="page-item">
                                        <button type="submit" name="pageNo" value="${pageNo + 1}" class="page-link">${pageNo + 2}</button>
                                    </li>
                                </c:when>
                            </c:choose>
                            <li class="page-item ${pageNo + 1 >= totalPages.intValue() ? 'disabled' : ''}">
                                <button type="submit" name="pageNo" value="${(totalPages - 1).intValue()}" class="page-link">Last</button>
                            </li>
                        </ul>
                    </div>
                </form>
            </div><!--end container-->

            <!--popup modal-->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header border-bottom p-3">
                            <h5 class="modal-title" id="exampleModalLabel">Service Details</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="modalServiceDetail">Loading...</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--end popup modal-->

            <!-- Popup Modal for Validation -->
            <div class="modal fade" id="priceValidationModal" tabindex="-1" aria-labelledby="priceValidationLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header border-bottom p-3">
                            <h5 class="modal-title" id="priceValidationLabel">Validation Error</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p id="modalValidationMessage">Invalid input.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Popup Modal for Validation -->

            <div class="container mt-100 mt-60">
                <div class="row">
                    <div class="col-12">
                        <h5 class="mb-0">Categories</h5>
                    </div><!--end col-->
                </div><!--end row-->

                <div class="row">
                    <div class="col-lg-12 mt-4 pt-2">
                        <div class="slider-range-four">
                            <c:forEach var="cat" items="${category}">
                                <div class="tiny-slide">
                                    <a href="${pageContext.request.contextPath}/customer/customerlistService?categoryId=${cat.categoryId}" class="card pharpachy-categories border-0 rounded overflow-hidden">
                                        <img src="${pageContext.request.contextPath}/${cat.categoryIcon}" class="img-fluid" alt="${cat.categoryTitle}">
                                        <div class="category-title">
                                            <span class="text-dark title-white">
                                                <span class="h5">${cat.categoryTitle}</span>
                                            </span>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->

            </div><!--end container-->

            <div class="container mt-100 mt-60">
                <div class="py-5 px-4 rounded shadow" >
                    <div class="row my-lg-5">
                        <div class="col-lg-12">
                            <div class="section-title">
                                <h1 class="title mb-4">Clinical Equipments <br> Stellar Price</h1>
                                <p class="para-desc mb-0">Great doctor if you need your family member to get effective immediate assistance, emergency treatment or a simple consultation.</p>

                                <div class="mt-4 pt-2">
                                    <a href="#" class="btn btn-primary">Shop now</a>
                                </div>
                            </div>
                        </div><!--end col-->
                    </div><!--end row-->
                </div>
            </div><!--end container-->

            <div class="container mt-100 mt-60">
                <div class="row">
                    <div class="col-12">
                        <h5 class="mb-0">Popular Products</h5>
                    </div><!--end col-->
                </div><!--end row-->

                <div class="row">
                    <c:forEach var="service" items="${bestservice}">
                        <div class="col-lg-3 col-md-6 col-12 mt-4 pt-2">
                            <div class="card shop-list border-0">
                                <ul class="label list-unstyled mb-0">
                                    <li><a href="javascript:void(0)" class="badge badge-pill badge-success">Popular</a></li>
                                </ul>
                                <div class="shop-image position-relative overflow-hidden rounded shadow">
                                    <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}">
                                        <img src="<c:out value='${service.serviceImage}'/>" class="img-fluid" alt="">
                                    </a>
                                    <ul class="list-unstyled shop-icons">
                                        <li><a href="#" class="btn btn-icon btn-pills btn-soft-danger"><i data-feather="heart" class="icons"></i></a></li>
                                        <li class="mt-2">
                                            <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-soft-primary" 
                                               data-bs-toggle="modal" data-bs-target="#exampleModal"
                                               data-detail="${service.serviceDetail}">
                                                <i data-feather="eye" class="icons"></i>
                                            </a>
                                        </li>
                                        <li class="mt-2"><a href="#" class="btn btn-icon btn-pills btn-soft-warning"><i data-feather="shopping-cart" class="icons"></i></a></li>
                                    </ul>
                                </div>
                                <div class="card-body content pt-4 p-2">
                                    <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}" class="text-dark product-name h6">
                                        <c:out value="${service.serviceTitle}" />
                                    </a>
                                    <div class="d-flex justify-content-between mt-1">
                                        <h6 class="text-muted small font-italic mb-0 mt-1">
                                            <s class="text-danger">$<c:out value="${service.servicePrice}" /></s> <!-- Original Price with Strike -->
                                            <span class="text-success">
                                                $<c:out value="${service.servicePrice - service.serviceDiscount}" />
                                            </span> <!-- Final Price After Discount -->
                                        </h6>

                                        <ul class="list-unstyled text-warning mb-0">
                                            <c:forEach var="i" begin="1" end="5">
                                                <li class="list-inline-item">
                                                    <i class="mdi mdi-star <c:if test='${i > service.serviceRateStar}'>mdi-star-outline</c:if>'"></i>
                                                    </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div><!--end col-->
                    </c:forEach>
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->

        <jsp:include page="../Footer.jsp"/>

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->



        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider-init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll('[data-bs-target="#exampleModal"]').forEach(button => {
                    button.addEventListener("click", function () {
                        let detailText = this.getAttribute("data-detail"); // Get serviceDetail from button
                        document.getElementById("modalServiceDetail").innerText = detailText; // Set modal content
                    });
                });
            });
        </script>
        <script>
            document.querySelector("form").addEventListener("submit", function (event) {
                let minPrice = parseFloat(document.getElementById("minPrice").value);
                let maxPrice = parseFloat(document.getElementById("maxPrice").value);

                if (!isNaN(minPrice) && !isNaN(maxPrice) && minPrice > maxPrice) {
                    event.preventDefault(); // Prevent form submission
                    document.getElementById("modalValidationMessage").textContent = "Min Price must be less than or equal to Max Price.";
                    var validationModal = new bootstrap.Modal(document.getElementById("priceValidationModal"));
                    validationModal.show();
                }
            });
        </script>
    </body>
</html>
