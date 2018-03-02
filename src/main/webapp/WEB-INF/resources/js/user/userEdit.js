/**
 * 
 */
$(document).ready(function() {

    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("userId"));
    submitUrl('./user/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
                $("#id").val(data.content.id);
                $("#userName").val(data.content.userName);
                $("#password").val(data.content.password);
                $("#createTime").val(data.content.createTime);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });

	$("#cancelBtn").click(function() {
			$("#id").val("");
			$("#userName").val("");
			$("#password").val("");
			$("#createTime").val("");
	
	});
	$("#saveBtn").click(function() {
		//提交保存数据
		var userInfo = {};
		userInfo.id = $("#id").val();
		userInfo.userName = $("#userName").val();
		userInfo.password = $("#password").val();
		userInfo.createTime = $("#createTime").val();
        userInfo.id = reqObj.id;
		submitUrl('./user/update',userInfo ,function(data) {
            console.log(data);
            if (data.code == "000") {
                layer.closeAll('loading'); //关闭加载层
                layer.alert('保存成功！', {icon: 6 ,title: '提示', closeBtn: 0} , function(){
                    layer.closeAll();
                    $('div[data-role$="guideMerchandiseList.html"]').trigger("click");
                });
            } else {
                layer.alert('保存失败', {icon: 5,title: '告警'});
            }
            layer.closeAll('loading');
         });
	});

	$("#cancelBtn").click(function(){
        layer.closeAll('dialog');
        $("div[data-role='./user/userList.html']").click();
	});
});