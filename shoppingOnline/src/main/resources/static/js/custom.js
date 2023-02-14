const signUpButton = document.getElementById("signUp");
const signInButton = document.getElementById("signIn");
const container = document.getElementById("container");
const deleteRequest = document.getElementById("delete");

signUpButton.addEventListener("click", () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});

function getConfirm() {
	var k = confirm("Do you want delete it?");
	deleteRequest.value = k;
	console.log(typeof k);
}