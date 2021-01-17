
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpAdministrativos titulo="Actualizar Precios">
     <jsp:body>
       <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Precio base alarmas:$</td>
            <td>
             <input type="text" name="pbalarma" value="${!empty pr?pr.pbaseAlarma:param.pbalarma}"  />
            </td>
        </tr>
      
         <tr>
            <td>Precio base camaras:$</td>
            <td>
               <input type="text" name="pbcamara" value="${!empty pr?pr.pbaseCamara:param.pbcamara}"  />
                
            </td>
        </tr>
          <td>Adicional por alarma:$</td>
            <td>
                <input type="text" name="aalarma" value="${!empty pr?pr.adicionalAlarma:param.aalarma}"  />
           </td>
          <tr>
            <td>Adicional por cámara:$</td>
            <td>
               <input type="text" name="acamara" value="${!empty pr?pr.adicionalCamara:param.acamara}"  />
            </td>
        </tr>
       
          <tr>
            <td>Tasa de monitoreo alarmas:</td>
            <td>
              <input type="text" name="tmalarma" value="${!empty pr?pr.tmonitoreoAlarma:param.tmalarma}"  />
            </td>
        </tr>
         <tr>
            <td>Tasa de monitoreo camaras:</td>
            <td>
              <input type="text" name="tmcamara" value="${!empty pr?pr.tmonitoreoCamara:param.tmcamara}"  />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Actualizar Precios" />
            </td>
        </tr>
    </table>
</form>
    
            <p><a href="precios">Volver...</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpAdministrativos>      
