var datatable = null;
var btnAuths; //本页面拥有的按钮权限
var $projectTable;
$(document).ready(function () {
    //查询按钮权限
    $projectTable = $("#projectTable");
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
            reqObj.groupid = $("#groupid").val() == "" ? null : $("#groupid").val();
            reqObj.artifactid = $("#artifactid").val() == "" ? null : $("#artifactid").val();
            reqObj.name = $("#name").val() == "" ? null : $("#name").val();
            reqObj.alies = $("#alies").val() == "" ? null : $("#alies").val();
            reqObj.databasename = $("#databasename").val() == "" ? null : $("#databasename").val();
            reqObj.pkgname = $("#pkgname").val() == "" ? null : $("#pkgname").val();
            reqObj.autor = $("#autor").val() == "" ? null : $("#autor").val();
            reqObj.createTime = $("#createTime").val() == "" ? null : $("#createTime").val();
            reqObj.exclusionsTable = $("#exclusionsTable").val() == "" ? null : $("#exclusionsTable").val();
            reqObj.produceType = $("#produceType option:selected").val() == "" ? null : $("#produceType option:selected").val();
            reqObj.dbUrl = $("#dbUrl").val() == "" ? null : $("#dbUrl").val();
            reqObj.dbUsername = $("#dbUsername").val() == "" ? null : $("#dbUsername").val();
            reqObj.dbPassword = $("#dbPassword").val() == "" ? null : $("#dbPassword").val();
            datatable.fnDraw();
        }
    });

    $("#resetBtn").click(function () {
        $("#id").val("");
        $("#groupid").val("");
        $("#artifactid").val("");
        $("#name").val("");
        $("#alies").val("");
        $("#databasename").val("");
        $("#pkgname").val("");
        $("#autor").val("");
        $("#createTime").val("");
        $("#exclusionsTable").val("");
        $("#produceType").val("");
        $("#dbUrl").val("");
        $("#dbUsername").val("");
        $("#dbPassword").val("");

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
            {"bSortable": false, "mData": null},// maven坐标
            {"bSortable": false, "mData": null},// Maven项目名
            {"bSortable": false, "mData": null},// 项目名称
            {"bSortable": false, "mData": null},// 项目中文名
            {"bSortable": false, "mData": null},// 数据库
            {"bSortable": false, "mData": null},// 作者
            {"bSortable": false, "mData": null},// 生成方式
            {"bSortable": false, "mData": null},// 创建时间
            {"bSortable": false, "mData": null} // 操作
        ];
        datatable = initDataTableCommonUseUrl($projectTable, columns, "./project/selectPageList", reqObj, "content", function (nRow, aData, iDisplayIndex) {
            $("td:eq(0)", nRow).text(aData.id);
            $("td:eq(1)", nRow).text(aData.groupid);
            $("td:eq(2)", nRow).text(aData.artifactid);
            $("td:eq(3)", nRow).text(aData.name);
            $("td:eq(4)", nRow).text(aData.alies);
            $("td:eq(5)", nRow).text(aData.databasename);
            $("td:eq(6)", nRow).text(aData.autor);
            var text = "";
            if(aData.produceType === 1){
                text = '数据库反向生成';
            }else if(aData.produceType === 2){
                text = '根据模型生成';
            }
            $("td:eq(7)", nRow).text(text);
            var createTime = new Date(aData.createTime).pattern("yyyy-MM-dd hh:mm:ss");
            $("td:eq(8)", nRow).text(createTime);

            var $operations_produce = $("<span><a style='cursor: pointer;' onclick=projectProduce('" + aData.id + "',"+aData.produceType+") class='deleteRowCls'>生成</a></span>");
            var $operations_edit = $("<span><a style='cursor: pointer;' onclick=projectEdit('" + aData.id + "','"+aData.produceType+"') class='deleteRowCls'>编辑</a></span>");
            var $operations_detail = $("<span><a style='cursor: pointer;' onclick=detail('" + aData.id + "') class='deleteRowCls'>详情</a></span>");
            var $operations_del = $("<span><a style='cursor: pointer;' onclick=confirmDel('" + aData.id + "') class='deleteRowCls'>删除</a></span>");
            $("td:eq(9)", nRow).empty();
            $("td:eq(9)", nRow).append($operations_produce).append(" ").append($operations_edit).append(" ").append($operations_detail).append(" ").append($operations_del);
        });
    }

});


function confirmDel(id) {
    //询问框
    var confirmInfo = "<div style='text-align: center;'>确认要删除该项目实体？</div>";
    layer.confirm(confirmInfo, {
        title: '提示',
        btn: ['确定', '取消'] //按钮
    }, function () {
        projectDell(id);
        layer.msg('操作成功', {
            time: 1000, //0.5s后自动关闭
        });
    }, function () {

    });
}

function projectDell(id) {
    var aData = {};
    aData.id = id;
    submitUrl('./project/delete', aData, function (data) {
        console.log(data);
        if (data.code == "000") {
            layer.alert('删除成功！', {icon: 6, title: '提示', closeBtn: 0}, function () {
                layer.closeAll();
            });
        } else {
            layer.alert('删除失败', {icon: 5, title: '告警'});
        }
        layer.closeAll('loading');
    });
}

/**
 * 生成项目
 * @param id
 */
function projectProduce(id,produceType) {
    if (produceType === 1){
        var confirmInfo = "<div style='text-align: center;'>确认要生成该项目？</div>";
        layer.confirm(confirmInfo, {
            title: '提示',
            btn: ['确定', '取消'] //按钮
        }, function () {
            var projectInfo = {};
            projectInfo.id = id;
            submitUrl('./project/produceById', projectInfo, function (data) {
                console.log(data);
                if (data.code == "000") {
                    var confirmInfo = "<div style='text-align: center;'>生成成功是否立即下载</div>";
                    layer.confirm(confirmInfo, {
                        title: '提示',
                        btn: ['确定', '取消'] //按钮
                    }, function () {
                        newWin("./project/download?filePath=" + data.content,1);
                        layer.msg('操作成功', {
                            time: 1000 //0.5s后自动关闭
                        });
                    }, function () {
                        layer.closeAll();
                    });
                } else {
                    layer.alert('生成失败', {icon: 5, title: '告警'});
                }
                layer.closeAll('loading');
            });
        }, function () {

        });
    }else{
        choseTable(id);
    }
}

function choseTable(id) {
    layer.open({
        type: 2,
        area: ['700px', '400px'],
        fixed: false, //不固定
        content: './project/produceProject.html?id='+id,
        cancel: function(){

        }
    });
}

/**
 * 跳转编辑页面
 */
function projectEdit(id) {
    $("#main-body").load("./project/projectEdit.html?id=" + id).attr("projectId", id);
}


/**
 * 跳转到详情页面
 * @param id
 */
function detail(id) {
    $("#main-body").load("./project/projectDetail.html?id=" + id).attr("projectId", id);
}

function newWin(url, id) {
    var a = document.createElement('a');
    a.setAttribute('href', url);
    a.setAttribute('target', '_blank');
    a.setAttribute('id', id);
    // 防止反复添加
    if(!document.getElementById(id)) {
        document.body.appendChild(a);
    }
    a.click();
}  