package com.cnit.yoyo.shop.enter.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Configuration;

/**
 * ue编辑器图片上传
 * @author yangyi
 *
 */
@Controller
@RequestMapping("/upload")
public class UEImageUploadController {
    @Autowired
    private RemoteService itemService;
    @Autowired
    private HessianInerface imagesService;

    /** 
    * @upfile 就是上面提到的upfile，要对应一致 
    */  
    @RequestMapping("/img")  
        @ResponseBody  
        public String upload(@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request, HttpServletResponse response) throws Exception {  
            request.setCharacterEncoding("utf-8");  
            response.setCharacterEncoding("utf-8");  
            String result = "";  
//            if(path == null) {  
//                System.out.println(">> the get file path error!");  
//                return result;  
//            }
            HeadObject head = CommonHeadUtil.geneHeadObject(request, "4000020101-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
            ResultObject resultObject = null;
            // 保存
            try {
                ImagesDTO imagesDTO = imagesService.uploadSingleFile(upfile.getBytes(), GlobalStatic.IMAGES_PATH_MP);
                System.out.println("===============success================");
                System.out.println(imagesDTO.getFileName());
                resultObject = itemService.doService(new RequestObject(head, imagesDTO.getFileName()));
                head.setRetMsg((String) resultObject.getContent()); // 返回一个数组
                result = "{\"name\":\""+ imagesDTO.getFileName() +"\", \"originalName\": \""+ imagesDTO.getFileName() +"\", \"size\": "+ upfile.getSize() +", \"state\": \"SUCCESS\", \"type\": \""+imagesDTO.getSuffix()+"\", \"url\": \""+imagesDTO.getFileName()+"\"}";  
                result = result.replaceAll( "\\\\", "\\\\" );  
            }  catch (Exception e) {
                e.printStackTrace();
                head.setRetCode(ErrorCode.FAILURE);
                head.setRetMsg("上传失败");
            } 
      
            return result;  
        } 
    
    /** 
     * 获得图片 
     * @param path 图片路径：140615_xxx，格式为:datedir_filename 
     * @param request 
     * @param response 
     */  
    @RequestMapping(value = "/file/{path}")  
    public @ResponseBody  
    void _file(@PathVariable String path, HttpServletRequest request, HttpServletResponse response) {  
                // 这个path就是上面result的url  
                // 因为我是多层目录所以就使用了_下划线代替/  
                // 主要就是注意路径要对应一致就行了  
        path = path.replaceAll("_", "/");  
        FileInputStream in = null;  
        OutputStream out = null;  
        try {  
            File file = new File(path);  
            in = new FileInputStream(file);  
            byte[] b = new byte[(int) file.length()];  
            in.read(b);  
            in.close();  
            response.setContentType("image/*");  
            out = response.getOutputStream();  
            out.write(b);  
            out.flush();  
            out.close();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (in != null)  
                    in.close();  
                if (out != null)  
                    out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
