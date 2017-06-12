<%@taglib prefix="list" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prace dyplomowe</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>

<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h2>Witaj w systemie do zarzadzania pracami dyplomowymi!</h2>
    <img src="${pageContext.request.contextPath}/img/pracedyplomowe.jpg" align="center">

    <p align="center"><font size="4">
    System zarządzania pracami dyplomowymi oferuje funkcjonalność wystarczającą do osiągnięcia<br/>
    podstawowego celu, jakim było uporządkowanie procesu zbierania ofert tematów prac<br/>
    dyplomowych od pracowników naukowo-dydaktycznych i wspomaganie „zapisywania” się<br/>
    studentów na poszczególne prace. System umożliwia współpracę promotorów prac oraz studentów.<br/>
    W wyniku działania systemu powstała strona internetowa zawierająca wykaz wszystkich tematów prac<br/>
    dyplomowych, oraz wykaz gotowych prac.</font></p>

</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>
