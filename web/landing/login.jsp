<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>Children Care - Doctor Appointment Booking System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
</head>

<body>
    <div class="back-to-home rounded d-none d-sm-block">
        <a href="<%= request.getContextPath() %>/home" class="btn btn-icon btn-primary">
            <i data-feather="home" class="icons"></i>
        </a>
    </div>

    <section class="bg-home d-flex bg-light align-items-center"
             style="background: url('<%= request.getContextPath() %>/assets/images/bg/bg-lines-one.png') center;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-5 col-md-8">
                    <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png" height="70"
                         class="mx-auto d-block" alt="">
                    <div class="card login-page bg-white shadow mt-4 rounded border-0">
                        <div class="card-body">
                            <h4 class="text-center">Sign In</h4>
                            <form action="<%= request.getContextPath() %>/login" class="login-form mt-4" method="GET"> 
                                <div class="mb-3">
                                    <label class="form-label">Your Email <span class="text-danger">*</span></label>
                                    <input type="email" class="form-control" placeholder="Email" name="email" 
                                           value="<%= request.getAttribute("user_email") != null ? request.getAttribute("user_email") : "" %>" required/>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Password <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" placeholder="Password" name="password" required/>
                                </div>

                                <div class="d-flex justify-content-between">
                                    <a href="<%= request.getContextPath() %>/resetredirect" class="text-dark h6 mb-0">
                                        Forgot password ?
                                    </a>
                                </div>

                                <div class="d-grid mt-3">
                                    <button class="btn btn-primary">Sign in</button>
                                </div>

                                <div class="text-center mt-3">
                                    <p>
                                        <small class="text-dark">Don't have an account?</small>
                                        <a href="<%= request.getContextPath() %>/register" class="text-dark fw-bold">Sign Up</a>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div><!-- end card -->
                </div> <!-- end col -->
            </div><!-- end row -->
        </div> <!-- end container -->
    </section><!-- end section -->

    <!-- Thêm thư viện SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            let errorMessage = "<%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>";

            if (errorMessage) {
                Swal.fire({
                    title: "Error!",
                    text: errorMessage,
                    icon: "error",
                    confirmButtonText: "Try Again"
                });
            }
        });
    </script>

    <!-- Javascript -->
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
</body>
</html>
