
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
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
                        <img alt="Brand" src="logos/gobrioja.png"  height="110" width="200" style="margin-top: -25%;">
                    </a>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="col-sm-offset-2">
                <h2>Servicio de cartelería del gobierno de La Rioja</h2>
                <h4>Aplicaciones:</h4>
                <ul>
                    <li><a href="Hospital/ListaHospital">Hospital San Pedro</a></li>
                    <li><a href="Justicia/ListaPalacioJusticia">Palacio de justicia</a></li>
                </ul>
            </div>
        </div>

    </body>
</html>
