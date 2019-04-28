<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title> Profile page </title>

        <h2> Home Profile SingIn\SignOut Language </h2>
    </head>

    <body>
            <form method="post" enctype="multipart/form-data">
                        Upload your profile photo : <input type="submit" value="Upload photo" name="submit"/>
                        <br/> User NAME should be shown here.
                        <br/> User LEVEL should be shown here.
                        <br/> List of favorites categories should be shown here with check boxes.
                        <br/> Change password
                        <br/> Current password : <input type="password" name="currentpass"/>
                        <br/> New password : <input type="password" name="newpass"/>
                        <br/> Confirm new password : <input type="password" name="confnewpass"/>
                        <br/> <input type="submit" value="Change password" name="submit">
            </form>

    </body>

</html>
