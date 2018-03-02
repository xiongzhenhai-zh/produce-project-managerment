/**
 * 关闭AJAX相应的缓存
 */
$.ajaxSetup({
	cache : false
});

function setJsonParam (dataObj) {
    sessionStorage.setItem('page_jump_data', encodeURIComponent(JSON.stringify(dataObj)));
};
function getJsonParam () {
	var obj = {};
	if (decodeURIComponent(sessionStorage.getItem('page_jump_data') || "")) {
    	obj = JSON.parse(decodeURIComponent(sessionStorage.getItem('page_jump_data') || ""));
	}
	sessionStorage.removeItem("page_jump_data");
	return obj;
};

/**
 * 序列化表单里面的元素，将里面的对象转换成JSON对象
 * @param $
 */
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery); 

/**
 * 定义datatable的汉化语言
 */
var dataTableLanguage_zh_CN = {
	    "emptyTable":     "无数据",
	    "info":           "第 _PAGE_ 页 ( 总共 _PAGES_ 页，共 _TOTAL_ 条记录 ) ",
	    "infoEmpty":      "无记录",
	    "infoFiltered":   "(从 _MAX_ 条记录过滤)",
	    "infoPostFix":    "",
	    "thousands":      ",",
	    "lengthMenu":     "每页 _MENU_ 条记录",
	    "loadingRecords": "数据加载中...",
	    "processing":     "处理中, 请稍候...",
	    "search":         "查询:",
	    "zeroRecords":    "无数据",
	    "paginate": {
	        "first":      "首页",
	        "last":       "末页",
	        "next":       "下一页",
	        "previous":   "上一页"
	    },
	    "aria": {
	        "sortAscending":  ": 升序",
	        "sortDescending": ": 将序"
	    }
};

/**
 * 正则表达式 替换括号,尖括号等
 */
function convertBracket(str) {
	var RexStr = /\<|\>|\"|\'|\&/g
	str = str.replace(RexStr, function(MatchStr) {
		switch (MatchStr) {
		case "<":
			return "&lt;";
			break;
		case ">":
			return "&gt;";
			break;
		case "\"":
			return "&quot;";
			break;
		case "'":
			return "&#39;";
			break;
		case "&":
			return "&amp;";
			break;
		default:
			break;
		}
	})
	return str;
}

/**
 * 判断是否包含特殊字符
 */
