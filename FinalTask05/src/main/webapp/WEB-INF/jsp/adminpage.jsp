<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title> Admin page </title>

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

        <p>
        <form method="post" class="form-horizontal">
            <div class="container">
                <div class="row">
                    <div class="container">
                        <div class="row">
                            <h5><strong><p class="text-center">Currently allowed file size (Mb) is : <c:out value="${settings.fileSize}"/></p></strong><h5>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFileSize3" class="col-sm-2 control-label">Enter max allowed file size</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputFileSize3" name="fileSize" placeholder="File size">
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <h5><strong><p class="text-center">Currently allowed files extension is : <c:out value="${settings.fileExtensions}"/></p></strong><h5>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputExtension3" class="col-sm-2 control-label">Enter allowed files extension</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputExtension3" name="fileExtensions" placeholder="Files extension">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button name="formAction" type="submit" value="saveSettings" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
            <p>
            <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                     <h4><strong>Users blacklist</strong><h4>
                 </div>
            </div>
            <div class="container">
                <div class="row">
                      <c:forEach var="elem" items="${blacklist}">
                          <c:out value="${elem.login}"/></br>
                      </c:forEach>
                </div>
            </div>
            <p>
            <div class="container">
                 <div class="row">
                     <div class="form-group">
                         <label for="userToAdd3" class="col-sm-2 control-label">Choose user login to add into blacklist</label>
                         <div class="col-sm-10">
                             <p><select name="userToAdd" id="userToAdd3">
                                 <option value="" disabled>Add user into blacklist</option>
                                 <c:forEach var="user" items="${usersNotInBlacklist}">
                                     <option value="${user.id}"><c:out value="${user.login}"/></option>
                                 </c:forEach>
                             </select></p>
                         </div>
                     </div>
                     <div class="form-group">
                         <div class="col-sm-offset-2 col-sm-10">
                             <button name="formAction" type="submit" value="addUserToBlacklist" class="btn btn-default">Add</button>
                         </div>
                     </div>
                     <div class="form-group">
                          <label for="userRemoveFrom3" class="col-sm-2 control-label">Choose user login to remove from blacklist</label>
                          <div class="col-sm-10">
                              <p><select name="userRemoveFrom" id="userRemoveFrom3">
                                  <option value="" disabled>Remove user from blacklist</option>
                                  <c:forEach var="user" items="${blacklist}">
                                      <option value="${user.id}"><c:out value="${user.login}"/></option>
                                  </c:forEach>
                              </select></p>
                          </div>
                      </div>
                     <div class="form-group">
                          <div class="col-sm-offset-2 col-sm-10">
                              <button name="formAction" type="submit" value="removeUserFromBlacklist" class="btn btn-default">Remove</button>
                          </div>
                     </div>
                 </div>
            </div>
        </form>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>