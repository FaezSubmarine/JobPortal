<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seekers Job List</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="CSS/EmployerJobPosting.css">
<link rel="stylesheet" href="CSS/NavBar.css">
<link rel="stylesheet" href="CSS/Footer.css">

</head>
<body>
    <div id="stickyBar">
        <ul id="navbar">
            <li><a href="GetSeekerHomePage">Home</a></li>
            <li><a href="SeekerJobList">Apply to a job</a></li>
            <li class="active"><a href="SeekerJobAppliedList">Application Status</a></li>
         	<li class="RightFloat"><a  href="Logout">Log Out</a></li>
         </ul>
    </div>
	<div id="NotepaperDiv">
		<% List<HashMap<String,String>> jobLists = (List<HashMap<String,String>>)session.getAttribute("JobList");
		if(jobLists.size()>0){
		for(HashMap<String,String> JobListing: jobLists){%>
			<div class="EachJobPosting">
				<div class="FloatRight">
					<p>applied on <%=JobListing.get("dateApplied") %></p>
					<p>status:</p>
					<% String statusString = JobListing.get("status");  %>
					<div class="StatusBar" id=<%=statusString %>><%=statusString %></div>
				</div>
				<h3><%=JobListing.get("role") %></h3>
				<%String hrefString = "GetEmployerHomePage?id="+JobListing.get("EmployerID"); %>
				<p>posted by <a href=<%=hrefString%>><%=JobListing.get("name")%></a> from <%=JobListing.get("company")%> on <%=JobListing.get("datePosted") %></p>
				<p>Posted on <%=JobListing.get("datePosted") %></p>

			</div>
		<%}
		}
		else{
		%>
			<div class="NoPosting">
	    		<h1>Looks like you have not applied to any jobs yet.</h1>
	    		<a href="SeekerJobList">Apply to a job now!</a>
	    	</div>
		<%} %>
    </div>
    <footer>
		<div>
			<h4>Seeker</h4>
				<a href="RegisterAsSeeker.jsp">Seeker Register</a>
				<a href="SeekerLogin.jsp">Seeker Log In</a>
		</div>
		<div>
			<h4>Employer</h4>
				<a href="RegisterAsEmployer.jsp">Employer Register</a>
				<a href="EmployerLogin.jsp">Employer Log In</a>
		
		</div>
		<div>
			<h4>Legal</h4>
			<a>Terms and Condition</a>
		</div>
		<div>
			<h4>About Us</h4>
			<a>About the developer</a>
		</div>
	</footer>
</body>
</html>