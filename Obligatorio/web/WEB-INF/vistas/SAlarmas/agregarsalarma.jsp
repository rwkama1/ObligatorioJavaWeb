
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpAdministrativos titulo="Registrar Servicio Alarma">
     <jsp:body>
       <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>¿Incluye Monitoreo?:</td>
            <td>
             <input type="checkbox" name="monitoreo" value="true" ${param.monitoreo=='true'?'checked':'false'}  />
            </td>
        </tr>
        <tr>
            <td>Fecha de Contratación:</td>
            <td>
                <input type="Date" name="fecha" value="${param.fecha}"  id="fecha" />
            </td>
        </tr>
         <tr>
            <td>Codigo de Anulación:</td>
            <td>
                <input type="text" name="codigo"  value="${param.codigo}" id="codigo" />
                
            </td>
        </tr>
          <td>Cedula Cliente:</td>
            <td>
                <input type="text" name="cedula"  value=" ${!empty cliente?cliente.cedulaCliente:param.cedula}" id="cedula" readonly />
           </td>
          <tr>
            <td>Propiedad del Cliente:</td>
            <td>
             <input type="text" name="idpropiedad"  value=" ${!empty propiedad ?propiedad.idpropiedad:param.idpropiedad}" id="idpropiedad" readonly  />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Agregar Servicio Alarma" />
            </td>
        </tr>
    </table>
</form>
    
            <p><a href="salarmas">Ir al Listado de Servicios de Alarmas....</a></p>
              <p><a href="clientes">Ir a los Clientes....</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpAdministrativos>      
