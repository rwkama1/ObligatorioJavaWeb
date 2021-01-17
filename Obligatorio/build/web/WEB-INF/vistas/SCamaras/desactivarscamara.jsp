
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Desactivar Servicio Camara">
    <jsp:body>
        <c:if test="${!empty scamara}">
            <p>¿Está seguro/a que desea desactivar el Servicio de Camara con ID <strong>${scamara.idServicio}</strong> </p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="idServicio" value="${scamara.idServicio}" />
                <input type="submit" name="accion" value="Desactivar" />
            </form>
        </c:if>
        
        <p><a href="scamaras">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>