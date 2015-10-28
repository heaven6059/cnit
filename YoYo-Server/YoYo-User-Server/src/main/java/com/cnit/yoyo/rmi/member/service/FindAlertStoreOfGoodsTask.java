package com.cnit.yoyo.rmi.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.email.EmailService;
import com.cnit.yoyo.util.email.TempalteEmailObject;



/**
 * 定时任务的去查找库存预警的产品，并发邮件通知店主
 */
@Service
public class FindAlertStoreOfGoodsTask {

	public static final Logger log = LoggerFactory.getLogger(FindAlertStoreOfGoodsTask.class);

    @Autowired
    private MemberMapper memberMapper;
    
    @Autowired
    private EmailService emailService;
	
	/**
	 * 查找库存预警的商品，通知相应的卖家
	 * @Description:
	 * @param param
	 */
	public void dealAlertStore(Object data) {
		long start = System.currentTimeMillis();
		try {
			List<String> emails = memberMapper.findAlertEmail();
			if(null != emails && emails.size() > 0) {
				for(int i=0;i<emails.size();i++) {
					if(StringUtil.isEmpty(emails.get(i))) {
						emails.remove(i);
					}
				}
				TempalteEmailObject emailObject=new TempalteEmailObject();
	            emailObject.setSubject("库存预警");
	            emailObject.setTemplateName("alertemail.ftl");
	            emailService.sendGroupTemplateMail(emailObject,emails);
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("库存预警处理时间：-->> " + (end - start) + " 毫秒");
	}
	

}
