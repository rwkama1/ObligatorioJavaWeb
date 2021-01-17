<%-- 
    Document   : verdcamara
    Created on : Jun 24, 2017, 1:46:25 AM
    Author     : Waldemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Camara">
    <jsp:body>
        <c:if test="${!empty dcamara}">
             
            <ul>
                <li><strong>Numero Inventario:</strong>${dcamara.ninventario}</li>
                <li><strong>Ubicacion:</strong>${dcamara.ubicacion}</li>
               <li><strong>Exterior o Interior?</strong>${dcamara.exteriorinterior}</li> 
              
            </ul>
        </c:if>
        
        <p><a href="dcamaras">Volver ...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>