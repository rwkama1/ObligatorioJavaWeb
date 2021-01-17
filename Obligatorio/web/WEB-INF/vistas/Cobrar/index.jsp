<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpCobrador titulo="Cobrar Recibos">
   <jsp:body>
         <form>
          
            Buscar Zona o Barrio:<p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="BuscarRecibo" /></p>
        </form>
        <table class="listado">
            <tr>
                <th>IDRecibo</th><th>FechaFacturacion</th><th>ImporteTotal</th><th>Cedula Cobrador</th><th>Cedula Cliente</th><th>Zona o Barrio del Cliente</th>
            </tr>
            
            <c:forEach items="${reci}" var="r">
                <tr>
                    <td class="texto-centro">${r.idRecibo}</td>
                    <td>${r.fechaFacturacion}</td>
                     <td >
                        <fmt:formatNumber type="number" pattern="0.00" value="${r.importetotal}" />
                    </td>
                    <td>${r.cobrador.cedulaEmp}</td>
                    <td>${r.cliente.cedulaCliente}</td>
                      <td>${r.cliente.zonabarrioCliente}</td>
                    <td>
                   <a href="cobrar?accion=cobrarecibo&id=${r.idRecibo}"><img src="imagenes/cobrar.png" alt="Ver" title="Cobrar" ></a>&nbsp;&nbsp;
                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principalcobrador">Volver...</a></p>
        
        <t:mensaje />
       
    </jsp:body>
</t:mpCobrador>

