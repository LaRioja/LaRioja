
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restablecer cuenta</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/custom.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="recover_container" class="container">
            <div class="row">
                <h2 class="col-sm-offset-4 col-sm-5">Recupera tu cuenta</h2>
            </div>
            <div class="row">
                <p class="col-sm-offset-3 col-sm-6">Podemos ayudarte a restablecer tu contraseña. Primero escribe tu nombre de usuario y sigue las siguientes instrucciones.</p>
            </div>
            <c:if test="${errores}">
                <div class="row">
                    <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                        <ul>
                            <c:if test="${errorcaptcha !=null}">
                                <li><c:out value="${errorcaptcha}"/></li>
                                </c:if>
                                <c:if test="${erroruser !=null}">
                                <li><c:out value="${erroruser}"/></li>
                                </c:if>
                        </ul>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <form class="form-horizontal" name="recoverForm" id="recoverForm" action="RecuerdaContrasenia" method="post">
                    <div class="form-group">
                        <label for="usr" class="col-sm-5 control-label">Nombre de usuario</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="usr" id="usr" placeholder="Nombre de usuario" required="true" autofocus="true" value="${user}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group">
                            <label class="col-sm-5 control-label" for="txtCaptcha">Introduce el texto de la imagen</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="txtCaptcha" id="txtCaptcha" value="" placeholder="Texto de la imagen" required="true"/>
                            </div>
                        </div>
                        <div class="row">
                            <img class="col-sm-offset-5 col-sm-4" src="simpleCaptcha.png"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-5 col-sm-4">
                            <button type="submit" class="btn btn-primary">Aceptar</button>
                            <a href="Inicio" class="btn btn-primary" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
