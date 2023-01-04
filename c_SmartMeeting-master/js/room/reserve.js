var deptJsonArray = window.parent.deptJsonArray;
var meetingRoomJsonArray = window.parent.meetingRoomJsonArray;
var employeeJsonArray = window.parent.employeeJsonArray;
var meetingJsonArray = window.parent.meetingJsonArray

$(function () {
    for (var i = 0; i < deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if (dept != undefined && dept.dname != "undefined") {
            $("#selDepartments").append("<option value='" + dept.did + "'>" + dept.dname + "</option>");
        }
    }
    fillEmployees();//根据选中的部门生成员工列表下拉框
    $("#mid").html("");
    var init = "<option value=\"1\" selected disabled style=\"display: none;\">--请选择--</option>"
    $("#mid").append(init)
    for (var i = 0; i < meetingRoomJsonArray.length; i++) {
        var meetingroom = meetingRoomJsonArray[i];
        if (meetingroom != undefined && meetingroom.name != "undefined") {
            var optionStr = "<option value='" + meetingroom.id + "'>" + meetingroom.name + "</option>";
            $("#mid").append(optionStr)
        }
    }
})

function getParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURIComponent(r[2]);
    return null;
}

//根据选中的部门生成员工列表下拉框
function fillEmployees() {
    $("#selEmployees").html("");//先清空该下拉框
    var deptid = $("#selDepartments").val();
    for (i = 0; i < employeeJsonArray.length; i++) {
        var employee = employeeJsonArray[i];
        if (employee != undefined && employee.depid == deptid && employee.status == "1") {
            $("#selEmployees").append("<option value='" + employee.id + "'>" + employee.name + "</option>");
        }
    }
}

