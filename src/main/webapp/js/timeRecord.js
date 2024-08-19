import { WEEKS } from "./const.js";

const employeeCD = document.getElementById("employeeCD").value;
const name = document.getElementById("name").value;
const timeRecordArea = document.getElementById("timeRecordArea");
const totalDataArea = document.getElementById("totalDataArea");
const prescribedArea = document.getElementById("prescribedArea");

let timeRecordData = document.getElementById("timeRecordHolder").value;
timeRecordData = JSON.parse(timeRecordData);
console.log("timeRecordData:", timeRecordData);
let breakData = document.getElementById("breaksHolder").value;
breakData = JSON.parse(breakData);
console.log("breakData:", breakData);
let shiftData = document.getElementById("shiftHolder").value;
shiftData = JSON.parse(shiftData);
console.log("shiftData:", shiftData);
let holidayData = document.getElementById("holidayHolder").value;
holidayData = JSON.parse(holidayData);
console.log("holidayData:", holidayData);

let currentYear = null;
let currentMonth = null;
let DispStartYear = null;
let DispStartMonth = null;
let dispDuration = null;
let endDate = null;

const getCurrent = (currentYear, currentMonth) => {
  document.getElementById("year").innerHTML = `${currentYear}年`;
  document.getElementById("month").innerHTML = `${currentMonth}月`;
  endDate = new Date(currentYear, currentMonth, 0);
}

