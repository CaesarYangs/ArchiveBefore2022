//初始化数据
var loginUser;

//初始化员工列表
var employeeJsonArray;
$.ajax({
    url:"../Json/employee.json",
    type:"get",
    dataType:"json",
    async:false,
    success: function (res){
        console.log(res);
        employeeJsonArray = res;
    },error: function(XMLHttpRequest, textStatus, errorThrown) {
        // alert(XMLHttpRequest.status);
        // alert(XMLHttpRequest.readyState);
        // alert(textStatus);
        alert("error");
    }
})

var deptJsonArray
$.ajax({
    url:"../Json/depts.json",
    type:"get",
    dataType:"json",
    success: function (res){
        console.log(res);
        deptJsonArray = res;
    },error: function(XMLHttpRequest, textStatus, errorThrown) {
        // alert(XMLHttpRequest.status);
        // alert(XMLHttpRequest.readyState);
        // alert(textStatus);
        alert("error");
    }
})




var meetingJsonArray;
$.ajax({
    contentType: false,
    processData: false,
    async:false,
    url:"json/meetings.json",
    dataType:"json",
    success:function(resp){
        meetingJsonArray = resp;
    }
});

var meetingRoomJsonArray;
$.ajax({
    contentType: false,
    processData: false,
    async:false,
    url:"json/meetingRoom.json",
    dataType:"json",
    success:function(resp){
        meetingRoomJsonArray = resp;
    }
});

var meetJsonArray;
$.ajax({
    url:"json/meeting.json",
    dataType:"json",
    success:function(resp){
        meetJsonArray=resp;
    }
})