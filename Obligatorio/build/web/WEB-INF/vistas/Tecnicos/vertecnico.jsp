

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Tecnicos">
    <jsp:body>
        <c:if test="${!empty tecnico}">
            <jsp:useBean id="tecnico" type="compartidos.beans.datatypes.DTTecnico" scope="request" />
            
            <h3><jsp:getProperty name="tecnico" property="nomEmp" /></h3>
            
            <ul>
                <li><strong>Cédula:</strong> <jsp:getProperty name="tecnico" property="cedulaEmp" /></li>
                <li><strong>Nombre:</strong> <jsp:getProperty name="tecnico" property="nomEmp" /></li>
                <li><strong>Sueldo:</strong> <jsp:getProperty name="tecnico" property="sueldoEmp" /></li>
                 <li><strong>FechaIngreso:</strong> <jsp:getProperty name="tecnico" property="fechaEmp" /></li>
                 <li><strong>EspecialistaCamaras:</strong>    <c:choose>
                        <c:when test="${tecnico.espCamaras==true}">
                            <td><input type="checkbox" name="espcamaras" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="espcamaras"/></td>
                        </c:otherwise>
                    </c:choose>
                  <li><strong>EspecialistaAlarmas:</strong>       
                 <c:choose>
                        <c:when test="${tecnico.espAlarmas==true}">
                            <td><input type="checkbox" name="espalarmas" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="espalarmas"/></td>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </c:if>
        
        <p><a href="tecnicos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>