//点击选择该员工进行参会的按钮触发的事件
function selectEmployees() {
    //获取得到所有的选中的员工列表
    var selectedEmps = $("#selEmployees>option:selected");
    for (var i = 0; i < selectedEmps.length; i++) {//将选中的员工追加到右侧的员工列表区域
        var selectedEmp = selectedEmps[i];
        //首先判断该员工原来是否有出现在右侧区域中
        //初始化定义并未出现过
        var flag = true;
        var selSelectedEmployees = $("#selSelectedEmployees option");
        for (var j = 0; j < selSelectedEmployees.length; j++) {
            var selSelectedEmployee = selSelectedEmployees[j];
            if ($(selSelectedEmployee).val() == $(selectedEmp).val()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            $("#selSelectedEmployees").append("<option value='" + $(selectedEmp).val() + "'>" + $(selectedEmp).text() + "</option>");
        }
    }
}

//点击取消选择该员工进行参会的按钮触发的事件
function deSelectEmployees() {
    //获取得到所有的选中的想要取消参加会议的员工列表
    var selSelectedEmployees = $("#selSelectedEmployees>option:selected");
    for (var i = 0; i < selSelectedEmployees.length; i++) {
        $(selSelectedEmployees[i]).remove();
    }
}

function checkIsUsed(mid, start_time, end_time) {
    var judge = true;
    for (var i = 0; i < meetingJsonArray.length; i++) {
        var meeting = meetingJsonArray[i];
        if (meeting != undefined && meeting.rid == mid && meeting.status == "1") {
            var pre_starttime = new Date(start_time.replaceAll("-", "/"));
            var pre_endtime = new Date(end_time.replaceAll("-", "/"));
            var meeting_starttime = new Date(meeting.start_time.replaceAll("-", "/"));
            var meeting_endtime = new Date(meeting.end_time.replaceAll("-", "/"));
            if (pre_starttime >= meeting_starttime && pre_starttime < meeting_endtime) {
                judge = false;
            }
            if (pre_endtime > meeting_starttime && pre_endtime <= meeting_endtime) {
                judge = false;
            }
            if (meeting_endtime > pre_starttime && meeting_endtime <= pre_endtime) {
                judge = false;
            }
            if (meeting_starttime >= pre_starttime && meeting_starttime < pre_endtime) {
                judge = false;
            }
        }
    }
    return judge
}

function checkUsedTime(mid, start_time, end_time) {
    var judge = true;
    var mark;
    for (var i = 0; i < meetingJsonArray.length; i++) {
        var meeting = meetingJsonArray[i];
        if (meeting != undefined && meeting.rid == mid && meeting.status == "1") {
            var pre_starttime = new Date(start_time.replaceAll("-", "/"));
            var pre_endtime = new Date(end_time.replaceAll("-", "/"));
            var meeting_starttime = new Date(meeting.start_time.replaceAll("-", "/"));
            var meeting_endtime = new Date(meeting.end_time.replaceAll("-", "/"));
            if (pre_starttime >= meeting_starttime && pre_starttime < meeting_endtime) {
                judge = false;
            }
            if (pre_endtime > meeting_starttime && pre_endtime <= meeting_endtime) {
                judge = false;
            }
            if (meeting_endtime > pre_starttime && meeting_endtime <= pre_endtime) {
                judge = false;
            }
            if (meeting_starttime >= pre_starttime && meeting_starttime < pre_endtime) {
                judge = false;
            }
        }
        if (!judge) {
            mark = i;
        }
    }
    return mark
}

//格式化时间
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function reserveMeeting() {
    var mname = $("#title").val();
    var start_time = $("#starttime").val();
    var end_time = $("#endtime").val();
    var num = $("#num").val();
    var mid = $("#mid").val();
    var participants = getParticipants();
    var remark = $("#remark").val();
    var now_time = new Date().Format("yyyy-MM-dd HH:mm");
    var loginUserId = getParam("loginUserId");
    var r_name;
    var room;
    var selSelectedEmployees = $("#selSelectedEmployees option");
    var r = /^\+?[1-9][0-9]*$/;　　//正整数
    var flag = r.test(num);
    for (var i = 0; i < employeeJsonArray.length; i++) {
        if (employeeJsonArray[i].id == loginUserId && employeeJsonArray[i] != undefined) {
            loginUser = employeeJsonArray[i];
            break;
        }
    }
    for (var i = 0; i < meetingRoomJsonArray.length; i++) {
        if (mid == meetingRoomJsonArray[i].id) {
            room = meetingRoomJsonArray[i]
            r_name = meetingRoomJsonArray[i].name;
        }
    }
    if (loginUser == undefined) {
        parent.swal("登录", "您还没有登录，请先登录！", "warning");
    }
    var per_name = loginUser.name
    if (mname == "") {
        // alert("请您输入会议名称")
        parent.swal("警告!", "请您输入会议名称", "warning")
    } else if (start_time == "") {
        // alert("请您输入会议开始时间")
        parent.swal("警告!", "请您输入会议开始时间", "warning")
    } else if (end_time == "") {
        // alert("请您输入会议结束时间")
        parent.swal("警告!", "请您输入会议结束时间", "warning")
    } else if (num == "") {
        // alert("请您输入会议人数")
        parent.swal("警告!", "请您输入会议人数", "warning")
    } else if (mid == undefined) {
        // alert("请您选择会议室")
        parent.swal("警告!", "请您选择会议室", "warning")
    } else if (participants == "") {
        // alert("请您选择参会人员")
        parent.swal("警告!", "请您选择参会人员", "warning")
    } else if (remark == "") {
        // alert("请您输入会议说明")
        parent.swal("警告!", "请您输入会议说明", "warning")
    } else if (loginUserId == undefined) {
        // alert("请您先登录")
        parent.swal("警告!", "请您先登录", "warning")
    } else if (start_time < now_time || end_time < end_time) {
        // alert("预定会议时间不能早于现在")
        parent.swal("警告!", "预定会议时间不能早于现在", "warning")
    } else if (start_time >= end_time) {
        parent.swal("警告!", "会议结束时间不能早于开始时间", "warning")
    } else if (flag == false) {
        parent.swal("警告!", "请输入合法的与会人数", "warning")
    } else if (room.large < num) {
        parent.swal("警告!", "会议人数大于会议室容量", "warning")
    } else if (num < selSelectedEmployees.length) {
        parent.swal("警告!", "与会人员数量大于预计参会人数", "warning")
    } else {
        if (checkIsUsed(mid, start_time, end_time)) {
            var meeting = {
                "mid": meetingJsonArray.length + 1,
                "name": mname,
                "num": num,
                "start_time": start_time,
                "end_time": end_time,
                "eid": "1",
                "rid": mid,
                "participants": participants,
                "remark": remark,
                "status": 1,
                "mtime": now_time,
                "mper": per_name,
                "mroom": r_name,
            }
            meetingJsonArray[meetingJsonArray.length] = meeting;
            parent.swal("成功", "会议预定成功!", "success")
            // alert("预定成功");
            $('#myFrame').attr('src', "MeetingRoom/reserve.html?loginUserId=" + loginUserId);
        } else {
            var mark;
            mark = checkUsedTime(mid, start_time, end_time)
            parent.swal("警告!", "预定失败！\n\n" +
                r_name + " 在 " +
                meetingJsonArray[mark].start_time + " 到 " +
                meetingJsonArray[mark].end_time + " 已经被 " +
                meetingJsonArray[mark].mper + " 预定 " +
                "\n\n请您调整会议时间", "error")
            // alert("预定失败！\n\n"+
            //     r_name+" 在 "+
            //     meetingJsonArray[mark].start_time+" 到 "+
            //     meetingJsonArray[mark].end_time + " 已经被 " +
            //     meetingJsonArray[mark].mper + " 预定 "+
            //     "\n\n请您调整会议时间")
        }
    }
}

function getParticipants() {
    var participants = "";
    //获取已经选择的员工
    var selSelectedEmployees = $("#selSelectedEmployees option");
    for (var i = 0; i < selSelectedEmployees.length; i++) {
        var selSelectedEmp = selSelectedEmployees[i];
        participants += $(selSelectedEmp).val() + ",";

    }
    return participants;
}

