<%-- 
    Document   : CategoriaJSP
    Created on : 6/03/2019, 10:39:14 AM
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
        <fieldset>
            <legend>Datos de la categorias</legend>
            <form name="categoriaForm" id="categoriaForm"
                method="post" action="CategoriaServlet?accion=guardar">
                <table>
                    <tr>
                        <th>Nombre</th>
                        <td><input type="text" name="txtNombreCategoria"
                                   value="<c:out value="${categoria.nombreCategoria}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Descripción</th>
                        <td>
                            <input type="text" name="txtDescripcion"
                                   value="<c:out value="${categoria.descripcionCategoria}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Aceptar</th>
                        <td colspan="2">
                            <input type="submit" name="cmdEnviar" value="enviar"/>
                        </td>
                    </tr>
                </table>
            </form>
        </fieldset>
        <a href="CategoriaServlet?accion=ListaCategorias">Listado</a>
    </body>
</html>
