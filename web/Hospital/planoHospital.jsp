
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
        <title>Plano hospital</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Hospital"/>
        <c:set var="selec" value="Plano"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <link href="${ctx}/CSS/bootstrap-datetimepicker.min.css" rel="stylesheet" media="all" type="text/css">
        <link href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" media="all" type="text/css">

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/moment-with-locales.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script src="${ctx}/JS/bootstrap.js"></script>
        
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
                    <h4>Planos hospital</h4>
                </div>
            </div>
            <c:if test="${msg !=null}">
                <div class="row">
                    <div class="alert alert-success col-sm-offset-2 col-sm-8" role="alert">
                        <p><c:out value="${msg}"/></p>
                    </div>
                </div>
            </c:if>
            <c:if test="${error_borrar!=null}">
                <div class="alert alert-danger col-sm-offset-2 col-sm-8" role="alert">
                    ${error_borrar}
                </div>  
            </c:if>
            <div class="row">
                <a class="btn btn-primary col-sm-offset-10" href="AnadirPlanoHospital" role="button">Añadir plano hospital</a>
            </div>
            <br><br><br>
            <div class="row">
                <div class="table-responsive col-sm-offset-1 col-sm-10">
                    <table id="tables" class="table table-hover">
                        <thead>
                            <tr>
                                <th hidden="true">id</th>
                                <th></th>
                                <th>Nombre Fichero</th>
                                <th>Tamaño</th>
                                <th>Fecha de creación</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ficheros}" var="fichero" varStatus="i">
                                <c:if test="${!fichero.isDirectory()}">
                                    <tr>
                                        <td hidden="true">${fichero.getName()}</td>
                                        <td><a title="Eliminar plano del hospital" href="<c:url value="EliminarPlano"><c:param name="id" value="${fichero.getName()}"/></c:url>"><span class="glyphicon glyphicon-remove"></span></a></td>
                                        <td><a target="_blank" href="${ctx}/contenidos/plano/${fichero.getName()}">${fichero.getName()}</a></td>
                                        <td>
                                            <c:if test="${fichero.getContentLength()>1024}">
                                                <c:if test="${fichero.getContentLength()/1024>1024}">
                                                    <fmt:formatNumber value="${(fichero.getContentLength()/1024)/1024}" pattern="#,##0.00" /> MB
                                                </c:if>
                                                <c:if test="${fichero.getContentLength()/1024<1024}">
                                                    <fmt:formatNumber value="${fichero.getContentLength()/1024}" pattern="#,##0.00" /> KB
                                                </c:if>
                                            </c:if>
                                            <c:if test="${fichero.getContentLength()<1024}">
                                                <fmt:formatNumber value="${fichero.getContentLength()}" pattern="#,##0.00" /> Bytes
                                            </c:if>
                                        </td>
                                        <td><fmt:formatDate type="both" 
                                                        dateStyle="short" timeStyle="short" 
                                                        value="${fichero.getCreation()}" /></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>