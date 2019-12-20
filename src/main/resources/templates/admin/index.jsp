<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:insert="fragments.jsp :: headerfiles"></head>
<body>
<header th:insert="fragments.jsp :: nav"></header>
<!-- Page content goes here -->
<div class="container">
    <p>This is Admin\Index. Only people with ADMIN can see this.</p>
</div>
</body>
</html>