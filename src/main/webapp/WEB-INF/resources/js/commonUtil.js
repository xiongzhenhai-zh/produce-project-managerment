 
   
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
 * 验证是否有待上传图片，有就执行上传，上传完执行回调方法
 * 1、判断是否有带上传图片
 * 2、如果有，执行上传方法；如果无，直接执行第四步
 * 3、上传完成将地址赋值给对应的imgUrlKey
 * 4、执行回调方法
 * @returns
 */
var submitWithImg = function (fromId, callback){
    layer.load();
    // 1、判断是否有带上传图片
    var f = false;
    $("input[type='file']").each(function(){
        if (!($(this).attr("imgUrlKey"))  && ($(this).fileinput("getFileStack")).length >0 ) {
            f = true;
            return false;
        }
    });
    if (f){
        // 2、如果有，执行上传方法
        $('#' + fromId).ajaxSubmit({
            url : './common/fileUploadToPublic',
            type : 'POST',
            dataType:'json',
            success : function(data) {
                if (data.code == "000") {
                    var urlList = data.content.split(",");
                    // 3、把图片地址放到对应的imgUrlkey中
                    var index = 0;
                    $("input:file").each(function(i,item){
                        var $self = $(this);
                        var files = $self.fileinput("getFileStack");
                        if(files && files.length > 0){
                            $(this).attr("imgUrlKey" , urlList[index]);
                            index++;
                        }
                    });
                    // 4、上传完执行回调方法
                    callback(urlList);
                } else {
                    errorAlert('图片上传失败');
                    layer.closeAll('loading');
                }
            },
            error : function(returndata) {
                errorAlert('图片上传失败');
                layer.closeAll('loading');
            }
        });
    }else {
        // 4、上传完执行回调方法
        callback(null);
    }
};

/**
 * 给初始化表格方法使用
 * dataProp 路径下为null 时转化为空数组
 * @param data
 * @param propList
 * @param i
 * @returns {*}
 */
function objnullToList(data,propList,i) {
    if (data[propList[i]]) {
        if (i < propList.length - 1) {
            data[propList[i]] = objnullToList(data[propList[i]], propList, i + 1);
        }
    } else {
        data[propList[i]] = [];
    }
    return data;
}

/**
 * datatable 对象信息
 * $TableId table的jquery对象，例如 $("#tableId")
 * columns 列元素属组，例如：
 * [
 { data: 'id' , visible: false , orderable: false},
 { data: 'title' , orderable: false},
 { data: 'updateTime' , orderable: false, render: function(data, type, row, meta){
     return new Date(data).pattern("yyyy-MM-dd hh:mm:ss");
 }},
 { data: 'onlineDate', orderable: false ,render: function(data, type, row, meta){
     return new Date(data).pattern("yyyy-MM-dd hh:mm:ss");
 }},
 { data: 'status', orderable: false , render: function(td, data, type, row, meta){
     return data == 1 ? "使用中" : "冻结中";
 }}
 ]
 * url 表数据Url 例如：
 * reqObj 查询对象
 * dataProp 包含数据层面，例如：content.list
 * rowCallback 列数据处理回调函数
 */
function initDataTableCommonUseUrl($TableId, columns, url, reqObj, dataProp, rowCallback){
    propList = dataProp.split(".");
    var datatable = $TableId.dataTable({
        bServerSide: true,
        bLengthChange: true,
        bPaginate: true,
        bProcessing: true,
        sPaginationType: "full_numbers",
        bFilter: false,
        bDestroy: true,
        ordering:false,
        bAutoWidth: false,
        aoColumns: columns,
        sAjaxSource: url,
        sAjaxDataProp: dataProp,
        fnServerData: function (sSource, aoData, fnCallback) {
            var page = {};
            for(var i=0;i<aoData.length;i++){
                if(aoData[i].name=='iDisplayLength'){
                    page.pageLength = aoData[i].value; // iDisplayLength
                }
                if(aoData[i].name=='iDisplayStart'){
                    page.currentPageNo = aoData[i].value;
                }
            }
            reqObj.pageNum = page.pageLength;
            reqObj.currentPage = page.currentPageNo/page.pageLength + 1;
            submitUrl(sSource, reqObj, function (data) {
                data.iTotalRecords = data.totalPage;
                data.iTotalDisplayRecords = data.totalNum;
                if (!data.totalPage) {
                    data.iTotalRecords = data.content.totalPage;
                    data.iTotalDisplayRecords = data.content.totalNum;
                }
                data = objnullToList(data, propList, 0);
                fnCallback(data);
            });
        },
        language: {
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
        },
        fnRowCallback: function(nRow, aData, iDisplayIndex) {
            rowCallback(nRow, aData, iDisplayIndex);
            return nRow;
        }
    });
    return datatable;
}