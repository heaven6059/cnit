package com.cnit.yoyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.painting.CarPart;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.Base64;
import com.cnit.yoyo.util.CommonHeadUtil;

@Controller("paintingController")
@RequestMapping("/painting")
public class PaintingController extends BaseController {
    @Autowired
    public RemoteService otherService;
    
    @Autowired
    public HessianInerface imagesService;
    
    /**
     * 喷漆部位查询
     * @param data
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/paintingPartsInfo")
	public Object paintingPartsInfo(String data, HttpServletRequest request) { 
    	log.info("---------getPaintingPartsInfo-----------");
    	log.info("----------------------data:"+data+"-------------------------");
        HeadObject headObject = null;
        ResultObject resultObject = null;
        try {
        	headObject = CommonHeadUtil.geneHeadObject("paintingService.findList");
            resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,new HashMap<String, Object>()));
            Object content = resultObject.getContent();
            //车前脸、车左侧灯父部件
            List<JSONObject> supNodes = new ArrayList<JSONObject>();
            Map<Integer,JSONObject> fatherParts = new HashMap<Integer,JSONObject>();
            
            //父部件的子部件
            List<JSONObject> subNodes = new ArrayList<JSONObject>();
            if(content !=null ){
                @SuppressWarnings("unchecked")
                List<CarPart> list = (List<CarPart>)content;
                for (CarPart carPart : list) {
                	JSONObject partsJson = new JSONObject();
                	partsJson.put("partId", carPart.getId());
                	partsJson.put("parentId", carPart.getParentId());
                	partsJson.put("partName", carPart.getPartName());
                    if(!carPart.getIsLeaf()){
                    	supNodes.add(partsJson);
                    	fatherParts.put(carPart.getId(), partsJson); 
                    }else{
                        subNodes.add(partsJson);
                    }
                }
                
              //把子部件放入父部件中
                for(JSONObject sub:subNodes){
                	 Integer parentId = (Integer) sub.get("parentId");
                	 if(parentId!=null){
                		 JSONObject fatherPart =  fatherParts.get(parentId);
                		 if(fatherPart!=null){
                			List<JSONObject>sons =  (List<JSONObject>) fatherPart.get("sons");
                			if(sons==null){
                				sons = new ArrayList<JSONObject>();
                				sons.add(sub);
                				fatherPart.put("sons", sons);
                			}
                			sons.add(sub);
                		 }
                	 }
                }
            }
            
            JSONObject contentJson = new JSONObject();
            contentJson.put("parts", supNodes);
//            contentJson.put("subParts", subNodes);
            
            resultObject.setHead(headObject);
            resultObject.setContent(contentJson);
            
            return resultObject;
        } catch (Exception e) {
        	log.error(e.getMessage(),e);
        	return processExpction(e.getMessage());
        }
	}
    
    @RequestMapping("/savePaintingPartsInfo")
    @ResponseBody
    public Object savePaintingPartsInfo(String data, HttpServletRequest request) throws Exception
    {
    	log.info("------------start savePaintingPartsInfo -----------------");
    	log.info("----------------------data:"+data+"-------------------------");
    	HeadObject headObject = null;
		try {
			headObject = CommonHeadUtil.geneHeadObject("paintingService.saveCarParts");
			JSONObject jsonData =  JSONObject.fromObject(data);
			MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
			JSONObject jsonObject = new JSONObject();
			
//			JSONObject carPartsJson = new JSONObject();
//			carPartsJson.put("car_part_id", jsonData.getString("car_part_id"));
//			carPartsJson.put("pic", jsonData.getString("pic"));
//			carPartsJson.put("remark", jsonData.getString("remark"));
			
			jsonObject.put("catParts",jsonData.get("catParts"));
			jsonObject.put("memberId",memberListDo.getMemberId());
			jsonObject.put("accountId", memberListDo.getAccountId());
			HeadObject retObject = (HeadObject) otherService.doServiceByServer(new RequestObject(headObject, jsonObject));
			return new ResultObject(retObject);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
           return processExpction(e.getMessage());
		}
    }
    
    /**
     * 上传图片
     * @throws IOException 
     */
    @RequestMapping("/paintingUploadImg")
    @ResponseBody
    public ResultObject paintingUploadImg(String data, HttpServletRequest request) throws IOException  
    {
    	JSONObject jsonData = JSONObject.fromObject(data);
    	String imgStr=jsonData.getString("file");
    	byte[] buffer = Base64.decode(imgStr);
        HeadObject head = CommonHeadUtil.geneHeadObject("pictureService.insertPicture");
        ResultObject resultObject = new ResultObject();
        ImagesDTO imagesDTO = null;
        // 保存
        try {
            imagesDTO = imagesService.uploadSingleFile(buffer,GlobalStatic.IMAGES_PATH_SHOP);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
        	log.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        
        resultObject.setHead(head);
        resultObject.setContent(imagesDTO);
        return resultObject;
    }

}
