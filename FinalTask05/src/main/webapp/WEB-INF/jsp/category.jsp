<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title><fmt:message key="Category"/></title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/cover.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
              <script src="js/html5shiv.min.js"></script>
              <script src="js/respond.min.js"></script>
        <![endif]-->
  </head>
  <body class="no-background-cover-image">
    <div class="site-wrapper">
        <div class="site-wrapper-inner">
            <div class="cover-container">

                <jsp:include page="headmenu.jsp"/>

                <div class="inner cover">
                    <form method="post">
                        <div class="row">
                             <div class="col-sm-offset-2 col-sm-10">
                                 <h4><strong><a href="home.html"><fmt:message key="Change category"/></a></strong><h4>
                             </div>
                        </div>
                        <div class="container">
                            <div class="row">
                                <h3><c:out value="${category.question}"/></h3>
                            </div>
                            <div class="row">
                                <c:forEach var="elem" items="${images}" varStatus="loop">
                                    <c:if test="${loop.index%3==0 && loop.index!=0}">
                                        </div>
                                    </c:if>
                                    <c:if test="${loop.index%3==0}">
                                        <div class="row">
                                    </c:if>
                                      <div class="col-md-4">
                                          <img src="${elem.path}" class="img-responsive"/>
                                          <div class="row">
                                              <input type="radio" name="answer-${elem.id}" value="yes"> <fmt:message key="Yes"/>
                                              <input type="radio" name="answer-${elem.id}" value="no"> <fmt:message key="No"/>
                                          </div>
                                      </div>
                                </c:forEach>
                            </div>
                        </div>
                            <div class="row">
                                <fmt:message key="Submit" var="buttonValue" />
                                <input type="submit" value="${buttonValue}"/>
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
