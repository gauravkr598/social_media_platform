	console.log("this is home page");
	/* submit form===================================================================================*/
	 function validate() {
	    	if($("#name").val().trim() == ""){
				alert('Please enter employee  name.');
				$("#name").focus();
				return false;
		  } else if($("#username").val() == ""){
		  		alert('Please enter employee  Email.');
				$("#username").focus();
				return false;
		} else if($("#password").val() == ""){
		  		alert('Please enter employee  password.');
				$("#password").focus();
				return false;
		} else if($("#gender").val() == ""){
		  		alert('Please select gender.');
				$("#gender").focus();
				return false;
		} else if($("#phone").val() == ""){
		  		alert('Please enter employee  phone.');
				$("#phone").focus();
				return false;
		} else if($("#city").val() == ""){
		  		alert('Please select city.');
				$("#city").focus();
				return false;
		}else if(confirm("Are you sure...")){
			$('#loderButton').html("<span style='color:red'>Please wait.. </span><img src='/loader/loading.gif' title='Please your request in processing' />")
				$.post("submit-form","name="+$("#name").val()+"&username="+$("#username").val()+"&password="+$("#password").val()+"&gender="+$("#gender").val()+"&profileImage="+$("#profileImage").val()+"&phone="+$("#phone").val()+"&city="+$("#city").val(),function(data){				
				console.log(data);
				
			 	var res = data.split("#@#");
				if(res[1] == "success") {
					document.formSubmit.reset();
					$("#loderButton").html("<button type=\"button\"  class=\"btn btn-success\" onclick=\"validate()\">Submit</button>");
					$("#responseTextId").html("<p style='color:green'>"+res[2]+"</p>");
				} else {
					$("#loderButton").html("<button type=\"button\"  class=\"btn btn-success\" onclick=\"validate()\">Submit</button>");
					$("#responseTextId").html("<p style='color:red'>"+res[2]+"</p>");
				}
				});
		}
	 }
	/*checkEmail====================================================================================================*/
	function checkEmail(){
			if($("#username").val() != ""){
				$('#helpUsername').html("<span style='color:red'>Please wait.. </span><img src='/loader/loading.gif' title='Please your request in processing' />")
				$.post("checkEmail","username="+$("#username").val(),function(data){				
				console.log(data);
			 	var res = data.split("#@#");
				if(res[1] == "success"){
					document.formSubmit.reset();
					$("#helpUsername").html("<p style='color:red'>"+res[2]+"</p>");
				} else {
					$("#helpUsername").html("<p style='color:green'>"+res[2]+"</p>");
				}
				}); 
		  }
	}
	
	
	/*Post function=================================================================================================*/
	
	function userPost(){
		if($("#postTitle").val().trim() == ""){
				alert('Please enter user title.');
				$("#postTitle").focus();
				return false;
		  } else if($("#postImage").val() == ""){
		  		alert('Please select postImage.');
				$("#postImage").focus();
				return false;
		}
	}
	
	/* Like Section  =================================================================================================*/
	 
	function doLike(postId,userId){
		console.log(postId+" ," +userId)
		$.post("LikeServlet","postId="+postId+"&userId="+userId,function(data){
			console.log(data);
			$("#showcomment").html('<span id="showcomment">"+data+"</span>');
		});
	}
	
    	   function viewUserDetails(userId){
    		   console.log(userId)
    			$.post("user-details","userId="+userId,function(data){
    				console.log(data);
						var str ="<table class=\"table\">"
							str+="<thead><tr><th>Name</th><th>Email</th><th>Gender</th><th>Phone</th><th>City</th></tr></thead><tbody>"
    						str += "<tr><td>"+data.name+"</td><td>"+data.username+"</td><td>"+data.gender+"</td><td>"+data.phone+"</td><td>"+data.city+"</td>"
    				str+="</tbody></table>";
    				$('#ResponseResult').html(str);
    			});
    	   } 
   	   
  
	  //update user details ===================================================================
	  function updateUserValidate(){
	  	if(confirm("Are you sure...")){
	  			$('#loderButton').html("<span style='color:red'>Please wait.. </span><img src='../loader/loading.gif' title='Please your request in processing' />")
	  			 
	  			$.ajax({
		            type: "POST",
		            enctype: "multipart/form-data",
		            url: "update-user-details",
		            data : 'data',
		            processData: false,
            		contentType: false, 
		            success: function (data) {
		                console.log(data);
		            },
		        });
	  			console.log("hello");
	      }
	  }  	   
    	   
    //check Password
    	function checkPassword(oldPassword){
    			console.log(oldPassword);
				$('#helpUsername').html("<span style='color:red'>Please wait.. </span><img src='/loader/loading.gif' title='Please your request in processing' />")
				$.post("checkPassword","oldPassword="+oldPassword,function(data){				
				console.log(data);
			 	var res = data.split("#@#");
				if(res[1] == "success"){
					$("#helpUsername").html("<p style='color:red'>"+res[2]+"</p>");
				} else {
					$("#helpUsername").html("<p style='color:green'>"+res[2]+"</p>");
				}
				}); 
		  }
	    
    	   
  //change Password  	
   function changePassword(){
			if($("#newpassword").val() != ""){
				$('#buttonLoading').html("<span style='color:red'>Please wait.. </span><img src='/loader/loading.gif' title='Please your request in processing' />")
				$.post("change-password","newpassword="+$("#newpassword").val(),function(data){				
				console.log(data);
			 	var res = data.split("#@#");
				if(res[1] == "success"){
					document.form.reset();
					$("#responseResult").html("<p style='color:red'>"+res[2]+"</p>");
				} else {
					$("#responseResult").html("<p style='color:green'>"+res[2]+"</p>");
				}
				}); 
		  }
	}	      
    	   
   //view full image =======================================================================
		   function fullImage(userId){
		  		 $.post("viewImage","userId="+userId,function(data){				
						console.log(data);
	    				  $('#ResponseImage').html("<img  src='/image/data' class='user_profile' />");
						 });
						
		    	   
		    	   }
    	
   //Search Box ==========================================================================
		   //search contact function...................
			  const search = () => {
				console.log("searching....");
				let query = $("#search-input").val();
				if(query==''){
					$(".search-result").hide();
				}else{
					//sending request to srver....
					let url="http://localhost:8080/user/search/"+query+"";
					fetch(url).then((response) =>{
						return response.json();
					}).then((data) => {
						//data
						console.log(data);
						let text='<div class=\"alert1\">\n'
						for (var i = 0; i < data.length; i++){
							  var obj = data[i];
						for(var key in obj){
								var value = obj[key];
						}
						text+="<div>"
						text+='<a href="/user/search-user/'+obj['userId']+'" class="SearchLink"><img src=\"../image/'+obj['image']+'\"  alt=\"hello gaurav\" class=\"contact_profile\" ><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; '+obj['name']+' <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+obj['city']+'</span> </a>'
						+"<br><br>"
						text+="<div>"
						}
						text+='</div>';
						$(".search-result").html(text);
						$(".search-result").show();
					});
					console.log(query);
				}
				};
				
				
				
                //Send Request =======================================
     
    			const sendRequest = (friendId) => {
				console.log(friendId);
					//sending request to srver....
					let url="http://localhost:8080/user/sendrequest/"+friendId+"";
					fetch(url).then((response) =>{
						return response.json();
					}).then((data) => {
						//data
						var responseText=data;
						if(responseText>0){
							responseText="Success";
							console.log(responseText);
						}else{
							responseText="error";
							console.log(responseText);
						}
						$("#responsebutton1").hide();
						$("#responsebutton2").html("<button id=\"responsebutton2\" class=\"btn btn-primary \" type=\"button\">request Send</button>");
						
			});
			}
    	
    	
    	
    	   
    	   
    	   