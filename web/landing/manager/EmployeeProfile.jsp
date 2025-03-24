<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
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

        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Employee Profile</h5>
                        </div>
                        <br>
                        <div class="row">                            
                            <div class="col-xl-4 col-lg-4 col-md-5 col-12">
                                <div class="bg-white rounded shadow overflow-hidden">
                                    <div class="card border-0">
                                        <img src="${pageContext.request.contextPath}/assets/images/bg/bg-profile.jpg" class="img-fluid" alt="">
                                    </div>

                                    <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                        <img src="${user.user_image}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                        <h5 class="mt-3 mb-1">${user.user_fullname}</h5>
                                    </div>

                                    <ul class="list-unstyled sidebar-nav mb-0">
                                        <li class="navbar-item"><a style="cursor: pointer;" class="navbar-link"><i class="ri-user-settings-line align-middle navbar-icon"></i> Profile Settings</a></li>
                                        <li class="navbar-item"><a style="cursor: pointer;" id="passwordLink" class="navbar-link"><i class="ri-device-recover-line align-middle navbar-icon"></i>  Password</a></li>
                                    </ul>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">                               
                                <div class="rounded shadow mt-0">
                                    <div class="p-4 border-bottom">
                                        <h5 class="mb-0">Personal Information :</h5>
                                    </div>
                                    <form enctype="multipart/form-data" action="userprofile" method="POST">
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
                                                    <p id="error-message" style=" color: #215AEE; font-weight: bold;">${uploadmess}</p>
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
                                                        <input type="password" name="newpass" class="form-control" placeholder="New password" required="">
                                                    </div>
                                                </div><!--end col-->

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Re-type New password :</label>
                                                        <input type="password" name="confpass" class="form-control" placeholder="Re-type New password" required="">
                                                    </div>
                                                </div><!--end col-->
                                                <div class="col-lg-12">
                                                    <p style=" color: #215AEE; font-weight: bold; display: ${not empty passmess ? 'block' : 'none'};">${passmess}</p>
                                                </div>
                                                <div class="col-lg-12 mt-2 mb-0">
                                                    <button type="submit" class="btn btn-primary">Save password</button>
                                                </div><!--end col-->
                                            </div><!--end row-->
                                        </form>
                                    </div>
                                </div>

                            </div><!--end col
                            <!--end col-->
                        </div><!--end row-->
                    </div>
                </div><!--end container-->
                <script>
                    document.addEventListener("DOMContentLoaded", function () {

                        const previewImage = document.getElementById("previewImage");
                        const originalAvatar = previewImage.src;

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
                                    form.scrollIntoView({behavior: "smooth", block: "start"});
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


                        document.querySelector("#passwordLink").addEventListener("click", function () {
                            const targetForm = document.querySelector("#changePasswordForm");
                            if (targetForm) {
                                targetForm.scrollIntoView({behavior: "smooth", block: "start"});
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

                            if (oldPass === "" || newPass === "" || confPass === "") {
                                errorMessage.innerText = "Invalid password! It cannot be empty or contain only spaces.";
                                errorMessage.style.display = "block";
                                event.preventDefault();
                                return;
                            }

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
                <!-- Footer Start -->

                <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->
        <!-- View Invoice End -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

    </body>
</html>
