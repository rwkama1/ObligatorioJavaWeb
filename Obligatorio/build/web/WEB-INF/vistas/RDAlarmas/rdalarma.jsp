
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpTecnicos titulo="Registrar Alarma en Servicio">
     <jsp:body>
       <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Numero de Inventario:</td>
            <td>
               <input type="text" name="ninventario" value="${!empty rdalarma?rdalarma.ninventario:param.ninventario}" readonly/>
            </td>
        </tr>
        <tr>
            <td>Ubicación</td>
            <td>
                <input type="text" name="ubicacion" value="${param.ubicacion}"/>
            </td>
        </tr>
         <tr>
            <td>Cedulas Tecnicos Especializado en Alarmas</td>
            <td>
               <select name="cedula">
                <c:forEach items="${tecnicos}" var="tecnico">
                <option  value="${!empty tecnico?tecnico.cedulaEmp:param.cedula}">${tecnico.cedulaEmp}</option>
                </c:forEach>
                </select>
                
            </td>
        </tr>
          <td>ID de Servicio de Alarmas</td>
            <td>
                 <select name="id">
                 <c:forEach items="${salarmas}" var="salarma">
                <option value="${!empty salarma?salarma.idServicio:param.id}" selected>${salarma.idServicio}</option>
                </c:forEach>
                </select>
                
           </td>

        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Registrar Alarma en Servicio" />
            </td>
        </tr>
    </table>
</form>
    
            <p><a href="rdalarmas">Volver...</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpTecnicos>   