<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import = "java.io.*,java.util.Locale" %>
<%@ page import = "javax.servlet.*,javax.servlet.http.* "%>

<fmt:setLocale value="${localeType}" scope="session" />
<fmt:setBundle basename="crowdsource-bundle" />

    <div class="mastfoot">
        <div class="inner">
            <p><fmt:message key="All rights reserved"/> <a href="">Crowdsource </a>, <fmt:message key="by"/> <a href="">georgy.khomenko@gmail.com</a>.</p>
        </div>
    </div>