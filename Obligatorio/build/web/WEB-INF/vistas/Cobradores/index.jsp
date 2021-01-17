

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Cobradores">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="cobradores?accion=agregarcobrador"><img src="imagenes/new.png" alt="Agregar" title="Agregar Cobrador..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>CLAVE</th><th>FECHAINGRESO</th><th>TRANSPORTE</th><th>SUELDO</th><th></th>
            </tr>
            
            <c:forEach items="${cobradores}" var="cobrador">
                <tr>
                    <td class="texto-centro">${cobrador.cedulaEmp}</td>
                    <td>${cobrador.nomEmp}</td>
                    <td>${cobrador.claveEmp}</td>
                    <td>${cobrador.fechaEmp}</td>
                     <td>${cobrador.tranporteCobrador}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${cobrador.sueldoEmp}" />
                    </td>
                    <td>
                        <a href="cobradores?accion=vercobrador&cedula=${cobrador.cedulaEmp}"><img src="imagenes/search.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="cobradores?accion=modificarcobrador&cedula=${cobrador.cedulaEmp}"><img src="imagenes/edit.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="cobradores?accion=eliminarcobrador&cedula=${cobrador.cedulaEmp}"><img src="imagenes/delete.png" alt="Eliminar" title="Eliminar..." ></a>
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
