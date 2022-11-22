<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter&family=Secular+One&display=swap" rel="stylesheet">
<link rel="stylesheet" href="CSS/LandingPage.css">
<link rel="stylesheet" href="CSS/Footer.css">

<script src="JS/IndexRegisterButtons.js"></script>
</head>
<body>
	<div id="stickyBar">
		<ul id="navbar">
			<li id="WelcomeSign">
				<h2 class="WelcomeSign">Welcome to the</h2>
				<h1 class="WelcomeSign">Job Portal</h1>
			</li>
		</ul>
	</div>
	<main>
	<h2 id="AreYou">Choose your role</h2>
	<div id="ChoiceContainer">
		<div id="RegisterAsSeeker" class="LandingPagebuttons"
			onclick="location.href='RegisterAsSeeker.jsp'">
			<img id="SeekerImg0" class="slide"
				src="image/RegisterSeeker/cashier.jpg"> <img id="SeekerImg1"
				class="slide" src="image/RegisterSeeker/director.jpg"> <img
				id="SeekerImg2" class="slide" src="image/RegisterSeeker/lawyer.jpg">
			<img id="SeekerImg3" class="slide"
				src="image/RegisterSeeker/salesman.jpg">
			<h1 class="RegisterText">Job Seeker</h1>
		</div>
		<div id="RegisterAsEmployer" class="LandingPagebuttons"
			onclick="location.href='RegisterAsEmployer.jsp'">
			<img id="EmployerImg0" class="slide"
				src="image/RegisterEmployer/CallCenter.jpg"> <img
				id="EmployerImg1" class="slide"
				src="image/RegisterEmployer/Captain.jpg"> <img
				id="EmployerImg2" class="slide" src="image/RegisterEmployer/CEO.png">
			<img id="EmployerImg3" class="slide"
				src="image/RegisterEmployer/Manager.jpg">
			<h1 class="RegisterText">Employer</h1>
		</div>
	</div>
	</main>
	<footer>
		<div>
			<h4>Seeker</h4>
			<a href="RegisterAsSeeker.jsp">Seeker Register</a> <a
				href="SeekerLogin.jsp">Seeker Log In</a>
		</div>
		<div>
			<h4>Employer</h4>
			<a href="RegisterAsEmployer.jsp">Employer Register</a> <a
				href="EmployerLogin.jsp">Employer Log In</a>

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