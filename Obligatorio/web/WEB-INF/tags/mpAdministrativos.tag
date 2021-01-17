<%@tag description="Página maestra del sitio." pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrativo ${titulo}</title>
        
        <link rel="stylesheet" href="css/general.css" />
    </head>
    <body>
        <div class="cabezal">
            <h1>Administrativos</h1>
            <h2>${titulo}</h2>
            <h3>Administrador:${a.nomEmp}</h3>
         
        </div>
        
        <jsp:doBody />
        
        <script src="js/general.js"></script>
    </body>
</html>
