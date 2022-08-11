<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seeker Change Password</title>
<script src="RegisterForm.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div id="stickyBar">
        <ul id="navbar">
            <li><a href="EmployerHomePage.jsp">Home</a></li>
            <li><a href="PostAJob.html">Post a Job</a></li>
            <li><a href="EmployerJobList">Applicants</a></li>
            <li><a href="EmployerJobPosting">Job Posting</a></li>
         </ul>
    </div>
    <div id="NotepaperDiv">
	    <form action="SeekerChangePassword">
	    <label for="oldPassword">old password</label>
	    <input id="oldPassword" name="oldPassword" type="password" required>
	    <%if(session.getAttribute("mismatchPassword") !=null){ %>
	    <span>password does not match</span>
	    <%} %>
	    <br>
	    
	    <label for="password">new password</label>
	    <input id="password" name="password" type="password" required><br>
	    
	    <label for="conPassword">confirm password</label>
	    <input id="conPassword" name="conPassword" type="password" required><span id="notConfirmed"></span> <br>
	    
	    <button id="submitButton" type="submit">change password</button>
	</form>
    </div>

</body>
</html>