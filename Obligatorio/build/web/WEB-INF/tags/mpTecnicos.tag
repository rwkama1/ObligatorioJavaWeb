<%@tag description="Página maestra del sitio." pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tecnicos ${titulo}</title>
        
        <link rel="stylesheet" href="css/tecnico.css" />
    </head>
    <body>
        <div class="cabezal">
            <h1>Tecnicos</h1>
            <h2>${titulo}</h2>
           <h3>Tecnico:${tecnico.nomEmp}</h3>
        </div>
        
        <jsp:doBody />
        
        <script src="js/general.js"></script>
    </body>
</html>
