<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import = "java.io.*,java.util.Locale" %>
<%@ page import = "javax.servlet.*,javax.servlet.http.* "%>

<fmt:setLocale value="ru_RU" />
<c:out value="ru_RU" />
<fmt:setBundle basename="flowers-bundle" />

<%

   Locale locale = request.getLocale();
   String language = locale.getLanguage();
   String country = locale.getCountry();
%>
<%
            out.println("Language : " + language  + "<br />");
            out.println("Country  : " + country   + "<br />");
         %>

<html>
<head>
    <title>Core: forEach</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Flowers table</h1>
<p>See the Flowers table below</p>
<table id="flowers">
<tr>
<th><fmt:message key="flowers.table.name"/></th>
<th>Soil</th>
<th>Origin</th>
<th>Visual Parameters</th>
<th>Growing Tips</th>
<th>Multiplying</th>
<th>Planting Date</th>
</tr>
 <c:forEach var="elem" items="${lst}">
<tr>
 <td><c:out value="${ elem.name }" /></td>
 <td><c:out value="${ elem.soil }" /></td>
 <td><c:out value="${ elem.origin }" /></td>
 <td>
     <ul>
     <li>Size: <c:out value="${ elem.visualParameters.size }" /></li>
     <li>Leaf color: <c:out value="${ elem.visualParameters.leafColor }" /></li>
     <li>Steam color: <c:out value="${ elem.visualParameters.stemColor }" /></li>
     </ul>
 </td>
 <td>
     <ul>
     <li>Temperature: <c:out value="${ elem.growingTips.temperature }" /></li>
     <li>Humidity: <c:out value="${ elem.growingTips.humidity }" /></li>
     <li>Light level: <c:out value="${ elem.growingTips.lightLevel }" /></li>
     </ul>
 </td>
 <td><c:out value="${ elem.multiplying }" /></td>
 <td><c:out value="${ elem.plantingDateString }" /></td>
</tr>
 </c:forEach>
</table>
</body></html>