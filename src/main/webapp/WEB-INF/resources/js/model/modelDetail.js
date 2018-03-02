var $id = $("#id");
var $name = $("#name");
var $dbname = $("#dbname");
var $alies = $("#alies");
var $idtype = $("#idtype");
var $createTime = $("#createTime");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./model/queryById',{"id":$("#main-body").attr("modelId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $name.html(data.content.name);
            $dbname.html(data.content.dbname);
            $alies.html(data.content.alies);
            $createTime.html((new Date(data.content.createTime)).pattern("yyyy-MM-dd hh:mm:ss"));
            var ids = [];
            if (data.content.attributeIds != null){
                ids = data.content.attributeIds.split(",");
            }
            _initAttr(ids);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./model/modelList.html']").click();
	})
}

$(document).ready(function() {
	_initElemts();
	_initEvent();
});

function _initAttr(attributeIds) {
    var columns = [
        {"bSortable": false, "mData": null},// 主键
        {"bSortable": false, "mData": null},// 字段名
        {"bSortable": false, "mData": null},// 数据库字段名
        {"bSortable": false, "mData": null},// 备注
        {"bSortable": false, "mData": null}// 字段类型
    ];
    var reqObj={};
    reqObj.ids =attributeIds;
    datatable = initDataTableCommonUseUrl($("#modelattributeTable"), columns, "./modelattribute/queryByIds", reqObj, "content", function (nRow, aData, iDisplayIndex) {
        $("td:eq(0)", nRow).text(aData.id);
        $("td:eq(1)", nRow).text(aData.name);
        $("td:eq(2)", nRow).text(aData.dbname);
        $("td:eq(3)", nRow).text(aData.alies);
        $("td:eq(4)", nRow).text(aData.type);
    });
}