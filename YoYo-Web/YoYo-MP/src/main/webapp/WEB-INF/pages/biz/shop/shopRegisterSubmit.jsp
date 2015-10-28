<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>平台添加店铺</title>
<link type="text/css" rel="stylesheet" href="${path}/resources/styles/shop/shopRegister.css">
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>

<script type="text/javascript">
$(function(){
	//分类对话框的实现
	
   /*  $('#good_category_ids').trapown(
			{ url : _path + '/comboBox/virtCateTreeCombox' , multiple : true , idField : 'catId' ,
				textField : 'catName' , parentField : 'parentCatId' , initParentId : 0 }); */
	
	//getSelect2Cat(_path+'/cate/cateTree','good_category_ids',false,"${shopInfo.catIds }");
	initTree('good_category_ids', true,"${shopInfo.catIds }");
	
    //查找店铺等级
  //  findGrade("gradeId");
    
    //公司注册地址  获取一级地区
    findArea(0,1,"companyArea_1");
    //获取二级地区
    $("#companyArea_1").combobox({onSelect:function(record){
        //清空后面下拉框的数
        $("#companyArea").combobox("loadData", {});
        $("#companyArea").combobox("clear");
        findArea(record.id,2,"companyArea_2");
    }});
    //获取三级地区
    $("#companyArea_2").combobox({onSelect:function(record){
        findArea(record.id,3,"companyArea");
    }});
    //公司联系地址
    findArea(0,1,"companyCarea_1");
    
    $("#companyCarea_1").combobox({onSelect:function(record){
        $("#companyCarea").combobox("loadData", {});
        $("#companyCarea").combobox("clear");
        findArea(record.id,2,"companyCarea_2");
    }});
    
    $("#companyCarea_2").combobox({onSelect:function(record){
        findArea(record.id,3,"companyCarea");
        
    }});
    
    initEdit();
    
    $("#shop_reg_sub_next").click(function(){
        if(!$("#shop_reg_form").form('validate')){
           return false;
       }  
       var reg_data = biz.serializeObject($("#shop_reg_form"));
        reg_data.companyArea = $("#companyArea_1").combo('getText')+"-"+$("#companyArea_2").combo('getText')+"-"+$("#companyArea").combo('getText');
        reg_data.companyAreaIds = $("#companyArea_1").combo('getValue')+"-"+$("#companyArea_2").combo('getValue')+"-"+$("#companyArea").combo('getValue');
       if($("#companyArea").combo('getValue')=='' || $("#companyArea").combo('getValue')==null){ //只有2级地区，如台湾，香港
             reg_data.companyArea=$("#companyArea_1").combo('getText')+"-"+$("#companyArea_2").combo('getText');
             reg_data.companyAreaIds = $("#companyArea_1").combo('getValue')+"-"+$("#companyArea_2").combo('getValue');
       }
       
       reg_data.companyCarea = $("#companyCarea_1").combo('getText')+"-"+$("#companyCarea_2").combo('getText')+"-"+$("#companyCarea").combo('getText');
       reg_data.companyCareaIds = $("#companyCarea_1").combo('getValue')+"-"+$("#companyCarea_2").combo('getValue')+"-"+$("#companyCarea").combo('getValue');
       if($("#companyCarea").combo('getValue')=='' || $("#companyCarea").combo('getValue')==null){ //只有2级地区，如台湾，香港
             reg_data.companyCarea=$("#companyCarea_1").combo('getText')+"-"+$("#companyCarea_2").combo('getText');
             reg_data.companyCareaIds = $("#companyCarea_1").combo('getValue')+"-"+$("#companyCarea_2").combo('getValue');
       }
     /*   if($("#agree_checkbox").is(":checked")==false){
            $.messager.alert('提示', '请勾选条款', 'warn');
            return false;
       }  */
      /*  if($("#brand_hidden").val()=='0' && $("#brandName").val()==''){
            $.messager.alert('提示', '请选择品牌', 'warn');
            return false;
       }  */
      /*  if("${type}"=="1"){
	       if($("#gradeId").combobox('getValue')==null || $("#gradeId").combobox('getValue')==''){
	           $.messager.alert('提示', '请选择开店等级', 'warn');
	           return false;
	      } 
       } */
     /*   var category = $('#good_category_ids').combotree('tree').tree('getChecked');
		var categoryIds = [];
		$.each(category,function(i,v){
			categoryIds.push(v.id);
		}); */
		
		
       var category = $('#good_category_ids').combotree('tree').tree('getChecked');
		var categoryIds = [];
		$.each(category,function(i,v){
			if(v.childCount==0){ //保存的是叶子节点 2015.06.13 xiaox修改
				categoryIds.push(v.id);
			}
		});
		if(categoryIds.length==0){
			easyuiMsg('错误',"请选择分类！");
			return false;
		}
		
       reg_data.goodCategory = categoryIds;
       if($("#imageId1").val()=='' || $("#imageCid1").val()=='' ||$("#imageCodeid1").val()==''||$("#imageTaxid1").val()==''){
	   		parent.$.messager.alert('错误', '部分图片没有上传', 'error',function(){
	   			
	   		});
	   		return false ;
	   	}
       parent.$.messager.progress({
            title : '提示',
            text : '数据处理中，请稍后....'
       });
       var url  = biz.rootPath() + "/shopEnter/shopRegisterSave";
       if($('#companyId').val()!=null && $('#companyId').val()!=''){
    	   url  = biz.rootPath() + "/shopEnter/updateShopAllInfo";
       }
        
      console.log(JSON.stringify(reg_data));
       $.ajax({
            url : url,
            data : reg_data, 
            type : "post",
            datatype : "json",
            success : function(data) {console.log(data);
                parent.$.messager.progress('close');
                if (data.head.retCode=='000000') {
                   // window.location.href=biz.rootPath() +"/shopEnter/shopRegisterImg?companyId="+data.content;
                	window.opener=null;
    				window.open('','_self');
    				window.close();
                } else if(data.head.retCode=='000004'){
                    $.messager.alert('错误', '该法人身份证已经入驻！', 'error');
                }else {
                    $.messager.alert('错误', '保存失败', 'error');
                }
            }
        });
    });
    
    
    
    /* //选择品牌
     $('#brand_table').datagrid({
         url :biz.rootPath() + '/brand/brandList',
            columns : [ [ {
                field:'ck',
                checkbox:"true"
            },{
                field : 'brandName',
                title : '品牌名称'          
            }, {
                field : 'brandKeywords',
                title : '品牌别名'
            }, {
                field : 'brandUrl',
                title : '品牌网址'
            }] ],
            toolbar : '#toolbar-brand',
            pagination : true,
            pagePosition : 'bottom',
            rownumbers : true,
            fitColumns : true,
            pageSize : 20,
            pageList : [ 20, 50, 100, 200 ],
            singleSelect : true,
            checkOnSelect:false,
            remoteSort : false,
            multiSort : true,
            loadFilter : function(data) {
                if (data.rows) {
                    return data;
                } else {
                    return data.content;
                }
            }
    }); 
    
    //打开品牌对话框
    $("#brandId").combobox({onShowPanel:function(){
        $('#window-brand').window('open');
        $("#brandName").val('');
        $("#brandAlais").val('');
    }}); */
    
   
}); 

