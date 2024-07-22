const clockInTime = document.getElementById("clockInTime").value;
const clockOutTime = document.getElementById("clockOutTime").value;
const employeeCD = document.getElementById("employeeCD").value;
const name = document.getElementById("name").value;
const dateArea = document.getElementById("dateArea");
const beforeTimeArea = document.getElementById("beforeTimeArea");
const afterTimeArea = document.getElementById("afterTimeArea");

console.log("clockOutTime: ", clockOutTime);
const inTime = new Date(Number(clockInTime));
const outTime = new Date(Number(clockOutTime));
console.log(
  outTime,
  outTime.getFullYear(),
  outTime.getMonth() + 1,
  outTime.getDate(),
  outTime.getHours(),
  outTime.getMinutes(),
  outTime.getSeconds()
);

const employeeName = document.createElement("p");
const beforeText = document.createElement("p");
const selectDate = document.createElement("p");
const selectDate2 = document.createElement("input");
const afterText = document.createElement("p");
const beforeClockInTime = document.createElement("input");
const beforeClockOutTime = document.createElement("input");
const afterClockInTime = document.createElement("input");
const afterClockOutTime = document.createElement("input");
employeeName.innerText = name;
selectDate.innerText = `${inTime.getFullYear()}年${
  inTime.getMonth() + 1
}月${inTime.getDate()}日`;
selectDate2.type = "hidden";
selectDate2.name = "selectDate";
selectDate2.value = `${inTime.getFullYear()}-${String(
  inTime.getMonth() + 1
).padStart(2, "0")}-${String(inTime.getDate()).padStart(2, "0")}`;
beforeText.innerText = "変更前";
afterText.innerText = "変更後";
beforeClockInTime.type = "time";
beforeClockInTime.name = "beforeClockInTime";
beforeClockInTime.value = `${inTime.getHours()}:${inTime.getMinutes()}`;
beforeClockInTime.disabled = true;
beforeClockOutTime.type = "time";
beforeClockOutTime.name = "beforeClockOutTime";
beforeClockOutTime.value = `${outTime.getHours()}:${outTime.getMinutes()}`;
beforeClockOutTime.disabled = true;

afterClockInTime.type = "time";
afterClockInTime.required = true;
afterClockInTime.name = "afterClockInTime";
afterClockInTime.value = `${inTime.getHours()}:${inTime.getMinutes()}`;
afterClockOutTime.type = "time";
afterClockOutTime.required = true;
afterClockOutTime.name = "afterClockOutTime";
afterClockOutTime.value = `${outTime.getHours()}:${outTime.getMinutes()}`;

dateArea.appendChild(employeeName);
dateArea.appendChild(selectDate);
dateArea.appendChild(selectDate2);
beforeTimeArea.appendChild(beforeText);
beforeTimeArea.appendChild(beforeClockInTime);
beforeTimeArea.appendChild(beforeClockOutTime);
afterTimeArea.appendChild(afterText);
afterTimeArea.appendChild(afterClockInTime);
afterTimeArea.appendChild(afterClockOutTime);
