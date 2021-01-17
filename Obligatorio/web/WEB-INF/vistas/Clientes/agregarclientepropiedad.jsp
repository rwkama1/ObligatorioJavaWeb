

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Agregar Cliente con Propiedad">
    <jsp:body>
        <form method="post" accept-charset="ISO-8859-1">
        <table>
        <tr>
            <td>Cédula del Cliente:</td>
            <td>  
           <input type="text" name="cedula" value="${param.cedula}" id="cedula" />
            </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td>
                <input type="text" name="nombre" value="${param.nombre}" id="nombre" />
            </td>
        </tr>
         <tr>
            <td>Direccion del Cliente:</td>
            <td>
                <input type="text" name="dircliente" value="${param.dircliente}" id="dircliente" />
            </td>
        </tr>
         <tr>
            <td>Zona o Barrio:</td>
            <td>
                <input type="text" name="zonabarrio" value="${param.zonabarrio}" id="zonabarrio" />
            </td>
        </tr>

        <tr>
            <td>Telefono:</td>
            <td>
                <input type="text" name="telefono" value="${param.telefono}" maxlength="15" onkeypress="return valida(event)" id="telefono" />
            </td>
        </tr>
         <tr>
            <td>PROPIEDAD:</td>
        </tr>
         <tr>
            <td>Tipo de Propiedad:</td>
            <td>
                <input type="text" name="tipopropiedad" value="${param.tipopropiedad}" id="tipopropiedad" />
            </td>
        </tr>
         <tr>
            <td>Direccion de la Propiedad:</td>
            <td>
                <input type="text" name="dirpropiedad" value="${param.dirpropiedad}" id="dirpropiedad" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Agregar" />
            </td>
        </tr>
    </table>
   </form>   
     <p><a href="clientes">Volver...</a></p>
        <t:mensaje />
        <script language="javascript" type="text/javascript">
function valida(e){
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8){
        return true;
    }
    patron =/[0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}
</script>
    </jsp:body>
     
</t:mpAdministrativos>