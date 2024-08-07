const dateInput1 = document.getElementById("applicationDate1");
const dateInput2 = document.getElementById("applicationDate2");
// const startDateInput = document.getElementById("startDate");
// const multipleMenu = document.getElementById("SelectHolidayKind");
const SelectHolidayKind = document.getElementById("multipleMenu");
const holidayKinds = document.getElementsByClassName("holidayKind");
const HolidayForm = document.getElementsByClassName("HolidayForm");

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
});

//SelectHolidayKind.addEventListener("change",() => {
//  if(holidayKinds[2].checked){
//    multipleMenu.style.display="";
//  }else{
//    multipleMenu.style.display="none";
//  }
//})

// HolidayForm.addEventListener("click", () => {
//   window.location.href = `HolidayApplicationConfirm.jsp`;
//   sessionStorage.setItem("INFO", JSON.stringify(INFO));
// })