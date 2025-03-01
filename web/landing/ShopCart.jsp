<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctris - Doctor Appointment Booking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <style>
            .modal {
                display: none; /* Ẩn modal mặc định */
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.2); /* Màu nền mờ */
            }

            .modal-content {
                margin: 15% auto; /* Căn giữa modal */
                padding: 0;
                border: 1px solid #888;
                width: 50%; /* Chiều rộng modal */
                max-width: 500px; /* Chiều rộng tối đa */
            }
            .popup-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.1);
                z-index: 10000;
                pointer-events: all;
            }
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                z-index: 10001;
                width: 1000px;
                height: 500px;
                border: 2px solid #384AEB;
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
        <jsp:include page="CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Hero Start -->
        <section class="bg-half-170 d-table w-100 bg-light">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Shop Cart</h3>
                            <p class="para-desc mx-auto text-muted">Excellent service if you need immediate and effective assistance, emergency care, or a simple consultation for your family member.</p>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-light rounded mb-0 bg-transparent">
                                    <li class="breadcrumb-item active" aria-current="page">Shop Cart</li>
                                </ul>
                            </nav>
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
        <!-- Hero End -->

        <!-- Start -->
        <section class="section">
            <!--            Modal được ẩn đi lúc đầu. Chờ ng dùng bấm nút xóa sẽ hiện lên -->
            <div id="myModal" class="modal"> 
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #1BA8FF; color: #FFFFFF; padding: 10px; border-radius: 5px; display: flex; justify-content: space-between; align-items: center;">
                        <strong style="margin: 0;">Delete Your Service</strong>
                        <span class="nhom" onclick="closeModal()"  style=" font-size: 25px; margin-right: 10px; cursor: pointer;">&times;</span>
                    </div>
                    <div class="modal-body" style="padding: 10px; background-color: white; border-radius: 5px;">
                        <p style="color: #000; margin-bottom: 25px; ">Do you want to delete this service ?</p>
                        <div class="col-md-12 form-group" style="display: flex; justify-content: space-between;">
                            <button id="cancelBtn" onclick="closeModal()" style="border: 2px solid #1BA8FF; background-color: #FFFFFF; color: #1BA8FF; padding: 10px 20px; border-radius: 2px; cursor: pointer; font-size: 16px; width: 200px; transition: background-color 0.3s;">No</button>
                            <form action="mycart" method="POST">
                                <input type="hidden" name="delete_id" id="ServiceId" value="0">
                                <!--                                Trường dữ liệu ẩn được gửi xuống servlet-->
                                <button id="confirmBtn" style="border: 1px solid #FF424E; background-color: #FF424E; color: #FFFFFF; padding: 10px 20px; border-radius: 2px; cursor: pointer; font-size: 16px; width: 200px; transition: background-color 0.3s;">Yes</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="table-responsive bg-white shadow rounded">
                            <table class="table table-center table-padding mb-0">
                                <thead>
                                    <tr>
                                        <th class="border-bottom p-3" style="min-width:20px "></th>
                                        <th class="border-bottom p-3" style="min-width: 300px;">Service</th>
                                        <th class="border-bottom text-center p-3" style="min-width: 160px;">Price</th>
                                        <th class="border-bottom text-center p-3" style="min-width: 190px;">Qty</th>
                                        <th class="border-bottom text-end p-3" style="min-width: 50px;">Total</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <!-- Vòng for để in ra các item -->
                                    <c:forEach var="r" items="${listreservation}"> 
                                        <tr data-service-id="${r.service_id}">
                                            <td class="h5 p-3 text-center"><a onclick="openModal(${r.service_id});" style="cursor: pointer;" class="text-danger"><i class="uil uil-times"></i></a></td>
                                            <td class="p-3">
                                                <div class="d-flex align-items-center">
                                                    <img src="${r.image_link}" class="img-fluid avatar avatar-small rounded shadow" style="height:auto;" alt="">
                                                    <h6 class="mb-0 ms-3">
                                                        <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${r.service_id}" class="text-decoration-none">
                                                            ${r.service_title}
                                                        </a>
                                                        <div>
                                                            <small class="text-muted" style="display: block;" >ID: ${r.service_id}</small>
                                                            <small class="text-muted" style="display: block; margin-top: 4px;">${r.category_name}</small>
                                                        </div>
                                                    </h6>
                                                </div>
                                            </td>
                                            <td class="text-center p-3 service_price">$ ${r.price}</td>
                                            <td class="text-center shop-list p-3">
                                                <div class="qty-icons">
                                                    <button onclick="this.parentNode.querySelector('input[type=number]').stepDown(); this.parentNode.querySelector('input[type=number]').dispatchEvent(new Event('change'));" class="btn btn-icon btn-primary minus">-</button>
                                                    <input min="1" name="quantity" value="${r.quantity}" type="number" class="btn btn-icon btn-primary qty-btn quantity">
                                                    <button onclick="this.parentNode.querySelector('input[type=number]').stepUp(); this.parentNode.querySelector('input[type=number]').dispatchEvent(new Event('change'));" class="btn btn-icon btn-primary plus">+</button>

                                                </div>
                                            </td>
                                            <td class="text-end font-weight-bold p-3 subtotal">$ ${r.price * r.quantity}</td>
                                        </tr>                                    
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
                <div class="row">
                    <div class="col-lg-8 col-md-6 mt-4 pt-2">
                        <a href="${pageContext.request.contextPath}/customer/customerlistService" class="btn btn-primary">Shop More</a>
                    </div>
                    <div class="col-lg-4 col-md-6 ms-auto mt-4 pt-2">
                        <div class="table-responsive bg-white rounded shadow">
                            <table class="table table-center table-padding mb-0">
                                <tbody>
                                    <tr>
                                        <td class="h6 p-3">Subtotal</td>
                                        <td class="text-end font-weight-bold p-3" id="totalAmount0">$ 0</td>
                                    </tr>
                                    <tr>
                                        <td class="h6 p-3">Taxes</td>
                                        <td class="text-end font-weight-bold p-3">$ 0</td>
                                    </tr>
                                    <tr class="bg-light">
                                        <td class="h6 p-3">Total</td>
                                        <td class="text-end font-weight-bold p-3" id="totalAmount1">$ 0</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="mt-4 pt-2 text-end">
                            <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary">Proceed to checkout</a>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <!-- End -->
        <script>
            $(document).ready(function () {
                // Gọi hàm updateTotal để cập nhật tổng tiền ngay khi trang tải xong
                updateTotal();

                // Duyệt qua mỗi phần tử input có class "quantity" và lưu giá trị ban đầu của chúng
                $(".quantity").each(function () {
                    $(this).data("previous-quantity", $(this).val()); // Lưu giá trị ban đầu của quantity vào data của phần tử
                });

                // Lắng nghe sự thay đổi của input quantity
                $(".quantity").on("change", function () {
                    var quantity = $(this).val(); // Lấy giá trị quantity mới khi người dùng thay đổi
                    var row = $(this).closest("tr"); // Lấy hàng (tr) chứa input quantity
                    var service_id = row.data("service-id"); // Lấy service_id từ data của tr
                    var price = parseFloat(row.find(".service_price").text().replace('$', '').trim()); // Lấy giá của dịch vụ từ phần tử .service_price và chuyển đổi thành số
                    var previousQuantity = $(this).data("previous-quantity"); // Lấy quantity trước đó từ data của phần tử

                    // Gửi yêu cầu AJAX để cập nhật số lượng dịch vụ trên giỏ hàng
                    $.ajax({
                        url: "mycart", // Địa chỉ URL để gửi yêu cầu
                        method: "POST", // Phương thức POST
                        data: {
                            service_id: service_id, // Truyền service_id
                            quantity: quantity // Truyền quantity mới
                        },
                        success: function (response) {
                            // Nếu yêu cầu thành công và response.status là "1"
                            if (response.status === "1") {
                                var newSubtotal = quantity * price; // Tính lại subtotal (tổng tiền cho service này)
                                row.find(".subtotal").text("$ " + newSubtotal.toFixed(1)); // Cập nhật subtotal trong giao diện
                                row.find(".quantity").data("previous-quantity", quantity); // Cập nhật previous quantity với giá trị mới
                                updateTotal(); // Gọi lại hàm cập nhật tổng tiền
                            } else {
                                // Nếu có lỗi, khôi phục lại giá trị quantity cũ
                                row.find(".quantity").val(previousQuantity);
                            }
                        },
                        error: function (error) {
                            // Nếu có lỗi trong quá trình gửi AJAX, khôi phục lại giá trị quantity cũ
                            row.find(".quantity").val(previousQuantity);
                        }
                    });
                });

                // Hàm cập nhật tổng tiền
                function updateTotal() {
                    var total = 0; // Khởi tạo tổng tiền
                    // Duyệt qua tất cả các subtotal và tính tổng
                    $(".subtotal").each(function () {
                        total += parseFloat($(this).text().replace('$', '').trim()); // Cộng giá trị của mỗi subtotal vào tổng
                    });
                    // Cập nhật tổng tiền vào phần tử có id "totalAmount0"
                    $("#totalAmount0").text("$ " + total.toFixed(1));

                    var shippingCost = 0; // Phí vận chuyển, có thể cập nhật ở đây nếu có
                    total += shippingCost; // Cộng phí vận chuyển vào tổng

                    // Cập nhật tổng tiền vào phần tử có id "totalAmount1"
                    $("#totalAmount1").text("$ " + total.toFixed(1));
                }
            });

        </script>
        <script>
            // Hàm mở modal khi người dùng muốn xóa dịch vụ
            function openModal(serviceId) {
                // Lấy phần tử modal bằng ID "myModal"
                const modal = document.getElementById("myModal");

                // Lấy phần tử input để lưu serviceId vào trường hidden input với ID "ServiceId"
                const serviceField = document.getElementById("ServiceId");

                // Kiểm tra nếu modal và serviceField tồn tại
                if (modal && serviceField) {
                    // Gán giá trị của serviceId vào trường hidden input
                    serviceField.value = serviceId;

                    // Hiển thị modal bằng cách thay đổi style display thành "block"
                    modal.style.display = "block";
                }
            }

