var adminID=null;
$(document).ready(function() {
	
    $("#adminLoginButton").click(function() {
        $(".AdminLoginDiv").show();
    });
        $(".changePasswordButton").click(function() {
        $(".changePasswordDiv").show();
    });
    
       $("#ProductsListButtonID").click(function() {
        $("#ProductDetailsDiv").show();
        $(".addNewProductButtonDiv").show();
        $("#UserDetailsDiv").hide();
        $("#PurchaseReportDiv").hide();
        $(".changePasswordDiv").hide();
        
    });
    $("#userListButtonID").click(function() {
        $("#UserDetailsDiv").show();
        $("#ProductDetailsDiv").hide();
        $("#PurchaseReportDiv").hide();
        $(".addNewProductButtonDiv").hide();
        $(".addNewProductDiv").hide();
        $(".changePasswordDiv").hide();
        
        
    });
        $("#PurchaseReportListButtonID").click(function() {
        $("#PurchaseReportDiv").show();
       $("#ProductDetailsDiv").hide();
       $("#UserDetailsDiv").hide();
       $(".addNewProductButtonDiv").hide();
       $(".addNewProductDiv").hide();
       $(".changePasswordDiv").hide();
    });
    
        $("#closeButtonChangePasswordDiv").click(function() {
        $(".changePasswordDiv").hide();
    });
    $("#closeButtonadminDiv").click(function() {
        $(".AdminLoginDiv").hide();
    });
    
    
        $(".SubmitNewPasswordButtonInputClass").click(function() {
     $.ajax({
            url: "http://localhost:8080/changeAdminPassword",
            type: 'PUT',
            data: {
				adminId: adminID,
                oldPassword: $('#oldAdminPassword').val(),
                newPassword: $('#newAdminPassword').val()
            },
            success: function(data) {
			alert(data);
			$(".changePasswordDiv").hide();
}
    });
    });
    
    
    
    $(".SubmitButtonInputClass").click(function() {
        $.ajax({
            url: "http://localhost:8080/adminLoginValidation",
            type: 'post',
            data: {
                adminUserId: $('#AdminLoginId').val(),
                password: $('#adminPassword').val()
            },
            success: function(data) {
	 			var pasrsedJson = JSON.parse(data);
	 			adminID=pasrsedJson.adminId;
				if(pasrsedJson.Result=="success"){
				$(".AdminLoginDiv").hide();
				$(".InnerContainer").hide();
				$(".headerDiv").hide();
				$("#AdminDiv").show();
                let userDetailsTable="<input type='text' id='myInput' onkeyup='searchUserByName()' placeholder='Search by user names..' title='Type in a name'><table class= 'table' id='UserDetailsTableId'><th scope='col'>#</th><th scope='col'>User Name</th><th scope='col'>User Email</th>";
                let productDetailsTable = "<table class = 'table' id='productDetailsTableId' ><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Product Price</th><th scope='col'>Product Category</th><th scope='col'>Update</th>"
                let purchasedDetailsTable="<table class= 'table' id='purchaseDetailsTableId'><thead><tr><th scope='col'>#</th><th scope='col'>Purchase Date</th><th scope='col'>Product Category</th><th scope='col'>Product Name</th><th scope='col'>User Name</th><th scope='col'>User Email</th> <th scope='col'>Product Price</th></tr></thead><tbody>";
                var productSNo=1;
                var userSno=1;
                var purchaseSno=1;
                for (let x in pasrsedJson.ProductDetails) {
					var productNameWithoutSpace=pasrsedJson.ProductDetails[x].ProductName;
					productNameWithoutSpace=productNameWithoutSpace.split(' ').join('_');
                    productDetailsTable += "<tr><th scope='row'>"+productSNo+"</th><td>" + pasrsedJson.ProductDetails[x].ProductName + "</td><td>" + pasrsedJson.ProductDetails[x].ProductPrice + "</td><td>" + pasrsedJson.ProductDetails[x].ProdcutCategory + "</td><td><button id='updateProductButton' onclick=updateProductWithId("+pasrsedJson.ProductDetails[x].ProductID+",'"+productNameWithoutSpace+"',"+pasrsedJson.ProductDetails[x].ProductPrice+")>Update</button></td></tr>";
                productSNo++;
                }
                for (let x in pasrsedJson.UserList) {
                    userDetailsTable += "<tr><th scope='row'>"+userSno+"</th><td>" + pasrsedJson.UserList[x].UserName + "</td><td>" + pasrsedJson.UserList[x].UserEmail + "</td></tr>";
                userSno++;
                }
                for (let x in pasrsedJson.PurchaseReport) {
                    purchasedDetailsTable += "<tr><th scope='row'>"+purchaseSno+"</th><td>" + pasrsedJson.PurchaseReport[x].PurchasedDate + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedProductType + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedProductName + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedUser + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedUserEmailId + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedPrice + "</td></tr>";
                purchaseSno++;
                }
                userDetailsTable += "</table>";
                productDetailsTable += "</table>";
                purchasedDetailsTable += "</tbody></table>";
                $("#PurchaseReportDiv").append(purchasedDetailsTable);
                $("#ProductDetailsDiv").append(productDetailsTable);
                $("#UserDetailsDiv").append(userDetailsTable);
            }else{
				$(".AdminLoginDiv").append("<div id='invaildUserIdPasswordDisplayDiv'><p style='color:red'>Invaild Username or Password!</p><div>");
			}
            }
        });

$('.Filter').on('change', function() {
  var value= this.value ;
  if(value=="date")
  {
	sortByDate();
}else{
	sortTableByCategory();
}
});
});
var header = document.getElementById("sidenav");
var btns = header.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function() {
  var current = document.getElementsByClassName("active");
  current[0].className = current[0].className.replace(" active", "");
  this.className += " active";
  });
}

});//document.ready End 
function addingNewProduct() {
        $.ajax({
            url: "http://localhost:8080/addNewProduct",
            type: 'post',
            data: {
                newProductName  : $('.newProductNameInput').val(),
                newProductPrice : $('.newProductPricecInput').val(),
                productCategoryId : $('.ProductCategories').find(":selected").val()
            },
            success: function(data) {
		if(data=='Succesful'){
			
			$("#main").load(" #main > *");
			alert("Product added Successfully");
			$.ajax({
            url: "http://localhost:8080/getAllDetails",
            type: 'GET',
            success: function(data) {
				$(".AdminLoginDiv").hide();
				$(".InnerContainer").hide();
				$(".headerDiv").hide();
				$("#AdminDiv").show();
                var pasrsedJson = JSON.parse(data);
                let userDetailsTable="<input type='text' id='myInput' onkeyup='searchUserByName()' placeholder='Search by user names..' title='Type in a name'><table class= 'table' id='UserDetailsTableId'><th scope='col'>#</th><th scope='col'>User Name</th><th scope='col'>User Email</th>";
                let productDetailsTable = "<table class = 'table' id='productDetailsTableId' ><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Product Price</th><th scope='col'>Product Category</th><th scope='col'>Update Product</th>"
                let purchasedDetailsTable="<table class= 'table' id='purchaseDetailsTableId'><thead><tr><th scope='col'>#</th><th scope='col'>Purchase Date</th><th scope='col'>Product Category</th><th scope='col'>Product Name</th><th scope='col'>User Name</th><th scope='col'>User Email</th> <th scope='col'>Product Price</th></tr></thead><tbody>";

                var productSNo=1;
                var userSno=1;
                var purchaseSno=1;
                for (let x in pasrsedJson.ProductDetails) {
					var productNameWithoutSpace=pasrsedJson.ProductDetails[x].ProductName;
					productNameWithoutSpace=productNameWithoutSpace.split(' ').join('_');
                    productDetailsTable += "<tr><th scope='row'>"+productSNo+"</th><td>" + pasrsedJson.ProductDetails[x].ProductName + "</td><td>" + pasrsedJson.ProductDetails[x].ProductPrice + "</td><td>" + pasrsedJson.ProductDetails[x].ProdcutCategory + "</td><td><button id='updateProductButton' onclick=updateProductWithId("+pasrsedJson.ProductDetails[x].ProductID+",'"+productNameWithoutSpace+"',"+pasrsedJson.ProductDetails[x].ProductPrice+")>Update</button></td></tr>";
                	productSNo++;
                }
                for (let x in pasrsedJson.UserList) {
                    userDetailsTable += "<tr><th scope='row'>"+userSno+"</th><td>" + pasrsedJson.UserList[x].UserName + "</td><td>" + pasrsedJson.UserList[x].UserEmail + "</td></tr>";
                	userSno++;
                }
                for (let x in pasrsedJson.PurchaseReport) {
                    purchasedDetailsTable += "<tr><th scope='row'>"+purchaseSno+"</th><td>" + pasrsedJson.PurchaseReport[x].PurchasedDate + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedProductType + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedProductName + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedUser + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedUserEmailId + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedPrice + "</td></tr>";
                	purchaseSno++;
                }
                	userDetailsTable += "</table>";
                	productDetailsTable += "</table>";
                	purchasedDetailsTable += "</tbody></table>";
                	$("#PurchaseReportDiv").append(purchasedDetailsTable);
                	$("#ProductDetailsDiv").append(productDetailsTable);
                	$("#UserDetailsDiv").append(userDetailsTable);
            }
        });
		}else
		{
			alert("Product Cannot be updated");
		}
	}
    });
    }
