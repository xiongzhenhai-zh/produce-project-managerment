/**
 * 
 */
$(document).ready(function() {
var ReturnValidatorObj =  ValidateFormUtil.valid($("#updateProduceRecord"));
    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("produceRecordId"));
    _init(reqObj);

	$("#cancelBtn").click(function() {
		layer.confirm("确认取消", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.closeAll();
            $('div[data-role$="ProduceRecordList.html"]').trigger("click");
        }, function () {

        });
	});
	$("#saveBtn").click(function() {
	    if (!ReturnValidatorObj.form()) {
            return;
        }
		layer.confirm("确认提交", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            _save(reqObj);
        }, function () {

        });
	});

});

/**
 * 初始化
 */
function _init(reqObj){
    submitUrl('./produceRecord/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
                $("#id").val(data.content.id);
                $("#userId").val(data.content.userId);
                $("#filePath").val(data.content.filePath);
                $("#sysType").val(data.content.sysType);
                $("#createTime").val(data.content.createTime);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

/**
 * 保存
 */
function _save(reqObj){
    //提交保存数据
    var produceRecordInfo = {};
    produceRecordInfo.id = $("#id").val();
    produceRecordInfo.userId = $("#userId").val();
    produceRecordInfo.filePath = $("#filePath").val();
    produceRecordInfo.sysType = $("#sysType").val();
    produceRecordInfo.createTime = $("#createTime").val();
    produceRecordInfo.id = reqObj.id;
    submitUrl('./produceRecord/update',produceRecordInfo ,function(data) {
        console.log(data);
        if (data.code == "000") {
            layer.alert('保存成功！', {icon: 6 ,title: '提示', closeBtn: 0} , function(){
                layer.closeAll();
                $('div[data-role$="ProduceRecordList.html"]').trigger("click");
            });
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
 });
}