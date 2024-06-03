<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="pt-AO">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>SIGEPA - Nova Categoria de Produto</title>

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
                                    <div class="d-sm-none d-md-none col-12 col-sm-12 col-md-6 d-none d-lg-block d-lg-6">
                                        <img class="w-100 h-100" src="<%= request.getContextPath() %>/assets/images/banner_10.jpg"
                                            alt="Um novo Acordo">
                                    </div>

                                    <div
                                        class="col-10 col-sm-10 col-md-6 d-lg-6 d-xl-6 mx-auto col-md-6 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <h4 class="card-title">Categoria de Produto - <span
                                                        class="badge badge-warning h3">Nova</span></h4>

                                                <form class="forms-sample" method="post" action="./category-register">
                                                    <div class="form-group">
                                                        <label for="exampleInputUsername1">Nome</label>
                                                        <input type="text" name="name" class="form-control"
                                                            id="exampleInputNome1" placeholder="Nome da Categoria">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputDescription1">Descrição</label>
                                                        <Textarea name="description" class="form-control" cols="6"
                                                            placeholder="Descrição da Categoria"></Textarea>
                                                    </div>

                                                    <button type="submit" class="btn btn-primary mr-2">
                                                        <i class="bi bi-pencil-fill"></i>
                                                        Registrar
                                                    </button>

                                                </form>
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