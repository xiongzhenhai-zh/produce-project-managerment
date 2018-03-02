var datatable = null;
var $produceRecordTable;
$(document).ready(function () {
    //查询按钮权限
    $produceRecordTable = $("#produceRecordTable");
    var reqObj = getJsonParam() || {
            pageLength: 10,
            currentPageNo: 1
        };
    initDataTable1();
    var $form = $("#form");
    var $validate = ValidateFormUtil.valid($form);


    $("#searchBtn").click(function () {
        $isValid = true;
        $.each($("#stockRange :input"), function (i, n) {
            if (!$validate.element($(n))) {
                $isValid = false;
            }
        });

        if ($isValid) {
            reqObj.id = $("#id").val() == "" ? null : $("#id").val();
            reqObj.userId = $("#userId").val() == "" ? null : $("#userId").val();
            reqObj.filePath = $("#filePath").val() == "" ? null : $("#filePath").val();
            reqObj.sysType = $("#sysType").val() == "" ? null : $("#sysType").val();
            reqObj.createTime = $("#createTime").val() == "" ? null : $("#createTime").val();
            datatable.fnDraw();
        }
    });

    $("#resetBtn").click(function () {
        $("#id").val("");
        $("#userId").val("");
        $("#filePath").val("");
        $("#sysType").val("");
        $("#createTime").val("");

    });

    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            jQuery("#searchBtn").click();
        }
    });

    // 初始化表格
    function initDataTable1() {
        var columns = [
            {"bSortable": false, "mData": null},// 主键
            {"bSortable": false, "mData": null},// 文件路径
            {"bSortable": false, "mData": null},// 文件路径
            {"bSortable": false, "mData": null} // 创建时间
        ];
        datatable = initDataTableCommonUseUrl($produceRecordTable, columns, "./produceRecord/selectPageList", reqObj, "content.list", function (nRow, aData, iDisplayIndex) {
            $("td:eq(0)", nRow).text(aData.id);
            $("td:eq(1)", nRow).text(aData.filePath);
            var createTime = new Date(aData.createTime).pattern("yyyy-MM-dd hh:mm:ss");
            $("td:eq(2)", nRow).text(createTime);
            var $operations_download = $("<span><a style='cursor: pointer;' href='./project/download?filePath=" + aData.id + "' class='deleteRowCls'>下载</a></span>");
            var $operations_del = $("<span><a style='cursor: pointer;' onclick=confirmDel('" + aData.id + "') class='deleteRowCls'>删除</a></span>");
            $("td:eq(3)", nRow).empty();
            $("td:eq(3)", nRow).append($operations_download).append(" ").append($operations_del);
        });
    }

});


function confirmDel(id) {
    //询问框
    var confirmInfo = "<div style='text-align: center;'>记录删除之后不可恢复</div>";

    layer.confirm(confirmInfo, {
        title: '提示',
        btn: ['确定', '取消'] //按钮
    }, function () {
        produceRecordDelete(id);
        layer.msg('操作成功', {
            time: 1000 //1s后自动关闭
        });
    }, function () {

    });
}

function produceRecordDelete(id) {
    var aData = {};
    aData.id = id;
    submitUrl('./produceRecord/delete', aData, function (data) {
        console.log(data);
        if (data.code == "000") {
            layer.alert('删除成功！', {icon: 6, title: '提示', closeBtn: 0}, function () {
                layer.closeAll();
                datatable.fnDraw();
            });
        } else {
            layer.alert('删除失败', {icon: 5, title: '告警'});
        }
        layer.closeAll('loading');
    });
}