//查找集团店铺等级
function findGrade(obj){
	
    if("${type}"=="1"){
        $.getJSON(biz.rootPath() + '/shopGrade/find?type=1', function(json) {
            var data="";
            data+='[{ "text": "--请选择--", "id": "" ,"selected":true  }';
            $.each(json.resultObject.content, function(i, n) {
            	if("${shopInfo.gradeId}"==n.gradeId){
            		 data+=',{ "text": "'+n.gradeName+'", "id":'+n.gradeId+',"selected":true}';
            	}else{
                	data+=',{ "text": "'+n.gradeName+'", "id":'+n.gradeId+'}';
            	}
            });
            data+="]";
            $("#"+obj).combobox("loadData", $.parseJSON(data));
        });
    }
    
}

function closeDialog(){
    $('#window-brand').window('close');
    $('#brand_table').datagrid('uncheckAll');
}

//获得品牌id
function getBrand(){
    var selectRows = $("#brand_table").datagrid('getChecked');
    if(selectRows.length==0){
        $.messager.show({title:'提示', msg:'请选择需要操作的数据项！'});
        return false;
    }
    $("#brandId").combobox('setText',selectRows[0].brandName);
    $("#brand_hidden").val(selectRows[0].brandId);
    $('#window-brand').window('close');
}

