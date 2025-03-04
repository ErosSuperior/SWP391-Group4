<%-- 
    Document   : UserManagerDetail
    Created on : Mar 3, 2025, 10:23:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>User Detail</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo.png"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/remixicon.css" type="text/css"/>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.min.css" type="text/css"/>
    </head>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp" />

                <div class="layout-specing">
                    <div class="d-md-flex flex-column">
                        <h5 class="mb-0">User Detail</h5>
                        <nav aria-label="breadcrumb" class="mt-2">
                            <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/adminList">User List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">User Detail</li>
                            </ul>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" action="${pageContext.request.contextPath}/admin/manageedits" method="post" onsubmit="return validateForm()">
                                        <div class="row">
                                            <c:if test="${not empty user.user_id}">
                                                <div class="col-md-4">
                                                    <div class="mb-3">
                                                        <label class="form-label" for="userId">User ID</label>
                                                        <input name="userId" id="userId" type="text" value="${user.user_id}" class="form-control" readonly>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Full Name<span style="color: red">*</span></label>
                                                    <input name="fullname" id="fullname" type="text" value="${user.user_fullname}" class="form-control" required>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Email<span style="color: red">*</span></label>
                                                    <input name="email" id="email" type="email" value="${user.user_email}" class="form-control" required>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Phone</label>
                                                    <input name="phone" id="phone" type="text" value="${user.user_phone}" class="form-control">
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Gender</label>
                                                    <select name="gender" id="gender" class="form-control">
                                                        <option value="true" ${user.user_gender ? 'selected' : ''}>Male</option>
                                                        <option value="false" ${!user.user_gender ? 'selected' : ''}>Female</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Role</label>
                                                    <select name="roleId" id="roleId" class="form-control">
                                                        <option value="1" ${user.role_id == 1 ? 'selected' : ''}>Admin</option>
                                                        <option value="2" ${user.role_id == 2 ? 'selected' : ''}>Staff</option>
                                                        <option value="3" ${user.role_id == 3 ? 'selected' : ''}>Customer</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <select name="status" id="status" class="form-control">
                                                        <option value="true" ${user.user_status ? 'selected' : ''}>Active</option>
                                                        <option value="false" ${!user.user_status ? 'selected' : ''}>Inactive</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Address</label>
                                                    <textarea name="address" id="address" class="form-control" rows="3">${user.user_address}</textarea>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <label class="form-label">Profile Image</label>
                                            </div>
                                            <div class="col-md-5">
                                                <div class="mb-3 d-flex align-items-center">
                                                    <div class="flex-grow-1">
                                                        <input name="image" id="image" class="form-control" type="text" value="${user.user_image}">
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="openImage()" style="margin-left: 15px">View</button>
                                                </div>
                                            </div>

                                            <c:if test="${empty user.user_id}">
                                                <div class="col-md-4">
                                                    <div class="mb-3">
                                                        <label class="form-label">Password<span style="color: red">*</span></label>
                                                        <input name="password" id="password" type="password" class="form-control" required>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <div class="col-md-12">
                                                <br>
                                                <c:if test="${not empty user.user_id}">
                                                    <button type="submit" value="edit" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Update</button>
                                                </c:if>
                                                <c:if test="${empty user.user_id}">
                                                    <button type="submit" value="add" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Add</button>
                                                </c:if>
                                                <button type="button" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem;margin: 10px;" onclick="window.location.href = '${pageContext.request.contextPath}/admin/adminList'" class="btn btn-primary">Cancel</button>
                                                <c:if test="${not empty message}">
                                                    <div class="alert alert-danger" style="margin-top: 10px;">${message}</div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <script>
            function validateForm() {
                var fullname = document.getElementById("fullname").value;
                var email = document.getElementById("email").value;
                
                if (fullname.trim() === "") {
                    alert("Full name is required");
                    return false;
                }
                if (email.trim() === "") {
                    alert("Email is required");
                    return false;
                }
                return true;
            }
            
            function openImage() {
                var imageUrl = document.getElementById("image").value;
                if (imageUrl) {
                    window.open(imageUrl, '_blank');
                } else {
                    alert("No image URL provided");
                }
            }
        </script>
    </body>
</html>
