<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register as Employer</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter&family=Secular+One&display=swap" rel="stylesheet">

<link rel="stylesheet" href="CSS/RegisterAsSeeker.css">
<link rel="stylesheet" href="CSS/Footer.css">
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

<script type="module" src="JS/TimerForInputer.js"></script>
<script type="module" src="JS/RegisterToSeeker.js"></script>
<script type="module" src="JS/CheckPasswordStrength.js"></script>
</head>
<body>
<div id="introduction">
	<div id="JoinMillions">
		<h1 id="JoinMillionsText">Join the millions of people who got connected with Job Portal!</h1>
	</div>
</div>

<div id="FormContainer">
	<form action="/JobPortal/AddEmployer">
	    <label for="firstName">First Name </label><br>
	    <input id="firstName" name="firstName" type="text" required><br>
	    
	    <label for="lastName">Last Name </label><br>
	    <input id="lastName" name="lastName" type="text" required><br>
		
	 	<label for="email">Email </label><br>
		<input id="EmployerEmail" name="email" type="email" required>
		<p id="EmailNotifier"></p>
		    
		<label for="password">password</label><br>
		<input id="password" name="password" type="password" required><br>
		<div id="passwordStrength">
			<p>Password Strength:</p><span id="passwordComment"></span>
			<p id="complaint"></p>
		</div>
		
		    
		<label for="conPassword">confirm password</label><br>
		<input id="conPassword" name="conPassword" type="password" required>
		<p id="notConfirmed"></p>
	    
	    <label for="phoneNumber">Phone Number</label><br>
	    <input id="phoneNumber" name="phoneNumber" type="text" required><br>
	    
	    <label for="address">address</label><br>
	    <input id="address" name="address" type="text" required><br>
	    
	    <label for="company">company</label><br>
	    <input id="company" name="company" type="text" required><br>
	    
	    <input id="submitButton" type="submit" value="Register">
	</form>
		<div id="NotRegister">
			<div>
				<p>Already have an account?</p>
				<a href="EmployerLogin.jsp">Sign up!</a>
			</div>
			<div>
				<p>Not an employer?</p>
				<a href="RegisterAsSeeker.jsp">log in as seeker!</a>
			</div>
		</div>
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