package com.cnit.yoyo.rmi.article.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.article.ArticleIndexMapper;
import com.cnit.yoyo.dao.article.ArticleNodesMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.article.ArticleNodesWithBLOBs;
import com.cnit.yoyo.model.article.dto.NodesDto;

@Service("articleNodesService")
public class ArticleNodesService {

    private static final Log log = LogFactory.getLog(ArticleNodesService.class);

    @Autowired
    private ArticleNodesMapper articleNodesMapper;

    @Autowired
    private ArticleIndexMapper articleIndexMapper;

    /**
     * @description <查询文章节点列表>
     * @detail <该方法将有效的文章节点全部查询出来,并查出其后代>
     * @author <a href="jpzhou@cnit.com">周加平</a>
     * @version 1.0.0
     * @data 2015年7月13日
     * @param data
     *        null
     * @return
     */
    public Object selectNodes(Object data) {
        HeadObject head = null;
        List<ArticleNodesWithBLOBs> list = null;
        try {
            log.info("start[ArticleNodesService.selectNodes]");
            head = new HeadObject(ErrorCode.SUCCESS);
            list = new ArrayList<ArticleNodesWithBLOBs>();
            Integer parentId = 0;
            List<ArticleNodesWithBLOBs> nodes = null;
            nodes = this.articleNodesMapper.selectByParentId(parentId, 0);
            NodesDto node = null;
            for (ArticleNodesWithBLOBs t : nodes) {
                list.add(t);
                geneNodesList(t);
            }
        } catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
            head.setRetMsg("获取文章节点列表异常");
            log.error("获取文章节点列表异常", e);
        }
        log.info("end[ArticleNodesService.selectNodes]");
        return new ResultObject(head, list);
    }

    /**
     * @description <设置节点的后代节点>
     * @detail <将指定节点的子节点查询出来，并赋给该节点的children属性，该方法进行递归调用>
     * @author <a href="jpzhou@cnit.com">周加平</a>
     * @version 1.0.0
     * @data 2015年7月14日
     * @param list
     */
    private void geneNodesList(ArticleNodesWithBLOBs node) {
        if (node.getHasChildren() == 0) {
            return;
        }
        Integer parentId = node.getNodeId();
        List<ArticleNodesWithBLOBs> nodes = new ArrayList<ArticleNodesWithBLOBs>();
        if(node.getNodeDepth() >= 2){
            nodes = this.articleNodesMapper.selectNodesWithArticle(parentId);
        }else {
            nodes = this.articleNodesMapper.selectByParentId(parentId, 0);
        }
        node.setChildren(nodes);
        NodesDto child = null;
        for (ArticleNodesWithBLOBs t : nodes) {
            geneNodesList(t);
        }
    }

    /**
     * @Title: selectByParentId
     * @Description: TODO(根据父节点id查询对象)
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
     * @date 2015-6-18 下午2:29:18
     * @version 1.0.0
     * @param @param data
     * @param @return
     * @return Object 返回类型
     * @throws
     */
    public Object selectByParentId(Object data) {
        log.info("start[ArticleNodesService.selectByParentId]");
        List<ArticleNodesWithBLOBs> list = articleNodesMapper.selectByParentId((Integer) data, 0);
        log.info("end[ArticleNodesService.selectByParentId]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONArray.toJSON(list));
    }

    /**
     * @Title: saveOrUpdate
     * @Description: TODO(保存或更新对象)
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
     * @date 2015-6-18 下午7:07:21
     * @version 1.0.0
     * @param @param data
     * @param @return
     * @param @throws Exception
     * @return Object 返回类型
     * @throws
     */
	public Object saveOrUpdate(Object data) throws Exception {
		log.info("start[ArticleNodesService.saveOrUpdate]");
		ArticleNodesWithBLOBs an = (ArticleNodesWithBLOBs) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(data),ArticleNodesWithBLOBs.class);
		ArticleNodesWithBLOBs parentAn = null;
		if (an.getParentId() != null && an.getParentId() != 0) {
			parentAn = articleNodesMapper.selectByPrimaryKey(an.getParentId());// 父级（用户选择的）
			if (parentAn != null && parentAn.getDisabled() != 1) {
				if (parentAn.getNodePath() != null && an.getNodeId() != null
						&& an.getNodeId() != 0) {
					if (parentAn.getNodePath().indexOf(
							an.getNodeId().toString()) >= 0) {
						return new HeadObject(ErrorCode.IS_EXIST_CASCADE);
					}
				}
			} else {
				an.setParentId(0);
			}
		}
		// 查询同名对象
		List<ArticleNodesWithBLOBs> temp = articleNodesMapper.selectByNodeName( an.getNodeName(), 0);

		if (an.getNodeId() != null && an.getNodeId() != 0) {// 修改对象
			if (temp != null && temp.size() > 0 && (!temp.get(0).getNodeId().equals(an.getNodeId()))) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			// 查询更新前的对象
			ArticleNodesWithBLOBs anFromDB = articleNodesMapper.selectByPrimaryKey(an.getNodeId());// 当前
			if (anFromDB != null && anFromDB.getDisabled() != 1) {
				if (an.getParentId() != null && an.getParentId() != 0 && parentAn != null && parentAn.getDisabled() != 1) {
					int count = articleNodesMapper.selectCountByParentId(an.getNodeId(), 0);
					an.setNodePath(parentAn.getNodePath() + parentAn.getNodeId() + ",");
					an.setNodeDepth(parentAn.getNodeDepth() + 1);
					if (count >= 1) {
						// 如果是第两级则还要修改子节点的NodePath
						an.setUptime(new Date(System.currentTimeMillis()));
						articleNodesMapper.updateByPrimaryKeySelective(an);
						articleNodesMapper.updateSubNodePath(an.getNodeId());
					}
				} else {
					an.setNodePath(",");
					an.setNodeDepth(1);
					an.setUptime(new Date(System.currentTimeMillis()));
					articleNodesMapper.updateByPrimaryKeySelective(an);
				}

				if (!an.getParentId().equals(anFromDB.getParentId())) {// 父级是否发生变化
					// 更新原来父级子类个数
					if (null != anFromDB.getParentId() && anFromDB.getParentId() > 0) {
						ArticleNodesWithBLOBs updateParent = articleNodesMapper.selectByPrimaryKey(anFromDB.getParentId());
						if (updateParent != null && updateParent.getNodeId() > 0 && updateParent.getHasChildren() != 0) {
							int count = articleNodesMapper .selectCountByParentId(updateParent.getNodeId(), 0);
							if (count == 0) {
								updateParent.setHasChildren(0);
								articleNodesMapper.updateByPrimaryKeyWithBLOBs(updateParent);
							}
						}
					}
					// 更新用户选择父类子类个数
					if (null != an.getParentId() && an.getParentId() > 0) {
						ArticleNodesWithBLOBs updateParent = new ArticleNodesWithBLOBs();
						updateParent.setNodeId(an.getParentId());
						updateParent.setHasChildren(1);
						articleNodesMapper.updateByPrimaryKeySelective(updateParent);
					}
				}else{
					articleNodesMapper.updateByPrimaryKeySelective(an);
				}
			} else {
				return new HeadObject(ErrorCode.NO_DATA);
			}
		} else {// 新增对象
			if (temp != null && temp.size() > 0) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			if (an.getParentId() == null || an.getParentId() <= 0) {
				an.setParentId(0);
				an.setNodePath(",");
				int count = articleNodesMapper .selectCountByParentId(0, 0);
				if(count >= 4){
					return new HeadObject("000005");
				}
			} else {
				an.setNodePath(parentAn.getNodePath() + parentAn.getNodeId() + ",");
			}
			an.setNodeDepth(parentAn != null ? parentAn.getNodeDepth() + 1 : 1);
			an.setUptime(new Date(System.currentTimeMillis()));
			an.setHasChildren(0);
			an.setContent("");
			an.setDisabled(0);
			an.setListTmplPath("0");
			articleNodesMapper.insertSelective(an);
			if (an.getParentId() != null && an.getParentId() > 0) {
				// 更新用户选择父类子类个数
				ArticleNodesWithBLOBs updateParent = new ArticleNodesWithBLOBs();
				updateParent.setNodeId(parentAn.getNodeId());
				updateParent.setHasChildren(1);
				articleNodesMapper.updateByPrimaryKeySelective(updateParent);
			}
		}
		log.info("end[ArticleNodesService.saveOrUpdate]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

    /**
     * @Title: selectByNodeId
     * @Description: TODO(根据主键查询对象)
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
     * @date 2015-6-18 下午2:28:32
     * @version 1.0.0
     * @param @param data
     * @param @return
     * @return Object 返回类型
     * @throws
     */
    public Object selectByNodeId(Object data) {
        log.info("start[ArticleNodesService.selectByNodeId]");
        ArticleNodesWithBLOBs articleNodes = articleNodesMapper.selectByNodeId((Integer) data, 0);
        log.info("end[ArticleNodesService.selectByNodeId]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(articleNodes));
    }

    /**
     * @Title: ifPub
     * @Description: TODO(更新发布状态)
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
     * @date 2015-6-24 下午6:32:19
     * @version 1.0.0
     * @param @param data
     * @param @return
     * @return Object 返回类型
     * @throws
     */
    public Object ifPub(Object data) {
        log.info("start[ArticleNodesService.ifPub]");
        JSONObject jsonObject = JSONObject.fromObject(data);
        Integer nodeId = jsonObject.getInt("nodeId");
        Integer ifPub = jsonObject.getInt("ifPub");
        if (nodeId != null && nodeId != 0 && ifPub != null) {
            ArticleNodesWithBLOBs an = articleNodesMapper.selectByPrimaryKey(nodeId);
            if (an != null && an.getDisabled() != 1) {
                if (ifPub == 0) {// 禁用，同时禁用子类
                    articleNodesMapper.updateifPubByPath(ifPub, an.getNodePath());
                    // 返回更新后的对象
                } else if (ifPub == 1) {// 启用，查看父类是否启用
                    if (an.getParentId() != null && an.getParentId() != 0) {
                        // 查询父类，若存在禁用，则操作失败
                        if (an.getNodePath() != null && !",".equals(an.getNodePath()) && an.getNodePath().length() >= 2) {
                            System.out.println("articleNodesService....nodePath..." + an.getNodePath());
                            List<Integer> nodeIdList = (List<Integer>) JSONArray.toCollection(
                                    JSONArray.fromObject("[" + an.getNodePath().substring(1, an.getNodePath().length() - 1) + "]"), Integer.class);
                            System.out.println("articleNodesService....nodeIdList..." + nodeIdList.toString());
                            if (nodeIdList != null && nodeIdList.size() >= 1) {
                                List<ArticleNodesWithBLOBs> nodeList = articleNodesMapper.selectByNodeIds(nodeIdList);
                                if (nodeList != null && nodeList.size() >= 1) {
                                    Integer ifPublish = 1;
                                    for (int i = 0; i < nodeList.size(); i++) {
                                        if (nodeList.get(i).getIfpub() == 0) {
                                            ifPublish = 0;
                                            break;
                                        }
                                    }
                                    if (ifPublish == 0) {// 存在父级禁用
                                        return new HeadObject(ErrorCode.IS_EXIST_CASCADE);
                                    } else {
                                        articleNodesMapper.updateifPubByNodeId(ifPub, nodeId);
                                        // 返回更新后的对象
                                    }
                                } else {
                                    // nodePath 对象不存在
                                    return new HeadObject(ErrorCode.UNKOWN);
                                }
                            } else {
                                // nodePath idlist 不存在，不太可能
                                return new HeadObject(ErrorCode.UNKOWN);
                            }
                        } else {
                            // parentId不为0，nodePath为空,数据有误
                            return new HeadObject(ErrorCode.UNKOWN);
                        }
                    } else {
                        articleNodesMapper.updateifPubByNodeId(ifPub, nodeId);
                        // 返回更新后的对象
                    }
                }
            } else {
                return new HeadObject(ErrorCode.NO_DATA);
            }
        } else {
            // 不响应
            return new HeadObject(ErrorCode.SUCCESS);
        }
        log.info("end[ArticleNodesService.ifPub]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), articleNodesMapper.selectByPrimaryKey(nodeId));
        // return new HeadObject(ErrorCode.SUCCESS);
    }

    /**
     * @Title: delectByNodeId
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
     * @date 2015-7-3 上午11:36:33
     * @version 1.0.0
     * @param @param data
     * @param @return
     * @return Object 返回类型
     * @throws
     */
    public Object delectById(Object data) {
        log.info("start[ArticleNodesService.delectById]");
        Integer nodeId = (Integer) data;
        if (nodeId != null && nodeId != 0) {
            ArticleNodesWithBLOBs articleNodes = articleNodesMapper.selectByPrimaryKey(nodeId);
            if (articleNodes != null && articleNodes.getDisabled() == 0) {
                if (articleNodes.getDeletable() == 1) {
                    List<ArticleNodesWithBLOBs> childList = articleNodesMapper.selectByParentId(articleNodes.getNodeId(), 0);
                    if (childList != null && childList.size() >= 1) {
                        return new HeadObject(ErrorCode.IS_EXIST_CASCADE);
                    } else {
                        articleNodesMapper.updateDisabledById(articleNodes.getNodeId(), 1);
                        // 更新父级
                        if (articleNodes.getParentId() != null && articleNodes.getParentId() != 0) {
                            ArticleNodesWithBLOBs updateParent = new ArticleNodesWithBLOBs();
                            updateParent.setNodeId(articleNodes.getParentId());
                            int count = articleNodesMapper.selectCountByParentId(articleNodes.getParentId(), 0);
                            if (count >= 1) {
                                updateParent.setHasChildren(1);
                            } else {
                                updateParent.setHasChildren(0);
                            }
                            articleNodesMapper.updateByPrimaryKeySelective(updateParent);
                        }
                        // 删除文章
                        articleIndexMapper.updateDisabledByNodeId(nodeId, 1);
                    }
                } else {
                    // 该栏目不可删
                    return new HeadObject(ErrorCode.UNKOWN);
                }
            } else {
                // 该栏目不存在或已被删除
            }
        }
        log.info("end[ArticleNodesService.delectById]");
        return new HeadObject(ErrorCode.SUCCESS);
    }

    /**
     * @Title:  selectNodesWithLevel  
     * @Description:  一次性查询指定等级 的节点
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-7-21 下午4:40:09  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
	public Object selectNodesWithLevel(Object data) {
        log.info("start[ArticleNodesService.delectById]");
        HeadObject head = new HeadObject();
        ResultObject object =  new ResultObject();
        List<ArticleNodesWithBLOBs>  list = new ArrayList<ArticleNodesWithBLOBs>();
    	try{
    		list = articleNodesMapper.selectNodesWithArticle(null);
    		object.setContent(list);
    	}catch (Exception e){
    		e.printStackTrace();
    		log.equals(e.toString());
    		head.setRetCode(ErrorCode.FAILURE);
    	}
		head.setRetCode(ErrorCode.SUCCESS);
        object.setHead(head);
        log.info("end[ArticleNodesService.delectById]");
        return object;
    }
}
