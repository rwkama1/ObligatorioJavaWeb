

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Precios">
    <jsp:body>

            <a href="precios?accion=actualizarprecios"><img src="imagenes/modificar.png" alt="Actualizar" title="Actualizar Precios..." ></a>
        <h1>Precios</h1>
     
     <table class="listado">
         <tr>
         <th>Precio Base Alarma</th> <th>Precio Base Camara</th> <th>Adicional por Alarma</th> <th>Adicional por Camara</th> <th>Tasa Monitoreo Alarma</th><th>Tasa Monitoreo Camara</th>
         </tr>
         <tr>
     <td>$${precios.pbaseAlarma}</td>
       <td>$${precios.pbaseCamara}</td>
       <td>$${precios.adicionalAlarma}</td>
       <td>$${precios.adicionalCamara}</td>
       <td>${precios.tmonitoreoAlarma}%</td>
       <td>${precios.tmonitoreoCamara}%</td
       </tr>

        </table>
        <p><a href="inicio?accion=principaladmin">Volver...</a></p>
        
        <t:mensaje />
        
      
    </jsp:body>
</t:mpAdministrativos>
