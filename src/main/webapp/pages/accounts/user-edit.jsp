<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.UserModel" %>
<%
    UserModel user = (UserModel) request.getAttribute("user");
%>
    <!DOCTYPE html>
    <html lang="pt-AO">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>SIGEPA - Editar Usuário</title>

        <%@ include file="/partials/head-imports.jsp" %>
    </head>


    <body>
        <div class="row d-none" id="proBanner">
            <i class="typcn typcn-delete-outline" id="bannerClose"></i>
        </div>

        <div class="container-scroller">
            <%@ include file="/partials/topbar.jsp" %>
                <div class="container-fluid page-body-wrapper">
                    <%@ include file="/partials/sidebar.jsp" %>

                        <div class="main-panel">
                            <main class="content-wrapper">
                            <%@ include file="/partials/error-messages.jsp" %>
                                <div class="row">
                                    <div
                                        class="col-10 col-sm-10 col-md-6 d-lg-6 d-xl-6 mx-auto col-md-6 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <h1 class="card-title h4">Edição de Usuário</h1>
                                                <p class="card-description">
                                                    <span class="badge-primary p-2 rounded">Alterar</span> usuário.
                                                </p>
                                                <form method="post" action="<%=request.getContextPath()%>/accounts/user-edit" class="forms-sample">
                                                    <input type="hidden" name="id" value="<%=user.getId()%>">
                                                    <div class="form-group">
                                                        <label for="exampleInputFullName">Nome Completo</label>
                                                        <input type="text" name="fullName" value="<%=user.getFullName()%>" class="form-control"
                                                            id="exampleInputFullName" placeholder="Nome Completo">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="exampleInputEmail1">Email</label>
                                                        <input type="email" name="email" value="<%=user.getEmail()%>" class="form-control"
                                                            id="exampleInputEmail1" placeholder="Email">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputUsername1">Username</label>
                                                        <input type="text" name="username" value="<%=user.getUsername()%>" class="form-control"
                                                            id="exampleInputUsername1" placeholder="Username">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputPassword1">Palavra-passe</label>
                                                        <input type="password" name="password" value="" class="form-control"
                                                            id="exampleInputPassword1" placeholder="Palavra-passe">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputConfirmPassword1">Confirmar
                                                            Palavra-passe</label>
                                                        <input type="password" name="confirmationPassword"
                                                            class="form-control" value="" id="exampleInputConfirmPassword1"
                                                            placeholder="Confirmar Palavra-passe">
                                                    </div>


                                                    <button type="submit" class="btn btn-primary mr-2">
                                                        <i class="bi bi-pencil-square"></i>
                                                        Registrar
                                                    </button>
                                                    <button type="reset" class="btn btn-secondary">
                                                        <i class="bi bi-x-circle"></i>
                                                        Cancelar
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="d-sm-none d-md-none col-12 col-sm-12 col-md-6 d-none d-lg-block d-lg-6">
                                        <img class="w-100 h-100" src="<%= request.getContextPath() %>/assets/images/user.png" alt="Criar Usuário">
                                    </div>

                                </div>

                            </main>

                            <%@ include file="/partials/footer.jsp" %>
                                </main>
                        </div>

                </div>

                <%@ include file="/partials/js-imports.jsp" %>
    </body>

    </html>