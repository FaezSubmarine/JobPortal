<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employer Home Page</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">

</head>
<body>
    <div id="stickyBar">
        <ul id="navbar">
            <li class="active"><a href="EmployerHomePage.jsp">Home</a></li>
            <li><a href="PostAJob.html">Post a Job</a></li>
            <li><a href="EmployerJobList">Applicants</a></li>
            <li><a href="EmployerJobPosting">Job Posting</a></li>
         </ul>
    </div>
    <div id="NotepaperDiv">
        <h1><% out.print("Hello "+session.getAttribute("firstName")+" "+session.getAttribute("lastName")); %></h1>
		<h1><%=session.getAttribute("email") %></h1>
		<h1><%=session.getAttribute("phoneNumber") %></h1>
		<h1><%=session.getAttribute("address") %></h1>
		<h1><%=session.getAttribute("company") %></h1>
	
	
		<button onclick="location.href='EmployerUpdateDetail.jsp'">change detail</button>
		<button onclick="location.href='EmployerChangePassword.jsp'">change password</button>
		<button id="logout" class="caution" onclick="location.href='Logout'" type="button"> log out</button></body>
		<button id="deleteAccount" class="caution" onclick="location.href='DeleteEmployer'" type="button"> Delete account</button>


    </div>

	
</body>
</html>