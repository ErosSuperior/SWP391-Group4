<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Home Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../../index.html" />
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" integrity="sha512-wR4oNhLBHf7smjy0K4oqzdWumd+r5/+6QO/vDda76MW5iug4PT7v86FoEkySIJft3XA0Ae6axhIvHrqwm793Nw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" integrity="sha512-6lLUdeQ5uheMFbWm3CP271l14RsX1xtx+J5x2yeIDkkiBpeVTNhTqijME7GgRKKi6hCqovwCoBTlRBEC20M8Mg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/slick.css"/>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/slick-theme.css"/>

    </head>

    <style>
        .slider {
            max-width: 80%;
            margin: auto;
            padding: 20px;
        }

        .slick-slider {
            width: 100%;
        }

        .slider-item {
            position: relative;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .slider-image {
            width: 100%;
            height: 700px;
            object-fit: cover;
            border-radius: 10px;
        }

        .slider-overlay {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 100%;
            text-align: center;
            background: rgba(0, 0, 0, 0.6);
            color: white;
            padding: 20px;
            border-radius: 10px;
        }

        .slider-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .slider-note {
            font-size: 16px;
            opacity: 0.9;
        }
        .slick-prev.slick-arrow,
        .slick-next.slick-arrow {
            position: absolute;
            z-index: 100;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            opacity: 0.8;
            transition: opacity 0.3s ease, transform 0.2s ease;
        }


        .slick-prev::before,
        .slick-next::before {
            font-size: 30px;
            font-weight: bold;
            color: white;
        }

        .slick-prev {
            left: 10px;
        }

        .slick-next {
            right: 10px;
        }

        .blog-title {
            text-align: center;
            font-size: 2.5rem;
            font-weight: bold;
            margin-bottom: 30px;
            color: #007bff;
            text-transform: uppercase;
            letter-spacing: 2px;
        }
        .card {
            transition: 0.3s;
            border-radius: 10px;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
        }
        .card img {
            border-radius: 10px 10px 0 0;
            height: 200px;
            object-fit: cover;
        }
    </style>
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

        <!-- Start Hero -->
        <section class="bg-half-260 d-table w-100" style="background: url('<%= request.getContextPath() %>/assets/images/bg/01.jpg') center;">
            <!--<section class="bg-half-260 d-table w-100">-->
            <div class="bg-overlay bg-overlay-dark"></div>

            <div class="container slider">
                <div class="slick-slider">
                    <c:forEach items="${requestScope.sliders}" var="s">
                        <div class="slider-item">
                            <img src="${s.image}" alt="alt" class="slider-image"/>
                            <div class="slider-overlay">
                                <h3 class="slider-title">${s.title}</h3>
                                <p class="slider-note">${s.note}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>


            <!--end container-->
        </section><!--end section-->
        <!-- End Hero -->

        <!-- Start -->
        <section class="section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-10">
                        <div class="features-absolute bg-white shadow rounded overflow-hidden card-group">
                            <div class="card border-0 bg-light p-4">
                                <i class="ri-heart-pulse-fill text-primary h2 mb-0"></i>
                                <h5 class="mt-1">Emergency Cases</h5>
                                <p class="text-muted mt-2">This is required when, for example, the is not yet available. Dummy text is also known as 'fill text'.</p>
                                <a href="departments.html" class="text-primary">Read More <i class="ri-arrow-right-line align-middle"></i></a>
                            </div>

                            <div class="card border-0 p-4">
                                <i class="ri-dossier-fill text-primary h2 mb-0"></i>
                                <h5 class="mt-1">Doctors Timetable</h5>
                                <p class="text-muted mt-2">This is required when, for example, the is not yet available. Dummy text is also known as 'fill text'.</p>
                                <a href="departments.html" class="text-primary">Read More <i class="ri-arrow-right-line align-middle"></i></a>
                            </div>

                            <div class="card border-0 bg-light p-4">
                                <i class="ri-time-fill text-primary h2 mb-0"></i>
                                <h5 class="mt-1">Opening Hours</h5>
                                <ul class="list-unstyled mt-2">
                                    <li class="d-flex justify-content-between">
                                        <p class="text-muted mb-0">Monday - Friday</p>
                                        <p class="text-primary mb-0">8.00 - 20.00</p>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <p class="text-muted mb-0">Saturday</p>
                                        <p class="text-primary mb-0">8.00 - 18.00</p>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <p class="text-muted mb-0">Sunday</p>
                                        <p class="text-primary mb-0">8.00 - 14.00</p>
                                    </li>
                                </ul>
                                <a href="departments.html" class="text-primary">Read More <i class="ri-arrow-right-line align-middle"></i></a>
                            </div>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->

            <!-- Các nội dung HTML khác của trang... -->
        </section>
        <!-- End -->
        <section class="section" style="padding-top: 0px">
            <div class="container">
                <h2 class="blog-title">Blog List</h2>
                <div class="row">
                    <c:forEach items="${requestScope.blogs}" var="b">
                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <img src="${b.blogImage}" class="card-img-top" alt="Blog Image">
                                <div class="card-body">
                                    <h5 class="card-title">${b.blogTitle}</h5>
                                    <p class="card-text text-muted">Tác giả: <strong>${b.authorName}</strong></p>
                                    <p class="card-text">${b.blogDetail}</p>
                                    <p class="text-muted"><small>Ngày đăng: ${b.blogCreatedDate}</small></p>
                                    <a href="#" class="btn btn-primary">Đọc thêm</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <section class="section" style="padding-top: 0px">
            <div class="container">
                <h2 class="blog-title">Service List</h2>
                <div class="row">
                    <c:forEach var="service" items="${services}">
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <img src="${service.serviceImage}" class="card-img-top" alt="Service Image" style="height: 200px; object-fit: cover;">
                                <div class="card-body">
                                    <h5 class="card-title">${service.serviceTitle}</h5>
                                    <p class="card-text">${service.serviceDetail}</p>
                                    <p><strong>Rate:</strong> ${service.serviceRateStar} ⭐</p>
                                    <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${service.serviceId}" class="btn btn-primary">View Service</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- Partners start -->
        <section class="py-4 bg-light">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-2 col-md-2 col-6 text-center py-4">
                        <img src="<%= request.getContextPath() %>/assets/images/client/amazon.png" class="avatar avatar-client" alt="">
                    </div><!--end col-->

                    <div class="col-lg-2 col-md-2 col-6 text-center py-4">
                        <img src="<%= request.getContextPath() %>/assets/images/client/google.png" class="avatar avatar-client" alt="">
                    </div><!--end col-->

                    <div class="col-lg-2 col-md-2 col-6 text-center py-4">
                        <img src="<%= request.getContextPath() %>/assets/images/client/lenovo.png" class="avatar avatar-client" alt="">
                    </div><!--end col-->

                    <div class="col-lg-2 col-md-2 col-6 text-center py-4">
                        <img src="<%= request.getContextPath() %>/assets/images/client/paypal.png" class="avatar avatar-client" alt="">
                    </div><!--end col-->

                    <div class="col-lg-2 col-md-2 col-6 text-center py-4">
                        <img src="<%= request.getContextPath() %>/assets/images/client/shopify.png" class="avatar avatar-client" alt="">
                    </div><!--end col-->

                    <div class="col-lg-2 col-md-2 col-6 text-center py-4">
                        <img src="<%= request.getContextPath() %>/assets/images/client/spotify.png" class="avatar avatar-client" alt="">
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <!-- Partners End -->

        <!-- Start -->
        <jsp:include page="Footer.jsp"/>
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->


        <!-- MOdal Start -->
        <div class="modal fade" id="watchvideomodal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content video-modal rounded overflow-hidden">
                    <div class="ratio ratio-16x9">
                        <iframe src="https://player.vimeo.com/video/99025203" title="Vimeo video" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </div>
        <!-- MOdal End -->

        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- SLIDER -->
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/tiny-slider-init.js"></script>
        <!-- Counter -->
        <script src="<%= request.getContextPath() %>/assets/js/counter.init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" integrity="sha512-HGOnQO9+SP1V92SrtZfjqxxtLmVzqZpjFFekvzZVWoiASSQgSr4cw9Kqd2+l8Llp4Gm0G8GIFJ4ddwZilcdb8A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
        <script>
                                        $('.slick-slider').slick({
                                            infinite: true,
                                            speed: 1000,
                                            autoplay: true,
                                            autoplaySpeed: 1000,
                                        });
        </script>
    </body>
</html>
