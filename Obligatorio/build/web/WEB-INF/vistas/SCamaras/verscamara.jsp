

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Servicio Camara">
    <jsp:body>
        <c:if test="${!empty scamara}">
            <jsp:useBean id="scamara" type="compartidos.beans.datatypes.DTServicioCamara" scope="request" />

            <ul>
                <li><strong>ID Servicio:</strong> <jsp:getProperty name="scamara" property="idServicio" /></li>
                <li><strong>Fecha Contratacion:</strong> <jsp:getProperty name="scamara" property="fechaServicio" /></li>
                 <li><strong>Monitoreo:</strong>    <c:choose>
                        <c:when test="${scamara.moniteroServicio==true}">
                            <td><input type="checkbox" name="moniteroServicio" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="moniteroServicio"/></td>
                        </c:otherwise>
                           </c:choose> </li>
                           
               <li><strong>Propiedad:</strong>${scamara.propServicio.idpropiedad}</li> 
               <li><strong>Cliente:</strong>${scamara.clienteServicio.cedulaCliente}</li> 
               <li><strong>Terminal de Grabación:</strong>
                   <c:choose>
                       <c:when test="${scamara.terminalgrabacion==true}">
                            <td><input type="checkbox" name="terminalgrabacion" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="terminalgrabacion"/></td>
                        </c:otherwise>
                    </c:choose>  
               </li>
            </ul>
        </c:if>
        
        <p><a href="scamaras">Volver ...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>