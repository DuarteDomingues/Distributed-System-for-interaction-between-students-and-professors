function FormRegistValidator(theForm, passwordFilter) {


  if ( theForm.username.value === "" ) {
    alert("You must enter a valid name.");
    theForm.username.focus();
    return false;
  }
   if ( passwordValidator(theForm, passwordFilter) === false){
      return false;
  }

  return true;
}

 
function emailValidator(theForm, emailFilter){


    if ( !emailFilter.test( theForm.email.value ) ) {
        alert('Please provide a valid e-mail address');
        theForm.email.focus();
        return false;
  }

  return true;


}

function passwordValidator(theForm, passwordFilter){



    if ( !passwordFilter.test( theForm.password.value ) ) {
    alert('The password must have at least 8 characters and a upper case character.');
    theForm.password.focus();
    return false;
  }

   if ( !(theForm.password.value === theForm.password_confirm.value )) {
    alert('Both passwords must be the same.');
    theForm.password_confirm.focus();
    return false;
  }


  return true;


}









function FormRegistValidatorEdit(theForm, passwordFilter, emailFilter) {

  if ( theForm.username.value === "" ) {
    alert("You must enter a valid name.");
    theForm.username.focus();
    return false;
  }
   if (emailValidator(theForm, emailFilter) === false || passwordValidator(theForm, passwordFilter) === false){
      return false;
  }

  return true;
}
