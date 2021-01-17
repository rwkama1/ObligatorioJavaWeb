<%-- 
    Document   : eliminardcamara
    Created on : Jun 24, 2017, 1:46:02 AM
    Author     : Waldemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Eliminar Camara">
    <jsp:body>
        <c:if test="${!empty dcamara}">
            <p>¿Está seguro/a que desea eliminar la Camara con el Numero <strong>${dcamara.ninventario}</strong> </p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="ninventario" value="${dcamara.ninventario}" />
                <input type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="dcamaras">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>