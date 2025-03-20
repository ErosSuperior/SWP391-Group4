<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Reservation Management</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo.png"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/remixicon.css" type="text/css"/>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.min.css" type="text/css"/>
    </head>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <h5 class="mb-0">Reservation Management</h5>
                        <nav aria-label="breadcrumb" class="mt-2">
                            <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Reservation List</li>
                            </ul>
                        </nav>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card rounded shadow">
                                    <div class="p-4 border-bottom">
                                        <h5 class="mb-0">Reservation List</h5>
                                    </div>
                                    <div class="p-4">
                                        <form action="${pageContext.request.contextPath}/admin/reservationList" method="GET" class="row">
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label>Reservation ID</label>
                                                    <input type="text" name="search" class="form-control" value="${search}" placeholder="Enter Reservation ID"/>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label>Status</label>
                                                    <select name="status" class="form-control">
                                                        <option value="-1" ${status == -1 ? 'selected' : ''}>All</option>
                                                        <option value="0" ${status == 0 ? 'selected' : ''}>Pending</option>
                                                        <option value="1" ${status == 1 ? 'selected' : ''}>Confirmed</option>
                                                        <option value="2" ${status == 2 ? 'selected' : ''}>Cancelled</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label>Payment Status</label>
                                                    <select name="paymentStatus" class="form-control">
                                                        <option value="-1" ${paymentStatus == -1 ? 'selected' : ''}>All</option>
                                                        <option value="0" ${paymentStatus == 0 ? 'selected' : ''}>Unpaid</option>
                                                        <option value="1" ${paymentStatus == 1 ? 'selected' : ''}>Paid</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label>&nbsp;</label>
                                                    <button type="submit" class="btn btn-primary form-control">Search</button>
                                                </div>
                                            </div>
                                        </form>

                                        <table class="table table-center bg-white mb-0">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>User ID</th>
                                                    <th>Total Price</th>
                                                    <th>Receiver Name</th>
                                                    <th>Status</th>
                                                    <th>Payment Status</th>
                                                    <th>Created Date</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${allReservations}" var="r">
                                                    <tr>
                                                        <td>${r.reservation_id}</td>
                                                        <td>${r.user_id}</td>
                                                        <td>${r.total_price}</td>
                                                        <td>${r.receiver_name}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${r.status == 0}">Pending</c:when>
                                                                <c:when test="${r.status == 1}">Confirmed</c:when>
                                                                <c:when test="${r.status == 2}">Cancelled</c:when>
                                                                <c:otherwise>Unknown</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${r.payment_status == 0}">Unpaid</c:when>
                                                                <c:when test="${r.payment_status == 1}">Paid</c:when>
                                                                <c:otherwise>Unknown</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>${r.created_date}</td>
                                                        <td>
                                                            <a href="${pageContext.request.contextPath}/admin/reservationDetail?id=${r.reservation_id}" class="btn btn-primary btn-sm">View</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                        <div class="row text-center">
                                            <div class="col-12 mt-4">
                                                <ul class="pagination justify-content-center">
                                                    <c:forEach begin="0" end="${(totalElements + pageSize - 1) / pageSize - 1}" var="i">
                                                        <li class="page-item ${pageNo == i ? 'active' : ''}">
                                                            <a class="page-link" href="${pageContext.request.contextPath}/admin/reservationList?pageNo=${i}&search=${search}&status=${status}&paymentStatus=${paymentStatus}">
                                                                ${i + 1}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
    </body>
</html>