//申请新品牌
function addBrand(){
    $("#old_brand").hide();
    $("#new_brand").show();
    $("#brand_hidden").val(0);
    $("#brandName").val('');
    $("#brandAlais").val('');
}

//隐藏申请新品牌输入框
function cancelBrand(){
    $("#new_brand").hide();
    $("#old_brand").show();
    $("#brandName").val('');
    $("#brandAlais").val('');
}

//验证登录名
function validateLoginName(){
	var loginName = $('#loginName').val();
	if(loginName==null || loginName.trim()==''){
		$.messager.alert('提示', '该会员账号不存在，请输入正确的会员账号或者先申请会员！','info',function(){
			 $('#loginName').val('');
		 });
	     return false;
	}
	
	var param = {};
	param.loginName = loginName;
	param.loginNameType='3'; //类型
	commonAjax(_path + '/sign/checkAccountExist', param, function(data) {
		var row = data.content.rows;
		if (row != 0) {
			console.log(data);
			if(row[0].accountType!='' && (row[0].accountType.substring(0,2)=='11' || row[0].accountType.substring(0,2)=='12')){  //企业账户
				if(row[0].companyId>0){
					 $.messager.alert('提示', '该账户已经绑定店铺，请重新输入！','info',function(){
						 $('#loginName').val('');
					 });
				}else{
					$('#accountId').val(row[0].accountId);
				}
			}else{
				 $.messager.alert('提示', '该账户不是企业账户，请重新输入！','info',function(){
					 $('#loginName').val('');
				 });
			}
		} else {
			 $.messager.alert('提示', '该会员不存在，请重新输入！','info',function(){
				 $('#loginName').val('');
			 });
		}}, null);
	
	
}

function onSelect(date) {
    var issd = this.id == 'companyTimestart';
    var sd = issd ? date : new Date($('#companyTimestart').datebox('getValue'));
    var ed = issd ? new Date($('#companyTimeend').datebox('getValue')) : date;
    if (ed < sd) {
        $.messager.alert('错误', '结束日期小于开始日期！', 'error');
        //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
        $('#companyTimeend').datebox('setValue', '').datebox('showPanel');
    }
}

/**获得分类*/
function getSelect2Cat(url, obj, isSelect,catIds) {
	var catArr = [];
	if(catIds!=null&&catIds.trim()!=''){
		catArr = catIds.split(",");
	}
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json, function(i, n) {
			if(catArr.length>0 && catArr.indexOf(n.catId)!=-1){
				data += '{ text: "' + n.catName + '", id:' + n.catId + ',selected:true},';
			}else{
				data += '{ text: "' + n.catName + '", id:' + n.catId + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({
			data : eval(data)
		});
	});
}


