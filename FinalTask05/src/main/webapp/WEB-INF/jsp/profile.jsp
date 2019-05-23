<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


        <form method="post" enctype="multipart/form-data" class="form-horizontal">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="${user.photoPath}" class="img-responsive" alt="">
                    </div>
                </div>


            <div class="form-group">
                <div class="row">
                     <div class="col-sm-offset-2 col-sm-10">
                         <h4><strong><a href="profilesettings.html">Profile settings</a></strong><h4>
                     </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <p><h5><em>name</em><h5><p>
                        <p><h3><strong><c:out value = "${user.name}"/></strong><h3><p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <p><h5><em>level</em><h5><p>
                        <p><h3><strong><c:out value = "${user.level}"/></strong><h3><p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <p><h4><strong>Favorite categories</strong><h4><p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <ul>
                            <c:forEach var="c" items="${categories}">
                                <c:if test="${favoriteIds.contains(c.id)}">
                                    <li><c:out value="${c.name}"/></li>
                                </c:if>
                            </c:forEach>
                        </ul>
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
