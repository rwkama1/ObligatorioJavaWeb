
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Administradores">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="admines?accion=agregaradmin"><img src="imagenes/new.png" alt="Agregar Admin" title="Agregar Admin..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>CLAVE</th><th>FECHAINGRESO</th><th>SUELDO</th><th></th>
            </tr>
            
            <c:forEach items="${admines}" var="admin">
                <tr>
                    <td class="texto-centro">${admin.cedulaEmp}</td>
                    <td>${admin.nomEmp}</td>
                    <td>${admin.claveEmp}</td>
                    <td>${admin.fechaEmp}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${admin.sueldoEmp}" />
                    </td>
                    <td>
                        <a href="admines?accion=veradmin&cedula=${admin.cedulaEmp}"><img src="imagenes/search.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="admines?accion=modificaradmin&cedula=${admin.cedulaEmp}"><img src="imagenes/edit.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="admines?accion=eliminaradmin&cedula=${admin.cedulaEmp}"><img src="imagenes/delete.png" alt="Eliminar" title="Eliminar..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaladmin">Volver...</a></p>
        
        <t:mensaje />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:mpAdministrativos>

