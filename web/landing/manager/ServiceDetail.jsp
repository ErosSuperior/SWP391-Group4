<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Service Detail</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo.png"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/remixicon.css" type="text/css"/>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tiny-slider.css"/>
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
                                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/manager/ServiceList">Service List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Service Detail</li>
                            </ul>
                        </nav>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <form class="mt-4" method="post" onsubmit="return validateForm()">
                                        <c:if test="${not empty service.serviceId}">
                                            <form action="${pageContext.request.contextPath}/manager/ServiceList/Update" method="post">
                                                <input type="hidden" name="serviceId" value="${service.serviceId}">
                                            </c:if>
                                            <c:if test="${empty service.serviceId}">
                                                <form action="${pageContext.request.contextPath}/manager/ServiceList/Add" method="post">
                                                </c:if>
                                                <div class="row">
                                                    <c:if test="${not empty service.serviceId}">
                                                        <div class="col-md-4">
                                                            <div class="mb-3">
                                                                <label class="form-label" for="serviceId">Service ID</label>
                                                                <input name="serviceId" id="serviceId" type="text" value="${service.serviceId}" class="form-control" readonly>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <div class="col-md-4">
                                                        <div class="mb-3">
                                                            <label class="form-label">Service Title<span style="color: red">*</span></label>
                                                            <input name="title" id="title" type="text" value="${service.serviceTitle}" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="mb-3">
                                                            <label class="form-label">Brief Introduction</label>
                                                            <input name="bi" id="bi" type="text" value="${service.serviceBi}" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="mb-3">
                                                            <label class="form-label">Category<span style="color: red">*</span></label>
                                                            <select name="categoryId" id="categoryId" class="form-control">
                                                                <option value="" ${empty service.categoryId ? 'selected' : ''}>None</option>
                                                                <c:forEach var="cat" items="${categories}">
                                                                    <option value="${cat.categoryId}" ${service.categoryId == cat.categoryId ? 'selected' : ''}>
                                                                        ${cat.categoryTitle}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="mb-3">
                                                            <label class="form-label">Price<span style="color: red">*</span></label>
                                                            <input name="price" id="price" type="number" step="0.01" value="${service.servicePrice}" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="mb-3">
                                                            <label class="form-label">Discount</label>
                                                            <input name="discount" id="discount" type="number" step="0.01" value="${service.serviceDiscount}" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="mb-3">
                                                            <label class="form-label" for="detail">Detail</label>
                                                            <textarea name="detail" id="detail" class="form-control">${service.serviceDetail}</textarea>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label class="form-label" for="image">Image</label>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <div class="mb-3 d-flex align-items-center">
                                                            <div class="flex-grow-1">
                                                                <input name="image" id="image" class="form-control" type="text" value="${service.serviceImage}">
                                                            </div>
                                                            <button type="button" class="btn btn-primary" onclick="openImage()" style="margin-left: 15px">View</button>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <br>
                                                        <c:if test="${not empty service.serviceId}">
                                                            <button type="submit" value="update" name="submit" class="btn btn-primary" style="margin: 10px;">Update</button>
                                                        </c:if>
                                                        <c:if test="${empty service.serviceId}">
                                                            <button type="submit" value="add" name="submit" class="btn btn-primary" style="margin: 10px;">Add</button>
                                                        </c:if>
                                                        <button type="button" onclick="window.location.href = '${pageContext.request.contextPath}/manager/ServiceList'" class="btn btn-primary" style="margin: 10px;">Cancel</button>
                                                        <c:if test="${not empty error}">
                                                            <div class="alert alert-danger" style="margin-top: 10px;">${error}</div>
                                                        </c:if>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="mb-3">
                                                            <label class="form-label">Status</label>
                                                            <select name="status" id="status" class="form-control">
                                                                <option value="1" ${service.serviceStatus == 1 ? 'selected' : ''}>Active</option>
                                                                <option value="0" ${service.serviceStatus == 0 ? 'selected' : ''}>Disabled</option>
                                                            </select>
                                                        </div>
                                                    </div>    
                                                </div>
                                            </form>
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                            <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
                                            <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
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
                                                                                function validateForm() {
                                                                                    let title = document.getElementById('title').value;
                                                                                    let categoryId = document.getElementById('categoryId').value;
                                                                                    let price = document.getElementById('price').value;
                                                                                    if (!title || !categoryId || !price) {
                                                                                        alert("Please fill in all required fields.");
                                                                                        return false;
                                                                                    }
                                                                                    return true;
                                                                                }
                                            </script>
                                            </main>
                                            </div>
                                            </body>
                                            </html>