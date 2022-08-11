<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.List"%>

<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Posting</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div id="stickyBar">
        <ul id="navbar">
            <li><a href="SeekerHomePage.jsp">Home</a></li>
            <li><a href="SeekerJobList">Apply to a job</a></li>
            <li><a href="SeekerJobAppliedList">Application Status</a></li>
         </ul>
    </div>
	<div id="NotepaperDiv">
    	<table>
        	<caption><h2>List of jobs</h2></caption>
            <tr>
                <th>company</th>
                <th>Email</th>
                <th>role</th>
                <th>description</th>
                <th>salary</th>
                <th>relevant experience</th>
                <th>Date Posted</th>
            </tr>
            <%
            for(HashMap<String,String> JobListing: (List<HashMap<String,String>>)session.getAttribute("JobList")){%>
            <tr>
            	<td><%=JobListing.get("company") %></td>
            	<td><%=JobListing.get("EmployerEmail") %></td>
            	<td><%=JobListing.get("role") %></td>
            	<td><%=JobListing.get("description") %></td>
            	<td><%=JobListing.get("salary") %></td>
            	<td><%=JobListing.get("relevantExp") %></td>
            	<td><%=JobListing.get("datePosted") %></td>
            	
            	<% if(JobListing.get("ButtonState").toString().equals("disable")){%>
            	<td><button disabled>applied</button></td>
            	<%}else{ %>
            	<td>
            	<form action="/JobPortal/Applying">
            	<button type="submit" name="JobID" value=<%=JobListing.get("ButtonState") %>>Apply</button>
            	</form>
            	</td>
            	<%} %>
            </tr>
            <%}%>
        </table>
    </div>
</body>
</html>