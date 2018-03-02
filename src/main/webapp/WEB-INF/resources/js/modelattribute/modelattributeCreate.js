/**
 *
 */
$(document).ready(function() {
    var ReturnValidatorObj = ValidateFormUtil.valid($("#createModelattribute"));
	$("#cancelBtn").click(function() {

        layer.confirm("确认取消", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            cancel();
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
            save();

        }, function () {

        });

	});
});

function cancel() {
    $("#id").val("");
    $("#name").val("");
    $("#dbname").val("");
    $("#alies").val("");
    $("#type").val("");
    $("#createTime").val("");
}

function save() {
    //提交保存数据
    var modelattributeInfo = {};
    modelattributeInfo.id = $("#id").val();
    modelattributeInfo.name = $("#name").val();
    modelattributeInfo.dbname = $("#dbname").val();
    modelattributeInfo.alies = $("#alies").val();
    modelattributeInfo.type = $("#type option:selected").val();
    modelattributeInfo.createTime = $("#createTime").val();

    submitUrl('./modelattribute/add',modelattributeInfo ,function(data) {
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