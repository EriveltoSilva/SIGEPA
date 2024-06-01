<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.CategoryModel" %>

    <!DOCTYPE html>
    <html lang="pt-AO">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>SIGEPA - Lista de Categorias</title>

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
                                    <div class="col-lg-12 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between">
                                                    <div>
                                                        <h1 class="card-title h4">Lista de Categorias</h1>
                                                        <p class="card-description">
                                                            Suas categorias de produtos
                                                        </p>
                                                    </div>
                                                    <div>
                                                        <a href="./category-new" class="btn btn-outline-primary">
                                                            <i class="bi bi-plus-circle"></i>
                                                            Nova Categoria
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="table-responsive">
                                                    <table class="table table-striped">
                                                        <thead>
                                                            <tr>
                                                                <th>Número</th>
                                                                <th>Imagem</th>
                                                                <th>Nome</th>
                                                                <th>Criado a</th>
                                                                <th>Ações</th>
                                                            </tr>
                                                        </thead>

                                                        <tbody>

                                                        <%
                                                            List<CategoryModel> categories = (List<CategoryModel>) request.getAttribute("categories");
                                                            if (categories != null) {
                                                                for (CategoryModel category : categories) {
                                                        %>
                                                            <tr>
                                                                <td><%= category.getId() %></td>
                                                                <td class="py-1">
                                                                    <img src="assets/images/banner_12.jpg" alt="image" />
                                                                </td>
                                                                <td><%= category.getName() %></td>
                                                                <td><%= category.getCreated_at() %></td>
                                                                <td>
                                                                    <a title="Editar" href=""
                                                                        class="btn p-2 bg-primary text-light">
                                                                        <i class="bi bi-pencil-square"></i>
                                                                    </a>

                                                                    <a title="Eliminar" href=""
                                                                        class="btn p-2 bg-danger text-light">
                                                                        <i class="bi bi-trash-fill"></i>
                                                                    </a>
                                                                </td>
                                                            </tr>


                                                         <%
                                                                }
                                                            } else {
                                                        %>
                                                            <tr>
                                                                <td colspan="4">Sem categorias disponiveis.</td>
                                                            </tr>
                                                        <%
                                                            }
                                                        %>
                                                        </tbody>

                                                    </table>
                                                </div>
                                            </div>
                                        </div>
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