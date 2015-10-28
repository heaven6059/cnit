var BOSH_SERVICE = 'http://10.255.8.17:7070/http-bind/';  
var connection = null;  
  
function log(msg)   
{  
    $('#log').append('<div></div>').append(document.createTextNode(msg));  
}  
  
/** 
 * 连接绑定方法 
 * @param status 
 */  
function onConnect(status)  
{  
    if (status == Strophe.Status.CONNECTING)
    {  
    	log('Strophe is connecting.');  
    }
    else if (status == Strophe.Status.CONNFAIL)
    {  
    	log('Strophe failed to connect.');  
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
    	log(from, Strophe.getText(body));  
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
    log("游客" +  '  说: ' + msg);  
}  
  
/** 
 * 事件监听 
 */  
$(document).ready(function (){  
    connection = new Strophe.Connection(BOSH_SERVICE);  
    connection.rawInput = function (data) { console.log('RECV: ' + data); };  
    connection.rawOutput = function (data) { console.log('SEND: ' + data); };  
    connection.connect("wanghb@yoyo","wanghb", onConnect);
//    $('#connect').bind('click', function () {  
//	    var button = $('#connect').get(0);  
//	    if (button.value == 'connect') {  
//	        button.value = 'disconnect';  
//	        connection.connect($('#jid').get(0).value, $('#pass').get(0).value, onConnect);  
//	    } else {  
//	        button.value = 'connect';  
//	        connection.disconnect();  
//	    }  
//    });  
      
    $('#replay').bind('click', function () {  
//        toId = $('#tojid').val();  
//        fromId = $('#jid').val(); 
    	 toId = "t1@yoyo";  
    	 fromId = "wanghb@yoyo";
        msg=$('#msg').val();  
        sendMsg(toId,fromId,msg);  
    });  
});