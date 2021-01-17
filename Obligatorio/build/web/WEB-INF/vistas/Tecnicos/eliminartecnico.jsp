

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Eliminar Tecnicos">
    <jsp:body>
        <c:if test="${!empty tecnico}">
            <p>¿Está seguro/a que desea eliminar el Tecnico con cédula <strong>${tecnico.cedulaEmp}</strong> (${tecnico.nomEmp})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="cedula" value="${tecnico.cedulaEmp}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="tecnicos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>