<%-- 
    Document   : CategoriaJSP
    Created on : 6/03/2019, 10:39:14 AM
    Author     : jonat
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="newcss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de articulo</title>
    </head>
    <body>
        <fieldset>
            <legend>Datos</legend>
            <form name="categoriaForm" id="categoriaForm"
                  method="post" action="CategoriaServlet?accion=guardar">
                <table>
                    <tr>
                        <th>ID cat:  </th>
                        <td><input type="number" name="id" readonly="readonly"
                                   value="<c:out value="${articulo.idcategoria}"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>Descripción: </th>
                        <td>
                            <input type="text" name="txtDescripcion"
                                   value="<c:out value="${articulo.descripcion}"/>"/>
                        </td>
                    </tr>                    

                    <tr>
                        <th>Aceptar </th>
                        <td colspan="2">
                            <input type="submit" name="cmdEnviar" value="enviar"/>
                        </td>
                    </tr>
                </table>
            </form>
        </fieldset>
        <a href="CategoriaServlet?accion=lista">Listado</a>
    </body>
</html>
