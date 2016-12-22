
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
        <title>Lista de consultas médicas</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Hospital"/>
        <link href="${ctx}/CSS/custom.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" media="all" type="text/css">

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
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
                    <h4>Hospital San Pedro</h4>
                </div>
            </div>
            <c:if test="${msg !=null}">
                <div class="row">
                    <div class="alert alert-success col-sm-offset-2 col-sm-8" role="alert">
                        <p><c:out value="${msg}"/></p>
                    </div>
                </div>
            </c:if>
            <c:if test="${error !=null}">
                <div class="row">
                    <div class="alert alert-danger col-sm-offset-2 col-sm-8" role="alert">
                        <p><c:out value="${error}"/></p>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <a class="btn btn-primary col-sm-offset-10" href="AnadirRegistroHospital" role="button">Nueva consulta médica</a>
            </div>
            <br><br><br>

            <div class="row">
                <div class="table-responsive col-sm-offset-1 col-sm-10">
                    <table id="tables" class="table table-hover">
                        <thead>
                            <tr>
                                <th hidden="true">id</th>
                                <th></th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Nº consulta</th>
                                <th>Hora inicio</th>
                                <th>Hora fin</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${hospitales}" var="hospital" varStatus="i"> 
                                <tr>
                                    <td hidden="true">${hospital.id} </td>
                                    <td>    
                                        <a href="<c:url value="ModificarRegistroHospital"><c:param name="id" value="${hospital.id}"/></c:url>"><span class="glyphicon glyphicon-pencil"></span></a>
                                        <a href="<c:url value="EliminarRegistroHospital"><c:param name="id" value="${hospital.id}"/></c:url>"><span class="glyphicon glyphicon-remove"></span></a>
                                        </td>
                                        <td>${hospital.nombremedico} </td>
                                    <td>${hospital.apellidomedico} </td>
                                    <td>${hospital.numeroconsulta} </td>
                                    <td><fmt:formatDate pattern="HH:mm" value="${hospital.horainicio}" /></td>
                                    <td> <fmt:formatDate pattern="HH:mm" value="${hospital.horafin}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <br/><br/>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" <c:if test="${error_foto!=null}">aria-expanded="true"</c:if> <c:if test="${error_foto!=null}">aria-expanded="false"</c:if>aria-controls="collapseExample">
                    Añadir plano hospital
                </a>
                    <div <c:if test="${error_foto!=null}"> class="collapse in" </c:if> <c:if test="${error_foto==null}"> class="collapse" </c:if> id="collapseExample" <c:if test="${error_foto!=null}"> aria-expended="true" </c:if>>
                    <br><br>
                <c:if test="${error_foto!=null}">
                    <div class="alert alert-danger">
                        ${error_foto}
                    </div>  
                </c:if>
                <form enctype="multipart/form-data" class="form-horizontal" name="login" id="flogin" action="AnadirPlanoHospital" method="post">
                    <div class="form-group">
                        <label for="inputFondo">Plano hospital</label>
                        <c:if test="${plano!=null}">
                            <a target="_blank" href="${ctx}/plano/${plano}">Plano hospital</a>
                        </c:if> 
                        <input type="file" name="plano" id="plano">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar plano hospital</button>
                </form>
            </div>
        </div>
    </body>
</html>
