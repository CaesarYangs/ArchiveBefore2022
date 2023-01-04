var loginUser = window.parent.loginUser;
$(function(){
	
	//每1秒刷新一次时间
    setInterval(function(){
    	$("#nowTime").html(getTime());
    },1000);
    
    /**********以下部分是菜单的一二级的相关动态样式**********/
    //活动下的一级菜单的高度是自动填充的
	$(".active").css("height","auto");
	//设置活动下的一级菜单下的二级菜单的背景颜色
	$(".active").children(".sub-menu").css("background","#FFFFFF");
	//设置活动的二级菜单的背景颜色及字体颜色
	$(".subactive").parent().css("background","#FFFFFF").css("color","#FFFFFF");
	
	//当点击一级菜单的超链接时，触发的事件
	$(".menu>li>a").click(function(){
		//获取当前点击的一级菜单下的二级菜单的显示状态
		var display = $(this).siblings(".sub-menu").css("display");
		//如果当前点击的一级菜单下的二级菜单时隐藏的
		if(display=="none"){
			//则让二级菜单显示
			// $(this).siblings(".sub-menu").show();
			$(this).siblings(".sub-menu").slideDown(500);
		}else{
			//否则让二级菜单隐藏
			// $(this).siblings(".sub-menu").hide();
			$(this).siblings(".sub-menu").slideUp(500);
		}
		//设置当前一级菜单的高度为自动适应
		$(this).parent().css("height","auto");
		//设置当前点击的一级菜单下的二级菜单的背景颜色
		$(this).siblings(".sub-menu").css("background","#FFFFFF");
		$(this).css("text-decoration","none");
	});
	//当点击二级菜单的超链接时，触发的事件
	$(".sub-menu>li>a").click(function(){
		// $(this).css("text-decoration","none");
		$(this).parent().css("background","#C4C4C4").css("color","#FFFFFF");
	});
	//当鼠标移入二级菜单的超链接时，触发的事件
	$(".sub-menu>li>a").mouseover(function(){
		// $(this).parent().css("background","#FFFFFF").css("color","#FFFFFF");
		$(this).css("text-decoration","none");
	});
	//当鼠标移出二级菜单的超链接时，触发的事件
	$(".sub-menu>li>a").mouseout(function(){
		if(!$(this).hasClass("subactive")){
			$(this).parent().css("background","#FFFFFF ");
		}
	});
	/**********以上部分是菜单的一二级的相关动态样式**********/
	
	syncUser();
	


	if(loginUser.auditstatus=="1"){
		parent.swal("管理员登录成功!", "欢迎您："+loginUser.name, "success")
	}else{
		parent.swal("普通用户登录成功!", "欢迎您："+loginUser.name, "success")
	}

	

});

function expandAll(){
	var display = $(".sub-menu").css("display");
	if(display=="none"){
		$(".sub-menu").slideDown(500,"swing");
	}else{
		$(".sub-menu").slideUp(500);
	}
}

var loginUserName;
function syncUser(){
	var loginUserId = getParam("loginUserId");
		for (var i=0; i<employeeJsonArray.length; i++) {
			if (employeeJsonArray[i].id == loginUserId && employeeJsonArray[i]!=undefined) {
				loginUser = employeeJsonArray[i];
				// alert('欢迎登陆：id'+loginUser.id);
				break;
			}
		}
	
	if(loginUser!=undefined){
		$("#loginSpan").html("欢迎登录,"+loginUser.name);
		loginUserName=loginUser.name;
	}
	
	
}


function changePassword(){
	if(loginUser==undefined){
		alert("当前为调试模式 无需修改密码");
		return;
	}
	id = loginUser.id;	

	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();


	if(oldPassword==""){
		parent.swal("警告!", "请输入原密码","error")
		// alert("请输入原密码");
		return;
	}
	if(newPassword==""){
		parent.swal("警告!", "请输入新密码","error")
		// alert("请输入新密码");
		return;
	}
	if(confirmPassword==""){
		parent.swal("警告!", "请输入确认密码","error")
		// alert("请输入确认密码");
		return;
	}
	if(loginUser.password!=oldPassword){
		parent.swal("警告!", "原密码错误","error")
		// alert("原密码错误！");
		return;
	}
	if(newPassword!=confirmPassword){
		parent.swal("警告！", "两次输入的密码不一致","error")
		// alert("两次输入的密码不一致");
		return;
	}
	if(oldPassword==newPassword){
		parent.swal("警告！", "新密码与原密码相同","error")
		// alert("新密码与原密码相同");
		return;
	}
	parent.swal("修改成功!", '您的新密码是:'+newPassword,"success")
	// alert("修改成功");
	loginUser.password=newPassword;
	// window.parent.location.href="login.html";
	// alert('您的新密码是:'+loginUser.password)
	
	
	// if(oldPassword==loginUser.password && newPassword==confirmPassword){
	// 	// loginUser.password=newPassword;
	// 	alert("修改成功");
	// 	window.location.href="login.html";
	// 	// $.ajax({
	// 	// 	type:"post",
	// 	// 	url:"/changePassword",
	// 	// 	data:{
	// 	// 		"id":id,
	// 	// 		"password":newPassword
	// 	// 	},
	// 	// 	success:function(data){
	// 	// 		if(data=="success"){
	// 	// 			alert("修改成功");
	// 	// 			window.location.href="login.html";
	// 	// 		}else{
	// 	// 			alert("修改失败");
	// 	// 		}
	// 	// 	}
	// 	// });
	// }
}

function cancelChange(){
	window.parent.location.href="login.html";
	
}
