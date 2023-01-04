// var deptJsonArray = window.parent.deptJsonArray;
// var meetingRoomJsonArray = window.parent.meetingRoomJsonArray;
// var employeeJsonArray = window.parent.employeeJsonArray;
// var meetingJsonArray = window.parent.meetingJsonArray

// function body_load(){//当页面加载时触发执行
//     //首先生成部门下拉选项
//     for(var i=0;i<deptJsonArray.length;i++){
//         var dept = deptJsonArray[i];
//         if(dept!=undefined){
//             $("#selDepartments").append("<option value='"+dept.did+"'>"+dept.dname+"</option>");
//         }
//     }
//     fillEmployees();//根据选中的部门生成员工列表下拉框
// }

// function getParam(name) {
//     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
//     var r = window.location.search.substr(1).match(reg);
//     if(r != null) return decodeURIComponent(r[2]);
//     return null;
// }

// //根据选中的部门生成员工列表下拉框
// function fillEmployees(){
//     $("#selEmployees").html("");//先清空该下拉框
//     //获取选中的部门下拉框
//     var deptid = $("#selDepartments").val();
//     for(i=0;i<employeeJsonArray.length;i++){
//         var employee = employeeJsonArray[i];
//         if(employee!=undefined && employee.depid==deptid){
//             $("#selEmployees").append("<option value='"+employee.id+"'>"+employee.name+"</option>");
//         }
//     }
// }

// //点击选择该员工进行参会的按钮触发的事件
// function selectEmployees(){
//     //获取得到所有的选中的员工列表
//     var selectedEmps = $("#selEmployees>option:selected");
//     for(var i=0;i<selectedEmps.length;i++) {//将选中的员工追加到右侧的员工列表区域
//         var selectedEmp = selectedEmps[i];
//         //首先判断该员工原来是否有出现在右侧区域中
//         //初始化定义并未出现过
//         var flag = true;
//         var selSelectedEmployees = $("#selSelectedEmployees option");
//         for (var j = 0; j < selSelectedEmployees.length; j++) {
//             var selSelectedEmployee = selSelectedEmployees[j];
//             if ($(selSelectedEmployee).val() == $(selectedEmp).val()) {
//                 flag = false;
//                 break;
//             }
//         }
//         if (flag) {
//             $("#selSelectedEmployees").append("<option value='" + $(selectedEmp).val() + "'>" + $(selectedEmp).text() + "</option>");
//         }
//     }
// }

// //点击取消选择该员工进行参会的按钮触发的事件
// function deSelectEmployees(){
//     //获取得到所有的选中的想要取消参加会议的员工列表
//     var selSelectedEmployees = $("#selSelectedEmployees>option:selected");
//     for(var i=0;i<selSelectedEmployees.length;i++){
//         $(selSelectedEmployees[i]).remove();
//     }
// }
// function checkIsUsed(mid,start_time,end_time){
//     for(var i=0;i<meetingJsonArray.length; i++){
//         var meeting = meetingJsonArray[i];
//         if(meeting != undefined && meeting.mid == mid && meeting.status == "1"){
//             var pre_starttime = new Date(start_time.replaceAll("-","/"));
//             var pre_endtime = new Date(end_time.replaceAll("-","/"));
//             var meeting_starttime = new Date(meeting.start_time.replaceAll("-","/"));
//             var meeting_endtime = new Date(meeting.end_time.replaceAll("-","/"));
//             if(pre_starttime>=meeting_starttime && pre_starttime<meeting_endtime){
//                 return false
//             }
//             if(pre_endtime>meeting_starttime && pre_endtime<=meeting_endtime){
//                 return false
//             }
//             if(meeting_endtime>pre_starttime && meeting_endtime<=pre_endtime){
//                 return false
//             }
//             if(meeting_starttime>=pre_starttime && meeting_starttime<pre_endtime){
//                 return false
//             }
//             return true
//         }
//     }
// }
// //格式化时间
// Date.prototype.Format = function (fmt) {
//     var o = {
//         "M+": this.getMonth() + 1, //月份
//         "d+": this.getDate(), //日
//         "H+": this.getHours(), //小时
//         "m+": this.getMinutes(), //分
//         "s+": this.getSeconds(), //秒
//         "q+": Math.floor((this.getMonth() + 3) / 3), //季度
//         "S": this.getMilliseconds() //毫秒
//     };
//     if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//     for (var k in o)
//         if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//     return fmt;
// }

// function reserveMeeting(){
//     var mname = $("#title").val();
//     var start_time = $("#starttime").val();
//     var end_time = $("#endtime").val();
//     var num = $("#num").val();
//     var mid = $("#mid").val();
//     var participants = getParticipants();
//     var remark = $("#remark").val();
//     var now_time = new Date().Format("yyyy-MM-dd HH:mm");
//     var loginUserId = getParam("loginUserId");
//     for (var i=0; i<employeeJsonArray.length; i++) {
//         if (employeeJsonArray[i].id == loginUserId && employeeJsonArray[i]!=undefined) {
//             loginUser = employeeJsonArray[i];
//             break;
//         }
//     }
//     var per_name = loginUser.name
//     if(checkIsUsed(mid,start_time,end_time)){
//         var meeting = {
//             "mid": meetingJsonArray.length+1,
//             "name":mname,
//             "num":num,
//             "start_time": start_time,
//             "end_time": end_time,
//             "eid":"1",
//             "rid":rid,
//             "participants":participants,
//             "remark": remark,
//             "status":1,
//             "mtime":now_time,
//             "mper":per_name
//         }
//         meetingJsonArray[meetingJsonArray.length] = meeting;
//         alert("预定成功");
//     }else{
//         alert("预定失败")
//     }
// }

// function chooseRoom(){
//     $("#mid").html("");
//     for (var i=0; i<meetingRoomJsonArray.length; i++) {
//         var meetingroom = meetingRoomJsonArray[i];
//         var optionStr = "<option value='"+ meetingroom.id + "'>"+meetingroom.name + "</option>";
//         $("#mid").append(optionStr)
//     }
// }

// function getParticipants(){
//     var participants = "";
//     //获取已经选择的员工
//     var selSelectedEmployees = $("#selSelectedEmployees option");
//     for(var i=0;i<selSelectedEmployees.length; i++){
//         var selSelectedEmp = selSelectedEmployees[i];
//         participants += $(selSelectedEmp).val()+",";
//     }
//     return participants;
// }

//从下面开始整新的试试