<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Registro de Alarmas a Servicio">
    <jsp:body>

       
        <h1>ALARMAS SIN SERVICIOS</h1>

        <table class="listado">
            <tr>
                <th>Numero de Inventario</th><th>Ubicación</th><TH></TH>
            </tr>
            
            
            <c:forEach items="${rdalarmas}" var="rdalarma">
                <tr>
                    <td class="texto-centro">${rdalarma.ninventario}</td>
                    <td>${rdalarma.ubicacion}</td>
               
                    <td>
                        <a href="rdalarmas?accion=rdalarma&ninventario=${rdalarma.ninventario}"><img src="imagenes/agregar.png" alt="Agregar" title="Registrar Alarma en Servicio..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaltecnico">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpTecnicos>
