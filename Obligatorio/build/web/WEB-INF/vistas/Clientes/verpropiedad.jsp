
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Propiedad">
    <jsp:body>
        <c:if test="${!empty propiedad}">
            <jsp:useBean id="propiedad" type="compartidos.beans.datatypes.DTPropiedad" scope="request" />
            
            <h3><jsp:getProperty name="propiedad" property="dirProp" /></h3>
            
            <ul>
                <li><strong>ID Propiedad:</strong> <jsp:getProperty name="propiedad" property="idpropiedad" /></li>
                <li><strong>Direccion de Propiedad:</strong> <jsp:getProperty name="propiedad" property="dirProp" /></li>
                <li><strong>Tipo Propiedad:</strong> <jsp:getProperty name="propiedad" property="tipoProp" /></li>             
            </ul>
        </c:if>
        
        <p><a href="clientes">Volver a los Clientes...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>