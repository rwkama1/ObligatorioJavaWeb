<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Liberar  Alarma de Servicio">
    <jsp:body>
        <c:if test="${!empty ldalarma}">
            <p>¿Está seguro/a que desea Liberar la Alarma con el Numero <strong>${ldalarma.ninventario}</strong>?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="ninventario" value="${ldalarma.ninventario}" />
                <input type="submit" name="accion" value="Liberar Alarma del Servicio" />
            </form>
        </c:if>
        
        <p><a href="ldalarmas">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpTecnicos>