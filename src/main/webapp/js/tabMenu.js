document.addEventListener("DOMContentLoaded",() => {
    const tabs = document.getElementsByClassName("tab");
    const contents = document.getElementsByClassName("content");
    const arrayTabs = Array.from(tabs);
    const arrayContents = Array.from(contents);
    arrayTabs.forEach((tab) => {
      tab.addEventListener("click", (e) => changeTab(e));
    });

    function changeTab(e) {
      console.log("e: ", e.target.value);
      const contentIndex = Number(e.target.value);
      // console.log(arrayContents);
      const beforeTab = document.getElementsByClassName("is-active")[0];
      beforeTab.classList.remove("is-active");
      const beforeContent = document.getElementsByClassName("is-display")[0];
      beforeContent.classList.remove("is-display");

      // const index2 = arrayContents.indexOf(this);
      // console.log("index2", index2);
      arrayTabs[contentIndex].classList.add("is-active");
      arrayContents[contentIndex].classList.add("is-display");
    }
  },
  false
);
