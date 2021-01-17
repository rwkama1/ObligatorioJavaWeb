

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpCobrador titulo="Cobrar Recibo">
     <jsp:body>
  <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>ID del Recibo</td>
            <td>
               <input type="text" name="id" value="${!empty recibo ? recibo.idRecibo : param.id}"readonly/>
            </td>
        </tr>
          <tr>
            <td>Cedula del Cobrador:</td>
            <td>
              <input type="text" name="cedula" value="${!empty c ? c.cedulaEmp : param.cedula}"readonly/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="accion" value="Cobrar Recibo" />
            </td>
        </tr>
    </table>
</form>
    
<p><a href="cobrar">Volver...</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpCobrador>    