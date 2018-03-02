var datatable = null;
var btnAuths; //本页面拥有的按钮权限
var $cmPrivTable;
$(document).ready(function() {
	//查询按钮权限
		$cmPrivTable = $("#cmPrivTable");
		var reqObj = getJsonParam() || {
		    pageLength:10,
		    currentPageNo:1
		};
		initDataTable1();
		var $form = $("#form");
		var $validate = ValidateFormUtil.valid($form);

		


	$("#searchBtn").click(function() {
		$isValid = true;
		$.each($("#stockRange :input"),function(i,n){
			if(!$validate.element($(n))){
				$isValid =false;
			}
		})
		
		if($isValid)
		{
            reqObj.id=$("#id").val()==""?null:$("#id").val();
            reqObj.name=$("#name").val()==""?null:$("#name").val();
            reqObj.pid=$("#pid").val()==""?null:$("#pid").val();
            reqObj.category=$("#category").val()==""?null:$("#category").val();
            reqObj.menuIcon=$("#menuIcon").val()==""?null:$("#menuIcon").val();
            reqObj.menuStat=$("#menuStat").val()==""?null:$("#menuStat").val();
            reqObj.menuSort=$("#menuSort").val()==""?null:$("#menuSort").val();
            reqObj.openUrl=$("#openUrl").val()==""?null:$("#openUrl").val();
            reqObj.identifyCode=$("#identifyCode").val()==""?null:$("#identifyCode").val();
            reqObj.reamrk=$("#reamrk").val()==""?null:$("#reamrk").val();
		    datatable.fnDraw();
		} 
	});		
	
	$("#resetBtn").click(function() {
			$("#id").val("");
			$("#name").val("");
			$("#pid").val("");
			$("#category").val("");
			$("#menuIcon").val("");
			$("#menuStat").val("");
			$("#menuSort").val("");
			$("#openUrl").val("");
			$("#identifyCode").val("");
			$("#reamrk").val("");
		
	});
	
	 $(document).keypress(function(e) {  
		    // 回车键事件  
		       if(e.which == 13) {  
		   		jQuery("#searchBtn").click();  
		       }  
      }); 	
	
		// 初始化表格
function initDataTable1(){
//	datatable.css("margin","0 5px");
        var columns = [
               {"bSortable": false, "mData": null},// 主键
               {"bSortable": false, "mData": null},// 菜单名称
               {"bSortable": false, "mData": null},// 父权限编号
               {"bSortable": false, "mData": null},// 权限类别
               {"bSortable": false, "mData": null},// 菜单图标
               {"bSortable": false, "mData": null},// 菜单展开状态 
               {"bSortable": false, "mData": null},// 菜单显示顺序
               {"bSortable": false, "mData": null},// 指向的URL
               {"bSortable": false, "mData": null},// 按钮的标识
               {"bSortable": false, "mData": null},// 备选字段
               {"bSortable": false, "mData": null} // 操作
           ];
    datatable = initDataTableCommonUseUrl($cmPrivTable,columns,"./cmPriv/selectPageList",reqObj,"content",function (nRow, aData, iDisplayIndex) {
$("td:eq(0)", nRow).text(aData.id);$("td:eq(1)", nRow).text(aData.name);
$("td:eq(2)", nRow).text(aData.pid);$("td:eq(3)", nRow).text(aData.category);
$("td:eq(4)", nRow).text(aData.menuIcon);
$("td:eq(5)", nRow).text(aData.menuStat);
$("td:eq(6)", nRow).text(aData.menuSort);$("td:eq(7)", nRow).text(aData.openUrl);
$("td:eq(8)", nRow).text(aData.identifyCode);
$("td:eq(9)", nRow).text(aData.reamrk);

         var $operations_edit = $("<span><a style='cursor: pointer;' onclick=cmPrivEdit('"+aData.id+"') class='deleteRowCls'>编辑</a></span>");
         var $operations_detail = $("<span><a style='cursor: pointer;' onclick=detail('" + aData.id + "') class='deleteRowCls'>详情</a></span>");
         var $operations_del = $("<span><a style='cursor: pointer;' onclick=confirmDel('"+aData.id+"') class='deleteRowCls'>删除</a></span>");
        $("td:eq(10)", nRow).empty();
        $("td:eq(10)", nRow).append($operations_edit).append(" ").append($operations_detail).append(" ").append($operations_del);
    });
}

});



 
 function confirmDel(id){
		//询问框	
		var confirmInfo = "";
		   confirmInfo = "<div style='text-align: center;'>确认要删除该权限？</div>";
		
		layer.confirm(confirmInfo, {
		  title:'提示',
		  btn: ['确定','取消'] //按钮
		}, function(){
			cmPrivDell(id);
			layer.msg('操作成功', {
			    time: 1000, //0.5s后自动关闭
			  });
		}, function(){
		  
		});	
	}

function cmPrivDell(id){
	var aData = {};
	aData.id = id;
	$.ajax({
		type : 'post',
		url : "./cmPriv/delete", // 地址
		dataType : "json",
		contentType : "application/json",
		data : JSON.stringify(aData), // 数据
		async : false,
		cache : "", // 请求字段是否缓存 
		success : function(data) {// 成功回调
			if (data.code == '000') {
				layer.alert("删除成功");
				layer.closeAll('loading');
			} else {
				layer.alert("删除失败");
				layer.closeAll('loading');
			}
		},
		error : function(data) {// 失败回调
			layer.alert("删除失败");
			layer.closeAll('loading');
		}
	});
	
}
 
 /**
  * 跳转编辑页面
  */
 function cmPrivEdit(id){
 	$("#main-body").load("./cmPriv/cmPrivEdit.html?id="+id).attr("cmPrivId",id);
 }


 /**
  * 跳转到详情页面
  * @param id
  */
 function detail(id){
     $("#main-body").load("./cmPriv/cmPrivDetail.html?id="+id).attr("cmPrivId",id);
 }