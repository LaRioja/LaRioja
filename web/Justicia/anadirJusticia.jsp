
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
        <title>Añadir registro palacio justicia</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Justicia"/>
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <link rel="stylesheet" href="${ctx}/CSS/jquery-ui.css" type="text/css">
        <link href="${ctx}/CSS/bootstrap-datetimepicker.min.css" rel="stylesheet" media="all" type="text/css">

        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/moment-with-locales.min.js"></script>
        <script src="${ctx}/JS/jquery-ui.js"></script>
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
                    <h4>Añadir nuevo registro</h4>
                </div>
            </div>
            <br/><br/>
            <c:if test="${error}">
                <div class="row">
                    <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                        <ul>
                            <c:if test="${errorsala !=null}">
                                <li><c:out value="${errorsala}"/></li>
                                </c:if>
                                <c:if test="${errorfecha !=null}">
                                <li><c:out value="${errorfecha}"/></li>
                                </c:if>
                                <c:if test="${erroradd !=null}">
                                <li><c:out value="${erroradd}"/></li>
                                </c:if>
                        </ul>
                    </div>  
                </div>
            </c:if>
            <div class="row">
                <form class="form-horizontal" name="ajusticia" id="ajusticia" action="AnadirRegistroPalacioJusticia" method="post">
                    <div class="form-group">
                        <label for="inputSala" class="col-sm-3 control-label">Número de sala *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sala" id="sala" placeholder="Número de sala" value="<c:out value="${sala}"/>" required="true" autofocus="true" maxlength="6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputProcedimiento" class="col-sm-3 control-label">Procedimiento *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="procedimiento" id="procedimiento" placeholder="Procedimiento" value="<c:out value="${procedimiento}"/>" required="true" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDescripcion" class="col-sm-3 control-label">Descripción *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="descripcion" id="descripcion" placeholder="Descripción" value="<c:out value="${descripcion}"/>" required="true" maxlength="500">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFecha" class="col-sm-3 control-label">Fecha *</label>
                        <div class="col-sm-6">
                            <input required="true" type="text" class="form-control" name="fecha" id="fecha" placeholder="Fecha" value="<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${fecha}" />">                   
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                            <a class="btn btn-primary" href="ListaPalacioJusticia" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
