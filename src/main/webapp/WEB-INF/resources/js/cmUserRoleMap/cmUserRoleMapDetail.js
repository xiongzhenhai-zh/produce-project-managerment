var $id = $("#id");
var $userId = $("#userId");
var $roleId = $("#roleId");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./cmUserRoleMap/queryById',{"id":$("#main-body").attr("cmUserRoleMapId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $userId.html(data.content.userId);
            $roleId.html(data.content.roleId);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./cmUserRoleMap/cmUserRoleMapList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})