<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <!-- Select2 -->
        <link href="${pageContext.request.contextPath}/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- SLIDER -->
        <link href="${pageContext.request.contextPath}/assets/css/tiny-slider.css" rel="stylesheet" />
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
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
                        <h5 class="mb-0">Dashboard</h5>
                        <div class="row">
                            <!-- Khối chọn ngày -->
                            <form class="row">
                                <div class="col-md-4">
                                    <div class="mt-3">
                                        <label class="form-label">Start Date</label>
                                        <input type="date" value="${startDate}" name="startDate" id="startDate" class="form-control" required="">
                                    </div>
                                </div>
                                    <input type="hidden" name="selectedYear" value="${selectedYear}">
                                <div class="col-md-4">
                                    <div class="mt-3">
                                        <label class="form-label">End Date</label>
                                        <input type="date" name="endDate" value="${endDate}" id="endDate" max="" class="form-control" required="">
                                    </div>
                                </div>

                                <div class="col-md-4 d-flex align-items-end">
                                    <div class="mt-3">
                                        <button class="btn btn-primary" type="submit">Search</button>
                                        <button class="btn btn-secondary ms-2" type="button" onclick="window.location.href = 'admindashboard';">Reset</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="row">
                            <div class="col-xl-2 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-bed h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${statistic.total_reservation}</h5>
                                            <p class="text-muted mb-0">Total Reservation</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-2 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-file-medical-alt h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0"> $${statistic.avg_cost} </h5>
                                            <p class="text-muted mb-0">Avg. costs</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-2 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-social-distancing h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${statistic.success_reservation}</h5>
                                            <p class="text-muted mb-0">Success Reservation</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-2 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-ambulance h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${statistic.cancelled_reservation}</h5>
                                            <p class="text-muted mb-0">Cancel Reservation</p>
                                        </div>
                                    </div>

                                </div>
                            </div><!--end col-->
                        </div><!--end row-->

                        <div class="row">
                            <div class="col-xl-8 col-lg-7 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Reservation Statistics</h6>
                                        <div class="mb-0 position-relative">
                                            <form action="admindashboard">
                                                <input type="hidden" value="${startDate}" name="startDate">
                                                <input type="hidden" value="${endDate}" name="endDate">
                                                <select class="form-select form-control" name="selectedYear" id="yearchart" onchange="this.form.submit()">
                                                    <c:forEach var="year" items="${years}">
                                                        <option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
                                                    </c:forEach>
                                                </select>
                                            </form>
                                        </div>
                                    </div>
                                    <div id="barChart" class="apex-chart"></div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-4 col-lg-5 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Revenue</h6>

                                    </div>
                                    <div id="donutChart" class="apex-chart"></div>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->

                        <div class="row">
                            <div class="col-xl-6 col-lg-6 mt-4">
                                <div class="card border-0 shadow rounded">
                                    <div class="d-flex justify-content-between align-items-center p-4 border-bottom">
                                        <h6 class="mb-0"><i class="uil uil-calender text-primary me-1 h5"></i> Newly Registered Customers</h6>
                                        <h6 class="text-muted mb-0">${countnewlycustomer} Customer</h6>
                                    </div>

                                    <ul class="list-unstyled mb-0 p-4">
                                        <c:forEach items="${newlycustomer}" var="n">
                                            <li class="mt-4">
                                                <div class="d-flex align-items-center justify-content-between">
                                                    <div class="d-inline-flex">
                                                        <img src="${n.user_image}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                                        <div class="ms-3">
                                                            <h6 class="text-dark mb-0 d-block">${n.user_fullname}</h6>
                                                            <small class="text-muted">${n.user_created_date}</small>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div><!--end col-->


                            <div class="col-xl-6 col-lg-12 mt-4">
                                <div class="card border-0 shadow rounded">
                                    <div class="p-4 border-bottom">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <h6 class="mb-0"><i class="uil uil-user text-primary me-1 h5"></i> Service Reviews</h6>

                                            <div class="mb-0 position-relative" style="font-weight: bold;">AVG Star: ${avgrateStar}<li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                            </div>
                                        </div>
                                    </div>

                                    <ul class="list-unstyled mb-0 p-4" data-simplebar style="height: 355px;">
                                        <c:forEach items="${listfb}" var="f">
                                            <li class="d-flex align-items-center justify-content-between mt-4">
                                                <div class="d-flex align-items-center">
                                                    <img src="${f.image_links}" class="avatar avatar-small rounded-circle border shadow" alt="">
                                                    <div class="flex-1 ms-3">
                                                        <span class="d-block h6 mb-0">${f.service_title}</span>

                                                        <ul class="list-unstyled mb-0">
                                                            <li class="list-inline-item text-muted">(${f.average_rating})</li>
                                                            <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <p class="text-muted mb-0">${f.vote_count} Vote</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div><!--end col-->
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
        <!-- Offcanvas End -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <!-- Chart -->
        <script src="${pageContext.request.contextPath}/assets/js/apexcharts.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/columnchart.init.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script>
                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        const today = new Date().toISOString().split('T')[0];
                                                        document.getElementById('endDate').setAttribute('max', today);
                                                    });
        </script>
        <script>
            var months = [];
            var successData = [];
            var submittedData = [];
            var cancelledData = [];

            <c:forEach var="stat" items="${statistics}">
            months.push("${stat.month}");
            successData.push("${stat.success_count}");
            submittedData.push("${stat.submitted_count}");
            cancelledData.push("${stat.cancelled_count}");
            </c:forEach>

            var optionsBarChart = {
                chart: {
                    type: 'bar',
                    height: 450
                },
                series: [
                    {
                        name: 'Success',
                        data: successData
                    },
                    {
                        name: 'Submitted',
                        data: submittedData
                    },
                    {
                        name: 'Cancelled',
                        data: cancelledData
                    }
                ],
                xaxis: {
                    categories: months  // Các tên tháng đã được chuyển đổi
                },
                colors: ['#28a745', '#ffc107', '#dc3545'], // Màu cho các trạng thái
                dataLabels: {
                    enabled: false
                },
                plotOptions: {
                    bar: {
                        columnWidth: '50%',
                        endingShape: 'rounded'
                    }
                }
            };

            var chartBar = new ApexCharts(document.querySelector("#barChart"), optionsBarChart);
            chartBar.render();
        </script>
        <script>
            // Dữ liệu từ Servlet
            var revenueData = [];
            var categoryLabels = [];

            // Lặp qua từng phần tử trong danh sách revenuebycategory từ request          
            <c:forEach var="c" items="${revenuebycategory}">
            revenueData.push(${c.revenue});  // Dữ liệu doanh thu cho từng category (sử dụng không dấu nháy cho số)
            categoryLabels.push("${c.categoryName}"); // Tên category (vẫn dùng dấu nháy cho chuỗi)
            </c:forEach>
            if (revenueData.length === 0) {
                revenueData.push(0);
                categoryLabels.push("No Data");
            }
            // Cấu hình chart Donut
            var optionsDonutChart = {
                chart: {
                    type: 'donut',
                    height: 450
                },
                series: revenueData, // Dữ liệu doanh thu
                labels: categoryLabels, // Tên các category
                plotOptions: {
                    pie: {
                        donut: {
                            size: '60%', // Kích thước vòng trong
                            labels: {
                                show: true,
                                total: {
                                    show: true,
                                    label: 'Total Revenue', // Tên của tổng số
                                    formatter: function (w) {
                                        return w.globals.seriesTotals.reduce((a, b) => a + b, 0); // Tổng doanh thu
                                    }
                                }
                            }
                        }
                    }
                },
                legend: {
                    position: 'bottom'
                },
                colors: ['#008FFB', '#00E396', '#FEB019', '#FF4560'] // Màu sắc vòng
            };

            // Vẽ chart
            var chartDonut = new ApexCharts(document.querySelector("#donutChart"), optionsDonutChart);
            chartDonut.render();
        </script>

    </body>
</html>
