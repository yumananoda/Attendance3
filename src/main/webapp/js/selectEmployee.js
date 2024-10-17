const retireCheck = document.getElementById("retireCheck");
const employed = document.getElementById("employed");
const retired = document.getElementById("retired");

retireCheck.addEventListener("change", () => {
    if(retireCheck.checked) {
        retired.classList.add("selected");
        employed.classList.remove("selected");
    } else {
        employed.classList.add("selected");
        retired.classList.remove("selected");
    }
});