var did  = getParam('did');
var deptJsonArray = window.parent.deptJsonArray;
$(function(){
    for(var i=0;i<deptJsonArray.length;i++){
        if(deptJsonArray[i].did==did){
            $("#dname").val(deptJsonArray[i].dname);
            $("#desc").val(deptJsonArray[i].desc);
            break;
        }
    }
});

function updateDept(){
    var dname = $("#dname").val();
    var desc = $("#desc").val();

    for(var i=0;i<deptJsonArray.length;i++){
        if(deptJsonArray[i].did==did){
            deptJsonArray[i].dname = dname;
            deptJsonArray[i].desc = desc;
            alert("修改成功");
            window.location.href="deptList.html";
            break;
        }
    }

}