<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
                            <h4 class="text-center">Verify email</h4>
                            
                            
                            <form action="verifyregister" method="post">
            <div style="text-align: center; margin-top: 30px">
                <input style="width: 400px;" type="text" name="authcode" > 
                <input style="margin-left: 20px; background: blue" type="submit" value="verify">
            </div>
        </form>
                     <form action="verifyregister" method="post">
                   <div class="container height-100 d-flex justify-content-center align-items-center">
                    <div class="position-relative">
                        <div class="card p-2 text-center">
                            <h6>Please enter the one time password <br> to verify your account</h6>
                            <div> <span>A code has been sent to</span> <small>*******@gmail.com</small> </div>
                            <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2"> 
                                <input name="authcode1" class="m-2 text-center form-control rounded" type="text" id="first" maxlength="1" /> 
                                <input name="authcode2" class="m-2 text-center form-control rounded" type="text" id="second" maxlength="1" /> 
                                <input name="authcode3" class="m-2 text-center form-control rounded" type="text" id="third" maxlength="1" />
                                <input name="authcode4" class="m-2 text-center form-control rounded" type="text" id="fourth" maxlength="1" /> 
                                <input name="authcode5" class="m-2 text-center form-control rounded" type="text" id="fifth" maxlength="1" />
                                <input name="authcode6" class="m-2 text-center form-control rounded" type="text" id="sixth" maxlength="1" /> 
                            </div>
                            <input hidden="" type="text" name="formid" value="<%= request.getAttribute("formid").toString() %>">
                            <div class="mt-4"> <button type="submit" value="verify" class="btn btn-danger px-4 validate">Validate</button> </div>
                        </div>
                    </div>
                </div>
                </form>
                        </div>
                    </div><!-- end card -->
                </div> <!-- end col -->
            </div><!-- end row -->
        </div> <!-- end container -->
    </section><!-- end section -->
    <!-- Hero End -->

    <!-- javascript -->
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
</body>
</html>
