

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Servicios Camaras">
    <jsp:body>
          <h1>Servicios de Camara</h1>
        <table class="listado">
            <tr>
                <th>IDServicio</th><th>FECHA CONTRATACIÓN</th><th>MONITOREO</th><th>TerminalGrabación</th><th>PROPIEDAD</th><th>CEDULACLIENTE</th><th></th>
            </tr>
            
            <c:forEach items="${scamaras}" var="scamara">
                <tr>
                    <td class="texto-centro">${scamara.idServicio}</td>
                    <td>${scamara.fechaServicio}</td>
                     <c:choose>
                        <c:when test="${scamara.moniteroServicio==true}">
                            <td><input type="checkbox" name="moniteroservicio" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="moniteroservicio"/></td>
                        </c:otherwise>
                           </c:choose> 
                    <c:choose>
                       <c:when test="${scamara.terminalgrabacion==true}">
                            <td><input type="checkbox" name="terminalgrabacion" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="terminalgrabacion"/> </td>
                          
                        </c:otherwise>
                    </c:choose>  </td>
                    <td>${scamara.propServicio.idpropiedad}</td>
                     <td>${scamara.clienteServicio.cedulaCliente}</td>
                    <td>
                        <a href="scamaras?accion=verscamara&idscamara=${scamara.idServicio}"><img src="imagenes/buscar.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="scamaras?accion=desactivarscamara&idscamara=${scamara.idServicio}"><img src="imagenes/eliminar.png" alt="Desactivar" title="Desactivar..." ></a>&nbsp;&nbsp;

                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="clientes">Volver a los Clientes...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpAdministrativos>
