var employeeJsonArray = window.parent.employeeJsonArray;
var deptJsonArray = window.parent.deptJsonArray;
$(function(){
    //获取得到员工信息列表

    // c_onClickSelector();
    // c_onClickShowSelector();
    $("#dep").html("");
    for (var i=0; i<deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if(dept != undefined && dept.dname != "undefined"){
            var optionStr = "<option value='"+ dept.did + "'>"+dept.dname + "</option>";
        }
        $("#dep").append(optionStr)
    }

    var name = $("#name").val()
    if(name==null){
        return
    }

    for(var i=0;i<employeeJsonArray.length;i++){
        if(employeeJsonArray[i].status=="0"){
            if(employeeJsonArray[i]!=undefined && employeeJsonArray[i].name!='undefined'){
                var employee = employeeJsonArray[i];
                var trStr = "<tr>"+
                "<td><input type='checkbox' name='id' value='"+employee.id+"'/></td>"+
                "<td>"+employee.name+"</td>"+
                "<td>"+employee.sex+"</td>"+
                "<td>"+employee.email+"</td>"+
                "<td>"+employee.dep+"</td>"+
                "<td>"+employee.speciality+"</td>"+
                "<td>"+employee.education+"</td>"+
                "<td>"+employee.party+"</td>"+
                "<td>"+employee.wechat+"</td>"+
                "<td>"+employee.phone+"</td>"+
                "<td><a onclick='c_showEditEmployee("+employee.id+")'>[查看详情]</a><a onclick='approve("+employee.id+")'>[审批通过]</a><a onclick='deny("+employee.id+")'>[不通过]</a><a onclick='showEditEmployee("+employee.id+")'>[修改并通过]</a></td>"+
                "</tr>";
                $("#check_employeeList").append(trStr);
            }
        }
        
    }

    c_setPages()
    
})

function c_showEmployeeList(){

    $("#check_employeeList").html("")
    
    var name = $("#name").val()
    if(name==null){
        return
    }
     
    for(var i=0;i<employeeJsonArray.length;i++){
        var employee = employeeJsonArray[i];
        if(employeeJsonArray[i].status=="1"){
            continue
        }
        if(employee!=undefined && employee.name!='undefined'){
            if(employee.name.indexOf(name)!=-1){
                var trStr = "<tr>"+
                "<td><input type='checkbox' name='id' value='"+employee.id+"'/></td>"+
                "<td>"+employee.name+"</td>"+
                "<td>"+employee.sex+"</td>"+
                "<td>"+employee.email+"</td>"+
                "<td>"+employee.dep+"</td>"+
                "<td>"+employee.speciality+"</td>"+
                "<td>"+employee.education+"</td>"+
                "<td>"+employee.party+"</td>"+
                "<td>"+employee.wechat+"</td>"+
                "<td>"+employee.phone+"</td>"+
                "<td><a onclick='c_showEditEmployee("+employee.id+")'>[查看详情]</a><a onclick='approve("+employee.id+")'>[审批通过]</a><a onclick='deny("+employee.id+")'>[不通过]</a><a onclick='showEditEmployee("+employee.id+")'>[修改并通过]</a></td>"+
                "</tr>";
                $("#check_employeeList").append(trStr);
            }
        }  
    }   
    c_setPages()
}

//展现满足条件的员工信息列表数据
function c_showDeptEmployeeList(){

    $("#check_employeeList").html("")
    
    var dep = $("#dep").val()
    if(dep==null){
        return
    }
     
    for(var i=0;i<employeeJsonArray.length;i++){
        var employee = employeeJsonArray[i];
        if(employeeJsonArray[i].status=="1"){
            continue
        }
        if(employee!=undefined && employee.name!='undefined'){
            if(employee.dep.indexOf(dep)!=-1){
                var trStr = "<tr>"+
                "<td><input type='checkbox' name='id' value='"+employee.id+"'/></td>"+
                "<td>"+employee.name+"</td>"+
                "<td>"+employee.sex+"</td>"+
                "<td>"+employee.email+"</td>"+
                "<td>"+employee.dep+"</td>"+
                "<td>"+employee.speciality+"</td>"+
                "<td>"+employee.education+"</td>"+
                "<td>"+employee.party+"</td>"+
                "<td>"+employee.wechat+"</td>"+
                "<td>"+employee.phone+"</td>"+
                "<td><a onclick='c_showEditEmployee("+employee.id+")'>[查看详情]</a><a onclick='approve("+employee.id+")'>[审批通过]</a><a onclick='deny("+employee.id+")'>[不通过]</a><a onclick='showEditEmployee("+employee.id+")'>[修改并通过]</a></td>"+
                "</tr>";
                $("#check_employeeList").append(trStr);
            }
        }  
    }   
    c_setPages()
}
        
