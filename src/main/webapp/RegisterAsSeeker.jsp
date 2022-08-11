<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register as Seeker</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">

<link rel="stylesheet" href="RegisterForm.css">
<script src="RegisterForm.js"></script>

</head>
<body>
<form action="/JobPortal/AddSeeker">
    <label for="firstName">First Name </label>
    <input name="firstName" type="text" required><br>
    
    <label for="lastName">Last Name </label>
    <input name="lastName" type="text" required><br>
	
	<label for="email">Email </label>
    <input name="email" type="email" required>
    <%if(session.getAttribute("ExistingEmail")!=null
    && session.getAttribute("ExistingEmail").toString().equals("true")){%>
    <span >There is an existing account already using this email</span> <%}%> <br>
    
    <label for="password">password</label>
    <input id="password" name="password" type="password" required><br>
    
    <label for="conPassword">confirm password</label>
    <input id="conPassword" name="conPassword" type="password" required><span id="notConfirmed"></span> <br>
    
    <label for="phoneNumber">Phone Number</label>
    <input name="phoneNumber" type="tel" required><br>
    
    <label for="address">address</label>
    <input name="address" type="text" required><br>
    
    <label for="SeekStatus">Choose your status:</label>
  		<select name="SeekStatus" id="SeekStatus">
    		<option value="ActLook" Selected>Actively Looking</option>
    		<option value="openOp">Open for opportunity</option>
    		<option value="NotLook">Not Looking</option>
  </select>
    
    <input id="submitButton" type="submit" value="Register">
</form>
</body>
</html>