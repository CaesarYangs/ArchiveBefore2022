var meetJsonArray = window.parent.meetingRoomJsonArray;

$(function ()
{
    for(var i=0;i<meetJsonArray.length;i++)
    {
        var meet = meetJsonArray[i];
        var trStr = "<tr>"+
            "<td><input type='checkbox' /></td>"+
            "<td>"+meet.name+"</td>"+
            "<td>"+meet.place+"</td>"+
            "<td>"+meet.large+"</td>"+
            "<td><img onclick='showEditMeet("+meet.id+")' src='../../img/update.gif' /></td>"+
            "</tr>";
        $("#meeting").append(trStr);
    }
    showSearchMeet();
});

function showSearchMeet(){
    $("#meeting").html("");

    var name = $("#name").val();

    if(name ==null){
        return
    }
    for(var i=0;i<meetJsonArray.length;i++){
        var meet = meetJsonArray[i];
       if(meet!=undefined&&meet.name!='undefined') {
           var inShow = false;
           if (name != "") {
               if (meet.name.indexOf(name) != -1) {
                   inShow = true;
               }
           } else {
               inShow = true;
           }

           if (inShow) {

               var Str = "<tr>" +
                   "<td><input type='checkbox' name='id' value='" +meet.id+ "' /></td>" +
                   "<td>" + meet.name + "</td>" +
                   "<td>" + meet.place + "</td>" +
                   "<td>" + meet.large + "</td>" +
                   "<td><img onclick='showEditMeet("+meet.id+")' src='../../img/update.gif' /></td>" +
                   "</tr>";
               $("#meeting").append(Str);
           }
       }
    }
    setPage();
}

//使用分页插件，进行分页展现
function setPage(){
    $('.pagination-container').html("")
    $('.table tbody').paginathing({
        perPage: 3,//每页展现条数
        insertAfter: '.table',
        pageNumbers: true
    });
}

function showAddMeet(){
    window.location.href="../../MeetingRoom/meet/addMeet.html";
}

function showEditMeet(mid){
    window.location.href="editRoom.html?id="+mid;
}

function checkAllmeet()
{
    var isCheck =$("#checkAllmeet").prop("checked");
    var checkbox =$("input[name='id']");
    for(var i=0;i<checkbox.length;i++)
    {
        var idCheck=checkbox[i];
        if($(idCheck).parent().parent("tr").css("display")=="table-row")
        {
            $(idCheck).prop("checked",isCheck);
        }
    }
}

function delJson(delmid)
{
    for(var i=0; i<meetJsonArray.length;i++)
    {
        var meet =meetJsonArray[i];
        if(meet.id==delmid)
        {
            // delete meetJsonArray[i];
            meetJsonArray[i].name="undefined";
            return;
        }
    }
}

function delChecked(){
    var checked=$("input[name='id']:checked")
    // if(checked.length>0)
    // {
    //     var flag =window.confirm("确定要删除么");
    //     if(flag)
    //     {
    //         for(var i=0;i<checked.length;i++)
    //         {
    //             var thisChecke =checked[i];
    //             var delmid =$(thisChecke).val();
    //             delJson(delmid);
    //         }
    //     }
    //     showSearchMeet(); }
    //     else
    //     {
    //         alert("请选择要删除的数据");
    //     }
    if(checked.length>0){
        parent.swal({
            title: "你确定？",
            text: "您将无法恢复这个会议室及会议信息！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#fff2ef",
            confirmButtonText: "是的，删除！",
            cancelButtonText: "不，取消",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function(isConfirm) {
            if (isConfirm) {
            for(var i=0;i<checked.length;i++){
                var thisChecke =checked[i];
                var delmid =$(thisChecke).val();
                delJson(delmid);
                parent.swal("删除!", "您选择的会议室已被删除！", "success")  
            }
            showSearchMeet();
        }else{
                parent.swal("取消!", "您的会议室将保持原样！", "error")
            }
        })
	}else{
		parent.swal("删除！", "请选择要删除的会议室", "error"); 
	}

}