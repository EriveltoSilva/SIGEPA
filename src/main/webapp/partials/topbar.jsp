<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.UserModel" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    UserModel sessionUser = (UserModel) session.getAttribute("user");
    String userName = sessionUser != null ? sessionUser.getFullName() : "Convidado";
    String createdAt = "";
    if (sessionUser != null && sessionUser.getCreated_at() != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        createdAt = sdf.format(sessionUser.getCreated_at());
    } else{
        createdAt = "Data indisponível";
    }
%>


<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
    <div class="navbar-brand-wrapper d-flex justify-content-center">
        <div class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
            <a class="navbar-brand brand-logo" href="<%= request.getContextPath() %>/dashboard">
            <img src="<%= request.getContextPath() %>/assets/images/logo.png" alt="logo" /></a>
            <a class="navbar-brand brand-logo-mini" href="<%= request.getContextPath() %>/dashboard">
            <img src="<%= request.getContextPath() %>/assets/images/logo.png" alt="logo" /></a>
            <button class="navbar-toggler navbar-toggler align-self-center" type="button"
                data-toggle="minimize">
                <span class="typcn typcn-th-menu"></span>
            </button>
        </div>
    </div>

    <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <ul class="navbar-nav mr-lg-2">
            <li class="nav-item nav-profile dropdown">
                <a class="nav-link" href="#" data-toggle="dropdown" id="profileDropdown">
                    <div class="d-flex ">
                        <img src="<%= request.getContextPath() %>/assets/images/user.png" alt="profile" />
                        <div>
                            <div class="d-flex flex-column">
                                <span class="nav-profile-name"><%=userName %></span>
                                <span class="nav-profile-name">Conta aberta a: <%=createdAt %></span>
                            </div>
                        </div>
                    </div>
                </a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown"
                    aria-labelledby="profileDropdown">
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/accounts/user-edit?id=<%=sessionUser.getId()%>">
                        <i class="typcn typcn-cog-outline text-primary"></i>
                        Perfil
                    </a>
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/accounts/logout">
                        <i class="typcn typcn-eject text-primary"></i>
                        Logout
                    </a>
                </div>
            </li>
        </ul>
        
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
            data-toggle="offcanvas">
            <span class="typcn typcn-th-menu"></span>
        </button>
    </div>
</nav>