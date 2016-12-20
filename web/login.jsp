
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/custom.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="login_container" class="container">
            <div class="row">
                <form class="form-horizontal" name="loginForm" id="loginForm" action="j_security_check" method="post">
                    <div class="form-group">
                        <label for="usr" class="col-sm-4 control-label">Usuario</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="j_username" id="usr" placeholder="Nombre de usuario" required="true" autofocus="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pwd" class="col-sm-4 control-label">Contraseña</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" name="j_password" id="pwd" placeholder="Contraseña" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <a href="../RecuerdaContrasenia">He olvidado mi contraseña</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
