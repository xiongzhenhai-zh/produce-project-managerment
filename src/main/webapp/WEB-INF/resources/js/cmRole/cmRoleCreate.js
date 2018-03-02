/**
 * 
 */
$(document).ready(function() {
	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#name").val("");
			$("#description").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var cmRoleInfo = {};
		cmRoleInfo.id = $("#id").val();
		cmRoleInfo.name = $("#name").val();
		cmRoleInfo.description = $("#description").val();

		submitUrl('./cmRole/add',cmRoleInfo ,function(data) {
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