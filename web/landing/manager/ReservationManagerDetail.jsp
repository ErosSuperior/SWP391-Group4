<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Reservation Detail</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo.png"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/remixicon.css" type="text/css"/>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.min.css" type="text/css"/>
    </head>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp" />

                <div class="layout-specing">
                    <div class="d-md-flex flex-column">
                        <h5 class="mb-0">Reservation Detail</h5>
                        <nav aria-label="breadcrumb" class="mt-2">
                            <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/reservationList">Reservation List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Reservation Detail</li>
                            </ul>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Reservation ID</label>
                                                    <input name="reservation_id" type="text" value="${reservation.reservation_id}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">User ID</label>
                                                    <input name="user_id" type="text" value="${reservation.user_id}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Total Price</label>
                                                    <input name="total_price" type="text" value="${reservation.total_price}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Note</label>
                                                    <input name="note" type="text" value="${reservation.note}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <input name="status" type="text" value="${reservation.status == 0 ? 'Pending' : reservation.status == 1 ? 'Not Yet' : reservation.status == 2 ? 'Confirmed (Not Operated)' : reservation.status == 3 ? 'Confirmed (Operated)':reservation.status == 4 ? 'Cancelled':reservation.status == 5 ? 'Operating':'Unknown'}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Payment Status</label>
                                                    <input name="payment_status" type="text" value="${reservation.payment_status == 0 ? 'Unpaid' : reservation.payment_status == 1 ? 'Paid' : 'Unknown'}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Created Date</label>
                                                    <input name="created_date" type="text" value="${reservation.created_date}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Receiver Name</label>
                                                    <input name="receiver_name" type="text" value="${reservation.receiver_name}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Receiver Email</label>
                                                    <input name="receiver_email" type="text" value="${reservation.receiver_email}" class="form-control" readonly>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Receiver Number</label>
                                                    <input name="receiver_number" type="text" value="${reservation.receiver_number}" class="form-control" readonly>
                                                </div>
                                            </div>
                                        </div> <!-- Đóng div.row -->
                                    </form>
                                </div> <!-- Đóng div.card -->
                            </div> <!-- Đóng div.col-md-12 -->
                        </div>
                    </div>                     <!-- Đóng div.layout-specing -->
            </main> <!-- Đóng main.page-content -->
        </div> <!-- Đóng div.page-wrapper -->

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

    </body>
</html>