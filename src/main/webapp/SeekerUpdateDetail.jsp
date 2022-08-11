<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seeker Update Detail</title>

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
    <div id= "NotepaperDiv">
    <form action="/JobPortal/SeekerUpdateDetail">
    <label for="firstName">First Name </label>
    <input name="firstName" type="text" value="<%=session.getAttribute("firstName")%>" required><br>
    
    <label for="lastName">Last Name </label>
    <input name="lastName" type="text" value="<%=session.getAttribute("lastName")%>"  required><br>
	
	<label for="email">Email </label>
    <input name="email" type="text" value="<%=session.getAttribute("email")%>" required>
    <%if(session.getAttribute("ExistingEmail")!=null
    && session.getAttribute("ExistingEmail").toString().equals("true")){%>
    <span >There is an existing account already using this email</span> <%}%> <br>
    
    <label for="phoneNumber">Phone Number</label>
    <input name="phoneNumber" type="text" value="<%=session.getAttribute("phoneNumber")%>" required><br>
    
    <label for="address">address</label>
    <input name="address" type="text" value="<%=session.getAttribute("address")%>" required><br>
    
    <label for="SeekStatus">Seeking Status</label>
    	<select name="SeekStatus" id="SeekStatus">
	  		<option value="ActLook"  <% if(session.getAttribute("SeekStatus").equals("ActLook")){%>selected<%} %>>actively looking</option>
	  		<option value="OpenForOp" <% if(session.getAttribute("SeekStatus").equals("OpenForOp")){%>selected<%} %>>Open for Opportunities</option>
	  		<option value="NotLook" <% if(session.getAttribute("SeekStatus").equals("NotLook")){%>selected<%} %>>Not Looking</option>
		</select>
    <br>
    <label for="password">password</label>
    <input id="password" name="password" type="password" required><br>
    <%if(session.getAttribute("mismatchPassword") !=null){ %>
    	<span>password does not match</span>
    <%} %>
    <input id="submitButton" type="submit" value="Update">
    
</form>
    </div>

</body>
</html>