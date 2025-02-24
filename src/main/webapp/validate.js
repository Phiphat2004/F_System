window.addEventListener('load', () => {
    var loadingscreen = document.querySelector('.loader');
    if (loadingscreen) {
        loadingscreen.classList.add("loader_hidden");
        loadingscreen.addEventListener('transitionend', () => {
            document.body.style.overflow = "auto";
            document.body.classList.remove("loader");
        });
    }
});

var emailValidation1 = /^[A-z]+[^\@\s]+\@[^\s]+\.[^\s]{2,}$/;
var emailValidation2 = /^[A-z]+\@[^\s]+\.[^\s]{2,}$/;
var numberValidation = /^0+[0-9]{9,10}$/;

function checkReset() {
    document.getElementById('emailerror').innerHTML = "";
    document.getElementById('emailerror1').innerHTML = "";
    document.getElementById('usernameerror').innerHTML = "";
    document.getElementById('passerror').innerHTML = "";
    document.getElementById('passerror1').innerHTML = "";
    document.getElementById('repasserror').innerHTML = "";
    document.getElementById('birtherror').innerHTML = "";
    document.getElementById('phoneerror').innerHTML = "";
    document.getElementById('addresserror').innerHTML = "";

}
function checkValidate() {
    checkEmailRegister();
    checkEmailLogin();
    checkUsername();
    checkPasswordLogin();
    checkPasswordRegister();
    checkBirthday();
    checkPhone();
    checkAddress();
}


function checkEmailLogin() {
    var email = document.getElementById('email').value;
    if (!emailValidation1.test(email) && !emailValidation2.test(email) && email == "") {

        return false;
    } else {
//            document.getElementById('emailerror').innerHTML = "";
        return true;
    }
}


function checkEmailRegister() {
    var email1 = document.getElementById('email1').value;
    if (email1 == "") {
//        document.getElementById('emailerror1').innerHTML = "Email cannot be empty";
//        document.getElementById('emailerror1').style.color = "red";
        return false;

    } else {
        if (!emailValidation1.test(email1) && !emailValidation2.test(email1)) {
//            document.getElementById('emailerror1').innerHTML = "Email is invalid";
//            document.getElementById('emailerror1').style.color = "red";
            return false;
        } else {
//            document.getElementById('emailerror1').innerHTML = "";
            return true;
        }
    }
}

function checkEmailEdit() {
    var email = document.getElementById('emailedit').value;
    if (email == "") {
//        document.getElementById('emailerror1').innerHTML = "Email cannot be empty";
//        document.getElementById('emailerror1').style.color = "red";
        return false;

    } else {
        if (!emailValidation1.test(email) && !emailValidation2.test(email)) {
//            document.getElementById('emailerror1').innerHTML = "Email is invalid";
//            document.getElementById('emailerror1').style.color = "red";
            return false;
        } else {
//            document.getElementById('emailerror1').innerHTML = "";
            return true;
        }
    }
}

function checkUsername() {
    var name = document.getElementById('username').value.trim();

    if (name == "") {
//        document.getElementById('usernameerror').innerHTML = "Username cannot be empty";
//        document.getElementById('usernameerror').style.color = "red";
        return false;
    } else {
//        document.getElementById('usernameerror').innerHTML = "";
        return true;
    }
}

function checkUsernameEdit() {
    var name = document.getElementById('usernameedit').value.trim();

    if (name == "") {
//        document.getElementById('usernameerror').innerHTML = "Username cannot be empty";
//        document.getElementById('usernameerror').style.color = "red";
        return false;
    } else {
//        document.getElementById('usernameerror').innerHTML = "";
        return true;
    }
}

function checkPasswordLogin() {
    var password = document.getElementById('password').value;

    if (password.length < 6 || password.length > 20 || password == "") {

        return false;
    } else {
//        document.getElementById('passerror').innerHTML = "";
        return true;
    }
}

function checkPasswordRegister() {
    var password = document.getElementById('password1').value;
    var repassword = document.getElementById('repassword').value;

    if (password.length < 6 || password.length > 20 || password == "") {
//        document.getElementById('passerror1').innerHTML = "Password must be between 6 and 20 characters";
//        document.getElementById('passerror1').style.color = "red";
        return false;
    } else {
//        document.getElementById('passerror1').innerHTML = "";
        return true;
    }
}
function checkRePassRegis() {
    var password = document.getElementById('password1').value;
    var repassword = document.getElementById('repassword').value;
    if (repassword != password) {
//        document.getElementById('repasserror').innerHTML = "Password does not match";
//        document.getElementById('repasserror').style.color = "red";
        return false;
    } else {
//        document.getElementById('repasserror').innerHTML = "";
        return true;
    }

}

