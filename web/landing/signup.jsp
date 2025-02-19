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
        <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-icon btn-primary">
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
                                    <!-- Username -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Username <span class="text-danger">*</span></label>
                                            <input type="text" class="form-control" placeholder="Username" name="username" required=""/>
                                        </div>
                                    </div>
                                    <!-- Gender -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Gender <span class="text-danger">*</span></label><br/>
                                            <label class="form-check-label">
                                                <input type="radio" name="gender" value="true" required/> Male
                                            </label>
                                            <label class="form-check-label ms-3">
                                                <input type="radio" name="gender" value="false" required/> Female
                                            </label>
                                        </div>
                                    </div>
                                    <!-- Address -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Address <span class="text-danger">*</span></label>
                                            <input type="text" class="form-control" placeholder="Address" name="address" required=""/>
                                        </div>
                                    </div>
                                    <!-- Email -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Email <span class="text-danger">*</span></label>
                                            <input type="email" class="form-control" placeholder="Email" name="useremail" required=""/>
                                        </div>
                                    </div>
                                    <!-- Phone -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Phone <span class="text-danger">*</span></label>
                                            <input type="text" class="form-control" placeholder="Phone" name="phone" required=""/>
                                        </div>
                                    </div>
                                    <!-- Password -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Password <span class="text-danger">*</span></label>
                                            <input type="password" class="form-control" placeholder="Password" name="password" required=""/>
                                        </div>
                                    </div>
                                    <!-- Repeat Password -->
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <label class="form-label">Repeat Password <span class="text-danger">*</span></label>
                                            <input type="password" class="form-control" placeholder="Repeat Password" name="repeatpassword" required=""/>
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
                                            <a href="<%= request.getContextPath() %>/login.jsp" class="text-dark fw-bold">Login</a>
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
</body>
</html>
