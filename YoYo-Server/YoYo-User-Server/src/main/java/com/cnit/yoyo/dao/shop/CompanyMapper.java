package com.cnit.yoyo.dao.shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.goods.Company;
import com.cnit.yoyo.model.goods.CompanyAndStoreBean;
import com.cnit.yoyo.model.goods.CompanyWithBLOBs;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.model.shop.dto.ShopDetailInfoDTO;

public interface CompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_company
     *
     * @mbggenerated Mon Mar 09 14:02:09 CST 2015
     */
    int deleteByPrimaryKey(Long companyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_company
     *
     * @mbggenerated Mon Mar 09 14:02:09 CST 2015
     */
    int insert(CompanyWithBLOBs record);

   

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_company
     *
     * @mbggenerated Mon Mar 09 14:02:09 CST 2015
     */
    CompanyWithBLOBs selectByPrimaryKey(Long companyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_company
     *
     * @mbggenerated Mon Mar 09 14:02:09 CST 2015
     */
    int updateByPrimaryKeySelective(CompanyWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_company
     *
     * @mbggenerated Mon Mar 09 14:02:09 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(CompanyWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_company
     *
     * @mbggenerated Mon Mar 09 14:02:09 CST 2015
     */
    int updateByPrimaryKey(Company record);
    
    void updateShopById(CompanyDTO record);
   
    int insertSelective(CompanyDTO record);
    /**
     * 
     * @Title: findByIdcard 
     * @Description:閫氳繃娉曚汉id杩涜鏌ユ壘
     * @param @param idcard
     * @param @return    璁惧畾鏂囦欢 
     * @return int    杩斿洖绫诲瀷 
     * @throws
     */
    int findByIdcard(CompanyDTO record);
    
    /**
     * 
     * @Title: findById 
     * @Description: 閫氳繃搴楅摵id鏌ユ壘搴楅摵淇℃伅 
     * @param @param companyId
     * @param @return    璁惧畾鏂囦欢 
     * @author xiaox
     * @date 2015-3-19 涓嬪崍5:18:26
     */
    CompanyDTO findById(Long companyId);
    
    
    void updateShopInfoById(CompanyWithBLOBs company);
    
    ShopGrade findGradeById(@Param("companyId")Long companyId);
    
    CompanyAndStoreBean selectCompanyAndStoreById(@Param("companyId")Long companyId);
    
    List<CompanyWithBLOBs> findCompanyByGradeId(Integer [] shopGradeIds);
    
    List<Company> findSelect();
    
    List<CompanyDTO> findShopListByParam(CompanyDTO dto);
    
    CompanyDTO findShopDetailByCompanyId(@Param("companyId")Integer comanyId);
    
    void updateExperienceAndEarnest(CompanyDTO dto);
    
    void updateShop(Map map);
    
    void deleteShop(Map map);
    
    List<Store> selectStore(CompanyDTO dto);
    void updateShopTime(Map map);
    //鏇存柊搴楅摵鐨勫晢鍝佹槸鍚﹂渶瑕佸鏍哥殑鐘舵�
    void updateShopGoodCheck(Map<String,Object> map);
    
    CompanyDTO findCheckById(@Param("companyId")Long companyId);
    
    int findStoreForbidden(@Param("companyId")Long companyId );
    
    void updateShopAllInfo(CompanyDTO dto);

    
    /**
     * @Title:  decreEarnestById  
     * @Description:  TODO(鏍规嵁闆嗗洟id鎵ｉ櫎淇濊瘉閲�  
     * @author <a href="cmlai@chinacnit.com">璧栧僵濡�/a> 
     * @date 2015-6-26 涓嬪崍6:48:57  
     * @version 1.0.0 
     * @param @param companyId
     * @param @param earnest
     * @param @return
     * @return int  杩斿洖绫诲瀷 
     * @throws
     */
    int decreEarnestById(@Param("companyId")Long companyId,@Param("earnest")Double earnest);

    
    int findEnabled(@Param("companyId")Integer companyId);
    
    int findByAccountId(@Param("accountId")Integer accountId);
    
    ShopDetailInfoDTO findShopDetail(Long companyId);

}