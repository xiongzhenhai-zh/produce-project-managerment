/**
 * 
 */
$(document).ready(function() {

    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("cmRoleId"));
    submitUrl('./cmRole/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
                $("#id").val(data.content.id);
                $("#name").val(data.content.name);
                $("#description").val(data.content.description);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });

	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#name").val("");
			$("#description").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var cmRoleInfo = {};
		cmRoleInfo.name = $("#name").val();
		cmRoleInfo.description = $("#description").val();
        cmRoleInfo.id = reqObj.id;
		submitUrl('./cmRole/update',cmRoleInfo ,function(data) {
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
        $("div[data-role='./cmRole/cmRoleList.html']").click();
	});
});