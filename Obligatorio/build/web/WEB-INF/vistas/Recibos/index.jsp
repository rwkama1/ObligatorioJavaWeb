<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Generar Recibos">
    <jsp:body>

        <table class="listado">
            <li><a href="recibos?accion=listarecibo">Ver Recibos Generados</a></li>
            <h1>CLIENTES PARA GENERAR RECIBOS</h1>
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
                        <a href="recibos?accion=generarrecibo&cedula=${cliente.cedulaCliente}"><img src="imagenes/recibo.gif" alt="Agregar" title="Generar Recibo del Cliente..." ></a>&nbsp;&nbsp;
                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaladmin">Volver...</a></p>
        
        <t:mensaje />
       
    </jsp:body>
</t:mpAdministrativos>

