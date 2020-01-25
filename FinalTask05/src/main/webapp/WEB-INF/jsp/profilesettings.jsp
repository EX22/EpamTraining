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
        <title><fmt:message key="Profile settings"/></title>

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
                    <form method="post" enctype="multipart/form-data" class="form-horizontal"
                     onsubmit="return validateProfileSettingsForm()" id="profileSettingsForm">
                        <div class="container">

                            <jsp:include page="errormessage.jsp"/>

                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${user.photoPath}" class="img-responsive" alt="">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <p><h4><fmt:message key="Upload profile photo"/></h4></p>
                                    <p><input type="file" name="photoToUpload" id="imageToUpload"></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <p><h5><em><fmt:message key="name"/></em><h5><p>
                                    <p><h3><strong><c:out value = "${user.name}"/></strong><h3><p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <h4><strong><p class="text-center"><fmt:message key="Change name"/></p></strong><h4>
                                </div>

                                <div class="form-group">
                                      <label for="newName3" class="col-sm-2 control-label"><fmt:message key="Enter your new name"/></label>
                                      <div class="col-sm-9">
                                        <input type="name" class="form-control" id="newName3" name="newUserName" placeholder=<fmt:message key="New name"/>>
                                      </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <p><h4><strong><fmt:message key="Change favorite category"/></strong><h4><p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="align-left-side">
                                        <c:forEach var="c" items="${categories}">
                                              <div class="checkbox">
                                                    <label>
                                                        <c:choose>
                                                            <c:when test="${favoriteIds.contains(c.id)}">
                                                                <input type="checkbox" name="category-${c.id}" value="" checked>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="checkbox" name="category-${c.id}" value="">
                                                            </c:otherwise>
                                                        </c:choose>
                                                      <c:out value="${c.name}"/>
                                                    </label>
                                              </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-10">
                                    <h4><strong><p class="text-center"><fmt:message key="Change password"/></p></strong><h4>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group">
                                    <label for="currentPassword3" class="col-sm-2 control-label"><fmt:message key="Enter your current password"/></label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" id="currentPassword3" name="currentPassword" placeholder=<fmt:message key="Current password"/>>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label for="newPassword3" class="col-sm-2 control-label"><fmt:message key="Enter new password"/></label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" id="newPassword3" name="newPassword" placeholder=<fmt:message key="New password"/>>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label for="confirmNewPassword3" class="col-sm-2 control-label"><fmt:message key="Confirm new password"/></label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" id="confirmNewPassword3" name="confirmNewPassword" placeholder=<fmt:message key="Confirm new password"/>>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-default"><fmt:message key="Save changes"/></button>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-4 col-md-offset-4">
                                        <button type="reset" class="btn" value="Reset"><fmt:message key="Reset"/></button>
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
