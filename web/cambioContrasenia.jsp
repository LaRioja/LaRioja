
<%@page contentType="text/html" pageEncoding="UTF-8"%>.
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambio contraseña</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/custom.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="recover_container" class="container">
            <div class="row">
                <h2 class="col-sm-offset-4 col-sm-5">Cambiar contraseña</h2>
            </div>
            <c:choose>
                <c:when test="${errorpeti!=null}">
                    <div class="row">
                        <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                            <c:out value="${errorpeti}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-offset-3 col-sm-2" role="alert">
                            <a class="btn bg-primary" href="${ctx}/Inicio">Salir de aquí</a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${errores}">
                        <div class="row">
                            <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                                <ul>
                                    <c:if test="${errorcoincide !=null}">
                                        <li><c:out value="${errorcoincide}"/></li>
                                        </c:if>
                                        <c:if test="${errorpwd !=null}">
                                        <li><c:out value="${errorpwd}"/></li>
                                        </c:if>
                                        <c:if test="${errorconf !=null}">
                                        <li><c:out value="${errorconf}"/></li>
                                        </c:if>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <p class="text-info col-sm-offset-4 col-sm-5">La clave debe tener una longitud mayor de 8 y contener letras (al menos una mayúscula y una minúscula) y números</p>
                    </div>
                    <div class="row">
                        <form class="form-horizontal" name="login" id="flogin" action="CambioContrasenia" method="post">
                            <div class="form-group">
                                <label for="inputUser" class="col-sm-4 control-label">Nueva contraseña *</label>
                                <div class="col-sm-5">
                                    <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Nueva contraseña" required="true" autofocus="true" value="<c:out value="${pwd}"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword" class="col-sm-4 control-label">Repite contraseña *</label>
                                <div class="col-sm-5">
                                    <input type="password" class="form-control" name="rpwd" id="rpwd" placeholder="Repite contraseña" required="true" value="<c:out value="${rpwd}"/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-5">
                                    <button type="submit" class="btn btn-primary">Modificar contraseña</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
