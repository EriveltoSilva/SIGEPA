<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% int totalProducts = (int) request.getAttribute("totalProducts"); %>
<% int totalCategories = (int) request.getAttribute("totalCategories"); %>
    <!DOCTYPE html>
    <html lang="pt-AO">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>SIGEPA - Dashboard</title>

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
                                    <div class="row g-4 justify-content-center">
                                        <div class="col-sm-6 col-xl-3 mt-2">
                                            <div
                                                class="bg-primary border border-light text-light rounded d-flex align-items-center justify-content-between p-4">
                                                <img class="rounded-circle"
                                                    src="<%=request.getContextPath()%>/assets/images/agricultural.jpg"
                                                    style="width: 80px; height: 80px;">
                                                <div class="ms-3">
                                                    <p class="mb-2">Total de Productos</p>
                                                    <h6 class="mb-0"> <%=totalProducts %> </h6>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-xl-3 mt-2">
                                            <div
                                                class="bg-light border border-primary text-primary rounded d-flex align-items-center justify-content-between p-4">
                                                <img class="rounded-circle"
                                                    src="<%=request.getContextPath()%>/assets/images/category.jpg"
                                                    style="width: 80px; height: 80px;">
                                                <div class="ms-3">
                                                    <p class="mb-2">Total de Categorias</p>
                                                    <h6 class="mb-0">  <%=totalCategories %>  </h6>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-sm-6 col-xl-3 mt-2">
                                            <div
                                                class="bg-primary border border-light text-light rounded d-flex align-items-center justify-content-between p-4">
                                                <img class="rounded-circle" src="<%=request.getContextPath()%>/assets/images/taxa.png"
                                                    style="width: 80px; height: 80px;">
                                                <div class="ms-3">
                                                    <p class="mb-2">Total de Usuários</p>
                                                    <h6 class="mb-0">{{taxa_agendadas|floatformat:2}}%</h6>
                                                </div>
                                            </div>
                                        </div>


                                    </div>

                                    <div class="row">
                                        <div class="col-xl-6 grid-margin stretch-card flex-column">
                                            <h5 class="mb-2 text-titlecase mb-4"> Estatisticas Interessantes</h5>
                                            <div class="row h-100">
                                                <div class="col-md-12 stretch-card">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <div
                                                                class="d-flex justify-content-between align-items-start flex-wrap">
                                                                <div>
                                                                    <p class="mb-3">Aumento Mensal de Produtos</p>
                                                                    <h3>67842</h3>
                                                                </div>
                                                                <div id="income-chart-legend"
                                                                    class="d-flex flex-wrap mt-1 mt-md-0">
                                                                </div>
                                                            </div>
                                                            <canvas id="income-chart"></canvas>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>



                                    <div class="row">
                                        <div class="col-md-4 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div
                                                        class="d-flex align-items-center justify-content-between justify-content-md-center justify-content-xl-between flex-wrap mb-4">
                                                        <div>
                                                            <p class="mb-2 text-md-center text-lg-left">Aumento de
                                                                Usuários
                                                            </p>
                                                            <h1 class="mb-0">8742</h1>
                                                        </div>
                                                        <i class="typcn typcn-briefcase icon-xl text-secondary"></i>
                                                    </div>
                                                    <canvas id="expense-chart" height="80"></canvas>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-4 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div
                                                        class="d-flex align-items-center justify-content-between justify-content-md-center justify-content-xl-between flex-wrap mb-4">
                                                        <div>
                                                            <p class="mb-2 text-md-center text-lg-left">Total Budget</p>
                                                            <h1 class="mb-0">47,840</h1>
                                                        </div>
                                                        <i class="typcn typcn-chart-pie icon-xl text-secondary"></i>
                                                    </div>
                                                    <canvas id="budget-chart" height="80"></canvas>
                                                </div>
                                            </div>
                                        </div>



                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card">
                                                <div class="table-responsive pt-3">
                                                    <table class="table table-striped project-orders-table">
                                                        <thead>
                                                            <tr>
                                                                <th class="ml-5">ID</th>
                                                                <th>Project name</th>
                                                                <th>Customer</th>
                                                                <th>Deadline</th>
                                                                <th>Payouts </th>
                                                                <th>Traffic</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>#D1</td>
                                                                <td>Consectetur adipisicing elit </td>
                                                                <td>Beulah Cummings</td>
                                                                <td>03 Jan 2019</td>
                                                                <td>$ 5235</td>
                                                                <td>1.3K</td>
                                                                <td>
                                                                    <div class="d-flex align-items-center">
                                                                        <button type="button"
                                                                            class="btn btn-success btn-sm btn-icon-text mr-3">
                                                                            Edit
                                                                            <i
                                                                                class="typcn typcn-edit btn-icon-append"></i>
                                                                        </button>
                                                                        <button type="button"
                                                                            class="btn btn-danger btn-sm btn-icon-text">
                                                                            Delete
                                                                            <i
                                                                                class="typcn typcn-delete-outline btn-icon-append"></i>
                                                                        </button>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
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