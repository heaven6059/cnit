package com.cnit.yoyo.imageupload.controller;

/**
 * @description 图片上传
 * @detail
 * @added  by  yuping
 * @version 1.0.0
 */

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.cnit.yoyo.util.DateUtils;

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

    /**
     * 上传图片
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public ResultObject uploadImg(@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
        System.out.println("开始");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "4000020101-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        if (file.getSize() > 2 * 1024 * 1024) { // 文件过大
            head.setRetCode(ErrorCode.OVER_LIMIT_SIZE);
            head.setRetMsg("文件大小不能超过2M");
        } else if (file.getSize() > 0 && file.getSize() < 2 * 1024 * 1024) {
            // 保存
            try {

                ImagesDTO imagesDTO = imagesService.uploadSingleFile(file.getBytes(), GlobalStatic.IMAGES_PATH_MEM);
                System.out.println("===============success================");
                System.out.println(imagesDTO.getFileName());
                resultObject = itemService.doService(new RequestObject(head, imagesDTO.getFileName()));
                head.setRetCode(ErrorCode.SUCCESS);
                head.setRetMsg((String) resultObject.getContent()); // 返回一个数组
            } catch (Exception e) {
                e.printStackTrace();
                head.setRetCode(ErrorCode.FAILURE);
                head.setRetMsg("上传失败");
            }
        } else {
            head.setRetCode(ErrorCode.FAILURE);
            head.setRetMsg("上传失败");
        }

        return new ResultObject(head);
    }

}
