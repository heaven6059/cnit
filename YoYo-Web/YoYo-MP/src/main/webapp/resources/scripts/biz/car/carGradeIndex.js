/**
 * 功能描述： 汽车车系年款的js
 * 
 */
$(function() {
	$('#car-grade-table').datagrid({
		url : _path + '/carBrandGrade/carBrandGradeList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'gradeName',
			align : 'center',
			halign: 'center',
			title : '级别名称',
			sortable: true
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a href='javascript:editGrade("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-car-grade',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		//singleSelect : true,
		selectOnCheck:false,
		checkOnSelect:false,
		remoteSort : false,
		singleSelect : true,
		multiSort : true,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});

});


//打开添加级别的对话框
function openAddGradeDialog(){
	$('#add_grade_form').form('clear');
	$('#window-add-grade').panel({title: "添加级别"});
	$("#window-add-grade").window('open');
}

//保存级别
function saveGrade(){
	if ($('#add_grade_form').form('validate')) {
		var param_data = biz.serializeObject($("#add_grade_form"));
		if($("#gradeid").val()!=''){ //更新
			$.post(_path + '/carBrandGrade/updateCarBrandGrade', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#car-grade-table').datagrid('reload', {});
					$('#window-add-grade').window('close');
				}else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该级别名称已经存在！");
				}else {
					easyuiMsg('错误', "更新失败！");
				}
			}, 'json');
		} else { //添加
			$.post(_path + '/carBrandGrade/insertCarBrandGrade', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#car-grade-table').datagrid('reload', {});
					$('#window-add-grade').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该级别名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
	}
}

//删除级别
function deleteGrade(){
	var selectRows = $("#car-grade-table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.gradeid;
	});
	var params={};
	params.ids=ids;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carBrandGrade/deleteCarBrandGrade',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#car-grade-table').datagrid('reload', {});
			    		}else if (_data.head.retCode == 'PDE001'){
			    			easyuiMsg('错误', "页面输入数据错误！");
			    		}else if (_data.head.retCode == '000007'){
			    			easyuiMsg('错误', "存在级联数据！");
			    			$('#car-grade-table').datagrid('reload', {});
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}

//编辑级别
function editGrade(row){
	$('#add_grade_form').form('clear');
	$('#add_grade_form').form('load', row);
	$('#gradeid').val(row.gradeid);
	$('#window-add-grade').panel({title: "编辑级别"});
	$('#window-add-grade').window('open');
}

/*$.extend($.fn.validatebox.defaults.rules, {
    CHS: {
      validator: function (value, param) {
        return /^[\u0391-\uFFE5]+$/.test(value);
      },
      message: '请输入汉字'
    }
    english : {// 验证英语
          validator : function(value) {
              return /^[A-Za-z]+$/i.test(value);
          },
          message : '请输入英文'
      },
      ip : {// 验证IP地址
          validator : function(value) {
              return /\d+\.\d+\.\d+\.\d+/.test(value);
          },
          message : 'IP地址格式不正确'
      },
    ZIP: {
      validator: function (value, param) {
        return /^[0-9]\d{5}$/.test(value);
      },
      message: '邮政编码不存在'
    },
    QQ: {
      validator: function (value, param) {
        return /^[1-9]\d{4,10}$/.test(value);
      },
      message: 'QQ号码不正确'
    },
    mobile: {
      validator: function (value, param) {
        return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
      },
      message: '手机号码不正确'
    },
    tel:{
      validator:function(value,param){
        return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
      },
      message:'电话号码不正确'
    },
    mobileAndTel: {
      validator: function (value, param) {
        return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
      },
      message: '请正确输入电话号码'
    },
    number: {
      validator: function (value, param) {
        return /^[0-9]+.?[0-9]*$/.test(value);
      },
      message: '请输入数字'
    },
    money:{
      validator: function (value, param) {
       	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
       },
       message:'请输入正确的金额'

    },
    mone:{
      validator: function (value, param) {
       	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
       },
       message:'请输入整数或小数'

    },
    integer:{
      validator:function(value,param){
        return /^[+]?[1-9]\d*$/.test(value);
      },
      message: '请输入最小为1的整数'
    },
    integ:{
      validator:function(value,param){
        return /^[+]?[0-9]\d*$/.test(value);
      },
      message: '请输入整数'
    },
    range:{
      validator:function(value,param){
        if(/^[1-9]\d*$/.test(value)){
          return value >= param[0] && value <= param[1]
        }else{
          return false;
        }
      },
      message:'输入的数字在{0}到{1}之间'
    },
    minLength:{
      validator:function(value,param){
        return value.length >=param[0]
      },
      message:'至少输入{0}个字'
    },
    maxLength:{
      validator:function(value,param){
        return value.length<=param[0]
      },
      message:'最多{0}个字'
    },
    //select即选择框的验证
    selectValid:{
      validator:function(value,param){
        if(value == param[0]){
          return false;
        }else{
          return true ;
        }
      },
      message:'请选择'
    },
    idCode:{
      validator:function(value,param){
        return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
      },
      message: '请输入正确的身份证号'
    },
    loginName: {
      validator: function (value, param) {
        return /^[\u0391-\uFFE5\w]+$/.test(value);
      },
      message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    equalTo: {
      validator: function (value, param) {
        return value == $(param[0]).val();
      },
      message: '两次输入的字符不一至'
    },
    englishOrNum : {// 只能输入英文和数字
          validator : function(value) {
              return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
          },
          message : '请输入英文、数字、下划线或者空格'
      },
     xiaoshu:{ 
        validator : function(value){ 
        return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
        }, 
        message : '最多保留两位小数！'    
    	},
    ddPrice:{
    validator:function(value,param){
      if(/^[1-9]\d*$/.test(value)){
        return value >= param[0] && value <= param[1];
      }else{
        return false;
      }
    },
    message:'请输入1到100之间正整数'
  },
  jretailUpperLimit:{
    validator:function(value,param){
      if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
        return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
      }else{
        return false;
      }
    },
    message:'请输入0到100之间的最多俩位小数的数字'
  },
  rateCheck:{
    validator:function(value,param){
      if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
        return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
      }else{
        return false;
      }
    },
    message:'请输入0到1000之间的最多俩位小数的数字'
  }
  });*/


