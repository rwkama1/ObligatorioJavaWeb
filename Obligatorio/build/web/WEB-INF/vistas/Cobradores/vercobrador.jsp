

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Cobradores">
    <jsp:body>
        <c:if test="${!empty cobrador}">
            <jsp:useBean id="cobrador" type="compartidos.beans.datatypes.DTCobrador" scope="request" />
            
            <h3><jsp:getProperty name="cobrador" property="nomEmp" /></h3>
            
            <ul>
                <li><strong>Cédula:</strong> <jsp:getProperty name="cobrador" property="cedulaEmp" /></li>
                <li><strong>Nombre:</strong> <jsp:getProperty name="cobrador" property="nomEmp" /></li>
                <li><strong>Sueldo:</strong> <jsp:getProperty name="cobrador" property="sueldoEmp" /></li>
                 <li><strong>FechaIngreso:</strong> <jsp:getProperty name="cobrador" property="fechaEmp" /></li>
                 <li><strong>Transporte:</strong> <jsp:getProperty name="cobrador" property="tranporteCobrador" /></li>
            </ul>
        </c:if>
        
        <p><a href="cobradores">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>