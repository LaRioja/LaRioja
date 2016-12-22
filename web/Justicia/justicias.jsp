<%-- 
    Document   : justicias
    Created on : 16-dic-2016, 10:59:22
    Author     : pmayor
--%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palacio de Justicia</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Justicia"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" media="all" type="text/css">

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
        <script type="text/javascript" src="${ctx}/JS/jquery.dataTables.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tables').DataTable({
                    "language": {
                        "sProcessing": "Procesando...",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "No se encontraron resultados",
                        "sEmptyTable": "Ningún dato disponible en esta tabla",
                        "sInfo": "Mostrando del _START_ al _END_ de un total de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando del 0 al 0 de un total de 0 registros",
                        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sSearch": "Buscar:",
                        "sUrl": "",
                        "sInfoThousands": ",",
                        "sLoadingRecords": "Cargando...",
                        "oPaginate": {
                            "sFirst": "Primero",
                            "sLast": "Último",
                            "sNext": "Siguiente",
                            "sPrevious": "Anterior"
                        },
                        "oAria": {
                            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                        }
                    }
                });
            });

        </script>
    </head>
    <body>
        <%@include file="../navbar.html" %>
        <div class="container">
            <div class="row">
                <div class="col-sm-offset-1 col-sm-5">
                    <h4>Palacio de Justicia</h4>
                </div>
            </div>
            <c:if test="${error !=null}">
                <div class="row">
                    <div class="alert alert-danger col-sm-offset-2 col-sm-8" role="alert">
                        <p><c:out value="${error}"/></p>
                    </div>
                </div>
            </c:if>
            <c:if test="${msg !=null}">
                <div class="row">
                    <div class="alert alert-success col-sm-offset-2 col-sm-8" role="alert">
                        <p><c:out value="${msg}"/></p>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <a class="btn btn-primary col-sm-offset-10" href="AnadirRegistroPalacioJusticia" role="button">Nuevo registro</a>
            </div>
            <br><br><br>
            <div class="row">
                <div id="listaPalacioJusticia" class="table-responsive col-sm-offset-1 col-sm-10">
                    <table id="tables" class="table table-hover">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Número de sala</th>
                                <th>Procedimiento</th>
                                <th>Descripción</th>
                                <th>Fecha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="justicia" items="${justicias}">
                                <tr>
                                    <td>

                                        <a href="<c:url value="EditarRegistroPalacioJusticia"><c:param name="id" value="${justicia.id}"/></c:url>" >
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                            <a href="<c:url value="EliminarRegistroPalacioJusticia"><c:param name="id" value="${justicia.id}"/></c:url>" >
                                                <span class="glyphicon glyphicon-remove"></span>
                                            </a>
                                        </td>
                                        <td>${justicia.numerosala}</td>
                                    <td>${justicia.procedimiento}</td>
                                    <td>${justicia.descripcion}</td>
                                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${justicia.fecha}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
