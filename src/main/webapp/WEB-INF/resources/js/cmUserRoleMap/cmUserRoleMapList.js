var datatable = null;
var btnAuths; //本页面拥有的按钮权限
var $cmUserRoleMapTable;
$(document).ready(function() {
	//查询按钮权限
		$cmUserRoleMapTable = $("#cmUserRoleMapTable");
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
            reqObj.userId=$("#userId").val()==""?null:$("#userId").val();
            reqObj.roleId=$("#roleId").val()==""?null:$("#roleId").val();
		    datatable.fnDraw();
		} 
	});		
	
	$("#resetBtn").click(function() {
			$("#id").val("");
			$("#userId").val("");
			$("#roleId").val("");
		
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
               {"bSortable": false, "mData": null},// 用户编号
               {"bSortable": false, "mData": null},// 角色编号
               {"bSortable": false, "mData": null} // 操作
           ];
    datatable = initDataTableCommonUseUrl($cmUserRoleMapTable,columns,"./cmUserRoleMap/selectPageList",reqObj,"content",function (nRow, aData, iDisplayIndex) {
$("td:eq(0)", nRow).text(aData.id);$("td:eq(1)", nRow).text(aData.userId);$("td:eq(2)", nRow).text(aData.roleId);
         var $operations_edit = $("<span><a style='cursor: pointer;' onclick=cmUserRoleMapEdit('"+aData.id+"') class='deleteRowCls'>编辑</a></span>");
         var $operations_detail = $("<span><a style='cursor: pointer;' onclick=detail('" + aData.id + "') class='deleteRowCls'>详情</a></span>");
         var $operations_del = $("<span><a style='cursor: pointer;' onclick=confirmDel('"+aData.id+"') class='deleteRowCls'>删除</a></span>");
        $("td:eq(3)", nRow).empty();
        $("td:eq(3)", nRow).append($operations_edit).append(" ").append($operations_detail).append(" ").append($operations_del);
    });
}

});



 
 function confirmDel(id){
		//询问框	
		var confirmInfo = "";
		   confirmInfo = "<div style='text-align: center;'>确认要删除该用户与角色关系表？</div>";
		
		layer.confirm(confirmInfo, {
		  title:'提示',
		  btn: ['确定','取消'] //按钮
		}, function(){
			cmUserRoleMapDell(id);
			layer.msg('操作成功', {
			    time: 1000, //0.5s后自动关闭
			  });
		}, function(){
		  
		});	
	}

function cmUserRoleMapDell(id){
	var aData = {};
	aData.id = id;
	$.ajax({
		type : 'post',
		url : "./cmUserRoleMap/delete", // 地址
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
 function cmUserRoleMapEdit(id){
 	$("#main-body").load("./cmUserRoleMap/cmUserRoleMapEdit.html?id="+id).attr("cmUserRoleMapId",id);
 }


 /**
  * 跳转到详情页面
  * @param id
  */
 function detail(id){
     $("#main-body").load("./cmUserRoleMap/cmUserRoleMapDetail.html?id="+id).attr("cmUserRoleMapId",id);
 }