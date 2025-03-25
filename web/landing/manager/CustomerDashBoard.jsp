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
                                                <h5 class="mb-0">${numberofres}</h5>
                                                <p class="text-muted mb-0">Reservation Completed</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--end col-->

                                <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                    <div class="card features feature-primary rounded border-0 shadow p-4">
                                        <div class="d-flex align-items-center">
                                            <div class="icon text-center rounded-md">
                                                <i class="uil uil-medical-drip h3 mb-0"></i>
                                            </div>
                                            <div class="flex-1 ms-2">
                                                <h5 class="mb-0">${pendingcount}</h5>
                                                <p class="text-muted mb-0">Reservation Pending</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--end col-->

                                <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                    <div class="card features feature-primary rounded border-0 shadow p-4">
                                        <div class="d-flex align-items-center">
                                            <div class="icon text-center rounded-md">
                                                <i class="uil uil-times-circle h3 mb-0"></i>
                                            </div>
                                            <div class="flex-1 ms-2">
                                                <h5 class="mb-0">${cancelcount}</h5>
                                                <p class="text-muted mb-0">Reservation Canceled</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--end col-->

                                <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                    <div class="card features feature-primary rounded border-0 shadow p-4">
                                        <div class="d-flex align-items-center">
                                            <div class="icon text-center rounded-md">
                                                <i class="uil uil-file-medical-alt h3 mb-0"></i>
                                            </div>
                                            <div class="flex-1 ms-2">
                                                <h5 class="mb-0">$${netrevenue}</h5>
                                                <p class="text-muted mb-0">Avg Net Revenue</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--end col-->

                                <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                    <div class="card features feature-primary rounded border-0 shadow p-4">
                                        <div class="d-flex align-items-center">
                                            <div class="icon text-center rounded-md">
                                                <i class="uil uil-social-distancing h3 mb-0"></i>
                                            </div>
                                            <div class="flex-1 ms-2">
                                                <h5 class="mb-0">${staffcount}</h5>
                                                <p class="text-muted mb-0">Staff Members</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--end col-->

                            </div><!--end row-->

                        </div><!--end row-->

                        <div class="row">
                            <!-- Combined Filter and Pagination Form -->
                            <form action="${pageContext.request.contextPath}/ManagerDashboardController" method="GET"
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
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/ManagerDashboardController'">
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
                                                        Created Date
                                                    </th>
                                                    <th class="border-bottom p-3">Total Price</th>
                                                    <th class="border-bottom p-3">Status</th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                        Action
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.allblogs}" var="d">
                                                    <tr>
                                                        <th class="p-3">${d.reservation_id}</th>
                                                        <td class="py-3">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <span class="ms-2">${d.note}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3">${d.created_date}</td>
                                                        <td class="p-3">${d.total_price}</td>

                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${d.status == '1'}">
                                                                    <span
                                                                        class="badge bg-soft-danger">Not Yet</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span
                                                                        class="badge bg-soft-danger">Inactive</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="p-3">
                                                            <button
                                                                class="btn btn-icon btn-pills btn-soft-success"
                                                                data-user-id="${d.reservation_id}"
                                                                data-new-status="2"
                                                                onclick="showConfirmationModal(${d.reservation_id}, 2)"
                                                                type="button">
                                                                <i class="uil uil-check-circle"></i>
                                                            </button>
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
        <!-- Modal for confirmation -->
        <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmationModalLabel">Confirm/Cancel Reservation</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to confirm/cancel the order?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button id="confirmAction" type="button" class="btn btn-primary">Confirm</button>
                    </div>
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
                                                                    let currentUserId, currentStatus;

                                                                    function showConfirmationModal(userId, newStatus) {
                                                                        currentUserId = userId;
                                                                        currentStatus = newStatus;
                                                                        $('#confirmationModal').modal('show');
                                                                    }

                                                                    $('#confirmAction').click(function () {
                                                                        updateStatus(currentUserId, currentStatus);
                                                                        $('#confirmationModal').modal('hide'); // Hide modal after confirmation
                                                                    });

                                                                    function updateStatus(userId, newStatus) {
                                                                        console.log(userId);
                                                                        console.log(newStatus);
                                                                        $.ajax({
                                                                            url: '${pageContext.request.contextPath}/dashboardReservationStatuschange',
                                                                            type: 'POST',
                                                                            data: {
                                                                                resId: userId,
                                                                                resStatus: newStatus
                                                                            },
                                                                            success: function (response) {
                                                                                console.log("AJAX request successful. Page will reload.");
                                                                                location.reload();
                                                                            },
                                                                            error: function (xhr, status, error) {
                                                                                console.error('AJAX Error:', xhr.status, status, error);
                                                                                // Optionally show an error message in the modal or elsewhere
                                                                            }
                                                                        });
                                                                    }

                                                                    document.addEventListener("DOMContentLoaded", function () {
                                                                        document.querySelectorAll("[data-bs-target='#exampleModal']").forEach(button => {
                                                                            button.addEventListener("click", function () {
                                                                                let imageUrl = this.getAttribute("data-detail"); // Get image URL from data attribute
                                                                                let modalImage = document.getElementById("modalServiceDetail");

                                                                                if (imageUrl) {
                                                                                    modalImage.src = imageUrl;
                                                                                    modalImage.style.display = "block"; // Show image
                                                                                } else {
                                                                                    modalImage.style.display = "none"; // Hide if no image
                                                                                }
                                                                            });
                                                                        });
                                                                    });
        </script>
    </body>
</html>
