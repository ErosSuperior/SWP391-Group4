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
                            <div class="col-lg-12 col-md-7 mt-4">
                                <div class="card border-0 shadow overflow-hidden">
                                    <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded-0 shadow overflow-hidden bg-white mb-0" id="pills-tab" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link rounded-0" id="experience-tab">
                                                <div class="text-center pt-1 pb-1">
                                                    <h5 class=" mb-0">Profile</h5>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>

                                    <div class="tab-content p-4" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-experience" role="tabpanel" aria-labelledby="experience-tab">
                                            <h5 class="mb-0">Personal Information :</h5>
                                            <div class="col-lg-4 col-md-4 d-flex align-items-center">
                                                
                                                <div class="me-3">
                                                    <img src="${cus.user_image}" class="avatar avatar-md-md rounded-pill shadow" alt="">
                                                </div>
                                                
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
                                            </div>

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
                                                <c:choose>
                                                    <c:when test="${cus.user_status}">
                                                        <form action="customerdetail" method="POST">
                                                            <h5 class="mb-0 text-danger">Deactivating Account :</h5>
                                                            <input type="hidden" name="status" value="0">
                                                            <input type="hidden" name="user_id" value="${cus.user_id}">
                                                            <p class="mb-0 mt-4">Are you sure you want to deactivate this account? Press the "Deactivate" button below to proceed.</p>
                                                            <div class="mt-4">
                                                                <button type="submit" class="btn btn-danger">Deactivate</button>
                                                            </div>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form action="customerdetail" method="POST">
                                                            <h5 class="mb-0 text-success">Activating Account :</h5>
                                                            <input type="hidden" name="status" value="1">
                                                            <input type="hidden" name="user_id" value="${cus.user_id}">
                                                            <p class="mb-0 mt-4">Are you sure you want to activate this account? Press the "Activate" button below to proceed.</p>
                                                            <div class="mt-4">
                                                                <button type="submit" class="btn btn-success">Activate</button>
                                                            </div>
                                                        </form>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>                     
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
