var meetJsonArray=window.parent.meetingJsonArray;
var meetingRoomJsonArray=window.parent.meetingRoomJsonArray;
var nowTime=new Date().getTime()

$(function(){
    showSearchList();
});

function showSearchList(){
    $("#meetNotice").html("");
    $(".pagination-container").html("");
    var name=$("#name").val();
    for(var i=0;i<meetJsonArray.length;i++){
		var meet = meetJsonArray[i];
		if(meet!='undefined'){
		var inShow = false;
		
		if(name!=""){
			if(meet.name.indexOf(name)!=-1){
				inShow=true;
			}
		}else{
			inShow=true;
		}
		
		if(inShow){
            var strTime=new Date(Date.parse((meet.start_time).replace('/-/g','/'))).getTime();
			if(strTime>nowTime){
				for(var j=0;j<meetingRoomJsonArray.length;j++){
					var Room=meetingRoomJsonArray[j];
					if(Room.id==meet.rid&&Room.name!="undefined"){
						parent.swal("最新通知", "您有一个新会议:"+meet.name+"\n会议地点:"+Room.name+"\n时间:"+meet.start_time, "info"); 
						var trStr = "<tr>"+
							"<td><input type='checkbox' name='id' value='"+meet.mid+"' /></td>"+
							"<td>"+meet.name+"</td>"+
							"<td>"+Room.name+"</td>"+
                			"<td>"+meet.start_time+"</td>"+
                			"<td>"+meet.end_time+"</td>"+
							"<td>"+meet.mtime+"</td>"+
                			"<td>"+meet.mper+"</td>"+
							"</tr>";
						$("#meetNotice").append(trStr);
					}		
				}
			}
        
		}
	}
}
	setPage();
}




//使用分页插件，进行分页展现
function setPage(){
	$('.table tbody').paginathing({
	  perPage: 3,//每页展现条数
	  insertAfter: '.table',
	  pageNumbers: true
	});
}



/*
var mid = getParam("mid");
var meetJsonArray=window.parent.meetingJsonArray;

$(function(){
    for(var i=0;i<meetJsonArray.length;i++){
    var meet=meetJsonArray[i];
    if(meet.mid==mid){
        $("#name").val(meet.name);
        $("#room").val(Room.name);
        $("#start").val(meet.start_time);
        $("#end").val(meet.end_time);
        $("#time").val(meet.mtime);
        $("#per").val(meet.mper);

        break;
        }
    }
});*/
/*
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
            meet.name=mname;
            Room.name=mroom;
            meet.start_time=mstart;
            meet.end_time=mend;
            meet.mtime=mtime;
            meet.mper=mper;
            alert("修改成功");
            window.location.href="meetRer.html";
        }
    }
}
*/
function checkAll(){
	var isChecked = $("#checkAll").prop("checked");
	var checkboxes = $("input[name='id']");
	for(var i=0;i<checkboxes.length;i++){
		var idCheckbox = checkboxes[i];
		if($(idCheckbox).parent().parent("tr").css("display")=="table-row"){
			$(idCheckbox).prop("checked",isChecked);
		}
	}
}

function delChecked(){
	var checkeds = $("input[name='id']:checked");
	// if(checkeds.length>0){
	// 	var flag = window.confirm("确定要删除吗？");
		
	// 	if(flag){
	// 		//alert("用户点击了确定删除");
	// 		//选中的部门一个个删掉
	// 		for(var i=0; i<checkeds.length;i++){
	// 			var thisChecked=checkeds[i];
	// 			var delMid=$(thisChecked).val();
	// 			delJson(delMid);
	// 		}
	// 		showSearchList();
	// 	}
	// }else{
	// 	alert("请选择要删除的数据");
	// }
	if(checkeds.length>0){
        parent.swal({
            title: "你确定？",
            text: "您将无法恢复这个会议信息！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的，删除！",
            cancelButtonText: "不，取消",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function(isConfirm) {
            if (isConfirm) {
				for(var i=0; i<checkeds.length;i++){
					var thisChecked=checkeds[i];
					var delMid=$(thisChecked).val();
					delJson(delMid);
					parent.swal("删除!", "您选择的会议已被删除！", "success")  
				}
                showSearchList();                  
            } else{
                parent.swal("取消!", "您的数据保持原样！", "error")
            }
        })
	}else{
		parent.swal("删除！", "请选择要删除的数据", "error"); 
	}
	
}

//从Json数组中删除指定部门编号的部门信息
function delJson(delMid){
	for(var i=0;i<meetJsonArray.length;i++){
		var meet=meetJsonArray[i];
		if(meet!=undefined){
			if(meet.mid==delMid){
				meetJsonArray[i] = 'undefined';
				// delete meetJsonArray[i];
				return;
			}
		}
	}
}