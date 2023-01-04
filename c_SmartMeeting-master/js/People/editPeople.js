var id  = getParam("id");
var employeeJsonArray = window.parent.employeeJsonArray;
var deptJsonArray = window.parent.deptJsonArray;
$(function(){

    // $("#dep").html("");
    // onClickSelector();
    $("#dep").html("");
    var optionStr = "<option value=''>请选择</option>";
    $("#dep").append(optionStr)
    for (var i=0; i<deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if(dept != undefined && dept.dname != "undefined"){
            $("#dep").append("<option value='"+ dept.did + "'>"+dept.dname + "</option>");
        }
        
    }
    // onClickShowSelector();
    
    for(var i=0;i<employeeJsonArray.length;i++){
        if(employeeJsonArray[i].id==id){
            $("#name").val(employeeJsonArray[i].name);
            $("#cardid").val(employeeJsonArray[i].cardid);
            $("#sex").val(employeeJsonArray[i].sex);
            $("#email").val(employeeJsonArray[i].email);
            $("#education").val(employeeJsonArray[i].education);
            $("#speciality").val(employeeJsonArray[i].speciality);
            $("#party").val(employeeJsonArray[i].party);
            $("#phone").val(employeeJsonArray[i].phone);
            $("#wechat").val(employeeJsonArray[i].wechat);
            $("#address").val(employeeJsonArray[i].address);
            $("#postcode").val(employeeJsonArray[i].postcode);
            $("#birthday").val(employeeJsonArray[i].birthday);
            $("#race").val(employeeJsonArray[i].race);
            $("#remark").val(employeeJsonArray[i].remark);
            $("#dep").val(employeeJsonArray[i].depid);
            $("#levelid").val(employeeJsonArray[i].level);
            $("#salary").val(employeeJsonArray[i].salary);
            
            break;
        }
    }

    
});

function updatePeople(){
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
    var cdep = $("#dep option:selected").text();
    var cdepid = $("#dep").val();
    var clevel = $("#levelid").val();
    var clevelid = $("#levelid option:selected").text();
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
            employeeJsonArray[i].level = clevel;
            employeeJsonArray[i].levelid = clevelid;
            employeeJsonArray[i].salary = csalary;
            employeeJsonArray[i].status = "1"

            parent.swal("提示", "员工："+employeeJsonArray[i].name+" 信息已修改成功！", "success")
            // alert("修改成功");
            window.location.href="peopleList.html";
            break;
        }
    }

}

function onClickSelector(){
    $("#dep").html("");
    var optionStr = "<option value=''>请选择</option>";
    $("#dep").append(optionStr)
    for (var i=0; i<deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if(dept != undefined && dept.dname != "undefined"){
            $("#dep").append("<option value='"+ dept.did + "'>"+dept.dname + "</option>");
        }
        
    }
    
}

function onClickShowSelector(){
    $("#dep").html("");
    var optionStr = "<option value=''>请选择</option>";
    $("#dep").append(optionStr)
    for (var i=0; i<deptJsonArray.length; i++) {
        var dept = deptJsonArray[i];
        if(dept != undefined && dept.dname != 'undefined'){
            var optionStr = "<option>"+dept.dname + "</option>";
            $("#dep").append(optionStr)
        }
        
    }
    
}