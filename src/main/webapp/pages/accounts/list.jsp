<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.UserModel" %>
<%
    List<UserModel> users = (List<UserModel>) request.getAttribute("users");
%>

    <!DOCTYPE html>
    <html lang="pt-AO">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>SIGEPA - Listar Usuário </title>

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
                                                <h4 class="card-title">Lista de Usuários Registados</h4>
                                                <a href="<%=request.getContextPath()%>/accounts/register" class="btn btn-outline-primary">
                                                    <i class="bi bi-person-plus menu-icon"></i>
                                                    Novo Usuário
                                                </a>
                                            </div>
                                            
                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>Usuário</th>
                                                            <th>Nome</th>
                                                            <th>Tipo</th>
                                                            <th>E-mail</th>
                                                            <th>Criado a </th>
                                                            <th>Acções</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <% if(users != null) { 
                                                                for (UserModel user : users) {
                                                            %>

                                                        <tr>
                                                            <td class="py-1">
                                                                <img onmouseenter='showModal("modalLawyer{{lawyer.id}}")'
                                                                    src="<%=request.getContextPath()%>/assets/images/user.png" alt="Imagens padrão de perfil" />
                                                            </td>
                                                            <td><%=user.getFullName() %></td>
                                                            <td><%=user.getUserType() %></td>
                                                            <td><%=user.getEmail() %></td>
                                                            <td><%=user.getCreated_at() %></td>
                        
                                                            <td>
                                                                <div class="d-flex align-items-center">

                                                                    <a title="Editar" href="<%=request.getContextPath()%>/accounts/user-edit?id=<%=user.getId()%>"
                                                                        class="btn btn-primary btn-sm btn-icon-text mr-3">
                                                                        <i class="typcn typcn-edit btn-icon-append"></i>
                                                                    </a>
                        
                        
                                                                    <button title="Eliminar usuário <%= user.getFullName() %>" type="button"
                                                                        class="btn btn-danger btn-sm btn-icon-text mr-3" data-toggle="modal" data-target="#modalDelete<%= user.getId() %>">
                                                                        <i class="bi bi-trash-fill btn-icon-append"></i>
                                                                    </button>

                                                                    <!-- Modal -->
                                                                    <div class="modal fade" id="modalDelete<%= user.getId() %>" tabindex="-1" aria-labelledby="modalDelete<%= user.getId() %>Label" aria-hidden="true">
                                                                        <div class="modal-dialog">
                                                                          <div class="modal-content">
                                                                            <div class="modal-header">
                                                                              <h5 class="modal-title" id="exampleModalLabel">Detelar Usuário</h5>
                                                                              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                              </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                              Tem a certeza que deseja deletar a Usuário <%= user.getFullName() %>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                              <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                                              <form method="post" action="<%=request.getContextPath()%>/accounts/delete">
                                                                                  <input type="hidden" name="id" value="<%= user.getId() %>" />
                                                                                  <button type="submit" class="btn btn-primary">Deletar</button>
                                                                              </form>
                                                                            </div>
                                                                          </div>
                                                                        </div>
                                                                      </div>
                                                                      <!-- EndModal -->
                        
                                                                    
                                                                </div>
                                                            </td>
                                                            
                                                        </tr>
                        
                                                        <% }} else{ %> 
                                                        <tr>
                                                            <td colspan=4>
                                                                <p>Não há Usuários registados</p>
                                                            </td>
                                                        </tr>

                                                        <% } %>
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