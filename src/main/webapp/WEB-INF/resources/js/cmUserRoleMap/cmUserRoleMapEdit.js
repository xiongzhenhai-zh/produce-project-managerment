/**
 * 
 */
$(document).ready(function() {

    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("cmUserRoleMapId"));
    submitUrl('./cmUserRoleMap/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
                $("#id").val(data.content.id);
                $("#userId").val(data.content.userId);
                $("#roleId").val(data.content.roleId);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });

	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#userId").val("");
			$("#roleId").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var cmUserRoleMapInfo = {};
		cmUserRoleMapInfo.userId = $("#userId").val();
		cmUserRoleMapInfo.roleId = $("#roleId").val();
        cmUserRoleMapInfo.id = reqObj.id;
		submitUrl('./cmUserRoleMap/update',cmUserRoleMapInfo ,function(data) {
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
        $("div[data-role='./cmUserRoleMap/cmUserRoleMapList.html']").click();
	});
});