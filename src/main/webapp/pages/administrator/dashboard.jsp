<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>

<% int totalProducts=(int) request.getAttribute("totalProducts"); %>
<% int totalCategories=(int) request.getAttribute("totalCategories"); %>
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
                                <div class="row g-4 justify-content-left">
                                    <div class="col-sm-6 col-xl-3 mt-2">
                                        <div
                                            class="bg-primary border border-light text-light rounded d-flex align-items-center justify-content-between p-4">
                                            <img class="rounded-circle"
                                                src="<%=request.getContextPath()%>/assets/images/agricultural.jpg"
                                                style="width: 80px; height: 80px;">
                                            <div class="ms-3">
                                                <p class="mb-2">Total de Productos</p>
                                                <h6 class="mb-0">
                                                    <%=totalProducts %>
                                                </h6>
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
                                                <h6 class="mb-0">
                                                    <%=totalCategories %>
                                                </h6>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-6 col-xl-3 mt-2">
                                        <div
                                            class="bg-primary border border-light text-light rounded d-flex align-items-center justify-content-between p-4">
                                            <img class="rounded-circle"
                                                src="<%=request.getContextPath()%>/assets/images/user.png"
                                                style="width: 80px; height: 80px;">
                                            <div class="ms-3">
                                                <p class="mb-2">Total de Usuários</p>
                                                <h6 class="mb-0">---</h6>
                                            </div>
                                        </div>
                                    </div>


                                </div>

                                
                                
                                
                                
                                <h2 class="text-titlecase mt-4 h5"> Quand. de Productos/Dia</h2>
                                <div class="row">
                                    <div class="col-lg-6 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <h3 class="card-title h4">Gráfico de Barras</h3>
                                                <canvas id="productBarChart"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <h4 class="card-title">Gráfico de Linha</h4>
                                                <canvas id="productLineChart"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <h2 class="text-titlecase mt-4 h5"> Quand. de Categorias Criadas/Dia</h2>
                                <div class="row">
                                    <div class="col-lg-6 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <h3 class="card-title h4">Gráfico de Barras</h3>
                                                <canvas id="categoryBarChart"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 grid-margin stretch-card">
                                        <div class="card">
                                            <div class="card-body">
                                                <h4 class="card-title">Gráfico de Linha</h4>
                                                <canvas id="categoryLineChart"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                        </main>

                        <%@ include file="/partials/footer.jsp" %>
                    </div>

            </div>



            <%@ include file="/partials/js-imports.jsp" %>

    <script>
        window.addEventListener("load", () => {
            let labelsProductBar = [];
            let dataProductBar = [];
            
            let labelsCategoryBar = [];
            let dataCategoryBar = [];

            <%
                Map<Date, Integer> productsBar = (Map<Date, Integer>) request.getAttribute("productsBar");
                if (productsBar != null) {
                    for (Map.Entry<Date, Integer> entry : productsBar.entrySet()) {
                        String date = entry.getKey().toString();
                        int count = entry.getValue();
            %>
                        labelsProductBar.push("<%= date %>");
                        dataProductBar.push(<%= count %>);
            <%
                    }
                }
            %>

            <%
                Map<Date, Integer> categoriesBar = (Map<Date, Integer>) request.getAttribute("categoriesBar");
                if (categoriesBar != null) {
                    for (Map.Entry<Date, Integer> entry : categoriesBar.entrySet()) {
                        String date = entry.getKey().toString();
                        int count = entry.getValue();
            %>
                        labelsCategoryBar.push("<%= date %>");
                        dataCategoryBar.push(<%= count %>);
            <%
                    }
                }
            %>

            var options = {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
                legend: {
                    display: false
                },
                elements: {
                    point: {
                        radius: 0
                    }
                }
            };


            var dataTotalProducts = {
                labels: labelsProductBar,
                datasets: [{
                    label: '# of Votes',
                    data: dataProductBar,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1,
                    fill: false
                }]
            };
            
            var dataTotalCategory = {
                labels: labelsCategoryBar,
                datasets: [{
                    label: '# of Votes',
                    data: dataCategoryBar,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1,
                    fill: false
                }]
            };

            // Get context with jQuery - using jQuery's .get() method.
            if ($("#productBarChart").length) {
                var barChartCanvas = $("#productBarChart").get(0).getContext("2d");
                // This will get the first returned node in the jQuery collection.
                var barChart = new Chart(barChartCanvas, {
                    type: 'bar',
                    data: dataTotalProducts,
                    options: options
                });
            }

            if ($("#productLineChart").length) {
                var lineChartCanvas = $("#productLineChart").get(0).getContext("2d");
                var lineChart = new Chart(lineChartCanvas, {
                    type: 'line',
                    data: dataTotalProducts,
                    options: options
                });
            }


            //----------------------------------------------------------------------------------------------------------------------
            if ($("#categoryBarChart").length) {
                var barChartCanvas = $("#categoryBarChart").get(0).getContext("2d");
                var barChart = new Chart(barChartCanvas, {
                    type: 'bar',
                    data: dataTotalCategory,
                    options: options
                });
            }

            if ($("#categoryLineChart").length) {
                var lineChartCanvas = $("#categoryLineChart").get(0).getContext("2d");
                var lineChart = new Chart(lineChartCanvas, {
                    type: 'line',
                    data: dataTotalCategory,
                    options: options
                });
            }
            
        });


    </script>
</body>

</html>