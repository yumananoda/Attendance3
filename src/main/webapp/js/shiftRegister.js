import { DAYS, DAY_TEXTS } from "./const.js";

const selectWeekEl = document.getElementById("selectWeek");
const DispDailyEl = document.getElementById("DispDailyEl");
const registerBtn = document.getElementById("registerBtn");
const layer = document.getElementById("layer");
const closeBtn = document.getElementById("closeBtn");
const errorEl = document.getElementById("error");
const employeeCD = document.getElementById("employeeCD").value;
const shiftEl = document.getElementById("shift");
const shift = JSON.parse(shiftEl.value);
console.log("employeeCD: ", employeeCD);
console.log("shift: ", shift);

let currentYear = null;
let DispStartMonth = null;
let durationIndex = null;

const getCurrent = (currentYear, DispStartMonth) => {
  document.getElementById("year").innerHTML = `${currentYear}年`;
  document.getElementById("month").innerHTML = `${DispStartMonth}月`;
}

const dispSelectDay = () => {
  while (selectWeekEl.firstChild) {
    selectWeekEl.removeChild(selectWeekEl.firstChild);
  }
  let dispDuration = currentYear + String(DispStartMonth).padStart(2, "0");
  console.log(dispDuration);
  for (const day of Object.values(DAYS)) {
    console.log("day:", day);
    const dayBtn = document.createElement("button");
    dayBtn.innerText = DAY_TEXTS[day];
    dayBtn.value = day;
    selectWeekEl.appendChild(dayBtn);
    
    durationIndex = shift.findIndex(({ shift_duration }) => shift_duration === Number(dispDuration));
    if (durationIndex !== -1) {
      if (shift.some(({ shift_day }) => shift_day === day)) {
        dayBtn.classList.add("gray");
      }
    }

    dayBtn.addEventListener('click', (e) => {
      const selectDayValue = e.target.value;
      console.log(selectDayValue);

      if (durationIndex !== -1) {
        const shiftIndex = shift.findIndex(({ shift_day }) => shift_day === Number(selectDayValue));
        console.log("shiftIndex: ", shiftIndex);
        if (shiftIndex === -1) {
          shift.push({
            employeeCD: Number(employeeCD),
            shift_duration: Number(dispDuration),
            shift_day: Number(selectDayValue),
            start_time: "",
            end_time: "",
          });
        } else {
          shift.splice(shiftIndex, 1);
        }
      }
      dayBtn.classList.toggle("gray");
      dispDailyTime();
    })
  }
}

const dispDailyTime = () => {
  while (DispDailyEl.firstChild) {
    DispDailyEl.removeChild(DispDailyEl.firstChild);
  }
  shift.sort((first, second) => {
    if (first.shift_day > second.shift_day) {
      return 1;
    } else if (first.shift_day < second.shift_day) {
      return -1;
    } else {
      return 0;
    }
  });
  console.log("shift: ", shift);
  console.log(durationIndex);
  if (durationIndex !== -1) {
    for (let i = 0; i < shift.length; i++) {
      const { shift_day, start_time, end_time } = shift[i];
      shift[i].start_time = start_time.slice(0, 5);
      shift[i].end_time = end_time.slice(0, 5);
      console.log(shift);
      const DailyEl = document.createElement("div");
      DailyEl.id = shift_day;
      const dayTextEl = document.createElement("p");
      dayTextEl.innerText = DAY_TEXTS[shift_day];

      const startTimeBox = document.createElement("input");
      startTimeBox.type = "time";
      startTimeBox.classList.add("start_time");
      startTimeBox.value = start_time;

      const endTimeBox = document.createElement("input");
      endTimeBox.type = "time";
      endTimeBox.classList.add("end_time");
      endTimeBox.value = end_time;

      startTimeBox.addEventListener("change", (e) => {
        console.log(Number(startTimeBox.id));
        console.log("e: ", e.target.value);
        const index = shift.findIndex(({shift_day}) => shift_day === Number(DailyEl.id));
        console.log(index);
        if (index !== -1) {
          shift[index].start_time = e.target.value;
        }
        console.log(shift)
      })

      endTimeBox.addEventListener("change", (e) => {
        console.log("e: ", e.target.value);
        const index = shift.findIndex(({shift_day}) => shift_day === Number(DailyEl.id));
        if (index !== -1) {
          shift[index].end_time = e.target.value;
        }
        console.log(shift)
      });
      DailyEl.appendChild(dayTextEl);
      DailyEl.appendChild(startTimeBox);
      DailyEl.appendChild(endTimeBox);
      DispDailyEl.appendChild(DailyEl);
    }
  }
}

window.addEventListener("DOMContentLoaded", () => {
  const currentDate = new Date();
  currentYear = currentDate.getFullYear();
  let currentMonth = currentDate.getMonth() + 1;
  if(currentMonth >= 4 && currentMonth < 10){
    DispStartMonth = 4;
  }else{
    DispStartMonth = 10;
  }
  // endDate = new Date(currentYear, DispStartMonth, 0);
  getCurrent(currentYear, DispStartMonth);
  dispSelectDay();
  dispDailyTime();

});

document.querySelector(".prev").addEventListener("click", () => {
  if (DispStartMonth === 4) {
    DispStartMonth = 10;
    currentYear--;
  } else {
    DispStartMonth -= 6 ;
  }
  getCurrent(currentYear, DispStartMonth);
  dispSelectDay();
  dispDailyTime();
});


document.querySelector(".next").addEventListener("click", () => {
  if (DispStartMonth === 10) {
    DispStartMonth = 4;
    currentYear++;
  } else {
    DispStartMonth += 6;
  }
  getCurrent(currentYear, DispStartMonth);
  dispSelectDay();
  dispDailyTime();
});

registerBtn.addEventListener('click', () => {
  console.log(shift);
  if (
    shift.some(
      ({ start_time, end_time }) => start_time === "" || end_time === "")
  ) {
    errorEl.innerText = "入力されていない項目があります。";
  } else {
    errorEl.innerText = "";
    fetch("/DateTime/ShiftRegisterServlet", {
      method: "POST",
      body: JSON.stringify(shift),
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
        layer.classList.add("is-open");
    })
  }
});

closeBtn.addEventListener('click', () => {
  layer.classList.remove('is-open'); 
})
document.body.appendChild(registerBtn);
