<%-- 
    Document   : index
    Created on : Jun 26, 2017, 2:36:04 PM
    Author     : Waldemar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Liberar Alarma de Servicio">
    <jsp:body>

       
        <h1>ALARMAS CON SERVICIOS</h1>
        
        
        <table class="listado">
            <tr>
                <th>Numero de Inventario</th><th>Ubicación</th><TH>Tecnico</TH><TH>Servicio Alarma</TH><TH></TH>
            </tr>
            
            
            <c:forEach items="${ldalarmas}" var="ldalarma">
                <tr>
                    <td class="texto-centro">${ldalarma.ninventario}</td>
                    <td>${ldalarma.ubicacion}</td>
                    <td>${ldalarma.tecnico.cedulaEmp}</td>
                    <td>${ldalarma.servicioAlarma.idServicio}</td>
                    <td>
                        <a href="ldalarmas?accion=verldalarma&ninventario=${ldalarma.ninventario}"><img src="imagenes/buscar.png" alt="Ver" title="Ver Alarma..." ></a>&nbsp;&nbsp;
                        <a href="ldalarmas?accion=ldalarma&ninventario=${ldalarma.ninventario}"><img src="imagenes/eliminar.png" alt="Liberar" title="Liberar Alarma de Servicio..." ></a>&nbsp;&nbsp;

                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaltecnico">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpTecnicos>

