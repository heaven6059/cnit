package com.cnit.yoyo.good.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

/**
 * @description 图片上传
 * @detail
 * @author xiaox
 * @version 1.0.0
 */
@Controller
@RequestMapping("/image")
public class ImageUploadController {
    @Autowired
    private RemoteService itemService;
    @Autowired
    private HessianInerface imagesService;

    @RequestMapping("/uploadImg")
    @ResponseBody
    public ResultObject uploadImg(@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
        System.out.println("开始");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "4000020101-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        // 保存
        try {
            ImagesDTO imagesDTO = imagesService.uploadSingleFile(file.getBytes(), GlobalStatic.IMAGES_PATH_MP);
            System.out.println("===============success================");
            System.out.println(imagesDTO.getFileName());
            resultObject = itemService.doService(new RequestObject(head, imagesDTO.getFileName()));
            head.setRetMsg((String) resultObject.getContent()); // 返回一个数组
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
            head.setRetMsg("上传失败");
        }
        return new ResultObject(head);
    }

    @RequestMapping("/uploadImgWithSize")
    @ResponseBody
    public Object uploadImgWithSize(@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request,Integer width, Integer height) {
    	ResultObject result = null;
    	try {
			String checked = checkFile(request, file, width, height);
			if(StringUtils.isNotBlank(checked)){
				HeadObject head = new HeadObject();
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg(checked);
				return new ResultObject(head);
			}else{
				result = uploadImg(file, request, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    private String checkFile(HttpServletRequest request, MultipartFile file,Integer width, Integer height) throws IOException {
		String msg = "";
    	if( null != width && null != height){
    		 BufferedImage bi = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
    		 if (width != bi.getWidth() || height != bi.getHeight()) {
                 msg +="图片尺寸不合适";
             }
    	}else if(file.getSize() > 2 * 1024 * 1024){
    		msg += "文件大小不能超过2M";
        }
    	return msg;
    }
}
