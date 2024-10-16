import { DAYS, DAY_TEXTS } from "./const.js";

const selectWeekEl = document.getElementById("selectWeek");
const DispDailyEl = document.getElementById("DispDailyEl");
const layer = document.getElementById("layer");
const errorEl = document.getElementById("error");
const btnArea = document.getElementById("btnArea");
const closeBtn = document.getElementById("closeBtn");
const registerBtn = document.getElementById("registerBtn");
const resetBtn = document.getElementById("resetBtn");
const employeeCD = document.getElementById("employeeCD").value;
const shiftEl = document.getElementById("shift");
const shift = JSON.parse(shiftEl.value);
const earliestTimeString = "09:30";
const latestTimeString = "21:30";
console.log("employeeCD: ", employeeCD);
console.log("shift: ", shift);

let currentYear = null;
let DispStartMonth = null;
let durationIndex = null;
let dispDuration = null;

const getCurrent = (currentYear, DispStartMonth) => {
  document.getElementById("year").innerHTML = `${currentYear}年`;
  document.getElementById("month").innerHTML = `${DispStartMonth}月`;
  dispDuration = currentYear + String(DispStartMonth).padStart(2, "0");
};

const convertToTime = (timeString) => {
  const [hours, minutes] = timeString.split(":").map(Number);
  const date = new Date();
  date.setHours(hours);
  date.setMinutes(minutes);
  return date;
};

const dispSelectDay = () => {
  while (selectWeekEl.firstChild) {
    selectWeekEl.removeChild(selectWeekEl.firstChild);
  }

  for (const day of Object.values(DAYS)) {
    console.log("day:", day);
    const dayBtn = document.createElement("button");
    dayBtn.innerText = DAY_TEXTS[day];
    dayBtn.value = day;
    selectWeekEl.appendChild(dayBtn);

    durationIndex = shift.findIndex(
      ({ shift_duration, shift_day }) =>
        shift_duration === Number(dispDuration) && shift_day === day
    );
    console.log("durationIndex: ", durationIndex);
    if (durationIndex !== -1) {
      dayBtn.classList.add("gray");
    }

    dayBtn.addEventListener("click", (e) => {
      const selectDayValue = e.target.value;
      console.log(selectDayValue);
      const shiftIndex = shift.findIndex(
        ({ shift_day, shift_duration }) =>
          shift_day === Number(selectDayValue) &&
          shift_duration === Number(dispDuration)
      );
      if (shiftIndex !== -1) {
        shift.splice(shiftIndex, 1);
      } else {
        shift.push({
          employeeCD: Number(employeeCD),
          shift_duration: Number(dispDuration),
          shift_day: Number(selectDayValue),
          start_time: "",
          end_time: "",
        });
      }
      dayBtn.classList.toggle("gray");

      console.log(shift);
      dispDailyTime();
    });
  }
};

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
  console.log(Number(dispDuration));

  shift.forEach((item) => {
    let { start_time, end_time } = item;
    item.start_time = start_time.slice(0, 5);
    item.end_time = end_time.slice(0, 5);
  });

  let durationShift = shift.filter(
    ({ shift_duration }) => shift_duration === Number(dispDuration)
  );
  console.log(durationShift);
  console.log(shift);

  const checkPrescribed = () => {
    let prescribedMinutes = 0;
    durationShift.forEach((item) => {
      const { start_time, end_time } = item;
      const start_time2 = start_time.replace(":", "");
      const end_time2 = end_time.replace(":", "");
      console.log(start_time2, end_time2);
      let intervalMinutes = end_time2 - start_time2;
      if (intervalMinutes >= 600) {
        intervalMinutes -= 100;
      }
      prescribedMinutes += intervalMinutes;

      console.log("1日:", intervalMinutes);
      console.log("1週間合計:", prescribedMinutes);
      if (prescribedMinutes > 4000) {
        console.log("法定労働時間超過");
        errorEl.innerText = "1週間の法定労働時間を超えています。";
        registerBtn.disabled = true;
      }
    });
  };

  for (let i = 0; i < durationShift.length; i++) {
    const { shift_day, start_time, end_time } = durationShift[i];
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
      console.log("e: ", e.target.value);
      const index = durationShift.findIndex(
        ({ shift_day }) => shift_day === Number(DailyEl.id)
      );
      console.log(index);
      if (index !== -1) {
        durationShift[index].start_time = e.target.value;
      }

      const timeString = startTimeBox.value;
      // const [hours, minutes] = timeString.split(":").map(Number);
      // console.log(hours);
      const inputTime = convertToTime(timeString);
      const earliestTime = convertToTime(earliestTimeString);
      const latestTime = convertToTime(latestTimeString);
      console.log(earliestTimeString);
      console.log(latestTimeString);
      if (inputTime < earliestTime || inputTime > latestTime) {
        errorEl.innerText = "営業時間外です。";
        registerBtn.disabled = true;
      } else {
        errorEl.innerText = "";
        registerBtn.disabled = false;
      }
      console.log(typeof startTimeBox.value);
      console.log(durationShift);
      console.log(shift);
      checkPrescribed();
    });

    endTimeBox.addEventListener("change", (e) => {
      console.log("e: ", e.target.value);
      const index = durationShift.findIndex(
        ({ shift_day }) => shift_day === Number(DailyEl.id)
      );
      if (index !== -1) {
        durationShift[index].end_time = e.target.value;
      }
      console.log(durationShift);
      console.log(shift);
      checkPrescribed();
    });
    DailyEl.appendChild(dayTextEl);
    DailyEl.appendChild(startTimeBox);
    DailyEl.appendChild(endTimeBox);
    DispDailyEl.appendChild(DailyEl);
  }

  console.log(DispDailyEl.children.length);
  if (DispDailyEl.children.length !== 0) {
    btnArea.classList.add("is-open");
  } else {
    btnArea.classList.remove("is-open");
  }
  checkPrescribed();
};

window.addEventListener("DOMContentLoaded", () => {
  const currentDate = new Date();
  currentYear = currentDate.getFullYear();
  let currentMonth = currentDate.getMonth() + 1;
  if (currentMonth >= 4 && currentMonth < 10) {
    DispStartMonth = 4;
  } else {
    DispStartMonth = 10;
  }
  getCurrent(currentYear, DispStartMonth);
  dispSelectDay();
  dispDailyTime();
});

document.querySelector(".prev").addEventListener("click", () => {
  if (DispStartMonth === 4) {
    DispStartMonth = 10;
    currentYear--;
  } else {
    DispStartMonth -= 6;
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

resetBtn.addEventListener("click", () => {
  console.log("aaa");
  window.location.href = `DispShiftRegisterServlet?employeeCD=${employeeCD}`;
});

registerBtn.addEventListener("click", () => {
  console.log(shift);
  if (
    shift.some(
      ({ start_time, end_time }) => start_time === "" || end_time === ""
    )
  ) {
    errorEl.innerText = "入力されていない項目があります。";
  } else {
    errorEl.innerText = "";
    fetch("/DateTime/ShiftRegisterServlet", {
      method: "POST",
      body: JSON.stringify(shift),
    }).then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      layer.classList.add("is-open");
    });
  }
});

closeBtn.addEventListener("click", () => {
  layer.classList.remove("is-open");
});
