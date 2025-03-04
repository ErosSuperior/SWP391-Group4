<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- Page Title -->
        <title>Service Detail</title>
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
                        <h5 class="mb-0">Service Detail</h5>
                        <nav aria-label="breadcrumb" class="mt-2">
                            <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/manager/serviceList">Service List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Service Detail</li>
                            </ul>
                        </nav>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" action="${pageContext.request.contextPath}/manager/serviceEdits" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
                                        <input type="hidden" name="submit" value="${serviceId != null ? 'edit' : 'add'}">

                                        <div class="row">
                                            <c:if test="${not empty serviceId}">
                                                <div class="col-md-4">
                                                    <div class="mb-3">
                                                        <label class="form-label" for="id">Service ID</label>
                                                        <input name="serviceId" id="id" type="text" value="${serviceId}" class="form-control" readonly="">
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Service Name<span style="color: red">*</span></label>
                                                    <input name="title" id="title" type="text" value="${title}" class="form-control">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label for="categoryTitle" class="form-label">Category<span style="color: red">*</span></label>
                                                    <input name="categoryTitle" id="categoryTitle" type="text" value="${categoryTitle}" class="form-control">
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">List Price ($)<span style="color: red">*</span></label>
                                                    <input name="servicePrice" id="servicePrice" type="number" step="0.01" class="form-control" value="${servicePrice}">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Discount ($)</label>
                                                    <input name="serviceDiscount" id="serviceDiscount" type="number" step="0.01" class="form-control" value="${serviceDiscount != null ? serviceDiscount : '0.00'}">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label class="form-label">Status<span style="color: red">*</span></label>
                                                    <select name="serviceStatus" id="serviceStatus" class="form-control">
                                                        <option value="1" ${serviceStatus == 1 ? 'selected' : ''}>Active</option>
                                                        <option value="0" ${serviceStatus == 0 ? 'selected' : ''}>Inactive</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-12" style="height: 100%">
                                                <div class="mb-3">
                                                    <label class="form-label" for="detail">Detail</label>
                                                    <textarea name="serviceDetail" id="detail" type="text" class="form-control">${serviceDetail}</textarea>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-12">
                                                <label class="form-label" for="image">Image</label>
                                            </div>
                                            <div class="col-md-5">
                                                <div class="mb-3 d-flex align-items-center">
                                                    <div class="flex-grow-1">
                                                        <c:if test="${not empty serviceImage}">
                                                            <input name="currentImage" id="currentImage" class="form-control" type="text" value="${serviceImage}" readonly>
                                                        </c:if>
                                                        <input name="serviceImage" id="image" class="form-control" type="file" accept="image/*">
                                                    </div>
                                                    <c:if test="${not empty serviceImage}">
                                                        <button type="button" class="btn btn-primary" onclick="openImage()" style="margin-left: 15px">View</button>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <div class="col-md-12" style="height: 100%">
                                                <br>
                                                <c:if test="${not empty serviceId}">
                                                    <!-- Show Update Button for Editing -->
                                                    <button type="submit" value="edit" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Update</button>
                                                </c:if>

                                                <c:if test="${empty serviceId}">
                                                    <!-- Show Add Button when serviceId is empty -->
                                                    <button type="submit" value="add" name="submit" class="btn btn-primary" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem; margin: 10px;">Add</button>
                                                </c:if>
                                                <button type="button" style="display: inline-block; width: auto; padding: 0.25rem 0.5rem; font-size: 0.875rem;margin: 10px;" onclick="window.location.href = '${pageContext.request.contextPath}/manager/serviceList'" class="btn btn-primary">Cancel</button>
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
                        let imageUrl = document.getElementById('currentImage').value;
                        if (imageUrl) {
                            window.open(imageUrl, '_blank');
                        } else {
                            alert("No image URL provided.");
                        }
                    }

                    function validateForm() {
                        let title = document.getElementById('title').value;
                        let categoryTitle = document.getElementById('categoryTitle').value;
                        let servicePrice = document.getElementById('servicePrice').value;
                        let status = document.getElementById('serviceStatus').value;

                        if (!title || title.trim() === "") {
                            alert("Please enter a service name.");
                            return false;
                        }
                        if (!categoryTitle || categoryTitle.trim() === "") {
                            alert("Please enter a category.");
                            return false;
                        }
                        if (!servicePrice || isNaN(servicePrice) || servicePrice <= 0) {
                            alert("Please enter a valid list price.");
                            return false;
                        }
                        if (status === "") {
                            alert("Please select a status.");
                            return false;
                        }
                        return true;
                    }
                </script>
            </main>
        </div>
    </body>
</html>