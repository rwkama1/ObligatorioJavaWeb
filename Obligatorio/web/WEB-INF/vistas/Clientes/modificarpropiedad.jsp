

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Modificar Propiedad">
    <jsp:body>
        <c:if test="${!ocultarFormulario}">
            <t:tagPropiedad deshabilitarClave="true" idFoco="dirpropiedad" textoBoton="Modificar" />
        </c:if>
        
        <p><a href="clientes">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>