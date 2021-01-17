<%-- 
    Document   : index
    Created on : Jun 24, 2017, 1:24:44 AM
    Author     : Waldemar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Camaras">
    <jsp:body>

       
        <h1>CAMARAS SIN SERVICIOS</h1>
        
         <p><a href="dcamaras?accion=agregardcamara"><img src="imagenes/agregar.png" alt="Agregar" title="Agregar Camara..." ></a></p>
        <table class="listado">
            <tr>
                <th>Numero de Inventario</th><th>Ubicación</th><th>Exterior o Interior</th><TH></TH>
            </tr>
            
            
            <c:forEach items="${dcamaras}" var="dcamara">
                <tr>
                    <td class="texto-centro">${dcamara.ninventario}</td>
                    <td>${dcamara.ubicacion}</td>
                     <td>${dcamara.exteriorinterior}</td>
                    <td>
                        <a href="dcamaras?accion=verdcamara&ninventario=${dcamara.ninventario}"><img src="imagenes/buscar.png" alt="Ver" title="Ver Camara..." ></a>&nbsp;&nbsp;
                        <a href="dcamaras?accion=eliminardcamara&ninventario=${dcamara.ninventario}"><img src="imagenes/eliminar.png" alt="Eliminar" title="Eliminar Camara..." ></a>&nbsp;&nbsp;

                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaladmin">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpAdministrativos>
