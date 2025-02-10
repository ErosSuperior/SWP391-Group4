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
    <style>
        .info-container {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .info-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 8px 12px;
            background: #f8f9fa;
            border-radius: 8px;
        }

        .info-label {
            font-weight: 600;
            color: #495057;
        }

        .info-value {
            font-weight: 500;
            color: #212529;
        }

        .info-image {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 8px;
            border: 2px solid #dee2e6;
        }

    </style>
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
                            <h5 class="mb-0">Slider List</h5>
                        </div>
                        <div class="row">
                            <!-- Combined Filter and Pagination Form -->
                            <div class="row">
                                <!-- Filters -->



                                <!-- User List Table -->
                                <div class="col-12 mt-4">
                                    <div class="table-responsive shadow rounded">
                                        <table class="table table-center bg-white mb-0">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="min-width: 50px;">Id
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 180px;">Title
                                                    </th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                        Image
                                                    </th>
                                                    <th class="border-bottom p-3">Note</th>
                                                    <th class="border-bottom p-3">Status</th>
                                                    <th class="border-bottom p-3" style="min-width: 150px;">
                                                        Action
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.sliders}" var="s">
                                                    <tr>
                                                        <th class="p-3">${s.sliderId}</th>
                                                        <td class="p-3">${s.title}</td>
                                                        <td class="p-3">
                                                            <image src="${s.image}" alt="Image" style="width: 80px; height: 80px; object-fit: cover" />
                                                        </td>
                                                        <td class="p-3">${s.note}</td>
                                                        <td class="p-3">
                                                            <c:choose>
                                                                <c:when test="${s.status == true}">
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
                                                            <a href="" data-bs-toggle="modal" data-bs-target="#sliderDetail-${s.sliderId}"
                                                               class="btn btn-icon btn-pills btn-soft-primary">
                                                                <i class="uil uil-bars"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                <div class="modal fade" id="sliderDetail-${s.sliderId}" tabindex="-1" aria-labelledby="confirmationModalLabel"
                                                     aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="confirmationModalLabel">Slider Detail</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                        aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body p-4">
                                                                <div class="info-container">
                                                                    <div class="info-item">
                                                                        <span class="info-label">Title:</span>
                                                                        <span class="info-value">${s.title}</span>
                                                                    </div>

                                                                    <div class="info-item">
                                                                        <span class="info-label">Image:</span>
                                                                        <span class="info-value">
                                                                            <img src="${s.image}" alt="Image" class="info-image"/>
                                                                        </span>
                                                                    </div>

                                                                    <div class="info-item">
                                                                        <span class="info-label">Note:</span>
                                                                        <span class="info-value">${s.note}</span>
                                                                    </div>

                                                                    <div class="info-item">
                                                                        <span class="info-label">Status:</span>
                                                                        <span class="info-value">
                                                                            <c:choose>
                                                                                <c:when test="${s.status == true}">
                                                                                    <span class="badge bg-success">Active</span>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <span class="badge bg-danger">Inactive</span>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- Pagination Section -->
                                <div class="d-md-flex align-items-center text-center flex-end justify-content-end" style="margin-top: 25px">
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a href="?page=${currentPage - 1}" class="page-link">
                                                First
                                            </a>
                                        </li>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="?page=${i}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a href="?page=${currentPage + 1}" class="page-link">
                                                Last
                                            </a>
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
