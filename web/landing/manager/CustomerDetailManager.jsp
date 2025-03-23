<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="${pageContext.request.contextPath}/${pageContext.request.contextPath}/${pageContext.request.contextPath}/index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="${pageContext.request.contextPath}/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css --><link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

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

        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Customer Profile</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="patients.html">Customers</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Profile</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-6 col-md-7 mt-4">
                                <div class="card border-0 shadow overflow-hidden">
                                    <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded-0 shadow overflow-hidden bg-white mb-0" id="pills-tab" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link rounded-0" id="experience-tab">
                                                <div class="text-center pt-1 pb-1">
                                                    <h5 class=" mb-0">Profile</h5>
                                                </div>
                                            </a><!--end nav link-->
                                        </li><!--end nav item-->
                                    </ul>

                                    <div class="tab-content p-4" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-experience" role="tabpanel" aria-labelledby="experience-tab">
                                            <h5 class="mb-0">Personal Information :</h5>
                                            <div class="col-lg-4 col-md-4 d-flex align-items-center">
                                                <!-- Ảnh đại diện -->
                                                <div class="me-3">
                                                    <img src="${cus.user_image}" class="avatar avatar-md-md rounded-pill shadow" alt="">
                                                </div>

                                                <!-- Trạng thái người dùng -->
                                                <div class="ms-2 d-flex align-items-center">
                                                    <c:choose>
                                                        <c:when test="${cus.user_status}">
                                                            <span class="fw-bold text-success">
                                                                <i class="bi bi-check-circle me-1"></i> Active
                                                            </span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="fw-bold text-danger">
                                                                <i class="bi bi-x-circle me-1"></i> Inactive
                                                            </span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div><!--end col-->

                                            <form class="mt-4" action="customerdetail" method="POST">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="mb-3">
                                                            <label class="form-label">Full Name</label>
                                                            <input name="name" id="name" type="text" value="${cus.user_fullname}"class="form-control">
                                                        </div>
                                                    </div><!--end col-->

                                                    <div class="col-lg-6">
                                                        <div class="mb-3">
                                                            <label class="form-label">Gender</label>
                                                            <select name="gender" id="gender" class="form-control">
                                                                <option value="1" ${cus.user_gender == true ? "selected" : ""}>Male</option>
                                                                <option value="0" ${cus.user_gender == false ? "selected" : ""}>Female</option>
                                                            </select>
                                                        </div>
                                                    </div><!--end col-->

                                                    <div class="col-lg-6">
                                                        <div class="mb-3">
                                                            <label class="form-label">Email</label>
                                                            <input id="email" type="email" class="form-control" value="${cus.user_email}" readonly="">
                                                        </div> 
                                                    </div><!--end col-->

                                                    <div class="col-lg-6">
                                                        <div class="mb-3">
                                                            <label class="form-label">Phone no.</label>
                                                            <input name="phone" id="number" type="tel" class="form-control" value="${cus.user_phone}">
                                                        </div>                                                                               
                                                    </div><!--end col-->
                                                    <input type="hidden" name="user_id" value="${cus.user_id}">
                                                    <div class="col-md-12">
                                                        <div class="mb-3">
                                                            <label class="form-label">Address</label>
                                                            <textarea name="address" id="comments" rows="3" class="form-control" >${cus.user_address}</textarea>
                                                        </div>
                                                    </div>
                                                </div><!--end row-->

                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <input type="submit" id="submit" class="btn btn-primary" value="Save Changes">
                                                    </div><!--end col-->
                                                </div><!--end row-->
                                            </form><!--end form-->

                                            <div class="mt-4 pt-2">
                                                <form action="customeraction" method="POST">
                                                    <h5 class="mb-0 text-danger">Delete Account :</h5>
                                                    <input type="hidden" name="service" value="del">
                                                    <input type="hidden" name="user_id" value="${cus.user_id}">
                                                    <p class="mb-0 mt-4">Do you want to delete the account? Please press below "Delete" button</p>
                                                    <div class="mt-4">
                                                        <button type="submit" class="btn btn-danger">Delete Account</button>
                                                    </div><!--end col-->
                                                </form>
                                            </div>
                                        </div>
                                    </div>                     
                                </div>
                            </div><!--end col-->

                            <div class="col-lg-6 mt-4">
                                <div class="card rounded border-0 shadow">
                                    <div class="p-4 border-bottom">
                                        <h5 class="mb-0">Changes History</h5>
                                    </div>

                                    <div style="height: 664px; overflow-y: scroll;">
                                        <table class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Full Name</th>
                                                    <th>Gender</th>
                                                    <th>Phone</th>
                                                    <th>Address</th>
                                                    <th>Update Date</th>
                                                    <th>Update By</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listhistory}" var="l">
                                                    <tr>
                                                        <td>${l.history_id}</td>
                                                        <td>${l.user_fullname}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${l.user_gender eq true}">
                                                                    Male
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Female
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>${l.user_phone}</td>
                                                        <td>${l.user_address}</td>
                                                        <td>${l.updated_date}</td>
                                                        <td>${l.updated_by_name}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div><!--end row-->
                    </div>
                </div><!--end container-->

                <!-- Footer Start -->
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->

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
                                    <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/dark-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                    <li class="d-grid"><a href="${pageContext.request.contextPath}/landing/index.html" target="_blank" class="mt-4"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Landing Demos</span></a></li>
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

        <!-- View Invoice Start -->
        <div class="modal fade" id="view-invoice" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Patient Invoice</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <div class="row mb-4">
                            <div class="col-lg-8 col-md-6">
                                <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="22" alt="">
                                <h6 class="mt-4 pt-2">Address :</h6>
                                <small class="text-muted mb-0">1419 Riverwood Drive, <br>Redding, CA 96001</small>
                            </div><!--end col-->

                            <div class="col-lg-4 col-md-6 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                <ul class="list-unstyled">
                                    <li class="d-flex">
                                        <small class="mb-0 text-muted">Invoice no. : </small>
                                        <small class="mb-0 text-dark">&nbsp;&nbsp;#54638990jnn</small>
                                    </li>
                                    <li class="d-flex mt-2">
                                        <small class="mb-0 text-muted">Email : </small>
                                        <small class="mb-0">&nbsp;&nbsp;<a href="mailto:contact@example.com" class="text-dark">info@doctris.com</a></small>
                                    </li>
                                    <li class="d-flex mt-2">
                                        <small class="mb-0 text-muted">Phone : </small>
                                        <small class="mb-0">&nbsp;&nbsp;<a href="tel:+152534-468-854" class="text-dark">(+12) 1546-456-856</a></small>
                                    </li>
                                    <li class="d-flex mt-2">
                                        <small class="mb-0 text-muted">Website : </small>
                                        <small class="mb-0">&nbsp;&nbsp;<a href="#" class="text-dark">www.doctris.com</a></small>
                                    </li>
                                    <li class="d-flex mt-2">
                                        <small class="mb-0 text-muted">Patient Name : </small>
                                        <small class="mb-0">&nbsp;&nbsp;Mary Skeens</small>
                                    </li>
                                </ul>
                            </div><!--end col-->
                        </div><!--end row-->

                        <div class="pt-4 border-top">
                            <div class="row">
                                <div class="col-lg-8 col-md-6">
                                    <h5 class="text-muted fw-bold">Invoice <span class="badge badge-pill badge-soft-success fw-normal ms-2">Paid</span></h5>
                                    <h6>Surgery (Gynecology)</h6>
                                </div><!--end col-->

                                <div class="col-lg-4 col-md-6 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                    <ul class="list-unstyled">
                                        <li class="d-flex mt-2">
                                            <small class="mb-0 text-muted">Issue Dt. : </small>
                                            <small class="mb-0 text-dark">&nbsp;&nbsp;25th Sep. 2020</small>
                                        </li>

                                        <li class="d-flex mt-2">
                                            <small class="mb-0 text-muted">Due Dt. : </small>
                                            <small class="mb-0 text-dark">&nbsp;&nbsp;11th Oct. 2020</small>
                                        </li>

                                        <li class="d-flex mt-2">
                                            <small class="mb-0 text-muted">Dr. Name : </small>
                                            <small class="mb-0 text-dark">&nbsp;&nbsp;Dr. Calvin Carlo</small>
                                        </li>
                                    </ul>
                                </div><!--end col-->
                            </div><!--end row-->

                            <div class="invoice-table pb-4">
                                <div class="table-responsive shadow rounded mt-4">
                                    <table class="table table-center invoice-tb mb-0">
                                        <thead>
                                            <tr>
                                                <th scope="col" class="text-start border-bottom p-3" style="min-width: 60px;">No.</th>
                                                <th scope="col" class="text-start border-bottom p-3" style="min-width: 220px;">Item</th>
                                                <th scope="col" class="text-center border-bottom p-3" style="min-width: 60px;">Qty</th>
                                                <th scope="col" class="border-bottom p-3" style="min-width: 130px;">Rate</th>
                                                <th scope="col" class="border-bottom p-3" style="min-width: 130px;">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row" class="text-start p-3">1</th>
                                                <td class="text-start p-3">Hospital Charges</td>
                                                <td class="text-center p-3">1</td>
                                                <td class="p-3">$ 125</td>
                                                <td class="p-3">$ 125</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="text-start p-3">2</th>
                                                <td class="text-start p-3">Medicine</td>
                                                <td class="text-center p-3">1</td>
                                                <td class="p-3">$ 245</td>
                                                <td class="p-3">$ 245</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="text-start p-3">3</th>
                                                <td class="text-start p-3">Special Visit Fee(Doctor)</td>
                                                <td class="text-center p-3">1</td>
                                                <td class="p-3">$ 150</td>
                                                <td class="p-3">$ 150</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="row">
                                    <div class="col-lg-4 col-md-5 ms-auto">
                                        <ul class="list-unstyled h6 fw-normal mt-4 mb-0 ms-md-5 ms-lg-4">
                                            <li class="text-muted d-flex justify-content-between pe-3">Subtotal :<span>$ 520</span></li>
                                            <li class="text-muted d-flex justify-content-between pe-3">Taxes :<span> 0</span></li>
                                            <li class="d-flex justify-content-between pe-3">Total :<span>$ 520</span></li>
                                        </ul>
                                    </div><!--end col-->
                                </div><!--end row-->
                            </div>

                            <div class="border-top pt-4">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="text-sm-start text-muted text-center">
                                            <small class="mb-0">Customer Services : <a href="tel:+152534-468-854" class="text-warning">(+12) 1546-456-856</a></small>
                                        </div>
                                    </div><!--end col-->

                                    <div class="col-sm-6">
                                        <div class="text-sm-end text-muted text-center">
                                            <small class="mb-0"><a href="#" class="text-primary">Terms & Conditions</a></small>
                                        </div>
                                    </div><!--end col-->
                                </div><!--end row-->
                            </div>
                        </div>

                        <!-- <div class="text-end mt-4 pt-2">
                            <a href="javascript:window.print()" class="btn btn-soft-primary d-print-none"><i class="uil uil-print"></i> Print</a>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
        <!-- View Invoice End -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

    </body>
</html>
