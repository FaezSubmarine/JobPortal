<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Seeker Home Page</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">

</head>
<body>
    <div id="stickyBar">
        <ul id="navbar">
            <li class="active"><a href="SeekerHomePage.jsp">Home</a></li>
            <li><a href="SeekerJobList">Apply to a job</a></li>
            <li><a href="SeekerJobAppliedList">Application Status</a></li>
         </ul>
    </div>
    <div id="NotepaperDiv">
    	<h1><% out.print("Hello "+session.getAttribute("firstName")+" "+session.getAttribute("lastName")); %></h1>
	<h1><%=session.getAttribute("email") %></h1>
	<h1><%=session.getAttribute("phoneNumber") %></h1>
	<h1><%=session.getAttribute("address") %></h1>
	<% String result = "";
	if(session.getAttribute("SeekStatus").equals("ActLook")){
		result = "actively looking for a job";
	}
	else if(session.getAttribute("SeekStatus").equals("openOp")){
		result = "Open for opportunities";
	}
	else if(session.getAttribute("SeekStatus").equals("NotLook")){
		result = "Not looking for a job";
	}
	%>
	<h1><%=result %></h1>
	
	<button onclick="location.href='SeekerUpdateDetail.jsp'">change detail</button>
	<button onclick="location.href='SeekerChangePassword.jsp'">change password</button>	
	<button id="logout" class="caution" onclick="location.href='Logout'" type="button"> log out</button></body>
	<button id="deleteAccount" class="caution" onclick="location.href='DeleteSeeker'" type="button"> Delete account</button></body>
    </div>

</html>