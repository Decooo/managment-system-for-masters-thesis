<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="list" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Rezerwacje</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Rezerwacje</h1>

    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <table border="1" align="center">
        <th>Temat</th>
        <th>Rezerwujacy</th>
        <th>Zaakceptuj</th>
        <th>Odrzuc</th>

        <list:forEach var="topic" items="${topics}" varStatus="loop">
            <tr>
                <td>${topic.temat}</td>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="user"
                                  items="${users}">${user.imie} ${user.nazwisko}</list:forEach></td>
                <td><input type="submit" value="Zaakceptuj" onclick="location.href='accept/${topic.idtematy}';"/></td>
                <td><input type="submit" value="Odrzuc" onclick="location.href='reject/${topic.idtematy}';"/></td>
            </tr>
        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>
</title>
</head>
<body>

</body>
</html>
