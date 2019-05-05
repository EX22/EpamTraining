<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title> Home page </title>
    </head>

    <body>
        <h2> Home Profile SingIn\SignOut Language </h2>
        <h3> Select a category </h3>
        <h4> Some categories pictures should be shown here. </h4>

    <br/>
        <c:forEach var="elem" items="${categories}">
            <a href="category.html?id=${elem.id}">
            <img src="${elem.imagePath}"/></a>
            <c:out value="${elem.name}"/><br/>
        </c:forEach>

        <c:forEach var="elem" begin="1" end="${pageCount}" step="1">
            <a href="?page=${elem}"><c:out value="${elem}"/></a>
        </c:forEach>

    </body>

</html>