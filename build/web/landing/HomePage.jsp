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
        <footer class="bg-footer">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-4 mb-0 mb-md-4 pb-0 pb-md-2">
                        <a href="#" class="logo-footer">
                            <img src="<%= request.getContextPath() %>/assets/images/logo-light.png" height="22" alt="">
                        </a>
                        <p class="mt-4 me-xl-5">Great doctor if you need your family member to get effective immediate assistance, emergency treatment or a simple consultation.</p>
                    </div><!--end col-->

                    <div class="col-xl-7 col-lg-8 col-md-12">
                        <div class="row">
                            <div class="col-md-4 col-12 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                <h5 class="text-light title-dark footer-head">Company</h5>
                                <ul class="list-unstyled footer-list mt-4">
                                    <li><a href="aboutus.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> About us</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Services</a></li>
                                    <li><a href="doctor-team-two.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Team</a></li>
                                    <li><a href="blog-detail.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Project</a></li>
                                    <li><a href="blogs.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Blog</a></li>
                                    <li><a href="login.jsp" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Login</a></li>
                                </ul>
                            </div><!--end col-->

                            <div class="col-md-4 col-12 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                <h5 class="text-light title-dark footer-head">Departments</h5>
                                <ul class="list-unstyled footer-list mt-4">
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Eye Care</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Psychotherapy</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Dental Care</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Orthopedic</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Cardiology</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Gynecology</a></li>
                                    <li><a href="departments.html" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Neurology</a></li>
                                </ul>
                            </div><!--end col-->

                            <div class="col-md-4 col-12 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                <h5 class="text-light title-dark footer-head">Contact us</h5>
                                <ul class="list-unstyled footer-list mt-4">
                                    <li class="d-flex align-items-center">
                                        <i data-feather="mail" class="fea icon-sm text-foot align-middle"></i>
                                        <a href="mailto:contact@example.com" class="text-foot ms-2">contact@example.com</a>
                                    </li>

                                    <li class="d-flex align-items-center">
                                        <i data-feather="phone" class="fea icon-sm text-foot align-middle"></i>
                                        <a href="tel:+152534-468-854" class="text-foot ms-2">4</a>
                                    </li>

                                    <li class="d-flex align-items-center">
                                        <i data-feather="map-pin" class="fea icon-sm text-foot align-middle"></i>
                                        <a href="javascript:void(0)" class="video-play-icon text-foot ms-2">View on Google map</a>
                                    </li>
                                </ul>

                                <ul class="list-unstyled social-icon footer-social mb-0 mt-4">
                                    <li class="list-inline-item"><a href="#" class="rounded-pill"><i data-feather="facebook" class="fea icon-sm fea-social"></i></a></li>
                                    <li class="list-inline-item"><a href="#" class="rounded-pill"><i data-feather="instagram" class="fea icon-sm fea-social"></i></a></li>
                                    <li class="list-inline-item"><a href="#" class="rounded-pill"><i data-feather="twitter" class="fea icon-sm fea-social"></i></a></li>
                                    <li class="list-inline-item"><a href="#" class="rounded-pill"><i data-feather="linkedin" class="fea icon-sm fea-social"></i></a></li>
                                </ul><!--end icon-->
                            </div><!--end col-->
                        </div><!--end row-->
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->

            <div class="container mt-5">
                <div class="pt-4 footer-bar">
                    <div class="row align-items-center">
                        <div class="col-sm-6">
                            <div class="text-sm-start text-center">
                                <p class="mb-0"><script>document.write(new Date().getFullYear())</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="../../../index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
                            </div>
                        </div><!--end col-->

                        <div class="col-sm-6 mt-4 mt-sm-0">
                            <ul class="list-unstyled footer-list text-sm-end text-center mb-0">
                                <li class="list-inline-item"><a href="terms.html" class="text-foot me-2">Terms</a></li>
                                <li class="list-inline-item"><a href="privacy.html" class="text-foot me-2">Privacy</a></li>
                                <li class="list-inline-item"><a href="aboutus.html" class="text-foot me-2">About</a></li>
                                <li class="list-inline-item"><a href="contact.html" class="text-foot me-2">Contact</a></li>
                            </ul>
                        </div><!--end col-->
                    </div><!--end row-->
                </div>
            </div><!--end container-->
        </footer><!--end footer-->
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- Offcanvas Start -->
        <div class="offcanvas bg-white offcanvas-top" tabindex="-1" id="offcanvasTop">
            <div class="offcanvas-body d-flex align-items-center align-items-center">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="text-center">
                                <h4>Search now.....</h4>
                                <div class="subcribe-form mt-4">
                                    <form>
                                        <div class="mb-0">
                                            <input type="text" id="help" name="name" class="border bg-white rounded-pill" required="" placeholder="Search">
                                            <button type="submit" class="btn btn-pills btn-primary">Search</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div><!--end col-->
                    </div><!--end row-->
                </div><!--end container-->
            </div>
        </div>
        <!-- Offcanvas End -->

        <!-- Offcanvas Start -->
        <div class="offcanvas offcanvas-end bg-white shadow" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header p-4 border-bottom">
                <h5 id="offcanvasRightLabel" class="mb-0">
                    <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png" height="24" class="light-version" alt="">
                    <img src="<%= request.getContextPath() %>/assets/images/logo-light.png" height="24" class="dark-version" alt="">
                </h5>
                <button type="button" class="btn-close d-flex align-items-center text-dark" data-bs-dismiss="offcanvas" aria-label="Close"><i class="uil uil-times fs-4"></i></button>
            </div>
            <div class="offcanvas-body p-4 px-md-5">
                <div class="row">
                    <div class="col-12">
                        <!-- Style switcher -->
                        <div id="style-switcher">
                            <div>
                                <ul class="text-center list-unstyled mb-0">
                                    <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="<%= request.getContextPath() %>/assets/images/layouts/landing-light-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="<%= request.getContextPath() %>/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="<%= request.getContextPath() %>/assets/images/layouts/landing-dark-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="<%= request.getContextPath() %>/assets/images/layouts/landing-dark.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="<%= request.getContextPath() %>/assets/images/layouts/landing-dark.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="<%= request.getContextPath() %>/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                    <li class="d-grid"><a href="../admin/index.html" target="_blank" class="mt-4"><img src="<%= request.getContextPath() %>/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Admin Dashboard</span></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end Style switcher -->
                    </div><!--end col-->
                </div><!--end row-->
            </div>

            <div class="offcanvas-footer p-4 border-top text-center">
                <ul class="list-unstyled social-icon mb-0">
                    <li class="list-inline-item mb-0"><a href="https://1.envato.market/doctris-template" target="_blank" class="rounded"><i class="uil uil-shopping-cart align-middle" title="Buy Now"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://dribbble.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-dribbble align-middle" title="dribbble"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.facebook.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-facebook-f align-middle" title="facebook"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.instagram.com/shreethemes/" target="_blank" class="rounded"><i class="uil uil-instagram align-middle" title="instagram"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://twitter.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-twitter align-middle" title="twitter"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="mailto:support@shreethemes.in" class="rounded"><i class="uil uil-envelope align-middle" title="email"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="../../../index.html" target="_blank" class="rounded"><i class="uil uil-globe align-middle" title="website"></i></a></li>
                </ul><!--end icon-->
            </div>
        </div>
        <!-- Offcanvas End -->

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
