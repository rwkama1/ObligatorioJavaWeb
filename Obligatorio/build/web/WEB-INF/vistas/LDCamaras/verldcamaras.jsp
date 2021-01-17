<%-- 
    Document   : verldcamaras
    Created on : Jun 26, 2017, 11:07:06 PM
    Author     : Waldemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Ver Camara">
    <jsp:body>
        <c:if test="${!empty ldcamara}">
            <ul>
                <li><strong>Numero de Inventario:</strong>${ldcamara.ninventario}</li>
                <li><strong>Ubicación:</strong>${ldcamara.ubicacion}</li>
                <li><strong>Tecnico:</strong>${ldcamara.tecnico.cedulaEmp}</li> 
                <li><strong>ID del Servicio:</strong>${ldcamara.servicioCamara.idServicio}</li> 
                 <li><strong>Exterior o Interior:</strong>${ldcamara.exteriorinterior}</li> 
            </ul>
        </c:if>
        
        <p><a href="ldcamaras">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpTecnicos>