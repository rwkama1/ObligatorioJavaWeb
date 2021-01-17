
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Agregar Administrador">
    <jsp:body>
        <t:tagEmpleados idFoco="cedula" textoBoton="Agregar" />
        
        <p><a href="admines">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>