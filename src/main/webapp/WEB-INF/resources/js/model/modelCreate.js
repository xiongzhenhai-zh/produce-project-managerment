/**
 * 
 */
var attributeIds = new Array();
$(document).ready(function() {
    var ReturnValidatorObj = ValidateFormUtil.valid($("#createModel"));
    _initAttr();
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
    $("#idtype").val("");
    $("#createTime").val("");
}

function save() {
    //提交保存数据
    var modelInfo = {};
    modelInfo.id = $("#id").val();
    modelInfo.name = $("#name").val();
    modelInfo.dbname = $("#dbname").val().replace(/\s+/g,"")==""?$("#name").val():$("#dbname").val();
    modelInfo.alies = $("#alies").val();
    modelInfo.idtype = $("#idtype").val();
    modelInfo.attributeIds = attributeIds.join(",");

    submitUrl('./model/add',modelInfo ,function(data) {
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


/**
 * 更改选中数据
 * @param obj
 */
function ChangeAttributeId(obj) {
    var id = $(obj).val();
    if ($(obj).is(':checked')){
        attributeIds.push(id);
        return;
    }
    for (var i=0;i<attributeIds.length;i++){
        if (id == attributeIds[i]){
            attributeIds.splice(i,1);
        }
    }
}

function _initAttr() {
    var columns = [
        {"bSortable": false, "mData": null},// 主键
        {"bSortable": false, "mData": null},// 字段名
        {"bSortable": false, "mData": null},// 数据库字段名
        {"bSortable": false, "mData": null},// 备注
        {"bSortable": false, "mData": null},// 字段类型
        {"bSortable": false, "mData": null},// 创建时间
        { data: 'id', orderable: false, render: function (data, type, row, meta) {
            var html = '<input name="attributeIds" onclick="ChangeAttributeId(this)" type="checkbox" value="' + data + '" style="width: 50px;">';
            if ($.inArray(data.toString(), attributeIds)>-1){
                html = '<input name="attributeIds" onclick="ChangeAttributeId(this)" type="checkbox" value="' + data + '" style="width: 50px;" checked>';
            }
            return html;
            }
        } // 操作
    ];
    var reqObj={};
    datatable = initDataTableCommonUseUrl($("#modelattributeTable"), columns, "./modelattribute/selectPageList", reqObj, "content", function (nRow, aData, iDisplayIndex) {
        $("td:eq(0)", nRow).text(aData.id);
        $("td:eq(1)", nRow).text(aData.name);
        $("td:eq(2)", nRow).text(aData.dbname);
        $("td:eq(3)", nRow).text(aData.alies);
        $("td:eq(4)", nRow).text(aData.type);
        var createTime = new Date(aData.createTime).pattern("yyyy-MM-dd hh:mm:ss");
        $("td:eq(5)", nRow).text(createTime);
    });
}