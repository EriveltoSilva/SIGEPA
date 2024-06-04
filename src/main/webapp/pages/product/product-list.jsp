<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.ProductModel" %>
<%
    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
%>
    <!DOCTYPE html>
    <html lang="pt-AO">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>SIGEPA - Lista de Productos</title>

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
                                                        <h1 class="card-title h4">Lista de Produtos</h1>
                                                        <p class="card-description">
                                                            Seus produtos registados
                                                        </p>
                                                    </div>
                                                    <div>
                                                        <a href="" class="btn btn-outline-primary">
                                                            <i class="bi bi-plus-circle"></i>
                                                            Novo Produto
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="table-responsive">
                                                    <table class="table table-striped">
                                                        <thead>
                                                            <tr>
                                                                <th>Imagem</th>
                                                                <th>Nome</th>
                                                                <th>Categoria</th>
                                                                <th>Quantid.(unidades)</th>
                                                                <th>Preço(Kz)</th>
                                                                <th>Criado a</th>
                                                                <th>Ações</th>
                                                            </tr>
                                                        </thead>

                                                        <tbody>
                                                            <% if (products != null) {
                                                                for (ProductModel product : products) {
                                                            %>
                                                            <tr>
                                                                <td class="py-1"><img
                                                                        src="assets/images/banner_12.jpg"
                                                                        alt="image" /></td>
                                                                <td><%=product.getName() %></td>
                                                                <td><%=product.getCategory().getName() %></td>
                                                                <td><%=product.getQuantity() %></td>
                                                                <td><%=product.getPrice()%></td>
                                                                <td><%=product.getCreated_at() %></td>
                                                                <td>
                                                                    <div class="d-flex">
                                                                        <form method="get" action="./product-edit">
                                                                            <input type="hidden" name="id" value="<%= product.getId() %>" />
                                                                            <button title="Editar"  class="btn p-2 bg-primary text-light">
                                                                                <i class="bi bi-pencil-square"></i>
                                                                            </button>
                                                                        </form>

                                                                        <button type="button" title="Deletar Produto" class="mx-2 btn p-2 bg-danger text-light" data-toggle="modal" data-target="#modalDelete<%= product.getId() %>">
                                                                          <i class="bi bi-trash-fill"></i>
                                                                        </button>
                                                                    </div>

                                                                    <!-- Modal -->
                                                                    <div class="modal fade" id="modalDelete<%= product.getId() %>" tabindex="-1" aria-labelledby="modalDelete<%= product.getId() %>Label" aria-hidden="true">
                                                                      <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                          <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Deletar</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                              <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                          </div>
                                                                          <div class="modal-body">
                                                                            Tem a certeza que deseja deletar a categoria <%= product.getName() %>
                                                                          </div>
                                                                          <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                                            <form method="post" action="./product-delete">
                                                                                <input type="hidden" name="id" value="<%= product.getId() %>" />
                                                                                <button type="submit" class="btn btn-primary">Deletar</button>
                                                                            </form>
                                                                          </div>
                                                                        </div>
                                                                      </div>
                                                                    </div>
                                                                    <!-- EndModal -->
                                                                </td>
                                                            </tr>

                                                            <% }} %>

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