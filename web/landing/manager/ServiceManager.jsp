<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Doctris - Service Management</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Service Management System for ChildrenCare" />
        <meta name="keywords" content="Service, Management, System, Dashboard, ChildrenCare" />
        <meta name="author" content="YourTeam" />
        <!-- favicon -->
        <link rel="shortcut icon" href="../assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="${pageContext.request.contextPath}/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    </head>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Service List</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html">ChildrenCare</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Service List</li>
                                </ul>
                            </nav>
                        </div>
                        <div class="row">
                            <form action="${pageContext.request.contextPath}/manager/serviceList" method="GET" class="row">
                                <div class="col-md-2">
                                    <div class="mt-2">
                                        <label class="form-label">Status</label>
                                        <select name="status" class="form-control">
                                            <option value="-1" ${status == -1 ? 'selected' : ''}>All Status</option>
                                            <option value="1" ${status == 1 ? 'selected' : ''}>Active</option>
                                            <option value="0" ${status == 0 ? 'selected' : ''}>Inactive</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="mt-2">
                                        <label class="form-label">Sort By</label>
                                        <select name="sortBy" class="form-control">
                                            <option value="title" ${sortBy == 'title' ? 'selected' : ''}>Title</option>
                                            <option value="category" ${sortBy == 'category' ? 'selected' : ''}>Category</option>
                                            <option value="listPrice" ${sortBy == 'listPrice' ? 'selected' : ''}>List Price</option>
                                            <option value="salePrice" ${sortBy == 'salePrice' ? 'selected' : ''}>Sale Price</option>
                                            <option value="featured" ${sortBy == 'featured' ? 'selected' : ''}>Featured</option>
                                            <option value="status" ${sortBy == 'status' ? 'selected' : ''}>Status</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="mt-2">
                                        <label class="form-label">Order</label>
                                        <select name="sortDir" class="form-control">
                                            <option value="asc" ${sortDir == 'asc' ? 'selected' : ''}>Ascending</option>
                                            <option value="desc" ${sortDir == 'desc' ? 'selected' : ''}>Descending</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="mt-2">
                                        <label class="form-label">Search</label>
                                        <div class="input-group">
                                            <input type="text" name="nameOrId" class="form-control" placeholder="Search by name or ID" value="${nameOrId}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button" onclick="window.location.href='${pageContext.request.contextPath}/manager/serviceList'">
                                                <i class="uil uil-redo"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-auto d-flex align-items-end">
                                    <button class="btn btn-primary btn-md" type="button" onclick="window.location.href='${pageContext.request.contextPath}/manager/serviceAdd'">
                                        <i class="uil uil-plus"></i> Add Service
                                    </button>
                                </div>
                                <div class="col-12 mt-3">
                                    <c:if test="${not empty message}">
                                        <div class="alert alert-info">${message}</div>
                                    </c:if>
                                    <c:if test="${empty services}">
                                        <div class="alert alert-warning">No services found.</div>
                                    </c:if>
                                    <c:if test="${not empty services}">
                                        <div class="table-responsive shadow rounded">
                                            <table class="table table-sm fs-sm table-center bg-white mb-0 p-1">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3 text-center">Service ID</th>
                                                        <th class="border-bottom p-3 text-center">Thumbnail</th>
                                                        <th class="border-bottom p-3 text-center">Title</th>
                                                        <th class="border-bottom p-3 text-center">Category</th>
                                                        <th class="border-bottom p-3 text-center">List Price</th>
                                                        <th class="border-bottom p-3 text-center">Sale Price</th>
                                                        <th class="border-bottom p-3 text-center">Featured</th>
                                                        <th class="border-bottom p-3 text-center">Status</th>
                                                        <th class="border-bottom p-3 text-center">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="service" items="${services}">
                                                        <tr>
                                                            <td class="text-center">${service.serviceId}</td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${not empty service.serviceImage}">
                                                                        <img src="${service.serviceImage}" alt="Thumbnail" width="50" height="50">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="${pageContext.request.contextPath}/assets/images/default_thumbnail.png" alt="No Image" width="50" height="50">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center">${service.serviceTitle}</td>
                                                            <td class="text-center">${service.categoryTitle != null ? service.categoryTitle : 'Unknown'}</td>
                                                            <td class="text-center">$${service.servicePrice}</td>
                                                            <td class="text-center">$${service.servicePrice - service.serviceDiscount}</td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${service.serviceRateStar >= 4.5 || service.serviceVote >= 100}">
                                                                        <span class="badge bg-primary">Featured</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-secondary">Standard</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${service.serviceStatus == '1'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="text-center">
                                                                <a href="${pageContext.request.contextPath}/manager/serviceEdit?serviceId=${service.serviceId}" class="btn btn-sm btn-primary">
                                                                    <i class="uil uil-pen"></i> Edit
                                                                </a>
                                                                <a href="${pageContext.request.contextPath}/manager/ServiceDetail?serviceId=${service.serviceId}" class="btn btn-sm btn-info">
                                                                    <i class="uil uil-eye"></i> View
                                                                </a>
                                                                <!-- Uncomment nếu muốn thêm chức năng Delete -->
                                                                <!-- 
                                                                <a href="${pageContext.request.contextPath}/manager/serviceDelete?serviceId=${service.serviceId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this service?')">
                                                                    <i class="uil uil-trash-alt"></i> Delete
                                                                </a>
                                                                -->
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:if>
                                </div>
                                <c:if test="${not empty services}">
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=1&nameOrId=${nameOrId}&status=${status}&sortBy=${sortBy}&sortDir=${sortDir}">First</a>
                                        </li>
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage - 1}&nameOrId=${nameOrId}&status=${status}&sortBy=${sortBy}&sortDir=${sortDir}">Prev</a>
                                        </li>
                                        <li class="page-item active">
                                            <span class="page-link">${currentPage}</span>
                                        </li>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${currentPage + 1}&nameOrId=${nameOrId}&status=${status}&sortBy=${sortBy}&sortDir=${sortDir}">Next</a>
                                        </li>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="?page=${totalPages}&nameOrId=${nameOrId}&status=${status}&sortBy=${sortBy}&sortDir=${sortDir}">Last</a>
                                        </li>
                                    </ul>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>
</html>