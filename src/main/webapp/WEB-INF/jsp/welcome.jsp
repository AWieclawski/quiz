<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>

    <meta charset="utf-8">

    <title>Quiz</title>
    <meta name="description" content="Quiz Webapp">
    <meta http-equiv="X-Ua-Compatible" content="IE=edge">

    <link rel="stylesheet" href="/css/style.css">

</head>

<body>

<div class="container">

    <div class="navbar">

        <% if (session.getAttribute("user_name") == null) { %>
        <jsp:include page="partials/logo.jsp"/>
        <% } else { %>
        <jsp:include page="partials/menu.jsp"/>
        <% } %>

    </div>

    <div class="content">

        <jsp:include page="partials/content_01.jsp"/>

    </div>

    <div class="footer">

        <jsp:include page="partials/footer.jsp"/>

    </div>

</div>

</body>

</html>