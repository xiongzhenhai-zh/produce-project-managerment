var $id = $("#id");
var $groupid = $("#groupid");
var $artifactid = $("#artifactid");
var $name = $("#name");
var $alies = $("#alies");
var $databasename = $("#databasename");
var $pkgname = $("#pkgname");
var $autor = $("#autor");
var $createTime = $("#createTime");
var $exclusionsTable = $("#exclusionsTable");
var $produceType = $("#produceType");
var $dbUrl = $("#dbUrl");
var $dbUsername = $("#dbUsername");
var $dbPassword = $("#dbPassword");
var $serverFrameType = $("#serverFrameType");
var $clientFrameType = $("#clientFrameType");

var $Cancel = $("#Cancel");

function _initElemts(){
	submitUrl('./project/queryById',{"id":$("#main-body").attr("projectId")} ,function(data) {
        console.log(data);
        if (data.code == "000") {
            $id.html(data.content.id);
            $groupid.html(data.content.groupid);
            $artifactid.html(data.content.artifactid);
            $name.html(data.content.name);
            $alies.html(data.content.alies);
            $databasename.html(data.content.databasename);
            $pkgname.html(data.content.pkgname);
            $autor.html(data.content.autor);
            $createTime.html((new Date(data.content.createTime)).pattern("yyyy-MM-dd hh:mm:ss"));
            $exclusionsTable.html(data.content.exclusionsTable);
            $produceType.html(data.content.produceType===1?'数据库反向生成':'根据模型生成');
            $serverFrameType.html(data.content.serverFrameType);
            $clientFrameType.html(data.content.clientFrameType);
            $dbUrl.html(data.content.dbUrl);
            $dbUsername.html(data.content.dbUsername);
            $dbPassword.html(data.content.dbPassword);
        } else {
            layer.alert('保存失败', {icon: 5,title: '告警'});
        }
        layer.closeAll('loading');
     });
}

function _initEvent(){
	$Cancel.click(function(){
		layer.closeAll('dialog');
		$("div[data-role='./project/projectList.html']").click();
	})
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


$(document).ready(function() {
    _initSelect();
	_initElemts();
	_initEvent();
});