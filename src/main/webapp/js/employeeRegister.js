import { POSITION_NAME, info, removeUsers } from "./const.js";

let form = document.getElementById("employeeForm");
const registerbtn = document.getElementById("register");
let registerUser = document.getElementById("registerUser");
let id = 1;
form.addEventListener("submit", async function (event) {
  event.preventDefault();
  let inputname = form.name.value;
  let inputemail = form.email.value;
  let inputradio = form.position.value;
  let inputdate = form.hire_date.value;
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
    let div4 = document.createElement("div");
    let check = document.createElement("input");
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

    div4.appendChild(check);
    let p1 = document.createElement("p");
    let p2 = document.createElement("p");
    let p3 = document.createElement("p");
    let p4 = document.createElement("p");
    p1.innerText = info[i].name;
    p2.innerText = info[i].email;
    p3.innerText = POSITION_NAME[Number(info[i].position)];
    p4.innerText = info[i].hire_date;
    div4.appendChild(p1);
    div4.appendChild(p2);
    div4.appendChild(p3);
    div4.appendChild(p4);
    registerUser.appendChild(div4);
  }

  if (registerShow !== 0) {
    let removebtn = document.createElement("button");
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
  fetch("/DateTime12/EmployeeRegisterServlet", {
    method: "POST",
    body: JSON.stringify(info),
  })
    .then((res) => console.log("success", res))
    .then((data) => console.log(data));
});
