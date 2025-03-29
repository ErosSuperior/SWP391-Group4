<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Children Care - Doctor Appointment Booking System</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
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

        <!-- Navbar STart -->
        <jsp:include page="CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Start -->
        <section class="bg-dashboard">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-3 col-lg-4 col-md-5 col-12">
                        <jsp:include page="ProfileSidebar.jsp"/>
                    </div>

                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <div class="rounded shadow mt-0">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Personal Information :</h5>
                            </div>
                            <form id="uploadProfileForm" enctype="multipart/form-data" action="userprofile" method="POST">
                                <div class="p-4 border-bottom">
                                    <div class="row align-items-center">
                                        <div class="col-lg-2 col-md-4">
                                            <img id="previewImage" src="${user.user_image}" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt="User Avatar">
                                        </div>

                                        <div class="col-lg-5 col-md-8 text-center text-md-start mt-4 mt-sm-0">
                                            <h5 class="">Upload your picture</h5>
                                            <p class="text-muted mb-0">For best results, use an image at least 256px by 256px in .jpg or .png format</p>
                                        </div>

                                        <div class="col-lg-5 col-md-12 text-lg-end text-center mt-4 mt-lg-0">                                        
                                            <a style="cursor: pointer;" class="btn btn-primary" onclick="document.getElementById('imageUpload').click();">Upload</a>

                                            <a style="cursor: pointer;" id="btnRemove" class="btn btn-soft-primary ms-2" onclick="removeImage()">Remove</a>

                                            <input type="file" name="avatar" id="imageUpload" class="d-none" accept="image/png, image/jpeg" onchange="previewImage(event)">
                                        </div>
                                    </div><!--end row-->
                                </div>
                                <div class="p-4">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Full Name</label>
                                                <input name="name" id="name" type="text" value="${user.user_fullname}" class="form-control" placeholder="Full Name :" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input name="email" id="email" value="${user.user_email}" type="email" class="form-control" placeholder="Email :" readonly >
                                                <small id="email-warning" style="color: #ff9999; display: none; font-weight: bold;">You are not allowed to edit your email!</small>
                                            </div> 
                                        </div><!--end col-->

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Gender</label>
                                                <select name="gender" id="gender" class="form-control" required="">
                                                    <option value="1" ${user.user_gender == true ? 'selected' : ''}>Male</option>
                                                    <option value="0" ${user.user_gender == false ? 'selected' : ''}>Female</option>                                                          
                                                </select>
                                            </div>
                                        </div><!--end col-->


                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Phone no.</label>
                                                <input name="phone" id="number" value="${user.user_phone}" type="text" class="form-control" placeholder="Phone no. :" required="">
                                            </div>                                                                               
                                        </div><!--end col-->

                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Address</label>
                                                <textarea name="address" id="address" rows="4" class="form-control" placeholder="Address :">${user.user_address}</textarea>
                                            </div>
                                        </div>
                                    </div><!--end row-->

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <p style=" color: #215AEE; font-weight: bold;">${uploadmess}</p>
                                        </div>
                                        <div class="col-sm-12">
                                            <input type="submit" id="submit" class="btn btn-primary" value="Save changes">
                                        </div><!--end col-->
                                    </div><!--end row-->
                                </div>
                            </form>
                        </div>

                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Change Password :</h5>
                            </div>

                            <div class="p-4">
                                <form id="changePasswordForm" action="changepassword" method="POST">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Old password :</label>
                                                <input type="password" name="oldpass" class="form-control" placeholder="Old password" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">New password :</label>
                                                <input id="newpassword" type="password" name="newpass" class="form-control" placeholder="New password" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Re-type New password :</label>
                                                <input type="password" name="confpass" class="form-control" placeholder="Re-type New password" required="">
                                            </div>
                                        </div><!--end col-->
                                        <div class="col-lg-12">
                                            <p id="error-message" style=" color: #215AEE; font-weight: bold; display: ${not empty passmess ? 'block' : 'none'};">${passmess}</p>
                                        </div>
                                        <div class="col-lg-12 mt-2 mb-0">
                                            <button type="submit" class="btn btn-primary">Save password</button>
                                        </div><!--end col-->
                                    </div><!--end row-->
                                </form>
                            </div>
                        </div>

                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->
        <!-- End -->

        <!-- Footer Start -->
        <jsp:include page="Footer.jsp"/>
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- Offcanvas Start -->
        <div class="offcanvas bg-white offcanvas-top" tabindex="-1" id="offcanvasTop">
            <div class="offcanvas-body d-flex align-items-center align-items-center">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="text-center">
                                <h4>Search now${pageContext.request.contextPath}${pageContext.request.contextPath}.</h4>
                                <div class="subcribe-form mt-4">
                                    <form>
                                        <div class="mb-0">
                                            <input type="text" id="help" name="name" class="border bg-white rounded-pill" required="" placeholder="Search">
                                            <button type="submit" class="btn btn-pills btn-primary">Search</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div><!--end col-->
                    </div><!--end row-->
                </div><!--end container-->
            </div>
        </div>
        <!-- Offcanvas End -->

        <!-- Offcanvas Start -->
        <div class="offcanvas offcanvas-end bg-white shadow" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header p-4 border-bottom">
                <h5 id="offcanvasRightLabel" class="mb-0">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="24" class="light-version" alt="">
                    <img src="${pageContext.request.contextPath}/assets/images/logo-light.png" height="24" class="dark-version" alt="">
                </h5>
                <button type="button" class="btn-close d-flex align-items-center text-dark" data-bs-dismiss="offcanvas" aria-label="Close"><i class="uil uil-times fs-4"></i></button>
            </div>
            <div class="offcanvas-body p-4 px-md-5">
                <div class="row">
                    <div class="col-12">
                        <!-- Style switcher -->
                        <div id="style-switcher">
                            <div>
                                <ul class="text-center list-unstyled mb-0">
                                    <li class="d-grid"><a href="javascript:void(0)" class="rtl-version t-rtl-light" onclick="setTheme('style-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="ltr-version t-ltr-light" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-rtl-version t-rtl-dark" onclick="setTheme('style-dark-rtl')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-dark-rtl.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">RTL Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-ltr-version t-ltr-dark" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-dark.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">LTR Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="dark-version t-dark mt-4" onclick="setTheme('style-dark')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-dark.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Dark Version</span></a></li>
                                    <li class="d-grid"><a href="javascript:void(0)" class="light-version t-light mt-4" onclick="setTheme('style')"><img src="${pageContext.request.contextPath}/assets/images/layouts/landing-light.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Light Version</span></a></li>
                                    <li class="d-grid"><a href="${pageContext.request.contextPath}/admin/index.html" target="_blank" class="mt-4"><img src="${pageContext.request.contextPath}/assets/images/layouts/light-dash.png" class="img-fluid rounded-md shadow-md d-block" alt=""><span class="text-muted mt-2 d-block">Admin Dashboard</span></a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end Style switcher -->
                    </div><!--end col-->
                </div><!--end row-->
            </div>

            <div class="offcanvas-footer p-4 border-top text-center">
                <ul class="list-unstyled social-icon mb-0">
                    <li class="list-inline-item mb-0"><a href="https://1.envato.market/doctris-template" target="_blank" class="rounded"><i class="uil uil-shopping-cart align-middle" title="Buy Now"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://dribbble.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-dribbble align-middle" title="dribbble"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.facebook.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-facebook-f align-middle" title="facebook"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://www.instagram.com/shreethemes/" target="_blank" class="rounded"><i class="uil uil-instagram align-middle" title="instagram"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="https://twitter.com/shreethemes" target="_blank" class="rounded"><i class="uil uil-twitter align-middle" title="twitter"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="mailto:support@shreethemes.in" class="rounded"><i class="uil uil-envelope align-middle" title="email"></i></a></li>
                    <li class="list-inline-item mb-0"><a href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/${pageContext.request.contextPath}/index.html" target="_blank" class="rounded"><i class="uil uil-globe align-middle" title="website"></i></a></li>
                </ul><!--end icon-->
            </div>
        </div>
        <!-- Offcanvas End -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const previewImage = document.getElementById("previewImage");
                const originalAvatar = previewImage.src;
                const btnRemove = document.getElementById("btnRemove");

                document.querySelectorAll("form").forEach(form => {
                    form.addEventListener("submit", function () {
                        localStorage.setItem("scrollFormId", this.id);
                    });
                });

                window.addEventListener("load", function () {
                    const formId = localStorage.getItem("scrollFormId");
                    if (formId) {
                        const form = document.getElementById(formId);
                        if (form) {
                            setTimeout(() => {
                                const offset = 200;
                                const targetPosition = form.getBoundingClientRect().top + window.scrollY - offset;
                                window.scrollTo({top: targetPosition, behavior: "smooth"});
                            }, 500);
                        }
                        localStorage.removeItem("scrollFormId");
                    }
                });

                document.getElementById("imageUpload").addEventListener("change", function (event) {
                    if (event.target.files.length > 0) {
                        const reader = new FileReader();
                        reader.onload = function (e) {
                            document.getElementById("previewImage").src = e.target.result;
                        };
                        reader.readAsDataURL(event.target.files[0]);
                    }
                });

                btnRemove.addEventListener("click", function () {
                    previewImage.src = originalAvatar;
                    imageUpload.value = "";
                });

                document.querySelector("#passwordLink")?.addEventListener("click", function () {
                    const targetForm = document.querySelector("#address");
                    if (targetForm) {
                        const offset = 0;
                        const targetPosition = targetForm.getBoundingClientRect().top + window.scrollY - offset;
                        window.scrollTo({top: targetPosition, behavior: "smooth"});
                    }
                });

                const form = document.querySelector("#changePasswordForm");
                const oldPassInput = form.querySelector("input[name='oldpass']");
                const newPassInput = form.querySelector("input[name='newpass']");
                const confPassInput = form.querySelector("input[name='confpass']");
                const errorMessage = document.querySelector("#error-message");

                form.addEventListener("submit", function (event) {
                    const oldPass = oldPassInput.value.trim();
                    const newPass = newPassInput.value.trim();
                    const confPass = confPassInput.value.trim();
                    const hasSpace = /\s/;

                    // Kiểm tra cơ bản
                    if (!oldPass || !newPass || !confPass) {
                        errorMessage.innerText = "All password fields must be filled!";
                        errorMessage.style.display = "block";
                        event.preventDefault();
                        return;
                    }

                    // Kiểm tra độ dài và dấu cách cho mật khẩu mới
                    if (newPass.length <= 6) {
                        errorMessage.innerText = "New password must be more than 6 characters!";
                        errorMessage.style.display = "block";
                        event.preventDefault();
                        return;
                    }

                    if (hasSpace.test(newPass)) {
                        errorMessage.innerText = "New password cannot contain spaces!";
                        errorMessage.style.display = "block";
                        event.preventDefault();
                        return;
                    }

                    // Kiểm tra các điều kiện khác
                    if (newPass === oldPass) {
                        errorMessage.innerText = "New password must be different from the old password!";
                        errorMessage.style.display = "block";
                        event.preventDefault();
                        return;
                    }

                    if (newPass !== confPass) {
                        errorMessage.innerText = "New password and Confirm password do not match!";
                        errorMessage.style.display = "block";
                        event.preventDefault();
                        return;
                    }
                });
            });
        </script>
        <script>
            document.getElementById("email").addEventListener("focus", function () {
                document.getElementById("email-warning").style.display = "block";
                this.style.borderColor = "#ff9999";
            });
        </script>
        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    </body>
</html>
