

<%@tag description="Editor de Propiedad." pageEncoding="UTF-8"%>

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
            <td>ID Propiedad:</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" name="idpropiedad" value="${!empty propiedad ? propiedad.idpropiedad : param.idpropiedad}" readonly="readonly" id="idpropiedad" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="idpropiedad" value="${!empty propiedad ? propiedad.idpropiedad : param.idpropiedad}" id="idpropiedad" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Tipo de Propiedad:</td>
            <td>
                <input type="text" name="tipopropiedad" value="${!empty propiedad ? propiedad.tipoProp : param.tipopropiedad}" id="tipopropiedad" />
            </td>
        </tr>
         <tr>
            <td>Direccion de la Propiedad:</td>
            <td>
                <input type="text" name="dirpropiedad" value="${!empty propiedad ? propiedad.dirProp : param.dirpropiedad}" id="dirpropiedad" />
            </td>
        </tr>

        <tr>
            <td>Cedula del Cliente:</td>
            <td>
                 <c:choose>
                    <c:when test="${deshabilitarClave}">
                         <input type="text" name="cedula" value="${!empty cliente ? cliente.cedulaCliente : param.cedula}" readonly="readonly" id="cedula" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="cedula" value="${!empty cliente ? cliente.cedulaCliente : param.cedula}" readonly id="cedula" />
                    </c:otherwise>
                </c:choose>
               
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
