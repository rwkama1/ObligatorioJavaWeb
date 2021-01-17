

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Servicio Alarma">
    <jsp:body>
        <c:if test="${!empty salarma}">
            <jsp:useBean id="salarma" type="compartidos.beans.datatypes.DTServicioAlarma" scope="request" />

            <ul>
                <li><strong>ID Servicio:</strong> <jsp:getProperty name="salarma" property="idServicio" /></li>
                <li><strong>Fecha Contratacion:</strong> <jsp:getProperty name="salarma" property="fechaServicio" /></li>
                 <li><strong>Monitoreo:</strong>    <c:choose>
                        <c:when test="${salarma.moniteroServicio==true}">
                            <td><input type="checkbox" name="moniteroServicio" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="moniteroServicio"/></td>
                        </c:otherwise>
                           </c:choose> </li>
                           
               <li><strong>Propiedad:</strong>${salarma.propServicio.idpropiedad}</li> 
               <li><strong>Cliente:</strong>${salarma.clienteServicio.cedulaCliente}</li> 
               <li><strong>Codigo de Anulación:</strong>${salarma.codigoanulacion}</li>
                 
            </ul>
        </c:if>
        
        <p><a href="salarmas">Volver ...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>