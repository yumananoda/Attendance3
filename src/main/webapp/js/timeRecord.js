import { WEEKS } from "./const.js";

const employeeCD = document.getElementById("employeeCD").value;
const name = document.getElementById("name").value;
const timeRecordArea = document.getElementById("timeRecordArea");
const totalDataArea = document.getElementById("totalDataArea");

let timeRecordData = document.getElementById("timeRecordHolder").value;
timeRecordData = JSON.parse(timeRecordData);
console.log("timeRecordData:", timeRecordData);
let shiftData = document.getElementById("shiftHolder").value;
shiftData = JSON.parse(shiftData);
console.log("shiftData:", shiftData);
let holidayData = document.getElementById("holidayHolder").value;
holidayData = JSON.parse(holidayData);
console.log("holidayData:", holidayData);

const currentDate = new Date();
let currentYear = currentDate.getFullYear();
let currentMonth = currentDate.getMonth() + 1;
let endDate = new Date(currentYear, currentMonth, 0);
document.getElementById("year").innerHTML = currentYear;
document.getElementById("month").innerHTML = currentMonth;

const getDateAndDay = () => {
  while (timeRecordArea.firstChild) {
    timeRecordArea.removeChild(timeRecordArea.firstChild);
  }
  const trEl = document.createElement("tr");
  const th1 = document.createElement("th");
  const th2 = document.createElement("th");
  const th3 = document.createElement("th");
  const th4 = document.createElement("th");
  const th5 = document.createElement("th");
  const th6 = document.createElement("th");
  const th7 = document.createElement("th");
  const th8 = document.createElement("th");
  const th9 = document.createElement("th");
  const th10 = document.createElement("th");
  const th11 = document.createElement("th");

  th1.innerText = "日";
  th2.innerText = "曜日";
  th3.innerText = "出勤時間";
  th4.innerText = "退勤時間";
  th5.innerText = "稼働時間";
  th6.innerText = "休憩時間";
  th7.innerText = "出勤予定時間";
  th8.innerText = "退勤予定時間";
  th9.innerText = "稼働予定時間";
  th10.innerText = "残業時間";
  th11.innerText = "処理";
  trEl.appendChild(th1);
  trEl.appendChild(th2);
  trEl.appendChild(th3);
  trEl.appendChild(th4);
  trEl.appendChild(th5);
  trEl.appendChild(th6);
  trEl.appendChild(th7);
  trEl.appendChild(th8);
  trEl.appendChild(th9);
  trEl.appendChild(th10);
  trEl.appendChild(th11);
  timeRecordArea.appendChild(trEl);
  let totalWorkingDays = 0;
  let totalWorkingMilliseconds = 0;
  let totalOverMilliseconds = 0;
  for (let i = 1; i <= endDate.getDate(); i++) {
    const col = document.createElement("tr");
    const date = document.createElement("td");
    const week = document.createElement("td");
    const clockIn = document.createElement("td");
    const clockOut = document.createElement("td");
    const clock = document.createElement("td");
    const breakTime = document.createElement("td");
    const shiftClockIn = document.createElement("td");
    const shiftClockOut = document.createElement("td");
    const shiftClock = document.createElement("td");
    const overTime = document.createElement("td");
    const edit = document.createElement("td");
    const editHref = document.createElement("a");

    const day = new Date(currentYear, currentMonth - 1, i).getDay();
    console.log(i, day);

    console.log(WEEKS[day]);
    date.innerText = `${i}日`;
    week.innerText = WEEKS[day];

    let dateStart = new Date(currentYear, currentMonth - 1, i, 0, 0, 0);
    let dateEnd = new Date(currentYear, currentMonth - 1, i, 23, 59, 59);
    dateStart = dateStart.getTime();
    dateEnd = dateEnd.getTime();
    console.log(dateStart, dateEnd);
    
    const findWorkingDate = timeRecordData.find(({ clockInTime }) => {
      return (
        dateStart <= clockInTime && dateEnd >= clockInTime
      );
    });
    console.log("findWorkingDate:", findWorkingDate);
    let workingTime = null;
    let estimatedWorkingTime = null;

    if (findWorkingDate !== undefined) {
      totalWorkingDays++;
      const inTime = new Date(findWorkingDate.clockInTime);
      const outTime = new Date(findWorkingDate.clockOutTime);
      console.log("outTime: ", outTime);
      console.log(outTime.getHours(), outTime.getMinutes(), outTime.getSeconds());
      // const outTimeDetail = outTime.getHours(), outTime.getMinutes(), outTime.getSeconds());

      clockIn.innerText = `${inTime.getHours()}:${String(inTime.getMinutes()).padStart(2, "0")}:${String(inTime.getSeconds()).padStart(2, "0")}`;
      if (findWorkingDate.clockOutTime !== null) {
        clockOut.innerText = `${String(outTime.getHours()).padStart(2, "0")}:${String(outTime.getMinutes()).padStart(2, "0")}:${String(outTime.getSeconds()).padStart(2, "0")}`;
      }

      workingTime = outTime - inTime;
      totalWorkingMilliseconds += workingTime;
      console.log("workingTime:", workingTime);
      let hoursWorked = workingTime / (1000 * 60 * 60);
      let clocklHours = Math.floor(hoursWorked);
      let clockMinutes = Math.floor((hoursWorked - clocklHours) * 60);
      if (workingTime >= 0) {
        clock.innerText = `${clocklHours}時間 ${String(clockMinutes).padStart(2, "0")}分`;
        console.log(`稼働時間は ${clocklHours}時間 ${clockMinutes}分 です`);
      }
    }

    const findShift = shiftData.find(({ shift_day }) => shift_day === day);
    if (findShift !== undefined) {
      console.log("findShift:", findShift);
      
      const { start_time, end_time } = findShift;
      shiftClockIn.innerText = start_time;
      shiftClockOut.innerText = end_time;

      let shiftStrat = new Date(`1970-01-01T${start_time}Z`).getTime();
      let shiftEnd = new Date(`1970-01-01T${end_time}Z`).getTime();

      estimatedWorkingTime = shiftEnd - shiftStrat;
      console.log(estimatedWorkingTime);

      let hours = estimatedWorkingTime / (1000 * 60 * 60);
      let clocklHours = Math.floor(hours);
      let clockMinutes = Math.floor((hours - clocklHours) * 60);
      if (estimatedWorkingTime >= 0) {
        shiftClock.innerText = `${clocklHours}時間 ${String(clockMinutes).padStart(2, "0")}分`;
        console.log(`稼働予定時間は ${clocklHours}時間 ${clockMinutes}分 です`);
      }
    }
    console.log(workingTime, estimatedWorkingTime);
    if (workingTime !== null && workingTime > estimatedWorkingTime) {
      let over = workingTime - estimatedWorkingTime;
      let hours = over / (1000 * 60 * 60);
      let overHours = Math.floor(hours);
      let overMinutes = Math.floor((hours - overHours) * 60);
      overTime.innerText = `${overHours}時間 ${String(overMinutes).padStart(2, "0")}分`;
      console.log(`残業時間は ${overHours}時間 ${overMinutes}分 です`);
      totalOverMilliseconds += over;
    }

    const findHolidayDate = holidayData.find(({ applicationStartDate }) => {
      return (
        dateStart <= applicationStartDate && dateEnd >= applicationStartDate
      );
    });
    console.log("findHolidayDate:", findHolidayDate);
    if (findHolidayDate !== undefined) {
      const holidatStartDate= new Date(findHolidayDate.applicationStartDate);
      const holidatEndDate = new Date(findHolidayDate.applicationEndDate);
      console.log("holidatStartDate: ", holidatStartDate);
      
      // clockIn.innerText = `${holidatStartDate.getHours()}:${String(holidatStartDate.getMinutes()).padStart(2, "0")}:${String(holidatStartDate.getSeconds()).padStart(2, "0")}`;
      // clockOut.innerText = `${String(holidatEndDate.getHours()).padStart(2, "0")}:${String(holidatEndDate.getMinutes()).padStart(2, "0")}:${String(holidatEndDate.getSeconds()).padStart(2, "0")}`;

      clockIn.innerText = "有給";
      // workingTime = outTime - inTime;
      // totalWorkingMilliseconds += workingTime;
      // console.log("workingTime:", workingTime);
      // let hoursWorked = workingTime / (1000 * 60 * 60);
      // let clocklHours = Math.floor(hoursWorked);
      // let clockMinutes = Math.floor((hoursWorked - clocklHours) * 60);
      // if (workingTime >= 0) {
      //   clock.innerText = `${clocklHours}時間 ${String(clockMinutes).padStart(2, "0")}分`;
      //   console.log(`稼働時間は ${clocklHours}時間 ${clockMinutes}分 です`);
      // }
    }
    
    col.appendChild(date);
    col.appendChild(week);
    col.appendChild(clockIn);
    col.appendChild(clockOut);
    col.appendChild(clock);
    col.appendChild(breakTime);
    col.appendChild(shiftClockIn);
    col.appendChild(shiftClockOut);
    col.appendChild(shiftClock);
    col.appendChild(overTime);

    if (findWorkingDate) {
      editHref.innerText = "実績変更";
      const { recordCD, clockInTime, clockOutTime } = findWorkingDate;
      editHref.href = `/DateTime/DispEditTimeRecordServlet?employeeCD=${employeeCD}&clockInTime=${clockInTime}&clockOutTime=${clockOutTime}&recordCD=${recordCD}&name=${name}`;
      edit.appendChild(editHref);
      col.appendChild(edit);
    }
    timeRecordArea.appendChild(col);
  }

  const total_tr1 = document.createElement("tr");
  const total_th1 = document.createElement("th");
  const total_td1 = document.createElement("td");
  total_th1.innerText = "合計勤務日数";
  total_td1.innerText = totalWorkingDays;
  total_tr1.appendChild(total_th1);
  total_tr1.appendChild(total_td1);
  totalDataArea.appendChild(total_tr1);
  console.log("totalWorkingDays:",totalWorkingDays);

  const total_tr2 = document.createElement("tr");
  const total_th2 = document.createElement("th");
  const total_td2 = document.createElement("td");
  total_th2.innerText = "合計勤務時間";
  let totalWorkingHours = totalOverMilliseconds / (1000 * 60 * 60);
  let totalWorkingHours2 = Math.floor(totalWorkingHours);
  let totalWorkingMinutes = Math.floor((totalWorkingHours - totalWorkingHours2) * 60);
  total_td2.innerText = `${totalWorkingHours2}時間 ${totalWorkingMinutes}分`;
  total_tr2.appendChild(total_th2);
  total_tr2.appendChild(total_td2);
  totalDataArea.appendChild(total_tr2);
  console.log();

  const total_tr3 = document.createElement("tr");
  const total_th3 = document.createElement("th");
  const total_td3 = document.createElement("td");
  total_th3.innerText = "合計残業時間";
  console.log(totalOverMilliseconds);
  let totalOverHours = totalOverMilliseconds / (1000 * 60 * 60);
  let totalOverHours2 = Math.floor(totalOverHours);
  let totalOverMinutes = Math.floor((totalOverHours - totalOverHours2) * 60);
  total_td3.innerText = `${totalOverHours2}時間 ${totalOverMinutes}分`;
  total_tr3.appendChild(total_th3);
  total_tr3.appendChild(total_td3);
  totalDataArea.appendChild(total_tr3);
  console.log();
}

window.addEventListener("DOMContentLoaded", () => {
  getDateAndDay();
})

document.querySelector(".prev").addEventListener("click", () => {
  if (currentMonth === 1) {
    currentMonth = 12;
    currentYear--;
  } else {
    currentMonth--;
  }
  document.getElementById("year").innerHTML = currentYear;
  document.getElementById("month").innerHTML = currentMonth;
  endDate = new Date(currentYear, currentMonth, 0);
  getDateAndDay();
});


document.querySelector(".next").addEventListener("click", () => {
  if (currentMonth === 12) {
    currentMonth = 1;
    currentYear++;
  } else {
    currentMonth++;
  }
  document.getElementById("year").innerHTML = currentYear;
  document.getElementById("month").innerHTML = currentMonth;
  endDate = new Date(currentYear, currentMonth, 0);
  getDateAndDay();
});