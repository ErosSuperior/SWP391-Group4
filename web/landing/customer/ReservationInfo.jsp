<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>
        <!-- Loader -->
        <!-- Navbar STart -->
        <jsp:include page="../CustomerNavbar.jsp"/>
        <!-- Navbar End -->
        <section class="bg-dashboard">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5 col-12">
                        <jsp:include page="../ProfileSidebar.jsp"/>
                    </div>
                    <div class="container">
                        <div class="container mt-4">
                            <!-- View Invoice Modal -->
                            <div class="modal fade" id="invoiceModal" tabindex="-1" aria-labelledby="invoiceLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header border-bottom p-3">
                                            <h5 class="modal-title" id="invoiceLabel">Reservation Information</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body p-3 pt-4">
                                            <div class="row mb-4">
                                                <div class="col-lg-8 col-md-6">
                                                    <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png?v=<%= System.currentTimeMillis() %>" height="140" class="logo-light-mode" alt="">
                                                </div>
                                                <div class="col-lg-4 col-md-6">
                                                    <ul class="list-unstyled">
                                                        <li class="d-flex"><small class="mb-0 text-muted">Invoice no.: </small><small class="mb-0 text-dark" id="invoiceNoDisplay"></small></li>
                                                        <li class="d-flex mt-2"><small class="mb-0 text-muted">Email: </small><small class="mb-0">${sessionScope.account.user_email}</small></li>
                                                        <li class="d-flex mt-2"><small class="mb-0 text-muted">Phone: </small><small class="mb-0">${sessionScope.account.user_phone}</small></li>
                                                        <li class="d-flex mt-2"><small class="mb-0 text-muted">Address: </small><small class="mb-0">${sessionScope.account.user_address}</small></li>
                                                        <li class="d-flex mt-2"><small class="mb-0 text-muted">Patient Name: </small><small class="mb-0">${sessionScope.account.user_fullname}</small></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="pt-4 border-top">
                                                <h5 class="text-muted fw-bold">Reservation <span class="badge badge-pill badge-soft-success fw-normal ms-2" id="resstatus"></span></h5>
                                                <div class="invoice-table pb-4">
                                                    <div class="table-responsive shadow rounded mt-4">
                                                        <table class="table table-center invoice-tb mb-0">
                                                            <thead>
                                                                <tr>
                                                                    <th>No.</th>
                                                                    <th>Item</th>
                                                                    <th class="text-center">Qty</th>
                                                                    <th>Rate</th>
                                                                    <th>Total</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row">${res.reservation_id}</th>
                                                                    <td>a</td>
                                                                    <td class="text-center">a</td>
                                                                    <td>$ a</td>
                                                                    <td>$ a</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-4 col-md-5 ms-auto">
                                                            <ul class="list-unstyled h6 fw-normal mt-4 mb-0">
                                                                <li class="text-muted d-flex justify-content-between">Subtotal :<small class="text-dark" id="subtotalprice"></small></li>
                                                                <li class="text-muted d-flex justify-content-between">Taxes :<span> 0</span></li>
                                                                <li class="d-flex justify-content-between">Total :<small class="text-dark" id="totalprice"></small></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
