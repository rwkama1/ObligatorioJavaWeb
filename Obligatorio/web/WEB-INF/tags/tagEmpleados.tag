
<%@tag description="Editor de admines." pageEncoding="UTF-8"%>

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
            <td>Cédula:</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" name="cedula" value="${!empty admin ? admin.cedulaEmp : param.cedula}" readonly="readonly" id="cedula" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="cedula" value="${!empty admin ? admin.cedulaEmp : param.cedula}" id="cedula" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td>
                <input type="text" name="nombre" value="${!empty admin ? admin.nomEmp : param.nombre}" id="nombre" />
            </td>
        </tr>
         <tr>
            <td>Clave:</td>
            <td>
                <input type="text" name="clave" value="${!empty admin ? admin.claveEmp : param.clave}" id="clave" />
            </td>
        </tr>
         <tr>
            <td>Fecha de Ingreso:</td>
            <td>
                <input type="Date" name="fecha" value="${!empty admin ? admin.fechaEmp : param.fecha}" id="fecha" />
            </td>
        </tr>
        <c:set var="sueldo" scope="page" value="${!empty admin ? admin.sueldoEmp : param.sueldo}" />
        
        <c:catch var="ex">
            <fmt:formatNumber type="number" pattern="0.00" value="${!empty admin ? admin.sueldoEmp : param.sueldo}" var="sueldo" scope="page" />
        </c:catch>
        
        <tr>
            <td>Sueldo:</td>
            <td>
                <input type="text" name="sueldo" value="${sueldo}" />
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