function checkBirthday() {
    var birthday = document.getElementById('birthday').value;

    if (birthday === "") {
//        document.getElementById('birtherror').innerHTML = "Birthday cannot be empty";
//        document.getElementById('birtherror').style.color = "red";
        return false;

    } else {
//        document.getElementById('birtherror').innerHTML = "";
        return true;
    }
}

function checkBirthdayEdit() {
    var birthday = document.getElementById('birthdayedit').value;

    if (birthday === "") {
//        document.getElementById('birtherror').innerHTML = "Birthday cannot be empty";
//        document.getElementById('birtherror').style.color = "red";
        return false;

    } else {
//        document.getElementById('birtherror').innerHTML = "";
        return true;
    }
}

function checkPhone() {
    var phone = document.getElementById('phone').value;

    if (!numberValidation.test(phone)) {
//        document.getElementById('phoneerror').innerHTML = "Phone is invalid";
//        document.getElementById('phoneerror').style.color = "red";
        return false;
    } else {
//        document.getElementById('phoneerror').innerHTML = "";
        return true;
    }

}

function checkPhoneEdit() {
    var phone = document.getElementById('phoneedit').value;

    if (!numberValidation.test(phone)) {
//        document.getElementById('phoneerror').innerHTML = "Phone is invalid";
//        document.getElementById('phoneerror').style.color = "red";
        return false;
    } else {
//        document.getElementById('phoneerror').innerHTML = "";
        return true;
    }

}

function checkAddress() {
    var address = document.getElementById('address').value;
    var address = address.replace(/\s/g, '');
    if (address === "") {
//        document.getElementById('addresserror').innerHTML = " You have to provide your address";
//        document.getElementById('addresserror').style.color = "red";
        return false;
    } else {
//        document.getElementById('addresserror').innerHTML = "";
        return true;
    }
}

function checkAddressEdit() {
    var address = document.getElementById('addressedit').value;
    var address = address.replace(/\s/g, '');
    if (address === "") {
//        document.getElementById('addresserror').innerHTML = " You have to provide your address";
//        document.getElementById('addresserror').style.color = "red";
        return false;
    } else {
//        document.getElementById('addresserror').innerHTML = "";
        return true;
    }
}

//validate login form
function checkLogin() {
    let emailerror = document.getElementById('emailerror');
    let passerror = document.getElementById('passerror');
    let check = true;
    if (checkEmailLogin() == false) {
        check = false;
        emailerror.innerHTML = "Email is invalid";
        emailerror.style.color = "red";
    } else {
        emailerror.innerHTML = "";
    }

    if (checkPasswordLogin() == false) {
        passerror.innerHTML = "Password must be between 6 and 20 characters";
        passerror.style.color = "red";
        check = false;
    } else {
        passerror.innerHTML = "";
    }
    if (!check) {
        event.preventDefault();
    } else {
        window.location.href = "Login";
    }

}

//validate register form
function checkRegis() {
    let emailerror = document.getElementById('emailerror1');
    let passerror = document.getElementById('passerror1');
    let nameerror = document.getElementById('usernameerror');
    let repasserror = document.getElementById('repasserror');
    let birtherror = document.getElementById('birtherror');
    let phoneerror = document.getElementById('phoneerror');
    let addresserror = document.getElementById('addresserror');
    let check = true;

    if (!checkEmailRegister()) {
        check = false;
        emailerror.innerHTML = "Email is invalid";
        emailerror.style.color = "red";
    } else {
        emailerror.innerHTML = "";
    }

    if (!checkUsername()) {
        check = false;
        nameerror.innerHTML = "Username cannot be empty";
        nameerror.style.color = "red";
    } else {
        nameerror.innerHTML = "";
    }

    if (!checkPasswordRegister()) {
        check = false;
        passerror.innerHTML = "Password must be between 6 and 20 characters";
        passerror.style.color = "red";
    } else {
        passerror.innerHTML = "";
    }
    if (!checkRePassRegis()) {
        check = false;
        repasserror.innerHTML = "Password does not match";
        repasserror.style.color = "red";
    } else {
        repasserror.innerHTML = "";
    }
    if (!checkBirthday()) {
        check = false;
        birtherror.innerHTML = "Birthday cannot be empty";
        birtherror.style.color = "red";
    } else {
        birtherror.innerHTML = "";
    }
    if (!checkPhone()) {
        check = false;
        phoneerror.innerHTML = "Phone is invalid";
        phoneerror.style.color = "red";
    } else {
        phoneerror.innerHTML = "";
    }
    if (!checkAddress()) {
        check = false;
        addresserror.innerHTML = "You have to provide your address";
        addresserror.style.color = "red";
    } else {
        addresserror.innerHTML = "";
    }

    if (!check) {
        event.preventDefault();
    } else {
        window.location.href = "Login";
    }

}




