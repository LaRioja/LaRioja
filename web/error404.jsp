
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("mErr", "El recurso al que intenta acceder no existe");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">
        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/custom.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="error_container" class="container">
            <div class="row">
                <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                    <p>Error ${requestScope['javax.servlet.error.status_code']}: ${mErr}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-offset-3 col-sm-2" role="alert">
                    <a class="btn bg-primary" href="${ctx}/Inicio">Salir de aquí</a>
                </div>
            </div>
        </div>
    </body>
</html>
