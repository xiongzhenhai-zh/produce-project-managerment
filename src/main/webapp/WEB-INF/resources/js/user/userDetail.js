var $id = $("#id");
var $userName = $("#userName");
var $password = $("#password");
var $createTime = $("#createTime");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./user/queryById',{"id":$("#main-body").attr("userId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $userName.html(data.content.userName);
            $password.html(data.content.password);
            $createTime.html(data.content.createTime);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./user/userList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})