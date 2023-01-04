var mid = getParam("mid");
var meetJsonArray=window.parent.meetingJsonArray;
var meetingRoomJsonArray=window.parent.meetingRoomJsonArray;
$(function(){
    $("#room").html("");
    for (var i=0; i<meetingRoomJsonArray.length; i++) {
        var meetingroom = meetingRoomJsonArray[i];
        var optionStr = "<option value='"+ meetingroom.id + "'>"+meetingroom.name + "</option>";
        $("#room").append(optionStr)
    }
    
    for(var i=0;i<meetJsonArray.length;i++){
    var meet=meetJsonArray[i];
    if(meet.mid==mid){
        for(var j=0;j<meetingRoomJsonArray.length;j++){
            var Room=meetingRoomJsonArray[j];
            if(Room.id==meet.rid){
        $("#name").val(meet.name);
        $("#room").val(Room.id);
        $("#start").val(meet.start_time);
        $("#end").val(meet.end_time);
        $("#time").val(meet.mtime);
        $("#per").val(meet.mper);

        break;
        }}}
    }
});
function chooseRoom(){
    $("#room").html("");
    for (var i=0; i<meetingRoomJsonArray.length; i++) {
        var meetingroom = meetingRoomJsonArray[i];
        var optionStr = "<option value='"+ meetingroom.id + "'>"+meetingroom.name + "</option>";
        $("#room").append(optionStr)
    }
}

function updateDept(){
    var mname=$("#name").val();
    var mroom=$("#room").val();
    var mstart=$("#start").val();
    var mend=$("#end").val();
    var mtime=$("#time").val();
    var mper=$("#per").val();
    for (var i=0;i<meetJsonArray.length;i++){
        var meet=meetJsonArray[i];
        if(meet.mid==mid){
        for(var j=0;j<meetingRoomJsonArray.length;j++){
            var Room=meetingRoomJsonArray[j];
            if(Room.name==mroom){
                meet.rid=Room.id;
            }
        meet.name=mname;
        meet.start_time=mstart;
        meet.end_time=mend;
        meet.mtime=mtime;
        meet.mper=mper;
        parent.swal("成功!", "您选择的会议修改成功！", "success")  
            // alert("修改成功");
            window.location.href="meetRer.html";}
        }
    }
}
function cancelAdd(){
    alert("取消成功");
   // window.history.go(-1);
    window.location.href="adminIndex.html#";
    
}