import {disallowed_characters_regex, alphabetic_password_regex, number_password_regex, special_password_regex} from "./const.js";

const currentPassword = document.getElementById("currentPassword");
const newPassword = document.getElementById("new");
const newPasswordRe = document.getElementById("newRe");
const currentEyeIcon = document.getElementById("current-eyeIcon");
const newEyeIcon = document.getElementById("new-eyeIcon");
const reEyeIcon = document.getElementById("re-eyeIcon");
const disallow = document.getElementById("disallow");
const numberWord = document.getElementById("number_word");
const Alphabetic = document.getElementById("Alphabetic");
const number = document.getElementById("number");
const specialCharacters = document.getElementById("special_characters");
const matchCheck = document.getElementById("match_check");
const formBtn = document.getElementById("btn");

currentEyeIcon.addEventListener('click',() => {
    if(currentPassword.type == "password"){
        currentPassword.type = "text";
    }else{
        currentPassword.type = "password";
    }
})
newEyeIcon.addEventListener('click',() => {
    if(newPassword.type == "password"){
        newPassword.type = "text";
    }else{
        newPassword.type = "password";
    }
})
reEyeIcon.addEventListener('click',() => {
    if(newPasswordRe.type == "password"){
        newPasswordRe.type = "text";
    }else{
        newPasswordRe.type = "password";
    }
})

newPassword.addEventListener('keyup',(e) => {
    console.log(e.target.value);

    const disallowed_checked = disallowed_characters_regex.test(e.target.value);
    if(disallowed_checked){
        disallow.innerText = "使用可能文字が入力されました。";
    }else{
        disallow.innerText = "";
    }

    if(newPassword.value.length < 8){
        numberWord.innerText = "8文字以上:×"
    }else{
        numberWord.innerText = "8文字以上:⚪︎"
    }

    const alphabetic_checked = alphabetic_password_regex.test(e.target.value);
    if(!alphabetic_checked){
        Alphabetic.innerText = "英字:×"
    }else{
        Alphabetic.innerText = "英字:⚪︎"
    }

    const number_checked = number_password_regex.test(e.target.value);
    if(!number_checked){
        number.innerText = "数値:×"
    }else{
        number.innerText = "数値:⚪︎"
    }

    const special_checked = special_password_regex.test(e.target.value);
    if(!special_checked){
        specialCharacters.innerText = "特殊文字:×"
    }else{
        specialCharacters.innerText = "特殊文字:⚪︎"
    }
})

newPasswordRe.addEventListener('keyup',() => {
    if(newPassword.value != newPasswordRe.value){
        matchCheck.innerText = "入力値が一致しません。"
    }else{
        matchCheck.innerText = "";
        formBtn.disabled = false;
    }
})
