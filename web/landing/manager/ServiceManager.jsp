

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Service Management</title>
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
                            <!-- Combined Filter and Pagination Form -->
                            <form  action="${pageContext.request.contextPath}/manager/ServiceList" method="GET"
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
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/manager/ServiceList'">
                                                <i class="uil uil-redo"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-1 d-flex align-items-end justify-content-end" style="margin-left: 275px">
                                    <button class="btn btn-primary btn-md"
                                            onclick="window.location.href = '${pageContext.request.contextPath}/manager/manageraddBlog'"
                                            type="button">
                                        <i class="uil uil-plus"></i>
                                    </button>   
                                </div>



                                <!-- User List Table -->
                                <div class="col-12 mt-4">
                                    <div class="table-responsive shadow rounded">
                                        <table class="table table-center bg-white mb-0">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="min-width: 50px;" onclick="submitSort('service_id')">
                                                        Id <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 180px;" onclick="submitSort('service_title')">
                                                        Title <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;" onclick="submitSort('c.category_id')">
                                                        Category <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" onclick="submitSort('service_rateStar')">
                                                        Rating <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" onclick="submitSort('service_detail')">
                                                        Detail <span>⇅</span>
                                                    </th>
                                                    <th class="border-bottom p-3" onclick="submitSort('serviceStatus')">
                                                        Status <span>⇅</span>
                                                    </th>

                                                    <th class="border-bottom p-3" style="min-width: 150px;"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.allblogs}" var="d">
                                                    <tr>
                                                        <th class="p-3">${d.serviceId}</th>
                                                        <td class="py-3">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <span class="ms-2">${d.serviceTitle}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3">${d.categoryTitle}</td>
                                                        <td class="p-3">${d.serviceRateStar}</td>
                                                        <td class="p-3">${d.serviceDetail}</td>

                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${d.serviceStatus == '1'}">
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
                                                            <a href="${pageContext.request.contextPath}/manager/managereditBlog?blog_id=${d.serviceId}"
                                                               class="btn btn-icon btn-pills btn-soft-primary">
                                                                <i class="uil uil-pen"></i>
                                                            </a>
                                                            <c:choose>
                                                                <c:when test="${d.serviceStatus == '1'}">
                                                                    <button
                                                                        class="btn btn-icon btn-pills btn-soft-danger"
                                                                        data-user-id="${d.serviceId}"
                                                                        data-new-status="0"
                                                                        onclick="showConfirmationModal(${d.serviceId}, 0)"
                                                                        type="button">
                                                                        <i class="uil uil-times-circle"></i>
                                                                    </button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button
                                                                        class="btn btn-icon btn-pills btn-soft-success"
                                                                        data-user-id="${d.serviceId}"
                                                                        data-new-status="1"
                                                                        onclick="showConfirmationModal(${d.serviceId}, 1)"
                                                                        type="button">
                                                                        <i class="uil uil-check-circle"></i>
                                                                    </button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-soft-primary" 
                                                               data-bs-toggle="modal" data-bs-target="#exampleModal"
                                                               data-detail="${d.serviceImage}">
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
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Service Thumbnail</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <img id="modalServiceDetail" src="" alt="Service Image" class="img-fluid" style="display: none;">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
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
                                                                                    url: '${pageContext.request.contextPath}/manager/managerupdatestatusBlog',
                                                                                    type: 'POST',
                                                                                    data: {
                                                                                        serviceId: userId,
                                                                                        serviceStatus: newStatus
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
