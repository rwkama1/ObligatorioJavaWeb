

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpAdministrativos titulo="Agregar Camara">
     <jsp:body>
  <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Numero de Inventario</td>
            <td>
               <input type="text" name="ninventario" value="${param.ninventario}"id="ninventario"/>
            </td>
        </tr>
       
        <tr>
            <td>
                <input type="submit" name="accion" value="Agregar Camara" />
            </td>
        </tr>
    </table>
</form>
    
<p><a href="dcamaras">Volver...</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpAdministrativos>      