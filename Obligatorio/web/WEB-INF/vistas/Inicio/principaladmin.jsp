
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpAdministrativos titulo="Menú Principal">
    <jsp:body>
      <form>
          <li><a href="inicio?accion=logout">Salir</a></li>
    </form>
      
        <ul>
            <li><a href="admines">Mantenimiento Administradores</a></li>
            <li><a href="cobradores">Mantenimiento Cobradores</a></li>
             <li><a href="tecnicos">Mantenimiento Tecnicos</a></li>
             <li><a href="clientes">Mantenimiento Servicios, Clientes y sus Propiedades</a></li>
             <li><a href="dcamaras">Agregar y Eliminar Camaras</a></li>
              <li><a href="dalarmas">Agregar y Eliminar Alarmas</a></li>
               <li><a href="precios">Ver y Actualizar Precios</a></li>
              <li><a href="recibos">Generar Recibos</a></li>
            
        </ul>
    </jsp:body>
</t:mpAdministrativos>
