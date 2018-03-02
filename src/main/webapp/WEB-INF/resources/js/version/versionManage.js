$(document).ready(function() {

    // 加载控件
    $('input:file.projectfile').fileinput({
        language: 'zh',
        uploadUrl: '#',
        allowedFileExtensions: ['apk'],
        // 接收的文件后缀
        showUpload: false,
        // 是否显示上传按钮
        browseClass: "btn btn-primary",
        // 按钮样式
        minFileCount: 1,
        showPreview: false,
        maxImageWidth: 1080,
        minImageWidth: 1080,
        maxImageHeight: 1920,
        minImageHeight: 1920,
        maxFileSize: 40960,
        layoutTemplates: {
            footer: ''
        },
        // 不显示图片下方的删除和上传小按钮
        enctype: 'multipart/form-data',
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
    });

    initVersionDataTable();

    $('#versionTable tbody').on('click', 'tr',
    function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            versionId = getCheckedVersionId($(this)).versionId;
            publishTime = getCheckedVersionId($(this)).publishTime;
        }
    });

    // 跳转至创建活动页面
    $("#createVersion").click(function() {
        var _url = "./version/newVersion.html";
        var _hash = _url.split(".html")[0].replace(/\//g, "_");
        if (window.location.hash == "#" + _hash) {
            $("#main-body").load(_url,
            function(XMLHttpRequest, textStatus, errorThrown) { // 失败回调
                if (textStatus == "error") {
                    layer.alert('会话超时，请重新登录',
                    function(index) {
                        // 弹出提示框，跳转到登录页面
                        layer.close(index);
                        window.location.href = $("#casLoginUrl").val() + "/index.html?url=" + $("#activityServerContextPath").val();
                    });
                }
            });
        } else {
            window.location.hash = _hash;
            $("#main-body").load(_url,
            function(XMLHttpRequest, textStatus, errorThrown) { // 失败回调
                if (textStatus == "error") {
                    layer.alert('会话超时，请重新登录',
                    function(index) {
                        // 弹出提示框，跳转到登录页面
                        layer.close(index);
                        window.location.href = $("#casLoginUrl").val() + "/logout?service=" + $("#collectionsServerContextPath").val();
                    });
                }
            });
        }
    });

    // 跳转至版本编辑页面
    $("#modifyVersion").click(function() {
        if (getCheckedVersionId($('tr.selected')) == null) {
            layer.alert('请选择要编辑的版本！', {
                icon: 5,
                title: '告警'
            });
        } else {
            var _url = "./version/editVersion.html";
            var _hash = _url.split(".html")[0].replace(/\//g, "_");
            if (window.location.hash == "#" + _hash) {
                $("#main-body").load(_url).attr("", versionId);
            } else {
                window.location.hash = _hash;
                $("#main-body").load(_url).attr("versionId", versionId);
            }
        }
    });

    // 跳转至查看页面
    $("#checkVersion").click(function() {
        if (getCheckedVersionId($('tr.selected')) == null) {
            layer.alert('请选择要查看的版本！', {
                icon: 5,
                title: '告警'
            });
        } else {
            var _url = "./version/viewVersion.html";
            var _hash = _url.split(".html")[0].replace(/\//g, "_");
            if (window.location.hash == "#" + _hash) {
                $("#main-body").load(_url).attr("", versionId);
            } else {
                window.location.hash = _hash;
                $("#main-body").load(_url).attr("versionId", versionId);
            }
        }
    });

    // 删除版本
    $("#deleteVersion").click(function() {
        var setObj = {};
        setObj.versionId = versionId;
        if (getCheckedVersionId($('tr.selected')) == null) {
            layer.alert('请选择要删除的版本！', {
                icon: 5,
                title: '告警'
            });
        } else {
            layer.confirm('确定删除？', {
                title: '提示',
                btn: ['确定', '取消']
                // 按钮l
            },
            function() {
                $.ajax({
                    url: "./version/deleteVersion",
                    type: "POST",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(setObj),
                    success: function(data) {
                        if (data.code == "000") {
                            datatable.fnDraw();
                        } else {
                            layer.alert(data.desc, {
                                icon: 5,
                                title: '告警'
                            });
                            layer.closeAll('loading');
                        }
                    },
                    error: function() {
                        layer.alert('删除失败', {
                            icon: 5,
                            title: '告警'
                        });
                        layer.closeAll('loading');
                    }
                });
                layer.msg('删除成功', {
                    time: 1000,
                    // 0.5s后自动关闭
                });
            });
        }
    });

    $('#saveVersionBtn').click(function() {
        if (returnValidation.form()) {
            layer.confirm('确认保存', {
                title: '提示'
            },
            function() {
                layer.closeAll('dialog');
                // 显示精度条
                layer.load();
                $('#versionForm').ajaxSubmit({
                    url: './common/versionUpload',
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {
                        if (data.code == "000") {
                            saveVersionInfo(data.content);
                        } else {
                            layer.alert('上传失败', {
                                icon: 5,
                                title: '告警'
                            });
                        }
                        layer.closeAll('loading');
                    },
                    error: function(returndata) {
                        layer.alert('上传失败', {
                            icon: 5,
                            title: '告警'
                        });
                        layer.closeAll('loading');
                    }
                });
            },
            function() {
                layer.closeAll('loading');
            });
        } else {
            layer.alert('有必填信息未填写', {
                icon: 5,
                title: '告警'
            });
        }
    });

    $('#cancelVersionBtn').click(function() {
        layer.confirm('放弃本次修改', {
            title: '提示'
        },
        function() {
            layer.closeAll('dialog');
            window.history.go( - 1);
        });
    });

    $('#backVersionBtn').click(function() {
        layer.closeAll('dialog');
        window.history.go( - 1);
    });

    // 指定校验规则
    var returnValidation = $("#versionForm").validate({
        rules: {
            checkRequired: {
                required: true
            },
            checkOnlyNumber: {
                required: true,
                number: true
            },
            checkdigits: { // 非空 + 整数
                required: true,
                digits: true
            },
        },
        onfocusout: function(element) {
            console.log("onfocusout1");
            $(element).valid();
            console.log("onfocusout2");
        },
        onkeyup: function(element) {
            console.log("onkeyup1");
            $(element).valid();
            console.log("onkeyup2");
        }
    });

});

// 保存活动信息
function saveVersionInfo(imgUrl) {
    var imgUrlArray = imgUrl.split(',');
    if (imgUrlArray.length == 2) {
        imgUrl = ',' + imgUrl;
        imgUrlArray = imgUrl.split(',');
    }
    var activityUrlTmp = $('#url').val();
    $.ajax({
        url: "./version/saveVersion",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            clientSystem: $('#versionLabel').val(),
            versionNum: $("#versionNum").val(),
            description: $('#description').val(),
            downloadUrl: imgUrlArray[0],
            forceUpgrade: $("input[name='forceUpgrade']:checked").val(),
            publishTime: $('#publishTime').val(),
        }),
        dataType: "json",
        success: function(data) {
            if (data.code == "000") {
                layer.closeAll('loading'); // 关闭加载层
                layer.alert('保存成功！', {
                    icon: 6,
                    title: '提示'
                },
                function() {
                    window.history.go( - 1);
                    layer.closeAll();
                });
            } else {
                layer.closeAll('loading'); // 关闭加载层
                layer.alert('保存失败！', {
                    icon: 5,
                    title: '告警'
                });
            }
        },
        error: function() {
            layer.closeAll('loading'); // 关闭加载层
            layer.alert('保存失败！', {
                icon: 5,
                title: '告警'
            });
        }
    });
}