//编辑状态
function initEdit(){
	var areaIds = "${shopInfo.companyAreaIds}";
	var careaIds = "${shopInfo.companyCareaIds}";
	if(areaIds!=null && areaIds.trim()!=''){
		var ids = areaIds.split("-");
		setDefaultArea(0,1,"companyArea_1",ids[0]);  //注册地址
		setDefaultArea(ids[0],2,"companyArea_2",ids[1]);
		setDefaultArea(ids[1],3,"companyArea",ids[2]);
		var cIds = careaIds.split("-");
		setDefaultArea(0,1,"companyCarea_1",cIds[0]);  //联系地址
		setDefaultArea(cIds[0],2,"companyCarea_2",cIds[1]);
		setDefaultArea(cIds[1],3,"companyCarea",cIds[2]);
	}
	
	if($('#imageId1').val()!=''){
		$('#imgImageid').attr('src',yoyo.imagesUrl+$('#imageId1').val());
	}else{
		$("#imgImageid").attr('src',biz.defaultPic());
	}
	if($('#imageCid1').val()!=''){
		$('#imgimageCid').attr('src',yoyo.imagesUrl+$('#imageCid1').val());
	}else{
		$("#imgimageCid").attr('src',biz.defaultPic());
	}
	if($('#imageCodeid1').val()!=''){
		$('#imgimageCodeid').attr('src',yoyo.imagesUrl+$('#imageCodeid1').val());
	}else{
		$("#imgimageCodeid").attr('src',biz.defaultPic());
	}
	if($('#imageTaxid1').val()!=''){
		$('#imgimageTaxid').attr('src',yoyo.imagesUrl+$('#imageTaxid1').val());
	}else{
		$("#imgimageTaxid").attr('src',biz.defaultPic());
	}
}

/**defaultVal:默认值*/
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

