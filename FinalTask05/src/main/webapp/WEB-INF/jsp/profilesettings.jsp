<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title> Profile settings page </title>

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
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="${user.photoPath}" class="img-responsive" alt="">
                    </div>
                </div>
            </div>
            <p>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p><h4>Upload profile photo</h4></p>
                        <p><input type="file" name="photoToUpload" id="imageToUpload"></p>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p><h5><em>name</em><h5><p>
                        <p><h3><strong><c:out value = "${user.name}"/></strong><h3><p>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h4><strong><p class="text-center">Change name</p></strong><h4>
                    </div>

                    <div class="form-group">
                          <label for="newName3" class="col-sm-2 control-label">Enter your new name</label>
                          <div class="col-sm-10">
                            <input type="name" class="form-control" id="newName3" name="newUserName" placeholder="New name">
                          </div>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p><h4><strong>Change favorite category</strong><h4><p>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <c:forEach var="elem" items="${favorites}">
                              <img src="${elem.categoryId}" class="img-responsive" alt="">
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <h4><strong><p class="text-center">Change password</p></strong><h4>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="form-group">
                        <label for="currentPassword" class="col-sm-2 control-label">Enter your current password</label>
                    <div class="col-sm-10">
                    <input type="password" class="form-control" id="currentPassword" name="oldPassword" placeholder="Current password">
                </div>
            </div>
            <div class="form-group">
                <label for="newPassword3" class="col-sm-2 control-label">Enter new password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="newPassword3" name="newPassword" placeholder="New password">
                </div>
            </div>
            <div class="form-group">
                <label for="confirmNewPassword3" class="col-sm-2 control-label">Confirm new password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="confirmNewPassword3" name="confirmNewPassword" placeholder="Confirm new password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit changes</button>
                </div>
            </div>

        </form>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>