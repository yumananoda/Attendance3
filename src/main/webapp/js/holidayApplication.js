document.addEventListener("DOMContentLoaded", function () {
  const dateInput = document.getElementById("applicationDate");
  const startDateInput = document.getElementById("startDate");
  const today = new Date();
  const yyyy = today.getFullYear();
  const mm = String(today.getMonth() + 1).padStart(2, "0");
  const dd = String(today.getDate()).padStart(2, "0");

  const formattedDate = `${yyyy}-${mm}-${dd}`;
  dateInput.value = formattedDate;
  startDateInput.value = formattedDate;
  dateInput.addEventListener("change", function () {
    startDateInput.value = dateInput.value;
  });
});
