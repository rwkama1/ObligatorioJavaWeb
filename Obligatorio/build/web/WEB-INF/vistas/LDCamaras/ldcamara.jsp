<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Liberar  Camara de Servicio">
    <jsp:body>
        <c:if test="${!empty ldcamara}">
            <p>¿Está seguro/a que desea Liberar la Camara con el Numero <strong>${ldcamara.ninventario}</strong>?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="ninventario" value="${ldcamara.ninventario}" />
                <input type="submit" name="accion" value="Liberar Camara del Servicio" />
            </form>
        </c:if>
        
        <p><a href="ldcamaras">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpTecnicos>