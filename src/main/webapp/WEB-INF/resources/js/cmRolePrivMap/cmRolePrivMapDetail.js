var $id = $("#id");
var $roleId = $("#roleId");
var $privId = $("#privId");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./cmRolePrivMap/queryById',{"id":$("#main-body").attr("cmRolePrivMapId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $roleId.html(data.content.roleId);
            $privId.html(data.content.privId);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./cmRolePrivMap/cmRolePrivMapList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})