<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Recibo y sus Lineas">
    <jsp:body>
        <c:if test="${!empty recibo}">
            
            <ul>
                <li><strong>IDRecibo:</strong> ${recibo.idRecibo}</li>
                <li><strong>FechaFacturacion:</strong> ${recibo.fechaFacturacion}</li>
                <li><strong>ImporteTotal:$</strong>  <td >
                        <fmt:formatNumber type="number" pattern="0.00" value="${recibo.importetotal}" />
                    </td></li>
                 <li><strong>Cedula Cobrador:</strong> ${recibo.cobrador.cedulaEmp}</li>
                <li><strong>Cedula Cliente:</strong> ${recibo.cliente.cedulaCliente}</li>
                
                <LI><strong>Lineas del Recibo:</STRONG></li>
              
           <table class="listado">
               
            <tr>
                <th>IDLinea</th><th>Numero de Servicio</th><th>Importe</th><th>Propiedades</th><th>Dirección Propiedad</th>
            </tr>
            
            <c:forEach items="${recibo.lineasRecibo}" var="linea">
                <tr>
                    <td class="texto-centro">${linea.idLinea}</td>
                    <td>${linea.servicelinea.idServicio}</td>
                    <td> 
                        <fmt:formatNumber type="number" pattern="0.00" value="${linea.importeLinea}" />
                    </td>
                     <td>${linea.servicelinea.propServicio.idpropiedad}</td>
                     <td>${linea.servicelinea.propServicio.dirProp}</td>
                
                </tr>
            </c:forEach>
        </table>
            </ul>
        </c:if>
        
        <p><a href="recibos?accion=listarecibo">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>