<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean isAdmin = sessionUser != null && "ADMIN".equals(sessionUser.getUserType());
%>

<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%= request.getContextPath() %>/dashboard">
                <i class="typcn typcn-device-desktop menu-icon"></i>
                <span class="menu-title">Dashboard </span>
            </a>
        </li>

        <%if(isAdmin){ %>
        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic" >
                <i class="bi bi-person-plus menu-icon"></i>
                <span class="menu-title">Usuários</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> 
                        <a class="nav-link" href="<%= request.getContextPath() %>/accounts/register">Cadastrar Usuário</a>
                    </li>
                    <li class="nav-item"> 
                        <a class="nav-link" href="<%= request.getContextPath() %>/accounts/list">Listar Usuários</a>
                    </li>
                </ul>
            </div>
        </li>
        

        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#form-category" aria-expanded="false" aria-controls="form-category">
                <i class="typcn typcn-film menu-icon"></i>
                <span class="menu-title">Categoria</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="form-category">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/category-register">Novo Categoria</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/category-list"> Listar Categorias</a>
                    </li>
                </ul>
            </div>
        </li>
        <% } %>
        
        
        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#form-elements" aria-expanded="false"
                aria-controls="form-elements">
                <i class="typcn typcn-film menu-icon"></i>
                <span class="menu-title">Produtos</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="form-elements">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/product-register">Novo Produto</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/product-list"> Listar Produtos</a>
                    </li>
                </ul>
            </div>
        </li>
    </ul>
</nav>