// Hàm đóng modal khi người dùng bấm nút "x" hoặc nút "No"
            function closeModal() {
                // Lấy phần tử modal bằng ID "myModal"
                const modal = document.getElementById("myModal");

                // Kiểm tra nếu modal tồn tại
                if (modal) {
                    // Ẩn modal bằng cách thay đổi style display thành "none"
                    modal.style.display = "none";
                }
            }
        </script>
        <!-- Start -->
        <footer class="bg-footer">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-4 mb-0 mb-md-4 pb-0 pb-md-2">
                        <a href="#" class="logo-footer">
                            <img src="${pageContext.request.contextPath}/assets/images/logo-light.png" height="22" alt="">
                        </a>
                        <p class="mt-4 me-xl-5">Great doctor if you need your family member to get effective immediate assistance, emergency treatment or a simple consultation.</p>
                    </div><!--end col-->

                    <div class="col-xl-7 col-lg-8 col-md-12">
                        <div class="row">
                            <div class="col-md-4 col-12 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                <h5 class="text-light title-dark footer-head">Company</h5>
                                <ul class="list-unstyled footer-list mt-4">
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> About us</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Services</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Team</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Project</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Blog</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Login</a></li>
                                </ul>
                            </div><!--end col-->

                            <div class="col-md-4 col-12 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                <h5 class="text-light title-dark footer-head">Departments</h5>
                                <ul class="list-unstyled footer-list mt-4">
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Eye Care</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Psychotherapy</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Dental Care</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Orthopedic</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Cardiology</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Gynecology</a></li>
                                    <li><a href="#" class="text-foot"><i class="mdi mdi-chevron-right me-1"></i> Neurology</a></li>
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
                                        <a href="tel:+152534-468-854" class="text-foot ms-2">+152 534-468-854</a>
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
                                <p class="mb-0"><script>document.write(new Date().getFullYear())</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/${pageContext.request.contextPath}/index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
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
                                <h4>Search now....</h4>
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
                    <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="24" class="light-version" alt="">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-light.png" height="24" class="dark-version" alt="">
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
                                    <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-dark-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-dark.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-dark.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                    <li class="d-grid"><a href="${pageContext.request.contextPath}/admin/index.html" target="_blank" class="mt-4"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Admin Dashboard</span></a></li>
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
                    <li class="list-inline-item mb-0"><a href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/${pageContext.request.contextPath}/index.html" target="_blank" class="rounded"><i class="uil uil-globe align-middle" title="website"></i></a></li>
                </ul><!--end icon-->
            </div>
        </div>
        <!-- Offcanvas End -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>
</html>
