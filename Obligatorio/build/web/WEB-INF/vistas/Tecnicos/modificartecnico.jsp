

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Modificar Tecnico">
    <jsp:body>
        <c:if test="${!ocultarFormulario}">
            <t:tagTecnicos deshabilitarClave="true" idFoco="nombre" textoBoton="Modificar" />
        </c:if>
        
        <p><a href="tecnicos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>