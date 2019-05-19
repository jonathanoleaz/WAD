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
                  method="post" action="ArticuloServlet?accion=guardar">
                <table>
                    <tr>
                        <th>Clave:  </th>
                        <td><input type="number" name="id" readonly="readonly"
                                   value="<c:out value="${articulo.clavearticulo}"/>"/>
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
                        <th>Precio: </th>
                        <td>
                            <input type="text" name="txtPrecio"
                                   value="<c:out value="${articulo.precio}"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>Existencia: </th>
                        <td>
                            <input type="text" name="txtExistencia"
                                   value="<c:out value="${articulo.existencia}"/>"/>
                        </td>
                    </tr>

                    <tr>
                        <th>Categoría: </th>
                        <td>
                            <select name="txtCategoria">
                                <c:forEach var="carreras" items="${listaCategorias}">
                                    <option value="${carreras.idcategoria}">${carreras.descripcion}</option>
                                </c:forEach>
                            </select>
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
        <a href="ArticuloServlet?accion=lista">Listado</a>
    </body>
</html>
