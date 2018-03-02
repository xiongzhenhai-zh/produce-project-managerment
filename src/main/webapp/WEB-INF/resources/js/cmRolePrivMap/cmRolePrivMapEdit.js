/**
 * 
 */
$(document).ready(function() {

    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("cmRolePrivMapId"));
    submitUrl('./cmRolePrivMap/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
                $("#id").val(data.content.id);
                $("#roleId").val(data.content.roleId);
                $("#privId").val(data.content.privId);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });

	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#roleId").val("");
			$("#privId").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var cmRolePrivMapInfo = {};
		cmRolePrivMapInfo.roleId = $("#roleId").val();
		cmRolePrivMapInfo.privId = $("#privId").val();
        cmRolePrivMapInfo.id = reqObj.id;
		submitUrl('./cmRolePrivMap/update',cmRolePrivMapInfo ,function(data) {
            console.log(data);
            if (data.code == "000") {
                layer.alert('保存成功！', {icon: 6 ,title: '提示', closeBtn: 0} , function(){
                    layer.closeAll();
                });
            } else {
                layer.alert('保存失败', {icon: 5,title: '告警'});
            }
            layer.closeAll('loading');
         });
	});

	$("#cancelBtn").click(function(){
        layer.closeAll('dialog');
        $("div[data-role='./cmRolePrivMap/cmRolePrivMapList.html']").click();
	});
});