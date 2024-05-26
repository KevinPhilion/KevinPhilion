document.addEventListener("DOMContentLoaded", function () {
  const mobileMenu = document.querySelector(".mobile-menu");
  const menu = document.querySelector(".menu");

  mobileMenu.addEventListener("click", function () {
    menu.classList.toggle("show");
  });
});

function processRegistration(event) {
  event.preventDefault();
  //alert('registration simulation');
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  //console.log(username);

  localStorage.setItem("RegisteredUsers", username + ":" + password + ";");
}

function processLogin(event) {
  event.preventDefault();
  let usernameEntered = document.getElementById("username").value;
  let passwordEntered = document.getElementById("password").value;

  //alert("login simulation");
  // Retrieving data from localStorage
  const registeredUsers = localStorage.getItem("RegisteredUsers");
  //console.log(registeredUsers);
  let loginStatus = false;
  let message = "";
  if (registeredUsers != null) {
    let usernamePasswordPairs = registeredUsers.split(";");
    //console.log(usernamePasswordPairs[0]);
    for (let i = 0; i < usernamePasswordPairs.length; i++) {
      //console.log(usernamePasswordPairs[i]);
      if (usernamePasswordPairs[i] != " ") {
        let registeredUsername = usernamePasswordPairs[i].split(":")[0];
        let registeredPassword = usernamePasswordPairs[i].split(":")[1];
        console.log(registeredUsername);
        console.log(registeredPassword);
        if (
          usernameEntered == registeredUsername &&
          passwordEntered == registeredPassword
        ) {
          loginStatus = true;
          break;
        }
      }
    }
    message = loginStatus
      ? "login success"
      : "login failed, invalid credentials";
  } else {
    message = "no one has registered!";
  }
  alert(message);
}
