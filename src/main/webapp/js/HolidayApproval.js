// const holidayCDList = document.querySelectorAll(".holidayCD");
// const approveButtons = document.querySelectorAll(".approved");

// const formData = new FormData(form);
// holidayCDList.forEach((item) => console.log(item.value));

// approveButtons.forEach((button, index) => {
//     button.addEventListener(("submit"), (e) => {
//         e.preventDefault();
//         console.log(`承認ボタン ${index + 1} がクリックされました。`);
//         console.log(item.value);
//         fetch("/DateTime/HolidayStatusApprovedServlet", {
//             method: "POST",
//             body: formData,
//         })
//             .then((response) => {
//                 if (!response.ok) {
//                 throw new Error("Network response was not ok");
//                 }
//                 return response;
//             })
//             .then((res) => res.text())
//             .then((text) => {
//                 console.log("text:",text);
//                 console.log(typeof text);
//             })
        
//     })
// })