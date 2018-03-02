var $id = $("#id");
var $name = $("#name");
var $pid = $("#pid");
var $category = $("#category");
var $menuIcon = $("#menuIcon");
var $menuStat = $("#menuStat");
var $menuSort = $("#menuSort");
var $openUrl = $("#openUrl");
var $identifyCode = $("#identifyCode");
var $reamrk = $("#reamrk");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./cmPriv/queryById',{"id":$("#main-body").attr("cmPrivId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $name.html(data.content.name);
            $pid.html(data.content.pid);
            $category.html(data.content.category);
            $menuIcon.html(data.content.menuIcon);
            $menuStat.html(data.content.menuStat);
            $menuSort.html(data.content.menuSort);
            $openUrl.html(data.content.openUrl);
            $identifyCode.html(data.content.identifyCode);
            $reamrk.html(data.content.reamrk);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./cmPriv/cmPrivList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})