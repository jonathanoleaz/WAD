<%-- 
    Document   : ListaCategorias
    Created on : 6/03/2019, 01:40:24 PM
    Author     : jonat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de categorías</title>
    </head>
    <body>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>E</th>
                <th>E</th>
            </tr>
            <c:forEach var="categorias" items="${listaDeCategorias}">
                <tr>
                    <td><c:out value="${categorias.idcategoria}"/></td>
                    <td><c:out value="${categorias.nombreCategoria}"/></td>
                    <td><c:out value="${categorias.descripcionCategoria}"/></td>
                    <td><a href="CategoriaServlet?accion=eliminarCategoria&id=${categorias.idcategoria} ">Eliminar</a></td>
                    <td><a href="CategoriaServlet?accion=actualizarCategoria&id=${categorias.idcategoria} ">Actualizar</a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
