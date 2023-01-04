var id =getParam("id");
var meetJsonArray = window.parent.meetingRoomJsonArray;

$(function ()
{
    for (var i = 0; i < meetJsonArray.length; i++) {
        var meet = meetJsonArray[i];
        if (meet.id == id) {

            $("#name").val(meet.name);
            $("#place").val(meet.place);
            $("#large").val(meet.large);;

            break;
        }
    }
});

function updateMeet(){
    var name =$("#name").val();
    var place = $("#place").val();
    var large=$("#large").val();

    if(name==""){
        parent.swal("会议室修改", "会议室名称不能为空", "error")
        // alert("会议名称不能为空");
        return;
    }

    if(place==""){
        parent.swal("会议室修改", "会议室地点不能为空", "error")
        // alert("会议地点不能为空");
        return;
    }

    if(large==""){
        parent.swal("会议室修改", "会议室大小不能为空", "error")
        // alert("会议大小不能为空");
        return;
    }


    for(var i=0;i<meetJsonArray.length;i++)
    {
        var meet = meetJsonArray[i];
        if(meet.id==id)
        {
            if(meet.name==name&&meet.place==place&&meet.large==large)
            {
                parent.swal("会议室修改", "您未作修改", "success")
                // alert("会议名称不能为空");
                window.location.href = "meeting.html"
                return;
            }

        }
        else
        {
            if (name == meet.name) {
                parent.swal("警告!", "会议室门牌号重复", "error")
                // alert("门牌号重复");
                return;
            }
            if (place == meet.place) {
                parent.swal("警告!", "会议室地点重复", "error")
                // alert("会议室地点重复");
                return;
            }
        }
    }
    for(var i=0;i<meetJsonArray.length;i++)
    {
        meet.name = name;
        meet.place = place;
        meet.large = large;

        parent.swal("会议室修改!", "修改成功", "success")
        window.location.href = "meeting.html"
        return;
    }

}