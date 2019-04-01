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
        <title>Lista de productos</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Clave</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Existencia</th>
                <th>ID categoría</th>
                <th>E</th>
                <th>E</th>
            </tr>
            <c:forEach var="productos" items="${listaDeArticulos}">
                <tr>
                    <td><c:out value="${productos.clavearticulo}"/></td>
                    <td><c:out value="${productos.nombreproducto}"/></td>
                    <td><c:out value="${productos.descripcionproducto}"/></td>
                    <td><c:out value="${productos.precio}"/></td>
                    <td><c:out value="${productos.existencia}"/></td>
                    <td><a href="ArticuloServlet?accion=eliminarArticulo&id=${productos.clavearticulo} ">Eliminar</a></td>
                    <td><a href="ArticuloServlet?accion=actualizarArticulo&id=${productos.clavearticulo} ">Actualizar</a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
