/**
 * 校验工具类
 * 使用方法：在文档加载完成是调用，例如
 * 
 * $(function(){
 * 	  var ReturnValidatorObj =  ValidateFormUtil.valid($("#someForm"));
 * });
 * 
 * 在表单提交前先验证
 * if(ReturnValidatorObj.form()){
 * 	   //doSomething
 * }
 * 
 */

ValidateFormUtil = {
	
	valid : function($form) {
		//扩展校验
		this.addExtendMethod();
		
		// 初始化验证模块
		return $form.validate({
			rules : {
				checkNULL: {
					
				},
				checkRequire : { //非空
					required : true
				},
				checkdigits : { //非空 + 整数
					required : true,
					digits : true
				},
				checkOnlydigits : { //必须输入整数。
					digits : true
				},
				checkdigitsRange200 : {
					required : true,
					digits : true,
					range : [ 1, 200 ]
				},
				checkNumber : { //非空 + 数值
					required : true,
					number : true
				},
				checkOnlyNumber : {  //必须输入合法的数字（负数，小数）。
					number : true
				},
				checkUrl : { //合法url
					//urlHead:true,
					url:true
				},
				checkEmail : { //合法email
					email:true
				},
				checkStartTime : { //检查开始时间大于当前时间
					required : true,
					greatThanNow : true
				},
				checkUrlHead : {
					required : true,
					urlHead :true
				},
				checkDiscount : { //折扣
					required : true,
					digits : true,
					range : [ 0, 99 ]
				},
				checkPercentage : { //百分比
					required : true,
					number : true,
					range : [ 0, 100 ]
				},
				/*checkRequireFun : {
					required : function(){ //返回为真，表示需要验证。
						
					}
				},*/
				checkChiness : { //1-6个中文字符校验
					required : true,
					chiness : true
				},
				checkChinessOnly : { //1-6个中文字符校验
					chiness : true
				},
				checkCharLength16 : {
					required : true,
					charLengthCaculate16 : true
				},
				checkOrderNumber : {
					required : true,
					digits : true,
					range : [ 1, 999 ]
				},
                charLengthCaculate : true,
                patternStr:true,//匹配正则
                leNumber:true,//小于等于
                fileNotNull : true//文件不为空
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			},
			onfocusout : function(element) {
				$(element).valid();
			},
			onkeyup : function(element) {
				if (element.id == "username" || element.id == "merchantName") {
					return false;
				} else if ($(element).attr("name") == "checkdigits" || $(element).attr("name") == "checknumber") {
					// 如果是数字，最后不允许输入空格
					$(element).val($(element).val().trim());
					$(element).valid();
				} else {
					$(element).valid();
				}
			}
		});
	},
	addExtendMethod : function() { //自定义校验方法
		
		
		jQuery.validator.addMethod("greatThanNow", function(value, element) {
			var now = new Date();
			value = value.replace(/-/g, "/");
			var valueDate = new Date(value);
			if (valueDate < now) {
				return false;
			}
			return true;
		}, "时间必须晚于当前时间");
		
		jQuery.validator.addMethod("chiness", function(value, element) {
			if(value == null || value == "") return true;
			var reg= /^[\u4E00-\u9FA5]+$/g ;
			return reg.test(value);
		}, "只能输入中文");
		
		jQuery.validator.addMethod("urlHead", function(value,element){
			var reg= /^((https|http):\/\/)/i ;
			return reg.test(value);
		},"必须以http://或者https://开头");
		
		jQuery.validator.addMethod("charLengthCaculate16", function(value, element) {
			var i = len = 0;    
		    if(value == null) return true;
		    for( ; i<value.length; i++){
				if( value.charCodeAt( i ) > 255 ){
				   len += 2;
				}else{
				   len++;
				}		   
		    }
			if (len <= 16) {
				return true;
			}
			return false;
		}, "最多可以输入16个字符");

        /**
         * 非空且字符长度限制
         * 使用方式，在input控件中增加  charLengthCaculate = 60 以及 required属性，
         * 例如： <input type="text" name="name" charLengthCaculate="60" required/>
         */
        jQuery.validator.addMethod("charLengthCaculate", function(value, element,param) {
            var i = len = 0;
            if(value == null) return false;
            for( ; i<value.length; i++){
                if( value.charCodeAt( i ) > 255 ){
                    len += 2;
                }else{
                    len++;
                }
            }
            if (len <= param) {
                return true;
            }
            $(element).attr("maxlength",param);
            return false;
        }, "最多可以输入{0}个字符");

        /**
         * 匹配正则表达式
         * 使用方式，在input控件中增加  patternStr = "\\d+" 以及 required属性，
         * 例如： <input type="text" name="name" patternStr="60" required/>
         */
        jQuery.validator.addMethod("patternStr", function(value, element,param) {
            var re = new RegExp(param);
            return re.test(value);
        }, "输入不符合规范");

        /**
         * 文件不为null,使用方式，在input 里面加上
         * class="fileNotNull",例如：<input type="file" name="file" class="fileNotNull"/>
         * 以及初始化时必须要加上imgurlkey属性
         * $file.on('change', function(event) {
			$(this).attr("hasChanged", "1");
			$file.attr("imgUrlKey", "");
		+	$file.parents(".file-input").find("font").remove();
		+	$file.parents(".file-input").find(".kv-fileinput-caption").css("border","");
		});
         */
        jQuery.validator.addMethod("fileNotNull", function(value, element) {
            $(element).parents(".file-input").find("font").remove();
            if ($(element).attr("imgurlkey")=="") {
                $(element).parents(".file-input").append("<font class=\"redStar\">这是必填字段</font>");
                $(element).parents(".file-input").find(".kv-fileinput-caption").css("border","1px solid red");
                return false;
            }
            return true;
        }, null);

        /**
         * 小于等于某个整数
         */
        jQuery.validator.addMethod("leNumber", function(value, element,param) {
            if (parseInt(value)<=parseInt(param) && parseInt(value)%1 === 0 ) {
                return true;
            }
            return false;
        }, "输入数不能大于{0}");
	}
}
