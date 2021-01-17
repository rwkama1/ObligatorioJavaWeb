
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Alarmas">
    <jsp:body>

       
        <h1>ALARMAS SIN SERVICIOS</h1>
        
         <p><a href="dalarmas?accion=agregardalarma"><img src="imagenes/agregar.png" alt="Agregar" title="Agregar Alarma..." ></a></p>
        <table class="listado">
            <tr>
                <th>Numero de Inventario</th><th>Ubicación</th><TH></TH>
            </tr>
            
            
            <c:forEach items="${dalarmas}" var="dalarma">
                <tr>
                    <td class="texto-centro">${dalarma.ninventario}</td>
                    <td>${dalarma.ubicacion}</td>
                  
                    <td>
                        <a href="dalarmas?accion=verdalarma&ninventario=${dalarma.ninventario}"><img src="imagenes/buscar.png" alt="Ver" title="Ver Alarma..." ></a>&nbsp;&nbsp;
                        <a href="dalarmas?accion=eliminardalarma&ninventario=${dalarma.ninventario}"><img src="imagenes/eliminar.png" alt="Eliminar" title="Eliminar Alarma..." ></a>&nbsp;&nbsp;

                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaladmin">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpAdministrativos>
