const editTimeRecordForm = document.getElementById("editTimeRecordForm");
const clockInTime = document.getElementById("clockInTime").value;
const clockOutTime = document.getElementById("clockOutTime").value;
const selectDate = document.getElementById("selectDate");
const selectDateValue = document.getElementById("selectDateValue");
const beforeClockInTime = document.getElementById("beforeClockInTime");
const beforeClockOutTime = document.getElementById("beforeClockOutTime");
const afterClockInTime = document.getElementById("afterClockInTime");
const afterClockOutTime = document.getElementById("afterClockOutTime");
const dateArea = document.getElementById("dateArea");
const beforeTimeArea = document.getElementById("beforeTimeArea");
const afterTimeArea = document.getElementById("afterTimeArea");
const errorEl = document.getElementById("error");
const layer = document.getElementById("layer");
const closeBtn = document.getElementById("closeBtn");

console.log("clockInTime: ", clockInTime);
console.log("clockOutTime: ", clockOutTime);
const inTime = new Date(Number(clockInTime));
const outTime = new Date(Number(clockOutTime));
console.log(outTime.getHours(),outTime.getMinutes());

selectDate.innerText = `${inTime.getFullYear()}年${inTime.getMonth() + 1}月${inTime.getDate()}日`;
selectDateValue.value = `${inTime.getFullYear()}-${String(inTime.getMonth() + 1).padStart(2, "0")}-${String(inTime.getDate()).padStart(2, "0")}`;

beforeClockInTime.value = `${String(inTime.getHours()).padStart(2, "0")}:${String(inTime.getMinutes()).padStart(2, "0")}`;
beforeClockOutTime.value = `${String(outTime.getHours()).padStart(2, "0")}:${String(outTime.getMinutes()).padStart(2, "0")}`;
afterClockInTime.value = `${String(inTime.getHours()).padStart(2, "0")}:${String(inTime.getMinutes()).padStart(2, "0")}`;
afterClockOutTime.value = `${String(outTime.getHours()).padStart(2, "0")}:${String(outTime.getMinutes()).padStart(2, "0")}`;

afterClockOutTime.addEventListener(("change"), () => {
  console.log(afterClockOutTime.value);
  console.log(afterClockInTime.value);
  if(afterClockOutTime.value < afterClockInTime.value){
    errorEl.textContent = "警告:日付を超えた労働時間を登録しようとしています。";
  }else{
    errorEl.textContent = "";
  }
})

editTimeRecordForm.addEventListener('submit', (e) => {
  e.preventDefault();
  console.log("実行");
  fetch("/DateTime/EditTimeRecordServlet", {
    method: "POST",
    body: JSON.stringify(shift),
  }).finally(() => {
    // alert("シフトの登録が完了しました。");
    layer.classList.add("is-open");
  });
});

dateArea.appendChild(selectDate);
dateArea.appendChild(selectDateValue);
beforeTimeArea.appendChild(beforeClockInTime);
beforeTimeArea.appendChild(beforeClockOutTime);
afterTimeArea.appendChild(afterClockInTime);
afterTimeArea.appendChild(afterClockOutTime);