var reqObj = {};
reqObj.pageSize = 10;
reqObj.pageNum = 1;

// 初始化表格
function initVersionDataTable() {
    var versionColumns = [{
        "bSortable": false,
        "mData": null
    },
    // 编号
    {
        "bSortable": false,
        "mData": null
    },
    // 客户端系统
    {
        "bSortable": false,
        "mData": null
    },
    // 版本号
    {
        "bSortable": false,
        "mData": null
    },
    // 升级描述
    {
        "bSortable": false,
        "mData": null
    },
    // 是否强制升级
    {
        "bSortable": false,
        "mData": null
    },
    // 修改时间
    {
        "bSortable": false,
        "mData": null
    },
    // 发布时间
    ];

    datatable = $('#versionTable').dataTable({
        bServerSide: true,
        bLengthChange: true,
        bPaginate: true,
        bProcessing: true,
        ordering: false,
        sPaginationType: "full_numbers",
        bFilter: false,
        bDestroy: true,
        bAutoWidth: false,
        aoColumns: versionColumns,
        aLengthMenu: [10, 20, 50],
        sAjaxSource: "./version/queryClientVersions",
        sAjaxDataProp: "content.versions",
        fnServerData: function(sSource, aoData, fnCallback) {
            var page = {};
            for (var i = 0; i < aoData.length; i++) {
                if (aoData[i].name == 'iDisplayLength') {
                    page.pageLength = aoData[i].value; // iDisplayLength
                }
                if (aoData[i].name == 'iDisplayStart') {
                    page.currentPageNo = aoData[i].value;
                }
            }
            reqObj.pageSize = page.pageLength;
            reqObj.pageNum = page.currentPageNo / page.pageLength + 1;
            $.ajax({
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                async: false,
                url: sSource,
                data: JSON.stringify(reqObj),
                success: function(data) {
                    if (!data.content.versions) {
                        data.content.versions = [];
                    }
                    data.iTotalRecords = data.content.totalNo;
                    data.iTotalDisplayRecords = data.content.totalNo;
                    fnCallback(data);
                },
                error:function() {
                	 layer.closeAll('loading'); // 关闭加载层
                     layer.alert('版本查询失败！', {
                         icon: 5,
                         title: '告警'
                     });
                }
            });
        },
        language: {
            sProcessing: "处理中, 请稍候...",
            sLoadingRecords: "数据加载中...",
            lengthMenu: "每页 _MENU_ 条记录",
            zeroRecords: "无数据",
            info: "第 _PAGE_ 页 ( 总共 _PAGES_ 页，共 _TOTAL_ 条记录 )",
            infoEmpty: "无记录",
            infoFiltered: "(从 _MAX_ 条记录过滤)",
            oPaginate: {
                sFirst: "首页",
                sPrevious: "上一页",
                sNext: "下一页",
                sLast: "末页"
            }
        },
        fnRowCallback: function(nRow, aData, iDisplayIndex) {
            $("td:eq(0)", nRow).text(aData.versionId);
            $("td:eq(1)", nRow).text(aData.clientSystem);
            $("td:eq(2)", nRow).text(aData.versionNum);
            $("td:eq(3)", nRow).text(aData.description);
            var onlineFlag = aData.forceUpgrade;
            if (onlineFlag == 1) {
                $("td:eq(4)", nRow).text("是");
            } else {
                $("td:eq(4)", nRow).text("否");
            }
            if (aData.updateTime == null) {
                $("td:eq(5)", nRow).text(null);
            } else {
                var updateTime = new Date(aData.updateTime).pattern("yyyy-MM-dd hh:mm:ss");
                $("td:eq(5)", nRow).text(updateTime);
            }
            var publishTime = new Date(aData.publishTime).pattern("yyyy-MM-dd hh:mm:ss");
            $("td:eq(6)", nRow).text(publishTime);
            return nRow;
        }
    });
}

function getCheckedVersionId(data) {
    return datatable.fnGetData(data);
}