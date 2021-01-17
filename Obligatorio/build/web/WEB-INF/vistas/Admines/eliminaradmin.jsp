

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Eliminar Administrador">
    <jsp:body>
        <c:if test="${!empty admin}">
            <p>¿Está seguro/a que desea eliminar el Administrador con cédula <strong>${admin.cedulaEmp}</strong> (${admin.nomEmp})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="cedula" value="${admin.cedulaEmp}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="admines">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>
