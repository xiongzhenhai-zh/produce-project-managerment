$(function(){
	
	var isAuditPage = false;

	window.onhashchange = function(){
		//alert(location.hash);
		var _hash = (location.hash.split("#")[1] || "").split("?")[0];
		var lastPageUrl = !!_hash ? _hash.replace(/_/g, "/") + ".html" : "";

		var $subMenu = $(".menu-link[data-role='" + lastPageUrl + "']");
		var $menu = null;
		if ($subMenu.length > 0) {
			$menu = $subMenu.parent().prev();
		} else {
			$menu = $(".accordion-first-Item[data-role='" + lastPageUrl + "']");
		}
		$menu.length > 0 && $menu.trigger("click");
		$subMenu.length > 0 && $subMenu.trigger("click");

		//判断是否为审核权限
		if(isAuditPage == true){
			$("#currentAuditPeosonFlow").show();
		}else{
			$("#currentAuditPeosonFlow").hide();
		}
	};


	var userId = getCookie("userid");
	//var $reportMan = $(".reportMan");
	//$reportMan.hide();
	var _displayName = "--";
	/*初始化title*/
	var myDate = new Date();
	var hour = myDate.getHours();
	var halloWord = "，您好！";
	if(hour >=0 && hour <= 10){
		halloWord = "，早上好！";
	}else if(hour >=11 && hour <= 13){
		halloWord = "，中午好！";
	}else if(hour >=14 && hour <= 17){
		halloWord = "，下午好！";
	}else if(hour >=18){
		halloWord = "，晚上好！";
	}
	
	//如果尚未有系统访问路径，则查询
	if(!$("#collectionsServerContextPath").val()){
        submitUrl("./user/getAllMenu", null ,function (data) {
			if (data.code == "000"){
                _init(data.content);
			}else{
                layer.alert('获取用户权限失败！', {icon: 5 ,title: '告警'});
			}
        });


	}
	
	function _init(ele){
		$("#accordion").children().remove();
			for(var i=0;i<ele.length;i++){
				var $html = $('<h3 class="accordion-first-Item menu-font-color" data-header="true" data-first-mec-num="12000">'
						  +'<img class="accordion-first-Item-img1" src="'+ele[i].menuIcon+'" />'
						  +'<div class="accordion-first-Item-Item accordion-first-Item-span">'+ele[i].name+'</div>'
						  +'<img class="accordion-first-Item-img2" src="./resources/img/home/icon_09.png" /></h3>'
						  );
				var $container = $('<div class="accordion-item-contain menu-font-color"></div>');
				$("#accordion").append($html).append($container);
				
				var subEle = ele[i].subPrivs;
				if (subEle) {
				for(var j=0;j<subEle.length;j++){
					$container.append("<div class='menu-link' data-mec-num='"+ subEle[j].id +"' data-role='" + subEle[j].openUrl + "'>" + subEle[j].name + "</div>");
				}
				}
			}
			
			$("#main-header-title").html("hi，"+ halloWord);
			
			//添加菜单事件绑定
			$(".menu-link").each(function(){
				$(this).click(function(){
					$(".menu-link").removeClass("menu-link-choose");
					$(this).addClass("menu-link-choose");
					var currManuId = parseInt($(this).attr("data-mec-num"));
					
					if($("#laydate_box").length>0){
						$("#laydate_box").remove();
					}
					if($("#lightbox").length>0){
						$("#lightbox").remove();
						$("#lightboxOverlay").remove();
					}
					
					$(".easyDialogHiddenDiv").remove();
					
					$("#main-body").empty();
					$("#main-body").attr("currManuId",currManuId);
					
					//判断是否为审核权限
					if(isAuditPage == true){
						$("#currentAuditPeosonFlow").show();
					}else{
						$("#currentAuditPeosonFlow").hide();
					}
					
					var _url = $(this).attr("data-role");
					var _hash = _url.split(".html")[0].replace(/\//g, "_");
					
					if(window.location.hash == "#" + _hash) {
						$("#main-body").load(_url, function(XMLHttpRequest, textStatus, errorThrown){//失败回调
							if(textStatus == "error"){
								layer.alert('会话超时，请重新登录', function(index){
									//弹出提示框，跳转到登录页面
									layer.close(index);
									window.location.href = $("#casLoginUrl").val() +"/index.html?url=" + $("#collectionsServerContextPath").val();
								});  
							}
						});
					}else {
						window.location.hash = _hash;
					}
					
			});
		});
			
		/*accordin*/
		$( "#accordion" ).accordion({
			heightStyle: "content",
			icons:false,
			beforeActivate: function( event, ui ) {
				$(ui.newHeader).children(".accordion-first-Item-img2").attr("src","./resources/img/home/icon_08.png");
				$(ui.oldHeader).children(".accordion-first-Item-img2").attr("src","./resources/img/home/icon_09.png");
				$(ui.newHeader).css("background","#0F1922");
				$(ui.newHeader).siblings(".accordion-first-Item").css("background","#203141");
			},
			activate: function( event, ui ) {
			}
	    });
		
		//登录后，默认进入 页面
        var _hash = (location.hash.split("#")[1] || "").split("?")[0];
        var lastPageUrl = !!_hash ? _hash.replace(/_/g, "/") + ".html": "";
        if(lastPageUrl === ""){
            // 没有指菜单，默认到第一个
            $(".menu-link").each(function(index,element){
                if(index === 0){
                    $(this).trigger("click");
                    return ;
                }
            });
        }else {
            $('div[data-role$="' + lastPageUrl + '"]').trigger("click");
        }
	}
	
	/**
	 * 查询用户权限
	 * 
	 */
	function loadUserAuthotity(){
        submitUrl('./authority/queryUserAuthority',{} ,function(data) {
            if(data.code == '000'){
                _init(data.content);
            }else{
                layer.alert('获取用户权限失败！', {icon: 5 ,title: '告警'});
            }
        });
	}
	
	/*嵌套layout 布局为左-右(上-下)*/
	$("body").layout({
		applyDefaultStyles: true,
		west: {
			size:258,
			spacing_open:0,
			closable:false,
			resizable:false,
			onclose_end : function(){
			},
			onopen_end: function(){
			}
		}
	});
	$("#rightLayout").layout({
		applyDefaultStyles: true,
		north: {
			size:82,
			spacing_open:0,
			closable:false,
			resizable:false,
			onclose_end : function(){
			},
			onopen_end: function(){
			}
		}
	});

	$(".accordion-first-Item").each(function(){
		$(this).click(function() {
			$(".menu-link").removeClass("menu-link-choose");
			if($(this).attr("data-header") == "true"){
				return;
			}

			var _url = $(this).attr("data-role");

			window.location.hash = _url.split(".html")[0].replace(/\//g, "_");

			//判断是否为审核权限
			if(isAuditPage == true){
				$("#currentAuditPeosonFlow").show();
			}else{
				$("#currentAuditPeosonFlow").hide();
			}

			currentPage = parseInt($(this).attr("data-mec-num"));
			if($("#laydate_box").length>0){
				$("#laydate_box").remove();
			}
			if($("#lightbox").length>0){
				$("#lightbox").remove();
				$("#lightboxOverlay").remove();
			}

			$(".easyDialogHiddenDiv").remove();

			/*$("#main-body").empty();
			$("#main-body").load(_url, function(XMLHttpRequest, textStatus, errorThrown) {//失败回调
				if(XMLHttpRequest.indexOf("<title>登录</title>")>0){
					//返回登录页，跳转页面
					window.location.href="./index.html";
				}
			});*/

		});
	});

	/**
	 * 退出账号
	 */
	var $quitAccount = $("#quitAccount");
	$quitAccount.click(function(){
		//询问框
		layer.confirm('您确定要退出当前登录账号吗？', {
		    title: "提示",
		    btn: ['确定','取消'] //按钮
		}, function(){
			var data = {};
            $.ajax({
                url: "./user/logout",
                global: false,
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (data.code === "000") {
                        window.location.href = './index.html';
                    } else {
                        alert("用户名或密码错误")
                    }
                },
                error: function (data) {

                }
            });
            layer.closeAll();
		}, function(){
			layer.closeAll();
		});
		
	});
	
	(function(){
		var _hash = (location.hash.split("#")[1] || "").split("?")[0];
		var lastPageUrl = !!_hash ? _hash.replace(/_/g, "/") + ".html" : "";

		if (!!lastPageUrl) {
			var $subMenu = $(".menu-link[data-role='" + lastPageUrl + "']");
			var $menu = null;
			if ($subMenu.length > 0) {
				$menu = $subMenu.parent().prev();
			} else {
				$menu = $(".accordion-first-Item[data-role='" + lastPageUrl + "']");
			}
			$menu.length > 0 && $menu.trigger("click");
			$subMenu.length > 0 && $subMenu.trigger("click");
		} else {
			//进入页面预加载
			/*
			setTimeout(function(){
				$("#main-body").load("/coupon/merchant/home.html");
				window.location.hash = "_merchant-audit_merchant_home";
				currentPage = 0;
			},100);
			*/
		}
	})();
});