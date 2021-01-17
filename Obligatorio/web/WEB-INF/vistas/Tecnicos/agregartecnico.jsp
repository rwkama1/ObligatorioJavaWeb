

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Agregar Tecnicos">
    <jsp:body>
       
        <t:tagTecnicos idFoco="cedula" textoBoton="Agregar" />
       
        <p><a href="tecnicos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>