/**
 * 文 件 名   :  PaintingController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="zjcai@chinacnit.com">蔡志杰</a>
 * 创建时间  :  2015-5-20 下午6:47:10
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.painting.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.model.painting.CarDamageOfferDetail;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.painting.dto.CarDamageQueryDTO;
import com.cnit.yoyo.model.painting.dto.PaintingOrderDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 *@description 钣金喷漆后台管理
 *@detail <功能详细描述>
 *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/painting")
public class PaintingControllerMP 
{
    public static final Logger logger = LoggerFactory.getLogger(PaintingControllerMP.class);
    public static final String TEMPLATFOLDER ="resources\\excel\\template\\";
    
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    
    
    @Autowired
    public RemoteService otherService;
    @Autowired
    public HessianInerface imagesService;

    @RequestMapping("/index")
    public String index() 
    {
        return "/pages/biz/painting/paintingAudit";
    }
    
    @RequestMapping("/damageList")
    @ResponseBody
    public Object getDamageList(HttpServletRequest request, CarDamageQueryDTO dto) {
        logger.info("###########YOYOMP-->PaintingController.getDamageList----->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-03", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject,dto));
//            request.setAttribute("damageList", resultObject.getContent());
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMP-->PaintingController.getDamageList----->end");
        return resultObject;
    }
    
    @RequestMapping("/updatePassStatus")
    @ResponseBody
    public Object modifyPassStatus(HttpServletRequest request, String id, String operation, String reason){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("id", id);
    	map.put("passStatus", operation);
    	map.put("reason", reason);
    	
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-06", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject,map));
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    	return resultObject;
    }
    
    @RequestMapping("/orderList")
    public String toOrderList(){
        return "/pages/biz/painting/orderList";
    }
    
    @RequestMapping("/list")
    public Object getOrderList(HttpServletRequest request, PaintingOrderDTO dto){
        logger.info("###########YOYOMP-->PaintingControllerMP.getOrderList----->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-11", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        if(dto.getMaxPayed() !=null && dto.getMinPayed() !=null && dto.getMaxPayed().compareTo(dto.getMinPayed()) <0 ){
        	BigDecimal temp = dto.getMaxPayed();
        	dto.setMaxPayed(dto.getMinPayed());
        	dto.setMinPayed(temp);
        }
        if(dto.getEndDate() !=null && dto.getStartDate() !=null && dto.getEndDate().before(dto.getStartDate())){
        	Date temp = dto.getEndDate();
        	dto.setEndDate(dto.getStartDate());
        	dto.setStartDate(temp);
        }
		try {
			resultObject = otherService.doService(new RequestObject(headObject, dto));
		} catch (Exception e) {
            logger.error(e.getMessage());
			e.printStackTrace();
		}
        logger.info("###########YOYOMP-->PaintingControllerMP.getOrderList----->end");
        System.out.println(JSONObject.fromObject(resultObject));
        return resultObject;
    }

    @RequestMapping("/orderDetailTab")
    public String orderDetailTab(HttpServletRequest request, Long orderId){
        logger.info("###########YOYOMP-->PaintingControllerMP.orderDetailTab----->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
		try {
			resultObject = otherService.doService(new RequestObject(headObject, orderId));
			request.setAttribute("order", resultObject.getContent());
		} catch (Exception e) {
            logger.error(e.getMessage());
			e.printStackTrace();
		}
        logger.info("###########YOYOMP-->PaintingControllerMP.orderDetailTab----->end");
        System.out.println(JSONObject.fromObject(resultObject));
        return "/pages/biz/painting/orderDetail";
    }
   
	 /**
     * 钣金订单导出
     * @param request
     * @param response
     * @throws IOException 
     */
	@RequestMapping("/exportOrder")
    @ResponseBody
	public void exportOrder(HttpServletRequest request, PaintingOrderDTO dto,HttpServletResponse response){
    	String templateName = "钣金订单模板.xlsx";
        String path = request.getSession().getServletContext().getRealPath("/") + TEMPLATFOLDER + templateName;
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			in = new FileInputStream(new File(path));
			wb = new XSSFWorkbook(in);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		String arr = (String) getExportOrderList(request,dto);
		List<PaintingOrders> dataList = JSON.parseObject(arr, new TypeReference<List<PaintingOrders>>() {});

		Resource resource = new ClassPathResource("tmpPath.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String docsPath = props.getProperty("paintingorder.path");
		File outPath = new File(docsPath);
		if(!outPath.isDirectory()){
			outPath.mkdirs();
		}
		String fileName = System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + fileName;
		
		//数据 list
		try {
			// 输出流
			OutputStream os = new FileOutputStream(filePath);
			// 工作区
			XSSFSheet sheet = wb.getSheetAt(0);
			
			for(int i=0; i<dataList.size(); i++){
				// 创建第一个sheet
				// 生成第一行
				XSSFRow row = sheet.createRow(2+i);
				
				row.createCell(0).setCellValue(format(dataList.get(i).getId(),"long"));
				row.createCell(1).setCellValue(format(dataList.get(i).getCreatetime(),"date"));
				row.createCell(2).setCellValue(dataList.get(i).getPayed()+"");
				row.createCell(3).setCellValue(dataList.get(i).getStatus());
				row.createCell(4).setCellValue(format(dataList.get(i).getPayStatus(), "payStatus"));
				row.createCell(5).setCellValue(format(dataList.get(i).getLastModified(),"date"));
				row.createCell(6).setCellValue(dataList.get(i).getPamAccount().getLoginName());
				row.createCell(7).setCellValue(dataList.get(i).getName());
				row.createCell(8).setCellValue(dataList.get(i).getPhone());
				row.createCell(9).setCellValue(dataList.get(i).getMarkText());
				row.createCell(10).setCellValue(dataList.get(i).getStoreId());
				row.createCell(11).setCellValue(dataList.get(i).getStore().getStoreName());
				row.createCell(12).setCellValue(dataList.get(i).getStore().getArea()+ " " + dataList.get(i).getStore().getAddr());
				row.createCell(13).setCellValue(dataList.get(i).getStore().getTel());
				List<CarDamageOfferDetail> list = dataList.get(i).getDamageOfferList();
				for(int j=0; j<list.size(); j++){
					CarDamageOfferDetail carDamageOfferDetail = list.get(j);
					row.createCell(14).setCellValue(carDamageOfferDetail.getCarDamageDetail().getCarPart().getPartName());
					row.createCell(15).setCellValue(carDamageOfferDetail.getOfferPrice()+"");
				}
			}
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		download(filePath, response);
	}
	
	/**
	 * 非分页获得导出订单列表
	 * @param request
	 * @param dto
	 * @return
	 */
	public Object getExportOrderList(HttpServletRequest request, PaintingOrderDTO dto){
		logger.info("###########YOYOMP-->PaintingControllerMP.getExportOrderList----->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-16", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		if(dto.getMaxPayed() !=null && dto.getMinPayed() !=null && dto.getMaxPayed().compareTo(dto.getMinPayed()) <0 ){
			BigDecimal temp = dto.getMaxPayed();
			dto.setMaxPayed(dto.getMinPayed());
			dto.setMinPayed(temp);
		}
		if(dto.getEndDate() !=null && dto.getStartDate() !=null && dto.getEndDate().before(dto.getStartDate())){
			Date temp = dto.getEndDate();
			dto.setEndDate(dto.getStartDate());
			dto.setStartDate(temp);
		}
		try {
			resultObject = otherService.doService(new RequestObject(headObject, dto));
		} catch (Exception e) {
		    logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("###########-->PaintingControllerMP.getExportOrderList----->end");
		System.out.println(JSONObject.fromObject(resultObject));
		return resultObject.getContent();
	}
	
	/**
	 * 弹出下载框
	 * @param path
	 * @param response
	 */
	private void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * 字段转换
	 * @param source
	 * @param type
	 * @return
	 */
	private String format(Object source, String type){
		String formated = "";
		if("date".equals(type)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
            formated = sdf.format((Date)source);
		}else if("payStatus".equals(type)){
			switch((String)source){
				case "0": formated = "未支付"; break; 
				case "1": formated = "已支付"; break; 
				case "2": formated = "已付款至担保方"; break; 
				case "3": formated = "部分付款"; break; 
				case "4": formated = "部分退款"; break; 
				case "5": formated = "全额退款"; break; 
			}
		}else if("long".equals(type)){
			DecimalFormat df = new DecimalFormat("0");
			formated = df.format((Long)source);
		}
		return formated;
	}
}
