<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title> Admin page </title>

    </head>

    <body>
        <h2> Home Profile SingIn\SignOut Language </h2>
        <form method="post" >
            Enter max files size : <input type="text" name="filesize"/> Current value is : <c:out value="${filesize}"/>
            <br/> Enter allowed files extension : <input type="text" name="extension"/> Current value is : <c:out value="${extension}"/>
            <br/> <input type="submit" name="submit">
            <br/> <c:forEach var="elem" items="${blacklist}">
                    <c:out value="${elem.userLogin}"/><br/>
                    </c:forEach>
                    <c:forEach var="elem" begin="1" end="${pageCount}" step="1">
                    <a href="?page=${elem}"><c:out value="${elem}"/></a>
                    </c:forEach>
            <br/> Enter user login to add into blacklist : <input type="text" name="userloginadd"/>
            <br/> <input type="submit" value="Add" name="submit">
            <br/> Enter user login to remove from blacklist : <input type="text" name="userloginremove"/>
            <br/> <input type="submit" value="Remove" name="submit">

        </form>

    </body>
</html>