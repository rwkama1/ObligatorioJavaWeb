

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Modificar Cobradores">
    <jsp:body>
        <c:if test="${!ocultarFormulario}">
            <t:tagCobradores deshabilitarClave="true" idFoco="nombre" textoBoton="Modificar" />
        </c:if>
        
        <p><a href="cobradores">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>