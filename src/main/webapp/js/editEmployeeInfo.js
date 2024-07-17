const employeeInfoEl = document.getElementById("employeeInfo");
const employeeInfo = JSON.parse(employeeInfoEl.value);
console.log("empInfo:", employeeInfo);

const empInfoDiv = document.getElementById("empInfoDiv");
const tableEl = document.createElement("table");
const tr1 = document.createElement("tr");
tr1.innerText = "従業員番号";

const inputEmpNo = document.getElementById("input");