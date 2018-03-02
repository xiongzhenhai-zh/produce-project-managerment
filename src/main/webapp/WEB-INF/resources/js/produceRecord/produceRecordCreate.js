/**
 * 
 */
$(document).ready(function() {
    var ReturnValidatorObj =  ValidateFormUtil.valid($("#createProduceRecord"));
	$("#cancelBtn").click(function() {
	    layer.confirm("确认取消", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            _cancel();
        }, function () {

        });
	});
	$("#saveBtn").click(function() {
	    // 验证表单
        if (!ReturnValidatorObj.form()) {
            return;
        }
		layer.confirm("确认提交", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            _save();
        }, function () {

        });
	});
});

/**
 * 取消
 */
function _cancel(){
        $("#id").val("");
        $("#userId").val("");
        $("#filePath").val("");
        $("#sysType").val("");
        $("#createTime").val("");
}

/**
 * 保存
 */
function _save(){
    //提交保存数据
    var produceRecordInfo = {};
    produceRecordInfo.id = $("#id").val();
    produceRecordInfo.userId = $("#userId").val();
    produceRecordInfo.filePath = $("#filePath").val();
    produceRecordInfo.sysType = $("#sysType").val();
    produceRecordInfo.createTime = $("#createTime").val();

    submitUrl('./produceRecord/add',produceRecordInfo ,function(data) {
        console.log(data);
        if (data.code == "000") {
            layer.alert('保存成功！', {icon: 6 ,title: '提示', closeBtn: 0} , function(){
                layer.closeAll();
            });
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}