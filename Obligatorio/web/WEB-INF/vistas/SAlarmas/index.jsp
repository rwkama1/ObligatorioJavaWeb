
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Servicios de Alarmas">
    <jsp:body>
        <table class="listado">
            <h1>Servicios de Alarma</h1>
            <tr>
                <th>IDSERVICIO</th><th>FECHA CONTRATACION</th><th>MONITOREO</th><th>CODIGO DE ANULACIÓN</th><th>PROPIEDAD<th>CEDULACLIENTE</th><th></th>
            </tr>
            
            <c:forEach items="${salarmas}" var="salarma">
                <tr>
                    <td class="texto-centro">${salarma.idServicio}</td>
                    <td>${salarma.fechaServicio}</td>
                     <c:choose>
                        <c:when test="${salarma.moniteroServicio==true}">
                            <td><input type="checkbox" name="moniteroservicio" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="moniteroservicio"/></td>
                        </c:otherwise>
                           </c:choose> 
                      <td>${salarma.codigoanulacion}</td>
                      <td>${salarma.propServicio.idpropiedad}</td>  
                     <td>${salarma.clienteServicio.cedulaCliente}</td>
                    <td>
                        <a href="salarmas?accion=versalarma&idsalarma=${salarma.idServicio}"><img src="imagenes/buscar.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="salarmas?accion=desactivarsalarma&idsalarma=${salarma.idServicio}"><img src="imagenes/eliminar.png" alt="Desactivar" title="Desactivar..." ></a>&nbsp;&nbsp;

                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="clientes">Volver a los Clientes...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpAdministrativos>
