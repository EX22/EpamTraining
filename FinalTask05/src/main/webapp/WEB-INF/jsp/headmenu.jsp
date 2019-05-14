<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title> Head menu </title>

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

    <ul class="nav nav-tabs">

         <c:choose>
             <c:when test="${sessionScope.userId==1}">
                   <c:set var="context" value="${pageContext.request.contextPath}" />
                   <li role="presentation" id="home.html" ><a href="home.html">Home</a></li>
                   <li role="presentation" id="adminpage.html" ><a href="adminpage.html">Administration</a></li>
                   <li role="presentation" id="profile.html" ><a href="profile.html">Profile</a></li>
                   <li role="presentation" id="category.html" ><a href="category.html">Category</a></li>
                   <li role="presentation" id="myimages.html" ><a href="myimages.html">My images</a></li>
                   <li role="presentation" id="registration.html" ><a href="registration.html">Register</a></li>
                   <li role="presentation" id="login.html" ><a href="login.html">LogIn</a></li>
                   <li role="presentation" id="logout.html" ><a href="logout.html">LogOut</a></li>
                   <li role="presentation" id="" ><a href="#">Language</a></li>

                   <script type="text/javascript">
                     context = "${context}"
                     active_id = window.location.pathname.substring(context.length + 1)
                     document.getElementById(active_id).className = "active"
                   </script>
              </c:when>

             <c:when test="${not empty sessionScope.userId}">
                  <c:set var="context" value="${pageContext.request.contextPath}" />
                  <li role="presentation" id="home.html" ><a href="home.html">Home</a></li>
                  <li role="presentation" id="profile.html" ><a href="profile.html">Profile</a></li>
                  <li role="presentation" id="category.html" ><a href="category.html">Category</a></li>
                  <li role="presentation" id="myimages.html" ><a href="myimages.html">My images</a></li>
                  <li role="presentation" id="logout.html" ><a href="logout.html">LogOut</a></li>
                  <li role="presentation" id="" ><a href="#">Language</a></li>

                  <script type="text/javascript">
                    context = "${context}"
                    active_id = window.location.pathname.substring(context.length + 1)
                    console.log(context)
                    console.log(active_id)
                    document.getElementById(active_id).className = "active"
                  </script>
             </c:when>
             <c:otherwise>
                  <c:set var="context" value="${pageContext.request.contextPath}" />

                  <li role="presentation" id="home.html" ><a href="home.html">Home</a></li>
                  <li role="presentation" id="registration.html" ><a href="registration.html">Register</a></li>
                  <li role="presentation" id="login.html" ><a href="login.html">LogIn</a></li>
                  <li role="presentation" id="home.html" ><a href="#">Language</a></li>

                   <script type="text/javascript">
                   	context = "${context}"
                   	active_id = window.location.pathname.substring(context.length + 1)
                   	console.log(context)
                    console.log(active_id)
                   	document.getElementById(active_id).className = "active"
                   </script>
             </c:otherwise>

         </c:choose>
    </ul>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
  </body>
</html>