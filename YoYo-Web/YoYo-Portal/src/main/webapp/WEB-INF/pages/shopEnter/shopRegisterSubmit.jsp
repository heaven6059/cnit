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
<title>YOYO汽车商城-商家填写资料</title>
<link type="text/css" rel="stylesheet" href="${path}/resources/styles/shopEnter/shopRegister.css">
<script type="text/javascript"  src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css"  href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<link type="text/css" href="${path}/resources/scripts/easyui/themes/bootstrap/easyui.css" rel="stylesheet" />


<script type="text/javascript">
$(function(){
	//经营范围对话框的实现
	initTree('good_category_ids', true);
  /*   $('#good_category_ids').trapown(
			{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
				textField : 'catName' , parentField : 'parentCatId' , initParentId : 0 });
    //查找店铺等级
    findGrade("gradeId"); */
    
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
       if($("#agree_checkbox").is(":checked")==false){
            $.messager.alert('提示', '请勾选条款', 'warn');
            return false;
       } 
      /*  if($("#brand_hidden").val()=='0' && $("#brandName").val()==''){
            $.messager.alert('提示', '请选择品牌', 'warn');
            return false;
       }  
       if("${type}"=="1"){
	       if($("#gradeId").combobox('getValue')==null || $("#gradeId").combobox('getValue')==''){
	           $.messager.alert('提示', '请选择开店等级', 'warn');
	           return false;
	      } 
       }*/
     
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
       reg_data.approved='-1';  //信息还未提交完成
       parent.$.messager.progress({
            title : '提示',
            text : '数据处理中，请稍后....'
       });
        
      console.log(JSON.stringify(reg_data));
       $.ajax({
            url : biz.rootPath() + "/shopEnter/shopRegisterSave",
            data : reg_data, 
            type : "post",
            datatype : "json",
            success : function(data) {console.log(data);
                parent.$.messager.progress('close');
                if (data.head.retCode=='000000') {
                    window.location.href=biz.rootPath() +"/shopEnter/shopRegisterImg?companyId="+data.content;
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
            pageSize : 50,
            pageList : [ 50, 100, 150, 500 ],
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
    }});
     */
   
}); 

$(document).keydown(function(e) {
	// enter
	if (e.keyCode == 13) {
		$("#shop_reg_sub_next").click();
	}
});

//查找集团店铺等级
function findGrade(obj){
    if("${type}"=="1"){
        $.getJSON(biz.rootPath() + '/shopGradeController/find?type=1', function(json) {
            var data="";
            data+='[{ "text": "--请选择--", "id": "" ,"selected":true  }';
            $.each(json.resultObject.content, function(i, n) {
                data+=',{ "text": "'+n.gradeName+'", "id":'+n.gradeId+'}';
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


/**
 * 方法描述：初始化经营范围下拉树
 * 
 * @param obj
 * @param multiple
 */
function initTree(obj, multiple, initParams) {
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
		}
	});
}
</script>

</head>
<body>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
	<div class="shop_reg_main">
		<div class="shop_reg_center"><img src="${path}/resources/images/shop/shop_reg_data.png" />	</div>
		<div >
			<form id="shop_reg_form" > 
				<input name="companyType" value="${type}" type="hidden">  
				<table class="shop_reg_sub_table">
				   	<tr><td><span style="font-size:16px;font-weight:bold;">申请公司信息：</span></td></tr>
				   	<c:if test="${type=='0' }">
						<tr><td align="right"><span style="color:red;">* </span>开店级别:</td><td>
						    <select name="issueType" >
						    	<option value="0" selected="selected">单店</option>
						    	<option value="1">集团</option>
						    </select>
						</td></tr>
					</c:if> 
					<tr><td align="right"><span style="color:red;">* </span>企业名称:</td><td><input type="text" id="companyName" validType="special" name="companyName" class="tab_input easyui-validatebox" data-options="required:true"  missingMessage='此处填写企业名称'></td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>营业执照号:</td><td><input type="text" id="companyNo"  name="companyNo" class="tab_input  easyui-validatebox" data-options="required:true" validType="license" missingMessage='此处填写营业执照号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>税务登记证号:</td><td><input type="text" id="companyTaxno" name="companyTaxno" class="tab_input  easyui-validatebox"data-options="required:true" validType="license" missingMessage='此处填写税务登记证号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>企业组织机构代码:</td><td><input type="text" id="companyCodename" name="companyCodename" class="tab_input easyui-validatebox" data-options="required:true" validType="organizationCode" missingMessage='此处填写企业组织机构代码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>法定代表人:</td><td><input type="text" id="companyIdname" validType="special" name="companyIdname"  class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写法人身份证姓名'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>法人身份证号:</td><td><input type="text" id="companyIdcard" name="companyIdcard" class="tab_input easyui-validatebox" data-options="required:true" validType="idcard"  missingMessage='此处填写法人身份证号码'></td></tr>
					 <tr><td align="right"><span style="color:red;">* </span>公司负责人:</td><td><input type="text" id="companyCname" validType="special" name="companyCname" class="tab_input easyui-validatebox" data-options="required:true"  missingMessage=此处填写公司负责人或申请人身份证姓名''></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>负责人身份证号:</td><td><input type="text" id="companyCidcard" name="companyCidcard" class="tab_input easyui-validatebox" data-options="required:true" validType="idcard" missingMessage='此处填写公司负责人或申请人身份证号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>负责人职务:</td><td><input type="text" id="companyCharge" validType="special" name="companyCharge" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写公司负责人或申请人职务'></td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>企业联系电话:</td><td><input type="text" id="companyCtel" name="companyCtel" class="tab_input easyui-validatebox" data-options="required:true" validType="phone" missingMessage='此处填写公司联系电话，例：021-87654321'></td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>企业手机电话:</td><td><input type="text" id="companyPhone" name="companyPhone" class="tab_input easyui-validatebox" data-options="required:true" validType="mobile" missingMessage='此处填写公司联系手机号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司注册地址:</td><td><select  style="width:120px;" id="companyArea_1" name="companyArea_1" class="easyui-combobox " data-options="valueField:'id',textField:'text',editable:false,required:true, panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'></select>
						<input type="text" style="width:120px;" id="companyArea_2" name="companyArea_2" class="easyui-combobox "  data-options="valueField:'id',textField:'text',required:true, editable:false,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
						<input type="text" style="width:120px;" id="companyArea" name="companyArea" class="easyui-combobox" data-options="valueField:'id',textField:'text', editable:false,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
					
					</td></tr> 
					<tr><td></td><td><input type="text" id="companyAddr" name="companyAddr" class="tab_input easyui-validatebox" validType="special" data-options="required:true" missingMessage='请填写详细地址'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司注册资金:</td><td><input type="text" id="companyEarnest" name="companyEarnest" class="tab_input easyui-numberbox easyui-validatebox" style="width:90px" data-options="required:true,min:0,max:999999999,precision:2" missingMessage='此处填写公司注册资金，最小值：0，最大值：9999999 单位：万元'>万元</td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司联系地址:</td><td><input type="text" style="width:120px;" id="companyCarea_1" name="companyCarea_1" class="easyui-combobox " data-options="valueField:'id',editable:false,textField:'text',required:true,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
					<input type="text" style="width:120px;" id="companyCarea_2" name="companyCarea_2" class="easyui-combobox " data-options="valueField:'id',textField:'text',required:true,editable:false,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
					<input type="text" style="width:120px;" id="companyCarea" name="companyCarea" class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'>
				 	
					</td></tr> 
					<tr><td></td><td><input type="text" id="companyCaddr" name="companyCaddr" class="tab_input " data-options="required:true" validType="special" missingMessage='请填写详细地址'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>公司成立时间:</td><td><input type="text" style="width:120px;" id="company_time" name="companyTime" class="tab_input  easyui-datebox" data-options="editable:false,required:true" missingMessage='此处填写公司成立日期'></td></tr>
					 <tr><td align="right">公司官网地址:</td><td><input type="text" id="companyUrl" name="companyUrl" class="tab_input " data-options="required:true" validType="url"></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>营业执照有效期:</td><td><input type="text" id="companyTimestart" style="width:120px;" name="companyTimestart" class="tab_input easyui-datebox " data-options="editable:false,required:true,onSelect:onSelect"/> ~ <input type="text" style="width:120px;" id="companyTimeend" name="companyTimeend" class="easyui-datebox" data-options="editable:false,required:true,onSelect:onSelect" missingMessage='此处填写营业执照有效起止日期'> </td></tr>
				 	<tr><td align="right"><span style="color:red;">* </span>营业执照经营范围:</td><td><textarea cols="50" rows="6" id="companyRemark" validType="special" name="companyRemark" class="tab_input easyui-validatebox" data-options="editable:false,required:true" missingMessage='此处填写营业执照上经营范围'></textarea></td></tr>
					
					<tr><td><span style="font-size:16px;font-weight:bold;">申请店铺信息：</span></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>店主姓名:</td><td><input type="text" id="storeIdcardname" name="storeIdcardname" validType="special" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写店主真实姓名'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>身份证号:</td><td><input type="text" id="storeIdcard" name="storeIdcard" class="tab_input easyui-validatebox" data-options="required:true" validType="idcard" missingMessage='此处填写店主身份证号码'></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>店铺名称:</td><td><input type="text" id="storeName" name="storeName" validType="special" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写店铺名称'></td></tr>
					<%-- <c:if test="${type=='1' }">
						<tr><td align="right"><span style="color:red;">* </span>开店等级:</td><td><input type="text" style="width:120px;" id="gradeId" name="gradeId" class="easyui-combobox " data-options="valueField:'id',editable:false,textField:'text',panelHeight:'auto',panelWidth:'auto'" missingMessage='此处选择开店个数'>
						</td></tr>
					</c:if> --%> 
					<tr><td align="right"><span style="color:red;">* </span>经营范围:</td>
					<td><select id="good_category_ids"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" multiple="multiple"></select>
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
					 <tr><td align="right"><span style="color:red;">* </span>手机号码:</td><td><input type="text" id="tel" name="tel" class="tab_input easyui-validatebox" data-options="required:true" validType="mobile" missingMessage='此处填写手机号码'></td></tr>
					
					<tr><td><span style="font-size:16px;font-weight:bold;">支付账户信息：</span></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>银行名称:</td><td><input type="text" id="bankName" name="bankName" validType="special" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填银行名称'></td></tr>
					 <tr><td align="right"><span style="color:red;">* </span>银行卡号:</td><td><input type="text" id="bankCardid" name="bankCardid" class="tab_input easyui-validatebox" data-options="required:true" validType="bankId" missingMessage='此处填银行卡号'></td></tr> 
				 	<tr><td></td><td><input style="width:20px;" type="checkbox" name="checkbox" id="agree_checkbox" />我已认真阅读并完全同意 <span style="color:red;">《商户服务协议》</span>与 <span style="color:red;">《支付协议》</span> 中的所有条款</td></tr>
				 	 
				</table>
			</form>	
		</div>
		
		<div class="shop_reg_next"><a href="${path}/shopEnter/shopRegisterData" class="easyui-linkbutton" style="margin-right:50px;">上一步</a><a id="shop_reg_sub_next" href="#" class="easyui-linkbutton" >下一步</a></div>
	</div>
	
	
	<div id="window-brand" class="easyui-window" title="选择品牌"
        closed="true" style="height: 700px; width: 800px;"
        data-options="cache:false,shadow:false,left:'400px',top:'1000px'">
        <table id="brand_table"></table>
        <div class="brand_btn"><a onclick="javascript:getBrand()" class="easyui-linkbutton" style="margin-right:50px;">确定</a><a id="brand_cancel"  onclick="javascript:closeDialog()" class="easyui-linkbutton" >取消</a></div>
     </div>
     
</body>
</html>