function c_setPages(){
    $('.pagination-container').html("")
    
    $('.table tbody').paginathing({
        perPage:6,
        insertAfter:'.table',
        pageNumbers:true
    })
}


//全选操作
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

function c_cancelSearch(){
    $("#name").val("")
    $("#dep").val("")
    c_showEmployeeList()
}

//指向至编辑并提交页面
function showEditEmployee(id){
    window.location.href = "editPeople.html?id="+id;
}

//指向仅编辑页面
function c_showEditEmployee(id){
    window.location.href = "showDetail.html?id="+id;
}

function approve(id){
    //alert("审批通过")
    parent.swal("审批!", "您选择的员工已审批通过！", "success") 
    for(var i=0;i<employeeJsonArray.length;i++){
        if(employeeJsonArray[i].id==id){
            employeeJsonArray[i].status="1"
        }
    }
    c_showEmployeeList();
}

function deny(){
    parent.swal("审批!", "已将您选择的员工审批不通过！", "error")
    //alert("不通过")
}

function c_updatePeople(){
    var cname = $("#name").val();
    var ccardid = $("#cardid").val();
    var csex = $("#sex").val();
    var cemail = $("#email").val();
    var ceducation = $("#education").val();
    var cspeciality = $("#speciality").val();
    var cparty = $("#party").val();
    var cphone = $("#phone").val();
    var cwechat = $("#wechat").val();
    var caddress = $("#address").val();
    var cpostcode = $("#postcode").val();
    var cbirthday = $("#birthday").val();
    var crace = $("#race").val();
    var cremark = $("#remark").val();
    // var cdep = $("#dep").val();
    var cdep = $("#dep option:selected").text();
    var cdepid = $("#dep").val();
    var clevel = $("#levelid option:selected").text();
    var clevelid = $("#levelid").val();
    var csalary = $("#salary").val();

    if(cname==null || cname==""){
        // alert("请输入姓名！")
        parent.swal("警告", "请输入姓名！", "warning")
        return
    }
    if(ccardid==null || ccardid==""){
        // alert("请输入身份证号！")
        parent.swal("警告", "请输入身份证号！", "warning")
        return
    }
    if(csex==null || csex==""){
        // alert("请输入性别！")
        parent.swal("警告", "请输入性别！", "warning")
        return
    }
    if(cemail==null || cemail==""){
        // alert("请输入邮箱！")
        parent.swal("警告", "请输入邮箱！", "warning")
        return
    }
    if(ceducation==null || ceducation==""){
        // alert("请输入学历！")
        parent.swal("警告", "请输入学历！", "warning")
        return
    }
    if(cspeciality==null || cspeciality==""){
        // alert("请输入专业！")
        parent.swal("警告", "请输入专业！", "warning")
        return
    }
    if(cparty==null || cparty==""){
        // alert("请输入政治面貌！")
        parent.swal("警告", "请输入政治面貌！", "warning")
        return 
    }
    if(cphone==null || cphone==""){
        // alert("请输入电话！")
        parent.swal("警告", "请输入电话！", "warning")
        return
    }
    if(cwechat==null || cwechat==""){
        // alert("请输入微信！")
        parent.swal("警告", "请输入微信！", "warning")
        return
    }
    if(caddress==null || caddress==""){
        // alert("请输入地址！")
        parent.swal("警告", "请输入地址！", "warning")
        return
    }
    if(cpostcode==null || cpostcode==""){
        // alert("请输入邮编！")
        parent.swal("警告", "请输入邮编！", "warning")
        return
    }
    if(cbirthday==null || cbirthday==""){
        // alert("请输入出生日期！")
        parent.swal("警告", "请输入出生日期！", "warning")
        return
    }
    if(crace==null || crace==""){
        // alert("请输入民族！")
        parent.swal("警告", "请输入民族！", "warning")
        return
    }
    if(cremark==null || cremark==""){
        // alert("请输入备注！")
        parent.swal("警告", "请输入备注！", "warning")
        return
    }
    if(cdep==null || cdep==""){
        // alert("请选择部门！")
        parent.swal("警告", "请选择部门！", "warning")
        return
    }
    if(clevel==null || clevel==""){
        // alert("请选择职级！")
        parent.swal("警告", "请选择职级！", "warning")
        return
    }
    if(csalary==null || csalary==""){
        // alert("请输入薪资！")
        parent.swal("警告", "请输入薪资！", "warning")
        return
    }

    for(var i=0;i<employeeJsonArray.length;i++){
        if(employeeJsonArray[i].id==id){
            employeeJsonArray[i].name = cname;
            employeeJsonArray[i].cardid = ccardid;
            employeeJsonArray[i].sex = csex;
            employeeJsonArray[i].email = cemail;
            employeeJsonArray[i].education = ceducation;
            employeeJsonArray[i].speciality = cspeciality;
            employeeJsonArray[i].party = cparty;
            employeeJsonArray[i].phone = cphone;
            employeeJsonArray[i].wechat = cwechat;
            employeeJsonArray[i].address = caddress;
            employeeJsonArray[i].postcode = cpostcode;
            employeeJsonArray[i].birthday = cbirthday;
            employeeJsonArray[i].race = crace;
            employeeJsonArray[i].remark = cremark;
            employeeJsonArray[i].dep = cdep;
            employeeJsonArray[i].depid = cdepid;

            employeeJsonArray[i].levelid = clevelid;
            employeeJsonArray[i].salary = csalary;
            employeeJsonArray[i].status = "0"

            parent.swal("修改", "修改成功! 请注意，没有将该员工通过审批", "success")
            //alert("修改成功");
            window.location.href="regCheck.html";
            break;
        }
    }
    // c_showEmployeeList();
}

