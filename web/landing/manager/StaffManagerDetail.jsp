

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Post Management</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../../index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="../assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="<%= request.getContextPath() %>/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

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
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Staff Detail</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/StaffList">Staff List</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Staff Detail</li>
                                </ul>
                            </nav>
                        </div>
                        <div class="row">
                            <div class="col-lg-9 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <c:if test="${not empty staff_id}">
                                        <div class="row align-items-center">
                                            <div class="col-lg-2 col-md-4">
                                                <img src="${image}" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt="">
                                            </div><!--end col-->
                                            <div class="col-lg-5 col-md-8 text-center text-md-start mt-4 mt-sm-0">
                                                <h5 class="">${name}</h5>
                                            </div><!--end col-->
                                        </div><!--end row-->
                                    </c:if>
                                    <form class="mt-4" action="${pageContext.request.contextPath}/StaffEdits" method="post">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="mb-3">
                                                    <label class="form-label">First Name</label>
                                                    <input name="name" id="name" type="text" class="form-control" placeholder="First Name :" value="${name}" required="">
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-5">
                                                <div class="mb-3">
                                                    <label class="form-label">Email</label>
                                                    <c:if test="${empty staff_id}">
                                                        <input name="email" id="email" type="email" class="form-control" placeholder="Email :" value="${email}" required="">
                                                    </c:if>
                                                    <c:if test="${not empty staff_id}">
                                                        <input name="email" id="email" type="email" class="form-control" placeholder="Email :" value="${email}" readonly="" required="">
                                                    </c:if>
                                                </div> 
                                            </div><!--end col-->

                                            <div class="col-md-5">
                                                <div class="mb-3">
                                                    <label class="form-label">Phone no</label>
                                                    <c:if test="${empty staff_id}">
                                                        <input name="phone" id="number" type="number" class="form-control" placeholder="Phone :" value="${phone}" required="">
                                                    </c:if>
                                                    <c:if test="${not empty staff_id}">
                                                        <input name="phone" id="number" type="number" class="form-control" placeholder="Phone :" value="${phone}" readonly="" required="">
                                                    </c:if>
                                                </div>                                                                               
                                            </div><!--end col-->

                                            <div class="col-md-5">
                                                <div class="mb-3">
                                                    <label class="form-label">Address</label>
                                                    <input name="address" id="address" type="text" class="form-control" placeholder="Address :" value="${address}" required="">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-5">
                                                <div class="mb-3">
                                                    <label class="form-label">Gender</label>
                                                    <select class="form-control gender-name select2input" name="gender">
                                                        <option value="1" ${gender == '1' ? 'selected' : ''}>Male</option>
                                                        <option value="0" ${gender == '0' ? 'selected' : ''}>Female</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->

                                            <c:if test="${not empty errormess}">
                                                <p class="text-danger">${errormess}</p>
                                            </c:if>


                                            <c:if test="${empty staff_id}">
                                                <div class="col-md-12">
                                                    <button type="submit" name="submit" value="add" class="btn btn-primary col-md-3" style=" margin-left: 0px">Add Staff</button>
                                                    <button type="button" onclick="window.location.href = '${pageContext.request.contextPath}/StaffList'" class="btn btn-primary col-md-2" style=" margin-left: 10px">Cancel</button>
                                                </div>
                                            </c:if>

                                        </div><!--end row-->
                                    </form>
                                    <div class="row">
                                        <c:if test="${not empty staff_id}">
                                            <div class="col-md-12">
                                                <div class="col-md-6">
                                                    <div class="table-responsive shadow rounded">
                                                        <table class="table table-center bg-white mb-0">
                                                            <thead>
                                                                <tr>
                                                                    <th class="border-bottom p-3" style="min-width: 50px;">Specialization</th>
                                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                                        <button class="btn btn-icon btn-pills btn-soft-success" onclick="openConfirmationModal()">
                                                                            <i class="uil uil-plus-circle"></i>
                                                                        </button>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${listspec}" var="d">
                                                                    <tr>
                                                                        <th class="p-3">${d.category_name}</th>
                                                                        <td class="p-3">
                                                                            <a href="${pageContext.request.contextPath}/StaffDelSpec?category_id=${d.category_id}&staff_id=${staff_id}&name=${name}&email=${email}&phone=${phone}&address=${address}&gender=${gender}&image=${image}" 
                                                                               class="btn btn-icon btn-pills btn-soft-danger">
                                                                                <i class="uil uil-times-circle"></i>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->
                    </div>
                </div>

            </main>
        </div>

        <!-- Modal for confirmation -->
        <!-- Modal for confirmation -->
        <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmationModalLabel">Confirm Add Specialization</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="${pageContext.request.contextPath}/StaffAddSpec" method="get">
                        <div class="modal-body">
                            <p>Are you sure you want to assign a category?</p>
                            <input type="hidden" value="${staff_id}" name="staff_id">
                            <input type="hidden" value="${name}" name="name">
                            <input type="hidden" value="${email}" name="email">
                            <input type="hidden" value="${address}" name="address">
                            <input type="hidden" value="${phone}" name="phone">
                            <input type="hidden" value="${image}" name="image">
                            <c:choose>
                                <c:when test="${empty listnotspec}">
                                    <p>No Category Available</p>
                                </c:when>
                                <c:otherwise>
                                    <select name="category_id" id="categoryDropdown" class="form-control">
                                        <c:forEach var="category" items="${listnotspec}">
                                            <option value="${category.category_id}">${category.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>

                            <c:if test="${not empty listnotspec}">
                                <input type="submit" class="btn btn-primary" value="Confirm" name="submit">
                            </c:if>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <!-- Delete Confirmation Modal -->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure to delete this Spec?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <a id="confirmDelete" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="<%= request.getContextPath() %>/assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
        <!-- Pagination JS -->
        <script src="https://cdn.datatables.net/2.1.6/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/2.1.6/js/dataTables.bootstrap5.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                                                                            function openConfirmationModal() {
                                                                                var modal = new bootstrap.Modal(document.getElementById('confirmationModal'));
                                                                                modal.show();
                                                                            }
        </script>

    </body>
</html>
