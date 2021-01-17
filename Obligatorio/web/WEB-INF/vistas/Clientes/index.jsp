
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Clientes">
    <jsp:body>
        <li><a href="scamaras">Ver y Desactivar Servicios de Camaras</a></li>
        <li><a href="salarmas">Ver y Desactivar Servicios de Alarmas</a></li>
        <br>
        <form>
          
           <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="BuscarCliente" /></p>
        </form>
        
        <p><a href="clientes?accion=agregarclientepropiedad"><img src="imagenes/new.png" alt="Agregar" title="Agregar Cliente con Propiedad..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>DIRECCION</th><th>ZONA O BARRIO</th><th>TELEFONO</th><th></th>
            </tr>
            
            <c:forEach items="${clientes}" var="cliente">
                <tr>
                    <td class="texto-centro">${cliente.cedulaCliente}</td>
                    <td>${cliente.nomCliente}</td>
                    <td>${cliente.dirCliente}</td>
                    <td>${cliente.zonabarrioCliente}</td>
                    <td>${cliente.telCliente}</td>
                    <td>
                        <a href="clientes?accion=vercliente&cedula=${cliente.cedulaCliente}"><img src="imagenes/search.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="clientes?accion=modificarcliente&cedula=${cliente.cedulaCliente}"><img src="imagenes/edit.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                     
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

