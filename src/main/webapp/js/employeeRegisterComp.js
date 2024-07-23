window.addEventListener("DOMContentLoaded", () => {
  console.log("called");
  const info = JSON.parse(sessionStorage.getItem("info"));
  console.log("info: ", info);
});