function CheckSpecialStr(str) {
	if (/[\':;*?~`!@#$%^&+={}\[\]\<\>\(\),\.]/.test(str)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 设置cookie
 * 
 * @param name
 * @param value
 */
function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

/**
 * 获取cookie
 * 
 * @param name
 * @returns
 */
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

/**
 * 清空cookie
 * 
 * @param name
 */
function cleanCookie(name) {
	setCookie(name, "", -1);
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
 * 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg: (new
 * Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new
 * Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new
 * Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new
 * Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}


/**
 * 将yyyy-mm-dd转成date对象
 * @param dateString
 * @returns {Date}
 */
function parseDate(dateString) {
	if (dateString == null || dateString == "") {
		return new Date();
	} else {
		var date = dateString.split(" ")[0];
		var time = dateString.split(" ")[1];
		var d = new Date();
		d.setFullYear(date.split("-")[0]);
		d.setMonth(parseInt(date.split("-")[1]) - 1);
		d.setDate(date.split("-")[2]);
		if (time != undefined) {
			d.setHours(time.split("-")[0]);
			d.setMinutes(time.split("-")[1]);
			d.setSeconds(time.split("-")[2]);
		}
		return d;
	}
};

/**
 * 邮件判断正则表达式
 * @param str
 * @returns
 */
function isEmail(str) {
	var reg = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
	return reg.test(str);
}


/**
 * 数组去重
 */
Array.prototype.unique = function() {
	var n = {}, r = []; // n为hash表，r为临时数组
	for (var i = 0; i < this.length; i++) // 遍历当前数组
	{
		if (!n[this[i]]) // 如果hash表中没有当前项
		{
			n[this[i]] = true; // 存入hash表
			r.push(this[i]); // 把当前数组的当前项push到临时数组里面
		}
	}
	return r;
}


/**
* 模拟java中的map来自定义js的map
*/
function Map() 
{
	var struct = function (key, value) 
	{   
		//构造函数
		this.key = key;
		this.value = value;
	};
	var put = function (key, value) 
	{ 
		//定义put方法
		for (var i = 0; i < this.arr.length; i++) 
		{
			if (this.arr[i].key === key) 
			{
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	};
	var get = function (key) 
	{ 
		//定义get方法
		for (var i = 0; i < this.arr.length; i++) 
		{
			if (this.arr[i].key === key) 
			{
				return this.arr[i].value;
			}
		}
		return null;
	};
	var remove = function (key) 
	{ 
		//定义remove方法
		var v;
		for (var i = 0; i < this.arr.length; i++) 
		{
			v = this.arr.pop();
			if (v.key === key) 
			{
				continue;
			}
			this.arr.unshift(v);
		}
	};
	var size = function () 
	{
		return this.arr.length;
	};
	var isEmpty = function () 
	{
		return this.arr.length <= 0;
	};
	var values = function()
	{  
		//获得map的值,author:lcs
		var resultVal  = new Array();
		for (var i = 0; i < this.arr.length; i++) 
		{
			resultVal.push(this.arr[i].value);
		}
		return resultVal;
	};
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
	this.values= values;
}

/**
 * 封装初始化图片控件<br>
 * 
 * 新增时调用实例: initFileInputComponent($file); <br>
 * 修改时调用实例: initFileInputComponent($file, "3050_SNIJ9PWVM2-LHDHUAE4T7F5P2KX7AHN3-8AYNDSXI-0.png", "爱思特整形.png"); <br>
 * 修改属性实例：  initFileInputComponent($file, "3050_SNIJ9PWVM2-LHDHUAE4T7F5P2KX7AHN3-8AYNDSXI-0.png", "爱思特整形.png", {maxFileSize:32}); <br>
 * 注意：该控件最大允许上传图片大小为4M<br>
 * 
 * @param $file 待初始化的对象(如:$('#inputId'))
 * @param imgUrlKey 图片的key，新增时传空，修改是传值
 * @param imgName 图片的名称，新增时传空，修改是传值
 * @param options 图片的名称，新增时传空，修改是传值
 */
function initFileInputComponent($file, imgUrlKey, imgName, options){
	var previewImgs = [];
	if(imgUrlKey){
		previewImgs.push("<img src='"+imgUrlKey+"' class='file-preview-image'>");
	}
	
	var defaultOptions = {
		language : 'zh',
		uploadUrl : '#',
		allowedFileExtensions : [ 'jpg', 'png', 'gif','jpeg','JPG', 'PNG', 'GIF','JPEG'],//接收的文件后缀
		showUpload : false, //是否显示上传按钮
		browseClass : "btn btn-primary", //按钮样式             
		minFileCount : 1,
		showPreview: true,
		maxFileSize : 4096,
		layoutTemplates : {
			footer : ''
		},//不显示图片下方的删除和上传小按钮
		enctype : 'multipart/form-data',
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		initialPreview: previewImgs,
	};
	var opt = $.extend(defaultOptions, options);
	
	//加载控件
	$file.fileinput(opt);
	
	//设置标题
	if(imgName){
		$file.fileinput("setCaption",imgName);
	}else if(imgUrlKey){
		$file.fileinput("setCaption",imgUrlKey);
	}
	
	//保存图片默认的key
	$file.attr({"imgUrlKey":imgUrlKey, "imgName":imgName});
	
	//添加事件监听
	$file.on('fileclear fileerror change', function(event) {	
		$file.attr("imgUrlKey","");
	});
	
}

/**
 * 获取fileinput控件中选择的文件的名称<br>
 * 如果一个控件有多个文件，则名称用逗号隔开
 * @param $file jquery文件控件对象(如:$('#inputId'))
 */
function getFileInputNames($file) {
	var fileNameArray = [];
	var fileStack = $file.fileinput("getFileStack");
	if (fileStack && fileStack.length > 0) {
		for (var i = 0; i < fileStack.length; i++) {
			fileNameArray.push(fileStack[i].name);
		}
	}
	return fileNameArray.join();
}
