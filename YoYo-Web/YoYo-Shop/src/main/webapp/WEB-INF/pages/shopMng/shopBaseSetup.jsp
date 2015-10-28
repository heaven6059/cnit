<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本设置</title>
<link type="text/css" rel="stylesheet" href="${path }/resources/styles/shopEnter/shopRegister.css?r=${versionVal}">
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function(){
		
		
		
		//公司注册地址  获取一级地区
		 findArea(0,1,"companyCarea_1");
		//获取二级地区
		$("#companyCarea_1").combobox({onSelect:function(record){
			//清空后面下拉框的数据
			$("#companyCarea_2").combobox("clear");
			$("#companyCarea").combobox("clear");
			findArea(record.id,2,"companyCarea_2");
		}});
		//获取三级地区
		$("#companyCarea_2").combobox({onSelect:function(record){
			findArea(record.id,3,"companyCarea");
		}}); 
		
		var careaIds = "${shop.companyCareaIds}";
		if(careaIds!=null && careaIds.trim()!=''){
			
			var cIds = careaIds.split("-");
			setDefaultArea(0,1,"companyCarea_1",cIds[0]);  //联系地址
			setDefaultArea(cIds[0],2,"companyCarea_2",cIds[1]);
			setDefaultArea(cIds[1],3,"companyCarea",cIds[2]);
		}
		
		$("#shop_reg_sub_next").click(function(){
		 	 if(!$("#shop_info_form").form('validate')){
				return false;
			}  
			var reg_data = biz.serializeObject($("#shop_info_form"));
		    reg_data.companyCarea = $("#companyCarea_1").combo('getText')+"-"+$("#companyCarea_2").combo('getText')+"-"+$("#companyCarea").combo('getText');
		    reg_data.companyCareaIds = $("#companyCarea_1").combo('getValue')+"-"+$("#companyCarea_2").combo('getValue')+"-"+$("#companyCarea").combo('getValue');
		    if($("#companyCarea").combo('getValue')=='' || $("#companyCarea").combo('getValue')==null){ //只有2级地区，如台湾，香港
				 reg_data.companyCarea=$("#companyCarea_1").combo('getText')+"-"+$("#companyCarea_2").combo('getText');
				 reg_data.companyCareaIds = $("#companyCarea_1").combo('getValue')+"-"+$("#companyCarea_2").combo('getValue');
		    }
			$.ajax({
				url : biz.rootPath() + "/shopManager/shopInfoUpdate",
				data : reg_data,
				type : "post",
				datatype : "json",
				success : function(data) {console.log(data);
					if (data.head.retCode=='000000') {
						$.messager.alert('提示', '修改成功！', 'info');
						setTimeout(function(){window.location.reload();},1000);
						;
					} else {
						$.messager.alert('错误', '保存失败！', 'error');
					}
				}
			});
		});
		
		initTree('good_category_ids', true,"${shop.catIds }");
		
	});
	
	//查找地址，obj:下拉框的id
	function findArea(pid,deepId,obj){
		$.getJSON(biz.rootPath() + '/areaController/find?areaParentId='+pid+'&areaDeep='+deepId, function(json) {
			
			var data="";
			data+='[{ "text": "--请选择--", "id": ""   }';
			$.each(json.resultObject.content, function(i, n) {
				data+=',{ "text": "'+n.areaName+'", "id":'+n.areaId+'}';
			});
			data+="]";
			$("#"+obj).combobox("loadData", $.parseJSON(data));
		});
		
	}
	
	function setDefaultArea(pid,deepId,obj,defaultVal){
		$.getJSON(biz.rootPath() + '/areaController/find?areaParentId='+pid+'&areaDeep='+deepId, function(json) {
			var data="";
			data+='[{ "text": "--请选择--", "id": "" ,"selected":true  }';
			$.each(json.resultObject.content, function(i, n) {
				if(defaultVal!=null && defaultVal.trim()!="" && defaultVal==n.areaId){
					data+=',{ "text": "'+n.areaName+'", "id":'+n.areaId+',"selected":true}';
				}else{
					data+=',{ "text": "'+n.areaName+'", "id":'+n.areaId+'}';
				}
			});
			data+="]";
			$("#"+obj).combobox("loadData", $.parseJSON(data));
		});
		
	}
	
	/**
	 * 方法描述：初始化经营范围下拉树
	 * 
	 * @param obj
	 * @param multiple
	 */
	function initTree(obj, multiple, catIds,initParams) {
		var $obj = $('#' + obj);
		$obj.combotree({
			url : _path + '/companyRegionCat/regionTree',
			multiple : multiple,
			queryParams : initParams || {
				'parentCatId' : 0
			},
			columns : [[{
				field : 'regionId',
				title : '经营范围Id',
				fixed : true
			}, {
				field : 'regionName',
				title : '经营范围名称',
				fixed : true
			}]],
			fitColumns : true,
			loadFilter : function(data) {
				for ( var i = 0; i < data.length; i++) {
					if (data[i].childCount > 0) {
						data[i].state = 'closed';
					} else {
						data[i].state = '';
					}
					data[i].id = data[i].regionId;
					data[i].text = data[i].regionName;
				}
				return data;
			},
			onBeforeExpand : function(row) {
				var url = _path + '/companyRegionCat/regionTree?parentCatId=' + row.regionId;
				$obj.combotree("tree").tree("options").url = url;
				return true;
			},
			onLoadSuccess : function(node, data) {
				$obj.combotree('tree').tree("expandAll");
	 			var catArr = [];
	 			if(catIds!=null&&catIds.trim()!=''){
	 				catArr = catIds.split(",");
	 			}
	 			$('#good_category_ids').combotree('setValues', catArr);
			}
		});
	}
	</script>
	<link type="text/css" href="${path}/resources/styles/shopMng/shopManager.css" rel="stylesheet" />
