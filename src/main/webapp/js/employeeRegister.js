import { POSITION_NAME, INFO, REMOVE_USERS, API_CONFIG } from "./const.js";

const formEl = document.getElementById("registerForm");
const addBtn = document.getElementById("addEmployee");
const registerBtn = document.getElementById("registerBtn");
const registerUserListEl = document.getElementById("registerUserList");
const positionEl = document.getElementById("position");
const errorEl = document.getElementById("error");

const identifyPosition = () => {
  while (positionEl.firstChild) {
    positionEl.removeChild(positionEl.firstChild);
  }
  const positionKeys = Object.keys(POSITION_NAME);
  positionKeys.forEach((key) => {
    const positionDiv = document.createElement("div");
    const position = document.createElement("input");
    const labelPosition = document.createElement("label");
    position.type = "radio";
    position.id = POSITION_NAME[key];
    position.name = "position";
    position.value = key;
    labelPosition.setAttribute("for", position.id);
    labelPosition.innerText = POSITION_NAME[key];
    if (Number(key) === 1) {
      position.checked = true;
    }
    positionDiv.appendChild(position);
    positionDiv.appendChild(labelPosition);
    positionEl.appendChild(positionDiv);
  });
};


const generateId = () => {
  let currentId = 0;
  return function () {
    return ++currentId;
  };
};
const getNextId = generateId();

const registerShow = () => {
  while (registerUserListEl.firstChild) {
    registerUserListEl.removeChild(registerUserListEl.firstChild);
  }
  INFO.forEach(({ id, name, email, hireDate, position }) => {
    const registerDiv = document.createElement("div");
    const check = document.createElement("input");
    check.type = "checkbox";
    check.name = "removeCheckBox";
    check.value = id;
    check.addEventListener("change", (e) => {
      console.log(e);
      if (REMOVE_USERS.some((x) => x === e.target.value)) {
        const index = REMOVE_USERS.findIndex((y) => y === e.target.value);
        REMOVE_USERS.splice(index, 1);
        console.log("REMOVE_USERSから削除");
      } else {
        REMOVE_USERS.push(e.target.value);
        console.log("REMOVE_USERSに追加");
      }
      if (!document.getElementById("removeBtn")) {
        createRemoveBtn();
      }
      console.log("REMOVE_USERS:", REMOVE_USERS);
    });

    registerBtn.disabled = false;
    errorEl.textContent = "";
    const registerName = document.createElement("p");
    const registerEmail = document.createElement("p");
    const registerPosition = document.createElement("p");
    const registerHireDate = document.createElement("p");
    registerName.innerText = name;
    registerEmail.innerText = email;
    registerPosition.innerText = POSITION_NAME[position];
    registerHireDate.innerText = hireDate;
    registerDiv.appendChild(check);
    registerDiv.appendChild(registerName);
    registerDiv.appendChild(registerEmail);
    registerDiv.appendChild(registerPosition);
    registerDiv.appendChild(registerHireDate);
    registerUserListEl.appendChild(registerDiv);
  });
};

const createRemoveBtn = () => {
  const removeDiv = document.createElement("div");
  const removeBtn = document.createElement("button");
  removeBtn.textContent = "削除する";
  removeBtn.id = "removeBtn";
  removeBtn.addEventListener("click", function () {
    removeSelectedUsers();
    registerShow();
    setTimeout(() => {
      alert("削除しました");
    }, 300);
    if (REMOVE_USERS.length === 0) {
      this.remove();
    }
  });
  removeDiv.appendChild(removeBtn);
  registerUserListEl.appendChild(removeDiv);
};

const removeSelectedUsers = () => {
  REMOVE_USERS.forEach((x) => {
    console.log(REMOVE_USERS);
    console.log("x:", x);
    const index = INFO.findIndex(({ id }) => id === Number(x));
    console.log(index);
    if (index !== -1) {
      INFO.splice(index, 1);
    }
  });
  REMOVE_USERS.length = 0;
  console.log(REMOVE_USERS);
  if(INFO.length === 0){
    registerBtn.disabled = true;
  }
};


identifyPosition();
addBtn.addEventListener("click", async function (event) {
  event.preventDefault();
  const inputName = formEl.name.value;
  const inputEmail = formEl.email.value;
  const inputPosition = formEl.position.value;
  const inputHireDate = formEl.hireDate.value;

  if(inputName === "" || inputEmail === "" || inputPosition === "" || inputHireDate === ""){
    errorEl.textContent = "エラー:入力されていない項目があります。";
    formEl.reset();
    identifyPosition();
    return;
  }
  const index = INFO.findIndex(({ email }) => email === inputEmail);
  console.log(index);
  if (index !== -1) {
    errorEl.textContent = "エラー: 同一のメールアドレスが入力されています。";
    formEl.reset();
    identifyPosition();
    return;
  }

  fetch("/DateTime/EmployeeRegisterCheckServlet", {
    method: "POST",
    body: inputEmail,
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
        return response;
    })
    .then((res) => res.text())
    .then((text) => {
      console.log("text:",text);
      console.log(typeof text);
      if (text === "false") {
        INFO.push({
          id: getNextId(),
          name: inputName,
          email: inputEmail,
          position: inputPosition,
          hireDate: inputHireDate,
        });
      }else{
        errorEl.textContent = "エラー: データベースに既に存在するメールアドレスは登録できません";
        formEl.reset();
        identifyPosition();
        return;
      }
      console.log(INFO);
      registerShow();
      formEl.reset();
      identifyPosition();
    });
    
    
  
});


const generatePassword = (length) => {
  const charset =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+~`|}{[]:;?><,./-=";
  let password = "";
  for (let i = 0, n = charset.length; i < length; ++i) {
    password += charset.charAt(Math.floor(Math.random() * n));
  }
  return password;
}

window.addEventListener("DOMContentLoaded", () => {
  emailjs.init(API_CONFIG.USER_ID);
});

registerBtn.addEventListener("click", function () {
  for (let i = 0; i < INFO.length; i++) {
    const passwordLength = 12;
    const newPassword = generatePassword(passwordLength);
    console.log("生成されたパスワード: " + newPassword);
    INFO[i].password = newPassword;
  }
  console.log(INFO);
  // fetch("/DateTime/DispEmployeeRegisterComfirmServlet", {
  //   method: "POST",
  //   body: JSON.stringify(INFO),
  // })
  // .then(
  //   console.log("success")
  // )
  window.location.href = "/DateTime/EmployeeRegisterConfirm.jsp";	
  sessionStorage.setItem("INFO", JSON.stringify(INFO));
  // INFO.forEach(({ name, email }) => {
  //   sendEmail({ name, email });
  // });
});

const sendEmail = ({ name, email }) => {
  const password = generatePassword();
  emailjs
    .send(API_CONFIG.SERVICE_ID, API_CONFIG.TEMPLATE_ID, {
      to_name: name,
      to_email: email,
      from_name: "店長",
      from_email: "admin@gmail.com",
      init_password: password,
    })
    .then(
      function (response) {
        console.log("SUCCESS!", response.status, response.text);
      },
      function (error) {
        console.log("FAILED...", error);
      }
    );
};