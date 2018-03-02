var $id = $("#id");
var $name = $("#name");
var $dbname = $("#dbname");
var $alies = $("#alies");
var $type = $("#type");
var $createTime = $("#createTime");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./modelattribute/queryById',{"id":$("#main-body").attr("modelattributeId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $name.html(data.content.name);
            $dbname.html(data.content.dbname);
            $alies.html(data.content.alies);
            $type.html(data.content.type);
            $createTime.html((new Date(data.content.createTime)).pattern("yyyy-MM-dd hh:mm:ss"));
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./modelattribute/modelattributeList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
})