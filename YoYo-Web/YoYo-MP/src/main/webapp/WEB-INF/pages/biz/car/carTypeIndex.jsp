<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>车型列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
	<link type="text/css"	href="${path}/resources/styles/good/brandIndex.css" rel="stylesheet" />
	<link type="text/css"    href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
	<script type="text/javascript"  src="${path}/resources/scripts/select2/select2.min.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/car/carTypeIndex.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript"  src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
    
	<div id="toolbar-car_type">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openSaveDialog()"> 添加</a> 
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteCarType()"> 删除 </a> 
		<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_factory')">高级筛选</a>
	</div>
	<table id="table-car-type"></table>


	<!-- 新增车型-->
	<div id="window-add-car-type" class="easyui-window" title="车型列表"
		closed="true" style="height: 550px; width: 800px;"
		data-options="inline:true,top:50,minimizable:false,cache:false,onBeforeClose:clearDetail,modal:true">

		<form id="form-car-type-add" class="easyui-form">
			<input type="hidden" name="carId" id="carId"/>
			<table class="add_brand_table">
				<tr>
					<td align="right"><span style="color: red;">*</span>品牌：</td>
					<td><select id="brandId" name="brandId" style="width: 260px;"></select></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>汽车品牌：</td>
					<td><select id="factoryId" name="factoryId" style="width: 260px;"></select></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>汽车级别：</td>
					<td>
						<!-- <label><input type="radio" name="gradename" value="微型车"/>微型车</label>
						<label><input type="radio" name="gradename" value="小型车"/>小型车</label>
						<label><input type="radio" name="gradename" value="豪华车"/>豪华车</label>
						<label><input type="radio" name="gradename" value="SUV"/>SUV</label> -->
						<select id="carGradeId" style="width: 260px;" name="gradeId"></select>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>汽车车系：</td>
					<td><select id="carDeptId" name="carDeptId" style="width: 260px;"></select></td>
					<!-- <td><input type='text' id="carDeptName" name="carDeptName" class="tab_input  easyui-validatebox" data-options="required:true" /></td> -->
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>汽车年款：</td>
					<td><select id="carYearValue" name="carYearId" style="width: 260px;"></select></td>
					<!-- <td><input type='text' id="carYearValue" name="carYearValue" class="tab_input  easyui-validatebox" data-options="required:true" /></td> -->
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>车型名称：</td>
					<td><input type='text' id="carName" style="width: 260px;" name="carName" class="tab_input  easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">状态：</td>
					<td><label><input type="radio" name="status" id="status1" value="1" />在产</label>
					<label><input type="radio" name="status" id="status2" value="2"/>停产</label></td>
					<input type="hidden" id="keyword" style="width: 260px;" name="keyword" class="tab_input  easyui-validatebox"  />
				</tr>
				<!-- <tr>
					<td align="right">搜索关键字：</td>
					<td><input type="hidden" id="keyword" style="width: 260px;" name="keyword" class="tab_input  easyui-validatebox"  /></td>
				</tr> -->
				<tr>
					<td align="right"><span style="color: red;">*</span>官方指导价：</td>
					<td><input type='text' id="price" style="width: 260px;" name="price" class="tab_input  easyui-validatebox" data-options="required:true" />万</td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>变速箱：</td>
					<td><label><input type="radio" name="cargearbox" id="cargearbox1" value="手动" />手动</label>
					<label><input type="radio" name="cargearbox" id="cargearbox2" value="自动"/>自动</label></td>
				</tr>
				
				<tr>
                    <td align="right"><span style="color: red;">*</span>车型图片：</td>
                    <td><div>
                            <form method="post" enctype="multipart/form-data">
                                <div class="upload_btn">
                                    <div class="fileInputContainer">
                                        <input type="file" name="imageFile" id="iconFile"
                                            class="fileInput"
                                            onchange="previewImage(this,'preIconFile','imgIconFile',260,150)" />

                                    </div>
                                    <input type="button" onclick="submitForm('iconFile','iconFile1','imgIconFile')"
                                        class="upLoad" value="上传" />
                                </div>
                                <div id="preIconFile" class="shop_logo">
                                    <img id="imgIconFile" width="260" height="150">
                                </div>
                            </form>
                        </div> <input type="hidden" name="iconFile" id="iconFile1" ></td>
                </tr>
			</table>
		</form>
		
		<!-- <div region="center" border="false" style="text-align: center; height: 150px; line-height: 30px; margin-bottom: 10px;">
			<div id="toolbar-spec-values">
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="newSpecValue()"> 生成</a>
				自动生成勾选的组合车型列表，车型名称=年款+车系+变速箱+汽车级别，也可自己更改
			</div>
			<table id="table-spec-values-add" class="easyui-datagrid" style="width:500px;height:100px;display:none;"
					data-options="collapsible:true,onDblClickCell:onDblClickCell,singleSelect:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'cartype',width:300,align:'center',editor:{type:'text',options:{required:true}}">车型</th>
						<th data-options="field:'productid',width:150,align:'center',formatter:specValuesEditor">操作</th>
					</tr>
				</thead>
			</table>
		</div> -->

		<div  border="false" style="text-align: center; height: 10px; line-height: 10px; margin-bottom: 5px;">
			<a class="easyui-linkbutton" id="add-car-type-btn" icon="icon-save" onclick="saveCarType()">保存</a> 
			<a class="easyui-linkbutton" id="update-car-type-btn" icon="icon-save" onclick="updateCarType()" style="display: none;">保存</a> 
			<a class="easyui-linkbutton" id="close-car_type-btn" icon="icon-cancel" onclick="javascript:closeDailog();" style="margin-left: 15px;">取消</a>
		</div>
	</div>



    <!-- 高级查询 -->
	<div id="advance_search_car_factory" class="easyui-dialog advance_search" title="高级筛选"
		style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
		data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				
				<tr>
					<td align="right"><span>品牌: </span></td>
					<td><select id="searchBrandId" name="brandId" style="width: 160px;" class="search_class">
                    </select></td>
				</tr>
				<tr>
                    <td align="right">汽车品牌：</td>
                    <td><select id="searchFactoryId" name="factoryId" style="width: 160px;" class="search_class">
                    </select></td>
                </tr>
                <tr>
					<td align="right"><span>汽车级别: </span></td>
					<td><select id="searchCarGradeId" name="gradeId" style="width: 160px;" class="search_class">
                    </select></td>
				</tr>
				<tr>
					<td align="right"><span>汽车车系: </span></td>
					<td><select id="searchCarDeptId" name="carDeptId" style="width: 160px;" class="search_class">
                    </select></td>
				</tr>
				<tr>
					<td align="right"><span>年款: </span></td>
					<td><select id="searchCarYearValue" name="carYearId" style="width: 160px;" class="search_class">
                    </select></td>
				</tr>
				<tr>
					<td align="right"><span>车型名称: </span></td>
					<td><input id="searchCarName" name="carName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>搜索关键字: </span></td>
					<td><input id="searchKeyword" name="keyword"
						class="easyui-textbox search_class" /></td>
				</tr>
			</table>
		</form>

	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok"
			onclick="advanceSearch()"
			style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="advance_clear()" style="width: 90px">清除条件</a>
	</div>
	
	
	<!-- 关联保养类别 -->
	<!-- <div id="window-factory-category" class="easyui-window" title="关联保养类别"
        closed="true" style="height: 450px; width: 600px;padding:10px;"
        data-options="cache:false">
        <input type="hidden" id="selectId">
        <table  style="border-collapse: separate; border-spacing: 5px;">
            <tr > 
                  <td align="right">保养类别：</td>
                  <td>
                  <select id="factory_select_parent" style="width:160px;" >
                   </select>
                    <select id="factory_select_child" style="width:160px;" >
                    </select>
                  </td>
              </tr>
              <tr id="good_category_tr" >
                  <td align="right">选择的分类：</td>
                  <td> <select id="factory_category_ids" name="goodCategory" style="width:360px;" multiple="multiple">
                  
                    </select></td>
              </tr>
        </table>
        <div style="text-align: center;margin-top:10px;">
         <a class="easyui-linkbutton" id="update-car-factory-btn" icon="icon-save" onclick="updatefactoryCategory()" >保存</a>
         <a class="easyui-linkbutton" id="update-car_factory-btn" icon="icon-cancel" onclick="javascript:closeCategory();" style="margin-left: 15px;">取消</a>
         </div>
    </div> -->
    
    
    <!-- 车型详情 -->
    <div id="window-view-car-type" class="easyui-window" title="车型详情 "
        closed="true" style="height: 700px; width: 800px;"
        data-options="cache:false,shadow:false,minimizable:false,left:'400px'">
        <table id="car-type-view-table" class="easyui-datagrid" 
					data-options="collapsible:true,singleSelect:true">
				<thead>
					<tr>
						<th data-options="field:'carName',align:'center'">车型名称</th>
						<!-- <th data-options="field:'keyword',align:'center'">关键字</th> -->
						<th data-options="field:'viewCount',align:'center'">浏览数</th>
						<th data-options="field:'status',align:'center'">状态</th>
						<th data-options="field:'price',align:'center'">官方指导价</th>
						<!-- <th data-options="field:'iconFile',align:'center'">车型图片</th> -->
						<th data-options="field:'displayName',align:'center'">数据名称</th>
						<th data-options="field:'displayValue',align:'center'">数据值</th>
					</tr>
				</thead>
			</table>
        <div class="brand_btn">
        <a id="brand_cancel"  onclick="javascript:closeDetailDialog()" class="easyui-linkbutton" >取消</a></div>
     </div>
</body>
</html>
