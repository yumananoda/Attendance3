import { DAYS, DAY_TEXTS } from "./const.js";

const employeeCDEl = document.getElementById("employeeCD");
const employeeCD = employeeCDEl.value;
const shiftEl = document.getElementById("shift");
const selectWeekEl = document.getElementById("selectWeek");
const inputTimeGroupEl = document.getElementById("inputTimeGroup");

const shift = JSON.parse(shiftEl.value);
console.log("shift: ", shift);

window.addEventListener("DOMContentLoaded", () => {
  console.log("DOMContentLoaded");
  dispSelectDay();
  showDayElements();
});

function dispSelectDay() {
  for (const day of Object.values(DAYS)) {
    console.log("day:", day);
    const div = document.createElement("div");
    const daybtn = document.createElement("button");
    daybtn.innerText = DAY_TEXTS[day];
    daybtn.value = day;
    div.appendChild(daybtn);
    selectWeekEl.appendChild(div);
    if (shift.some(({ shift_day }) => shift_day === day)) {
      daybtn.classList.add("gray");
    }

    daybtn.addEventListener("click", function (event) {
      const selectValue = event.target.value;

      const index = shift.findIndex(
        ({ shift_day }) => shift_day === Number(selectValue)
      );
      console.log("index: ", index);
      if (index === -1) {
        shift.push({
          employeeCD: Number(employeeCD),
          shift_day: Number(selectValue),
          start_time: "",
          end_time: "",
        });
      } else {
        shift.splice(index, 1);
      }

      daybtn.classList.toggle("gray");
      console.log("shift: ", shift);
      showDayElements();
    });
  }
}

function showDayElements() {
  while (inputTimeGroupEl.firstChild) {
    inputTimeGroupEl.removeChild(inputTimeGroupEl.firstChild);
  }
  shift.sort(function (first, second) {
    if (first.shift_day > second.shift_day) {
      return 1;
    } else if (first.shift_day < second.shift_day) {
      return -1;
    } else {
      return 0;
    }
  });
  console.log("shift: ", shift);
  for (let i = 0; i < shift.length; i++) {
    const { shift_day, start_time, end_time } = shift[i];
    const dayTextEl = document.createElement("div");
    dayTextEl.id = `item_${shift_day}`;
    const dayTextPr = document.createElement("p");
    dayTextPr.innerText = DAY_TEXTS[shift_day];

    const startTimeBox = document.createElement("input");
    startTimeBox.value = start_time;
    startTimeBox.type = "time";
    startTimeBox.addEventListener("change", (event) => {
      console.log("e: ", event.target.value);
      const index = shift.findIndex((x) => x.shift_day === shift_day);
      if (index !== -1) {
        shift[index].start_time = event.target.value;
      }
    });
    startTimeBox.classList.add("start_time");
    const endTimeBox = document.createElement("input");
    endTimeBox.value = end_time;
    endTimeBox.type = "time";
    endTimeBox.classList.add("end_time");
    endTimeBox.addEventListener("change", (event) => {
      console.log("e: ", event.target.value);
      const index = shift.findIndex((x) => x.shift_day === shift_day);
      if (index !== -1) {
        shift[index].end_time = event.target.value;
      }
    });
    dayTextEl.appendChild(dayTextPr);
    dayTextEl.appendChild(startTimeBox);
    dayTextEl.appendChild(endTimeBox);

    inputTimeGroupEl.appendChild(dayTextEl);
  }
}

const btn = document.createElement("button");
btn.innerText = "送信";
btn.addEventListener("click", function () {
  console.log("送信しました");
  console.log(shift);
  if (
    shift.some(
      ({ start_time, end_time }) => start_time === "" || end_time === ""
    )
  ) {
    console.log("入力されていない項目があります。");
  } else {
    console.log("実行");
    fetch("/DateTime12/ShiftRegisterServlet", {
      method: "POST",
      body: JSON.stringify(shift),
    }).finally(() => {
      alert("シフトの登録が完了しました。");
    });
  }
});
document.body.appendChild(btn);
