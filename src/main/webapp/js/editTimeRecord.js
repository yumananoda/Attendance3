const clockInTime = document.getElementById("clockInTime").value;
const clockOutTime = document.getElementById("clockOutTime").value;
const dateArea = document.getElementById("clockOutTime").value;

console.log("clockInTime: ", clockInTime);
const inTime = new Date(Number(clockInTime));
console.log(
  inTime,
  inTime.getFullYear(),
  inTime.getMonth() + 1,
  inTime.getDate(),
  inTime.getHours(),
  inTime.getMinutes(),
  inTime.getSeconds()
);
