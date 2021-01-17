<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Registro de Camaras a Servicio">
    <jsp:body>

       
        <h1>CAMARAS SIN SERVICIOS</h1>

        <table class="listado">
            <tr>
                <th>Numero de Inventario</th><th>Ubicación</th><th>Exterior o Interior</th><TH></TH>
            </tr>
            
            
            <c:forEach items="${rdcamaras}" var="rdcamara">
                <tr>
                    <td class="texto-centro">${rdcamara.ninventario}</td>
                    <td>${rdcamara.ubicacion}</td>
                     <td>${rdcamara.exteriorinterior}</td>
                    <td>
                        <a href="rdcamaras?accion=rdcamara&ninventario=${rdcamara.ninventario}"><img src="imagenes/agregar.png" alt="Agregar" title="Registrar Camara en Servicio..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaltecnico">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpTecnicos>
