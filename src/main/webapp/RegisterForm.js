var password = null;
var conPassword = null;
var submitButton = null;
var notConfirmed = null;

window.onload=function(){
	password = document.getElementById("password");
	conPassword = document.getElementById("conPassword");
	submitButton = document.getElementById("submitButton");
	notConfirmed =document.getElementById("notConfirmed");
	
	password.addEventListener("input",confirmingPassword);
	conPassword.addEventListener("input",confirmingPassword);
}

function confirmingPassword(){
	if(conPassword.value.length<password.value.length || !(conPassword.value == password.value)){
		submitButton.disabled = true;
		notConfirmed.innerHTML="password does not match";
		return;
	}
	submitButton.disabled = false;
	notConfirmed.innerHTML="";
}


