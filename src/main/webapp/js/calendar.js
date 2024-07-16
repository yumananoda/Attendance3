import { weeks } from "./const.js";

const calender = document.getElementById("calendar");
const total = document.getElementById("totalData");
let jsValue = document.getElementById("dataHolder").value;
jsValue = JSON.parse(jsValue);
console.log("jsValue:", jsValue);
let unixTime = jsValue[0].clockInTime;
let dateTime = new Date(unixTime * 1000);
console.log("unixtime変換後:", dateTime);

let jsValue2 = document.getElementById("shiftHolder").value;
jsValue2 = JSON.parse(jsValue2);
console.log("jsValue2:", jsValue2);

const currentTime = new Date();
let currentYear = currentTime.getFullYear();
let currentMonth = currentTime.getMonth() + 1;
let endDate = new Date(currentYear, currentMonth, 0);

function getDateAndDay() {
  while (calender.firstChild) {
    calender.removeChild(calender.firstChild);
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
  calender.appendChild(trEl);
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
    const shift_clockIn = document.createElement("td");
    const shift_clockOut = document.createElement("td");
    const shift_clock = document.createElement("td");
    const overTime = document.createElement("td");

    const day = new Date(currentYear, currentMonth - 1, i).getDay();
    console.log(i, day);

    console.log(weeks[day]);
    date.innerText = `${i}日`;
    week.innerText = weeks[day];

    let date_start = new Date(currentYear, currentMonth - 1, i, 0, 0, 0);
    let date_end = new Date(currentYear, currentMonth - 1, i, 23, 59, 59);
    date_start = date_start.getTime() / 1000;
    date_end = date_end.getTime() / 1000;
    const result = jsValue.find(({ clockInTime }) => {
      const clockInTimeInMillis = clockInTime / 1000;
      return (
        date_start <= clockInTimeInMillis && date_end >= clockInTimeInMillis
      );
    });
    console.log("result:");
    console.log(result);
    let workingTime = null;

    if (result !== undefined) {
      totalWorkingDays++;
      const input = new Date(result.clockInTime);
      const out = new Date(result.clockOutTime);

      clockIn.innerText = input.toLocaleTimeString();
      if (result.clockOutTime !== null) {
        clockOut.innerText = out.toLocaleTimeString();
      }

      workingTime = out - input;
      totalWorkingMilliseconds += workingTime;
      console.log("workingTime:", workingTime);
      let hoursWorked = workingTime / (1000 * 60 * 60);
      let clocklHours = Math.floor(hoursWorked);
      let clockMinutes = Math.floor((hoursWorked - clocklHours) * 60);

      console.log("out:");
      console.log(out);
      if (workingTime >= 0) {
        clock.innerText = `${clocklHours}時間 ${clockMinutes}分`;
        console.log(`稼働時間は ${clocklHours}時間 ${clockMinutes}分 です`);
      }
    }

    const findShift = jsValue2.find(({ shift_day }) => shift_day === day);
    if (findShift !== undefined) {
      console.log("findShift:");
      console.log(findShift);

      const { start_time, end_time } = findShift;
      shift_clockIn.innerText = start_time;
      shift_clockOut.innerText = end_time;

      let shiftStrat = new Date(`1970-01-01T${start_time}Z`);
      let shiftEnd = new Date(`1970-01-01T${end_time}Z`);

      let diffMilliseconds = shiftEnd - shiftStrat;

      let shiftHours = Math.floor(diffMilliseconds / (1000 * 60 * 60));
      const diffMilliseconds2 = diffMilliseconds % (1000 * 60 * 60);

      let shitMnutes = Math.floor(diffMilliseconds2 / (1000 * 60));

      shift_clock.innerText = `${shiftHours}時間 ${shitMnutes}分`;
      console.log(workingTime, diffMilliseconds);
      if (workingTime !== null && workingTime > diffMilliseconds) {
        let over = workingTime - diffMilliseconds;
        let hours = Math.floor(over / (1000 * 60 * 60));
        const over2 = over % (1000 * 60 * 60);
        let minutes = Math.floor(over2 / (1000 * 60));

        console.log(`残業時間は ${hours}時間 ${minutes}分 です`);
        overTime.innerText = `${hours}時間 ${minutes}分`;
        totalOverMilliseconds += over;
      }
    }

    col.appendChild(date);
    col.appendChild(week);
    col.appendChild(clockIn);
    col.appendChild(clockOut);
    col.appendChild(clock);
    col.appendChild(breakTime);
    col.appendChild(shift_clockIn);
    col.appendChild(shift_clockOut);
    col.appendChild(shift_clock);
    col.appendChild(overTime);
    calender.appendChild(col);
  }

  const total_tr1 = document.createElement("tr");
  const total_th1 = document.createElement("th");
  const total_td1 = document.createElement("td");
  total_th1.innerText = "合計勤務日数";
  total_td1.innerText = totalWorkingDays;
  total_tr1.appendChild(total_th1);
  total_tr1.appendChild(total_td1);
  total.appendChild(total_tr1);
  console.log(totalWorkingDays);

  const total_tr2 = document.createElement("tr");
  const total_th2 = document.createElement("th");
  const total_td2 = document.createElement("td");
  total_th2.innerText = "合計勤務時間";

  // 合計勤務時間と残業時間を関数でまとめたい
  // function TotalTimeCalculation(totalTimeMilliseconds){
  //   let totalHours = Math.floor(
  //     totalTimeMilliseconds / (1000 * 60 * 60)
  //   );
  //   let remainingMilliseconds = totalTimeMilliseconds % (1000 * 60 * 60);
  //   let totalMinutes = Math.floor(remainingMilliseconds / (1000 * 60));

  // }

  // 合計勤務時間を計算して表示
  let totalWorkingHours = Math.floor(
    totalWorkingMilliseconds / (1000 * 60 * 60)
  );
  let remainingMilliseconds = totalWorkingMilliseconds % (1000 * 60 * 60);
  let totalWorkingMinutes = Math.floor(remainingMilliseconds / (1000 * 60));

  total_td2.innerText = `${totalWorkingHours}時間 ${totalWorkingMinutes}分`;

  total_tr2.appendChild(total_th2);
  total_tr2.appendChild(total_td2);
  total.appendChild(total_tr2);
  console.log();

  const total_tr3 = document.createElement("tr");
  const total_th3 = document.createElement("th");
  const total_td3 = document.createElement("td");
  total_th3.innerText = "合計残業時間";
  console.log(totalOverMilliseconds);
  let totalOverHours = Math.floor(totalOverMilliseconds / (1000 * 60 * 60));
  let remainingMilliseconds2 = totalOverMilliseconds % (1000 * 60 * 60);
  let totalOverMinutes = Math.floor(remainingMilliseconds2 / (1000 * 60));

  total_td3.innerText = `${totalOverHours}時間 ${totalOverMinutes}分`;

  total_tr3.appendChild(total_th3);
  total_tr3.appendChild(total_td3);
  total.appendChild(total_tr3);
  console.log();
}

document.querySelector("#year").innerHTML = currentYear;
document.querySelector("#month").innerHTML = currentMonth;

document.querySelector(".prev").addEventListener("click", function () {
  if (currentMonth === 1) {
    currentMonth = 12;
    currentYear--;
  } else {
    currentMonth--;
  }
  document.querySelector("#year").innerHTML = currentYear;
  document.querySelector("#month").innerHTML = currentMonth;
  endDate = new Date(currentYear, currentMonth, 0);
  getDateAndDay();
});

document.querySelector(".next").addEventListener("click", function () {
  if (currentMonth === 12) {
    currentMonth = 1;
    currentYear++;
  } else {
    currentMonth++;
  }
  document.querySelector("#year").innerHTML = currentYear;
  document.querySelector("#month").innerHTML = currentMonth;
  endDate = new Date(currentYear, currentMonth, 0);
  getDateAndDay();
});
getDateAndDay();
