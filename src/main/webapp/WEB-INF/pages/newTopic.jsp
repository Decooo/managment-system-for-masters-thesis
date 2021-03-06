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

    <title>Nowa temat</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Nowy temat pracy dyplomowej</h1><br/>


    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <form:form commandName="topic" method="post" action="${pageContext.request.contextPath}save" role="form">

        Temat: <form:input path="temat"/> <form:errors path="temat"/><br/><br/>

        <form:button >Dodaj temat</form:button>
    </form:form>

</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>

