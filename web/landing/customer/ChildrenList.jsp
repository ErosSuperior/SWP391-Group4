<%-- 
    Document   : ChildrenList
    Created on : Mar 29, 2025
    Author     : [Your Name]
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Children Care - Manage Your Children</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Children, Management, Dashboard, Health" />
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
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
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
        <!-- Navbar Start -->
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
                        <form action="${pageContext.request.contextPath}/children/list" method="get">
                            <div class="row">
                                <div class="col-12">
                                    <h5 class="mb-3">Your Children</h5>
                                </div>
                                <nav aria-label="breadcrumb" class="col-12">
                                    <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">My Children</li>
                                    </ul>
                                </nav>

                                <div class="col-4">
                                    <div class="col-md-12 mt-3">
                                        <label class="form-label">Search</label>
                                        <div class="input-group">
                                            <input type="text" name="nameOrId" class="form-control"
                                                   placeholder="Search by name or ID" value="${param.nameOrId}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                            <button class="btn btn-primary ms-2" type="button"
                                                    onclick="window.location.href = '${pageContext.request.contextPath}/children/list?nameOrId=&sortBy=${param.sortBy}&sortDir=${param.sortDir}'">
                                                <i class="uil uil-redo"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="mt-3">
                                        <label class="form-label">Sort</label>
                                        <select name="sortBy" class="form-control" onchange="this.form.submit()">
                                            <option value="children_id" ${param.sortBy == 'children_id' ? 'selected' : ''}>ID</option>
                                            <option value="children_name" ${param.sortBy == 'children_name' ? 'selected' : ''}>Name</option>
                                            <option value="children_gender" ${param.sortBy == 'children_gender' ? 'selected' : ''}>Gender</option>
                                            <option value="children_age" ${param.sortBy == 'children_age' ? 'selected' : ''}>Age</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="mt-3">
                                        <label class="form-label">Order</label>
                                        <select name="sortDir" class="form-control" onchange="this.form.submit()">
                                            <option value="ASC" ${param.sortDir == 'ASC' ? 'selected' : ''}>Ascending</option>
                                            <option value="DESC" ${param.sortDir == 'DESC' ? 'selected' : ''}>Descending</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive bg-white shadow rounded">
                                        <table class="table mb-0 table-center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" onclick="submitSort('children_id')"># <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('children_name')">Name <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('children_gender')">Gender <span>⇅</span></th>
                                                    <th class="border-bottom p-3" onclick="submitSort('children_age')">Age <span>⇅</span></th>
                                                    <th class="border-bottom p-3 text-center">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${not empty childrenList}">
                                                        <c:forEach var="child" items="${childrenList}" varStatus="status">
                                                            <tr>
                                                                <th class="p-3">${child.children_id}</th>
                                                                <td class="p-3">${child.children_name}</td>
                                                                <td class="p-3">${child.children_gender ? 'Male' : 'Female'}</td>
                                                                <td class="p-3">${child.children_age}</td>
                                                                <td class="text-center p-3">
                                                                    <a href="${pageContext.request.contextPath}/children/detail?childId=${child.children_id}&edit=true"
                                                                       class="btn btn-icon btn-pills btn-soft-warning">
                                                                        <i class="uil uil-pen"></i>
                                                                    </a>
                                                                    <button class="btn btn-icon btn-pills btn-soft-danger"
                                                                            onclick="showConfirmationModal(${child.children_id})"
                                                                            type="button">
                                                                        <i class="uil uil-times-circle"></i>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr>
                                                            <td colspan="5" class="text-center p-3">No children found.</td>
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
                        <!-- Add Child Button -->
                        <div class="row">
                            <div class="col-12 mt-4">
                                <a href="${pageContext.request.contextPath}/children/detail?add=true"
                                   class="btn btn-primary" id="addChildBtn">Add New Child</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for confirmation -->
            <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmationModalLabel">Confirm Delete</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete this child?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button id="confirmAction" type="button" class="btn btn-primary">Confirm</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Error Modal -->
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
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top">
            <i data-feather="arrow-up" class="icons"></i>
        </a>

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
            let currentChildId;

            // Initialize modals
            document.addEventListener('DOMContentLoaded', function() {
                const confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
                const errorModal = new bootstrap.Modal(document.getElementById('exampleModal'));

                // Add event listener for confirmation modal
                document.getElementById('confirmAction').addEventListener('click', function() {
                    deleteChild(currentChildId);
                    confirmationModal.hide();
                });

                // Add event listener for add child button
                document.getElementById('addChildBtn').addEventListener('click', function() {
                    window.location.href = '${pageContext.request.contextPath}/children/detail?add=true';
                });
            });

            function showConfirmationModal(childId) {
                currentChildId = childId;
                const confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
                confirmationModal.show();
            }

            // CHANGE: Added console logging to debug the deleteChild function
            function deleteChild(childId) {
                console.log("Deleting child with ID:", childId);
                fetch('${pageContext.request.contextPath}/children/delete', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'childId=' + encodeURIComponent(childId)
                })
                .then(response => {
                    console.log("Response status:", response.status);
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log("Response data:", data);
                    if (data.success) {
                        console.log("Child deleted successfully. Page will reload.");
                        window.location.reload();
                    } else {
                        showErrorModal(data.message);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    showErrorModal("An unexpected error occurred. Please try again.");
                });
            }

            function showErrorModal(message) {
                document.querySelector('#exampleModal .modal-body').textContent = message;
                const errorModal = new bootstrap.Modal(document.getElementById('exampleModal'));
                errorModal.show();
            }

            function submitSort(column) {
                const form = document.querySelector('form');
                const currentSort = form.querySelector('select[name="sortBy"]').value;
                const currentDir = form.querySelector('select[name="sortDir"]').value;
                
                if (currentSort === column) {
                    // If already sorted by this column, toggle direction
                    form.querySelector('select[name="sortDir"]').value = currentDir === 'ASC' ? 'DESC' : 'ASC';
                } else {
                    // If sorting by different column, reset direction to ASC
                    form.querySelector('select[name="sortDir"]').value = 'ASC';
                }
                form.querySelector('select[name="sortBy"]').value = column;
                form.submit();
            }

            // Highlight sorted column
            document.addEventListener('DOMContentLoaded', function() {
                const urlParams = new URLSearchParams(window.location.search);
                const sortValue = urlParams.get('sortBy');
                document.querySelectorAll('th[onclick]').forEach(th => {
                    if (th.getAttribute('onclick').includes(sortValue)) {
                        th.style.color = 'blue';
                    }
                });
            });
        </script>
    </body>
</html>