</head>
<body>
		<div class="shop_manager_right">
			<div class="title"><span style="float:left">基本设置</span>
			</div>
			<form id="shop_info_form" method="post" style="margin-left: -210px;">
				<table class="shop_base_info_table">
					<input type="hidden" name="companyId" value="${shop.companyId }" />
					<tr>
						<td style="width:25%;text-align:right">店主姓名:</td>
						<td><input type="text" class="tab_input " id="shopName" name="shopName"
							  value="${shop.shopName }" disabled></td>
					</tr>
					 <tr>
						<td style="width:25%;text-align:right">身份证号:</td>
						<td><input type="text" id="companyIdcard"
							name="companyIdcard" class="tab_input "	value="${shop.companyIdcard }" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">店铺名称:</td>
						<td><input type="text" id="storeName" name="storeName"
							class="tab_input "  value="${shop.storeName }" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">经营范围:</td>
						<td><select id="good_category_ids"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator"   multiple="multiple" disabled></select></td>
					</tr>
						<tr>
						<td style="width:25%;text-align:right">公司所在地区:</td>
						<td><select type="text" style="width: 120px;"
							id="companyCarea_1" name="companyCarea_1"
							class="easyui-combobox "
							data-options="valueField:'id',textField:'text',required:true,editable:false, panelHeight:'auto',panelWidth:'120'"
							missingMessage='请选择省市地区'></select> <input type="text"
							style="width: 120px;" id="companyCarea_2" name="companyCarea_2"
							class="easyui-combobox "
							data-options="valueField:'id',textField:'text',required:true,editable:false, panelHeight:'auto',panelWidth:'120'"
							missingMessage='请选择省市地区'> <input type="text"
							style="width: 120px;" id="companyCarea" name="companyCarea"
							class="easyui-combobox "
							data-options="valueField:'id',textField:'text',editable:false, panelHeight:'auto',panelWidth:'120'"
							missingMessage='请选择省市地区'></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">详细地址:</td>
						<td><input type="text" id="companyCaddr" name="companyCaddr"
							class="tab_input easyui-validatebox" data-options="required:true" value="${shop.companyCaddr}" missingMessage='请填详细地址'></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right;">店铺标志:</td>
						<td>
						
						 <div>
							<div style="height:31px;">
								<div class="fileInputContainer"><input type="file" name="imageFile" id="imageId" class="fileInput" onchange="previewImage(this,'previewImageId','imgimageId',358,248)" /></div>
								<input type="button" onclick="submitForm('imageId','imageId1','imgImageid')"	class="upLoad"  value="上传" />
							</div>
							<div id="previewImageId" class="shop_logo">
								<img id="imgImageid"  width="360" height="250" >
							</div>
						 </div>
				
						<input type="hidden" id="imageId1" name="image" value="${shop.image }" >
						
						</td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">企业名称:</td>
						<td><input type="text" id="companyName" name="companyName"
							class="tab_input "  value="${shop.companyName }" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">营业执照号:</td>
						<td><input type="text" id="companyNo" name="companyNo"
							class="tab_input" value="${shop.companyNo }" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">税务登记证号:</td>
						<td><input type="text" id="companyTaxno" name="companyTaxno"
							class="tab_input " value="${ shop.companyTaxno}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">企业组织机构代码:</td>
						<td><input type="text" id="companyCodename"
							name="companyCodename" class="tab_input " value="${ shop.companyCodename}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">法定代表人:</td>
						<td><input type="text" id="companyIdname"
							name="companyIdname" class="tab_input" value="${ shop.companyIdname}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">法人身份证号:</td>
						<td><input type="text" id="companyIdcard"
							name="companyIdcard" class="tab_input "
							onkeyup="value=value.replace(/[^0-9a-zA-Z]/g,'')" value="${ shop.companyIdcard}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">公司负责人:</td>
						<td><input type="text" id="companyCname" name="companyCname"
							class="tab_input easyui-validatebox" data-options="required:true"
							missingMessage='请填写真实姓名'  value="${shop.companyCname }"></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">负责人身份证号:</td>
						<td><input type="text" id="companyCidcard"
							name="companyCidcard" class="tab_input easyui-validatebox"
							data-options="required:true" missingMessage='请填写负责人身份证号' validType="idcard" value="${shop.companyCidcard}"></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">企业联系电话:</td>
						<td><input type="text" id="companyCtel" name="companyCtel"
							class="tab_input easyui-validatebox" data-options="required:true" validType="phone"
							missingMessage='此处填写公司联系电话，例：021-87654321' value="${shop.companyCtel}"></td>
					</tr>
				 	<tr>
						<td style="width:25%;text-align:right">银行名称:</td>
						<td><input type="text" id="bankName" name="bankName"
							class="tab_input " value="${ shop.bankName}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">银行卡号:</td>
						<td><input type="text" id="bankCardid" name="bankCardid"
							class=" tab_input " value="${ shop.bankCardid}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">公司注册地址:</td>
						<td> <input type="text"
							 id="companyArea" name="companyArea"
							class="tab_input "  value="${ shop.companyArea}${shop.companyAddr}" disabled></td>
					</tr>
					
					<tr>
						<td style="width:25%;text-align:right">公司注册资金:</td>
						<td><input type="text" id="companyEarnest"
							name="companyEarnest" class="tab_input" value="${ shop.companyEarnest}" disabled></td>
					</tr>			
					<tr>
						<td style="width:25%;text-align:right">公司成立时间:</td>
						<td><input type="text" style="width: 120px;"
							id="company_time" name="company_time"
							class="tab_input  easyui-datebox " value="${ shop.companyTime}"  disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">公司官网地址:</td>
						<td><input type="text" id="companyUrl" name="companyUrl"
							class="tab_input easyui-validatebox" data-options="required:false" validType="isUrl" value="${shop.companyUrl }"></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">营业执照有效期:</td>
						<td><input id="companyTimestart"
							name="companyTimestart"
							class="easyui-datebox textbox" value="${ shop.companyTimestart}" disabled/> ~ <input type="text"
							id="companyTimeend" name="companyTimeend"
							class="easyui-datebox textbox" value="${ shop.companyTimeend}" disabled></td>
					</tr>
					<tr>
						<td style="width:25%;text-align:right">营业执照经营范围:</td>
						<td><textarea cols="50" rows="6" id="companyRemark"
								name="companyRemark" class="tab_input easyui-validatebox"
								data-options="required:true" missingMessage='此处填写营业执照上经营范围' >${shop.companyRemark }</textarea></td>
					</tr> 
				</table>
			</form>
		</div>
		<div id="dlg-buttons" style=" margin-left: 50%;margin-bottom: 20px;">
			<a class="easyui-linkbutton c6" id="shop_reg_sub_next" iconCls="icon-ok" style="width: 90px">保存</a>
		</div>
		<script type="text/javascript">

		if("${shop.image}"!=null && "${shop.image}"!=''){
			$('#imgImageid').attr('src',yoyo.imagesUrl+"${shop.image}");
			console.log("333");
		}
		</script>
</body>
</html>
