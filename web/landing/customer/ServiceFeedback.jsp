<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Feedback</title>
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

    </head>

    <body>
        <!-- Loader -->

        <!-- Loader -->

        <!-- Navbar STart -->
        <jsp:include page="../CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Start Hero -->
        <section class="bg-half-150 d-table w-100 bg-light">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Feedback so we can improve our service</h3>

                            <ul class="list-unstyled mt-4">
                                <li class="list-inline-item user text-muted me-2"><i class="mdi mdi-account"></i> Calvin Carlo</li>
                                <li class="list-inline-item date text-muted"><i class="mdi mdi-calendar-check"></i> 1st January, 2021</li>
                            </ul>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <div class="position-relative">
            <div class="shape overflow-hidden text-white">
                <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
                </svg>
            </div>
        </div>
        <!-- End Hero -->

        <!-- Start -->
        <section class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-lg-7">
                        <form action="${pageContext.request.contextPath}/customer/service/serviceFeedBack" method="get">
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
                                                        <c:forEach var="i" begin="1" end="5">
                                                            <li class="list-inline-item">
                                                                <i class="mdi ${i <= feed.rateStar ? 'mdi-star' : 'mdi-star-outline'}"></i>
                                                            </li>
                                                        </c:forEach>
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

                            <h5 class="card-title mt-4 mb-0">Leave A Comment :</h5>

                            <div class="mt-3">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Your Comment</label>
                                            <textarea id="message" placeholder="Your Comment" rows="5" name="message" class="form-control" required=""></textarea>
                                        </div>
                                    </div><!--end col-->

                                    <div class="col-md-12 d-flex justify-content-between align-items-center">
                                        <div class="send">
                                            <button type="submit" class="btn btn-primary">Send Message</button>
                                        </div>
                                        <div class="rate-product">
                                            <select class="form-select" aria-label="Rate Product">
                                                <option selected>Rate Product</option>
                                                <option value="5">⭐⭐⭐⭐⭐ (5 Stars)</option>
                                                <option value="4">⭐⭐⭐⭐ (4 Stars)</option>
                                                <option value="3">⭐⭐⭐ (3 Stars)</option>
                                                <option value="2">⭐⭐ (2 Stars)</option>
                                                <option value="1">⭐ (1 Star)</option>
                                            </select>
                                        </div>
                                    </div>

                                </div><!--end row-->
                            </div>    
                        </form><!--end form-->
                    </div><!--end col-->


                </div><!--end row-->
            </div><!--end container-->

            <div class="container mt-100 mt-60">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h4 class="title mb-0">Related Post:</h4>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <!-- End -->

        <!-- Start -->
        <jsp:include page="../Footer.jsp"/>
        <!-- End -->

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

    </body>

</html>