package com.cnit.yoyo.dao.requirement;

import java.util.List;

import com.cnit.yoyo.model.requirement.PostRequirement;
import com.cnit.yoyo.model.requirement.dto.PostRequirementDTO;

public interface PostRequirementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_post_requirement
     *
     * @mbggenerated Tue Jun 16 15:52:19 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_post_requirement
     *
     * @mbggenerated Tue Jun 16 15:52:19 CST 2015
     */
    int insert(PostRequirement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_post_requirement
     *
     * @mbggenerated Tue Jun 16 15:52:19 CST 2015
     */
    int insertSelective(PostRequirement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_post_requirement
     *
     * @mbggenerated Tue Jun 16 15:52:19 CST 2015
     */
    PostRequirement selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_post_requirement
     *
     * @mbggenerated Tue Jun 16 15:52:19 CST 2015
     */
    int updateByPrimaryKeySelective(PostRequirement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_post_requirement
     *
     * @mbggenerated Tue Jun 16 15:52:19 CST 2015
     */
    int updateByPrimaryKey(PostRequirement record);
    
    List<PostRequirement> selectList(PostRequirementDTO record);
    
    List<Object> selectIdList(PostRequirementDTO record);
    List<Object> selectMyList(PostRequirementDTO record);
}