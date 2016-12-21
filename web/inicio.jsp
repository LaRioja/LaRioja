
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Página de inicio</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>

    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" >
                        <img alt="Brand" src="logos/gobierno_de_la_rioja.png" height="50" width="150" style="margin-top: -10%">
                    </a>
                </div>
            </div>
        </nav>

        <div class="container">
            <h3>Cartelería del gobierno de La Rioja</h3>
            <p>Aplicaciones:</p>
            <a href="ListaHospital">Hospital</a>
        </div>

    </body>
</html>
