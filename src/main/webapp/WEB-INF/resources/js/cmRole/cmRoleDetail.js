var $id = $("#id");
var $name = $("#name");
var $description = $("#description");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./cmRole/queryById',{"id":$("#main-body").attr("cmRoleId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $name.html(data.content.name);
            $description.html(data.content.description);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./cmRole/cmRoleList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})