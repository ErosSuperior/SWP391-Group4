<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Children Care - Verify Registration</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
        <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <!-- Back to home -->
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="<%= request.getContextPath() %>/home" class="btn btn-icon btn-primary">
                <i data-feather="home" class="icons"></i>
            </a>
        </div>

        <!-- Hero Start -->
        <section class="bg-home d-flex bg-light align-items-center" style="background: url('<%= request.getContextPath() %>/assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <img src="<%= request.getContextPath() %>/assets/images/logo-dark.png" height="24"
                             class="mx-auto d-block" alt="">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Verify Registration</h4>

                                <!-- Form xác minh -->
                                <form action="<%= request.getContextPath() %>/verifyRegister" method="post" class="login-form mt-4">
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

                            </div><!-- end card-body -->
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
                let successMessage = "<%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %>";

                if (errorMessage) {
                    Swal.fire({
                        title: "Error!",
                        text: errorMessage,
                        icon: "error",
                        confirmButtonText: "Try Again"
                    });
                }

                if (successMessage) {
                    Swal.fire({
                        title: "Success!",
                        text: successMessage,
                        icon: "success",
                        confirmButtonText: "OK"
                    }).then(() => {
                        window.location.href = "<%= request.getContextPath() %>/loginnavigation";
                    });
                }
            });

            document.addEventListener("DOMContentLoaded", function () {
                let inputs = document.querySelectorAll("input[name^='authcode']");
                inputs.forEach((input, index) => {
                    input.addEventListener("input", function () {
                        if (this.value.length === 1 && index < inputs.length - 1) {
                            inputs[index + 1].focus();
                        }
                    });

                    input.addEventListener("keydown", function (event) {
                        if (event.key === "Backspace" && this.value === "" && index > 0) {
                            inputs[index - 1].focus();
                        }
                    });

                    input.addEventListener("keypress", function (event) {
                        if (!/[0-9]/.test(event.key)) {
                            event.preventDefault();
                        }
                    });
                });
            });
        </script>

        <!-- Javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
    </body>
</html>
