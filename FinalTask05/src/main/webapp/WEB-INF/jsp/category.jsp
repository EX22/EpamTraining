<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title> Category page </title>
    </head>

    <body>
        <h2> Home Profile SingIn\SignOut Language </h2>
        <h3> some pictures should be placed here </h3>

        <form method="post">
        <br/>
                <c:forEach var="elem" items="${images}">
                    <img src="${elem.path}"/>
                    <c:out value="${elem.categoryId}"/>
                    <input type="radio" name="answer-${elem.id}" value="yes"> Yes
                    <input type="radio" name="answer-${elem.id}" value="no"> No
                    <br/>
                </c:forEach>



                    <input type="submit" name="submit">
        </form>
    </body>
</html>