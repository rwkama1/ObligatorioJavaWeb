

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Administrador">
    <jsp:body>
        <c:if test="${!empty admin}">
            <jsp:useBean id="admin" type="compartidos.beans.datatypes.DTAdministrador" scope="request" />
            
            <h3><jsp:getProperty name="admin" property="nomEmp" /></h3>
            
            <ul>
                <li><strong>Cédula:</strong> <jsp:getProperty name="admin" property="cedulaEmp" /></li>
                <li><strong>Nombre:</strong> <jsp:getProperty name="admin" property="nomEmp" /></li>
                <li><strong>Sueldo:</strong> <jsp:getProperty name="admin" property="sueldoEmp" /></li>
                 <li><strong>FechaIngreso:</strong> <jsp:getProperty name="admin" property="fechaEmp" /></li>
              
            </ul>
        </c:if>
        
        <p><a href="admines">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>