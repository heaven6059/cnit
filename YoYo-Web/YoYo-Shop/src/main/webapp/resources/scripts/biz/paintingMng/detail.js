$(function(){
	$('#commit').click(ajax);
	$('.input_moneny').focus(function(){
		$(this).val("");
	});
	$('.input_moneny').blur(function(){
		var valArr = $('.input_moneny');
		var sum=0;
		for(var i=0; i<valArr.length; i++){
			var isNum = /^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test(valArr.eq(i).val());
			if(!isNum){
				valArr.eq(i).val(0);
			}
			if(valArr.eq(i).val()){
				sum += parseFloat(valArr.eq(i).val())*100;
			}
		}
		$('#totalprice').text(sum/100);
	});
	$('.remark2').blur(function(){
		if($(this).val().trim()){
			$(this).next().hide();
		}
	});
	
	//限制100个字符
	$('#offerForm').on('keydown','.remark2',function(e){
        var curLength=$(this).val().length;   
        if(curLength>=100){
        	if(e.which !=8){
        		return false;
        	}
        }  
	});
	
	$('#offerForm').on('blur','.remark2',function(e){
		if($(this).val().length>100){
			$(this).parent().find('.red.remark2').show();
		}else{
			$(this).parent().find('.red.remark2').hide();
		}
	});
	
	layer.config({
		extend: [
		'extend/layer.ext.js' 
		],
		shift:5, //设置动画风格
		});
	
	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	    layer.photos({
	        photos: '.p_img.fl'
	    });
	}); 
});

function ajax(){
	$('.input_moneny').trigger('blur');
	if($('#totalprice').text() <= 0){
		alert("报价总额必须要大于0！");
		return ;
	}
	var flag = true;
	$('.input_moneny').each(function (index, ele) {
		var root = $(ele).closest('.item_single');
		 if($(ele).val() <= 0 && !root.find('.remark2').val().trim()){
			 root.find('.red.remark').show();
			 flag = false;
			 return ;
		 }else{
			 root.find('.red.remark').hide();
		 }
	 });

	if(flag){
		var rows = getCarDamageDetailList();
		$.messager.confirm('确认', '确定提交报价吗？', function(r){
			if (r){
				var message = {};
				//店铺ID
				message.companyId = companyId ;
				//报价综合
				message.totalprice = $('#totalprice').text();
				//受损单ID
				message.carDamageId = getParameter('id');
				//受损部位
				message.rows = JSON.stringify(rows);
				$.ajax({
					type:'post',
					url:_path + '/paintingManager/offer',
					data:message,
					beforeSend:function(){
						$('#commit').off('click');
					},
					success:function(data){
						if (data.head.retCode == '000000') {
							window.location.href = _path+ '/paintingManager/detailList?canSee=true';
						} else if(data.head.retCode == 'NOPERMISSION'){
							$('div.right_side').empty().append(data.head.retMsg); 
						} else {
							$.messager.alert('错误', '报价失败', 'error');
						}
					},
					complete:function(){
						$('#commit').click(ajax);
					}
				});
			}
		});
	}
}

//获得所有部件报价
function getCarDamageDetailList(){
	var rows = [];
	$('.item_list').each(function(){
		var carDamageDetailId = $(this).find('input:hidden').val();
		var offerPrice = $(this).find('input[name="offerPrice"]').val();
		var remark = $(this).find('textarea[name="remark"]').val();
		rows.push('{"carDamageDetailId":"'+carDamageDetailId+'","offerPrice":"'+offerPrice+'","remark":"'+remark+'"}'); 
	});
	return rows;
}


function sss(obj) {
    if (isNaN(obj.value)) {
        obj.value = obj.value.substring(0, obj.value.length - 1);
    }
}
function standard(obj) {
    var val = obj.value;
    var kc = window.event.keyCode;

    //首位或者0.后不能输入.
    if (val.length == 0 || val == "0.") {
        if (kc == 110 || kc == 190) 
        {
            window.event.returnValue = false;
            return;
        }
    }

    //第一位为0第二位必须是.
    if (val.length == 1 && val == "0") 
    {
        if (kc == 8) {
            window.event.returnValue = true;
            return;
        }
        if (kc != 110 && kc != 190) {
            window.event.returnValue = false;
            return;
        }
    }
    var index = val.indexOf(".");
    if (val.length >= 9 && index < 0) {
        if (kc == 8 || kc == 110 || kc == 190) {
            window.event.returnValue = true;
            return;
        } else {
            window.event.returnValue = false;
            return;
        }
    }
    if (index >= 0) {
        var len = val.substring(index + 1, val.length).length;
        if (len >= 2) {
            if (kc == 8) {
            	window.event.returnValue = true;
                return;
            } else {
                window.event.returnValue = false;
                return;
            }
        }
    }
    //允许输入的数字键0~9和小数点（110,190）和回退键
    if ((kc >= 48 && kc <= 57) || (kc >= 96 && kc <= 105) || kc == 110 || kc == 190 || kc == 8) //如果是数字 或 .
    {
        window.event.returnValue = true;
        return;
    } else {
        window.event.returnValue = false;
        return;
    }
}