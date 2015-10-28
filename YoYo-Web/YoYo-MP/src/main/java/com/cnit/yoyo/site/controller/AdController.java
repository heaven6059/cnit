package com.cnit.yoyo.site.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.site.Ad;
import com.cnit.yoyo.model.site.dto.AdQueryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Image2Base64Utils;

/**
 * @description <商城首页广告管理>
 * @detail <进行商城首页广告的维护>
 * @author <a href="jpzhou@cnit.com">周加平</a>
 * @version 1.0.0
 */
//TODO 异常处理 resultObject
@Controller
@RequestMapping("/siteAd")
public class AdController {
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);
    @Autowired
    private RemoteService otherService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
    }
    /**
     * 
     *@description <进入广告管理页面>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月9日
     *@param request
     *@return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        logger.info("start[AdController.index]");
        logger.info("end[AdController.index]");
        return "pages/biz/site/adIndex";
    }

    /**
     * 
     *@description <获取广告列表>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月9日
     *@param request
     *@return
     *@throws Exception
     */
    @RequestMapping(value = "/adList", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject adList(HttpServletRequest request,AdQueryDTO dto) throws Exception {
        logger.info("start[AdController.adList]");
        HeadObject headObject = null;
        headObject = CommonHeadUtil.geneHeadObject(request, "990701-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = otherService.doService(new RequestObject(headObject, dto));
        } catch (Exception e) {
            logger.error("获取广告列表异常", e);
            headObject.setRetCode(ErrorCode.FAILURE);
            resultObject.setHead(headObject);
        }
        logger.info("start[AdController.adList]");
        return resultObject;
    }

    /**
     * 
     *@description <进入广告编辑页面>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月9日
     *@param request
     *@param name
     *@return
     *@throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Integer id) throws Exception {
        logger.info("start[AdController.edit]");
        HeadObject headObject;
        headObject = CommonHeadUtil.geneHeadObject(request, "990701-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject;
        try {
            resultObject = otherService.doService(new RequestObject(headObject, id));
            Ad ad =(Ad)resultObject.getContent();
            String jsonStr = ad.getAdConfig();
            JSONObject adJson = JSONObject.fromObject(ad);
            adJson.put("adConfig", JSON.toJSON(jsonStr));
            request.setAttribute("adJson", adJson);
        } catch (Exception e) {
            logger.error("跳转到广告编辑页面异常", e);
        }
        logger.info("end[AdController.edit]");
        return "pages/biz/site/adEdit";
    }

    /**
     * 
     *@description <保存编辑的广告>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月9日
     *@param request
     *@param ad
     *@return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject save(HttpServletRequest request, Ad ad) {
//        logger.info("start[AdController.save]");
//		//获取颜色
//        String config = ad.getAdConfig();
//        AdConfig adConfig = JSON.parseObject(config, AdConfig.class);
//        List<PicInfo> list = adConfig.getContent();
//        String imgurl = "";
//        try {
//	        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//	        	PicInfo info = (PicInfo) iterator.next();
//				imgurl = Configuration.getInstance().getConfigValue("images.url") + info.getPicUrl();
//				String color;
//				color = getColor(imgurl);
//				info.setBgColor(color);
//			}
//	        config = JSON.toJSONString(adConfig);
//	        ad.setAdConfig(config);
//        } catch (IOException e) {
//			e.printStackTrace();
//		}
//        HeadObject head = null;
//        head = CommonHeadUtil.geneHeadObject(request, "990701-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//        ResultObject result = null;
//        try {
//            result = otherService.doService(new RequestObject(head, ad));
//            head.setRetCode(ErrorCode.SUCCESS);
//        } catch (Exception e) {
//            head.setRetCode(ErrorCode.FAILURE);
//            logger.error("保存广告更改异常", e);
//        }
//        logger.info("end[AdController.save]");
//        return result;
    	
        logger.info("start[AdController.save]");
        HeadObject head = null;
        head = CommonHeadUtil.geneHeadObject(request, "990701-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject result = null;
        try {
            result = otherService.doService(new RequestObject(head, ad));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("保存广告更改异常", e);
        }
        logger.info("end[AdController.save]");
        return result;
    }
    
    /**
     * @Title:  getColor  
     * @Description:  获取颜色  
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-7-20 下午7:06:10  
     * @version 1.0.0 
     * @param @param imgpath
     * @param @return
     * @param @throws IOException
     * @return String  返回类型 
     * @throws
     */
    private String getColor(String imgpath) throws IOException{
    	int[] rgb = new int[3];
        //new一个URL对象  
        URL url = new URL(imgpath);  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File("temp1.jpg");  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();   
    	BufferedImage bi = ImageIO.read(imageFile);; 
    	int minx=bi.getMinX(); 
    	int miny=bi.getMinY(); 
    	int pixel=bi.getRGB(minx+2, miny+2); 
    	rgb[0] = (pixel & 0xff0000 ) >> 16 ; 
    	rgb[1] = (pixel & 0xff00 ) >> 8 ; 
    	rgb[2] = (pixel & 0xff ); 
    	imageFile.delete();
    	return "rgb("+rgb[0]+"," + rgb[1] + "," + rgb[2] + ")";
    }
    
    private  byte[] readInputStream(InputStream inStream){  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        try {
			while( (len=inStream.read(buffer)) != -1 ){  
			    //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
			    outStream.write(buffer, 0, len);  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    
    @RequestMapping("/img2base64")
    @ResponseBody 
    public String img2base64(HttpServletRequest request) {
        logger.info("start[AdController.img2base64]");
    	String url = request.getParameter("url");
    	String base64 = Image2Base64Utils.encodeImgageToBase64(url);
        logger.info("end[AdController.img2base64]");
    	return "data:image/jpg;base64,"+base64;
    }
    
    /**
     * @Title:  addAd  
     * @Description:  初始化该广告配置
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-6 下午1:18:39  
     * @version 1.0.0 
     * @param @param request
     * @param @param adTypeConfig
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/newAd")
    @ResponseBody 
    public Object addAd(HttpServletRequest request,Ad ad) {
    	logger.info("start[AdController.addAd]");
    	HeadObject head = CommonHeadUtil.geneHeadObject(request, "990701-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try {
			resultObject = otherService.doService(new RequestObject(head, ad));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
    	logger.info("end[AdController.addAd]");
    	return resultObject;
    }
    
    /**
     * @Title:  editAdConfig  
     * @Description:  编辑广告配置
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-7 下午1:53:10  
     * @version 1.0.0 
     * @param @param request
     * @param @param adNum
     * @param @param config
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/editAdConfig")
    @ResponseBody 
    public Object editAdConfig(HttpServletRequest request,Ad ad) {
    	logger.info("start[AdController.editAdConfig]");
    	HeadObject head = CommonHeadUtil.geneHeadObject(request, "990701-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try {
			resultObject = otherService.doService(new RequestObject(head, ad));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
    	logger.info("end[AdController.editAdConfig]");
    	return resultObject;
    }
    
    @RequestMapping("/deleteAd")
    @ResponseBody 
    public Object deleteAd(HttpServletRequest request){
    	logger.info("start[AdController.deleteAd]");
    	HeadObject head = CommonHeadUtil.geneHeadObject(request, "990701-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	String ids = request.getParameter("adIds");
    	try {
			resultObject = otherService.doService(new RequestObject(head, ids));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
    	logger.info("end[AdController.deleteAd]");
    	return resultObject;
    }
}
