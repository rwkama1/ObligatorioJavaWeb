<%@tag description="Página maestra del sitio." pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cobradores ${titulo}</title>
        
        <link rel="stylesheet" href="css/cobrador.css" />
    </head>
    <body>
        <div class="cabezal">
            <h1>Cobradores</h1>
            <h2>${titulo}</h2>
            <p><h3>Cobrador:${c.nomEmp}</h3>      </p>
        
        </div>
        
        <jsp:doBody />
        
        <script src="js/general.js"></script>
    </body>
</html>
