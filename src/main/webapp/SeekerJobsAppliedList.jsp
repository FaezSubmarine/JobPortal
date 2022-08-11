<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
            <li class="active"><a href="SeekerJobAppliedList">Application Status</a></li>
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
            	<td><%=JobListing.get("posterEmail") %></td>
            	<td><%=JobListing.get("role") %></td>
            	<td><%=JobListing.get("datePosted") %></td>
            	<td><%=JobListing.get("dateApplied") %></td>
            	<td><%=JobListing.get("status")%></td>

            </tr>
            <%}%>
        </table>
    </div>
</body>
</html>