import { POSITION_NAME, STORE_NAME } from "./const.js";

let employeeInfoEl = document.getElementById("employeeInfo");
employeeInfoEl = JSON.parse(employeeInfoEl.value);
const editForm = document.getElementById("editForm");
const employeeCDEL = document.getElementById("employeeCD");
const nameEL = document.getElementById("name");
const emailEl = document.getElementById("email");
const storeCDEl = document.getElementById("storeName");
const hireDateEl = document.getElementById("hireDate");
const positionEl = document.getElementById("position");
const resetBtn = document.getElementById("resetBtn");
console.log("employeeInfoEl:", employeeInfoEl);

const employeeCD = document.createElement("input");
const name = document.createElement("input");
const email = document.createElement("input");
const storeName = document.createElement("select");
const hireDate = document.createElement("input");
const position = document.createElement("input");

employeeCD.type = "text";
employeeCD.name = "employeeCD";
employeeCD.value = employeeInfoEl.employeeCD;
employeeCD.readOnly = true;

name.type = "text";
name.name = "name";
name.value = employeeInfoEl.name;

email.type = "text";
email.name = "email";
email.value = employeeInfoEl.email;
email.readOnly = true;

storeName.name = "storeName";
storeName.value = employeeInfoEl.storeName;
const storeKeys = Object.keys(STORE_NAME);
storeKeys.forEach(key => {
    const option = document.createElement("option");
    option.value = key;
    option.innerText = STORE_NAME[key];
    storeName.appendChild(option);
    if(key === employeeInfoEl.storeCD){
        option.selected = true;
    }
});

hireDate.type = "text";
hireDate.name = "hireDate";
let hireDateValue = new Date(employeeInfoEl.hire_date);
hireDateValue = hireDateValue.getFullYear() + "年" + String((hireDateValue.getMonth()+1)).padStart(2, '0') + "月" + String(hireDateValue.getDate()).padStart(2, '0') + "日";
hireDate.value = hireDateValue;
hireDate.readOnly = true;

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
        if(Number(key) === employeeInfoEl.position){
            position.checked = true;
        }
        positionDiv.appendChild(position);
        positionDiv.appendChild(labelPosition);
        positionEl.appendChild(positionDiv);
    })
}
identifyPosition();

resetBtn.addEventListener("click", (event) => {
    event.preventDefault(); 
    name.value = employeeInfoEl.name;
    storeName.value = employeeInfoEl.storeCD;
    identifyPosition();
});

employeeCDEL.appendChild(employeeCD);
nameEL.appendChild(name);
emailEl.appendChild(email);
storeCDEl.appendChild(storeName);
hireDateEl.appendChild(hireDate);