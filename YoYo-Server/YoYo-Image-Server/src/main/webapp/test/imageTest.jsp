<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>Firefox3,IE6,IE7,IE8上传图片预览</title>  
<style type="text/css" mce_bogus="1">#preview_wrapper{  
    display:inline-block;  
    width:300px;  
    height:300px;  
    background-color:#CCC;  
}  
#preview_fake{ /* 该对象用户在IE下显示预览图片 */  
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);  
}  
#preview_size_fake{ /* 该对象只用来在IE下获得图片的原始尺寸，无其它用途 */  
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);  
    visibility:hidden;  
}  
#preview{ /* 该对象用户在FF下显示预览图片 */  
    width:300px;  
    height:300px;  
}
</style>  
<script type="text/javascript">
function onUploadImgChange(sender){  
    if( !sender.value.match( /.jpg|.gif|.png|.bmp/i ) ){  
        alert('图片格式无效！');
        return false;  
    }  
    var objPreview = document.getElementById( 'preview' );  
    var objPreviewFake = document.getElementById( 'preview_fake' );  
    var objPreviewSizeFake = document.getElementById( 'preview_size_fake' );  
  
    if( sender.files && sender.files[0] ){  
        objPreview.style.display = 'block';  
        objPreview.style.width = 'auto';  
        objPreview.style.height = 'auto';  
  
        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径  
        objPreview.src = sender.files[0].getAsDataURL();  
    }else if( objPreviewFake.filters ){  
        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果  
        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决  
        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径  
        sender.select();  
        var imgSrc = document.selection.createRange().text;  
        objPreviewFake.filters.item(  
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;  
        objPreviewSizeFake.filters.item(  
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;  
  
        autoSizePreview( objPreviewFake,  
            objPreviewSizeFake.offsetWidth, objPreviewSizeFake.offsetHeight );  
        objPreview.style.display = 'none';  
    }  
}  
function onPreviewLoad(sender){  
    autoSizePreview( sender, sender.offsetWidth, sender.offsetHeight );  
}  
  
function autoSizePreview( objPre, originalWidth, originalHeight ){  
    var zoomParam = clacImgZoomParam( 300, 300, originalWidth, originalHeight );  
    objPre.style.width = zoomParam.width + 'px';  
    objPre.style.height = zoomParam.height + 'px';  
    objPre.style.marginTop = zoomParam.top + 'px';  
    objPre.style.marginLeft = zoomParam.left + 'px';  
}  
  
function clacImgZoomParam( maxWidth, maxHeight, width, height ){  
    var param = { width:width, height:height, top:0, left:0 };  
  
    if( width>maxWidth || height>maxHeight ){  
        rateWidth = width / maxWidth;  
        rateHeight = height / maxHeight;  
  
        if( rateWidth > rateHeight ){  
            param.width = maxWidth;  
            param.height = height / rateWidth;  
        }else{  
            param.width = width / rateHeight;  
            param.height = maxHeight;  
        }  
    }  
    param.left = (maxWidth - param.width) / 2;  
    param.top = (maxHeight - param.height) / 2;  
    return param;  
}  
</script>  
</head>  
<body>  
<form action="saveImageTest.shtml" method="post" id="formQuery" name="formQuery" accept-charset="UTF-8" enctype="multipart/form-data">
    <div id="preview_wrapper">  
        <div id="preview_fake">  
            <img id="preview" onload="onPreviewLoad(this)"/>  
        </div>  
    </div>  
    <br/>  
    
    <span>
    <a>
    <input id="upload_img" type="file" name="fileImage" onchange="onUploadImgChange(this)"/>  
    </a>
    </span>
    <br/>  
    <img id="preview_size_fake"/> 
     <div>
       <select id="sel" name="imageType">
          <option value="0">JMagick</option>
          <option value="1">GraphicsMagick</option>
       </select>
       <input type="submit" value="保存"/><a href="/store/test/imageTest.shtml" >返回</a>
       <span> <h2>${msg} </h2>	</span>
       <span><a href="${url}" >连接图上</a></span>
     </div>
    </form>
</body>  
</html>  