function c_delChecked(){
    var checkeds = $("input[name='id']:checked");
	// if(checkeds.length>0){
	// 	var flag = window.confirm("确定要删除吗？");
	// 	if(flag){
	// 		alert("用户点击了确定删除");
	// 		for(var i=0;i<checkeds.length;i++){
	// 			var thisChecked = checkeds[i];
	// 			var delId = $(thisChecked).val();
	// 			delPJson(delId);
	// 		}
			
	// 	}
	// 	c_showEmployeeList();
	// }else{
	// 	alert("请选择要删除的数据");
	// }
    if(checkeds.length>0){
        parent.swal({
            title: "你确定？",
            text: "您将无法恢复这个员工的信息！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的，删除！",
            cancelButtonText: "不，取消",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function(isConfirm) {
            if (isConfirm) {
                for(var i=0;i<checkeds.length;i++){
                    var thisChecked = checkeds[i];
                    var delId = $(thisChecked).val();
                    delPJson(delId);
                    parent.swal("删除!", "您选择的员工已被删除！", "success");
                   
                }
                c_showEmployeeList();
                                
            } else{
                parent.swal("取消!", "取消删除员工！", "error")
            }
        })
	}else{
		parent.swal("删除！", "请选择要删除的数据", "error"); 
	}
}

//全选操作
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

function c_onClickSelector(){
    $("#dep").html("");
    for (var i=0; i<deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if(dept != undefined && dept.dname != "undefined"){
            var optionStr = "<option value='"+ dept.did + "'>"+dept.dname + "</option>";
        }
        $("#dep").append(optionStr)
    }
    
}

function c_onClickShowSelector(){
    $("#dep").html("");
    var optionStr = "<option value=''>请选择</option>";
    $("#dep").append(optionStr)
    for (var i=0; i<deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if(dept != undefined){
            var optionStr = "<option>"+dept.dname + "</option>";
        }
        $("#dep").append(optionStr)
    }
}