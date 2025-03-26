<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Sign Up - Your App</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- favicon -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
        <!-- Css -->

        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

    </head>

    <body>
        <!-- Loader (nếu có) -->
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>
        <!-- End Loader -->

        <!-- Back to home (nếu cần) -->
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="<%= request.getContextPath() %>/home" class="btn btn-icon btn-primary">
                <i data-feather="home" class="icons"></i>
            </a>
        </div>

        <!-- Hero Start -->
        <section class="bg-home d-flex bg-light align-items-center"
                 style="background: url('<%= request.getContextPath() %>/assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png" height="24"
                             class="mx-auto d-block" alt="">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Sign Up</h4>

                                <%-- Hiển thị thông báo lỗi nếu có (các lỗi được set qua request attribute "r") --%>
                                <%
                                    String error = (String) request.getAttribute("r");
                                    if (error != null) {
                                %>
                                <div class="alert alert-danger mt-3">
                                    <%= error %>
                                </div>
                                <%
                                    }
                                %>

                                <!-- Form đăng ký -->
                                <form action="<%= request.getContextPath() %>/register" class="login-form mt-4" method="POST">
                                    <div class="row">
                                        <!-- Email -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                                <input type="email" class="form-control" placeholder="Email" name="useremail" required="" id="email">
                                                <div class="invalid-feedback" id="emailError"></div>
                                            </div>
                                        </div>
                                        <!-- Username -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Full name <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Full name" name="username" required="" id="username">
                                                <div class="invalid-feedback" id="usernameError"></div>
                                            </div>
                                        </div>
                                        <!-- Gender -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Gender <span class="text-danger">*</span></label><br/>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" name="gender" id="male" value="true">
                                                    <label class="form-check-label" for="male">Male</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" name="gender" id="female" value="false">
                                                    <label class="form-check-label" for="female">Female</label>
                                                </div>
                                                <div class="invalid-feedback" id="genderError"></div>
                                            </div>
                                        </div>
                                        <!-- Address -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Address <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Address" name="address" required="" id="address">
                                                <div class="invalid-feedback" id="addressError"></div>
                                            </div>
                                        </div>
                                        <!-- Phone -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Phone <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Phone" name="phone" required="" id="phone">
                                                <div class="invalid-feedback" id="phoneError"></div>
                                            </div>
                                        </div>
                                        <!-- Password -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Password <span class="text-danger">*</span></label>
                                                <input type="password" class="form-control" placeholder="Password" name="password" required="" id="password">
                                                <div class="invalid-feedback" id="passwordError"></div>
                                            </div>
                                        </div>
                                        <!-- Repeat Password -->
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Repeat Password <span class="text-danger">*</span></label>
                                                <input type="password" class="form-control" placeholder="Repeat Password" name="repeatpassword" required="" id="repeatpassword">
                                                <div class="invalid-feedback" id="confirmPasswordError"></div>
                                            </div>
                                        </div>
                                        <!-- Submit Button -->
                                        <div class="col-lg-12 mb-0">
                                            <div class="d-grid">
                                                <button class="btn btn-primary">Sign Up</button>
                                            </div>
                                        </div>
                                        <!-- Link đến trang login -->
                                        <div class="col-12 text-center">
                                            <p class="mb-0 mt-3">
                                                <small class="text-dark me-2">Already have an account?</small>
                                                <a href="<%= request.getContextPath() %>/loginnavigation" class="text-dark fw-bold">Login</a>
                                            </p>    
                                        </div>
                                    </div>
                                </form>
                            </div><!-- end card-body -->
                        </div><!-- end card -->
                    </div> <!-- end col -->
                </div><!-- end row -->
            </div> <!-- end container -->
        </section><!-- end section -->
        <!-- End Hero -->

        <!-- Javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelector(".login-form").addEventListener("submit", function (event) {
                    let errorMessages = [];
                    let email = document.getElementById("email").value;
                    let username = document.getElementById("username").value;
                    let gender = document.querySelector('input[name="gender"]:checked');
                    let address = document.getElementById("address").value;
                    let phone = document.getElementById("phone").value;
                    let password = document.getElementById("password").value;
                    let repeatPassword = document.getElementById("repeatpassword").value;

                    // Reset error messages
                    document.getElementById("emailError").textContent = "";
                    document.getElementById("usernameError").textContent = "";
                    document.getElementById("genderError").textContent = "";
                    document.getElementById("addressError").textContent = "";
                    document.getElementById("phoneError").textContent = "";
                    document.getElementById("passwordError").textContent = "";
                    document.getElementById("confirmPasswordError").textContent = "";

                    // Validate email
                    const emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                    if (!emailRegex.test(email)) {
                        errorMessages.push("❌ Invalid email format! Please use a valid email address (example: user@example.com)");
                        document.getElementById("emailError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    // Validate username
                    if (username.length < 3) {
                        errorMessages.push("❌ Full name must be at least 3 characters! Please enter your full name.");
                        document.getElementById("usernameError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    // Validate gender
                    if (!gender) {
                        errorMessages.push("❌ Please select gender.");
                        document.getElementById("genderError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    // Validate address
                    if (address.length < 5) {
                        errorMessages.push("❌ Address must be at least 5 characters! Please provide a complete address.");
                        document.getElementById("addressError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    // Validate phone
                    const phoneRegex = "^[0-9]{10,11}$";
                    if (!phoneRegex.test(phone)) {
                        errorMessages.push("❌ Phone number must be 10-11 digits! Please enter a valid phone number without spaces or special characters.");
                        document.getElementById("phoneError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    // Validate password
                    const passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
                    if (!passwordRegex.test(password)) {
                        errorMessages.push("❌ Password must be at least 6 characters and contain both letters and numbers!\n" +
                                       "Example: Password123");
                        document.getElementById("passwordError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    // Validate confirm password
                    if (password !== repeatPassword) {
                        errorMessages.push("❌ Passwords do not match! Please make sure both password fields are identical.");
                        document.getElementById("confirmPasswordError").textContent = errorMessages[errorMessages.length - 1];
                    }

                    if (errorMessages.length > 0) {
                        event.preventDefault(); // Ngăn chặn gửi form
                        // Hiển thị lỗi trực tiếp dưới mỗi trường
                    }
                });
            });
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                // Kiểm tra và hiển thị thông báo lỗi từ server
                const error = "<%= request.getAttribute("r") != null ? request.getAttribute("r") : "" %>";
                const isSwal = "<%= request.getAttribute("swal") != null ? request.getAttribute("swal") : "false" %>";
                
                if (error && isSwal === "true") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: error,
                        confirmButtonText: 'OK',
                        customClass: {
                            popup: 'swal-wide'
                        }
                    });
                }
            });
        </script>
    </body>
</html>
