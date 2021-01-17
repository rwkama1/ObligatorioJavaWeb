


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpTecnicos titulo="Menú Principal">
    <jsp:body>
      <form>
         <li><a href="inicio?accion=logout">Salir</a></li>
    </form>
        <ul>
            <li><a href="rdcamaras">Registrar Camaras a un Servicio</a></li>
            <li><a href="rdalarmas">Registrar Alarmas a un Servicio</a></li>
             <li><a href="ldcamaras">Liberar Camaras de un Servicio</a></li>
             <li><a href="ldalarmas">Liberar Alarmas de un Servicio</a></li>
            
        </ul>
    </jsp:body>
</t:mpTecnicos>
