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

function checkOldPass() {
    var oldpass = document.getElementById('oldpass').value.trim();

    if (oldpass === "") {
//        document.getElementById('usernameerror').innerHTML = "Username cannot be empty";
//        document.getElementById('usernameerror').style.color = "red";
        return false;
    } else {
//        document.getElementById('usernameerror').innerHTML = "";
        return true;
    }
}

function checkNewpass() {
    var newpass = document.getElementById('newpass').value;

    if (newpass.length < 6 || newpass.length > 20 || newpass == "") {
        return false;
    } else {
        return true;
    }

}

function checkReNewpass() {
    var newpass1 = document.getElementById('newpass').value;
    var newpass2 = document.getElementById('renewpass').value;
    if (newpass1 !== newpass2) {
        return false;
    } else {
        return true;
    }
}


//validate change password
function checkChangePass() {
    let oldpasserror = document.getElementById('oldpasserror');
    let newpasserror = document.getElementById('newpasserror');
    let renewpasserror = document.getElementById('renewpasserror');
    let check = true;

    if(!checkOldPass()){
        check = false;
        oldpasserror.innerHTML = "Enter your password";
        oldpasserror.style.color = "red";
    } else {
        oldpasserror.innerHTML = "";
    }

    if(!checkNewpass()){
        check = false;
        newpasserror.innerHTML = "Password must be between 6 and 20 characters";
        newpasserror.style.color = "red";
    } else {
        newpasserror.innerHTML = "";
    }
    
    if(!checkReNewpass()){
        check = false;
        renewpasserror.innerHTML = "Password does not match";
        renewpasserror.style.color = "red";
    } else {
        renewpasserror.innerHTML = "";
    }
   

    if (!check) {
        event.preventDefault();
    } else {
        window.location.href = "User";
    }
}

//validate edit form
function checkEdit() {
    let nameerror = document.getElementById('usernameerror');
    let birtherror = document.getElementById('birtherror');
    let phoneerror = document.getElementById('phoneerror');
    let addresserror = document.getElementById('addresserror');
    let check = true;

    if (!checkUsername()) {
        check = false;
        nameerror.innerHTML = "Username cannot be empty";
        nameerror.style.color = "red";
    } else {
        nameerror.innerHTML = "";
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
        window.location.href = "User";
    }
}

