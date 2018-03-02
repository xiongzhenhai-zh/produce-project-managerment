/**
 * 
 */
$(document).ready(function() {
    var ReturnValidatorObj = ValidateFormUtil.valid($("#updateModelattribute"));
    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("modelattributeId"));
    _init(reqObj);

	$("#cancelBtn").click(function() {
        layer.confirm("确认取消", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.closeAll();
            $('div[data-role$="modelattributeList.html"]').trigger("click");
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
            save(reqObj);
        }, function () {

        });

	});

});

function _init(reqObj) {
    submitUrl('./modelattribute/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $("#id").val(data.content.id);
            $("#name").val(data.content.name);
            $("#dbname").val(data.content.dbname);
            $("#alies").val(data.content.alies);
            $("#type").val(data.content.type);
            $("#createTime").val(data.content.createTime);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
    });
}

function save(reqObj) {
    //提交保存数据
    var modelattributeInfo = {};
    modelattributeInfo.name = $("#name").val();
    modelattributeInfo.dbname = $("#dbname").val();
    modelattributeInfo.alies = $("#alies").val();
    modelattributeInfo.type = $("#type option:selected").val();
    modelattributeInfo.id = reqObj.id;
    submitUrl('./modelattribute/update',modelattributeInfo ,function(data) {
        console.log(data);
        if (data.code == "000") {
            layer.alert('保存成功！', {icon: 6 ,title: '提示', closeBtn: 0} , function(){
                layer.closeAll();
                $('div[data-role$="modelattributeList.html"]').trigger("click");
            });
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
    });
}