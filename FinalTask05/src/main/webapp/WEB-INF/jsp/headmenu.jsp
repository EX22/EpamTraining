<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import = "java.io.*,java.util.Locale" %>
<%@ page import = "javax.servlet.*,javax.servlet.http.* "%>

<fmt:setLocale value="${localeType}" scope="session" />
<fmt:setBundle basename="crowdsource-bundle" />

  <div class="masthead clearfix">
    <div class="inner">
      <h3 class="masthead-brand">Crowdsource</h3>
      <nav>
        <ul class="nav masthead-nav">
             <c:choose>
                 <c:when test="${sessionScope.userId==1}">
                       <c:set var="context" value="${pageContext.request.contextPath}" />
                       <li role="presentation" id="home.html" ><a href="home.html"><fmt:message key="Home"/></a></li>
                       <li role="presentation" id="adminpage.html" ><a href="adminpage.html"><fmt:message key="Administration"/></a></li>
                       <li role="presentation" id="profile.html" ><a href="profile.html"><fmt:message key="Profile"/></a></li>
                       <li role="presentation" id="category.html" ><a href="category.html"><fmt:message key="Category"/></a></li>
                       <li role="presentation" id="myimages.html" ><a href="myimages.html"><fmt:message key="My images"/></a></li>
                       <li role="presentation" id="registration.html" ><a href="registration.html"><fmt:message key="Register"/></a></li>
                       <li role="presentation" id="login.html" ><a href="login.html"><fmt:message key="LogIn"/></a></li>
                       <li role="presentation" id="logout.html" ><a href="logout.html"><fmt:message key="LogOut"/></a></li>
                       <li class="dropdown">
                           <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="Language"/> <span class="caret"></span></a>
                           <ul class="dropdown-menu">
                             <li><a href="?locale=en_US">English</a></li>
                             <li><a href="?locale=be_BY">Belarusian</a></li>
                             <li><a href="?locale=de_DE">German</a></li>
                           </ul>
                       </li>

                       <script type="text/javascript">
                         context = "${context}"
                         active_id = window.location.pathname.substring(context.length + 1)
                         document.getElementById(active_id).className = "active"
                       </script>
                  </c:when>

                 <c:when test="${not empty sessionScope.userId}">
                      <c:set var="context" value="${pageContext.request.contextPath}" />
                      <li role="presentation" id="home.html" ><a href="home.html"><fmt:message key="Home"/></a></li>
                      <li role="presentation" id="profile.html" ><a href="profile.html"><fmt:message key="Profile"/></a></li>
                      <li role="presentation" id="category.html" ><a href="category.html"><fmt:message key="Category"/></a></li>
                      <li role="presentation" id="myimages.html" ><a href="myimages.html"><fmt:message key="My images"/></a></li>
                      <li role="presentation" id="logout.html" ><a href="logout.html"><fmt:message key="LogOut"/></a></li>
                      <li class="dropdown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="Language"/> <span class="caret"></span></a>
                          <ul class="dropdown-menu">
                            <li><a href="?locale=en_US">English</a></li>
                            <li><a href="?locale=be_BY">Belarusian</a></li>
                            <li><a href="?locale=de_DE">German</a></li>
                          </ul>
                      </li>

                      <script type="text/javascript">
                        context = "${context}"
                        active_id = window.location.pathname.substring(context.length + 1)
                        document.getElementById(active_id).className = "active"
                      </script>
                 </c:when>
                 <c:otherwise>
                      <c:set var="context" value="${pageContext.request.contextPath}" />

                      <li role="presentation" id="home.html" ><a href="home.html"><fmt:message key="Home"/></a></li>
                      <li role="presentation" id="registration.html" ><a href="registration.html"><fmt:message key="Register"/></a></li>
                      <li role="presentation" id="login.html" ><a href="login.html"><fmt:message key="LogIn"/></a></li>
                      <li class="dropdown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="Language"/> <span class="caret"></span></a>
                          <ul class="dropdown-menu">
                            <li><a href="?locale=en_US">English</a></li>
                            <li><a href="?locale=be_BY">Belarusian</a></li>
                            <li><a href="?locale=de_DE">German</a></li>
                          </ul>
                      </li>

                       <script type="text/javascript">
                        context = "${context}"
                        active_id = window.location.pathname.substring(context.length + 1)
                        document.getElementById(active_id).className = "active"
                       </script>
                 </c:otherwise>
             </c:choose>
        </ul>
      </nav>
    </div>
  </div>
