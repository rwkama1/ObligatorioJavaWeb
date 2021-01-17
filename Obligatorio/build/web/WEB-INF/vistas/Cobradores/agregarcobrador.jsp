

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Agregar Cobrador">
    <jsp:body>
       
        <t:tagCobradores idFoco="cedula" textoBoton="Agregar" />
       
        <p><a href="cobradores">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>