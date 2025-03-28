<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Category Management</title>
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

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Category Management</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Category Management</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card rounded shadow">
                                    <div class="p-4 border-bottom">
                                        <div class="d-flex justify-content-between">
                                            <h5 class="mb-0">Category List</h5>
                                            <a href="${pageContext.request.contextPath}/admin/addcategory" class="btn btn-primary">
                                                <i class="uil uil-plus"></i> Add Category
                                            </a>
                                        </div>
                                    </div>

                                    <div class="p-4">
                                        <form action="${pageContext.request.contextPath}/admin/categoryList" method="GET" class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Search</label>
                                                    <div class="input-group">
                                                        <input type="text" name="nameOrId" class="form-control" 
                                                               placeholder="Search by name or ID" value="${param.nameOrId}">
                                                        <button class="btn btn-primary" type="submit">Search</button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                    <label class="form-label"> </label>
                                                    <button type="button" class="btn btn-primary form-control" 
                                                            onclick="window.location.href = '${pageContext.request.contextPath}/admin/categoryList'">
                                                        <i class="uil uil-redo"></i> Reset
                                                    </button>
                                                </div>
                                            </div>
                                        </form>

                                        <div class="table-responsive shadow rounded">
                                            <table class="table table-center bg-white mb-0">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="min-width: 50px;">ID</th>
                                                        <th class="border-bottom p-3" style="min-width: 180px;">Name</th>
                                                        <th class="border-bottom p-3">Icon</th>
                                                        <th class="border-bottom p-3">Status</th>
                                                        <th class="border-bottom p-3" style="min-width: 150px;">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${allCategories}" var="category">
                                                        <tr>
                                                            <th class="p-3">${category.category_id}</th>
                                                            <td class="p-3">${category.category_name}</td>
                                                            <td class="p-3">
                                                                <img src="${category.category_icon}" class="avatar avatar-md-sm rounded-circle shadow" alt="Icon" style="max-width: 50px;">
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${category.category_status == 1}">
                                                                        <div class="badge bg-soft-success rounded px-3 py-1">Active</div>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="badge bg-soft-danger rounded px-3 py-1">Inactive</div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="p-3">
                                                                <a href="${pageContext.request.contextPath}/admin/editcategory?categoryId=${category.category_id}"
                                                                   class="btn btn-icon btn-pills btn-soft-primary" title="Edit">
                                                                    <i class="uil uil-pen"></i>
                                                                </a>
                                                                <c:choose>
                                                                    <c:when test="${category.category_status == 1}">
                                                                        <button class="btn btn-icon btn-pills btn-soft-danger"
                                                                                data-category-id="${category.category_id}"
                                                                                onclick="showConfirmationModal(${category.category_id}, 0)"
                                                                                type="button" title="Deactivate">
                                                                            <i class="uil uil-ban"></i>
                                                                        </button>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <button class="btn btn-icon btn-pills btn-soft-success"
                                                                                data-category-id="${category.category_id}"
                                                                                onclick="showConfirmationModal(${category.category_id}, 1)"
                                                                                type="button" title="Activate">
                                                                            <i class="uil uil-check-circle"></i>
                                                                        </button>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="row text-center">
                                            <div class="col-12 mt-4">
                                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                                    <span class="text-muted me-3">
                                                        Showing ${pageNo * pageSize + 1} - 
                                                        ${totalElements < ((pageNo + 1) * pageSize) ? totalElements : ((pageNo + 1) * pageSize)}
                                                        out of ${totalElements}
                                                    </span>
                                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                                        <c:forEach begin="0" end="${(totalElements + pageSize - 1) / pageSize - 1}" var="i">
                                                            <li class="page-item ${pageNo == i ? 'active' : ''}">
                                                                <a class="page-link" href="${pageContext.request.contextPath}/admin/categoryList?pageNo=${i}&nameOrId=${param.nameOrId}">
                                                                    ${i + 1}
                                                                </a>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <!-- Modal Xác nhận -->

        <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmationModalLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p id="confirmationModalMessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger" id="confirmActionButton"></button>
                    </div>
                </div>
            </div>
        </div>



        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                                                                    let currentCategoryId;
                                                                                    let currentStatus;

                                                                                    function showConfirmationModal(categoryId, status) {
                                                                                        currentCategoryId = categoryId;
                                                                                        currentStatus = status;

                                                                                        let modalTitle = document.getElementById("confirmationModalLabel");
                                                                                        let modalMessage = document.getElementById("confirmationModalMessage");
                                                                                        let confirmButton = document.getElementById("confirmActionButton");

                                                                                        if (status === 0) {
                                                                                            modalTitle.innerText = "Confirm Deactivation";
                                                                                            modalMessage.innerText = "Are you sure you want to deactivate this category?";
                                                                                            confirmButton.innerText = "Deactive";
                                                                                            confirmButton.className = "btn btn-danger";
                                                                                        } else {
                                                                                            modalTitle.innerText = "Confirm Activation";
                                                                                            modalMessage.innerText = "Are you sure you want to activate this category?";
                                                                                            confirmButton.innerText = "Activate";
                                                                                            confirmButton.className = "btn btn-success";
                                                                                        }

                                                                                        $('#confirmationModal').modal('show');
                                                                                    }

                                                                                    $('#confirmActionButton').click(function () {
                                                                                        changeStatusCategory(currentCategoryId, currentStatus);
                                                                                        $('#confirmationModal').modal('hide');
                                                                                    });

                                                                                    function changeStatusCategory(categoryId, currentStatus) {
                                                                                        $.ajax({
                                                                                            url: '${pageContext.request.contextPath}/admin/deletecategory',
                                                                                            type: 'GET',
                                                                                            data: {
                                                                                                categoryId: categoryId,
                                                                                                status: currentStatus
                                                                                            },
                                                                                            success: function () {
                                                                                                location.reload();
                                                                                            },
                                                                                            error: function (xhr, status, error) {
                                                                                                console.error('AJAX Error:', xhr.status, status, error);
                                                                                            }
                                                                                        });
                                                                                    }
        </script>
    </body>
</html>