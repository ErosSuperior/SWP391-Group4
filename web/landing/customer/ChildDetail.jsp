<%-- 
    Document   : ChildDetail
    Created on : Mar 29, 2025
    Author     : [Your Name]
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Children Detail - Children Care</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Children, Management, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="<%= request.getContextPath() %>/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Icons -->
        <link href="<%= request.getContextPath() %>/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath() %>/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
        <!-- Css -->
        <link href="<%= request.getContextPath() %>/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    </head>
    <body>
        <!-- Navbar Start -->
        <jsp:include page="../CustomerNavbar.jsp"/>
        <!-- Navbar End -->

        <!-- Start Hero -->
        <section class="bg-dashboard">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5 col-12">
                        <jsp:include page="../ProfileSidebar.jsp"/>
                    </div>

                    <div class="col-xl-9 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <!-- Start Form -->
                        <!-- Precompute the form action URL using c:set to avoid EL parsing issues -->
                        <c:set var="formAction" value="${child == null ? pageContext.request.contextPath.concat('/children/add') : pageContext.request.contextPath.concat('/children/update')}" />
                        <form action="${formAction}" method="post">
                            <!-- CHANGE: Add hidden input for child ID when updating -->
                            <c:if test="${child != null}">
                                <input type="hidden" name="children_id" value="${child.children_id}">
                            </c:if>
                            <div class="row">
                                <div class="col-12">
                                    <h5 class="mb-3">Child Information</h5>
                                </div>
                                <nav aria-label="breadcrumb" class="col-12">
                                    <ul class="breadcrumb breadcrumb-muted bg-transparent rounded mb-0 p-0">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/children/list">My Children</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Child Detail</li>
                                    </ul>
                                </nav>

                                <div class="col-12 mt-4">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Name</label>
                                                        <input type="text" class="form-control" 
                                                               name="children_name" value="${child.children_name}" 
                                                               required="required">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">Gender</label>
                                                        <select class="form-control" name="children_gender" required="required">
                                                            <option value="true" ${child.children_gender ? 'selected' : ''}>Male</option>
                                                            <option value="false" ${!child.children_gender ? 'selected' : ''}>Female</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="mb-3">
                                                        <label class="form-label">Age</label>
                                                        <input type="number" class="form-control" 
                                                               name="children_age" value="${child.children_age}" 
                                                               min="0" max="18" required="required">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row mt-4">
                                                <div class="col-12">
                                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                                    <a href="${pageContext.request.contextPath}/children/list" 
                                                       class="btn btn-secondary ms-2">Back to List</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <!-- Back to top -->
        <jsp:include page="../Footer.jsp"/>

        <!-- javascript -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Select2 -->
        <script src="<%= request.getContextPath() %>/assets/js/select2.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/select2.init.js"></script>
        <!-- Icons -->
        <script src="<%= request.getContextPath() %>/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="<%= request.getContextPath() %>/assets/js/app.js"></script>
    </body>
</html>