<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Posting</title>
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
            <li><a href="GetEmployerHomePage">Home</a></li>
            <li><a href="PostAJob.html">Post a Job</a></li>
            <li><a href="EmployerJobList">Applicants</a></li>
            <li class="active"><a href="EmployerJobPosting">Job Posting</a></li>
            <li class="RightFloat"><a  href="Logout">Log Out</a></li>
         </ul>
    </div>
	<div id="NotepaperDiv">
		<%List<HashMap<String,String>> jobLists = (List<HashMap<String,String>>)session.getAttribute("JobList");
		if(jobLists.size()!=0){
			for(HashMap<String,String> JobListing: jobLists){%>
			<% String hrefString = "DeleteJobPosting?id="+JobListing.get("id"); %>
				<div class="EachJobPosting">
					<div class="FloatRight">
					<p>Posted on <%=JobListing.get("datePosted") %></p>
					<a class="DeleteJob" href=<%=hrefString %>>Delete</a>
					</div>
					<h3><%=JobListing.get("role") %></h3>
					<p>Salary: $<%=JobListing.get("salary") %></p>
					<p>Description<br><%=JobListing.get("description") %></p>
					<p>Relevant experience:<br><%=JobListing.get("relevantExp") %></p>
					
				</div>
	        <%}
	    }else{%>
	    	<div class="NoPosting">
	    		<h1>Looks like you don't have a job postings yet.</h1>
	    		<a href="PostAJob.html">Post a job now!</a>
	    	</div>
	    <%} %>
    </div>
</body>
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
</html>