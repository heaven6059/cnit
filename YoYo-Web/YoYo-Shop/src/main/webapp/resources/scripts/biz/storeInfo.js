$(function(){
	$("#save_store_info").on("click",function(){
		var info = biz.serializeObject($("#get_store_info"));
		//alert(info[0].value);
		debugger;
		$.ajax({
			url : biz.rootPath() + "/storeInfoSave",
			data : info,
			type : "post",
			datatype : "json",
			success : function(data) {
				if (data.success) {
					//$('#sysmenu_container').dialog('close');
					document.write("操作成功！");
				} else {
					document.write("保存失败");
				}
			}
		});
	})
});