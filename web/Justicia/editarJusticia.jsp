
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
        <title>Editar registro palacio justicia</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <link href="${ctx}/CSS/bootstrap-datetimepicker.min.css" rel="stylesheet" media="all" type="text/css">

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/moment-with-locales.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script src="${ctx}/JS/bootstrap.js"></script>
        <script src="${ctx}/JS/bootstrap-datetimepicker.min.js"></script>
        <script>
            $(function () {
                $("#fecha").datetimepicker({
                    locale: 'es'
                });
            });
        </script>
    </head>
    <body>
        <%@include file="../navbar.html" %>
        <div class="container">
            <div class="row">
                <div class="col-sm-offset-1 col-sm-5">
                    <h4>Editar registro</h4>
                </div>
            </div>
            <c:choose>
                <c:when test="${error!=null}">
                    <div class="row">
                        <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                            <c:out value="${error}"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${errores}">
                        <div class="row">
                            <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                                <ul>
                                    <c:if test="${errorsala !=null}">
                                        <li><c:out value="${errorsala}"/></li>
                                        </c:if>
                                        <c:if test="${errorfecha !=null}">
                                        <li><c:out value="${errorfecha}"/></li>
                                        </c:if>
                                        <c:if test="${erroredit !=null}">
                                        <li><c:out value="${erroredit}"/></li>
                                        </c:if>
                                </ul>
                            </div>  
                        </div>
                    </c:if>
                    <div class="row">
                        <form class="form-horizontal" name="ejusticia" id="ejusticia" action="EditarRegistroPalacioJusticia" method="post">
                            <div class="form-group">
                                <label for="inputSala" class="col-sm-3 control-label">Número de sala *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="sala" id="sala" placeholder="Número de sala" value="${justicia.numerosala!=null ? justicia.numerosala : sala}" required="true" autofocus="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputProcedimiento" class="col-sm-3 control-label">Procedimiento *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="procedimiento" id="procedimiento" placeholder="Procedimiento" value="${justicia.procedimiento!=null ? justicia.procedimiento : procedimiento}" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputDescripcion" class="col-sm-3 control-label">Descripción *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="descripcion" id="descripcion" placeholder="Descripción" value="${justicia.descripcion!=null ? justicia.descripcion : descripcion}" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputFecha" class="col-sm-3 control-label">Fecha *</label>
                                <div class="col-sm-6">
                                    <input required="true" type="text" class="form-control" name="fecha" id="fecha" placeholder="Fecha (19/12/2016 10:15)" value="<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${justicia.fecha!=null ? justicia.fecha : fecha}" />">                   
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-5">
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                    <a class="btn btn-primary" href="ListaPalacioJusticia" role="button">Cancelar</a>
                                </div>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
