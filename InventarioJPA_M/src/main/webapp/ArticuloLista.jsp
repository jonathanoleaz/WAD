<%-- 
    Document   : ListaCategorias
    Created on : 6/03/2019, 01:40:24 PM
    Author     : jonat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de articulos</title>
    </head>
    <body>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Existencia</th>
                <th>ID categoría</th>
                <th>E</th>
                <th>E</th>
            </tr>
            <c:forEach var="articulo" items="${lista}">
                <tr>
                    <td><c:out value="${articulo.claveart}"/></td>
                    <td><c:out value="${articulo.descripcion}"/></td>
                    <td><c:out value="${articulo.precio}"/></td>
                    <td><c:out value="${articulo.existencia}"/></td>
                    <td><c:out value="${articulo.idcategoria}"/></td>
                    <td><a href="CarreraServlet?accion=eliminar&id=${articulo.idcarrera} ">Eliminar</a></td>
                    <td><a href="CarreraServlet?accion=actualizar&id=${articulo.idcarrera} ">Actualizar</a></td>
                </tr>
            </c:forEach>

        </table>
        <a href="CarreraForm.jsp">Nueva carrera</a>
    </body>
</html>
