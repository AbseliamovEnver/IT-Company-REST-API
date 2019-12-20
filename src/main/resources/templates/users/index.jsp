<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head th:insert="fragments.jsp :: headerfiles"></head>
<body>
<header th:insert="fragments.jsp :: nav"></header>
<!-- Page content goes here -->
<div class="container">
    <p>This is User\Index. Only authenticated people can see this</p>
</div>
</body>
</html>