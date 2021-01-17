

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Ver Cliente y sus Propiedades">
    <jsp:body>
        <c:if test="${!empty cliente}">
            <jsp:useBean id="cliente" type="compartidos.beans.datatypes.DTCliente" scope="request" />
            
            <h3><jsp:getProperty name="cliente" property="nomCliente" /></h3>
            
            <ul>
                <li><strong>Cédula:</strong> <jsp:getProperty name="cliente" property="cedulaCliente" /></li>
                <li><strong>Nombre:</strong> <jsp:getProperty name="cliente" property="nomCliente" /></li>
                <li><strong>Direccion:</strong> <jsp:getProperty name="cliente" property="dirCliente" /></li>
                 <li><strong>Zona o Barrio:</strong> <jsp:getProperty name="cliente" property="zonabarrioCliente" /></li>
                <li><strong>Telefono</strong> <jsp:getProperty name="cliente" property="telCliente" /></li>
                <LI><strong>PROPIEDADES:</STRONG></li>
                <p><a href="clientes?accion=agregarpropiedad&cedula=${cliente.cedulaCliente}"><img src="imagenes/addp.png" alt="Agregar" title="Agregar Propiedad..." ></a></p>
           <table class="listado">
               
            <tr>
                <th>IDPropiedad</th><th>DIRECCION PROPIEDAD</th><th>TIPO DE PROPIEDAD</th><th></th>
            </tr>
            
            <c:forEach items="${cliente.propCliente}" var="propiedad">
                <tr>
                    <td class="texto-centro">${propiedad.idpropiedad}</td>
                    <td>${propiedad.dirProp}</td>
                    <td>${propiedad.tipoProp}</td>
                    <td>
                        <a href="clientes?accion=verpropiedad&idpropiedad=${propiedad.idpropiedad}"><img src="imagenes/searchp.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="clientes?accion=modificarpropiedad&idpropiedad=${propiedad.idpropiedad}&cedula=${cliente.cedulaCliente}"><img src="imagenes/editp.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                          <a href="scamaras?accion=agregarscamara&idpropiedad=${propiedad.idpropiedad}&cedula=${cliente.cedulaCliente}"><img src="imagenes/scamara.png" alt="RSC" title="Registrar esta Propiedad a un Servicio de Camara..." ></a>
                          <a href="salarmas?accion=agregarsalarma&idpropiedad=${propiedad.idpropiedad}&cedula=${cliente.cedulaCliente}"><img src="imagenes/salarma.png" alt="RSA" title="Registrar esta Propiedad a un Servicio de Alarma..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
            </ul>
        </c:if>
        
        <p><a href="clientes">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:mpAdministrativos>