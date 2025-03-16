<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Category Detail</title>
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
            <jsp:include page="AdminSidebar.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="AdminNavbar.jsp"/>

                <div class="layout-specing">
                    <div class="d-md-flex flex-column">
                        <h5 class="mb-0">Category Detail</h5>
                        <nav aria-label="breadcrumb" class="mt-2">
                            <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/categoryList">Category List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Category Detail</li>
                            </ul>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" action="${pageContext.request.contextPath}/admin/managecategory" method="post" onsubmit="return validateForm()">
                                        <div class="row">
                                            <c:if test="${not empty category.categoryId}">
                                                <div class="col-md-4">
                                                    <div class="mb-3">
                                                        <label class="form-label" for="categoryId">Category ID</label>
                                                        <input name="categoryId" id="categoryId" type="text" value="${category.categoryId}" class="form-control" readonly>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Category Name<span style="color: red">*</span></label>
                                                    <input name="categoryName" id="categoryName" type="text" value="${category.categoryName}" class="form-control" required>
                                                </div>
                                            </div>

                                            <div class="col-md-5">
                                                <div class="mb-3 d-flex align-items-center">
                                                    <div class="flex-grow-1">
                                                        <label class="form-label">Icon</label>
                                                        <input name="icon" id="icon" class="form-control" type="text" value="${category.icon}" placeholder="Enter icon URL">
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="openImage()" style="margin-left: 15px">View</button>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <br>
                                                <c:if test="${not empty category.categoryId}">
                                                    <button type="submit" value="edit" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Update</button>
                                                </c:if>
                                                <c:if test="${empty category.categoryId}">
                                                    <button type="submit" value="add" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Add</button>
                                                </c:if>
                                                <button type="button" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;" onclick="window.location.href = '${pageContext.request.contextPath}/admin/categoryList'" class="btn btn-primary">Cancel</button>
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
                var categoryName = document.getElementById("categoryName").value;

                if (categoryName.trim() === "") {
                    alert("Category name is required");
                    return false;
                }
                return true;
            }

            function openImage() {
                var imageUrl = document.getElementById("icon").value;
                if (imageUrl) {
                    window.open(imageUrl, '_blank');
                } else {
                    alert("No icon URL provided");
                }
            }
        </script>
    </body>
</html>