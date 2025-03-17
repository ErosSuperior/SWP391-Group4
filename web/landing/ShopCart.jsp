<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
    <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
    <meta name="author" content="Shreethemes" />
    <meta name="email" content="support@shreethemes.in" />
    <meta name="website" content="https://shreethemes.in" />
    <meta name="Version" content="v1.2.0" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Icons -->
    <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
    <!-- Css -->
    <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.2);
        }
        .modal-content {
            margin: 15% auto;
            padding: 0;
            border: 1px solid #888;
            width: 50%;
            max-width: 500px;
        }
        .modal-header {
            background-color: #1BA8FF;
            color: #FFFFFF;
            padding: 10px;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .modal-body {
            padding: 10px;
            background-color: white;
            border-radius: 5px;
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
    <!-- Navbar -->
    <jsp:include page="CustomerNavbar.jsp"/>
    <!-- Hero -->
    <div class="position-relative">
        <div class="shape overflow-hidden text-white">
            <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
            </svg>
        </div>
    </div>
    <!-- Start -->
    <section class="section">
        <!-- Modal Delete -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <strong>Delete Your Service</strong>
                    <span onclick="closeModal()" style="font-size: 25px; cursor: pointer;">×</span>
                </div>
                <div class="modal-body">
                    <p>Do you want to delete this service?</p>
                    <div class="col-md-12 form-group" style="display: flex; justify-content: space-between;">
                        <button onclick="closeModal()" style="border: 2px solid #1BA8FF; background-color: #FFFFFF; color: #1BA8FF; padding: 10px 20px; border-radius: 2px; cursor: pointer; font-size: 16px; width: 200px;">No</button>
                        <form action="mycart" method="POST">
                            <input type="hidden" name="delete_id" id="ServiceId" value="0">
                            <button type="submit" style="border: 1px solid #FF424E; background-color: #FF424E; color: #FFFFFF; padding: 10px 20px; border-radius: 2px; cursor: pointer; font-size: 16px; width: 200px;">Yes</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive bg-white shadow rounded">
                        <table class="table table-center table-padding mb-0">
                            <thead>
                                <tr>
                                    <th class="border-bottom p-3" style="min-width:20px"><input type="checkbox" id="selectAll"></th>
                                    <th class="border-bottom p-3" style="min-width:20px"></th>
                                    <th class="border-bottom p-3" style="min-width: 300px;">Service</th>
                                    <th class="border-bottom text-center p-3" style="min-width: 160px;">Price</th>
                                    <th class="border-bottom text-center p-3" style="min-width: 190px;">Qty</th>
                                    <th class="border-bottom text-end p-3" style="min-width: 50px;">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${listreservation}">
                                    <tr data-service-id="${r.service_id}">
                                        <td class="p-3 text-center"><input type="checkbox" class="serviceCheckbox" value="${r.service_id}"></td>
                                        <td class="h5 p-3 text-center"><a onclick="openModal(${r.service_id});" style="cursor: pointer;" class="text-danger"><i class="uil uil-times"></i></a></td>
                                        <td class="p-3">
                                            <div class="d-flex align-items-center">
                                                <img src="${r.image_link}" class="img-fluid avatar avatar-small rounded shadow" style="height:auto;" alt="">
                                                <h6 class="mb-0 ms-3">
                                                    <a href="${pageContext.request.contextPath}/customer/customerdetailService?serviceId=${r.service_id}" class="text-decoration-none">${r.service_title}</a>
                                                    <div>
                                                        <small class="text-muted" style="display: block;">ID: ${r.service_id}</small>
                                                        <small class="text-muted" style="display: block; margin-top: 4px;">${r.category_name}</small>
                                                    </div>
                                                </h6>
                                            </div>
                                        </td>
                                        <td class="text-center p-3 service_price">$ ${r.price}</td>
                                        <td class="text-center shop-list p-3">
                                            <div class="qty-icons">
                                                <button onclick="this.parentNode.querySelector('input[type=number]').stepDown(); this.parentNode.querySelector('input[type=number]').dispatchEvent(new Event('change'));" class="btn btn-icon btn-primary minus">-</button>
                                                <input min="1" name="quantity" value="${r.quantity}" type="number" class="btn btn-icon btn-primary qty-btn quantity">
                                                <button onclick="this.parentNode.querySelector('input[type=number]').stepUp(); this.parentNode.querySelector('input[type=number]').dispatchEvent(new Event('change'));" class="btn btn-icon btn-primary plus">+</button>
                                            </div>
                                        </td>
                                        <td class="text-end font-weight-bold p-3 subtotal">$ ${r.price * r.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-6 mt-4 pt-2">
                    <a href="${pageContext.request.contextPath}/customer/customerlistService" class="btn btn-primary">Shop More</a>
                </div>
                <div class="col-lg-4 col-md-6 ms-auto mt-4 pt-2">
                    <div class="table-responsive bg-white rounded shadow">
                        <table class="table table-center table-padding mb-0">
                            <tbody>
                                <tr>
                                    <td class="h6 p-3">Subtotal</td>
                                    <td class="text-end font-weight-bold p-3" id="totalAmount0">$ 0</td>
                                </tr>
                                <tr>
                                    <td class="h6 p-3">Taxes</td>
                                    <td class="text-end font-weight-bold p-3">$ 0</td>
                                </tr>
                                <tr class="bg-light">
                                    <td class="h6 p-3">Total</td>
                                    <td class="text-end font-weight-bold p-3" id="totalAmount1">$ 0</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="mt-4 pt-2 text-end">
                        <a href="#" onclick="proceedToCheckout(event)" class="btn btn-primary">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- JavaScript -->
    <script>
        $(document).ready(function () {
            updateTotal();

            // Select All Checkbox
            $("#selectAll").on("change", function () {
                $(".serviceCheckbox").prop("checked", $(this).prop("checked"));
                updateSelectedServices();
                updateTotal();
            });

            // Individual Service Checkbox
            $(".serviceCheckbox").on("change", function () {
                if (!$(this).prop("checked")) {
                    $("#selectAll").prop("checked", false);
                } else if ($(".serviceCheckbox:checked").length === $(".serviceCheckbox").length) {
                    $("#selectAll").prop("checked", true);
                }
                updateSelectedServices();
                updateTotal();
            });

            // Update selected services
            function updateSelectedServices() {
                var selectedServices = [];
                $(".serviceCheckbox:checked").each(function () {
                    selectedServices.push($(this).val());
                });
                console.log("Selected Services:", selectedServices);
            }

            // Quantity Change
            $(".quantity").each(function () {
                $(this).data("previous-quantity", $(this).val());
            });

            $(".quantity").on("change", function () {
                var quantity = $(this).val();
                var row = $(this).closest("tr");
                var service_id = row.data("service-id");
                var price = parseFloat(row.find(".service_price").text().replace('$', '').trim());
                var previousQuantity = $(this).data("previous-quantity");

                $.ajax({
                    url: "mycart",
                    method: "POST",
                    data: {
                        service_id: service_id,
                        quantity: quantity
                    },
                    success: function (response) {
                        if (response.status === "1") {
                            var newSubtotal = quantity * price;
                            row.find(".subtotal").text("$ " + newSubtotal.toFixed(1));
                            row.find(".quantity").data("previous-quantity", quantity);
                            updateTotal();
                        } else {
                            row.find(".quantity").val(previousQuantity);
                        }
                    },
                    error: function () {
                        row.find(".quantity").val(previousQuantity);
                    }
                });
            });

            // Update Total based on selected services
            function updateTotal() {
                var total = 0;
                $(".serviceCheckbox:checked").each(function () {
                    var row = $(this).closest("tr");
                    total += parseFloat(row.find(".subtotal").text().replace('$', '').trim());
                });
                $("#totalAmount0").text("$ " + total.toFixed(1)); // Subtotal
                var taxes = 0; // Có thể thêm logic tính thuế
                total += taxes;
                $("#totalAmount1").text("$ " + total.toFixed(1)); // Total
            }

            // Proceed to Checkout
            window.proceedToCheckout = function(event) {
                event.preventDefault();
                if ($(".serviceCheckbox:checked").length === 0) {
                    alert("You have not selected any products to buy yet.");
                } else {
                    window.location.href = "${pageContext.request.contextPath}/checkout";
                }
            }
        });

        // Modal Functions
        function openModal(serviceId) {
            const modal = document.getElementById("myModal");
            const serviceField = document.getElementById("ServiceId");
            if (modal && serviceField) {
                serviceField.value = serviceId;
                modal.style.display = "block";
            }
        }

        function closeModal() {
            const modal = document.getElementById("myModal");
            if (modal) {
                modal.style.display = "none";
            }
        }
    </script>
    <!-- Footer -->
    <jsp:include page="Footer.jsp"/>
    <!-- Back to top -->
    <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>
</html>