<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import = "java.io.*,java.util.Locale" %>
<%@ page import = "javax.servlet.*,javax.servlet.http.* "%>

<fmt:setLocale value="${localeType}" scope="session" />
<fmt:setBundle basename="flowers-bundle" />

<html>
<head>
    <title><fmt:message key="greenhouse"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1><fmt:message key="flowerstable"/></h1>
<p><fmt:message key="flowersingreenhouse"/>:</p>
<p><fmt:message key="chosenparser"/>: <strong><c:out value="${parserType}"/></strong></p>
<p><fmt:message key="passedvalidation"/>: <strong><fmt:message key="${isValid}"/></strong></p>
<table id="flowers">
<tr>
<th><fmt:message key="flowers.table.name"/></th>
<th><fmt:message key="flowers.table.soil"/></th>
<th><fmt:message key="flowers.table.origin"/></th>
<th><fmt:message key="flowers.table.visualparameters"/></th>
<th><fmt:message key="flowers.table.growingtips"/></th>
<th><fmt:message key="flowers.table.multiplying"/></th>
<th><fmt:message key="flowers.table.plantingdate"/></th>
</tr>
 <c:forEach var="elem" items="${lst}">
<tr>
 <td><fmt:message key="${ elem.name }" /></td>
 <td><fmt:message key="${ elem.soil }" /></td>
 <td><fmt:message key="${ elem.origin }" /></td>
 <td>
     <ul>
     <li><fmt:message key="flowers.table.size"/>: <c:out value="${ elem.visualParameters.size }" /> cm</li>
     <li><fmt:message key="flowers.table.leafcolor"/>: <fmt:message key="${ elem.visualParameters.leafColor }"/></li>
     <li><fmt:message key="flowers.table.stemcolor"/>: <fmt:message key="${ elem.visualParameters.stemColor }" /></li>
     </ul>
 </td>
 <td>
     <ul>
     <li><fmt:message key="flowers.table.temperature"/>: <c:out value="${ elem.growingTips.temperature }" /> *C</li>
     <li><fmt:message key="flowers.table.humidity"/>: <c:out value="${ elem.growingTips.humidity }" /> %</li>
     <li><fmt:message key="flowers.table.lightlevel"/>: <fmt:message key="${ elem.growingTips.lightLevel }" /></li>
     </ul>
 </td>
 <td><fmt:message key="${ elem.multiplying }" /></td>
 <td><c:out value="${ elem.plantingDateString }" /></td>
</tr>
 </c:forEach>
</table>
</body></html>