<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 23.05.2017
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="list" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Lista prac</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Lista wsystkich prac</h1>


    <table border="1" align="center">
        <th>Autor</th>
        <th>Temat</th>
        <th>Status</th>
        <th>Pobierz</th>

        <list:forEach var="topic" items="${topics}" varStatus="loop">
            <tr>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="user"
                                  items="${users}">${user.imie} ${user.nazwisko}</list:forEach></td>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="top"
                                  items="${tops}">${top.temat}</list:forEach></td>
                <td>${topic.status}</td>
                <td><input type="submit" value="Pokaż prace" onclick="location.href='download/${topic.idtematy}';"/></td>
            </tr>
        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>