</head>
<body>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
	<div class="shop_reg_main">
		<div >
			<form id="shop_reg_form" >   
				<table class="shop_reg_sub_table">
				   	
					
					<tr><td><span style="font-size:16px;font-weight:bold;">申请店铺信息：</span></td></tr>
					<input type="hidden" id="accountId" name="accountId">
					<input name="companyType" value="${type}" type="hidden">  
					<tr><td align="right"><span style="color:red;">* </span>店主名:</td><td><input type="text" id="loginName" name="shopName" value="${shopInfo.shopName }" onblur="validateLoginName()" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写前台会员用户名(登录名)' <c:if test="${shopInfo!=null }">disabled</c:if>></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>店主实名:</td><td><input type="text" id="storeIdcardname" name="storeIdcardname" value="${shopInfo.storeIdcardname }" class="tab_input easyui-validatebox" data-options="required:true"  validType="special" missingMessage='此处填写店主真实姓名' <c:if test="${shopInfo!=null }">disabled</c:if>></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>身份证号:</td><td><input type="text" id="storeIdcard" name="storeIdcard" value="${shopInfo.storeIdcard }" class="tab_input easyui-validatebox" data-options="required:true" validType="idcard" missingMessage='此处填写店主身份证号码' <c:if test="${shopInfo!=null }">disabled</c:if>></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>店铺名称:</td><td><input type="text" id="storeName" name="storeName" value="${shopInfo.storeName }" class="tab_input easyui-validatebox" data-options="required:true" validType="special" missingMessage='此处填写店铺名称' <c:if test="${shopInfo!=null }">disabled</c:if>></td></tr>
					<%-- <c:if test="${type=='1' }">
						<tr><td align="right"><span style="color:red;">* </span>开店等级:</td><td><input type="text" style="width:120px;" id="gradeId" name="gradeId" class="easyui-combobox " data-options="valueField:'id',editable:false,textField:'text',panelHeight:'auto',panelWidth:'auto'" missingMessage='此处选择开店个数'>
						</td></tr>
					</c:if>  --%>
					<tr><td align="right"><span style="color:red;">* </span>经营范围:</td>
					<td><select id="good_category_ids"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator"   multiple="multiple"></select>
						  </td>
					
					</tr>
					<%-- <tr><td align="right"><span style="color:red;">* </span>选择品牌:</td>
						 <td id="old_brand"><select  id="brandId"  style="width:90px;" class="easyui-combobox " data-options="panelHeight:'0',editable:false,panelWidth:'62'" missingMessage='请选择品牌'>
						   <option>请选择</option>
						 </select>
						 <input type="hidden" name="brandId" id="brand_hidden" value='0'/>
						 <img src="${path }/resources/images/shop/new_brand.png" onclick="addBrand()" style="margin-left:15px;"></img>
						</td> 
						<td id="new_brand" style="display:none;">
						    <span>品牌名称：</span><input type="text" class="tab_input" style="width:200px;" name="brandName" id="brandName"></br>
						    <span>品牌别名：</span><input type="text" class="tab_input" style="width:200px;" name="brandAlias" id="brandAlias">
						     <img src="${path }/resources/images/shop/cancel_brand.png" onclick="cancelBrand()" style="margin-left:15px;"></img>
						</td>
					</tr> --%>
					 <tr><td align="right"><span style="color:red;">* </span>手机号码:</td><td><input type="text" id="tel" name="tel" value="${shopInfo.tel }" class="tab_input easyui-validatebox" data-options="required:true" validType="mobile" missingMessage='此处填写手机号码' <c:if test="${shopInfo!=null }">disabled</c:if>></td></tr>
					
					<tr><td><span style="font-size:16px;font-weight:bold;">支付账户信息：</span></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>银行名称:</td><td><input type="text" id="bankName" name="bankName" value="${shopInfo.bankName }" class="tab_input easyui-validatebox" data-options="required:true" validType="special" missingMessage='此处填银行名称'></td></tr>
					 <tr><td align="right"><span style="color:red;">* </span>银行卡号:</td><td><input type="text" id="bankCardid" name="bankCardid" value="${shopInfo.bankCardid }" class="tab_input easyui-validatebox" data-options="required:true" validType="bankId" missingMessage='此处填银行卡号'></td></tr> 
				 <!-- 	<tr><td></td><td><input style="width:20px;" type="checkbox" name="checkbox" id="agree_checkbox" />我已认真阅读并完全同意 <span style="color:red;">《商户服务协议》</span>与 <span style="color:red;">《支付协议》</span> 中的所有条款</td></tr> -->
				 	 
				 	 <tr><td><span style="font-size:16px;font-weight:bold;">申请公司信息：</span></td></tr>
				 	  <c:if test="${type=='0' }">
						<tr><td align="right"><span style="color:red;">* </span>开店级别:</td><td>
						    <select name="issueType" >
						    	<option value="0" selected="selected">单店</option>
						    	<option value="1">集团</option>
						    </select>
						</td></tr>
					</c:if> 
					<tr><td align="right"><span style="color:red;">* </span>企业名称:</td><td><input type="text" id="companyName" name="companyName" value="${shopInfo.companyName }" class="tab_input easyui-validatebox" data-options="required:true" validType="special"  missingMessage='此处填写企业名称' <c:if test="${shopInfo!=null }">disabled</c:if>></td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>营业执照号:</td><td><input type="text" id="companyNo"  name="companyNo" value="${shopInfo.companyNo }" class="tab_input  easyui-validatebox" data-options="required:true" validType="license" missingMessage='此处填写营业执照号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>税务登记证号:</td><td><input type="text" id="companyTaxno" name="companyTaxno" value="${shopInfo.companyTaxno }" class="tab_input  easyui-validatebox"data-options="required:true" validType="license" missingMessage='此处填写税务登记证号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>企业组织机构代码:</td><td><input type="text" id="companyCodename" name="companyCodename" value="${shopInfo.companyCodename }" class="tab_input easyui-validatebox" data-options="required:true" validType="organizationCode" missingMessage='此处填写企业组织机构代码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>法定代表人:</td><td><input type="text" id="companyIdname" name="companyIdname" value="${shopInfo.companyIdname }" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写法人身份证姓名'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>法人身份证号:</td><td><input type="text" id="companyIdcard" name="companyIdcard" value="${shopInfo.companyIdcard }" class="tab_input easyui-validatebox" data-options="required:true" validType="idcard"  missingMessage='此处填写法人身份证号码'></td></tr>
					 <tr><td align="right"><span style="color:red;">* </span>公司负责人:</td><td><input type="text" id="companyCname" name="companyCname" value="${shopInfo.companyCname }" class="tab_input easyui-validatebox" data-options="required:true" validType="special" missingMessage=此处填写公司负责人或申请人身份证姓名''></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>负责人身份证号:</td><td><input type="text" id="companyCidcard" name="companyCidcard"value="${shopInfo.companyCidcard }"  class="tab_input easyui-validatebox" data-options="required:true" validType="idcard" missingMessage='此处填写公司负责人或申请人身份证号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>负责人职务:</td><td><input type="text" id="companyCharge" name="companyCharge" value="${shopInfo.companyCharge }" class="tab_input easyui-validatebox" data-options="required:true" validType="special" missingMessage='此处填写公司负责人或申请人职务'></td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>企业手机电话:</td><td><input type="text" id="companyPhone" name="companyPhone" value="${shopInfo.companyPhone }" class="tab_input easyui-validatebox" data-options="required:true" validType="mobile" missingMessage='此处填写公司联系手机号码'></td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>企业联系电话:</td><td><input type="text" id="companyCtel" name="companyCtel" value="${shopInfo.companyCtel }" class="tab_input easyui-validatebox" data-options="required:true" validType="phone" missingMessage='此处填写公司联系电话，例：021-87654321'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司注册地址:</td><td><select  style="width:120px;" id="companyArea_1" name="companyArea_1"  class="easyui-combobox " data-options="valueField:'id',textField:'text',editable:false,required:true, panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'></select>
						<input type="text" style="width:120px;" id="companyArea_2" name="companyArea_2" class="easyui-combobox "  data-options="valueField:'id',textField:'text',required:true, editable:false,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
						<input type="text" style="width:120px;" id="companyArea" name="companyArea" class="easyui-combobox" data-options="valueField:'id',textField:'text', editable:false,panelHeight:'auto',panelWidth:'120'"  missingMessage='请选择省市地区'>
					
					</td></tr> 
					<tr><td></td><td><input type="text" id="companyAddr" name="companyAddr" class="tab_input easyui-validatebox" value="${shopInfo.companyAddr }"  data-options="required:true" validType="special" missingMessage='请填写详细地址'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司注册资金:</td><td><input type="text" id="companyEarnest" name="companyEarnest"  value="${shopInfo.companyEarnest }" class="tab_input easyui-numberbox easyui-validatebox" style="width:90px" data-options="required:true,min:0,max:999999999,precision:2" missingMessage='此处填写公司注册资金，最小值0，最大值：9999999 单位：万元'>万元</td></tr>
					
					<tr><td align="right"><span style="color:red;">* </span>公司联系地址:</td><td><input type="text" style="width:120px;" id="companyCarea_1" name="companyCarea_1" class="easyui-combobox " data-options="valueField:'id',editable:false,textField:'text',required:true,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
					<input type="text" style="width:120px;" id="companyCarea_2" name="companyCarea_2" class="easyui-combobox " data-options="valueField:'id',textField:'text',required:true,editable:false,panelHeight:'auto',panelWidth:'120'"  missingMessage='请选择省市地区'>
					<input type="text" style="width:120px;" id="companyCarea" name="companyCarea" class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
				 	
					</td></tr> 
					<tr><td></td><td><input type="text" id="companyCaddr" name="companyCaddr"  value="${shopInfo.companyCaddr }" class="tab_input " data-options="required:true" validType="special" missingMessage='请填写详细地址'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司成立时间:</td><td><input type="text" style="width:120px;" id="company_time" name="companyTime" value="${shopInfo.companyTime }" class="tab_input  easyui-datebox" data-options="editable:false,required:true" missingMessage='此处填写公司成立日期'></td></tr>
					 <tr><td align="right">公司官网地址:</td><td><input type="text" id="companyUrl" name="companyUrl" value="${shopInfo.companyUrl }" class="tab_input " data-options="required:true" validType="url"></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>营业执照有效期:</td><td><input type="text" id="companyTimestart"  value="${shopInfo.companyTimestart }" style="width:120px;" name="companyTimestart" class="tab_input easyui-datebox " data-options="editable:false,required:true,onSelect:onSelect"/> ~ <input type="text" style="width:120px;" id="companyTimeend" value="${shopInfo.companyTimeend }" name="companyTimeend" class="easyui-datebox" data-options="editable:false,required:true,onSelect:onSelect" missingMessage='此处填写营业执照有效起止日期'> </td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>营业执照经营范围:</td><td><textarea cols="50" rows="6" id="companyRemark"   name="companyRemark" class="tab_input easyui-validatebox" data-options="editable:false,required:true" validType="special" missingMessage='此处填写营业执照上经营范围'>${shopInfo.companyRemark }</textarea></td></tr>
				</table>
				<input type="hidden" name="imageId" id="imageId1" value="${shopInfo.imageId }"/>
				<input type="hidden" name="imageCid" id="imageCid1" value="${shopInfo.imageCid }"/>
				<input type="hidden" name="imageCodeid" id="imageCodeid1" value="${shopInfo.imageCodeid }"/>
				<input type="hidden" name="imageTaxid" id="imageTaxid1" value="${shopInfo.imageTaxid }"/>
				<input type="hidden" name="companyId" id="companyId" value="${shopInfo.companyId}"/>
			</form>	
			<!-- 图片 -->
			<div style="margin-left:100px; ">
				
				<div class="shop_reg_info_title"><span style="font-size:18px;font-weight:bold;">公司图片资料：</span><span style="margin-left:700px;">图片大小不能超过2M</span></div>
				<hr style="width:1000px; margin-left: 0px;"/>
				<div class="fig">
					<h4>身份证</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传身份证照:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageId" class="fileInput" onchange="previewImage(this,'previewImageId','imgimageId',260,150)" />
						
						 </div>
						 <input type="button" onclick="submitForm('imageId','imageId1','imgImageid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewImageId" class="register_web">
							<img id="imgImageid"  width="260" height="150">
						</div>
					</form>
				</div>
				
				<div class="fig">
					<h4>执照</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传执照:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageCid" class="fileInput" onchange="previewImage(this,'previewImageCid','imgimageCid',260,150)" />
						 </div>
						 <input type="button" onclick="submitForm('imageCid','imageCid1','imgimageCid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewImageCid" class="register_web">
							<img id="imgimageCid"  width="260" height="150">
						</div>
					</form>
				</div>
				
				
				<div class="fig">
					<h4>组织机构代码</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传组织机构代码:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageCodeid" class="fileInput" onchange="previewImage(this,'previewCodeid','imgimageCodeid',260,150)" />
						 </div>
						 <input type="button" onclick="submitForm('imageCodeid','imageCodeid1','imgimageCodeid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewCodeid" class="register_web">
							<img id="imgimageCodeid" width="260" height="150">
						</div>
					</form>
				</div>
				
				
				<div class="fig">
					<h4>税务登记证</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传税务登记证:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageTaxid" class="fileInput" onchange="previewImage(this,'previewTaxid','imgimageTaxid',260,150)" />
						 </div>
						 <input type="button" onclick="submitForm('imageTaxid','imageTaxid1','imgimageTaxid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewTaxid" class="register_web">
							<img id="imgimageTaxid"  width="260" height="150">
						</div>
					</form>
				</div>
				
				<div class="clear">
				</div>
		   </div>
		</div>
			
		<div class="shop_reg_next"><a class="easyui-linkbutton" icon="icon-save"   id="shop_reg_sub_next" >保存</a></div>
	</div>
	
	
	<!-- <div id="window-brand" class="easyui-window" title="选择品牌"
        closed="true" style="height: 700px; width: 800px;"
        data-options="cache:false,shadow:false,left:'400px',top:'100px'">
        <table id="brand_table"></table>
        <div class="brand_btn"><a onclick="javascript:getBrand()" class="easyui-linkbutton" style="margin-right:50px;">确定</a><a id="brand_cancel"  onclick="javascript:closeDialog()" class="easyui-linkbutton" >取消</a></div>
     </div> -->
     
</body>
</html>