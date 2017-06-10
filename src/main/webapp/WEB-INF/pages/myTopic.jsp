<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 06.04.2017
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Moja praca</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Moja praca</h1><br/>

    <c:choose>
        <c:when test="${not empty msg}">
            Nie masz wybranego tematu pracy dyplomowej
            <br/>
        </c:when>
        <c:otherwise>
            Temat: ${topic.temat}<br/>
            Promotor: ${users.imie} ${users.nazwisko}<br/>
            Status: ${dissertation.status}<br/>
            <input type="submit" value="Pobierz prace" onclick="location.href='download/${topic.idtematy}';"/>

            <br/><br/>
            <form:form commandName="dissertation" action="add" method="POST" enctype="multipart/form-data">

                Praca: <input type="file" name="file"/><br/>

                <form:button>Edytuj prace</form:button>
            </form:form>

        </c:otherwise>
    </c:choose>


</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>

