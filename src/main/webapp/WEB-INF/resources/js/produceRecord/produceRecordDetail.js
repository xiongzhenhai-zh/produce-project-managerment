var $id = $("#id");
var $userId = $("#userId");
var $filePath = $("#filePath");
var $sysType = $("#sysType");
var $createTime = $("#createTime");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./produceRecord/queryById',{"id":$("#main-body").attr("produceRecordId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $userId.html(data.content.userId);
            $filePath.html(data.content.filePath);
            $sysType.html(data.content.sysType);
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
		$("div[data-role='./produceRecord/produceRecordList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})