<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Doctris - Doctor Appointment Booking System</title>
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
        <!-- Loader -->
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Service List</h5>
                        </div>
                        <div class="row">
                            <form action="#" method="GET" class="row">
                                <div class="col-md-2">
                                    <div class="mt-2">
                                        <label class="form-label">Status</label>
                                        <select name="status" class="form-control">
                                            <option value="-1">All Status</option>
                                            <option value="1">Active</option>
                                            <option value="0">Inactive</option>
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
                                            <input type="text" name="nameOrId" class="form-control" placeholder="Search by name or ID">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/manager/ServicesList'">
                                                <i class="uil uil-redo"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-auto d-flex align-items-end">
                                    <button class="btn btn-primary btn-md" type="button"
                                            onclick="window.location.href = '${pageContext.request.contextPath}/manager/ServicesList/Add'">
                                        <i class="uil uil-plus"></i>
                                    </button>
                                </div>
                                <div class="col-12 mt-3">
                                    <div class="table-responsive shadow rounded">
                                        <table class="table table-sm fs-sm table-center bg-white mb-0 p-1">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3 text-center">Services Id</th>
                                                    <th class="border-bottom p-3 text-center">Thumbnail</th>
                                                    <th class="border-bottom p-3 text-center">Title</th>
                                                    <th class="border-bottom p-3 text-center">Category</th>
                                                    <th class="border-bottom p-3 text-center">List Price</th>
                                                    <th class="border-bottom p-3 text-center">Sale Price</th>
                                                    <th class="border-bottom p-3 text-center">Feature</th>
                                                    <th class="border-bottom p-3 text-center">Status</th>
                                                    <th class="border-bottom p-3 text-center"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="service" items="${services}">
                                                    <tr>
                                                        <td class="text-center">${service.serviceId}</td>
                                                        <td class="text-center"><img src="${service.serviceImage}" alt="Service Thumbnail" width="150"></td>
                                                        <td class="text-center">${service.serviceTitle}</td>
                                                        <td class="text-center">${service.categoryTitle}</td>
                                                        <td class="text-center">${service.serviceBi}</td>
                                                        <td class="text-center">${service.serviceBi}</td>
                                                        <td class="text-center">${service.serviceDetail}</td>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${service.serviceStatus == 1}">
                                                                    <span class="badge bg-success">Active</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="badge bg-danger">Inactive</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="text-center">
                                                            <a href="edit-service?id=${service.serviceId}" class="btn btn-sm btn-primary">Hide</a>
                                                            <a href="edit-service?id=${service.serviceId}" class="btn btn-sm btn-primary">Show</a>
                                                            <a href="edit-service?id=${service.serviceId}" class="btn btn-sm btn-primary">Edit</a>
                                                            <a href="delete-service?id=${service.serviceId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
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
                                        <a class="page-link" hr ef="?page=${totalPages}&nameOrId=${nameOrId}&status=${status}&sortBy=${sortBy}&sortDir=${sortDir}">Last</a>
                                    </li>
                                </ul>
                            </form>
                        </div>
                    </div>
                </div>

            </main>
        </div>
        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="<%= request.getContextPath() %>/assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
    </body>
</html>