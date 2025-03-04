<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>User Management</title>
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
                <jsp:include page="../AdminNavbar.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">User Management</h5>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">User Management</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card rounded shadow">
                                    <div class="p-4 border-bottom">
                                        <div class="d-flex justify-content-between">
                                            <h5 class="mb-0">User List</h5>
                                            <a href="${pageContext.request.contextPath}/admin/adduser" class="btn btn-primary">
                                                <i class="uil uil-plus"></i> Add User
                                            </a>
                                        </div>
                                    </div>

                                    <div class="p-4">
                                        <form action="${pageContext.request.contextPath}/admin/adminList" method="GET" class="row">
                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label class="form-label">Role</label>
                                                    <select name="role_id" class="form-control">
                                                        <option value="-1">All Roles</option>
                                                        <option value="1" ${param.role_id=='1' ? 'selected' : ''}>Admin</option>
                                                        <option value="2" ${param.role_id=='2' ? 'selected' : ''}>Staff</option>
                                                        <option value="3" ${param.role_id=='3' ? 'selected' : ''}>Customer</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <select name="status" class="form-control">
                                                        <option value="-1">All Status</option>
                                                        <option value="1" ${param.status=='1' ? 'selected' : ''}>Active</option>
                                                        <option value="0" ${param.status=='0' ? 'selected' : ''}>Inactive</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
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
                                                    <label class="form-label">&nbsp;</label>
                                                    <button type="button" class="btn btn-primary form-control" 
                                                            onclick="window.location.href = '${pageContext.request.contextPath}/admin/adminList'">
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
                                                        <th class="border-bottom p-3">Email</th>
                                                        <th class="border-bottom p-3">Phone</th>
                                                        <th class="border-bottom p-3">Role</th>
                                                        <th class="border-bottom p-3">Status</th>
                                                        <th class="border-bottom p-3" style="min-width: 150px;">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.alluser}" var="user">
                                                        <tr>
                                                            <th class="p-3">${user.user_id}</th>
                                                            <td class="py-3">
                                                                <a href="#" class="text-dark">
                                                                    <div class="d-flex align-items-center">
                                                                        <img src="${user.user_image}" class="avatar avatar-md-sm rounded-circle shadow" alt="">
                                                                        <span class="ms-2">${user.user_fullname}</span>
                                                                    </div>
                                                                </a>
                                                            </td>
                                                            <td class="p-3">${user.user_email}</td>
                                                            <td class="p-3">${user.user_phone}</td>
                                                            <td class="p-3">
                                                                <c:choose>
                                                                    <c:when test="${user.role_id == 1}">Admin</c:when>
                                                                    <c:when test="${user.role_id == 2}">Staff</c:when>
                                                                    <c:when test="${user.role_id == 3}">Customer</c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td class="p-3">
                                                                <c:choose>
                                                                    <c:when test="${user.user_status}">
                                                                        <span class="badge bg-soft-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-soft-danger">Inactive</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="p-3">
                                                                <a href="${pageContext.request.contextPath}/admin/edituser?email=${user.user_email}"
                                                                   class="btn btn-icon btn-pills btn-soft-primary" title="Edit">
                                                                    <i class="uil uil-pen"></i>
                                                                </a>
                                                                <a href="${pageContext.request.contextPath}/admin/updatestatus?userId=${user.user_id}&status=${!user.user_status}"
                                                                   class="btn btn-icon btn-pills btn-soft-${user.user_status ? 'danger' : 'success'}"
                                                                   title="${user.user_status ? 'Deactivate' : 'Activate'}">
                                                                    <i class="uil ${user.user_status ? 'uil-ban' : 'uil-check'}"></i>
                                                                </a>

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
                                                                <a class="page-link" href="${pageContext.request.contextPath}/admin/adminList?pageNo=${i}&nameOrId=${param.nameOrId}&status=${param.status}&role_id=${param.role_id}">
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

        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <script>
                                                                let currentUserId, newStatus;

                                                                function confirmStatusChange(userId, status) {
                                                                    currentUserId = userId;
                                                                    newStatus = status;
                                                                    const action = status ? 'activate' : 'deactivate';
                                                                    document.getElementById('statusConfirmMessage').textContent =
                                                                            'Are you sure you want to ' + action + ' this user?';
                                                                    new bootstrap.Modal(document.getElementById('statusModal')).show();
                                                                }

        </script>
    </body>
</html>
