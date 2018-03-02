/**
 * Created by xiongzhenhai on 2018-02-09
 */
//icon 2错 1对 5带表情的错 6 带表情的对  0感叹号 3问号
//成功提示
var succAlert = (function(content) {
    layer.alert(content, {
        icon: 1,
        skin: 'layui-layer-lan'
    });
});

//失败提示
var errorAlert = (function(content) {
    layer.alert(content, {
        icon: 2,
        skin: 'layui-layer-lan'
    });
});

//告警提示
var warningAlert = (function(content) {
    layer.alert(content, {
        icon: 0,
        skin: 'layui-layer-lan'
    });
});

//成功提示
var succMsg = (function(content) {
    layer.msg(content, {
        time:500,
        icon: 1,
        skin: 'layui-layer-lan'
    });
});

//失败提示
var errorMsg = (function(content) {
    layer.msg(content, {
        time:500,
        icon: 2,
        skin: 'layui-layer-lan'
    });
});

//告警提示
var warningMsg = (function(content) {
    layer.msg(content, {
        time:500,
        icon: 0,
        skin: 'layui-layer-lan'
    });
});


var submitUrl_Sync = (function(myurl, mydata, resultFunction) {
    layer.load();
    $.ajax( {
        url : myurl,
        global : false,
        async:false,
        type : "POST",
        data : JSON.stringify(mydata),
        contentType : "application/json",
        success : function(data) {
            layer.closeAll('loading');
            if (data.code === "111") {
                location.href = "./index.html?url=" + window.location.host;
            }
            resultFunction(data);
        },
        error : function() {
            errorAlert("系统错误");
            layer.closeAll('loading');
        }
    });
});

var submitUrl = (function(myurl, mydata, resultFunction) {
    layer.load();
    $.ajax( {
        url : myurl,
        global : false,
        type : "POST",
        data : JSON.stringify(mydata),
        contentType : "application/json",
        success : function(data) {
            layer.closeAll('loading');
            if (data.code === "111") {
                location.href = "./index.html?url=" + window.location.host;
            }
            resultFunction(data);
        },
        error : function() {
            errorAlert("系统错误");
            layer.closeAll('loading');
        }
    });
});

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
};