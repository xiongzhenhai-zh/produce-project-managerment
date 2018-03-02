$(document).ready(function(){
	initializeEditVersionhtml($("#main-body").attr("versionId"));
	
	 //指定校验规则
	var returnEditValidation = $("#editVersionForm").validate({
		rules : {
			checkRequired : {
				required : true
			},
			checkOnlyNumber:{
				required : true,
				number : true
			},
			checkdigits : { //非空 + 整数
				required : true,
				digits : true
			},
		},
		onfocusout : function(element) {
			console.log("onfocusout1");
			$(element).valid();
			console.log("onfocusout2");
		},
		onkeyup : function(element) {
			console.log("onkeyup1");
			$(element).valid();
			console.log("onkeyup2");
		}
	});
	
});


$("#editVersionBtn").click(function() {
	console.log("returnEditValidation.form()" + returnEditValidation.form());
	if (returnEditValidation.form()) {
		layer.confirm('确认编辑', {
			title : '提示'
		}, function() {
			console.log("testset");
			layer.closeAll('dialog');
			// 显示精度条
			layer.load();
			if ($("#editVersionForm input[hasChanged=1]").length > 0) {
				$('#editVersionForm').ajaxSubmit({
					url : './common/versionUpload',
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.code == "000") {
							updateVersionInfo(data.content);
						} else {
							layer.alert('上传失败', {
								icon : 5,
								title : '告警'
							});
						}
						layer.closeAll('loading');
					},
					error : function(returndata) {
						layer.alert('上传失败', {
							icon : 5,
							title : '告警'
						});
						layer.closeAll('loading');
					}
				});
			} else {
				updateVersionInfo(null);
			}
		}, function() {
			layer.closeAll('loading');
		});
	} else {
		console.log("有必填信息未填");
//		layer.alert('有必填信息未填写', {
//			icon : 5,
//			title : '告警'
//		});
	}
});

//保存活动信息
function updateVersionInfo(imgUrl){
		$.ajax({
			url:"./version/updateVersion",
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify({
					versionId:$("#main-body").attr("versionId"),
					clientSystem:$('#versionLabel').val(),
					versionNum:$("#versionNum").val(),
					description:$('#description').val(),
					downloadUrl: imgUrl,
					forceUpgrade: $('#forceUpgrade').val(),
					publishTime:$('#publishTime').val(),
				}),
			dataType:"json",
			success:function(data){
				if(data.code == "000"){
					layer.closeAll('loading'); //关闭加载层
					layer.alert('修改成功！', {icon: 6 ,title: '提示'} , function(){
						window.history.go(-1);
						layer.closeAll();
					});
				} else {
					layer.closeAll('loading'); //关闭加载层
					layer.alert('修改失败！', {icon: 5 ,title: '告警'});
				}
			},
			error:function(){
				layer.closeAll('loading'); //关闭加载层
				layer.alert('修改失败！', {icon: 5 ,title: '告警'});
			}
		});
}


function initializeEditVersionhtml(versionId) {
	var setObj = {};
	setObj.versionId = versionId;
	$.ajax({
		url:"./version/queryCurrentVersion",
		type:"POST",
		data:JSON.stringify(setObj),
		contentType : "application/json",
		dataType:"json",
		success:function(data){
			if(data.code == "000"){
				$('#versionLabel').val(data.content.clientSystem);
				$('#versionNum').val(data.content.versionNum);
				$('#description').val(data.content.description);
				initEditFileInput("versionFile", data.content.downloadUrl);
				$('#versionFile').fileinput("setCaption",data.content.downloadUrl);
				if (data.content.forceUpgrade == 1) {
					 $(":radio[name='forceUpgrade'][value=1]").attr("checked", true);
				} else {
					 $(":radio[name='forceUpgrade'][value=0]").attr("checked", true);
				}
				console.log("data.content.publishTime"+data.content.publishTime);
				$('#publishTime').val(data.content.publishTime);
			} else {
				layer.alert('操作失败，没有数据', {icon: 5,title: '告警'});
				layer.closeAll('loading');
			}
		},
		error:function(){
			layer.alert('操作失败，没有数据', {icon: 5,title: '告警'});
			layer.closeAll('loading');
		}
	});
}

function initEditFileInput(inputfileId, downloadUrl){
	$('#'+inputfileId).fileinput({
		language : 'zh',
		uploadUrl : '#',
		allowedFileExtensions : ['apk'],//接收的文件后缀
		showUpload : false, //是否显示上传按钮
		browseClass : "btn btn-primary", //按钮样式             
		minFileCount : 1,
		showPreview: false,
		maxImageWidth: 1080,
		minImageWidth: 1080,
		maxImageHeight: 1920,
		minImageHeight: 1920,
		maxFileSize : 40960,
		layoutTemplates : {
			footer : ''
		},//不显示图片下方的删除和上传小按钮
		enctype : 'multipart/form-data',
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		initialPreview: [ //预览图片的设置
                          "<img src='"+downloadUrl+"' class='file-preview-image'  >"  
                      ],
	});
	
	//添加事件监听
	$('#'+inputfileId).on('fileclear', function(event) {	
	    $("#"+inputfileId+"_OldUrlKey").val("");
	});
	
	//添加事件监听
	$('#'+inputfileId).on('fileerror', function(event) {
	    $("#"+inputfileId+"_OldUrlKey").val("");
	});
	
	//添加事件监听，当重新选择时，给与标记
	$('#'+inputfileId).on('change', function(event) {
	    $(this).attr("hasChanged","1");
	    $("#"+inputfileId+"_OldUrlKey").val("");
	});
}