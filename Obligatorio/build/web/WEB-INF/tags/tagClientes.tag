<%-- 
    Document   : tagClientes
    Created on : Jun 18, 2017, 9:14:43 PM
    Author     : Waldemar
--%>


<%@tag description="Editor de Clientes." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="idFoco" required="true"%>
<%@attribute name="textoBoton" required="true"%>

<%-- any content can be specified here e.g.: --%>
<fmt:setLocale value="en-US" />

<form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Cédula del Cliente:</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" name="cedula" value="${!empty cliente ? cliente.cedulaCliente : param.cedula}" readonly="readonly" id="cedula" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="cedula" value="${!empty cliente ? cliente.cedulaCliente : param.cedula}" id="cedula" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td>
                <input type="text" name="nombre" value="${!empty cliente ? cliente.nomCliente : param.nombre}" id="nombre" />
            </td>
        </tr>
         <tr>
            <td>Direccion del Cliente:</td>
            <td>
                <input type="text" name="dircliente" value="${!empty cliente ? cliente.dirCliente : param.dircliente}" id="dircliente" />
            </td>
        </tr>
         <tr>
            <td>Zona o Barrio:</td>
            <td>
                <input type="text" name="zonabarrio" value="${!empty cliente ? cliente.zonabarrioCliente : param.zonabarrio}" id="zonabarrio" />
            </td>
        </tr>

        <tr>
            <td>Telefono:</td>
            <td>
                <input type="text" name="telefono" value="${!empty cliente ? cliente.telCliente : param.telefono}" maxlength="15" onkeypress="return valida(event)" id="telefono" />
            </td>
        </tr>
        
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="${textoBoton}" />
            </td>
        </tr>
    </table>
</form>

<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>
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