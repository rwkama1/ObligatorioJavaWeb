<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Listar Recibos">
    <jsp:body>

        <table class="listado">
            <tr>
                <th>IDRecibo</th><th>FechaFacturacion</th><th>ImporteTotal</th><th>Cedula Cobrador</th><th>Cedula Cliente</th><th></th>
            </tr>
            
           
            <c:forEach items="${recibos}" var="recibo">
                <tr>
                    <td class="texto-centro">${recibo.idRecibo}</td>
                    <td>${recibo.fechaFacturacion}</td>
                    <td >
                        <fmt:formatNumber type="number" pattern="0.00" value="${recibo.importetotal}" />
                    </td>
                    <td>${recibo.cobrador.cedulaEmp}</td>
                    <td>${recibo.cliente.cedulaCliente}</td>
                    <td>
                   <a href="recibos?accion=verrecibo&id=${recibo.idRecibo}"><img src="imagenes/buscar.png" alt="Ver" title="Ver Recibo con Lineas..." ></a>&nbsp;&nbsp;
                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="recibos">Volver...</a></p>
        
        <t:mensaje />
       
    </jsp:body>
</t:mpAdministrativos>