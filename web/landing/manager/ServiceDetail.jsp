<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>ChildrenCare - Service Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Service Management System for ChildrenCare" />
    <meta name="keywords" content="Service, Management, System, Dashboard, ChildrenCare" />
    <meta name="author" content="YourTeam" />
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
    <!-- Icons -->
    <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
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
        <!-- Start Page Content -->
        <main class="page-content bg-light">
            <jsp:include page="../AdminNavbar.jsp"/>
            <div class="container-fluid">
                <div class="layout-specing">
                    <div class="d-md-flex justify-content-between">
                        <h5 class="mb-0">${serviceId != null ? 'Edit Service' : 'Add New Service'}</h5>
                        <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                            <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/manager/serviceList">ChildrenCare</a></li>
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/manager/serviceList">Service List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${serviceId != null ? 'Edit Service' : 'Add Service'}</li>
                            </ul>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-lg-8 mt-4">
                            <div class="card border-0 p-4 rounded shadow">
                                <!-- Hiển thị thông báo nếu có -->
                                <c:if test="${not empty message}">
                                    <div class="alert alert-info">${message}</div>
                                </c:if>

                                <!-- Form thêm/sửa dịch vụ -->
                                <form action="${pageContext.request.contextPath}/manager/serviceEdits" method="POST" enctype="multipart/form-data">
                                    <input type="hidden" name="serviceId" value="${serviceId}">
                                    <input type="hidden" name="submit" value="${serviceId != null ? 'edit' : 'add'}">

                                    <!-- Hiển thị ảnh hiện tại và tùy chọn upload ảnh mới -->
                                    <div class="row align-items-center">
                                        <div class="col-lg-2 col-md-4">
                                            <c:choose>
                                                <c:when test="${not empty serviceImage}">
                                                    <img src="${serviceImage}" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt="Service Image">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="${pageContext.request.contextPath}/assets/images/default_thumbnail.png" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt="No Image">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-lg-5 col-md-8 text-center text-md-start mt-4 mt-sm-0">
                                            <p class="text-muted mb-0">For best results, use an image at least 600px by 600px in either .jpg or .png format</p>
                                        </div>
                                        <div class="col-lg-5 col-md-12 text-lg-end text-center mt-4 mt-lg-0">
                                            <input type="file" name="serviceImage" accept="image/*" class="form-control">
                                        </div>
                                    </div>

                                    <div class="row mt-4">
                                        <!-- Title -->
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Title</label>
                                                <input name="title" id="title" type="text" class="form-control" value="${title != null ? title : ''}" required>
                                            </div>
                                        </div>

                                        <!-- Category -->
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Category</label>
                                                <select name="categoryId" class="form-control department-name select2input" required>
                                                    <option value="">Select Category</option>
                                                    <c:forEach var="category" items="${categories}">
                                                        <option value="${category.categoryId}" ${categoryId == category.categoryId ? 'selected' : ''}>${category.categoryTitle}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- List Price -->
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">List Price ($)</label>
                                                <input name="servicePrice" id="servicePrice" type="number" step="0.01" class="form-control" value="${servicePrice != null ? servicePrice : ''}" required>
                                            </div>
                                        </div>

<!--                                         Discount 
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Discount ($)</label>
                                                <input name="serviceDiscount" id="serviceDiscount" type="number" step="0.01" class="form-control" value="${serviceDiscount != null ? serviceDiscount : '0.00'}">
                                            </div>
                                        </div>-->

                                        <!-- Service Detail -->
                                        <div class="col-12">
                                            <div class="mb-3">
                                                <label class="form-label">Description</label>
                                                <textarea name="serviceDetail" class="form-control" rows="4">${serviceDetail != null ? serviceDetail : ''}</textarea>
                                            </div>
                                        </div>

                                        <!-- Status -->
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Status</label>
                                                <select name="serviceStatus" class="form-control" required>
                                                    <option value="1" ${serviceStatus == 1 ? 'selected' : ''}>Active</option>
                                                    <option value="0" ${serviceStatus == 0 ? 'selected' : ''}>Inactive</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-primary">${serviceId != null ? 'Update' : 'Add'} Service</button>
                                    <a href="${pageContext.request.contextPath}/manager/serviceList" class="btn btn-secondary">Cancel</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <!-- End page-content -->
        </div>
        <!-- page-wrapper -->

        <!-- Footer Start -->
        <footer class="bg-white shadow py-3">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col">
                        <div class="text-sm-start text-center">
                            <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear())</script> © ChildrenCare. Design with <i class="mdi mdi-heart text-danger"></i> by Group 4.</p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- End -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.init.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/flatpickr.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/flatpickr.init.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>
</html>