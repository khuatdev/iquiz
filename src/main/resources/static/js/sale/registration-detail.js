function showPopup() {
    document.getElementById("popupForm").style.display = "block";
}
  
function hidePopup() {
    document.getElementById("popupForm").style.display = "none";
}
function change(isYours){
    document.getElementById("save-button").style.display="block";
    document.getElementById("change-button").style.display="none";
    document.getElementById("cancel-button").style.display="block";
    document.getElementById("inputNote").disabled=false;
    document.getElementById("inputStatus").disabled=false;
    if(isYours==true){
        document.getElementById("inputEmail").disabled=false;
        document.getElementById("inputFullName").disabled=false;
        document.getElementById("inputGender").disabled=false;
        document.getElementById("inputMobile").disabled=false;
        document.getElementById("inputSubject").disabled=false;
        document.getElementById("inputPricePackage").disabled=false;
    }
}
function cancel(){
    document.getElementById("save-button").style.display="none";
    document.getElementById("change-button").style.display="block";
    document.getElementById("cancel-button").style.display="none";
    document.getElementById("inputNote").disabled=true;
    document.getElementById("inputStatus").disabled=true;
    document.getElementById("inputEmail").disabled=true;
    document.getElementById("inputFullName").disabled=true;
    document.getElementById("inputGender").disabled=true;
    document.getElementById("inputMobile").disabled=true;
    document.getElementById("inputSubject").disabled=true;
    document.getElementById("inputPricePackage").disabled=true;
}
function backToParents(){
    window.location.href="/sale/registrations-list";
}