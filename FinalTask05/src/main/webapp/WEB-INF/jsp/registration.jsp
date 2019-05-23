<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/cover.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
  </head>
  <body>

    <jsp:include page="headmenu.jsp"/>

        <form method="post" class="form-horizontal">
              <div class="container">
                <div class="row">
                  <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">Enter your Email address</label>
                      <div class="col-sm-10">
                      <c:choose>
                          <c:when test="${not empty sessionScope.regLogin}">
                              <input type="email" class="form-control" id="inputEmail3" value="${sessionScope.regLogin}" name="login" placeholder="Email">
                          </c:when>
                          <c:otherwise>
                              <input type="email" class="form-control" id="inputEmail3" name="login" placeholder="Email">
                          </c:otherwise>
                      </c:choose>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="password3" class="col-sm-2 control-label">Enter password</label>
                      <div class="col-sm-10">
                        <input type="password" class="form-control" id="password3" name="password" placeholder="Password">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="confirmPassword3" class="col-sm-2 control-label">Confirm password</label>
                      <div class="col-sm-10">
                        <input type="password" class="form-control" id="confirmPassword3" name="confirmPassword" placeholder="Confirm password">
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Register</button>
                      </div>
                    </div>
                </div>
              </div>
        </form>

        <jsp:include page="footer.jsp"/>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>