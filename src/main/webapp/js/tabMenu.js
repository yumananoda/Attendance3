document.addEventListener('DOMContentLoaded', () => {
  const tabs = document.getElementsByClassName('tab');
  const contents = document.getElementsByClassName('content');
  const arrayTabs = Array.from(tabs); 
  const arrayContents = Array.from(contents); 
  arrayTabs.forEach(tab => {
    tab.addEventListener('click', changeTab);
  })

  function changeTab(){
    const beforeTab = document.getElementsByClassName('is-active')[0];
    beforeTab.classList.remove('is-active');
    const beforeContent = document.getElementsByClassName('is-display')[0];
    beforeContent.classList.remove('is-display');

    const index2 = arrayContents.indexOf(this);
    this.classList.add('is-active');
    document.getElementsByClassName('content')[index2].classList.add('is-display');
  };
}, false);
