<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Ver Alarma">
    <jsp:body>
        <c:if test="${!empty ldalarma}">
            <ul>
                <li><strong>Numero de Inventario:</strong>${ldalarma.ninventario}</li>
                <li><strong>Ubicación:</strong>${ldalarma.ubicacion}</li>
                <li><strong>Tecnico:</strong>${ldalarma.tecnico.cedulaEmp}</li> 
                <li><strong>ID del Servicio:</strong>${ldalarma.servicioAlarma.idServicio}</li> 
               
            </ul>
        </c:if>
        
        <p><a href="ldalarmas">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpTecnicos>