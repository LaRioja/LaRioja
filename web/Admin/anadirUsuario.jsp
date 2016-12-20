
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir usuarios</title>
        <meta name="description" content="Aplicación">
        <meta name="author" content="Hiberus Osaba">

        <c:set var="ctx" value="${pageContext.request.contextPath}"/>
        <link href="${ctx}/CSS/customAdmin.css" rel="stylesheet" media="all" type="text/css">
        <link href="${ctx}/CSS/bootstrap.min.css" rel="stylesheet" media="all" type="text/css">
        <script src="${ctx}/JS/jquery-1.12.4.min.js"></script>
        <script src="${ctx}/JS/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="adduser_container" class="container">
            <div class="row">
                <div class="col-sm-offset-1 col-sm-5">
                    <h4>Añadir nuevos usuarios</h4>
                </div>
            </div>
            <c:if test="${errores !=null}">
                <div class="row">
                    <div class="alert alert-danger col-sm-offset-3 col-sm-6" role="alert">
                        <ul>
                            <c:if test="${errorpwd !=null}">
                                <li><c:out value="${errorpwd}"/></li>
                                </c:if>
                                <c:if test="${errormail !=null}">
                                <li><c:out value="${errormail}"/></li>
                                </c:if>
                                <c:if test="${error !=null}">
                                <li><c:out value="${error}"/></li>
                                </c:if>
                                <c:if test="${errorrol !=null}">
                                <li><c:out value="${errorrol}"/></li>
                                </c:if>
                                <c:if test="${errorname !=null}">
                                <li><c:out value="${errorname}"/></li>
                                </c:if>
                                <c:if test="${erroremail !=null}">
                                <li><c:out value="${erroremail}"/></li>
                                </c:if>
                        </ul>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <form class="form-horizontal" name="login" id="flogin" action="AnadirUsuario" method="post">
                    <div class="form-group">
                        <label for="inputUser" class="col-sm-4 control-label">Usuario *</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="usr" id="usr" placeholder="Nombre de usuario" required="true" autofocus="true" value="<c:out value="${usr}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-4 control-label">Contraseña *</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Contraseña" required="true" value="<c:out value="${pwd}"/>">
                        </div>
                        <div class="row">
                            <p class="text-info col-sm-offset-4 col-sm-5">La clave debe tener una longitud mayor de 8 y contener letras (al menos una mayúscula y una minúscula) y números</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-4 control-label">Correo electrónico *</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control" name="mail" id="mail" placeholder="Correo electrónico" required="true" value="<c:out value="${mail}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRol" class="col-sm-4 control-label">Rol *</label>
                        <div class="col-sm-5">
                            <c:forEach var="rol" items="${rols}">
                                <label class="radio-inline"><input type="checkbox" name="rol" id="rol1" value="${rol.id}"> ${rol.rolname}</label>
                                </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <button type="submit" class="btn btn-primary">Añadir usuario</button>
                            <a href="ListaUsuarios" class="btn btn-primary" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
