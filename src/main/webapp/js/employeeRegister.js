import { POSITION_NAME, info, removeUsers } from "./const.js";

const form = document.getElementById("employeeForm");
const registerbtn = document.getElementById("register");
const registerUser = document.getElementById("registerUser");
const id = 1;
form.addEventListener("submit", async function (event) {
  event.preventDefault();
  const inputname = form.name.value;
  const inputemail = form.email.value;
  const inputradio = form.position.value;
  const inputdate = form.hire_date.value;
  console.log(inputname, inputemail, inputradio, inputdate);
  info.push({
    id: id,
    name: inputname,
    email: inputemail,
    position: inputradio,
    hire_date: inputdate,
  });
  console.log(info);
  form.reset();
  registerShow();
  console.log(id);
  id++;
});

const registerShow = () => {
  while (registerUser.firstChild) {
    registerUser.removeChild(registerUser.firstChild);
  }

  for (let i = 0; i < info.length; i++) {
    const div = document.createElement("div");
    const check = document.createElement("input");
    check.setAttribute("type", "checkbox");
    check.setAttribute("name", "remove");
    check.setAttribute("value", info[i].id);
    check.addEventListener("change", (e) => {
      if (removeUsers.some((x) => x === e.target.value)) {
        const index = removeUsers.findIndex((y) => y === e.target.value);
        removeUsers.splice(index, 1);
        console.log("removeUsersから削除");
      } else {
        removeUsers.push(e.target.value);
        console.log("removeUsersに追加");
      }
      console.log(removeUsers);
    });

    div.appendChild(check);
    const p1 = document.createElement("p");
    const p2 = document.createElement("p");
    const p3 = document.createElement("p");
    const p4 = document.createElement("p");
    p1.innerText = info[i].name;
    p2.innerText = info[i].email;
    p3.innerText = POSITION_NAME[Number(info[i].position)];
    p4.innerText = info[i].hire_date;
    div.appendChild(p1);
    div.appendChild(p2);
    div.appendChild(p3);
    div.appendChild(p4);
    registerUser.appendChild(div4);
  }

  if (registerShow !== 0) {
    const removebtn = document.createElement("button");
    removebtn.textContent = "削除する";
    registerUser.appendChild(removebtn);
    removebtn.id = "removebtn";
    removebtn.addEventListener("click", function () {
      removeSelectedUsers();
      registerShow();
      alert("削除しました");
    });
  }
};
const removeSelectedUsers = () => {
  for (const userId of removeUsers) {
    const index = info.findIndex((user) => user.id === parseInt(userId, 10));
    if (index !== -1) {
      info.splice(index, 1);
    }
  }
  removeUsers.length = 0;
};

registerbtn.addEventListener("click", function () {
  console.log("aaa");
  fetch("/DateTime/EmployeeRegisterServlet", {
    method: "POST",
    body: JSON.stringify(info),
  })
    .then((res) => console.log("success", res))
    .then((data) => console.log(data));
});
