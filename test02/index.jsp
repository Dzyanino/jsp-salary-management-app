<%@ page language="java" errorPage="pages/error.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
  </head>

  <body>
    <%
      out.println("Hello world");
    %>
    <br>

    <form method="post" action="pages/test.jsp">
      <input type="text" name="nom" placeholder="nom" required></input>
      <input type="text" name="prenom" placeholder="prenom" required></input>
      <input type="submit" value="send"></input>
    </form>

    <%-- <%= 10/0 %> --%>
  </body>
</html>