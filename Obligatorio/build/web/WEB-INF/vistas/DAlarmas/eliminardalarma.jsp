

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Eliminar Alarma">
    <jsp:body>
        <c:if test="${!empty dalarma}">
            <p>¿Está seguro/a que desea eliminar la Alarma con el Numero <strong>${dalarma.ninventario}</strong> </p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="ninventario" value="${dalarma.ninventario}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="dalarmas">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>