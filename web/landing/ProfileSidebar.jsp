<%@ page import="model.User" %>

<div class="rounded shadow overflow-hidden sticky-bar">
    <div class="card border-0">
        <img src="<%= request.getContextPath() %>/assets/images/doctors/profile-bg.jpg" class="img-fluid" alt="">
    </div>

    <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
        <%
            User account = (User) session.getAttribute("account");
            if (account != null) {
        %>
            <img src="<%= account.getUser_image() != null ? account.getUser_image() : "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-image-icon-default-avatar-profile-icon-social-media-user-vector-image-209162840.jpg" %>" 
                 class="rounded-circle shadow-md avatar avatar-md-md" alt="User Image">
            <h5 class="mt-3 mb-1"><%= account.getUser_fullname() %></h5>
        <%
            } else {
        %>
            <img src="https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-image-icon-default-avatar-profile-icon-social-media-user-vector-image-209162840.jpg" 
                 class="rounded-circle shadow-md avatar avatar-md-md" alt="Default User">
            <h5 class="mt-3 mb-1">Guest</h5>
            <p class="text-muted mb-0">Please log in</p>
        <%
            }
        %>
    </div>

    <ul class="list-unstyled sidebar-nav mb-0">
        <li class="navbar-item"><a href="doctor-dashboard.html" class="navbar-link"><i class="ri-airplay-line align-middle navbar-icon"></i> Dashboard</a></li>
        <li class="navbar-item"><a href="${pageContext.request.contextPath}/customer/myreservationlist" class="navbar-link"><i class="ri-pages-line align-middle navbar-icon"></i> Your Reservations</a></li>
        
        
    </ul>
</div>

<!--dat trong format nhu nay-->
<!--<section class="bg-dashboard">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5 col-12">
                        include tai day
                    </div>end col-->
<!--                </div>
            </div>
    </div>-->
                
