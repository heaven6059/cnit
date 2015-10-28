var BOSH_SERVICE = 'http://localhost:7070/http-bind/';  
var connection = null; 

$(document).ready(function (){  
    connection = new Strophe.Connection(BOSH_SERVICE);  
    connection.rawInput = function (data) { console.log('RECV: ' + data); };  
    connection.rawOutput = function (data) { console.log('SEND: ' + data); };  
    connection.connect("wanghb@yoyo","wanghb", onConnect);
      
    $('#msg').bind('keypress',function(event){
        if(event.keyCode == "13"){
        	var  toId = "客服1@yoyo";  
        	var  fromId = "wanghb@yoyo";
            msg=$('#msg').val();  
            sendMsg(toId,fromId,msg);  
        }
    });
});
function onConnect(status)  
{  
    if (status == Strophe.Status.CONNECTING)
    {  
//    	log('正在连接服务器。。。。');  
    }
    else if (status == Strophe.Status.CONNFAIL)
    {  
    	log('','Strophe failed to connect.');  
    	$('#connect').get(0).value = 'connect';  
    }
    else if (status == Strophe.Status.DISCONNECTING)
    {  
//    	log('Strophe is disconnecting.');  
    }
    else if (status == Strophe.Status.DISCONNECTED)
    {  
//    	log('Strophe is disconnected.');  
    	$('#connect').get(0).value = 'connect';  
    }
    else if (status == Strophe.Status.CONNECTED)
    {  
//    	log('Strophe is connected.');  
//    	log(connection.jid+''); 
    	log("",'尊敬的优优车用户，我们又见面了，请问您有什么问题咨询？');  
    	connection.addHandler(onMessage, null, 'message', null, null,  null);   
    	connection.send($pres().tree());  
    }  
}  
/** 
 * 获取消息时的方法 
 * @param msg 
 * @returns {Boolean} 
 */  
function onMessage(msg)
{  
    var to = msg.getAttribute('to');  
    var from = msg.getAttribute('from');  
    var type = msg.getAttribute('type');  
    var elems = msg.getElementsByTagName('body'); 
    if(type == "chat" && elems.length > 0)
    {  
    	var body = elems[0];  
    	log(from+":" , Strophe.getText(body));  
    }
    return true;
}
  
/** 
 * 发信息 
 * @param toId 
 * @param fromId 
 * @param msg 
 */  
function sendMsg(toId,fromId,msg)
{  
    var reply = $msg({to: toId, from:fromId , type: 'chat'}).cnode(Strophe.xmlElement('body', '' ,msg));  
    connection.send(reply.tree());  
//    log( toId +  ' 说: ' + msg);
    var nickName = $("#nickName").val();
    if(nickName){
    	log(nickName+":", msg);
    }else{
    	log("游客:", msg);
    }
    $('#msg').val(""); 
}

function log(user,msg){
//    $('#log').append('<div></div>').append(document.createTextNode(msg)); 
    var html='<div class="message clearfix"><div class="wrap-text">';
    	if(user){
    		html+='<h5 class="clearfix">'+user+'</h5>';
    	}
		html+='<div>'+msg+'</div></div>';
		html+='<div class="wrap-ri"><div clsss="clearfix"><span>'+getCurDateTime()+'</span>';
		html+='</div></div><div style="clear:both;"></div></div>';
	$("#logMessage").append(html);
	$(".chat01_content").scrollTop($("#logMessage")[0].scrollHeight);
}
function getCurDateTime() {
	var now = new Date();
	var SY = now.getFullYear();
	var SM = now.getMonth();
	var SD = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	if(minute<10){
		minute="0"+minute;
	}
	if(second<10){
		second="0"+second;
	}
	return SY+"-"+(SM + 1)+"-"+SD+' '+hour+':'+minute+':'+second;
}