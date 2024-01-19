<%@ page errorPage="./error.jsp" import="java.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
  </head>

  <body>
    <%
      out.println("Yooo");
      Integer nombre = 12;
      String message = "Ceci est un message";

      out.println("<br>" + message + " <span>accompagné</span> d'un nombre " + nombre);
    %>
  </body>
</html>