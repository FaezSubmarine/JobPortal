<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Applicants</title>
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
            <li class="active"><a href="EmployerJobList">Applicants</a></li>
            <li><a href="EmployerJobPosting">Job Posting</a></li>
            <li class="RightFloat"><a  href="Logout">Log Out</a></li>
            
         </ul>
    </div>
<div id="NotepaperDiv">
	<%List<HashMap<String,String>> jobLists = (List<HashMap<String,String>>)session.getAttribute("JobList");
		if(jobLists.size()!=0){
			for(HashMap<String,String> JobListing: jobLists){%>
				<div class="EachJobPosting">
					<div class="FloatRight">
						<p>applied on <%=JobListing.get("datePosted") %></p>
						<p>status:</p>
						<form action="/JobPortal/UpdateJobStatus">
            				<%=JobListing.get("status")%>
            					<select name="status" id="status">
	  								<option value="pending"  <% if(JobListing.get("status").equals("pending")){%>selected<%} %>>pending</option>
	  								<option value="reviewing" <% if(JobListing.get("status").equals("reviewing")){%>selected<%} %>>in review</option>
	  								<option value="accepted" <% if(JobListing.get("status").equals("accepted")){%>selected<%} %>>accepted</option>
	  								<option value="declined" <% if(JobListing.get("status").equals("declined")){%>selected<%} %>>declined</option>
								</select>
							<button name="jobID" type="submit" value=<%=JobListing.get("jobID") %>>Update status</button>
            			</form>
					</div>
				<h3><%=JobListing.get("role") %></h3>
				<%String hrefString = "GetSeekerHomePage?id="+JobListing.get("SeekerID"); %>
				<p>applied by <a href=<%=hrefString %>><%=JobListing.get("name")%></a> on <%=JobListing.get("dateApplied") %></p>
			</div>
	        <%}
	    }else{%>
	    	<div class="NoPosting">
	    		<h1>Looks like you don't have an applicant yet.</h1>
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