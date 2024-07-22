import { POSITION_NAME, storeName } from "./const.js";

let employeeInfoEl = document.getElementById("employeeInfo");
employeeInfoEl = JSON.parse(employeeInfoEl.value);
const editForm = document.getElementById("editForm");
console.log("employeeInfoEl:", employeeInfoEl);

const empInfoDiv = document.getElementById("empInfoDiv");
const title = document.createElement("h1");
const tableEl = document.createElement("table");
const explanation = document.createElement("p");
const trEmpCD = document.createElement("tr");
const thEmpCD = document.createElement("th");
const tdEmpCD = document.createElement("td");
const inputEmpCD = document.createElement("input");
const trName = document.createElement("tr");
const thName = document.createElement("th");
const tdName = document.createElement("td");
const inputName = document.createElement("input");
const trEmail = document.createElement("tr");
const thEmail = document.createElement("th");
const tdEmail = document.createElement("td");
const inputEmail = document.createElement("input");
const trStoreName = document.createElement("tr");
const thStoreName = document.createElement("th");
const tdStoreName = document.createElement("td");
const inputStoreName = document.createElement("select");
const trHireDate = document.createElement("tr");
const thHireDate = document.createElement("th");
const tdHireDate = document.createElement("td");
const inputHireDate = document.createElement("input");
const trPosition = document.createElement("tr");
const thPosition = document.createElement("th");
const tdPosition = document.createElement("td");
const PositionFieldset = document.createElement("fieldset");
const formButton = document.createElement("input");
const resetButton = document.createElement("input");

title.innerText = "従業員情報変更画面";
explanation.innerText = "以下の項目に変更したい情報をご入力の上、「変更」ボタンをクリックしてください。";
thEmpCD.innerText = "従業員番号";
inputEmpCD.type = "text";
inputEmpCD.name = "empCD"
inputEmpCD.value = employeeInfoEl.employeeCD;
inputEmpCD.readOnly = true;

thName.innerText = "氏名";
inputName.type = "text";
inputName.name = "name"
inputName.value = employeeInfoEl.name;

thEmail.innerText = "メールアドレス";
inputEmail.type = "text";
inputEmail.name = "email"
inputEmail.value = employeeInfoEl.email;
inputEmail.readOnly = true;

thStoreName.innerText = "店舗名";
inputStoreName.name = "storeName"
const storeKeys = Object.keys(storeName);
storeKeys.forEach(key => {
    const option = document.createElement("option");
    option.value = key;
    option.innerText = storeName[key];
    inputStoreName.appendChild(option);
    if(key === employeeInfoEl.storeCD){
        option.selected = true;
    }
});

thHireDate.innerText = "入社日";
let hireDate = new Date(employeeInfoEl.hire_date);
hireDate = hireDate.getFullYear() + "年" + String((hireDate.getMonth()+1)).padStart(2, '0') + "月" + String(hireDate.getDate()).padStart(2, '0') + "日";
inputHireDate.type = "text";
inputHireDate.name = "hireDate"
inputHireDate.value = hireDate;
inputHireDate.readOnly = true;

function identifyPosition(){
    while (PositionFieldset.firstChild) {
        PositionFieldset.removeChild(PositionFieldset.firstChild);
    }
    thPosition.innerText = "役職";
    const positionKeys = Object.keys(POSITION_NAME);
    positionKeys.forEach(key => {
        const divPosition = document.createElement("div");
        const inputPosition = document.createElement("input");
        const labelPosition = document.createElement("label");
        inputPosition.type = "radio";
        inputPosition.id = POSITION_NAME[key]
        inputPosition.name = "position"
        inputPosition.value = key;
        labelPosition.setAttribute("for", inputPosition.id);
        labelPosition.innerText = POSITION_NAME[key];
        if(Number(key) === employeeInfoEl.position){
            inputPosition.checked = true;
        }
        divPosition.appendChild(inputPosition);
        divPosition.appendChild(labelPosition);
        PositionFieldset.appendChild(divPosition);
    })
}
identifyPosition();


// inputPosition.value = POSITION_NAME[employeeInfoEl.position];

formButton.type = "submit";
formButton.value = "変更"
resetButton.type = "reset";
resetButton.value = "リセット"
resetButton.addEventListener("click", (event) => {
    event.preventDefault(); 
    inputName.value = employeeInfoEl.name;
    inputStoreName.value = employeeInfoEl.storeCD;
    identifyPosition();
});

tdEmpCD.appendChild(inputEmpCD);
trEmpCD.appendChild(thEmpCD);
trEmpCD.appendChild(tdEmpCD);
tdName.appendChild(inputName);
trName.appendChild(thName);
trName.appendChild(tdName);
tdEmail.appendChild(inputEmail);
trEmail.appendChild(thEmail);
trEmail.appendChild(tdEmail);
tdStoreName.appendChild(inputStoreName);
trStoreName.appendChild(thStoreName);
trStoreName.appendChild(tdStoreName);
tdHireDate.appendChild(inputHireDate);
trHireDate.appendChild(thHireDate);
trHireDate.appendChild(tdHireDate);
tdPosition.appendChild(PositionFieldset);
trPosition.appendChild(thPosition);
trPosition.appendChild(tdPosition);
tableEl.appendChild(trEmpCD);
tableEl.appendChild(trName);
tableEl.appendChild(trEmail);
tableEl.appendChild(trStoreName);
tableEl.appendChild(trHireDate);
tableEl.appendChild(trPosition);
editForm.appendChild(tableEl);
editForm.appendChild(formButton);
editForm.appendChild(resetButton);
empInfoDiv.appendChild(title);
empInfoDiv.appendChild(explanation);
empInfoDiv.appendChild(editForm);