const getDateAndDay = () => {
  while (timeRecordArea.firstChild) {
    timeRecordArea.removeChild(timeRecordArea.firstChild);
  }
  while (totalDataArea.firstChild) {
    totalDataArea.removeChild(totalDataArea.firstChild);
  }
  while (prescribedArea.firstChild) {
    prescribedArea.removeChild(prescribedArea.firstChild);
  }
  let totalWorkingDays = 0;
  let totalHolidayMilliseconds = 0;
  let totalWorkingMillisecondsOfWeeks = 0;
  let totalWorkingMilliseconds = 0;
  let totalOverMilliseconds = 0;
  let prescribedDays = 0;
  let prescribedMilliseconds = 0;
  let HolidayTimeFlag = "false";
  dispDuration = DispStartYear + String(DispStartMonth).padStart(2, "0");
  
  
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
  const th12 = document.createElement("th");
  
  th1.innerText = "日";
  th2.innerText = "曜日";
  th3.innerText = "出勤時間";
  th4.innerText = "退勤時間";
  th5.innerText = "稼働時間";
  th6.innerText = "休憩時間";
  th7.innerText = "出勤予定時間";
  th8.innerText = "退勤予定時間";
  th9.innerText = "所定労働時間";
  th10.innerText = "残業時間";
  th11.innerText = "処理";
  th12.innerText = "備考";
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
  trEl.appendChild(th11);
  trEl.appendChild(th12);
  timeRecordArea.appendChild(trEl);
  
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
    const note = document.createElement("td");

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

    const findBreakData = breakData.find(({ breakStartTime }) => {
      return (
        dateStart <= breakStartTime && dateEnd >= breakStartTime
      );
    });
    console.log("findBreakData:", findBreakData);
    let breakingTime = null;
    let breakMinutes=null;

    if (findBreakData !== undefined) {
      const {breakStartTime, breakEndTime} = findBreakData;
      const inTime = new Date(breakStartTime);
      const outTime = new Date(breakEndTime);
      console.log(inTime, outTime);
      console.log(outTime - inTime);

      breakingTime = outTime - inTime;
      console.log("breakingTime:", breakingTime);
      breakMinutes = breakingTime / (1000 * 60);
      breakMinutes = Math.floor(breakMinutes);
      if (breakingTime >= 0) {        
        breakTime.innerText = `${String(breakMinutes).padStart(2, "0")}分`;
        console.log(`休憩時間は ${breakMinutes}分 です`);
      }
    }
    console.log(breakMinutes);
    
    const findWorkingDate = timeRecordData.find(({ clockInTime }) => {
      return (
        dateStart <= clockInTime && dateEnd >= clockInTime
      );
    });
    console.log("findWorkingDate:", findWorkingDate);
    let includedWorkingTime = null;
    let workingTime = null;
    let workingHours;
    let estimatedWorkingTime = null;

    if (findWorkingDate !== undefined) {
        const {clockInTime, clockOutTime} = findWorkingDate;
        totalWorkingDays++;
        const inTime = new Date(clockInTime);
        const outTime = new Date(clockOutTime);
        console.log("outTime: ", outTime);
        console.log(outTime.getHours(), outTime.getMinutes(), outTime.getSeconds());
        // const outTimeDetail = outTime.getHours(), outTime.getMinutes(), outTime.getSeconds());

        clockIn.innerText = `${inTime.getHours()}:${String(inTime.getMinutes()).padStart(2, "0")}:${String(inTime.getSeconds()).padStart(2, "0")}`;
        if (findWorkingDate.clockOutTime !== null) {
          clockOut.innerText = `${String(outTime.getHours()).padStart(2, "0")}:${String(outTime.getMinutes()).padStart(2, "0")}:${String(outTime.getSeconds()).padStart(2, "0")}`;
      }

      includedWorkingTime = outTime - inTime;
      console.log("includedWorkingTime:", includedWorkingTime);
      workingTime = includedWorkingTime - breakingTime;
      workingHours = workingTime / (1000 * 60 * 60);
      let workingHours2 = Math.floor(workingHours);
      let workingMinutes = Math.floor((workingHours - workingHours2) * 60);
      totalWorkingMilliseconds += workingTime;
      totalWorkingMillisecondsOfWeeks += workingTime;
      console.log(totalWorkingMillisecondsOfWeeks);
      if (includedWorkingTime >= 0) {
        clock.innerText = `${workingHours2}時間 ${String(workingMinutes).padStart(2, "0")}分`;
        console.log(`稼働時間は ${workingHours2}時間 ${workingMinutes}分 です`);
      }
    }else{
      breakTime.innerText = "";
      
    }
    
    
    if(workingHours > 8){
      console.log(workingHours)
      note.innerText = "労働基準法で定められている1日の労働時間を超えています。"

    }else if(totalWorkingMillisecondsOfWeeks > 144000000){
      console.log(totalWorkingMillisecondsOfWeeks)
      note.innerText = "労働基準法で定められている一週間の労働時間を超えています。"

    }else if(workingHours >= 8 && breakMinutes < 60)  {
      console.log(workingHours)
      console.log(breakMinutes)
      console.log("適切な休憩時間を取得できていません。")
      note.innerText = "適切な休憩時間を取得できていません。";

    }else if(workingHours >= 6 && breakMinutes < 45)  {
      console.log(workingHours)
      console.log(breakMinutes)
      console.log("適切な休憩時間を取得できていません。")
      note.innerText = "適切な休憩時間を取得できていません。";
      
    }else{
      console.log(workingHours)
      console.log(breakMinutes)
      console.log("適切な休憩時間を取得しています")
    }  

    if(day === 0){
      totalWorkingMillisecondsOfWeeks = null;
    }

    const durationShift = shiftData.filter(({ shift_duration }) => shift_duration === Number(dispDuration));
    const findShift = durationShift.find(({ shift_day }) => shift_day === day);
    if (findShift !== undefined) {
      prescribedDays ++;
      
      const { start_time, end_time } = findShift;
      shiftClockIn.innerText = start_time;
      shiftClockOut.innerText = end_time;

      let shiftStrat = new Date(`1970-01-01T${start_time}Z`).getTime();
      let shiftEnd = new Date(`1970-01-01T${end_time}Z`).getTime();

      estimatedWorkingTime = shiftEnd - shiftStrat;
      console.log(estimatedWorkingTime);

      if(estimatedWorkingTime >= 21600000){
        estimatedWorkingTime -= 3600000;
      }
      prescribedMilliseconds += estimatedWorkingTime;
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
      console.log(`所定時間外労働 ${overHours}時間 ${overMinutes}分 です`);
      totalOverMilliseconds += over;
    }
    if (workingTime === null &&  estimatedWorkingTime !== null) {
      clockIn.innerText = "欠勤";

    }



    const findHolidayDate = holidayData.find(({ applicationStartDate }) => {
      return (
        dateStart <= applicationStartDate && dateEnd >= applicationStartDate
      );
    });
    console.log("findHolidayDate:", findHolidayDate);
    
    if (findHolidayDate !== undefined) {
      const {applicationStartDate, applicationEndDate} = findHolidayDate;
      const holidayStartDate= new Date(applicationStartDate);
      console.log("holidayStartDate: ", holidayStartDate);
      
      if (findShift !== undefined) {
        HolidayTimeFlag = "true";
        const { start_time, end_time } = findShift;
        let holidayStart = new Date(`1970-01-01T${start_time}Z`).getTime();
        let holidayEnd = new Date(`1970-01-01T${end_time}Z`).getTime();
        let HolidayTime = (holidayEnd - holidayStart);
        console.log(HolidayTime);
        clockIn.innerText = `有給(${start_time}`;
        clockOut.innerText = `${end_time})`;
        totalHolidayMilliseconds += HolidayTime;
      }else{
        clockIn.innerText = "(有給)"
        note.innerText = "出勤日以外の有給が使用されています。"
      }
    }
    if(HolidayTimeFlag === "true" && findShift !== undefined){
      const { start_time, end_time } = findShift;
      let holidayStart = new Date(`1970-01-01T${start_time}Z`).getTime();
      let holidayEnd = new Date(`1970-01-01T${end_time}Z`).getTime();
      let HolidayTime = (holidayEnd - holidayStart);
      console.log(HolidayTime);

      clockIn.innerText = `有給(${start_time}`;
      clockOut.innerText = `${end_time})`;
      totalHolidayMilliseconds += HolidayTime;
    }

    const findHolidayEnd = holidayData.find(({ applicationEndDate }) => {
      return (
        dateStart <= applicationEndDate && dateEnd >= applicationEndDate
      );
    });
    console.log("findHolidayDate:", findHolidayDate);
    if(findHolidayEnd !== undefined){
      HolidayTimeFlag = "false";
    }
    console.log(HolidayTimeFlag)

    if (findWorkingDate) {
      editHref.innerText = "実績変更";
      const { recordCD, clockInTime, clockOutTime } = findWorkingDate;
      editHref.href = `/DateTime/DispEditTimeRecordServlet?employeeCD=${employeeCD}&name=${name}&recordCD=${recordCD}&clockInTime=${clockInTime}&clockOutTime=${clockOutTime}`;
      edit.appendChild(editHref);
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
    col.appendChild(edit);
    col.appendChild(note);

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
  let totalWorkingHours = totalWorkingMilliseconds / (1000 * 60 * 60);
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

  const total_tr4 = document.createElement("tr");
  const total_th4 = document.createElement("th");
  const total_td4 = document.createElement("td");
  total_th4.innerText = "合計有給時間";
  console.log(totalHolidayMilliseconds);
  let totalHolidayHours = totalHolidayMilliseconds / (1000 * 60 * 60);
  let totalHolidayHours2 = Math.floor(totalHolidayHours);
  let totalHolidayMinutes = Math.floor((totalHolidayHours - totalHolidayHours2) * 60);
  total_td4.innerText = `${totalHolidayHours2}時間 ${totalHolidayMinutes}分`;
  total_tr4.appendChild(total_th4);
  total_tr4.appendChild(total_td4);
  totalDataArea.appendChild(total_tr4);

  const prescribed_tr1 = document.createElement("tr");
  const prescribed_th1 = document.createElement("th");
  const prescribed_td1 = document.createElement("td");
  prescribed_th1.innerText = "所定労働日数";
  prescribed_td1.innerText = prescribedDays;
  prescribed_tr1.appendChild(prescribed_th1);
  prescribed_tr1.appendChild(prescribed_td1);
  prescribedArea.appendChild(prescribed_tr1);
  console.log("prescribedDays:",prescribedDays);

  const prescribed_tr2 = document.createElement("tr");
  const prescribed_th2 = document.createElement("th");
  const prescribed_td2 = document.createElement("td");
  prescribed_th2.innerText = "所定労働時間";
  let prescribedHours = prescribedMilliseconds / (1000 * 60 * 60);
  let prescribedHours2 = Math.floor(prescribedHours);
  let prescribedMinutes = Math.floor((prescribedHours - prescribedHours2) * 60);
  prescribed_td2.innerText = `${prescribedHours2}時間 ${prescribedMinutes}分`;
  prescribed_tr2.appendChild(prescribed_th2);
  prescribed_tr2.appendChild(prescribed_td2);
  prescribedArea.appendChild(prescribed_tr2);
}

window.addEventListener("DOMContentLoaded", () => {
  const currentDate = new Date();
  currentYear = currentDate.getFullYear();
  currentMonth = currentDate.getMonth() + 1;
  DispStartYear = currentYear;
  endDate = new Date(currentYear, currentMonth, 0);
  if(currentMonth >= 4 && currentMonth < 10){
    DispStartMonth = 4;
  }else{
    DispStartMonth = 10;
  }
  getCurrent(DispStartYear, currentMonth);
  getDateAndDay();
})

document.querySelector(".prev").addEventListener("click", () => {
  if (currentMonth === 1) {
    currentMonth = 12;
    currentYear--;
  } else {
    currentMonth--;
  }
  if (currentMonth === 3) {
    DispStartMonth = 10;
    DispStartYear--;
  } else if (currentMonth === 9){
    DispStartMonth = 4 ;
  }
  getCurrent(currentYear, currentMonth);
  getDateAndDay();
});


document.querySelector(".next").addEventListener("click", () => {
  if (currentMonth === 12) {
    currentMonth = 1;
    currentYear++;
  } else {
    currentMonth++;
  }
  if (currentMonth === 4) {
    DispStartMonth = 4;
    DispStartYear++;
  } else if (currentMonth === 10){
    DispStartMonth = 10 ;
  }
  getCurrent(currentYear, currentMonth);
  getDateAndDay();
});