<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- Page Title -->
        <title>Post Detail</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo.png"/>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
        <!-- Simplebar -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css" type="text/css"/>
        <!-- Select2 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css"/>
        <!-- Icons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/remixicon.css" type="text/css"/>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"/>
        <!-- Slider -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tiny-slider.css"/>
        <!-- Main CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.min.css" type="text/css"/>
    </head>
    <body>

        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../AdminNavbar.jsp" />

                <div class="layout-specing">
                    <div class="d-md-flex flex-column">
                        <h5 class="mb-0">Post Detail</h5>
                        <nav aria-label="breadcrumb" class="mt-2">
                            <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/manager/managerlistBlog">Post List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Post Detail</li>
                            </ul>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" action="${pageContext.request.contextPath}/manager/manageredits" method="post" onsubmit="return validateForm()">

                                        <div class="row">
                                            <c:if test="${not empty blog_id}">
                                                <div class="col-md-4">

                                                    <div class="mb-3">
                                                        <label class="form-label" for="id">Post ID</label>
                                                        <input name="blog_id" id="id" type="text" value="${blog_id}" class="form-control" readonly="">
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="col-md-4">

                                                <div class="mb-3">
                                                    <label class="form-label">Post Name<span style="color: red">*</span></label>
                                                    <input name="name" id="name" type="text" value="${title}" class="form-control">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label for="author" class="form-label">Author<span style="color: red">*</span></label>
                                                    <select name="author" id="author" class="form-control">
                                                        <option value="null" ${selectedAuthor == null ? 'selected' : ''}>None</option>
                                                        <c:forEach var="auth" items="${author}">
                                                            <option value="${auth.blogUserId}" ${selectedAuthor != 'null' && auth.blogUserId == selectedAuthor ? 'selected' : ''}>
                                                                ${auth.authorName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <br>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label for="category" class="form-label">Category<span style="color: red">*</span></label>
                                                    <select name="category" id="category" class="form-control">
                                                        <option value="" ${empty selectedCategoryId ? 'selected' : ''}>None</option>
                                                        <c:forEach var="cat" items="${category}">
                                                            <option value="${cat.blogCategory}" ${selectedCategoryId != null && selectedCategoryId == cat.blogCategory ? 'selected' : ''}>
                                                                ${cat.blogCategoryName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>

                                                    <br>
                                                </div>
                                            </div>


                                            <div class="col-md-12" style="height: 100%">
                                                <div class="mb-3">
                                                    <label class="form-label" for="detail">Detail</label>
                                                    <textarea name="detail" id="detail" type="text" class="form-control">${detail}</textarea>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-12">
                                                <label class="form-label" for="image">Image</label>
                                            </div>
                                            <div class="col-md-5">
                                                <div class="mb-3 d-flex align-items-center">
                                                    <div class="flex-grow-1">
                                                        <input name="image" id="image" class="form-control" type="text" value="${image}">
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="openImage()" style="margin-left: 15px">View</button>
                                                </div>
                                            </div>

                                            <div class="col-md-12" style="height: 100%">
                                                <br>
                                                <c:if test="${not empty blog_id}">
                                                    <!-- Show Save Button for Editing -->
                                                    <button type="submit" value="edit" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Update</button>
                                                </c:if>

                                                <c:if test="${empty blog_id}">
                                                    <!-- Show Add Button when department_id is empty -->
                                                    <button type="submit" value="add" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Add</button>
                                                </c:if>
                                                <button type="button" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem;margin: 10px;" onclick="window.location.href = '${pageContext.request.contextPath}/manager/managerlistBlog'" class="btn btn-primary">Cancel</button>
                                                <c:if test="${not empty message}">
                                                    <div class="alert alert-danger" style="margin-top: 10px;">${message}</div>
                                                </c:if>
                                            </div>    
                                        </div><!--end row-->
                                    </form>


                                </div>
                            </div><!--end col-->
                        </div>
                    </div>

                </div>


                <!-- Bootstrap -->
                <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
                <!-- Icons -->
                <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
                <!-- Main Js -->
                <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

                <script>
                                                    function openImage() {
                                                        let imageUrl = document.getElementById('image').value;
                                                        if (imageUrl) {
                                                            window.open(imageUrl, '_blank');
                                                        } else {
                                                            alert("No image URL provided.");
                                                        }
                                                    }
                </script>
            </main>
        </div>
    </body>
</html>
