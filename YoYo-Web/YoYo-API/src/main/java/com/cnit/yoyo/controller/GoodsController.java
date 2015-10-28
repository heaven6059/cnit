package com.cnit.yoyo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
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
import com.cnit.yoyo.model.goods.GoodsForAppVo;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.dto.PictureDTO;
import com.cnit.yoyo.model.goods.dto.ProductImgDTO;
import com.cnit.yoyo.model.member.dto.OrderCommentDetailDTO;
import com.cnit.yoyo.model.member.dto.TagsDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.domain.ResultPage;

@Controller("goodsController")
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	private Integer pageSize =10;
	@Autowired
	private RemoteService itemService;
	@Autowired
	private RemoteService orderService;
	@Autowired
	private RemoteService memberService;
	
    @RequestMapping("/usefulVirtualCategory")
    @ResponseBody
    public Object usefulVirtualCategory(String data,HttpServletRequest request){
        log.info("###########usefulVirtualCategory-->start-------------------");
        log.info("----------------------data:"+data+"-------------------------");

		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			//获取请求参数
//			JSONObject jsonData =  JSONObject.fromObject(data);

			headObject = CommonHeadUtil.geneHeadObject("categoryService.getUsefulVirtualCategory");
	        resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, null));
			log.info("###########usefulVirtualCategory-->end-------------------");
			return resultObject;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return processExpction(e.getMessage());
		}
    }
    
    @RequestMapping("/goodsList")
    @ResponseBody
    public Object goodsList(String data,HttpServletRequest request){
        log.info("###########goodsList-->start-------------------");
        log.info("----------------------data:"+data+"-------------------------");

		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			String companyId = (String) CommonUtil.getJsonValue(jsonData,"companyId");
			String orderType = (String) CommonUtil.getJsonValue(jsonData,"orderType");
			String act =(String) CommonUtil.getJsonValue(jsonData, "act");
			String catId = (String) CommonUtil.getJsonValue(jsonData,"catId");  //虚拟分类id;
			String num = (String) CommonUtil.getJsonValue(jsonData,"num");  //查询条数
			
//			String companyId = request.getParameter("companyId");
//			String orderType = request.getParameter("orderType");
//			String act = request.getParameter("act");
//			String catId = request.getParameter("catId");  //虚拟分类id;
//			String num = request.getParameter("num");  //查询条数
			
			Map<String,Object> queryCon = new HashMap<String,Object>(); //查询参数;
			queryCon.put("companyId", companyId);
			queryCon.put("orderType", orderType);
			queryCon.put("act", act);
			queryCon.put("catId", catId);
			
			if(num==null||num.equals("")){
				queryCon.put("limit", 5); //默认5条
			}else{
				queryCon.put("limit", Integer.valueOf(num));
			}

			headObject = CommonHeadUtil.geneHeadObject("goodsService.listGoods");
	        resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, queryCon));
	        
			//图片地址转成httpURL
	        if(resultObject!=null&&resultObject.getContent()!=null){
				String imgUrl = Configuration.getInstance().getConfigValue("images.url");
				 List<GoodsForAppVo> goods = ( List<GoodsForAppVo>) resultObject.getContent();
				for(GoodsForAppVo gfav:goods){
					gfav.setBigPic(imgUrl+gfav.getBigPic());
					gfav.setSmallPic(imgUrl+gfav.getSmallPic());
					gfav.setMidPic(imgUrl+gfav.getMidPic());
				}
	        }
			log.info("###########goodsList-->end-------------------");
			return resultObject;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return processExpction(e.getMessage());
		}
    }
    
    @RequestMapping("/goodsDetail")
    @ResponseBody
    public Object goodsDetail(String data,HttpServletRequest request){
    	 log.info("###########goodsDetail-->start-------------------");
         log.info("----------------------data:"+data+"-------------------------");

 		HeadObject headObject = null;
 		ResultObject resultObject = null;
 		try {
 			//获取请求参数
 			JSONObject jsonData =  JSONObject.fromObject(data);
 			String goodsIdString = jsonData.getString("goodsId");
// 			String goodsIdString = request.getParameter("goodsId");
 			
 			JSONObject jsonObject = new JSONObject();
 			if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
 				Integer goodsId = Integer.parseInt(goodsIdString);
 				//查询商品详情
 				headObject = CommonHeadUtil.geneHeadObject("goodsService.getGoodsDetailById");
 		        resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, goodsId));
 		        GoodsWithBLOBs goods = (GoodsWithBLOBs) JSONObject.toBean(((JSONObject)resultObject.getContent()),GoodsWithBLOBs.class);
 		        if(goods!=null&&goods.getGoodsId()!=null&&!"1".equals(goods.getDisabled())){
 		        	if(!"0".equals(goods.getMarketable())){
 		        		//查询货品详情
 			        	headObject = CommonHeadUtil.geneHeadObject("productService.findByGoodsId");
 				        resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, goodsId));
 				        List<ProductImgDTO> productDTOList = (List<ProductImgDTO>) resultObject.getContent();
 				        if(productDTOList!=null&&productDTOList.size()>=1){
 				        	List<Picture> picList = null;
 				        	Picture pic = null;
 				        	for(int i=productDTOList.size()-1 ; i>=0 ; i--){
 				        		if(!"1".equals(productDTOList.get(i).getDisabled()) && !"1".equals(productDTOList.get(i).getLimitStore()) &&!"1".equals(productDTOList.get(i).getLimitStoredown())){
 				        			if(!(productDTOList.get(i).getPicList()!=null&&productDTOList.get(i).getPicList().size()>=1) ){
 					        			picList = new ArrayList<Picture>();
 					        			pic = new Picture();
 					        			pic.setPicturePath(goods.getBigPic()!=null&&!"".equals(goods.getBigPic())?goods.getBigPic():goods.getMidPic());
 					        			picList.add(pic);
 					        			productDTOList.get(i).setPicList(picList);
 					        		}
 				        		}else{
 				        			productDTOList.remove(i);
 				        		}
 				        	}
 				        }
 				        Map<String,Object> res = new HashMap<String,Object>();
 				        
 						//图片地址转成httpURL
 						String imgUrl = Configuration.getInstance().getConfigValue("images.url");
 						if(goods!=null){
 							goods.setBigPic(imgUrl+goods.getBigPic());
 							goods.setSmallPic(imgUrl+goods.getSmallPic());
 							goods.setMidPic(imgUrl+goods.getMidPic());
 						}
 						if(productDTOList!=null&&!productDTOList.isEmpty()){
 							for(ProductImgDTO po:productDTOList){
 								List<Picture> picList = po.getPicList();
 								if(picList!=null&&!picList.isEmpty()){
 									for(Picture pic:picList){
 										pic.setPicturePath(imgUrl+pic.getPicturePath());
 									}
 								}
 							}
 						}
 						
 				        res.put("goods", goods);
 				        res.put("product", productDTOList);
 				        resultObject.setContent(res);
 		        	}else{
 		        		resultObject.setContent(null);
 		        	}
 		        }else{
 		        	resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
 		        }
 			}else{
 				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
 			}
 			log.info("###########goodsDetail-->end-------------------");
 			return resultObject;
 		} catch (Exception e) {
 			log.error(e.getMessage(), e);
 			return processExpction(e.getMessage());
 		}
    	
    }
    
    @RequestMapping("/goodsComment")
    @ResponseBody
    public Object goodsComment(String data,HttpServletRequest request){
    	 log.info("###########goodsComment-->start-------------------");
         log.info("----------------------data:"+data+"-------------------------");

 		HeadObject headObject = null;
 		ResultObject resultObject = null;
 		JSONObject jsonObject = new JSONObject();
 		try {
 			//获取请求参数
 			JSONObject jsonData =  JSONObject.fromObject(data);
 			String goodsIdString = jsonData.getString("goodsId");
 			String pageIndexString = jsonData.getString("pageIndex");
 			String pageSizeStr = (String) CommonUtil.getJsonValue(jsonData, "pageSize");
// 			String goodsIdString = request.getParameter("goodsId");
// 			String pageIndexString = request.getParameter("pageIndex");
 			if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
 				Integer pageIndex = 1;
 				if(pageIndexString!=null&&!"".equals(pageIndexString)){
 					pageIndex = Integer.parseInt(pageIndexString);
 				}
 				if(pageIndex < 1){
 					pageIndex = 1;
 				}
 				Integer goodsId = Integer.parseInt(goodsIdString);
 				headObject = CommonHeadUtil.geneHeadObject( "orderCommentService.selectOrderCommentByGoodsId");
 				jsonObject.put("pageIndex", pageIndex);
 				jsonObject.put("pageSize", pageSizeStr==null?pageSize:Integer.valueOf(pageSizeStr));
 				jsonObject.put("goodsId", goodsId); 
 			    resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject,jsonObject));
 			    Map<String ,Class> map = new HashMap<String,Class>();
 			    map.put("rows", OrderCommentDetailDTO.class);
 			    //查询评论列表
 			    ResultPage<OrderCommentDetailDTO> commentPage = (ResultPage<OrderCommentDetailDTO>) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),ResultPage.class, map);
 			    if(commentPage!=null&&commentPage.getRows().size()>=1){
 			    	ResultPage<Picture> picPage = null;
 			    	ResultPage<PictureDTO> picDtoPage = null;
 			    	PictureDTO picDto = null;
 			    	List<PictureDTO> picDtoList = null;
 			    	for(int i=0;i<commentPage.getRows().size();i++){
 			    		picDtoPage = new ResultPage<PictureDTO>();
 			    		picDtoList = null;
 			    		//评论照片
 			    		headObject = CommonHeadUtil.geneHeadObject("pictureService.selectByCommentId");
 						jsonObject.put("commentId", commentPage.getRows().get(i).getCommentId());
 					    resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject,jsonObject));
 				    	List<Picture> picLst = (List<Picture>)resultObject.getContent(); 
 				    	if(picLst.size()>=1){
 				    		picDtoList = new ArrayList<PictureDTO>();
 					    	for(int j=0;j<picLst.size();j++){
 					    		picDto = new PictureDTO();
 					    		BeanUtils.copyProperties(picLst.get(j), picDto);
 					    		picDtoList.add(picDto);
 					    	}
 				    	}
 					    commentPage.getRows().get(i).setPicPageList(picDtoList);
 			    	}
 			    	
 			    	//查询好评度 positive (comment) 中评 moderate/neutral (comment) 差评 negative (comment)
 				    headObject = CommonHeadUtil.geneHeadObject( "orderCommentService.selectOrderCommentRateByGoodsId");
 				    resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject,goodsId));
 				    
 			    	commentPage.getRows().get(0).setPositiveRate((Double)(resultObject.getContent()!=null?JSONObject.fromObject(resultObject.getContent()).get("positiveRate"):(double)0));
 			    	commentPage.getRows().get(0).setNeutralRate((Double)(resultObject.getContent()!=null?JSONObject.fromObject(resultObject.getContent()).get("neutralRate"):(double)0));
 			    	commentPage.getRows().get(0).setNegativeRate((Double)(resultObject.getContent()!=null?JSONObject.fromObject(resultObject.getContent()).get("negativeRate"):(double)0));
 				    
 				    //查询标签
 				    headObject = CommonHeadUtil.geneHeadObject( "tagsService.selectTopCommentTagsByGoodsId");
 				    jsonObject.put("pageIndex", 1);
 					jsonObject.put("pageSize", 10);
 					jsonObject.put("goodsId", goodsId);
 				    resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject,jsonObject));
 				    map.put("rows", TagsDTO.class);
 				    ResultPage<TagsDTO> tagsPage = (ResultPage<TagsDTO>) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),ResultPage.class,map);
 				    if(tagsPage!=null){
 				    	commentPage.getRows().get(0).setTagsList(tagsPage.getRows());
 				    }
 			    }
 			    resultObject.setHead(new HeadObject(ErrorCode.SUCCESS));
 			    resultObject.setContent(commentPage);
 			}
 			log.info("###########goodsComment-->end-------------------");
 			return resultObject;
 		} catch (Exception e) {
 			log.error(e.getMessage(), e);
 			return processExpction(e.getMessage());
 		}
    	
    }
    
}
