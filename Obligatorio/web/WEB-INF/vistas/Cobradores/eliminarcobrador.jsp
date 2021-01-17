
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Eliminar Cobrador">
    <jsp:body>
        <c:if test="${!empty cobrador}">
            <p>¿Está seguro/a que desea eliminar el Cobrador con cédula <strong>${cobrador.cedulaEmp}</strong> (${cobrador.nomEmp})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="cedula" value="${cobrador.cedulaEmp}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="cobradores">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>