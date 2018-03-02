/**
 * 
 */
$(document).ready(function() {
	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#roleId").val("");
			$("#privId").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var cmRolePrivMapInfo = {};
		cmRolePrivMapInfo.id = $("#id").val();
		cmRolePrivMapInfo.roleId = $("#roleId").val();
		cmRolePrivMapInfo.privId = $("#privId").val();

		submitUrl('./cmRolePrivMap/add',cmRolePrivMapInfo ,function(data) {
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
});