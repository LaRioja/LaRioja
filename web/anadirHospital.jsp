
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Añadir registro hospital</title>
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
        <div class="row">
            <div class="col-sm-offset-1 col-sm-5">
                <h4>Añadir nueva consulta médica</h4>
            </div>
        </div>
        <div class="row">
            <div class="container">
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
                <form class="form-horizontal" name="hospital" id="fhospital" action="AnadirRegistroHospital" method="post">
                    <div class="form-group">
                        <label for="inputNombre" class="col-sm-3 control-label">Nombre *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="<c:out value="${nombre}"/>" required="true" autofocus="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidos" class="col-sm-3 control-label">Apellidos *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder="Apellidos" value="<c:out value="${apellidos}"/>" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputConsulta" class="col-sm-3 control-label">Número consulta *</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="consulta" id="consulta" placeholder="Número consulta" value="<c:out value="${consulta}"/>" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputHoraInicio" class="col-sm-3 control-label">Hora inicio *</label>
                        <div class="col-sm-6">
                            <input required="true" type="text" class="form-control" name="horaIni" id="horaIni" value="<fmt:formatDate pattern="HH:mm" value="${horaIni}" />">                   
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputHoraFin" class="col-sm-3 control-label">Hora fin *</label>
                        <div class="col-sm-6">
                            <input required="true" type="text" class="form-control" name="horaFin" id="horaFin" value="<fmt:formatDate pattern="HH:mm" value="${horaFin}" />">                   
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                            <a class="btn btn-primary" href="ListaHospital" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>