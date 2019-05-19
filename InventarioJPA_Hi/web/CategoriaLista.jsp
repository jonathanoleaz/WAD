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
        <title>Lista de categorías</title>
    </head>
    <body>
        <table>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>E</th>
                <th>E</th>
            </tr>
            <c:forEach var="categoria" items="${lista}">
                <tr>
                    <td><c:out value="${categoria.idcategoria}"/></td>
                    <td><c:out value="${categoria.descripcion}"/></td>
                    
                    
                    <td><a href="CarreraServlet?accion=eliminar&id=${categoria.idcategoria} ">Eliminar</a></td>
                    <td><a href="CarreraServlet?accion=actualizar&id=${categoria.idcategoria} ">Actualizar</a></td>
                </tr>
            </c:forEach>

        </table>
        <a href="ArticuloForm.jsp">Nuevo</a>
    </body>
</html>
