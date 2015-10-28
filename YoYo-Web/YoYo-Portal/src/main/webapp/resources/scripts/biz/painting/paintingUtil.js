//事件工具类
var EventUtil = {
	addHandle: function(element, type, handle){
		if(element.addEventListener){
			element.addEventListener(type, handle, false);
		}else if(element.attachEvent){
			element.attachEvent('on' + type, handle);
		}else{
			element['on' + type] = handle;
		}
	},
	removeHandle: function(element, type, handle){
		if(element.removeEventlistener){
			element.removeEventlistener(type, handle, false);
		}else if(element.detachEvent){
			element.detachEvent('on' + type, handle);
		}else{
			element['on' + type] = null;
		}
	},
	getEvent: function(event){
		return event ? event : window.event;
	},
	getTarget: function(event){
		return event.target || event.srcElement; 
	},
	preventDefault: function(event){
		if(event.preventDefault){
			event.preventDefault();
		}else{
			event.returnValue = false;
		}
	}
}

//通用样式方法
function hasClass(obj,cls){
	return new RegExp('(\\s|^)' + cls + '(\\s|$)').test(obj.className);
}
function addClass(obj, cls){
	if(!hasClass(obj,cls)){
		obj.className += " " + cls;
	}
}
function removeClass(obj, cls){
	if(hasClass(obj,cls)){
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		obj.className = obj.className.replace(reg, '');
	}
}

//根据class选择元素，兼容ie8及以下版本
function getElesByClass(obj, cls){
	var eles = 	obj.getElementsByTagName('*');
	var result = new Array();
	for(var i=0; i<eles.length; i++){
		if(hasClass(eles[i],cls)){
			result.push(eles[i]);	
		}
	}
	return result;
}

//去掉DOM遍历时的空白节点
function removeBlankNodes(nodeList){
	var nodes = new Array();
	for(var i=0; i<nodeList.length; i++){
		var node = nodeList[i];
	    if(node && node.nodeType ==1){
	    	nodes.push(node);
	    }
    }
    return nodes;
}

var createAjax=function(){
    var xhr=null;
    try{//IE系列浏览器
        xhr=new ActiveXObject("microsoft.xmlhttp");
    }catch(e1){
        try{//非IE浏览器
            xhr=new XMLHttpRequest();
        }catch(e2){
            window.alert("您的浏览器不支持ajax，请更换！");
        }
    }
    return xhr;
};

//获取url某个参数
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) {
		return decodeURIComponent(r[2]);
	} 
	return null;
}