<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title> My images page </title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

    <jsp:include page="headmenu.jsp"/>

      <form method="post" enctype="multipart/form-data" class="form-horizontal">
            Pagination tabs should be placed here.

            <div class="container">
                <div class="row">
                    <c:forEach var="im" items="${images}">
                        <div class="col-md-4">
                            <img src="${im.path}" class="img-responsive"/>
                            <p class="text-left">Category :
                            <p><select name="category-${im.id}">
                                <option value="" disabled>Chose the category</option>
                                <c:forEach var="cat" items="${categories}">
                                    <c:choose>
                                        <c:when test="${im.categoryId==cat.id}">
                                            <option selected value="${cat.id}"><c:out value="${cat.name}"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cat.id}"><c:out value="${cat.name}"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p><h4>Select image to upload:</h4></p>
                        <p><input type="file" name="imageToUpload" id="imageToUpload"></p>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p><input type="submit" value="Add image"></p>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p><input type="submit" value="Save changes"></p>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4 col-md-offset-4">
                    <button type="reset" class="btn" value="Reset">Cancel</button>
                </div>
            </div>
      </form>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>
