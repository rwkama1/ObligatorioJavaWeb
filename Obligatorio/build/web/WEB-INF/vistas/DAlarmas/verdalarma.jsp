
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Alarma">
    <jsp:body>
        <c:if test="${!empty dalarma}">
             
            <ul>
                <li><strong>Numero Inventario:</strong>${dalarma.ninventario}</li>
                <li><strong>Ubicacion:</strong>${dalarma.ubicacion}</li>
               
              
            </ul>
        </c:if>
        
        <p><a href="dalarmas">Volver ...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>