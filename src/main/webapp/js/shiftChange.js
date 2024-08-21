const shiftChangeForm = document.getElementById("shiftChangeForm");
const employeeCD = document.getElementById("employeeCD").value;
const selectDate = document.getElementById("changeDete");
const changeTimeStart = document.getElementById("changeTimeStart");
const changeTimeEnd = document.getElementById("changeTimeEnd");
const applicationCategory = document.getElementById("application_category");
const error = document.getElementById("error");
const registerBtn = document.getElementById("registerBtn");
const closeBtn = document.getElementById("closeBtn");
const shift = document.getElementById("shift").value;
const shiftData = JSON.parse(shift);
const changeData = [];



const today = new Date();
const currentYear = today.getFullYear();
const currentMonth = today.getMonth() + 1;
const currentDate = today.getDate();
const currentDay = today.getDay();

let selectDuration = null;
let selectDay = null;
let selectValue = null;

document.addEventListener("DOMContentLoaded", function () {
    const formattedDate = `${currentYear}-${String(currentMonth).padStart(2, "0")}-${String(currentDate).padStart(2, "0")}`;
    selectDate.value = formattedDate;
    selectDate.min = formattedDate;
});


applicationCategory.addEventListener('change', () => {
    selectValue = applicationCategory.value;
    console.log(selectValue);
    checkShift();
});

changeDete.addEventListener('change', (e) => {
    console.log(new Date(e.target.value).getFullYear(), new Date(e.target.value).getMonth());
    selectDay = new Date(e.target.value).getDay();

    if(new Date(e.target.value).getMonth()+1 >= 4 && new Date(e.target.value).getMonth()+1 < 10){
        selectDuration = `${new Date(e.target.value).getFullYear()}04`
    }else{
        selectDuration = `${new Date(e.target.value).getFullYear()}10`
    }
    checkShift();
})  

const checkShift = () => {
    const filterShiftData = shiftData.filter(({ shift_duration }) => shift_duration === Number(selectDuration));
    console.log(shiftData);
    console.log(filterShiftData);
    const findShift = filterShiftData.find(({shift_day}) => shift_day === selectDay);
    console.log(selectValue, findShift)
    if(selectValue === "addShift" && findShift !== undefined){
        console.log("すでにシフトが登録されている日が選択されています。");
        error.innerText = "警告:すでにシフトが登録されている日が選択されています。";
    }else if(selectValue === "removeShift" && findShift === undefined){
        console.log("シフトに入っていない日が選択されています。");
        error.innerText = "警告:シフトに入っていない日が選択されています。";
        registerBtn.disabled = true;
    }else{
        error.innerText = "";
        registerBtn.disabled = false; 
    }
}

registerBtn.addEventListener('click', (e) => {
    e.preventDefault();
    changeData.push(
        employeeCD,
        selectDate.value,
        changeTimeStart.value,
        changeTimeEnd.value,
        applicationCategory.value,
    )
    console.log(changeData);
    console.log("実行");
    fetch("/DateTime/ShiftChangeServlet", {
        method: "POST",
        body: JSON.stringify(changeData),
    })
    .then((response) => {
        console.log("response", response);
        if(!response.ok){
        throw new Error("Network response was not ok");
        }
        if (!response.isError) {
            layer.classList.add("is-open");
        }
    })
});

closeBtn.addEventListener('click', () => {
    layer.classList.remove('is-open'); 
    shiftChangeForm.reset();
})