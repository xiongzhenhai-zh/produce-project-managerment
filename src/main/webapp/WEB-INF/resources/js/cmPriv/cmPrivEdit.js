/**
 * 
 */
$(document).ready(function() {

    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("cmPrivId"));
    submitUrl('./cmPriv/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
                $("#id").val(data.content.id);
                $("#name").val(data.content.name);
                $("#pid").val(data.content.pid);
                $("#category").val(data.content.category);
                $("#menuIcon").val(data.content.menuIcon);
                $("#menuStat").val(data.content.menuStat);
                $("#menuSort").val(data.content.menuSort);
                $("#openUrl").val(data.content.openUrl);
                $("#identifyCode").val(data.content.identifyCode);
                $("#reamrk").val(data.content.reamrk);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });

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
		cmPrivInfo.name = $("#name").val();
		cmPrivInfo.pid = $("#pid").val();
		cmPrivInfo.category = $("#category").val();
		cmPrivInfo.menuIcon = $("#menuIcon").val();
		cmPrivInfo.menuStat = $("#menuStat").val();
		cmPrivInfo.menuSort = $("#menuSort").val();
		cmPrivInfo.openUrl = $("#openUrl").val();
		cmPrivInfo.identifyCode = $("#identifyCode").val();
		cmPrivInfo.reamrk = $("#reamrk").val();
        cmPrivInfo.id = reqObj.id;
		submitUrl('./cmPriv/update',cmPrivInfo ,function(data) {
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
        $("div[data-role='./cmPriv/cmPrivList.html']").click();
	});
});