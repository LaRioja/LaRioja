
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
        <title>Añadir registro hospital</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Hospital"/>
        <c:set var="selec" value="Extra"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <link href="${ctx}/CSS/bootstrap-datetimepicker.min.css" rel="stylesheet" media="all" type="text/css">

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/moment-with-locales.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script src="${ctx}/JS/bootstrap.js"></script>
    </head>
    <body>
        <%@include file="../navbar.html" %>
        <div class="container">
            <div class="row">
                <div class="col-sm-offset-1 col-sm-5">
                    <h4>Añadir contenido extra</h4>
                </div>
            </div>
            <br/><br/>
            <c:if test="${error}">
                <div class="row">
                    <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                        <ul>
                            <c:if test="${errorconsulta !=null}">
                                <li><c:out value="${errorconsulta}"/></li>
                                </c:if>
                                <c:if test="${errorhorai !=null}">
                                <li><c:out value="${errorhorai}"/></li>
                                </c:if>
                                <c:if test="${errorhoraf !=null}">
                                <li><c:out value="${errorhoraf}"/></li>
                                </c:if>
                                <c:if test="${erroradd !=null}">
                                <li><c:out value="${erroradd}"/></li>
                                </c:if>
                        </ul>
                    </div>  
                </div>
            </c:if>
            <div class="row">
                <div class="col-sm-offset-1">
                    <c:if test="${error_foto!=null}">
                        <div class="alert alert-danger">
                            ${error_foto}
                        </div>  
                    </c:if>
                    <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="AnadirContenidoExtra" method="post">
                        <div class="form-group">
                            <input type="file" name="archivo" id="plano">
                        </div>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>