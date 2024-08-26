const shiftChangeForm = document.getElementById("shiftChangeForm");
const employeeCD = document.getElementById("employeeCD").value;
const applicationCategory = document.getElementById("application_category");
const selectDate = document.getElementById("changeDete");
const changeTimeStart = document.getElementById("changeTimeStart");
const changeTimeEnd = document.getElementById("changeTimeEnd");
const changeHistory = document.getElementById("changeHistory");
const addShift = document.getElementById("addShift");
const removeShift = document.getElementById("removeShift");
const changeShift = document.getElementById("changeShift");
const error = document.getElementById("error");
const registerBtn = document.getElementById("registerBtn");
const closeBtn = document.getElementById("closeBtn");
const shift = document.getElementById("shift").value;
const shiftData = JSON.parse(shift);
const exceptionShift = document.getElementById("exceptionShift").value;
const exceptionShiftData = JSON.parse(exceptionShift);
console.log(exceptionShiftData)
const changeData = [];



const today = new Date();
const currentYear = today.getFullYear();
const currentMonth = today.getMonth() + 1;
const currentDate = today.getDate();
const currentDay = today.getDay();

let selectDuration = null;
let selectDay = null;
let selectValue = null;
let startDate = null;
let endDate = null;

document.addEventListener("DOMContentLoaded", function () {
    const formattedDate = `${currentYear}-${String(currentMonth).padStart(2, "0")}-${String(currentDate).padStart(2, "0")}`;
    selectDate.value = formattedDate;
    selectDate.min = formattedDate;
    if(currentMonth >= 4 && currentMonth < 10){
        selectDuration = `${currentYear}04`
    }else{
        selectDuration = `${currentYear}10`
    }
    selectDay = new Date(formattedDate).getDay();
    checkShift();
    dispChangeHistory();
});


applicationCategory.addEventListener('change', (e) => {
    selectValue = applicationCategory.value;
    console.log(selectValue);
    if(e.target.value === "removeShift"){
        changeTimeStart.disabled=true;
        changeTimeEnd.disabled=true;
    }else{
        changeTimeStart.disabled=false;
        changeTimeEnd.disabled=false;
    }
    checkShift();
});

selectDate.addEventListener('change', (e) => {
    console.log(new Date(e.target.value).getFullYear(), new Date(e.target.value).getMonth());
    selectDay = new Date(e.target.value).getDay();
    console.log("aaa")

    if(new Date(e.target.value).getMonth()+1 >= 4 && new Date(e.target.value).getMonth()+1 < 10){
        selectDuration = `${new Date(e.target.value).getFullYear()}04`
    }else{
        selectDuration = `${new Date(e.target.value).getFullYear()}10`
    }
    checkShift();
    dispChangeHistory();
})  

const checkShift = () => {
    error.innerText = "";
    changeTimeStart.value="";
    changeTimeEnd.value="";
    const filterShiftData = shiftData.filter(({ shift_duration }) => shift_duration === Number(selectDuration));
    console.log(shiftData);
    console.log(filterShiftData);
    const findShift = filterShiftData.find(({shift_day}) => shift_day === selectDay);

    console.log(selectValue, findShift);
    if(selectValue === "addShift" && findShift !== undefined){
        console.log("すでにシフトが登録されている日が選択されています。");
        error.innerText = "警告:すでにシフトが登録されている日が選択されています。";
        registerBtn.disabled = true;
    }else if((selectValue === "removeShift" || selectValue === "changeTime") && findShift === undefined){
        console.log("シフトに入っていない日が選択されています。");
        error.innerText = "警告:シフトに入っていない日が選択されています。";
        registerBtn.disabled = true;
    }else if((selectValue === "removeShift" || selectValue === "changeTime") && findShift !== undefined){
        let {start_time, end_time} = findShift;
        start_time = start_time.slice(0, 5);
        end_time = end_time.slice(0, 5);
        changeTimeStart.value=start_time;
        changeTimeEnd.value=end_time;
        registerBtn.disabled = false;
    }else{
        registerBtn.disabled = false; 
    }
}

