<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>汽车品牌</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript"  src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css"    href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/car/carFactoryIndex.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js?v=${versionVal}"></script>
    <script type="text/javascript"  src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <div id="toolbar-car_factory">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteCarfactory()"> 删除 </a> <a href="#"
            style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openSearch()">高级筛选</a>
    </div>
    <table id="table-car-factory"></table>


    <!-- 新增汽车厂商-->
    <div id="window-add-car-factory" class="easyui-window" title="添加汽车品牌"
        closed="true" style="height: 550px; width: 800px;"
        data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

        <form id="form-car-factory-add" class="easyui-form">
            <input type="hidden" name="factoryId" id="factoryId">
            <table class="add_brand_table">
                <tr>
                    <td align="right"><span style="color: red;">*</span>汽车品牌：</td>
                    <td>
                    	<select id="brandId" style="width: 160px;" name="brandId">
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td align="right">汽车品牌名称：</td>
                    <td><input type='text' name="factoryName" id="factoryName"
                        class="tab_input easyui-validatebox" data-options="required:true" validType="special"/></td>
                </tr>
                <tr>
                    <td align="right">品牌区域：</td>
                    <td>
                    	<select id="scopeId" style="width: 160px;" name="scopeId">
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td align="right">汽车品牌网址：</td>
                    <td><input type='text' name="linkUrl"  class="tab_input easyui-validatebox"  id="linkUrl" data-options="required:true" />
                   </td>
                </tr>
                <tr>
                    <td align="right">汽车品牌拼音：</td>
                    <td><input type='text' name="pinyin"     class="tab_input easyui-validatebox" data-options="required:true"  validType="special"/> </td>
                </tr>
                <tr>
                    <td align="right">进口国产类型：</td>
                    <td><label><input type="radio" name="carBrandBrandtobrand" value='1'/>国产</label>
                    <label><input type="radio" name="carBrandBrandtobrand" value='2'/>进口</label></td>
                </tr>
                <tr>
                    <td align="right">汽车品牌LOGO：</td>
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
                        </div> <input type="hidden" name="iconFile" id="iconFile1"></td>
                </tr>

            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-car-factory-btn" icon="icon-save" onclick="saveCarfactory()">保存</a>
                <a class="easyui-linkbutton" id="update-car_factory-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-car-factory');" style="margin-left: 15px;">取消</a>
        </div>
    </div>



    <!-- 高级查询 -->
    <div id="advance_search_car_factory" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
        <form id="car_factory_search_form" class="easyui-form" method="post"
            data-options="novalidate:true">
            <table style="border-collapse: separate; border-spacing: 5px;">
                
                <tr>
                    <td align="right"><span>汽车品牌名称: </span></td>
                    <td><input  name="factoryName"
                        class="easyui-textbox search_class" /></td>
                </tr>
                <tr>
                    <td align="right"><span>汽车品牌网址: </span></td>
                    <td><input  name="linkUrl"
                        class="easyui-textbox search_class" /></td>
                </tr>
                <tr>
                    <td align="right"><span>汽车品牌区域: </span></td>
                    <td>
                    <select id="query_carType" name='scopeId' style="width: 160px;" >
                    </select>
                    </td>
                </tr>
                <tr>
                    <td align="right"><span>品牌: </span></td>
                    <td><select id="query_brand" name="brandId" style="width: 160px;" >
                    </select></td>
                </tr>
                
                <tr>
                    <td align="right"><span>汽车品牌拼音: </span></td>
                    <td><input  name="pinyin"
                        class="easyui-textbox search_class" /></td>
                </tr>
                <tr>
                    <td align="right"><span>进口国产类型: </span></td>
                    <td>
                    <select id="carBrandBrandtobrand" name="carBrandBrandtobrand" style="width: 160px;" class="easyui-combobox">
                      <option value="-1" selected="selected">请选择</option>
                      <option value="1">国产</option>
                      <option value="2">进口</option>
                    </select></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok"
            onclick="CarFacAdvanceSearch()"
            style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
            class="easyui-linkbutton" iconCls="icon-cancel"
            onclick="advance_clear()" style="width: 90px">清除条件</a>
    </div>
    
</body>
</html>
