<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
</head>
<body>

<div id="NotepaperDiv">
<h2 class="WelcomeSign">Welcome to the</h1>
<h1 class="WelcomeSign">Job Board</h1>
<div id="RegisterChoice">
Register as an<br>
<button id="RegisterAsSeeker" class="RegisterButtons" onclick="location.href='RegisterAsEmployer.jsp'" type="button"> Employer</button>
<button class="RegisterButtons" onclick="location.href='RegisterAsSeeker.jsp'" type="button"> Job Seeker</button>
<br>
Or log in as a<br>
<form action="/JobPortal/Login">
	<label for="Employer">Employer</label>
	<input type="radio" id="Employer" name="UserType" value="Employer">

	<label for="Seeker">Seeker</label>
	<input type="radio" id="Seeker" name="UserType" value="Seeker" checked="checked"><br>

	<label for="email">email</label><br>
	<input name="email" type="email" required>
	<%if(session.getAttribute("NoEmail")!=null
    && session.getAttribute("NoEmail").toString().equals("true")){%>
    <span>There is no account using this email</span> <%}%><br>

    <label for="password">password</label><br>
    <input name="password" type="password" required><br>
    	<%if(session.getAttribute("passwordMismatch")!=null
    && session.getAttribute("passwordMismatch").toString().equals("true")){%>
    <span>Wrong password</span> <%}%><br>
    <input type="submit" value="Login">
    
</form>
</div>
</div>

</body>
</html>