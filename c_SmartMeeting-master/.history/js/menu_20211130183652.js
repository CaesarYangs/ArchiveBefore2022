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

function reserveMeetingPage(){
	checkMenu("reserveMeetingPage");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/reserve.html");
}

function addMeeting(){
	checkMenu("addmeeting");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/meet/addMeet.html");
}

function showMeetingRoom(){
	checkMenu("showmeetingroom");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "MeetingRoom/meet/meeting.html");
}

//当点击部门管理时，触发的事件
function deptSearch(){
	checkMenu("deptSearch");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "deptList.html");
}

function meetSearch(){
	checkMenu("meetSearch");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "person/meetList.html");
}

function meetRer(){
	checkMenu("meetRer");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "person/meetRer.html");
}

function meetNotice(){
	checkMenu("meetNotice");
	//让id="myFrame"的链接指向到用户列表页面
	$('#myFrame').attr('src', "person/meetNotice.html");
}


//改变选中菜单样式
function checkMenu(menuA){
	//找到当前活跃的二级菜单
	var subactive = $(".sub-menu>li>a.subactive");
	//如果现在点击的菜单不是当前活跃的菜单，证明需要改变样式
	if(subactive.text()!=$("#"+menuA).text()){
		$(subactive).parent().css("background","#FFFFFF");
		$(subactive).removeClass("subactive");
		$("#"+menuA).attr("class","subactive");
		$(menuA).parent().css("background","#FFFFFF");
	}
}
