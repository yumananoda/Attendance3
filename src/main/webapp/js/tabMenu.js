document.addEventListener('DOMContentLoaded', function () {
  const targets = document.getElementsByClassName('tab');
  const arrayTabs = Array.from(targets); 
    
  arrayTabs.forEach(tab => {
	   tab.addEventListener('click', changeTab);
  })
  
    // タブメニューボタンをクリックすると実行
    function changeTab() {
        // タブのclassを変更
        document.getElementsByClassName('is-active')[0].classList.remove('is-active');
        this.classList.add('is-active');
        // コンテンツのclassの値を変更
        document.getElementsByClassName('is-display')[0].classList.remove('is-display');
        const arrayTabs = Array.prototype.slice.call(targets);
        const index = arrayTabs.indexOf(this);
        document.getElementsByClassName('content')[index].classList.add('is-display');
    };
}, false);