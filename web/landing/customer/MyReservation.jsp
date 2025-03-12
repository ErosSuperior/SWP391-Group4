<%-- 
    Document   : MyReservation
    Created on : Feb 17, 2025, 10:07:26 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Children Care - Doctor Appointment Booking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="<%= request.getContextPath() %>/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/flatpickr.min.css">
        <link href="<%= request.getContextPath() %>/assets/css/jquery.timepicker.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        
        <style>
            th span {
                color: #aaa;
                font-size: 0.8em;
                margin-left: 5px;
            }
        </style>
    </head>
    <body>
        <!-- Loader -->

        <!-- Loader -->

        <!-- Navbar STart -->
        <jsp:include page="../CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Start Hero -->
        <section class="bg-dashboard">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5 col-12">
                        <jsp:include page="../ProfileSidebar.jsp"/>
                    </div>

                    <div class="col-xl-9 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <!-- Start Form -->
                        <form action="${pageContext.request.contextPath}/customer/myreservationlist" method="get">
                            <div class="row">
                                <div class="col-12">
                                    <h5 class="mb-3">Your Reservation</h5>
                                </div>
                                <nav aria-label="breadcrumb" class="col-12">
                                    <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">My Reservation</li>
                                    </ul>
                                </nav>  

                                <div class="col-4">
                                    <div class="col-md-12 mt-3">
                                        <label class="form-label">Search</label>
                                        <div class="input-group">
                                            <input type="text" name="nameOrId" class="form-control"
                                                   placeholder="Search by note or ID" value="${param.nameOrId}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/customer/myreservationlist'">
                                                <i class="uil uil-redo"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="mt-3">
                                        <label class="form-label">Sort</label>
                                        <select name="sortdir" class="form-control">
                                            <option value="ASC" ${param.sortdir=='ASC' ? 'selected' : '' }>Ascend
                                            </option>
                                            <option value="DESC" ${param.sortdir=='DESC' ? 'selected' : '' }>Descend
                                            </option>
                                        </select>
                                    </div>
                                </div>


                                <%
                                    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); // take this current year
                                %>
                                <div class="col-12">
                                    <div class="col-md-4 mt-3">
                                        <label class="form-label">Select Date</label>
                                        <div class="input-group">
                                            <select name="day" class="form-select">
                                                <option value="">Day</option>
                                                <c:forEach var="d" begin="1" end="31">
                                                    <option value="${d}" ${param.day == d ? 'selected' : ''}>${d}</option>
                                                </c:forEach>
                                            </select>
                                            <select name="month" class="form-select ms-2">
                                                <option value="">Month</option>
                                                <c:forEach var="m" begin="1" end="12">
                                                    <option value="${m}" ${param.month == m ? 'selected' : ''}>${m}</option>
                                                </c:forEach>
                                            </select>
                                            <select name="year" class="form-select ms-2">
                                                <option value="">Year</option>
                                                <c:forEach var="y" begin="<%= currentYear - 10 %>" end="<%= currentYear %>">
                                                    <option value="${y}" ${param.year == y ? 'selected' : ''}>${y}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>




                            </div>

                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive bg-white shadow rounded">
                                        <table class="table mb-0 table-center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" onclick="submitSort('reservation_id')"># <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('note')">Note <span>⇅</span></th>     
                                                    <th class="border-bottom p-3" onclick="submitSort('created_date')">Submit Date <span>⇅</span></th>
                                                    <th class="border-bottom p-3" style="width:240px" onclick="submitSort('reservation_status')">State <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('total_price')">Total Price <span>⇅</span></th>
                                                    <th class="border-bottom p-3 text-center">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${not empty reservations}">
                                                        <c:forEach var="reservation" items="${reservations}" varStatus="status">
                                                            <tr>
                                                                <th class="p-3">${reservation.reservation_id}</th>
                                                                <td class="p-3">${reservation.note}</td>
                                                                <td class="p-3">${reservation.created_date}</td>
                                                                <td class="p-3">
                                                                    <c:choose>
                                                                        <c:when test="${reservation.status == '1'}">
                                                                            <span class="badge bg-soft-danger">Not Yet</span> 
                                                                        </c:when>
                                                                        <c:when test="${reservation.status == '2'}">
                                                                            <span class="badge bg-soft-success">Confirmed </span>  <span class="badge bg-soft-danger">Not Operated</span>
                                                                        </c:when>
                                                                        <c:when test="${reservation.status == '4'}">
                                                                            <span class="badge bg-soft-danger">Cancelled</span> 
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span class="badge bg-soft-success">Confirmed </span> <span class="badge bg-soft-success">Operated</span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>

                                                                <td class="p-3">$${reservation.total_price}</td>
                                                                <td class="text-center p-3">
                                                                    <c:choose>
                                                                        <c:when test="${reservation.status == '1'}">
                                                                            <a href="${pageContext.request.contextPath}/customer/myreservationdetail?reservation_id=${reservation.reservation_id}&fix=1"
                                                                               class="btn btn-icon btn-pills btn-soft-primary">
                                                                                <i class="uil uil-pen"></i>
                                                                            </a>
                                                                            <button class="btn btn-icon btn-pills btn-soft-danger"
                                                                                    data-user-id="1"
                                                                                    data-new-status="0"
                                                                                    onclick="showConfirmationModal(${reservation.reservation_id})"
                                                                                    type="button">
                                                                                <i class="uil uil-times-circle"></i>
                                                                            </button>

                                                                        </c:when>

                                                                        <c:when test="${reservation.status == '4'}">
                                                                            <a href="${pageContext.request.contextPath}/customer/myreservationdetail?reservation_id=${reservation.reservation_id}&fix=1"
                                                                               >

                                                                            </a>
                                                                        </c:when>

                                                                        <c:otherwise>
                                                                            <a href="${pageContext.request.contextPath}/customer/myreservationdetail?reservation_id=${reservation.reservation_id}" 
                                                                               class="btn btn-icon btn-pills btn-soft-primary view-invoice-btn">
                                                                                <i class="uil uil-eye"></i>
                                                                            </a>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr>
                                                            <td colspan="7" class="text-center p-3">No reservations found.</td>
                                                        </tr>
                                                    </c:otherwise>
                                                </c:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- Pagination Section -->
                                <div class="d-md-flex align-items-center text-center justify-content-between" style="margin-top: 25px">
                                    <span class="text-muted me-3">Showing ${pageNo * pageSize + 1}
                                        - ${totalElements.intValue() < ((pageNo + 1) * pageSize) ? totalElements.intValue() : ((pageNo + 1) * pageSize)} out of
                                        ${totalElements.intValue()}</span>
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <c:set var="totalPages" value="${(totalElements + pageSize - 1) div pageSize}" />
                                        <li class="page-item ${pageNo <= 0 ? 'disabled' : ''}">
                                            <button type="submit" name="pageNo" value="0" class="page-link">First</button>
                                        </li>
                                        <c:choose>
                                            <c:when test="${pageNo >= 1}">
                                                <li class="page-item">
                                                    <button type="submit" name="pageNo" value="${pageNo - 1}" class="page-link">${pageNo}</button>
                                                </li>
                                            </c:when>
                                        </c:choose>
                                        <li class="page-item active">
                                            <button type="submit" name="pageNo" value="${pageNo}" class="page-link" disabled>${pageNo + 1}</button>
                                        </li>
                                        <c:choose>
                                            <c:when test="${(pageNo + 1).intValue() < totalPages.intValue()}">
                                                <li class="page-item">
                                                    <button type="submit" name="pageNo" value="${pageNo + 1}" class="page-link">${pageNo + 2}</button>
                                                </li>
                                            </c:when>
                                        </c:choose>
                                        <li class="page-item ${pageNo + 1 >= totalPages.intValue() ? 'disabled' : ''}">
                                            <button type="submit" name="pageNo" value="${(totalPages - 1).intValue()}" class="page-link">Last</button>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </form><!-- End Form -->
                    </div>
                </div>
            </div>
            <!-- Modal for confirmation -->
            <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmationModalLabel">Confirm Cancel</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to cancel the order?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button id="confirmAction" type="button" class="btn btn-primary">Confirm</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header border-bottom p-3">
                            <h5 class="modal-title" id="exampleModalLabel">Error</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            ...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="location.reload();">Close</button>
                        </div>
                    </div>
                </div>
            </div>

        </section><!-- End Hero -->

        <jsp:include page="../Footer.jsp"/>

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Select2 -->
        <script src="<%= request.getContextPath() %>/assets/js/select2.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/select2.init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>

        <script>
            let currentUserId;

            function showConfirmationModal(reservationId) {
                currentUserId = reservationId;
                $('#confirmationModal').modal('show');
            }

            $('#confirmAction').click(function () {
                updateStatus(currentUserId);
                $('#confirmationModal').modal('hide'); // Hide modal after confirmation
            });

            function updateStatus(reservationId) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/deleteReservation',
                    type: 'POST',
                    data: {
                        delete_id: reservationId
                    },
                    success: function (response) {
                        if (!response.success) {
                            showErrorModal(response.message);
                            return;
                        }
                        console.log("AJAX request successful. Page will reload.");
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error('AJAX Error:', xhr.status, status, error);
                        showErrorModal("An unexpected error occurred. Please try again.");
                    }
                });
            }

            function showErrorModal(message) {
                $('#exampleModal .modal-body').text(message);
                $('#exampleModal').modal('show');
            }

        </script>
        <script>
            function submitSort(sortValue) {
                const form = document.querySelector('form');
                const sortInput = document.createElement('input');
                sortInput.type = 'hidden';
                sortInput.name = 'sortvalue';
                sortInput.value = sortValue;
                form.appendChild(sortInput);
                form.submit();
            }
        </script>
        <script>
            // Get the URL parameters
            const urlParams = new URLSearchParams(window.location.search);
            const sortValue = urlParams.get('sortvalue');

            // Highlight the matched th
            document.querySelectorAll('th[onclick]').forEach(th => {
                if (th.getAttribute('onclick').includes(sortValue)) {
                    th.style.color = 'blue';
                }
            });
        </script>
    </body>
</html>
