<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>Children Care - Doctor Appointment Booking System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Các thẻ meta và liên kết CSS -->
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
    <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
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

    <!-- Back to home -->
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
                            <h4 class="text-center">Verify Registration</h4>
                            
                            <!-- Hiển thị lỗi nếu có (do VerifyRegisterController set request attribute "error") -->
                            <%
                                String error = (String) request.getAttribute("error");
                                if (error != null) {
                            %>
                                <div class="alert alert-danger mt-3">
                                    <%= error %>
                                </div>
                            <%
                                }
                            %>
                            
                            <!-- Form xác minh: gửi đến servlet verifyRegister -->
                            <form action="<%= request.getContextPath() %>/verifyRegister" method="post" class="login-form mt-4">
                                <!-- Hidden field để truyền formid (mã được tạo trong RegisterServlet) -->
                                <input type="hidden" name="formid" value="<%= request.getAttribute("formid") != null ? request.getAttribute("formid") : "" %>" />
                                
                                <div class="mb-3">
                                    <label class="form-label">Enter your 6-digit verification code <span class="text-danger">*</span></label>
                                    <div class="d-flex">
                                        <input type="text" name="authcode1" maxlength="1" class="form-control me-1" required/>
                                        <input type="text" name="authcode2" maxlength="1" class="form-control me-1" required/>
                                        <input type="text" name="authcode3" maxlength="1" class="form-control me-1" required/>
                                        <input type="text" name="authcode4" maxlength="1" class="form-control me-1" required/>
                                        <input type="text" name="authcode5" maxlength="1" class="form-control me-1" required/>
                                        <input type="text" name="authcode6" maxlength="1" class="form-control" required/>
                                    </div>
                                </div>
                                
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Verify</button>
                                </div>
                            </form>
                            
                            <div class="text-center mt-3">
                                <small>Didn't receive a code? <a href="#">Resend</a></small>
                            </div>
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
</body>
</html>
