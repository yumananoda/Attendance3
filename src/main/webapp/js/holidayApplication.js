const dateInput1 = document.getElementById("applicationDate1");
const dateInput2 = document.getElementById("applicationDate2");
const worningArea = document.getElementById("worningArea");
const shiftHolder = document.getElementById("shiftData").value;
const shiftData = JSON.parse(shiftHolder);
console.log(shiftData);
const SelectHolidayKind = document.getElementById("multipleMenu");
const holidayKinds = document.getElementsByClassName("holidayKind");
const HolidayForm = document.getElementsByClassName("HolidayForm");
const approved = document.getElementById("approved");
const divAll = document.getElementById("divAll");
const divApproved = document.getElementById("divApproved");

document.addEventListener("DOMContentLoaded", function () {
  const today = new Date();
  const yyyy = today.getFullYear();
  const mm = String(today.getMonth() + 1).padStart(2, "0");
  const dd = String(today.getDate()).padStart(2, "0");

  const formattedDate = `${yyyy}-${mm}-${dd}`;
  dateInput1.value = formattedDate;
  dateInput2.value = formattedDate;
  // startDateInput.value = formattedDate;
});
dateInput1.addEventListener("change", function () {
  dateInput2.value = dateInput1.value;

  let selectYearMonth = `${new Date(dateInput1.value).getFullYear()}${String(new Date(dateInput1.value).getMonth()).padStart(2, "0")}`;
  console.log(selectYearMonth);
  if(selectYearMonth.slice(5) >= 4 && selectYearMonth.slice(5) < 10){
    selectYearMonth = selectYearMonth.slice(0,4) + "04";
  }else{
    selectYearMonth = selectYearMonth.slice(0,4) + "10";
  }
  console.log(selectYearMonth);
  const durationShift = shiftData.filter(({ shift_duration }) => shift_duration === Number(selectYearMonth));
  console.log(durationShift);
  const findShift = durationShift.find(({shift_day}) => shift_day === new Date(dateInput1.value).getDay());
  console.log(new Date(dateInput1.value).getDay());
  console.log(findShift);
  if(findShift === undefined){
    worningArea.innerText = "警告:シフトに登録されていない日が選択されています。";
  }else{
    worningArea.innerText = "";
  }
});



approved.addEventListener("change", () => {
  if(approved.checked) {
    divApproved.classList.add("selected");
    divAll.classList.remove("selected");
  } else {
    divAll.classList.add("selected");
    divApproved.classList.remove("selected");
  }
});
