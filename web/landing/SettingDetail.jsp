<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Children Care - Doctor Appointment Booking System</title>
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

        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="AdminSidebar.jsp"/>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <jsp:include page="AdminNavbar.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between d-print-none">
                            <h5 class="mb-0">Setting</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a >Settings</a></li>
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/adminsettinglist">Setting List</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Setting Detail</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row mt-4 justify-content-center">
                            <div class="col-lg-7">
                                <div class="card bg-white border-0 rounded shadow px-4 py-5">
                                    <span class="position-absolute top-0 end-0 mt-2 me-3 text-muted">
                                        <h3 style="color: #007bff;">#${settingdetail.setting_id}</h3>
                                    </span>
                                    <div class="row mb-4">
                                        <div class="col-lg-8 col-md-6">
                                            <h5 class="mt-4 pt-2">Setting Name:</h5>
                                            <small class="text-muted mb-0">${settingdetail.setting_name}</small>
                                        </div><!--end col-->

                                        <div class="col-lg-4 col-md-6 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                            <ul class="list-unstyled">
                                                <li class="d-flex mt-2">

                                                </li>
                                                <li class="d-flex mt-2">

                                                </li>
                                            </ul>
                                        </div><!--end col-->
                                    </div><!--end row-->

                                    <div class="pt-4 border-top">
                                        <div class="row">
                                            <div class="col-lg-8 col-md-6">
                                                <h5 class="text-muted fw-bold">Setting 
                                                    <c:choose>
                                                        <c:when test="${s.setting_status eq 'Active'}">
                                                            <span class="badge bg-soft-success rounded px-3 py-1">Active</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-soft-danger rounded px-3 py-1">Inactive</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </h5>
                                            </div><!--end col-->


                                            <div class="col-lg-4 col-md-6 mt-4 mt-sm-0 pt-2 pt-sm-0">
                                                <ul class="list-unstyled">

                                                    <li class="d-flex mt-2">
                                                        <small class="mb-0 text-muted">Setting Type: </small>
                                                        <strong class="mb-0 text-dark">&nbsp;&nbsp;${settingdetail.setting_type}</strong>
                                                    </li>

                                                    <li class="d-flex mt-2">
                                                    </li>

                                                    <li class="d-flex mt-2">
                                                    </li>
                                                </ul>
                                            </div><!--end col-->
                                        </div><!--end row-->

                                        <div class="invoice-table pb-4">
                                            <div class="table-responsive shadow rounded mt-4">
                                                <table class="table table-center table-bordered invoice-tb mb-0" style="border-radius: 5px;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="text-start border-bottom p-3" style="min-width: 200px;">Setting Description</th>
                                                            <th scope="col" class="text-start border-bottom p-3" style="min-width: 50px;">Value</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row" class="text-start p-3">${settingdetail.setting_description}</th>
                                                            <td class="text-start p-3">${settingdetail.setting_value}</td>
                                                        </tr>                                                      
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="row">
                                                <div style="margin-right: 50%; margin-top: 20px;"class="col-lg-6 col-md-5 ms-auto">
                                                    <!--                                                    Hiển thị thông báo cho người dùng-->
                                                    <c:if test="${not empty sessionScope.update}">
                                                        <div class="alert alert-success" role="alert" style="font-weight: bold;">
                                                            <strong>${sessionScope.update}</strong>
                                                        </div>
                                                        <% 
                                                            session.removeAttribute("update");  // Hiển thị xong xoa
                                                        %>
                                                    </c:if>
                                                </div><!--end col-->
                                            </div><!--end row-->
                                        </div>

                                        <div class="border-top pt-4">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="text-sm-start text-muted text-center">                                                        
                                                    </div>
                                                </div><!--end col-->

                                                <div class="col-sm-12">
                                                    <div class="text-sm-end text-muted text-center">
                                                        <a href="${pageContext.request.contextPath}/adminsettinglist" class="btn btn-soft-primary d-print-none"><i  class="uil uil-arrow-left"></i> Back</a>
                                                        <a href="${pageContext.request.contextPath}/adminsettingremove?setting_id=${settingdetail.setting_id}" class="btn btn-soft-primary d-print-none"><i  class="uil uil-trash"></i> Delete</a>
                                                        <a href="" class="btn btn-soft-primary d-print-none" data-bs-toggle="modal" data-bs-target="#view-invoice"><i class="uil uil-edit"></i> Change</a>
                                                    </div>
                                                </div><!--end col-->
                                            </div><!--end row-->
                                        </div>
                                    </div>
                                </div>

                                <div class="text-end mt-4">                                  
                                </div>
                            </div><!--end col-->
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
                        <h5 class="modal-title" id="exampleModalLabel">Edit Setting</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="card border-0 p-4 rounded shadow">
                        <form class="mt-4" action="adminsettingdetail" method="POST">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Setting Name</label>
                                        <input name="setting_name" value="${settingdetail.setting_name}" id="name" type="text" class="form-control" placeholder="Enter Name" required>
                                    </div>
                                </div><!--end col-->

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Setting Type</label>                                                  
                                        <select class="form-control department-name select2input" id="settingSelect" name="setting_type"required>                                                    
                                            <c:forEach var="t" items="${listtype}">
                                                <option value="${t}" <c:if test="${t == settingdetail.setting_type}">selected</c:if>>${t}</option>
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div><!--end col-->

                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label class="form-label">Setting Description</label>
                                        <textarea name="setting_description" id="comments" rows="3" class="form-control" placeholder="...">${settingdetail.setting_description}</textarea>
                                    </div>
                                </div>


                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Setting Value</label>
                                        <input name="setting_value" value="${settingdetail.setting_value}" id="name" type="text" class="form-control" placeholder="Enter Value" required>
                                    </div>
                                </div><!--end col-->

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Status</label>
                                        <select class="form-control gender-name select2input" name="setting_status">
                                            <option value="Active" <c:if test="${setting_status == 'Active'}">selected</c:if>>Active</option>
                                            <option value="Inactive" <c:if test="${setting_status == 'Inactive'}">selected</c:if>>Inactive</option>
                                            </select>
                                        </div>
                                    </div><!--end col-->
                                    <input type="hidden" name="setting_id" value="${settingdetail.setting_id}">
                            </div><!--end row-->                           
                            <button style="margin-top: 25px;" type="submit" class="btn btn-primary">Update</button> 
                        </form>
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
