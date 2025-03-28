<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="${pageContext.request.contextPath}/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="${pageContext.request.contextPath}/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flatpickr.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.min.css" rel="stylesheet" type="text/css" />
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
            <jsp:include page="../AdminSidebar.jsp"/>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-xl-9 col-lg-6 col-md-4">
                                <h5 class="mb-0">Customers</h5>
                                <nav aria-label="breadcrumb" class="d-inline-block mt-2">
                                    <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item active" aria-current="page">Customer List</li>
                                    </ul>
                                </nav>
                            </div><!--end col-->

                            <div class="col-xl-3 col-lg-6 col-md-8 mt-4 mt-md-0">
                                <div class="justify-content-md-end">
                                    <form action="customercontroller" method="POST">
                                        <div class="row justify-content-between align-items-center">
                                            <div class="col-sm-12 col-md-5">
                                                <div class="mb-0 position-relative">
                                                    <select class="form-control time-during select2input" name="status" onchange="this.form.submit();">
                                                        <option value="" ${empty status ? 'selected' : ''}>Status</option>
                                                        <option value="1" ${status == '1' ? 'selected' : ''}>Active</option>
                                                        <option value="0" ${status == '0' ? 'selected' : ''}>Inactive</option>
                                                    </select>
                                                </div>
                                                <input type="hidden" name="sort" value="${sort}">
                                                <input type="hidden" name="search" value="${search}">
                                                <input type="hidden" name="by" value="${by}">
                                            </div><!--end col-->
                                            <div class="col-sm-12 col-md-7 mt-4 mt-sm-0">
                                                <div class="d-grid">
                                                    <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#optionsform">Search & Sort</a>
                                                </div>
                                            </div><!--end col-->
                                        </div><!--end row-->
                                    </form><!--end form-->
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" style="min-width: 50px;">#</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Name</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Email</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Gender</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Mobile</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Address</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Status</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listcustomer}" var="c">
                                                <tr>
                                                    <th class="p-3">${c.user_id}</th>
                                                    <td class="p-3">
                                                        <a class="text-dark">
                                                            <div class="d-flex align-items-center">
                                                                <img src="${c.user_image}" class="avatar avatar-md-sm rounded-circle shadow" alt="">
                                                                <span class="ms-2">${c.user_fullname}</span>
                                                            </div>
                                                        </a>
                                                    </td>
                                                    <td class="p-3">${c.user_email}</td>
                                                    <td class="p-3">
                                                        <c:choose>
                                                            <c:when test="${c.user_gender == true}">
                                                                <span class="text-primary"><i class="uil uil-mars"></i> Male</span>
                                                            </c:when>
                                                            <c:when test="${c.user_gender == false}">
                                                                <span style="color: #e83e8c;"><i class="uil uil-venus"></i> Female</span>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td class="p-3">${c.user_phone}</td>
                                                    <td class="p-3">${c.user_address}</td>
                                                    <td class="p-3">
                                                        <c:choose>
                                                            <c:when test="${c.user_status == true}">
                                                                <span class="text-success"><i class="uil uil-check-circle"></i> Active</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="text-danger"><i class="uil uil-times-circle"></i> Inactive</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                    <td class="text-end p-3">
                                                        <a href="customerdetail?user_id=${c.user_id}" class="btn btn-icon btn-pills btn-soft-primary"  style="margin-right: 10px;"><i class="uil uil-eye"></i></a>
                                                            <c:choose>
                                                                <c:when test="${c.user_status == true}">
                                                                <button class="btn btn-icon btn-pills btn-soft-danger"
                                                                        data-category-id="${c.user_id}"
                                                                        onclick="showConfirmationModal(${c.user_id}, 0)"
                                                                        type="button" title="Deactivate">
                                                                    <i class="uil uil-ban"></i>
                                                                </button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <button class="btn btn-icon btn-pills btn-soft-success"
                                                                        data-category-id="${c.user_id}"
                                                                        onclick="showConfirmationModal(${c.user_id}, 1)"
                                                                        type="button" title="Activate">
                                                                    <i class="uil uil-check-circle"></i>
                                                                </button>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div><!--end row-->

                        <div class="row text-center">
                            <!-- PAGINATION START -->
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <span class="text-muted me-3"></span>
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <c:if test="${totalPages > 1}">
                                            <li class="page-item" style="${page == 1 ? 'display: none;' : ''}">
                                                <a class="page-link" href="${baseURL}${baseURL.contains('?') ? '&' : '?'}page=${page - 1}" aria-label="Previous">Prev</a>
                                            </li>

                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <li class="page-item ${page == i ? 'active' : ''}">
                                                    <a class="page-link" href="${baseURL}${baseURL.contains('?') ? '&' : '?'}page=${i}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <li class="page-item" style="${page == totalPages ? 'display: none;' : ''}">
                                                <a class="page-link" href="${baseURL}${baseURL.contains('?') ? '&' : '?'}page=${page + 1}" aria-label="Next">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div><!--end col-->
                            <!-- PAGINATION END -->
                        </div><!--end row-->
                    </div>
                </div><!--end container-->
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- Offcanvas End -->

        <!-- Modal start -->
        <!-- Add New Appointment Start -->
        <div class="modal fade" id="optionsform" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Advanced Search</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <form action="customercontroller" method="POST">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="mb-3">
                                        <label class="form-label">Search <span class="text-danger">*</span></label>
                                        <input name="search" id="name" type="text" class="form-control" value="${search}" placeholder="Search Keywords...">
                                    </div>
                                </div><!--end col-->

                                <div class="col-lg-4 col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Sort Criteria</label>
                                        <select class="form-control select2input" name="sort" >
                                            <option value="" ${empty sort ? "selected" : ""}>Select Sort</option>
                                            <option value="user_id" ${sort == "user_id" ? "selected" : ""}>ID</option>
                                            <option value="user_fullname" ${sort == "user_fullname" ? "selected" : ""}>Name</option>
                                            <option value="user_email" ${sort == "user_email" ? "selected" : ""}>Email</option>
                                            <option value="user_phone" ${sort == "user_phone" ? "selected" : ""}>Mobile</option>
                                            <option value="user_address" ${sort == "user_address" ? "selected" : ""}>Address</option>
                                            <option value="user_status" ${sort == "user_status" ? "selected" : ""}>Status</option>
                                        </select>
                                    </div>
                                </div><!--end col-->
                                <input type="hidden" name="status" value="${status}">
                                <div class="col-lg-4 col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Sort Order</label>
                                        <select class="form-control select2input" name="by" >
                                            <option value="" ${empty by ? "selected" : ""}>Select Order</option>
                                            <option value="ASC" ${by == "ASC" ? "selected" : ""}>ASC</option>
                                            <option value="DESC" ${by == "DESC" ? "selected" : ""}>DESC</option>
                                        </select>
                                    </div>
                                </div><!--end col-->

                                <div class="col-lg-12">
                                    <div class="d-grid gap-2" style="display: grid; grid-template-columns: 1fr 1fr;">
                                        <button type="button" onclick="window.location.href = 'customercontroller'" class="btn btn-primary">Reset </button>
                                        <button type="submit" class="btn btn-primary">Apply</button>
                                    </div>

                                </div><!--end col-->
                            </div><!--end row-->
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Add New Appointment End -->
        <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmationModalLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p id="confirmationModalMessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger" id="confirmActionButton"></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cancel Appointment End -->
        <!-- Modal end -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <!-- Select2 -->
        <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.init.js"></script>
        <!-- Datepicker -->
        <script src="${pageContext.request.contextPath}/assets/js/flatpickr.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/flatpickr.init.js"></script>
        <!-- Datepicker -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.timepicker.min.js"></script> 
        <script src="${pageContext.request.contextPath}/assets/js/timepicker.init.js"></script> 
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script>
                                            let currentUserId;
                                            let currentStatus;

                                            function showConfirmationModal(userId, status) {
                                                currentUserId = userId;
                                                currentStatus = status;

                                                let modalTitle = document.getElementById("confirmationModalLabel");
                                                let modalMessage = document.getElementById("confirmationModalMessage");
                                                let confirmButton = document.getElementById("confirmActionButton");

                                                if (status === 0) {
                                                    modalTitle.innerText = "Confirm Deactivation";
                                                    modalMessage.innerText = "Are you sure you want to deactivate this user?";
                                                    confirmButton.innerText = "Deactive";
                                                    confirmButton.className = "btn btn-danger";
                                                } else {
                                                    modalTitle.innerText = "Confirm Activation";
                                                    modalMessage.innerText = "Are you sure you want to activate this user?";
                                                    confirmButton.innerText = "Activate";
                                                    confirmButton.className = "btn btn-success";
                                                }

                                                $('#confirmationModal').modal('show');
                                            }

                                            $('#confirmActionButton').click(function () {
                                                changeStatusUser(currentUserId, currentStatus);
                                                $('#confirmationModal').modal('hide');
                                            });

                                            function changeStatusUser(userId, currentStatus) {
                                                $.ajax({
                                                    url: '${pageContext.request.contextPath}/customeraction',
                                                    type: 'POST',
                                                    data: {
                                                        userId: userId,
                                                        status: currentStatus,
                                                        service: "del"
                                                    },
                                                    success: function () {
                                                        location.reload();
                                                    },
                                                    error: function (xhr, status, error) {
                                                        console.error('AJAX Error:', xhr.status, status, error);
                                                    }
                                                });
                                            }
        </script>
    </body>
</html>
