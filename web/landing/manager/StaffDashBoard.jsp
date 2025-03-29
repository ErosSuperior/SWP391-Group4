<%-- 
    Document   : ManagerDashboard
    Created on : Mar 23, 2025, 5:56:32 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Children Care - Appointment Booking System</title>
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
        <!-- Select2 -->
        <link href="<%= request.getContextPath() %>/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- SLIDER -->
        <link href="<%= request.getContextPath() %>/assets/css/tiny-slider.css" rel="stylesheet" />
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

    </head>
    <body>


        <div class="page-wrapper doctris-theme toggled">
            <!--sidebar here-->
            <!-- sidebar-wrapper  -->
            <jsp:include page="../AdminSidebar.jsp"/>
            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <!--navbarhere-->
                <jsp:include page="../AdminNavbar.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <h5 class="mb-0">Dashboard</h5>

                        <div class="row" style=" margin-right: 13px">
                            <div class="row" style=" margin-right: 13px">
                                <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                    <div class="card features feature-primary rounded border-0 shadow p-4">
                                        <div class="d-flex align-items-center">
                                            <div class="icon text-center rounded-md">
                                                <i class="uil uil-bed h3 mb-0"></i>
                                            </div>
                                            <div class="flex-1 ms-2">
                                                <h5 class="mb-0">${countservice}</h5>
                                                <p class="text-muted mb-0">Service Completed</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--end col-->


                            </div><!--end row-->

                        </div><!--end row-->

                        <div class="row">
                            <!-- Combined Filter and Pagination Form -->
                            <form action="${pageContext.request.contextPath}/StaffDashboardController" method="GET"
                                  class="row">
                                <!-- Filters -->

                                <div class="col-md-4">
                                    <div class="mt-3">
                                        <label class="form-label">Search</label>
                                        <div class="input-group">
                                            <input type="text" name="nameOrId" class="form-control"
                                                   placeholder="Search by name or ID" value="${param.nameOrId}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/StaffDashboardController'">
                                                <i class="uil uil-redo"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <!-- User List Table -->
                                <div class="col-12 mt-4">
                                    <div class="table-responsive shadow rounded">
                                        <table class="table table-center bg-white mb-0">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">Id
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 180px;">Note
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                        Expected Date
                                                    </th>
                                                    <th class="border-bottom p-3">Service Title</th>
                                                    <th class="border-bottom p-3">Status</th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                        Action
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.allblogs}" var="d">
                                                    <tr>
                                                        <th class="p-3">${d.detail_id}</th>
                                                        <td class="py-3">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <span class="ms-2">${d.note}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3">${d.begin_time}</td>
                                                        <td class="p-3">${d.service_title}</td>

                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${d.slot == '0' && d.children_id == '0'}">
                                                                    <span
                                                                        class="badge bg-soft-danger">Not Yet</span>
                                                                </c:when>
                                                                <c:when test="${d.slot != '0' && d.children_id == '0'}">
                                                                    <span
                                                                        class="badge bg-soft-success">Scheduled</span> 
                                                                    <span
                                                                        class="badge bg-soft-danger">Not Received</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span
                                                                        class="badge bg-soft-danger">Error</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${d.slot == '0' && d.children_id == '0'}">

                                                                    <button type="button" class="btn btn-icon btn-pills btn-soft-primary view-invoice-btn" onclick="openCustomModal2('${d.detail_id}')"><i class="uil uil-clipboard-notes icons"></i></button>
                                                                    </c:when>
                                                                    <c:when test="${d.slot != '0' && d.children_id == '0'}">
                                                                    <button type="button" class="btn btn-icon btn-pills btn-soft-success" onclick="openCustomModal1('${d.detail_id}')">
                                                                        <i class="uil uil-check-circle"></i>
                                                                    </button>

                                                                </c:when>
                                                                <c:otherwise>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
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
                                <!-- Pagination Section End -->         
                            </form>
                        </div>
                    </div>
                </div><!--end container-->
            </main>
            <!--End page-content" -->
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="myModalLabel">Service Confirmation</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        You have successfully operated the service for the customer?
                    </div>
                    <form action="${pageContext.request.contextPath}/confirmservice" method="get">
                        <input type="hidden" id="detailIdInputc" name="detail_id">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="customModal" tabindex="-1" aria-labelledby="customModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="customModalLabel">Schedule a Slot</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="${pageContext.request.contextPath}/scheduleslot" method="get">
                        <div class="modal-body">
                            <input type="hidden" id="detailIdInputs" name="detail_id" value="" />
                            <label for="slotDropdown" class="form-label">Select a Slot:</label>
                            <select id="slotDropdown" class="form-select" name="slot" onchange="displaySlotTime()">
                                <option value="1">Slot 1 - 8:00 - 10:00</option>
                                <option value="2">Slot 2 - 10:00 - 12:00</option>
                                <option value="3">Slot 3 - 12:00 - 14:00</option>
                                <option value="4">Slot 4 - 14:00 - 16:00</option>
                                <option value="5">Slot 5 - 16:00 - 18:00</option>
                                <option value="6">Slot 6 - 18:00 - 20:00</option>
                            </select>
                            <p id="slotTime" class="mt-3"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <input type="submit" value="Save changes" class="btn btn-primary"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="<%= request.getContextPath() %>/assets/js/simplebar.min.js"></script>
        <!-- Chart -->
        <script src="<%= request.getContextPath() %>/assets/js/apexcharts.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/columnchart.init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>

        <script src="<%= request.getContextPath() %>/assets/js/areachart.init.js"></script>

        <script src="https://cdn.datatables.net/2.1.6/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/2.1.6/js/dataTables.bootstrap5.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        
        <script>
            function openCustomModal2(detailId) {
                document.getElementById('detailIdInputs').value = detailId;
                var customModal = new bootstrap.Modal(document.getElementById('customModal'));
                customModal.show();
            }

            function displaySlotTime() {
                const slotTimes = {
                    '1': '8:00 - 10:00',
                    '2': '10:00 - 12:00',
                    '3': '12:00 - 14:00',
                    '4': '14:00 - 16:00',
                    '5': '16:00 - 18:00',
                    '6': '18:00 - 20:00'
                };
                const selectedSlot = document.getElementById('slotDropdown').value;
                document.getElementById('slotTime').textContent = 'Time: ' + slotTimes[selectedSlot];
            }
        </script>
        <script>
            function openCustomModal1(detailId) {
                // Set the detail_id in the hidden input
                document.getElementById('detailIdInputc').value = detailId;
                // Show the modal
                var myModal = new bootstrap.Modal(document.getElementById('myModal'));
                myModal.show();
            }
        </script>

    </body>
</html>
