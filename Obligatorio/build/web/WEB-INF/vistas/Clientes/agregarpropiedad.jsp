

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Agregar Propiedad">
    <jsp:body>
        <t:tagPropiedad idFoco="idpropiedad" textoBoton="Agregar" />
        
        <p><a href="clientes">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>