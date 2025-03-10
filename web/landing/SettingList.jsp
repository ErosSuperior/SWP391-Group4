<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Children Care - Doctor Appointment Booking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="${pageContext.request.contextPath}/${pageContext.request.contextPath}/${pageContext.request.contextPath}/index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="${pageContext.request.contextPath}/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

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
            <jsp:include page="AdminSidebar.jsp"/>

            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <jsp:include page="AdminNavbar.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <div class="col-md-6">
                                <h5 class="mb-0">Setting List</h5>
                                <a href="${pageContext.request.contextPath}/adminaddsetting" class="btn btn-primary"style="margin-top:20px">
                                                <i class="uil uil-plus"></i> Add Setting
                                            </a>
                            </div>
                           
                            <div style="margin-right: 50%;"class="col-lg-6 col-md-5 ms-auto">
                                <!--                                Hiển thị thông báo cho ng dùng nếu tìm thấy-->
                                <c:if test="${not empty sessionScope.delete}">
                                    <div class="alert alert-success" role="alert" style="font-weight: bold;">
                                        <strong>${sessionScope.delete}</strong>
                                    </div>
                                    <% 
                                        session.removeAttribute("delete");  // Hiển thị xong xóa
                                    %>

                                </c:if>
                            </div>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/adminsettinglist">Settings</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Setting List</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <table class="table table-center bg-white mb-0" id="Setting-Table">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3">Setting</th>
                                                <th class="border-bottom p-3" style="min-width: 220px;">Name</th>
                                                <th class="text-center border-bottom p-3" style="min-width: 180px;">Type</th>
                                                <th class="text-center border-bottom p-3" style="min-width: 140px;">Value</th>
                                                <th class="text-center border-bottom p-3">Status</th>
                                                <th class="text-end border-bottom p-3" style="min-width: 200px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- Start -->
                                            <c:forEach var="s" items="${listsetting}">
                                                <tr>
                                                    <th class="p-3">#${s.setting_id}</th>
                                                    <td class="p-3">
                                                        <a class="text-primary">
                                                            <div class="d-flex align-items-center">                                                               
                                                                <strong class="ms-2">${s.setting_name}</strong>
                                                            </div>
                                                        </a>
                                                    </td>
                                                    <td class="text-center p-3">${s.setting_type}</td>
                                                    <td class="text-center p-3">${s.setting_value}</td>
                                                    <td class="text-center p-3">

                                                        <c:choose>
                                                            <c:when test="${s.setting_status eq 'Active'}">
                                                                <div class="badge bg-soft-success rounded px-3 py-1">Active</div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="badge bg-soft-danger rounded px-3 py-1">Inactive</div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-end p-3">
                                                        <a href="${pageContext.request.contextPath}/adminsettingdetail?setting_id=${s.setting_id}" class="btn btn-sm btn-primary" >View</a>
                                                        <!--                                                        Nếu trạng thái hiện tại là Active thì hiển thị nút Inactive và ngược lại-->
                                                        <c:choose>
                                                            <c:when test="${s.setting_status eq 'Active'}">
                                                                <a href="adminsettingstatus?setting_id=${s.setting_id}" class="btn btn-sm btn-outline-danger ms-2">Inactive</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="adminsettingstatus?setting_id=${s.setting_id}" class="btn btn-sm btn-outline-success ms-2">Inactive</a>
                                                            </c:otherwise>
                                                        </c:choose>                                                    
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <!-- End -->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div><!--end row-->

                    </div>
                </div><!--end container-->

                <!-- Footer Start -->               
                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->

        <!-- Offcanvas Start -->
        <div class="offcanvas offcanvas-end bg-white shadow" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header p-4 border-bottom">
                <h5 id="offcanvasRightLabel" class="mb-0">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="24" class="light-version" alt="">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-light.png" height="24" class="dark-version" alt="">
                </h5>
                <button type="button" class="btn-close d-flex align-items-center text-dark" data-bs-dismiss="offcanvas" aria-label="Close"><i class="uil uil-times fs-4"></i></button>
            </div>
            <div class="offcanvas-body p-4 px-md-5">
                <div class="row">
                    <div class="col-12">
                        <!-- Style switcher -->
                        <div id="style-switcher">
                            <div>
                                <ul class="text-center list-unstyled mb-0">
                                    <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/dark-dash-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/dark-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                    <li class="d-grid"><a href="${pageContext.request.contextPath}/landing/index.html" target="_blank" class="mt-4"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Landing Demos</span></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end Style switcher -->
                    </div><!--end col-->
                </div><!--end row-->
            </div>

            <div class="offcanvas-footer p-4 border-top text-center">
                <ul class="list-unstyled social-icon mb-0">
                    <li class="list-inline-item mb-0"><a href="https://1.envato.market/doctris-template" target="_blank" class="rounded"><i class="uil uil-shopping-cart align-middle" title="Buy Now"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://dribbble.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-dribbble align-middle" title="dribbble"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.facebook.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-facebook-f align-middle" title="facebook"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.instagram.com/shreethemes/" target="_blank" class="rounded"><i class="uil uil-instagram align-middle" title="instagram"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://twitter.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-twitter align-middle" title="twitter"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="mailto:support@shreethemes.in" class="rounded"><i class="uil uil-envelope align-middle" title="email"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/${pageContext.request.contextPath}/index.html" target="_blank" class="rounded"><i class="uil uil-globe align-middle" title="website"></i></a></li>
                </ul><!--end icon-->
            </div>
        </div>
        <!-- Offcanvas End -->

        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script>
                                        window.addEventListener('DOMContentLoaded', event => {
                                            // Lắng nghe sự kiện khi toàn bộ nội dung HTML đã tải xong

                                            const settingTable = document.getElementById('Setting-Table');
                                            // Lấy phần tử bảng có id="Setting-Table"

                                            if (settingTable) {
                                                // Kiểm tra xem bảng có tồn tại không trước khi thực hiện tiếp.

                                                window.dataTable = new simpleDatatables.DataTable("#Setting-Table", {
                                                    // Khởi tạo một đối tượng DataTable sử dụng thư viện simpleDatatables
                                                    // Cấu hình các tùy chọn cho bảng

                                                    searchable: true, // Cho phép tìm kiếm trong bảng
                                                    perPage: 5, // Số lượng dòng hiển thị trên mỗi trang (phân trang)
                                                    perPageSelect: false, // Không cho phép người dùng thay đổi số dòng trên mỗi trang

                                                    columns: [
                                                        {select: 0, sortable: true, searchable: true}, // Cột 0 có thể sắp xếp và tìm kiếm
                                                        {select: 1, sortable: true, searchable: true}, // Cột 1 có thể sắp xếp và tìm kiếm
                                                        {select: 2, sortable: true, searchable: true}, // Cột 2 có thể sắp xếp và tìm kiếm
                                                        {select: 3, sortable: true, searchable: true}, // Cột 3 có thể sắp xếp và tìm kiếm
                                                        {select: 4, sortable: true, searchable: true}  // Cột 4 có thể sắp xếp và tìm kiếm
                                                    ],

                                                    labels: {
                                                        noRows: "No results found." // Hiển thị thông báo khi không có dữ liệu phù hợp với tìm kiếm
                                                    }
                                                });

                                                const savedPage = localStorage.getItem("currentPage");
                                                // Lấy số trang hiện tại từ localStorage (nếu có), giúp người dùng quay lại đúng trang khi tải lại trang web

                                                if (savedPage) {
                                                    // Nếu tìm thấy trang đã lưu, đặt trang hiện tại của DataTable về trang đó
                                                    dataTable.page(parseInt(savedPage, 10));
                                                }

                                                dataTable.on("datatable.page", function (page) {
                                                    // Lắng nghe sự kiện thay đổi trang của DataTable
                                                    localStorage.setItem("currentPage", page);
                                                    // Lưu số trang hiện tại vào localStorage để duy trì trạng thái khi tải lại trang
                                                });

                                            }
                                        });

        </script>
    </body>
</html>