function addNewProduct(){
	$(".addNewProductDiv").show();
        $.ajax({
            url: "http://localhost:8080/getProductCategory",
            type: 'GET',
            success: function(data) {
			 var productCategoryJson = JSON.parse(data);
			 let selectCategory="<select class='ProductCategories'><option selected style='display: none'>Product Catetgories</option>";
			 for (let x in productCategoryJson.ProductCategories) {
			 selectCategory+="<option value="+productCategoryJson.ProductCategories[x].ProductCategoryID+">"+productCategoryJson.ProductCategories[x].ProductCategoryName+"</option>";
			 }
			 selectCategory+="</select>";
			 var inputs="<h3>Enter Details of Product</h3><br><input type='text' class='newProductNameInput' placeholder='Enter New Product Name'><br><input type='text' class='newProductPricecInput' placeholder='Enter New Product Price'><br> <button id='addingNewProductButtonId' onclick=addingNewProduct()>Click To Add New Product</button><br>";
			 $(".addNewProductDiv").html(inputs);
			 $(".addNewProductDiv").append(selectCategory);	 
	}
	});  
}
function searchUserByName() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("UserDetailsTableId");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
				
				function convertDate(d) {
  var p = d.split("-");
  return +(p[2]+p[1]+p[0]);
}
function sortByDate() {
  var tbody = document.querySelector("#purchaseDetailsTableId tbody");
  // get trs as array for ease of use
  var rows = [].slice.call(tbody.querySelectorAll("tr"));
  
  rows.sort(function(a,b) {
    return convertDate(a.cells[1].innerHTML) - convertDate(b.cells[1].innerHTML);
  });
  
  rows.forEach(function(v) {
    tbody.appendChild(v); // note that .appendChild() *moves* elements
  });
}
function sortTableByCategory() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("purchaseDetailsTableId");
  switching = true;
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[1];
      y = rows[i + 1].getElementsByTagName("TD")[1];
      if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}
