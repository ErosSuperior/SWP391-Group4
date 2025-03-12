

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Feedback Management</title>
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
                            <h5 class="mb-0">Feedback List</h5>
                        </div>
                        <div class="row">
                            <!-- Combined Filter and Pagination Form -->
                            <form action="${pageContext.request.contextPath}/manager/feedbackList" method="GET"
                                  class="row">
                                <!-- Filters -->

                                <div class="col-md-2">
                                    <div class="mt-3">
                                        <label class="form-label">Status</label>
                                        <select name="status" class="form-control">
                                            <option value="-1">All Status</option>
                                            <option value="1" ${param.status=='1' ? 'selected' : '' }>Active
                                            </option>
                                            <option value="0" ${param.status=='0' ? 'selected' : '' }>Inactive
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="mt-3">
                                        <label class="form-label">Rating</label>
                                        <select name="ratestar" class="form-control">
                                            <option value="-1" ${param.ratestar == '-1' ? 'selected' : ''}>All Ratings</option>
                                            <option value="5" ${param.ratestar == '5' ? 'selected' : ''}>★★★★★ (5 Stars)</option>
                                            <option value="4" ${param.ratestar == '4' ? 'selected' : ''}>★★★★☆ (4 Stars)</option>
                                            <option value="3" ${param.ratestar == '3' ? 'selected' : ''}>★★★☆☆ (3 Stars)</option>
                                            <option value="2" ${param.ratestar == '2' ? 'selected' : ''}>★★☆☆☆ (2 Stars)</option>
                                            <option value="1" ${param.ratestar == '1' ? 'selected' : ''}>★☆☆☆☆ (1 Star)</option>
                                            <option value="0" ${param.ratestar == '0' ? 'selected' : ''}>No Rating</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-2">
                                    <div class="mt-3">
                                        <label class="form-label">Service</label>
                                        <select name="serviceId" class="form-control">
                                            <option value="-1" ${param.serviceId == '-1' ? 'selected' : ''}>All Services</option>
                                            <c:forEach var="service" items="${serviceinfo}">
                                                <option value="${service.serviceId}" ${param.serviceId == service.serviceId ? 'selected' : ''}>
                                                    ${service.serviceTitle}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-2">
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

                                <div class="col-md-4">
                                    <div class="mt-3">
                                        <label class="form-label">Search</label>
                                        <div class="input-group">
                                            <input type="text" name="nameOrId" class="form-control"
                                                   placeholder="Search by name or ID" value="${param.nameOrId}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/manager/feedbackList'">
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
                                                    <th class="border-bottom p-3" style="min-width: 50px;" onclick="submitSort('feedback_id')">Id <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 180px;" onclick="submitSort('content')">Content <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;" onclick="submitSort('name')">
                                                        Author <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" onclick="submitSort('s.service_title')">Service <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('rate_Star')">Rate Star <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('status')">Status <span>⇅</span></th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.allblogs}" var="d">
                                                    <tr>
                                                        <th class="p-3">${d.feedbackId}</th>
                                                        <td class="py-3">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <span class="ms-2">${d.content}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3">${d.name}</td>
                                                        <td class="p-3">${d.serviceTitle}</td>
                                                        <c:if test="${d.rateStar != 0}">
                                                            <td class="p-3">${d.rateStar}⭐</td>
                                                        </c:if>
                                                        <c:if test="${d.rateStar == 0}">
                                                            <td class="p-3"><span
                                                                    class="badge bg-soft-danger">No Rating</span></td>
                                                            </c:if>
                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${d.status == '1'}">
                                                                    <span
                                                                        class="badge bg-soft-success">Active</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span
                                                                        class="badge bg-soft-danger">Inactive</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${d.status == '1'}">
                                                                    <button
                                                                        class="btn btn-icon btn-pills btn-soft-danger"
                                                                        data-user-id="${d.feedbackId}"
                                                                        data-new-status="0"
                                                                        onclick="showConfirmationModal(${d.feedbackId}, 0)"
                                                                        type="button">
                                                                        <i class="uil uil-times-circle"></i>
                                                                    </button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button
                                                                        class="btn btn-icon btn-pills btn-soft-success"
                                                                        data-user-id="${d.feedbackId}"
                                                                        data-new-status="1"
                                                                        onclick="showConfirmationModal(${d.feedbackId}, 1)"
                                                                        type="button">
                                                                        <i class="uil uil-check-circle"></i>
                                                                    </button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-soft-primary view-feedback-btn"
                                                               data-user-image="${d.userImage}"
                                                               data-user-name="${d.name}"
                                                               data-user-email="${d.email}"
                                                               data-user-mobile="${d.mobile}"
                                                               data-service-title="${d.serviceTitle}"
                                                               data-rate-star="${d.rateStar}"
                                                               data-status="${d.status}"
                                                               data-created-date="${d.createdDate}"
                                                               data-content="${d.content}">
                                                                <i data-feather="eye" class="icons"></i>
                                                            </a>

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
                </div>

            </main>
        </div>
        <!-- Img modal -->
        <!-- View Appointment Start -->
        <div class="modal fade" id="viewappointment" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel1">Feedback Detail</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <div class="d-flex align-items-center">
                            <img id="feedbackUserImage" src="" class="avatar avatar-small rounded-pill" alt="">
                            <h5 class="mb-0 ms-3" id="feedbackUserName"></h5>
                        </div>
                        <ul class="list-unstyled mb-0 d-md-flex justify-content-between mt-4">
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Email:</h6>
                                        <p class="text-muted ms-2" id="feedbackEmail"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Mobile:</h6>
                                        <p class="text-muted ms-2" id="feedbackMobile"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Service:</h6>
                                        <p class="text-muted ms-2" id="feedbackService"></p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex">
                                        <h6>Rating:</h6>
                                        <p class="text-muted ms-2" id="feedbackRating"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Status:</h6>
                                        <p class="text-muted ms-2" id="feedbackStatus"></p>
                                    </li>

                                    <li class="d-flex">
                                        <h6>Created Date:</h6>
                                        <p class="text-muted ms-2" id="feedbackDate"></p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <hr>
                        <h6>Feedback Content:</h6>
                        <p class="text-muted" id="feedbackContent"></p>
                    </div>
                </div>
            </div>
        </div>
        <!-- View Appointment End -->

        <!-- Img modal end -->
        <!-- Modal for confirmation -->
        <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmationModalLabel">Confirm Status Change</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to change the status?
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
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
        <!-- Pagination JS -->
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
                                                                                    url: '${pageContext.request.contextPath}/manager/updatefeedbackstatus',
                                                                                    type: 'POST',
                                                                                    data: {
                                                                                        feedbackId: userId,
                                                                                        status: newStatus
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



        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const feedbackModal = document.getElementById("viewappointment");

                document.querySelectorAll(".view-feedback-btn").forEach(button => {
                    button.addEventListener("click", function () {
                        const userImage = this.getAttribute("data-user-image");
                        const userName = this.getAttribute("data-user-name");
                        const email = this.getAttribute("data-user-email");
                        const mobile = this.getAttribute("data-user-mobile");
                        const serviceTitle = this.getAttribute("data-service-title");
                        const rating = this.getAttribute("data-rate-star");
                        const status = this.getAttribute("data-status") === "1" ? "Active" : "Inactive";
                        const date = this.getAttribute("data-created-date");
                        const content = this.getAttribute("data-content");

                        document.getElementById("feedbackUserImage").src = userImage ? userImage : "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-image-icon-default-avatar-profile-icon-social-media-user-vector-image-209162840.jpg";
                        document.getElementById("feedbackUserName").innerText = userName;
                        document.getElementById("feedbackEmail").innerText = email;
                        document.getElementById("feedbackMobile").innerText = mobile;
                        document.getElementById("feedbackService").innerText = serviceTitle;
                        document.getElementById("feedbackRating").innerText = rating ? rating + "⭐" : "No Rating";
                        document.getElementById("feedbackStatus").innerText = status;
                        document.getElementById("feedbackDate").innerText = date;
                        document.getElementById("feedbackContent").innerText = content;

                        // Show the modal
                        new bootstrap.Modal(feedbackModal).show();
                    });
                });
            });
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
