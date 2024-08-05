import { POSITION_NAME } from "./const.js";
const employeeArea = document.getElementById("employeeArea");
const registerForm = document.getElementById("registerForm");
const INFO = JSON.parse(sessionStorage.getItem("INFO"));

window.addEventListener("DOMContentLoaded", () => {
  console.log("called");
  console.log("INFO: ", INFO);
  INFO.forEach(({ name, email, password, position, hireDate }) => {
    const employeeDiv = document.createElement("div");
    const tableEl = document.createElement("table");
    const trName = document.createElement("tr");
    const thName = document.createElement("th");
    const tdName = document.createElement("td");
    thName.innerText = "氏名:";
    tdName.innerText = name;

    const trEmail= document.createElement("tr");
    const thEmail = document.createElement("th");
    const tdEmail = document.createElement("td");
    thEmail.innerText = "メールアドレス:";
    tdEmail.innerText = email;

    const trPosition= document.createElement("tr");
    const thPosition = document.createElement("th");
    const tdPosition = document.createElement("td");
    thPosition.innerText = "役職:";
    tdPosition.innerText = POSITION_NAME[position];

    const trHireDate= document.createElement("tr");
    const thHireDate = document.createElement("th");
    const tdHireDate = document.createElement("td");
    thHireDate.innerText = "入社日:";
    tdHireDate.innerText = hireDate;
    
    trName.appendChild(thName);
    trName.appendChild(tdName);
    trEmail.appendChild(thEmail);
    trEmail.appendChild(tdEmail);
    trPosition.appendChild(thPosition);
    trPosition.appendChild(tdPosition);
    trHireDate.appendChild(thHireDate);
    trHireDate.appendChild(tdHireDate);

    tableEl.appendChild(trName);
    tableEl.appendChild(trEmail);
    tableEl.appendChild(trPosition);
    tableEl.appendChild(trHireDate);
    employeeDiv.appendChild(tableEl);

    const inputName = document.createElement("input");
    const inputEmail = document.createElement("input");
    const inputPassword = document.createElement("input");
    const inputPosition = document.createElement("input");
    const inputHireDate = document.createElement("input");
    inputName.type = "hidden";
    inputName.name = "name";
    inputName.value = name;
    inputEmail.type="hidden";
    inputEmail.name = "email";
    inputEmail.value = email;
    inputPassword.type="hidden";
    inputPassword.name = "password";
    inputPassword.value = password;
    inputPosition.type="hidden";
    inputPosition.name = "position";
    inputPosition.value = position;
    inputHireDate.type="hidden";
    inputHireDate.name = "hireDate";
    inputHireDate.value = hireDate;

    registerForm.appendChild(inputName);
    registerForm.appendChild(inputEmail);
    registerForm.appendChild(inputPassword);
    registerForm.appendChild(inputPosition);
    registerForm.appendChild(inputHireDate);
    employeeArea.appendChild(employeeDiv);
  });
});

registerForm.addEventListener("submit", (e) => {
  console.log("INFO: ", INFO);
  e.preventDefault();
	fetch("/DateTime/EmployeeRegisterServlet", {
    method: "POST",
    body: JSON.stringify(INFO),
	})
    .then((response) => {
      console.log("response", response);
      if(!response.ok){
        throw new Error("Network response was not ok");
      }
      if (!response.isError) {
        window.location.href = `EmployeeRegisterComp.jsp`;
      }
    })
    .catch((err) => console.log("err: ", err));
})