const dispChangeHistory = () => {
    while (addShift.firstChild) {
        addShift.removeChild(addShift.firstChild);
    }
    while (removeShift.firstChild) {
        removeShift.removeChild(removeShift.firstChild);
    }
    while (changeShift.firstChild) {
        changeShift.removeChild(changeShift.firstChild);
    }
    console.log(selectDate.value);
    let selectYear = new Date(selectDate.value).getFullYear();
    let selectMonth = new Date(selectDate.value).getMonth();
    startDate = new Date(selectYear, selectMonth, 1).getTime();
    endDate = new Date(selectYear, selectMonth+1, 1).getTime();
    console.log(startDate, endDate)

    const filterAddExceptionShiftData = exceptionShiftData.filter(({ shiftDate, category }) => shiftDate >= startDate && shiftDate < endDate && category===1);
    console.log(filterAddExceptionShiftData);
    if(filterAddExceptionShiftData.length !== 0){
        const category = document.createElement("h2");
        category.innerText = "シフト追加";
        const tr1 = document.createElement("tr");
        const th1_1 = document.createElement("th");
        th1_1.innerText="日にち";
        const th1_2 = document.createElement("th");
        th1_2.innerText="出勤予定時間"
        const th1_3 = document.createElement("th");
        th1_3.innerText="退勤予定時間";
        tr1.appendChild(th1_1);
        tr1.appendChild(th1_2);
        tr1.appendChild(th1_3);
        addShift.appendChild(category);
        addShift.appendChild(tr1);
        for(let i=0; i<filterAddExceptionShiftData.length; i++){
            const {shiftDate, startTime, endTime} = filterAddExceptionShiftData[i];
            const tr = document.createElement("tr");
            const td1 = document.createElement("td");
            td1.innerText=`${new Date(shiftDate).getFullYear()}-${new Date(shiftDate).getMonth()+1}-${new Date(shiftDate).getDate()}`;
            const td2 = document.createElement("td");
            td2.innerText=startTime;
            const td3 = document.createElement("td");
            td3.innerText=endTime;
            
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            addShift.appendChild(tr);
        }
        changeHistory.appendChild(addShift);
    }

    const filterRemoveExceptionShiftData = exceptionShiftData.filter(({ shiftDate, category }) => shiftDate >= startDate && shiftDate < endDate && category===2);
    console.log(filterRemoveExceptionShiftData);
    if(filterRemoveExceptionShiftData.length !== 0){
        const category = document.createElement("h2");
        category.innerText = "シフト削除";
        const tr2 = document.createElement("tr");
        const th2_1 = document.createElement("th");
        th2_1.innerText="日にち";
        const th2_2 = document.createElement("th");
        th2_2.innerText="出勤予定時間"
        const th2_3 = document.createElement("th");
        th2_3.innerText="退勤予定時間";
        tr2.appendChild(th2_1);
        tr2.appendChild(th2_2);
        tr2.appendChild(th2_3);
        removeShift.appendChild(category);
        removeShift.appendChild(tr2);
        for(let i=0; i<filterRemoveExceptionShiftData.length; i++){
            const {shiftDate, startTime, endTime} = filterRemoveExceptionShiftData[i];
            const tr = document.createElement("tr");
            const td1 = document.createElement("td");
            td1.innerText=`${new Date(shiftDate).getFullYear()}-${new Date(shiftDate).getMonth()+1}-${new Date(shiftDate).getDate()}`;
            const td2 = document.createElement("td");
            td2.innerText=startTime;
            const td3 = document.createElement("td");
            td3.innerText=endTime;
            
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            removeShift.appendChild(tr);
        }
        changeHistory.appendChild(removeShift);
    }

    const filterChangeExceptionShiftData = exceptionShiftData.filter(({ shiftDate, category }) => shiftDate >= startDate && shiftDate < endDate && category===3);
    console.log(filterChangeExceptionShiftData);
    if(filterChangeExceptionShiftData.length !== 0){
        const category = document.createElement("h2");
        category.innerText = "シフト時間変更";
        const tr3 = document.createElement("tr");
        const th3_1 = document.createElement("th");
        th3_1.innerText="日にち";
        const th3_2 = document.createElement("th");
        th3_2.innerText="出勤予定時間"
        const th3_3 = document.createElement("th");
        th3_3.innerText="退勤予定時間";
        tr3.appendChild(th3_1);
        tr3.appendChild(th3_2);
        tr3.appendChild(th3_3);
        changeShift.appendChild(category);
        changeShift.appendChild(tr3);
        for(let i=0; i<filterChangeExceptionShiftData.length; i++){
            const {shiftDate, startTime, endTime} = filterChangeExceptionShiftData[i];
            const tr = document.createElement("tr");
            const td1 = document.createElement("td");
            td1.innerText=`${new Date(shiftDate).getFullYear()}-${new Date(shiftDate).getMonth()+1}-${new Date(shiftDate).getDate()}`;
            const td2 = document.createElement("td");
            td2.innerText=startTime;
            const td3 = document.createElement("td");
            td3.innerText=endTime;
            
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            changeShift.appendChild(tr);
        }
        changeHistory.appendChild(changeShift);
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
    changeData.length = 0;
    changeTimeStart.value="";
    changeTimeEnd.value="";

    const formattedDate = `${currentYear}-${String(currentMonth).padStart(2, "0")}-${String(currentDate).padStart(2, "0")}`;
    selectDate.value = formattedDate;
    selectDate.min = formattedDate;
    checkShift();
})