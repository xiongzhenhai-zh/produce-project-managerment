/**
 * 
 */
$(document).ready(function() {
	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#name").val("");
			$("#pid").val("");
			$("#category").val("");
			$("#menuIcon").val("");
			$("#menuStat").val("");
			$("#menuSort").val("");
			$("#openUrl").val("");
			$("#identifyCode").val("");
			$("#reamrk").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var cmPrivInfo = {};
		cmPrivInfo.id = $("#id").val();
		cmPrivInfo.name = $("#name").val();
		cmPrivInfo.pid = $("#pid").val();
		cmPrivInfo.category = $("#category").val();
		cmPrivInfo.menuIcon = $("#menuIcon").val();
		cmPrivInfo.menuStat = $("#menuStat").val();
		cmPrivInfo.menuSort = $("#menuSort").val();
		cmPrivInfo.openUrl = $("#openUrl").val();
		cmPrivInfo.identifyCode = $("#identifyCode").val();
		cmPrivInfo.reamrk = $("#reamrk").val();

		submitUrl('./cmPriv/add',cmPrivInfo ,function(data) {
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