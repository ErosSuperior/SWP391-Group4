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
                            <li class="list-inline-item me-2 h6 text-muted">${highlightedService.serviceRateStar}(${highlightedService.serviceVote} Ratings)</li>
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

    <script>
        // Get today's date in YYYY-MM-DD format
        let today = new Date().toISOString().split('T')[0];

        // Set the min and default value to today
        let dateInput = document.getElementById('dateInput');
        dateInput.setAttribute('min', today);
        dateInput.value = today;
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
