<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                               <h1>Lista de Usuário</h1>

                            </main>

                            <%@ include file="/partials/footer.jsp" %>
                        </main>
                </div>

        </div>

        <%@ include file="/partials/js-imports.jsp" %>
    </body>

    </html>