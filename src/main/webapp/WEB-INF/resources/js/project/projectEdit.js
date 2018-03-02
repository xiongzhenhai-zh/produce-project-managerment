/**
 * 
 */
var modelIds = new Array();
$(document).ready(function() {
    var ReturnValidatorObj = ValidateFormUtil.valid($("#updateProject"));
    var reqObj = {};
    reqObj.id = parseInt($("#main-body").attr("projectId"));
    _init(reqObj);
    changeProduceType(1);
    $("#produceType").change(function () {
        changeProduceType($("#produceType option:selected").val());
    });
	$("#cancelBtn").click(function() {
        layer.confirm("确认取消", {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.closeAll();
            $('div[data-role$="projectList.html"]').trigger("click");
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


function changeProduceType(type) {
    if(type == 2){
        $('#entityTable').show();
    }else{
        $('#entityTable').hide();
    }
}


function _init(reqObj) {
    _initSelect();
    submitUrl('./project/queryById',reqObj ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $("#id").val(data.content.id);
            $("#groupid").val(data.content.groupid);
            $("#artifactid").val(data.content.artifactid);
            $("#name").val(data.content.name);
            $("#alies").val(data.content.alies);
            $("#databasename").val(data.content.databasename);
            $("#pkgname").val(data.content.pkgname);
            $("#autor").val(data.content.autor);
            $("#createTime").val(data.content.createTime);
            $("#exclusionsTable").val(data.content.exclusionsTable);
            $("#produceType").val(data.content.produceType);
            $("#serverFrameType").val(data.content.serverFrameType);
            $("#clientFrameType").val(data.content.clientFrameType);
            $("#dbUrl").val(data.content.dbUrl);
            $("#dbUsername").val(data.content.dbUsername);
            $("#dbPassword").val(data.content.dbPassword);
            initmodelDataTable(data.content);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
    });
}

function save(reqObj) {
    //提交保存数据
    var projectInfo = {};
    projectInfo.groupid = $("#groupid").val();
    projectInfo.artifactid = $("#artifactid").val();
    projectInfo.name = $("#name").val();
    projectInfo.alies = $("#alies").val();
    projectInfo.databasename = $("#databasename").val();
    projectInfo.pkgname = $("#pkgname").val();
    projectInfo.autor = $("#autor").val();
    projectInfo.createTime = $("#createTime").val();
    projectInfo.exclusionsTable = $("#exclusionsTable").val();
    projectInfo.produceType = $("#produceType option:selected").val();
    projectInfo.serverFrameType = $("#serverFrameType option:selected").val();
    projectInfo.clientFrameType = $("#clientFrameType option:selected").val();
    projectInfo.dbUrl = $("#dbUrl").val();
    projectInfo.dbUsername = $("#dbUsername").val();
    projectInfo.dbPassword = $("#dbPassword").val();
    projectInfo.modelIds = modelIds.join(",");
    projectInfo.id = reqObj.id;
    submitUrl('./project/update',projectInfo ,function(data) {
        console.log(data);
        if (data.code == "000") {
            layer.alert('保存成功！', {icon: 6 ,title: '提示', closeBtn: 0} , function(){
                layer.closeAll();
                $('div[data-role$="projectList.html"]').trigger("click");
            });
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
    });
}

function ChangeModelId(obj) {
    var id = $(obj).val();
    if ($(obj).is(':checked')){
        modelIds.push(id);
        return;
    }
    for (var i=0;i<modelIds.length;i++){
        if (id == modelIds[i]){
            modelIds.splice(i,1);
        }
    }
}


// 初始化表格
function initmodelDataTable(dataObj) {
    if (dataObj != null && dataObj.modelIds != null) {
        modelIds = dataObj.modelIds.split(",");
    }
    var columns = [
        {"bSortable": false, "mData": null},// 主键
        {"bSortable": false, "mData": null},// 实体名
        {"bSortable": false, "mData": null},// 对应数据表名
        {"bSortable": false, "mData": null},// 备注
        {"bSortable": false, "mData": null},// 创建时间
        {
            data: 'id', orderable: false, render: function (data, type, row, meta) {
            var html = '<input name="modelIds" type="checkbox" onclick="ChangeModelId(this)" value="' + data + '" style="width: 50px;">';
            if ($.inArray(data.toString(), modelIds) > -1) {
                html = '<input name="modelIds" type="checkbox" onclick="ChangeModelId(this)" value="' + data + '" style="width: 50px;" checked>';
            }
            return html;
        }
        } // 操作
    ];
    var modelReqObj = {};
    datatable = initDataTableCommonUseUrl($("#modelTable"), columns, "./model/selectPageList", modelReqObj, "content", function (nRow, aData, iDisplayIndex) {
        $("td:eq(0)", nRow).text(aData.id);
        $("td:eq(1)", nRow).text(aData.name);
        $("td:eq(2)", nRow).text(aData.dbname);
        $("td:eq(3)", nRow).text(aData.alies);
        var createTime = new Date(aData.createTime).pattern("yyyy-MM-dd hh:mm:ss");
        $("td:eq(4)", nRow).text(createTime);
    });
}

function _initSelect() {
    submitUrl("./project/queryOsType", {}, function (data) {
        if (data.code === "000") {
            var serviceHtml = '';
            for (var i = 0; i < data.content.service.length; i++) {
                serviceHtml += '<option value="' + data.content.service[i].name + '">' + data.content.service[i].alies + '</option>'
            }
            $("#serverFrameType").html(serviceHtml);

            var clientHtml = '';
            for (var i = 0; i < data.content.client.length; i++) {
                clientHtml += '<option value="' + data.content.client[i].name + '">' + data.content.client[i].alies + '</option>'
            }
            $("#clientFrameType").html(clientHtml);

            var mobileHtml = '';
            for (var i = 0; i < data.content.mobile.length; i++) {
                mobileHtml += '<option value="' + data.content.mobile[i].name + '">' + data.content.mobile[i].alies + '</option>'
            }
            $("#mobileFrameType").html(mobileHtml);
        } else {
            warningAlert('获取下拉列表失败');
        }
    });
}