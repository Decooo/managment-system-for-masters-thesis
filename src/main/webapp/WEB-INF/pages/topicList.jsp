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
    <title>Lista tematów</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Lista wsystkich tematów</h1>


    <table border="1" align="center">
        <th>Promotor</th>
        <th>Temat</th>
        <th>Status</th>

        <list:forEach var="topic" items="${topics}" varStatus="loop">
            <tr>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="user"
                                  items="${users}">${user.imie} ${user.nazwisko}</list:forEach></td>
                <td>${topic.temat}</td>
                <td>${topic.status}</td>
            </tr>
        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>
