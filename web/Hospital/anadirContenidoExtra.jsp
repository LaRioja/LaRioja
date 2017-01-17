<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    HttpSession misession = (HttpSession) request.getSession();
    String usuario = (String) misession.getAttribute("username");
    request.setAttribute("isAdmin", request.isUserInRole("administrador"));
    if (usuario == null) {
        request.setAttribute("username", request.getUserPrincipal().getName().toUpperCase());
    } else {
        request.setAttribute("username", usuario.toUpperCase());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Añadir contenido extra hospital</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Hospital"/>
        <c:set var="selec" value="Extra"/>
        <c:set var="activo" value="si"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script src="${ctx}/JS/bootstrap.js"></script>
        <script src="${ctx}/JS/bootstrap-filestyle.min.js"></script>
    </head>
    <body>
        <%@include file="../navbar.html" %>
        <div class="container">
            <div class="row">
                <div class="col-sm-offset-1 col-sm-5">
                    <h4>Añadir contenido extra</h4>
                </div>
            </div>
            <br><br><br>
            <div class="row">
                <div class="col-sm-offset-1">
                    <c:if test="${error_foto!=null}">
                        <div class="alert alert-danger">
                            ${error_foto}
                        </div>  
                    </c:if>
                    <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="AnadirContenidoExtra" method="post">
                        <div class="form-group col-sm-6">
                            <input type="file" name="archivo" required="true" class="filestyle" data-buttonName="btn-primary" data-buttonText="Elegir fichero">
                        </div>
                        <div class="form-group col-sm-8">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

