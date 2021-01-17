<%-- 
    Document   : index
    Created on : Jun 26, 2017, 2:50:42 PM
    Author     : Waldemar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Liberar Camara de Servicio">
    <jsp:body>

       
        <h1>CAMARAS CON SERVICIOS</h1>
        
        
        <table class="listado">
            <tr>
                <th>Numero de Inventario</th><th>Ubicación</th><th>Exterior o Interior</th><TH>Tecnico</TH><TH>Servicio Camara</TH><TH></TH>
            </tr>
            
            
            <c:forEach items="${ldcamaras}" var="ldcamara">
                <tr>
                    <td class="texto-centro">${ldcamara.ninventario}</td>
                    <td>${ldcamara.ubicacion}</td>
                     <td>${ldcamara.exteriorinterior}</td>
                    <td>${ldcamara.tecnico.cedulaEmp}</td>
                    <td>${ldcamara.servicioCamara.idServicio}</td>
                    <td>
                        <a href="ldcamaras?accion=verldcamaras&ninventario=${ldcamara.ninventario}"><img src="imagenes/buscar.png" alt="Ver" title="Ver Camara..." ></a>&nbsp;&nbsp;
                        <a href="ldcamaras?accion=ldcamara&ninventario=${ldcamara.ninventario}"><img src="imagenes/eliminar.png" alt="Liberar" title="Liberar Camara de Servicio..." ></a>&nbsp;&nbsp;

                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaltecnico">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpTecnicos>
