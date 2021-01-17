<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpAdministrativos titulo="Generar Recibo">
     <jsp:body>
       <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Cliente:</td>
            <td>
             <input type="text" name="cedula" value="${cliente.cedulaCliente}" readonly />
            </td>
        </tr>
       
          <tr>
            <td>Fecha de Facturación</td>
            <td>
                
                <input type="Date" name="fecha" value="${param.fecha}"/>
            </td>
        </tr>
            <td>
                <input type="submit" name="accion" value="Generar Recibos" />
            </td>
        </tr>
    </table>
</form>
    
            <p><a href="recibos">Volver....</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpAdministrativos>      

