window.addEventListener("DOMContentLoaded", () => {
  console.log("called");
  const INFO = JSON.parse(sessionStorage.getItem("INFO"));
  console.log("INFO: ", INFO);
  INFO.forEach(({ email, hireDate, id, name, position }) => {
    const employeeDiv = document.createElement("div");
    const name = document.createElement("p");
    name.name = "name";
    position.value = key;
    labelPosition.setAttribute("for", position.id);
    labelPosition.innerText = POSITION_NAME[key];
    if (Number(key) === 1) {
      position.checked = true;
    }
    positionDiv.appendChild(position);
    positionDiv.appendChild(labelPosition);
    positionEl.appendChild(positionDiv);
  });
});
