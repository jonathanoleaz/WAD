<%-- 
    Document   : ArticuloJSP
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
            <legend>Datos del producto</legend>
            <form name="articuloForm" id="articuloForm"
                  method="post" action="ArticuloServlet?accion=guardar">
                <table>
                    <tr>
                        <th>Clave articulo</th>
                        <td><input type="text" name="txtClaveArticulo"
                                   value="<c:out value="${producto.clavearticulo}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Nombre producto: </th>
                        <td>
                            <input type="text" name="txtNombreProducto"
                                   value="<c:out value="${producto.nombreproducto}"/>"/>
                        </td>
                    </tr>
                    <tr>
                    <tr>
                        <th>Descripción: </th>
                        <td>
                            <input type="text" name="txtDescripcionProducto"
                                   value="<c:out value="${producto.descripcionproducto}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Precio: </th>
                        <td>
                            <input type="number" min="0" name="txtPrecio"
                                   value="<c:out value="${producto.precio}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Existencia: </th>
                        <td>
                            <input type="number" name="txtExistencia"
                                   value="<c:out value="${producto.existencia}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>ID categoría: </th>
                        <td>
                            <input type="number" name="txtCategoria"
                                   value="<c:out value="${producto.existencia}"/>"/>
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
        <a href="ArticuloServlet?accion=ListaProductos">Listado</a>
    </body>
</html>
