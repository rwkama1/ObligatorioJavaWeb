<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
          <link rel="stylesheet" href="css/logueo.css" />
    </head>
    <body>
         <div class="cabezal">
        <h1>BIOS Security</h1>
      
          </div>
        <form method="post" accept-charset="ISO-8859-1">
        <table>
        <h1>Iniciar Sesión</h1>
        <tr>
            <td>Cedula Empleado:</td>
            <td>
               <input type="text" name="cedula" value="${param.cedula}"id="cedula"/>
            </td>
        </tr>
        <tr>
            <td>Clave Empleado:</td>
            <td>
               <input type="password" name="clave" value="${param.clave}"id="clave" />
            </td>
        </tr>
        </table>
            <input type="submit" value="Iniciar Sesión"/>
        </form>
       <t:mensaje />
    </body>

