

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mpTecnicos titulo="Registrar Camara en Servicio">
     <jsp:body>
       <form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Numero de Inventario:</td>
            <td>
               <input type="text" name="ninventario" value="${!empty rdcamara?rdcamara.ninventario:param.ninventario}" readonly/>
            </td>
        </tr>
        <tr>
            <td>Ubicación</td>
            <td>
                <input type="text" name="ubicacion" value="${param.ubicacion}"/>
            </td>
        </tr>
         <tr>
            <td>Cedulas Tecnicos Especializado en Camaras:</td>
            <td>
               <select name="cedula">
                <c:forEach items="${tecnicos}" var="tecnico">
                <option  value="${!empty tecnico?tecnico.cedulaEmp:param.cedula}">${tecnico.cedulaEmp}</option>
                </c:forEach>
                </select>
                
            </td>
        </tr>
          <td>ID de Servicio de Camaras:</td>
            <td>
                 <select name="id">
                 <c:forEach items="${scamaras}" var="scamara">
                <option value="${!empty scamara?scamara.idServicio:param.id}" selected>${scamara.idServicio}</option>
                </c:forEach>
                </select>
                
           </td>
          <tr>
            <td>¿Exterior o Interior?:</td>
            <td>
       <input type="radio" name="exi" value="Exterior"${param.exi=='Exterior'?'checked':''}>  Exterior
        <input type="radio" name="exi" value="Interior"${param.exi=='Interior'?'checked':''}>  Interior
         
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="Registrar camara en Servicio" />
            </td>
        </tr>
    </table>
</form>
    
            <p><a href="rdcamaras">Volver...</a></p>
    <t:mensaje />

  </jsp:body>
  </t:mpTecnicos>    