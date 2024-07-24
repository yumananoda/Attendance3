import { POSITION_NAME, INFO, REMOVE_USERS } from "./const.js";

const formEl = document.getElementById("registerForm");
const registerbtn = document.getElementById("registerbtn");
const registerUserListEl = document.getElementById("registerUserList");
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
formEl.addEventListener("submit", async function (event) {
  event.preventDefault();
  const inputname = formEl.name.value;
  const inputemail = formEl.email.value;
  const inputradio = formEl.position.value;
  const inputdate = formEl.hireDate.value;
  console.log(inputname, inputemail, inputradio, inputdate);
  INFO.push({
    id: id,
    name: inputname,
    email: inputemail,
    position: inputradio,
    hireDate: inputdate,
  });
  console.log(INFO);
  formEl.reset();
  registerShow();
  console.log(id);
  id++;
});

const registerShow = () => {
  while (registerUserListEl.firstChild) {
    registerUserListEl.removeChild(registerUserListEl.firstChild);
  }

  for (let i = 0; i < INFO.length; i++) {
    const div = document.createElement("div");
    const check = document.createElement("input");
    check.type = "checkbox";
    check.name = "remove";
    check.value = INFO[i].id;
    check.addEventListener("change", (e) => {
      if (REMOVE_USERS.some((x) => x === e.target.value)) {
        const index = REMOVE_USERS.findIndex((y) => y === e.target.value);
        REMOVE_USERS.splice(index, 1);
        console.log("REMOVE_USERSから削除");
      } else {
        REMOVE_USERS.push(e.target.value);
        console.log("REMOVE_USERSに追加");
      }
      console.log(REMOVE_USERS);
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
    registerUserListEl.appendChild(div);
  }

  if (registerShow !== 0) {
    const removebtn = document.createElement("button");
    removebtn.textContent = "削除する";
    registerUserListEl.appendChild(removebtn);
    removebtn.id = "removebtn";
    removebtn.addEventListener("click", function () {
      removeSelectedUsers();
      registerShow();
      alert("削除しました");
    });
  }
};
const removeSelectedUsers = () => {
  for (const userId of REMOVE_USERS) {
    const index = INFO.findIndex((user) => user.id === parseInt(userId, 10));
    if (index !== -1) {
      INFO.splice(index, 1);
    }
  }
  REMOVE_USERS.length = 0;
};

registerbtn.addEventListener("click", function () {
  console.log("aaa");
  fetch("/DateTime/EmployeeRegisterServlet", {
    method: "POST",
    body: JSON.stringify(INFO),
  })
    .finally(() => {
	//メール送信処理	
      sessionStorage.setItem("INFO", JSON.stringify(INFO));
      window.location.href = `DispEmployeeRegisterComp`;
    });
});
