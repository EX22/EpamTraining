<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import = "java.io.*,java.util.Locale" %>
<%@ page import = "javax.servlet.*,javax.servlet.http.* "%>

<fmt:setLocale value="${localeType}" scope="session" />
<fmt:setBundle basename="crowdsource-bundle" />

<!doctype html>
<html lang="en">
  <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="css/pictures/crowdlogo.jpg">
        <title>  </title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/cover.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.min.js"></script>
          <script src="js/respond.min.js"></script>
        <![endif]-->

        <script src="js/validation.js"></script>

  </head>
  <body class="background-cover-image">
    <div class="site-wrapper">
        <div class="site-wrapper-inner">
            <div class="cover-container">

                <jsp:include page="headmenu.jsp"/>

                <div class="inner cover">
                    <form method="post" class="form-horizontal" onsubmit="return validateRegistrationForm()" id="registrationForm">
                          <div class="container">

                            <jsp:include page="errormessage.jsp"/>

                            <div class="row">
                              <div class="form-group">
                                  <label for="inputEmail3" class="col-sm-2 control-label"><fmt:message key="Enter your Email address"/></label>
                                  <div class="col-sm-8">
                                  <c:choose>
                                      <c:when test="${not empty regLogin}">
                                          <input type="email" class="form-control" id="inputEmail3" value="${regLogin}" name="login" placeholder=<fmt:message key="Email"/>>
                                      </c:when>
                                      <c:otherwise>
                                          <input type="email" class="form-control" id="inputEmail3" name="login" placeholder=<fmt:message key="Email"/>>
                                      </c:otherwise>
                                  </c:choose>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="password3" class="col-sm-2 control-label"><fmt:message key="Enter password"/></label>
                                  <div class="col-sm-8">
                                    <input type="password" class="form-control" id="password3" name="password" placeholder=<fmt:message key="Password"/>>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label for="confirmPassword3" class="col-sm-2 control-label"><fmt:message key="Confirm password"/></label>
                                  <div class="col-sm-8">
                                    <input type="password" class="form-control" id="confirmPassword3" name="confirmPassword" placeholder=<fmt:message key="Confirm password"/>>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default"><fmt:message key="Register"/></button>
                                  </div>
                                </div>
                            </div>
                          </div>
                    </form>
                </div>

                <jsp:include page="footer.jsp"/>

            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>