<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mpCobrador titulo="Menú Principal">
    <jsp:body>
      <form>
           <li><a href="inicio?accion=logout">Salir</a></li>
    </form>
        <ul>
            <li><a href="cobrar">Cobrar Recibos</a></li>
             <li><a href="cobrar?accion=listarc">Ver Recibos Cobrados</a></li>
        </ul>
    </jsp:body>
</t:mpCobrador>
