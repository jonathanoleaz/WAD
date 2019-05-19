<%-- 
    Document   : ListaCategorias
    Created on : 6/03/2019, 01:40:24 PM
    Author     : jonat
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="newcss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de articulos</title>
    </head>
    <body>
        <table>
            <tr>
                <th>ID</th>
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
                    <td><c:out value="${articulo.categoria.descripcion}"/></td>
                    <td><a href="CarreraServlet?accion=eliminar&id=${articulo.claveart} ">Eliminar</a></td>
                    <td><a href="CarreraServlet?accion=actualizar&id=${articulo.claveart} ">Actualizar</a></td>
                </tr>
            </c:forEach>

        </table>
        <a href="ArticuloForm.jsp">Nuevo</a>
    </body>
</html>
