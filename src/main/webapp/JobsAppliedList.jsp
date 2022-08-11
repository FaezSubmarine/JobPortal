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
<link rel="stylesheet" href="style.css">

</head>
<body>
    <div id="stickyBar">
        <ul id="navbar">
            <li><a href="EmployerHomePage.jsp">Home</a></li>
            <li><a href="PostAJob.html">Post a Job</a></li>
            <li class="active"><a href="EmployerJobList">Applicants</a></li>
            <li><a href="EmployerJobPosting">Job Posting</a></li>
         </ul>
    </div>
<div id="NotepaperDiv">
    	<table>
        	<caption><h2>List of jobs</h2></caption>
            <tr>
                <th>Applicant Email</th>
                <th>Role</th>
                <th>Date Posted</th>
                <th>Date Applied</th>
                <th>Status</th>
            </tr>
            <%
            for(HashMap<String,String> JobListing: (List<HashMap<String,String>>)session.getAttribute("JobList")){%>
            <tr>
            	<td><%=JobListing.get("applicantEmail") %></td>
            	<td><%=JobListing.get("role") %></td>
            	<td><%=JobListing.get("datePosted") %></td>
            	<td><%=JobListing.get("dateApplied") %></td>
            	<td>
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
            	</td>
            </tr>
            <%}%>
        </table>
    </div>
</body>
</html>