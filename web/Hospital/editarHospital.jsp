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
        <title>Editar registro hospital</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <c:set var="apli" value="Hospital"/>
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
                $("#horaIni").datetimepicker({
                    format: 'HH:mm',
                    locale: 'es'
                });
                $("#horaFin").datetimepicker({
                    format: 'HH:mm',
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
                    <h4>Modificar consulta médica</h4>
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
                        <form class="form-horizontal" name="hospital" id="fhospital" action="ModificarRegistroHospital" method="post">
                            <div class="form-group">
                                <label for="inputNombre" class="col-sm-3 control-label">Nombre *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="${hospital.nombremedico!=null ? hospital.nombremedico : nombre}" required="true" autofocus="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputApellidos" class="col-sm-3 control-label">Apellidos *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="${hospital.apellidomedico!=null ? hospital.apellidomedico : apellidos}" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputConsulta" class="col-sm-3 control-label">Número consulta *</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="consulta" id="consulta" placeholder="Número consulta" value="${hospital.numeroconsulta!=null ? hospital.numeroconsulta : consulta}" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputHoraInicio" class="col-sm-3 control-label">Hora inicio *</label>
                                <div class="col-sm-6">
                                    <input required="true" type="text" class="form-control" name="horaIni" id="horaIni" value="<fmt:formatDate pattern="HH:mm" value="${hospital.horainicio!=null ? hospital.horainicio : horaIni}" />">                
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputHoraFin" class="col-sm-3 control-label">Hora fin *</label>
                                <div class="col-sm-6">
                                    <input required="true" type="text" class="form-control" name="horaFin" id="horaFin" value="<fmt:formatDate pattern="HH:mm" value="${hospital.horafin!=null ? hospital.horafin : horaFin}" />">                   
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-5">
                                    <button type="submit" class="btn btn-primary">Modificar</button>
                                    <a class="btn btn-primary" href="ListaHospital" role="button">Cancelar</a>
                                </div>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>