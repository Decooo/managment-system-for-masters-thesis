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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Moje tematy</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Lista moich tematów</h1>

    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <table border="1" align="center">
        <th>Temat</th>
        <th>Status</th>
        <th>Usun</th>

        <list:forEach var="topic" items="${topics}" varStatus="loop">
            <tr>
                <td>${topic.temat}</td>
                <td>${topic.status}</td>
                <td><c:if test="${topic.status=='wolny'}">
                    <input type="submit" value="Usun" onclick="location.href='delete/${topic.idtematy}';"/>
                </c:if></td>
            </tr>
        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>
