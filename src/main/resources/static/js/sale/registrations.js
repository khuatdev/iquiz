function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const validFrom = urlParams.get("validFrom");
    const validTo = urlParams.get("validTo");
    const status_ = urlParams.get("status");
    const sortBy = urlParams.get("sortBy");
    const order = urlParams.get("order");
    const searchValue=urlParams.get("searchValue");
    if (validFrom) {
        document.getElementById("validFromSelect").value = gender;
    }
    if (validTo) {
        document.getElementById("validToSelect").value = role;
    }
    if (status_) {
        document.getElementById("statusSelect").value = status_;
    }
    if (sortBy) {
        document.getElementById("sortBySelect").value = sortBy;
    }
    if (order) {
        document.getElementById("orderSelect").value = order;
    }
    if(searchValue){
        document.getElementById("search").value=searchValue;
    }
}
  
window.addEventListener("load", setPageFilters);
function navigateToInteract() {
    var validFrom = document.getElementById("validFromSelect").value;
    var validTo = document.getElementById("validToSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/sale/registrations-list?";
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (validFrom) {
        filters.push("validFrom=" + gender);
    } 
    if (validTo) {
        filters.push("validTO=" + role);
    }
    if (status) {
        filters.push("status=" + status);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");
    
    window.location.href = url;
    
}
function moveOn(pageNo){
    var validFrom = document.getElementById("validFromSelect").value;
    var validTo = document.getElementById("validToSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/sale/registrations-list?pageNo=" + (parseInt(pageNo) + 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (validFrom) {
        filters.push("validFrom=" + gender);
    } 
    if (validTo) {
        filters.push("validTO=" + role);
    }
    if (status) {
        filters.push("status=" + status);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");
    
    window.location.href = url;
}

function moveBack(pageNo){
    var validFrom = document.getElementById("validFromSelect").value;
    var validTo = document.getElementById("validToSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/sale/registrations-list?pageNo=" + (parseInt(pageNo) - 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (validFrom) {
        filters.push("validFrom=" + gender);
    } 
    if (validTo) {
        filters.push("validTO=" + role);
    }
    if (status) {
        filters.push("status=" + status);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");
    
    window.location.href = url;
}
function showPopup() {
    document.getElementById("popupForm").style.display = "block";
}
  
function hidePopup() {
    document.getElementById("popupForm").style.display = "none";
}
function getPricePackage() {
    var subjectId = $("#inputSubject").val();
    $.get("/sale/get-pricepackages?subjectId=" + subjectId, function(data) {
        $("#inputPricePackage").empty();
        data.forEach(function(item) {
            var option = "<option value=" + item.id + ">" + item.name + "</option>";
            $("#inputPricePackage").append(option);
            $("#listPrice").text(item.listPrice);
            $("#listPrice").text(item.listPrice);
            $("#salePrice").text(item.salePrice);
        });
    });
}
function changePrice(){
    var packageId=$("#inputPricePackage").val();
    $.get("/sale/get-pricepackage?pricePackageId="+packageId,function(data){
        $("#listPrice").text(data.listPrice);
        $("#salePrice").text(data.salePrice);
    });
}
