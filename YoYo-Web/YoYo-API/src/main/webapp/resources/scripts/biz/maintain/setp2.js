$(function (){
	maintian.init();
});

var maintian={
	maintian_items:[],
	maintian_category_id:[],
	my_car_cook:"my_car",
	maintian_accessory:[],
	choose_company:null,
	is_login:"IS_LOGIN",
	init:function (){
		var mycar=this.readCookie(this.my_car_cook);
		if(mycar){
			mycar=mycar.split("|");
			$(".models1 h3 b").html(mycar[3]+" "+mycar[4]);
		}
		
		$(".addmainten").click(function (){
			var len =$(this).closest(".delist").find("input:checkbox:checked").length;
			if(len==0){
				alert("请选择保养项目");
				return;
			}
			var maintainItem={
				index:$(this).closest(".delist").index(),
				maintainText:$(this).attr("data-orgs"),
				maintainItems:[]
			};
			$(this).closest(".delist").find("input[type='checkbox']").each(function (x,item){
				if($(item).is(":checked")){
					maintainItem.maintainItems.push($(item));
					maintian.maintian_category_id.push($(item).val());
					maintian.maintian_accessory.push($(item).attr("data-key"));
				}
			});
			maintian.addMaintainItem($(this).closest(".delist").index(),maintainItem);
			maintian.loadMaintainCompany();
		});
		$(".nextstep").click(function (){
			if(maintian.choose_company==null){
				layer.alert("请选择需预约店铺！",{title:false,closeBtn:false,icon:0});
				return;
			}
			window.location.href=yoyo.portalUrl+"/carMaintain/step3?maintainId="+$("#maintainId").val()+"&companyId="+maintian.choose_company+"&ids="+maintian.maintian_accessory.join(",")+"&accessoryCategoryIds="+maintian.maintian_category_id.join(",");
		});
	},
	addMaintainItem:function (index,newItem){
		var notJoin=true;
		$.each(maintian.maintian_items,function (x,item){
			if(item.index==index){
				maintian.maintian_items.splice(x, 1,newItem);
				notJoin=false;
				return notJoin;
			}
		});
		if(notJoin){
			maintian.maintian_items.push(newItem);
		}
		maintian.buildMaintainList();
	},
	loadMaintainCompany:function(){
		$.post("../carMaintain/findMaintainCompany",{ids:maintian.maintian_accessory.join(",")},function (result){
			var html=[];
			$.each(result.content,function (x,item){
				html.push('<dl>');
				html.push('<dt>');
				html.push('<b>'+item.companyName+' </b>');
				html.push('</dt>');
				html.push('<dd>');
				html.push('<a href="javascript:;" data-org="'+item.companyId+'" class="addmainten hidefocus choosecompany">预约&gt;&gt;</a>');
				html.push('</dd>');
				html.push('</dl>');
			});
			$("#dd_tablestep2mt").find("h6").nextAll().remove();
			$("#dd_tablestep2mt").append(html.join(""));
			$("#dd_tablestep2mt").on("click",".choosecompany",function (){
				maintian.choose_company=$(this).attr("data-org");
			});
		});
	},
	buildMaintainList:function(){
		var maintainLit=[];
		$(".sList").find("tr:gt(0)").remove();
		$.each(maintian.maintian_items,function (x,items){
			$.each(items.maintainItems,function (x,item){				
				maintainLit.push('<tr>');
				if(x==0){
				maintainLit.push('<td rowspan="'+items.maintainItems.length+'">'+items.maintainText+'</td>');
				}
				maintainLit.push('<td class="project">'+item.attr("data-orgs")+'</td>');
				maintainLit.push('<td class="center"><a class="hidefocus" onclick="removeMaintain('+items.index+','+item.attr("data-key")+');" href="javascript:;">删除</a></td>');
				maintainLit.push('</tr>');				
			});
		});
		$(".sList").append(maintainLit.join(""));
	},
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
	},	
};

function removeMaintain(index,id){
	$.each(maintian.maintian_items,function (x,items){
		if(items.index==index){
			$.each(items.maintainItems,function (y,item){
				if(item.attr("data-key")==id){
					items.maintainItems.splice(y, 1);
					if(items.maintainItems.length==0){
						maintian.maintian_items.splice(x,1);
					}
					maintian.buildMaintainList();
				}
			});
		}
	});
}