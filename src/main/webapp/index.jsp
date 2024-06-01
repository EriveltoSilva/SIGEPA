<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-AO">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Login, SIGEPA, Busca, emprego">
    <meta name="description" content="Página de Login da SIGEPA">
    <title>SIGEPA- Login </title>
    <link rel="shortcut icon" href="assets/images/logo.png" />
    <link href="assets/vendors/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendors/bootstrap5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/login.css">

</head>

<body>
    <div class="row vh-100 g-0">
        <div class="col-lg-6 position-relative d-none d-lg-block">
            <img src="assets/images/bg.jpg" alt="Imagem de Fundo do Login na SIGEPA" class="bg-holder">
        </div>

        <div class="col-lg-6">
            <div class="row align-items-center justify-content-center h-100 g-0 px-4 px-sm-0">
                <div class="col col-sm-6 col-lg-7 col-xl-6">
                    <a href="#" class="d-flex justify-content-center mb-4">
                        <img src="assets/images/logo.png" alt="Logotipo da SIGEPA" width="220">
                    </a>

                    <div class="text-center mb-5">
                        <h3 class="fw-bold">Faça o seu Login</h3>
                        <p class="text-secondary">Acesse a sua conta</p>
                    </div>


                    <!-- Divider -->
                    <div class="position-relative">
                        <hr class="text-secondary divider">
                        <div class="divider-content-center">(0-0)</div>
                    </div>
                    <!--/ End Divider -->

                    <form method="post" action="./login">
                        <div class="form-group">
                        <%@ include file="partials/error-messages.jsp" %>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">
                                <i class="bi bi-envelope"></i>
                            </span>
                            <input type="email"  name="email" id="email" placeholder="E-mail"
                                class="form-control form-control-lg fs-6" />
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">
                                <i class="bi bi-lock"></i>
                            </span>
                            <input type="password"  name="password" id="password" placeholder="Palavra-passe"
                                class="form-control form-control-lg fs-6" />
                        </div>

                        <div class="input-group mb-3 d-flex justify-content-end">
                            <div>
                                <small><a href="/accounts/password-reset">Esqueceu a senha?</a></small>
                            </div>
                        </div>

                        <!-- <a href="./pages/administrator/home.html" class="btn btn-primary btn-lg w-100 mb-3">Login</a> -->
                        <button type="submit" class="btn btn-primary btn-lg w-100 mb-3">Login</button>
                    </form>

                    <div class="text-center">
                        <small>
                            Não tem uma conta ainda?
                            <a href="/accounts/register" class="fw-bold">Registre-se</a>
                        </small>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="assets/vendors/bootstrap5/js/bootstrap.bundle.min.js"></script>
</body>

</html>