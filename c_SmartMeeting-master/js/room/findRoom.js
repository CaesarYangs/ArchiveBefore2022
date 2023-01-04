var meetingJsonArray = window.parent.meetingJsonArray
var employeeJsonArray = window.parent.employeeJsonArray;
var meetingRoomJsonArray = window.parent.meetingRoomJsonArray;

$(function () {
    //页面被加载完成后，立即会被执行的操作
    //获取会议信息列表，每一个部门对象生成遗憾tr存放在会议表格
    for (var i = 0; i < meetingJsonArray.length; i++) {
        var meeting = meetingJsonArray[i];
        for (var j = 0; j < meetingRoomJsonArray.length; j++) {
            if (meetingRoomJsonArray[j].id == meeting.rid) {
                Room = meetingRoomJsonArray[j];
            }
        }
        if(Room.name !="undefined" && meeting.name != "undefined"){
            for (var j = 0; j < meetingRoomJsonArray.length; j++) {
                var Room = meetingRoomJsonArray[j];
                if (Room.id == meeting.rid) {
                    var trStr = "<tr>" +
                        "<td>" + meeting.name + "</td>" +
                        "<td>" + Room.name + "</td>" +
                        "<td>" + meeting.start_time + "</td>" +
                        "<td>" + meeting.end_time + "</td>" +
                        "<td>" + meeting.remark + "</td>" +
                        "<td>" + meeting.mper + "</td>" +
                        "</tr>";
                    $("#findRoom").append(trStr);
                }
            }
        }
    }
    setPages()
    $("#room").html("");
    var init = "<option value=\"1\" selected disabled style=\"display: none;\">--请选择--</option>";
    $("#room").append(init);
    init = "<option value=''>" + "不限制" + "</option>";
    $("#room").append(init);
    for (var i = 0; i < meetingRoomJsonArray.length; i++) {
        var meetingroom = meetingRoomJsonArray[i];
        if (meetingroom != undefined && meetingroom.name != "undefined") {
            var optionStr = "<option value='" + meetingroom.id + "'>" + meetingroom.name + "</option>";
            $("#room").append(optionStr)
        }
    }
})

function setPages() {
    $('.pagination-container').html("")

    $('.table tbody').paginathing({
        perPage: 6,
        insertAfter: '.table',
        pageNumbers: true
    })
}

function searchMeeting() {
    var meeting_name = $("#meetingName").val();
    var per_name = $("#pername").val();
    var room_get = $("#room").val();
    var room = ""
    if (room_get != null) {
        room = room_get;
    }
    var count_list = 0;
    $("#findRoom").html("");

    for (var i = 0; i < meetingJsonArray.length; i++) {
        var meeting = meetingJsonArray[i];
        var room_id = meeting.rid
        var rid = room_id.toString();
        var Room
        for (var j = 0; j < meetingRoomJsonArray.length; j++) {
            if (meetingRoomJsonArray[j].id == meeting.rid) {
                Room = meetingRoomJsonArray[j];
            }
        }
        if (meeting != undefined) {
            var isShow = false;
            if (Room.name == "undefined"|| meeting.name == "undefined") {
                isShow = false
            } else {
                if (meeting_name != "" || per_name != "" || room != "") {
                    //判断当前会议是否满足过滤条件
                    if (meeting_name != "" && per_name == "" && room == "") {
                        if (meeting.name.indexOf(meeting_name) != -1) {
                            isShow = true;
                        }
                    } else if (meeting_name == "" && per_name == "" && room != "") {
                        if (room == rid) {
                            isShow = true;
                        }
                    } else if (meeting_name == "" && per_name != "" && room == "") {
                        if (meeting.mper.indexOf(per_name) != -1) {
                            isShow = true;
                        }
                    } else if (meeting_name != "" && per_name != "" && room == "") {
                        if (meeting.name.indexOf(meeting_name) != -1 && meeting.mper.indexOf(per_name) != -1) {
                            isShow = true;
                        }
                    } else if (meeting_name == "" && per_name != "" && room != "") {
                        if (room == rid && meeting.mper.indexOf(per_name) != -1) {
                            isShow = true;
                        }
                    } else if (meeting_name != "" && per_name == "" && room != "") {
                        if (room == rid && meeting.name.indexOf(meeting_name) != -1) {
                            isShow = true;
                        }
                    } else {
                        if (meeting.name.indexOf(meeting_name) != -1 && meeting.mper.indexOf(per_name) != -1 && room == rid) {
                            isShow = true;
                        }
                    }
                } else {
                    isShow = true;
                }
                if (isShow) {
                    var Room
                    for (var j = 0; j < meetingRoomJsonArray.length; j++) {
                        if (meetingRoomJsonArray[j].id == meeting.rid) {
                            Room = meetingRoomJsonArray[j];
                        }
                    }
                    var trStr = "<tr>" +
                        "<td>" + meeting.name + "</td>" +
                        "<td>" + Room.name + "</td>" +
                        "<td>" + meeting.start_time + "</td>" +
                        "<td>" + meeting.end_time + "</td>" +
                        "<td>" + meeting.remark + "</td>" +
                        "<td>" + meeting.mper + "</td>" +
                        "</tr>";
                    $("#findRoom").append(trStr);
                    count_list++;
                }
            }
        }
    }
    if (meeting_name == "" && per_name == "" && room_get == null) {
        parent.swal("警告!", "请输入要搜索的会议室名称、预定者或者会议地点", "warning")
    }
    if (count_list == 0) {
        parent.swal("警告!", "未找到您要搜索的会议", "warning")
    }
}