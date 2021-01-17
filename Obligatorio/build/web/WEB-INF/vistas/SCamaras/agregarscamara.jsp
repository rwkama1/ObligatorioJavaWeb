


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpAdministrativos titulo="Registrar Servicio Camara">
     <jsp:body>
       <form method="post" accept-charset="ISO-8859-1" >
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
            <td>¿Incluye TerminalGrabación?:</td>
            <td>
                 <input type="checkbox" name="terminal" value="true" ${param.terminal=='true'?'checked':'false'}  />
                
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
                <input type="submit" name="accion" value="Agregar Servicio Camara" onclick="return validar();"  />
            </td>
        </tr>
    </table>
</form>
    
            <p><a href="scamaras">Ir al Listado de Servicios de Camaras....</a></p>
            <p><a href="clientes">Ir a los Clientes....</a></p>
    <t:mensaje />
<script type="text/javascript"> 
function validar()
{
 
  if (document.getElementById('monitoreo').checked)
  {
    return 1;
 
  }
  
}
</script>
  </jsp:body>
  </t:mpAdministrativos>      