function updateProductWithId(productId,productName,productPrice){
		$(".updateDiv").show();
		productName=productName.split('_').join(' ');
		
	var inputs="<button class='btn-close' aria-label='Close' id='closeButtonUpdateProductDiv'></button><br><h3>Update Name and Price of the Product</h3><br><br>Product Name : <input type='text' class='inputForProductName' value='"+productName+"'><br>Product Price : <input type='text' class='inputForProductPrice' value='"+productPrice+"'><br><br><button id='updatingsProductButton' onclick=updatingProduct("+productId+",'"+productName.split(' ').join('_')+"',"+productPrice+")>Click to Update</button>";
    $(".updateDiv").html(inputs);
}
function updatingProduct(productId,productName,productPrice){
	 $("#closeButtonUpdateProductDiv").click(function() {
        $(".updateDiv").hide();
    });
	var newProductValue=$('.inputForProductName').val();
    var newProductPrice=$('.inputForProductPrice').val();
    productName=productName.split('_').join(' ');
    if(productName===newProductValue && productPrice==newProductPrice ){
	alert("Cannot update same value! Please Enter new Value");
}else{
    $.ajax({
	url: "http://localhost:8080/productUpdate",
    type: 'put',
	data: {		
				productId : productId,
                newProductValue: newProductValue,
                newProductPrice: newProductPrice
          },
          success: function(data) {
		if(data=='Succesful'){
			
			$("#main").load(" #main > *");
			alert("Product Updated Successfully");
			$.ajax({
            url: "http://localhost:8080/getAllDetails",
            type: 'GET',
            success: function(data) {
				$(".AdminLoginDiv").hide();
				$(".InnerContainer").hide();
				$(".headerDiv").hide();
				$("#AdminDiv").show();
                var pasrsedJson = JSON.parse(data);
                let userDetailsTable="<input type='text' id='myInput' onkeyup='searchUserByName()' placeholder='Search by user names..' title='Type in a name'><table class= 'table' id='UserDetailsTableId'><th scope='col'>#</th><th scope='col'>User Name</th><th scope='col'>User Email</th>";
                let productDetailsTable = "<table class = 'table' id='productDetailsTableId' ><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Product Price</th><th scope='col'>Product Category</th><th scope='col'>Update Product</th>"
                let purchasedDetailsTable="<table class= 'table' id='purchaseDetailsTableId'><thead><tr><th scope='col'>#</th><th scope='col'>Purchase Date</th><th scope='col'>Product Category</th><th scope='col'>Product Name</th><th scope='col'>User Name</th><th scope='col'>User Email</th> <th scope='col'>Product Price</th></tr></thead><tbody>";

                var productSNo=1;
                var userSno=1;
                var purchaseSno=1;
                for (let x in pasrsedJson.ProductDetails) {
					var productNameWithoutSpace=pasrsedJson.ProductDetails[x].ProductName;
					productNameWithoutSpace=productNameWithoutSpace.split(' ').join('_');
                    productDetailsTable += "<tr><th scope='row'>"+productSNo+"</th><td>" + pasrsedJson.ProductDetails[x].ProductName + "</td><td>" + pasrsedJson.ProductDetails[x].ProductPrice + "</td><td>" + pasrsedJson.ProductDetails[x].ProdcutCategory + "</td><td><button id='updateProductButton' onclick=updateProductWithId("+pasrsedJson.ProductDetails[x].ProductID+",'"+productNameWithoutSpace+"',"+pasrsedJson.ProductDetails[x].ProductPrice+")>Update</button></td></tr>";
                	productSNo++;
                }
                for (let x in pasrsedJson.UserList) {
                    userDetailsTable += "<tr><th scope='row'>"+userSno+"</th><td>" + pasrsedJson.UserList[x].UserName + "</td><td>" + pasrsedJson.UserList[x].UserEmail + "</td></tr>";
                	userSno++;
                }
                for (let x in pasrsedJson.PurchaseReport) {
                    purchasedDetailsTable += "<tr><th scope='row'>"+purchaseSno+"</th><td>" + pasrsedJson.PurchaseReport[x].PurchasedDate + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedProductType + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedProductName + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedUser + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedUserEmailId + "</td><td>" + pasrsedJson.PurchaseReport[x].PurchasedPrice + "</td></tr>";
                	purchaseSno++;
                }
                	userDetailsTable += "</table>";
                	productDetailsTable += "</table>";
                	purchasedDetailsTable += "</tbody></table>";
                	$("#PurchaseReportDiv").append(purchasedDetailsTable);
                	$("#ProductDetailsDiv").append(productDetailsTable);
                	$("#UserDetailsDiv").append(userDetailsTable);
            }
        });
		}else
		{
			alert("Product Cannot be updated");
		}
}
})
}
}



