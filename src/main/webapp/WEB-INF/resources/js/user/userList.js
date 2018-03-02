var datatable = null;
var btnAuths; //本页面拥有的按钮权限
var $userTable;
$(document).ready(function () {
    //查询按钮权限
    $userTable = $("#userTable");
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
        })

        if ($isValid) {
            reqObj.id = $("#id").val() == "" ? null : $("#id").val();
            reqObj.userName = $("#userName").val() == "" ? null : $("#userName").val();
            reqObj.password = $("#password").val() == "" ? null : $("#password").val();
            reqObj.createTime = $("#createTime").val() == "" ? null : $("#createTime").val();
            datatable.fnDraw();
        }
    });

    $("#resetBtn").click(function () {
        $("#id").val("");
        $("#userName").val("");
        $("#password").val("");
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
//	datatable.css("margin","0 5px");
        var columns = [
            {"bSortable": false, "mData": null},// 主键
            {"bSortable": false, "mData": null},// 用户名
            {"bSortable": false, "mData": null},// 密码
            {"bSortable": false, "mData": null},// 创建时间
            {"bSortable": false, "mData": null} // 操作
        ];
        datatable = initDataTableCommonUseUrl($userTable, columns, "./user/selectPageList", reqObj, "content", function (nRow, aData, iDisplayIndex) {
            $("td:eq(0)", nRow).text(aData.id);
            $("td:eq(1)", nRow).text(aData.userName);
            $("td:eq(2)", nRow).text(aData.password);
            var createTime = new Date(aData.createTime).pattern("yyyy-MM-dd hh:mm:ss");
            $("td:eq(3)", nRow).text(createTime);

            var $operations_edit = $("<span><a style='cursor: pointer;' onclick=userEdit('" + aData.id + "') class='deleteRowCls'>编辑</a></span>");
            var $operations_detail = $("<span><a style='cursor: pointer;' onclick=detail('" + aData.id + "') class='deleteRowCls'>详情</a></span>");
            var $operations_del = $("<span><a style='cursor: pointer;' onclick=confirmDel('" + aData.id + "') class='deleteRowCls'>删除</a></span>");
            $("td:eq(4)", nRow).empty();
            $("td:eq(4)", nRow).append($operations_edit).append(" ").append($operations_detail).append(" ").append($operations_del);
        });
    }

});


function confirmDel(id) {
    //询问框
    var confirmInfo = "";
    confirmInfo = "<div style='text-align: center;'>确认要删除该用户？</div>";

    layer.confirm(confirmInfo, {
        title: '提示',
        btn: ['确定', '取消'] //按钮
    }, function () {
        userDell(id);
        layer.msg('操作成功', {
            time: 1000, //0.5s后自动关闭
        });
    }, function () {

    });
}

function userDell(id) {
    var aData = {};
    aData.id = id;
    submitUrl('./user/delete', aData, function (data) {
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

/**
 * 跳转编辑页面
 */
function userEdit(id) {
    $("#main-body").load("./user/userEdit.html?id=" + id).attr("userId", id);
}


/**
 * 跳转到详情页面
 * @param id
 */
function detail(id) {
    $("#main-body").load("./user/userDetail.html?id=" + id).attr("userId", id);
}