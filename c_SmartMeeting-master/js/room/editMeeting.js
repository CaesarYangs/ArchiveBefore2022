//接收地址栏传递来的参数
//部门信息列表中查询寻指定部门编号的信息

var mid = getParam("mid");
var meetingJsonArray = window.parent.meetingJsonArray;

$(function (){
    for(var i=0; i<meetingJsonArray.length; i++){
        var meeting = meetingJsonArray[i];
        if(dept.mid==mid){
            $("#name").val(meeting.name);
            $("#starttime").val(meeting.start_time);
            $("#remark").val()(meeting.remark);
            break
        }
    }
});

function updateDept(){
    //获取用户输入的修改后的信息
    var dname = $("#name").val();
    var desc = $("#remark").val();

    for(var i=0; i<deptJsonArray.length; i++){
        var dept = deptJsonArray[i];
        if(dept.did==did){
            dept.dname = dname;
            dept.desc = desc;
            alert("修改成功");
            window.location.href = "../../room/.html"
        }
    }
}
