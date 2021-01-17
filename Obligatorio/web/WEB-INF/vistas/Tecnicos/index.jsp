

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Tecnicos">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <form>
            <p><input type="text" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="tecnicos?accion=agregartecnico"><img src="imagenes/new.png" alt="Agregar" title="Agregar..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>CLAVE</th><th>FECHAINGRESO</th><th>¿ESPECIALIZADO CAMARAS?</th><th>¿ESPECIALIZADO ALARMAS?</th><th>SUELDO</th><th></th>
            </tr>
            
            <c:forEach items="${tecnicos}" var="tecnico">
                <tr>
                    <td class="texto-centro">${tecnico.cedulaEmp}</td>
                    <td>${tecnico.nomEmp}</td>
                    <td>${tecnico.claveEmp}</td>
                    <td>${tecnico.fechaEmp}</td>
                    <c:choose>
                        <c:when test="${tecnico.espCamaras==true}">
                            <td><input type="checkbox" name="espcamaras" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="espcamaras"/></td>
                        </c:otherwise>
                    </c:choose>
                  
                 <c:choose>
                        <c:when test="${tecnico.espAlarmas==true}">
                            <td><input type="checkbox" name="espalarmas" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="espalarmas"/></td>
                        </c:otherwise>
                    </c:choose>
              
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${tecnico.sueldoEmp}" />
                    </td>
                    <td>
                        <a href="tecnicos?accion=vertecnico&cedula=${tecnico.cedulaEmp}"><img src="imagenes/search.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="tecnicos?accion=modificartecnico&cedula=${tecnico.cedulaEmp}"><img src="imagenes/edit.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="tecnicos?accion=eliminartecnico&cedula=${tecnico.cedulaEmp}"><img src="imagenes/delete.png" alt="Eliminar" title="Eliminar..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio?accion=principaladmin">Volver...</a></p>
        
        <t:mensaje />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:mpAdministrativos>