//当点击部门管理时，触发的事件
function deptSearch(){
	checkMenu("deptSearch");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "PeopleManagement/dept/deptList.html");
}

function addPeoplePage(){
	checkMenu("addPeople");
	$('#myFrame').attr('src', "PeopleManagement/people/addPeople.html");
}

function showPeople(){
	checkMenu("showPeople");
	$('#myFrame').attr('src', "PeopleManagement/people/peopleList.html");
}

function regCheck(){
	checkMenu("regCheck");
	$('#myFrame').attr('src', "PeopleManagement/people/regCheck.html");
}

function meetingSearch(){
	checkMenu("meetingSearch");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/findRoom.html");
}

function reserveMeetingPage(loginUserId){
	checkMenu("reserveMeetingPage");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/reserve.html?loginUserId="+loginUserId);
}

function addMeeting(){
	checkMenu("addmeeting");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/meet/checkmeet.html");
}

function showMeetingRoom(){
	checkMenu("showmeetingroom");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/meet/meeting.html");
}

function meetSearch(){
	checkMenu("meetSearch");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "person/meetList.html");
}

function meetRer(loginUserId){
	checkMenu("meetRer");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "person/meetRer.html?loginUserId="+loginUserId);
}

function meetNotice(){
	checkMenu("meetNotice");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "person/meetNotice.html");
}

function showStatistics(){
	checkMenu("showStatistics")
	// $('#myFrame').attr('src', "Statistics/employeeStatistics.html");
	$('#myFrame').attr('src', "Statistics/navStatistics.html");
}

function adjust(){
	checkMenu("adjustment");
	$('#myFrame').attr('src', "Settings/adjust.html");
}

function aboutus(){
	checkMenu("aboutus");
	$('#myFrame').attr('src', "Settings/aboutus.html");
}

function gohome(){
	checkMenu("");
	$('#myFrame').attr('src', "../index.html");
}
function logout(){
	loginUser = null;
	window.location.href="login.html";
}

function changePwd(){
	// $('#passWindow').modal();
	$('#myFrame').attr('src', "changePwd.html")
	// $('#myFrame').attr('src', "index.html");
}

function Index(){
	$('#myFrame').attr('src', "index.html");
}

function test1(){
	checkAMenu("test1");
}

//改变选中菜单样式
function checkMenu(menuA){
	//找到当前活跃的二级菜单
	var subactive = $(".sub-menu>li>a.subactive");
	//如果现在点击的菜单不是当前活跃的菜单，证明需要改变样式
	if(subactive.text()!=$("#"+menuA).text()){
		$(subactive).parent().css("background","#FFFFFF");
		$(subactive).css("color","#586575");
		$(subactive).removeClass("subactive");
		$("#"+menuA).attr("class","subactive");
		$("#"+menuA).css("color","#1B6DE8");
		$(menuA).parent().css("background","#FFFFFF");
		$(menuA).parent().css("textcolor","#2885e2");
	}
}

function checkAMenu(menuA){
	var active = $(".menu>li>a.subactive");

	if(active.text()!=$("#"+menuA).text()){
		$(active).parent().css("background","#FFFFFF");
		$(active).css("color","#586575");
		$(active).removeClass("subactive");
		$("#"+menuA).attr("class","subactive");
		$("#"+menuA).css("color","#1B6DE8");
		$(menuA).parent().css("background","#FFFFFF");
		// $(menuA).parent().css("textcolor","#2885e2");
	}
}


