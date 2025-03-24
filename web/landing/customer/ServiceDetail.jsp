<%-- 
    Document   : ServiceDetail
    Created on : Feb 5, 2025, 3:45:40 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <section class="bg-half-170 d-table w-100 bg-light" style="background:url('<%= request.getContextPath() %>/assets/images/bg/pharm01.jpg') center center;">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">${highlightedService.serviceTitle}</h3>
                            <p class="para-desc mx-auto text-muted">We provide great service for baby and ensure their quality to the best</p>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-light rounded mb-0 bg-transparent">
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/customer/customerlistService">Service List</a></li>
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
                                <div><img src="${image}" class="img-fluid rounded" alt="Service Image"></div>
                                </c:forEach>
                        </div>

                        <div class="slider slider-nav">
                            <c:forEach var="image" items="${serviceImages}">
                                <div><img src="${image}" class="img-fluid" alt="Service Thumbnail"></div>
                                </c:forEach>
                        </div>
                    </div>

                    <div class="col-md-7 mt-4 mt-sm-0 pt-2 pt-sm-0">
                        <div class="section-title ms-md-4">
                            <h4 class="title">${highlightedService.serviceTitle}</h4>
                            <h5 class="text-muted">
                                Original Price: <s class="text-danger">$${highlightedService.servicePrice}</s><br>
                                Discount: <span class="text-danger"> $${highlightedService.serviceDiscount}</span><br>
                                Final Price: <span class="text-success"> $${highlightedService.servicePrice - highlightedService.serviceDiscount}</span>
                            </h5>

                            <ul class="list-unstyled text-warning h5 mb-0">
                                <c:forEach var="i" begin="1" end="5">
                                    <li class="list-inline-item">
                                        <i class="mdi ${i <= highlightedService.serviceRateStar ? 'mdi-star' : 'mdi-star-outline'}"></i>
                                    </li>
                                </c:forEach>
                                <li class="list-inline-item me-2 h6 text-muted">
                                    <fmt:formatNumber value="${highlightedService.serviceRateStar}" type="number" maxFractionDigits="2" /> 
                                    (${highlightedService.serviceVote} Ratings)
                                </li>

                            </ul>

                            <h5 class="mt-4 py-2">Overview:</h5>
                            <p class="text-muted">${highlightedService.serviceDetail}</p>

                            <form id="addToCartForm" action="${pageContext.request.contextPath}/customer/service/addToCart" method="GET">
                                <input type="text" name="serviceId" value="${serviceId}" hidden>
                                <div class="d-flex shop-list align-items-center">
                                    <h6 class="mb-0">Quantity:</h6>
                                    <div class="qty-icons ms-3">
                                        <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" class="btn btn-icon btn-primary minus">-</button>
                                        <input min="1" name="quantity" value="1" type="number" class="btn btn-icon btn-primary qty-btn quantity">
                                        <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="btn btn-icon btn-primary plus">+</button>
                                    </div>
                                </div>

                                <div class="d-flex align-items-center">
                                    <div>
                                        <h6 class="col-md-12">Select Date:</h6>
                                        <input type="date" name="reservation_date" class="col-md-12 form-control date-input" id="dateInput" 
                                               style="width: 200px; border: 2px solid #1274de; border-radius: 5px; padding: 5px;">
                                    </div>
                                </div>

                                <!-- Staff Selection Dropdown -->
                                <div class="mt-3">
                                    <h6 class="col-md-12">Choose Your Operator:</h6>
                                    <select name="selected_staff" class="form-control" style="width: 200px; border: 2px solid #1274de; border-radius: 5px; padding: 5px;">
                                        <c:forEach var="staff" items="${staffList}" varStatus="status">
                                            <option value="${staff.user_id}" ${status.first ? 'selected' : ''}>
                                                ${staff.user_fullname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Staff Image Preview -->
                                <div class="mt-2">
                                    <c:forEach var="staff" items="${staffList}" varStatus="status">
                                        <img src="${staff.user_image}" alt="${staff.user_fullname}" 
                                             class="staff-img ${status.first ? '' : 'd-none'}" 
                                             data-user-id="${staff.user_id}" 
                                             style="width: 100px; height: 100px; object-fit: cover; border-radius: 50%;">
                                    </c:forEach>
                                </div>


                                <div class="mt-4 pt-2">
                                    <button type="submit" id="addToCartButton" class="btn btn-primary">Add to Cart</button>
                                    <button type="button" class="btn btn-soft-primary ms-2" 
                                            onclick="window.location.href = '${pageContext.request.contextPath}/customer/service/serviceFeedBack?service_id=${serviceId}'">
                                        Feedback & Rating
                                    </button>

                                </div>
                            </form>
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
                                                <img src="${service.serviceImage}" class="img-fluid" alt="${service.serviceTitle}">
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

            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-lg-7">
                        <!-- Feedback List & Pagination (GET) -->
                        <form action="${pageContext.request.contextPath}/customer/customerdetailService" method="get">
                            <input type="hidden" name="serviceId" value="${serviceId}">
                            <h5 class="card-title mt-4 mb-0">Comments :</h5>

                            <ul class="media-list list-unstyled mb-0">
                                <c:forEach var="feed" items="${requestScope.allfeedback}">
                                    <li class="mt-4">
                                        <div class="d-flex justify-content-between">
                                            <div class="d-flex align-items-center">
                                                <a class="pe-3" href="#">
                                                    <img src="${feed.userImage}" class="img-fluid avatar avatar-md-sm rounded-circle shadow" alt="img">
                                                </a>
                                                <div class="commentor-detail">
                                                    <h6 class="mb-0">
                                                        <a href="javascript:void(0)" class="text-dark media-heading">${feed.name}</a>
                                                    </h6>
                                                    <small class="text-muted">${feed.createdDate}</small>

                                                    <!-- Rating Stars -->
                                                    <ul class="list-inline mb-0">
                                                        <c:if test="${feed.rateStar != 0}">
                                                            <c:forEach var="i" begin="1" end="5">
                                                                <li class="list-inline-item">
                                                                    <i class="mdi ${i <= feed.rateStar ? 'mdi-star' : 'mdi-star-outline'}"></i>
                                                                </li>
                                                            </c:forEach>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="mt-3">
                                            <p class="text-muted font-italic p-3 bg-light rounded">
                                                ${feed.content}
                                            </p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <!-- Pagination -->
                            <div class="d-md-flex align-items-center text-center justify-content-between" style="margin-top: 25px">
                                <span class="text-muted me-3">Showing ${pageNo * pageSize + 1}
                                    - ${totalElements.intValue() < ((pageNo + 1) * pageSize) ? totalElements.intValue() : ((pageNo + 1) * pageSize)} out of
                                    ${totalElements.intValue()}</span>
                                <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                    <c:set var="totalPages" value="${(totalElements + pageSize - 1) div pageSize}" />
                                    <li class="page-item ${pageNo <= 0 ? 'disabled' : ''}">
                                        <button type="submit" name="pageNo" value="0" class="page-link">First</button>
                                    </li>
                                    <c:if test="${pageNo >= 1}">
                                        <li class="page-item">
                                            <button type="submit" name="pageNo" value="${pageNo - 1}" class="page-link">${pageNo}</button>
                                        </li>
                                    </c:if>
                                    <li class="page-item active">
                                        <button type="submit" name="pageNo" value="${pageNo}" class="page-link" disabled>${pageNo + 1}</button>
                                    </li>
                                    <c:if test="${(pageNo + 1).intValue() < totalPages.intValue()}">
                                        <li class="page-item">
                                            <button type="submit" name="pageNo" value="${pageNo + 1}" class="page-link">${pageNo + 2}</button>
                                        </li>
                                    </c:if>
                                    <li class="page-item ${pageNo + 1 >= totalPages.intValue() ? 'disabled' : ''}">
                                        <button type="submit" name="pageNo" value="${(totalPages - 1).intValue()}" class="page-link">Last</button>
                                    </li>
                                </ul>
                            </div>
                        </form>

                        <!-- Feedback Submission Form (POST) -->
                        <form action="${pageContext.request.contextPath}/customer/service/updateRating" method="post" onsubmit="return validateFeedback()">
                            <h5 class="card-title mt-4 mb-0">Leave A Comment :</h5>

                            <div class="mt-3">
                                <div class="row">
                                    <div class="col-md-12">
                                        <c:if test="${not empty loggedin}">
                                            <p class="text-danger">Please Login/Register to access feedback</p>
                                        </c:if>

                                        <c:if test="${empty loggedin and empty purchased}">
                                            <p class="text-danger">Please purchase the service before leaving feedback</p>
                                        </c:if>

                                        <c:if test="${not empty purchased}">
                                            <input type="hidden" name="serviceId" value="${serviceId}">

                                            <!-- Comment Input -->
                                            <div class="mb-3">
                                                <label class="form-label">Your Comment</label>
                                                <textarea id="message" placeholder="Your Comment" rows="5" name="message" class="form-control"></textarea>
                                            </div>

                                            <!-- Rating Dropdown -->
                                            <div class="col-md-12 d-flex justify-content-between align-items-center">
                                                <div class="rate-product">
                                                    <select class="form-select" name="rateStar" id="rateStar">
                                                        <option value="-1" selected>Rate Product</option>
                                                        <option value="5">⭐⭐⭐⭐⭐ (5 Stars)</option>
                                                        <option value="4">⭐⭐⭐⭐ (4 Stars)</option>
                                                        <option value="3">⭐⭐⭐ (3 Stars)</option>
                                                        <option value="2">⭐⭐ (2 Stars)</option>
                                                        <option value="1">⭐ (1 Star)</option>
                                                    </select>
                                                </div>

                                                <!-- Submit Button -->
                                                <div class="send">
                                                    <button type="submit" class="btn btn-primary">Submit Feedback</button>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div><!--end col-->


                </div><!--end row-->
            </div><!--end container-->
            <!-- Notification Modal -->
            <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header border-bottom p-3">
                            <h5 class="modal-title" id="exampleModalLabel">Notification</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Please enter a comment or select a rating.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

        </section><!--end section-->

        <!-- Bootstrap Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered"> <!-- Center the modal -->
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Notification</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-center"> <!-- Center text inside the modal -->
                        <p class="text-success fw-bold">${cartmessage}</p> <!-- Green text and bold -->
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Start -->
        <jsp:include page="../Footer.jsp"/>
        <!--end footer-->
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

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

        <script>
            // Get tomorrow's date in YYYY-MM-DD format
            let tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            let formattedTomorrow = tomorrow.toISOString().split('T')[0];

            // Set the min and default value to tomorrow
            let dateInput = document.getElementById('dateInput');
            dateInput.setAttribute('min', formattedTomorrow);
            dateInput.value = formattedTomorrow;
        </script>

        <script>
            document.querySelector("select[name='selected_staff']").addEventListener("change", function () {
                let selectedId = this.value;
                document.querySelectorAll(".staff-img").forEach(img => {
                    img.classList.toggle("d-none", img.getAttribute("data-user-id") !== selectedId);
                });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var cartMessage = "${cartmessage}"; // Get cart message from JSP
                if (cartMessage && cartMessage.trim() !== "") {
                    var myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {});
                    myModal.show();
                }
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.getElementById("addToCartForm").addEventListener("submit", function (event) {
                    event.preventDefault(); // Prevent default form submission

                    let formData = new URLSearchParams(new FormData(this)).toString();
                    let url = this.action + "?" + formData;  // Use form's action attribute directly

                    console.log("Sending request to:", url); // Debugging: Check the URL being sent

                    fetch(url, {
                        method: "GET"
                    })
                            .then(response => response.json())
                            .then(data => {
                                console.log("Server response:", data); // Debugging: Check the server's response
                                if (data.status === "success") {
                                    document.getElementById("exampleModal").querySelector(".modal-body p").textContent = data.message;
                                    new bootstrap.Modal(document.getElementById('exampleModal')).show();
                                } else {
                                    alert("Failed to add to cart. " + data.message);
                                }
                            })
                            .catch(error => {
                                console.error("Error:", error);
                                alert("An error occurred. Please try again.");
                            });
                });
            });


        </script>

    </body>
</html>
