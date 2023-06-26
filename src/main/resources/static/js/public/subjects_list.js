function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const order = urlParams.get("order");
    const searchValue=urlParams.get("search");
    
    if (order) {
        document.getElementById("orderSelect").value = order;
    }
    if(searchValue){
        document.getElementById("search").value=searchValue;
    }
}
  
window.addEventListener("load", setPageFilters);

function navigateToInteract() {
    
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    
    var url = "/subjects/subjects-list?";
    var filters = [];

    if(searchValue){
        filters.push("search=" + searchValue);
    }
    if(order) {
        filters.push("order="+order);
    }
    var checkedSubcategories = document.querySelectorAll(".subcategory:checked");
    checkedSubcategories.forEach(function (checkbox) {
        if(checkbox.checked){
            filters.push("subCategory" + checkbox.value);
        }else{
            var valueToRemove = "subCategory=" + checkbox.value;
            var index = filters.indexOf(valueToRemove);
            if (index !== -1) {
                filters.splice(index, 1);
            }
        }
    });
    url += filters.join("&");
    
    window.location.href = url;
    
}

function moveOn(pageNo){

    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/subjects/subjects-list?pageNo=" + (parseInt(pageNo) + 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("search=" + searchValue);
    }
    if(order) {
        filters.push("order="+order);
    }
    var checkedSubcategories = document.querySelectorAll(".subcategory:checked");
    checkedSubcategories.forEach(function (checkbox) {
        filters.push("subCategory=" + checkbox.value);
    });
    url += filters.join("&");
    
    window.location.href = url;
}

function moveBack(pageNo){
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/subjects/subjects-list?pageNo="+ (parseInt(pageNo) - 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("search=" + searchValue);
    }
    if(order) {
        filters.push("order="+order);
    }
    var checkedSubcategories = document.querySelectorAll(".subcategory:checked");
    checkedSubcategories.forEach(function (checkbox) {
        filters.push("subCategory=" + checkbox.value);
    });
    url += filters.join("&");
    
    window.location.href = url;
}


function getSubCategory(categoryId){
    var subcategoryContainer = $('#subcategoryContainer-' + categoryId);
    if (subcategoryContainer.children().length > 0) {
        subcategoryContainer.empty();
    }else{
        $.get("/subCategories?categoryId="+categoryId,function(data){
            var subcategories='';
            data.forEach(function(item){
                subcategories+='<input type="checkbox" class="subcategory" onchange="navigateToInteract()" name="subcategory" value="' + item.id + '">' + item.name + '<br>';
            });
            $(subcategoryContainer).html(subcategories);
        });
    }
}