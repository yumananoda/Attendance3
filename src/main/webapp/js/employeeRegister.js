import { POSITION_NAME, INFO, REMOVEUSERS } from "./const.js";

const form = document.getElementById("registerForm");
const registerbtn = document.getElementById("registerbtn");
const registerUserList = document.getElementById("registerUserList");
const positionEl = document.getElementById("position");

const identifyPosition = () => {
  while (positionEl.firstChild) {
      positionEl.removeChild(positionEl.firstChild);
  }
  const positionKeys = Object.keys(POSITION_NAME);
  positionKeys.forEach(key => {
      const positionDiv = document.createElement("div");
      const position = document.createElement("input");
      const labelPosition = document.createElement("label");
      position.type = "radio";
      position.id = POSITION_NAME[key]
      position.name = "position"
      position.value = key;
      labelPosition.setAttribute("for", position.id);
      labelPosition.innerText = POSITION_NAME[key];
      if(Number(key) === 1){
          position.checked = true;
      }
      positionDiv.appendChild(position);
      positionDiv.appendChild(labelPosition);
      positionEl.appendChild(positionDiv);
  })
}
identifyPosition();

const id = 1;
form.addEventListener("submit", async function (event) {
  event.preventDefault();
  const inputname = form.name.value;
  const inputemail = form.email.value;
  const inputradio = form.position.value;
  const inputdate = form.hireDate.value;
  console.log(inputname, inputemail, inputradio, inputdate);
  INFO.push({
    id: id,
    name: inputname,
    email: inputemail,
    position: inputradio,
    hireDate: inputdate,
  });
  console.log(INFO);
  form.reset();
  registerShow();
  console.log(id);
  id++;
});

const registerShow = () => {
  while (registerUserList.firstChild) {
    registerUserList.removeChild(registerUserList.firstChild);
  }

  for (let i = 0; i < INFO.length; i++) {
    const div = document.createElement("div");
    const check = document.createElement("input");
    check.setAttribute("type", "checkbox");
    check.setAttribute("name", "remove");
    check.setAttribute("value", INFO[i].id);
    check.addEventListener("change", (e) => {
      if (REMOVEUSERS.some((x) => x === e.target.value)) {
        const index = REMOVEUSERS.findIndex((y) => y === e.target.value);
        REMOVEUSERS.splice(index, 1);
        console.log("removeUsersから削除");
      } else {
        REMOVEUSERS.push(e.target.value);
        console.log("removeUsersに追加");
      }
      console.log(REMOVEUSERS);
    });

    div.appendChild(check);
    const p1 = document.createElement("p");
    const p2 = document.createElement("p");
    const p3 = document.createElement("p");
    const p4 = document.createElement("p");
    p1.innerText = INFO[i].name;
    p2.innerText = INFO[i].email;
    p3.innerText = POSITION_NAME[Number(INFO[i].position)];
    p4.innerText = INFO[i].hireDate;
    div.appendChild(p1);
    div.appendChild(p2);
    div.appendChild(p3);
    div.appendChild(p4);
    registerUserList.appendChild(div);
  }

  if (registerShow !== 0) {
    const removebtn = document.createElement("button");
    removebtn.textContent = "削除する";
    registerUserList.appendChild(removebtn);
    removebtn.id = "removebtn";
    removebtn.addEventListener("click", function () {
      removeSelectedUsers();
      registerShow();
      alert("削除しました");
    });
  }
};
const removeSelectedUsers = () => {
  for (const userId of REMOVEUSERS) {
    const index = INFO.findIndex((user) => user.id === parseInt(userId, 10));
    if (index !== -1) {
      INFO.splice(index, 1);
    }
  }
  REMOVEUSERS.length = 0;
};

registerbtn.addEventListener("click", function () {
  console.log("aaa");
  fetch("/DateTime/EmployeeRegisterServlet", {
    method: "POST",
    body: JSON.stringify(INFO),
  })
    .finally(() => {
      sessionStorage.setItem("INFO", JSON.stringify(INFO));
      window.location.href = `DispEmployeeRegisterComp`;
    });
});
