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
        <title>Doctris - Doctor Appointment Booking System</title>
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

                                <div class="col-12">
                                    <div class="col-md-4 mt-3">
                                        <label class="form-label">Search</label>
                                        <div class="input-group">
                                            <input type="text" name="nameOrId" class="form-control"
                                                   placeholder="Search by name or ID" value="${param.nameOrId}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/customer/myreservationlist'">
                                                <i class="uil uil-redo"></i>
                                            </button>
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
                                                    <th class="border-bottom p-3">#</th>
                                                    <th class="border-bottom p-3">Note</th>     
                                                    <th class="border-bottom p-3">Date</th>
                                                    <th class="border-bottom p-3">State</th>
                                                    <th class="border-bottom p-3">Total Price</th>
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
                                                                            <span class="badge bg-soft-success">Submitted</span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span class="badge bg-soft-danger">Pending</span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>

                                                                <td class="p-3">$${reservation.total_price}</td>
                                                                <td class="text-center p-3">
                                                                    <c:choose>
                                                                        <c:when test="${reservation.status == '1'}">
                                                                            <a href="#" class="btn btn-icon btn-pills btn-soft-primary" data-bs-toggle="modal" data-bs-target="#view-invoice">
                                                                                <i class="uil uil-eye"></i>
                                                                            </a>
                                                                        </c:when>
                                                                        <c:when test="${reservation.status == '0'}">
                                                                            <a href="${pageContext.request.contextPath}/customer/myreservationlist"
                                                                               class="btn btn-icon btn-pills btn-soft-primary">
                                                                                <i class="uil uil-pen"></i>
                                                                            </a>
                                                                        </c:when>
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
        </section><!-- End Hero -->

        <!-- View Invoice Start -->
        <div class="modal fade" id="view-invoice" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Reservation Information</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <div class="row mb-4">
                            <div class="col-lg-8 col-md-6">
                                <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png?v=<%= System.currentTimeMillis() %>" height="140" class="logo-light-mode" alt="">
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
    </body>
</html>
