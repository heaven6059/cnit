package com.cnit.yoyo.search.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.index.model.ResultContent;
import com.cnit.yoyo.model.activity.Coupons;
import com.cnit.yoyo.model.goods.AttributeGoodsIndex;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.goods.GoodsVirtualCat;
import com.cnit.yoyo.model.goods.dto.GoodAttributeValueDTO;
import com.cnit.yoyo.model.goods.dto.GoodsInfoListDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.search.model.SearchGoods;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.ElasticSearchClient;
import com.cnit.yoyo.util.domain.ResultPage;

/**
 * 商品搜索服务
 * 
 * @author wanghb
 * @version 1.0.0
 * @copyright CNIT
 */
@Service
public class SearchService {

	public static final Logger log = LoggerFactory.getLogger(SearchService.class);

	@Autowired
	private RemoteService itemService;
	@Autowired
	private ElasticSearchClient elasticSearchClient;

	private static final String INDEXNAME = "products";// 索引库
	private static final String INDEXTYPE = "productList";// 索引类型
	private static final String INDEXTYPE_ATTR = "productAttr";// 索引类型扩展属性
	private static final String INDEXTYPE_COUPONS = "productCoupons_t";// 索引类型优惠券
	/**
	 * 全量创建索引商品和扩展属性
	 * @Description:
	 * @param param
	 */
	@SuppressWarnings("unchecked")
	public void builderSearchIndex(Object param) {
		long start = System.currentTimeMillis();
		try {
			// 如果索引存在,删除索引
			this.deleteIndex(null);
			// 商品基本信息
			HeadObject headObject = CommonHeadUtil.geneHeadObject("goodsService.getGoodsByParam");
			QueryParamObject paramObject = null;
			JSONObject content = new JSONObject();

			net.sf.json.JSONArray equals = new net.sf.json.JSONArray();
			JSONObject disabled = new JSONObject();
			disabled.put("paramName", "A.disabledQ");
			disabled.put("paramValue", '0');
			equals.add(disabled);

			JSONObject equal = new JSONObject();
			equal.put("paramName", "A.marketableQ");
			equal.put("paramValue", '1');
			equals.add(equal);

			content.put("equals", equals);
			paramObject = (QueryParamObject) JSONObject.toBean(content, QueryParamObject.class);
			paramObject.setPage(1);
			paramObject.setRows(1000);
			Object resultObject = itemService.doServiceByServer(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
			ResultContent<GoodsInfoListDTO> page = com.alibaba.fastjson.JSONObject.parseObject(resultObject.toString(), ResultContent.class);
			List<GoodsInfoListDTO> list = com.alibaba.fastjson.JSONArray.parseArray(page.getContent().getRows().toString(), GoodsInfoListDTO.class);

			Client client = elasticSearchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (GoodsInfoListDTO goodsDto : list) {
				goodsDto.setPrices(goodsDto.getPrice().multiply(new BigDecimal(100)).longValue());
				IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE);
				indexRequest.setSource(com.alibaba.fastjson.JSONObject.toJSONString(goodsDto));
				bulkRequest.add(indexRequest);
			}
			// 扩展信息
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject("goodsService.selectAllAttr");
			ResultObject resultObject1 = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject1, 0));
			if (null != resultObject1) {
				JSONArray jsonarray = JSONArray.fromObject(resultObject1.getContent());
				List<GoodAttributeValueDTO> list1 = (List<GoodAttributeValueDTO>) JSONArray.toCollection(jsonarray, GoodAttributeValueDTO.class);
				if (null != list1 && list1.size() > 0) {
					for (GoodAttributeValueDTO goodAttributeValueDTO : list1) {
						IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE_ATTR);
						indexRequest.setSource(JSONObject.fromObject(goodAttributeValueDTO).toString());
						bulkRequest.add(indexRequest);
					}
				}
			}
			
			// 优惠券
			HeadObject headObject2 = CommonHeadUtil.geneHeadObject("couponsService.findListPage");
			JSONObject content1 = new JSONObject();
			content1.put("pageIndex", 1);
			content1.put("pageSize", 100000);
			Object resultObject2 = itemService.doServiceByServer(new RequestObject(headObject2, JSONObject.fromObject(content1)));
			if (null != resultObject2) {
				ResultContent<Coupons> page2 = com.alibaba.fastjson.JSONObject.parseObject(resultObject2.toString(), ResultContent.class);
				List<Coupons> list2 = com.alibaba.fastjson.JSONArray.parseArray(page2.getContent().getRows().toString(), Coupons.class);
				if (null != list2 && list2.size() > 0) {
					for (Coupons coupons : list2) {
						IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE_COUPONS);
						JsonConfig jsonConfig = new JsonConfig();
						jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
						jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
						JSONObject json = JSONObject.fromObject(coupons, jsonConfig);
						indexRequest.setSource(json.toString());
						bulkRequest.add(indexRequest);
					}
				}
			}
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				System.out.println(bulkResponse.buildFailureMessage());
			}
			client.close();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("创建索引时间:数据量是  ,共用时间 -->> " + (end - start) + " 毫秒");
	}

	/**
	 * 批量新增商品
	 * @param goodsId
	 */
	@SuppressWarnings("unchecked")
	public void addGoodsOneIndex(Object param) {
		List<GoodsInfoListDTO> list = com.alibaba.fastjson.JSONArray.parseArray(param.toString(), GoodsInfoListDTO.class);
		long start = System.currentTimeMillis();
		try {
			Client client = elasticSearchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (GoodsInfoListDTO goodsDto : list) {
				QueryBuilder query = QueryBuilders.matchQuery("goodsId", goodsDto.getGoodsId().toString());
				client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE).setQuery(query).execute().actionGet();
				goodsDto.setPrices(goodsDto.getPrice().multiply(new BigDecimal(100)).longValue());
				IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE);
				indexRequest.setSource(com.alibaba.fastjson.JSONObject.toJSONString(goodsDto));
				bulkRequest.add(indexRequest);
				// 扩展信息
				HeadObject headObject1 = CommonHeadUtil.geneHeadObject("goodsService.selectAllAttr");
				ResultObject resultObject1 = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject1, goodsDto.getGoodsId()));
				if (null != resultObject1) {
					JSONArray jsonarray = JSONArray.fromObject(resultObject1.getContent());
					List<GoodAttributeValueDTO> list1 = (List<GoodAttributeValueDTO>) JSONArray.toCollection(jsonarray, GoodAttributeValueDTO.class);
					if (null != list1 && list1.size() > 0) {
						for (GoodAttributeValueDTO goodAttributeValueDTO : list1) {
							IndexRequestBuilder indexRequest1 = client.prepareIndex(INDEXNAME, INDEXTYPE_ATTR);
							indexRequest1.setSource(JSONObject.fromObject(goodAttributeValueDTO).toString());
							bulkRequest.add(indexRequest1);
						}
					}
				}
			}
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				System.out.println(bulkResponse.buildFailureMessage());
			}
			client.close();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("批量新增索引:,共用时间 -->> " + (end - start) + " 毫秒");
	}
	/**
	 * 新增优惠券
	 * @Description: 
	 * @param param
	 */
	public void addCoupons(Object param) {
		long start = System.currentTimeMillis();
		Coupons coupons=JSON.parseObject(param.toString(), Coupons.class);
		try {
			Client client = elasticSearchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			QueryBuilder query = QueryBuilders.matchQuery("cpnsId", coupons.getCpnsId());
			client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE_COUPONS).setQuery(query).execute().actionGet();
			IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE_COUPONS);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
			jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
			JSONObject json = JSONObject.fromObject(coupons, jsonConfig);
			indexRequest.setSource(json.toString());
			bulkRequest.add(indexRequest);
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				System.out.println(bulkResponse.buildFailureMessage());
			}
			client.close();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("新增优惠券索引:,共用时间 -->> " + (end - start) + " 毫秒");
	}
	
	public void deleteCoupons(Object param) {
		long start = System.currentTimeMillis();
		Coupons coupons=JSON.parseObject(param.toString(), Coupons.class);
		try {
			Client client = elasticSearchClient.getClient();
			QueryBuilder query = QueryBuilders.matchQuery("cpnsId", coupons.getCpnsId());
			client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE_COUPONS).setQuery(query).execute().actionGet();
			client.close();
		} catch (Exception e) {
			log.error("删除优惠券索引:" + e);
		}
		long end = System.currentTimeMillis();
		log.info("删除优惠券索引:,共用时间 -->> " + (end - start) + " 毫秒");
	}
	/**
	 * 新增商品扩展属性
	 * @param goodsId
	 */
	public void addGoodsAttrIndex(Object param) {
		List<AttributeGoodsIndex> list = com.alibaba.fastjson.JSONArray.parseArray(param.toString(), AttributeGoodsIndex.class);
		long start = System.currentTimeMillis();
		try {
			Client client = elasticSearchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			if (null != list && list.size() > 0) {
				for (AttributeGoodsIndex attributeGoodsIndex : list) {
					IndexRequestBuilder indexRequest1 = client.prepareIndex(INDEXNAME, INDEXTYPE_ATTR);
					indexRequest1.setSource(JSONObject.fromObject(attributeGoodsIndex).toString());
					bulkRequest.add(indexRequest1);
				}
			}
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				System.out.println(bulkResponse.buildFailureMessage());
			}
			client.close();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("批量新增索引:,共用时间 -->> " + (end - start) + " 毫秒");
	}

	/**
	 * 全量删除索引
	 */
	public void deleteIndex(Object param) {
		long start = System.currentTimeMillis();
		try {
			Client client = elasticSearchClient.getClient();
			client.prepareDeleteByQuery(INDEXNAME).setQuery(QueryBuilders.termQuery("_type", INDEXTYPE)).execute().actionGet();
			client.prepareDeleteByQuery(INDEXNAME).setQuery(QueryBuilders.termQuery("_type", INDEXTYPE_ATTR)).execute().actionGet();
			client.prepareDeleteByQuery(INDEXNAME).setQuery(QueryBuilders.termQuery("_type", INDEXTYPE_COUPONS)).execute().actionGet();
			client.close();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("删除索引时间:,共用时间 -->> " + (end - start) + " 毫秒");
	}

	/**
	 * 根据商品ID删除
	 * 
	 * @param goodsId
	 */
	public void deleteIndexByGoodId(Object goodsId) {
		long start = System.currentTimeMillis();
		try {
			Client client = elasticSearchClient.getClient();
			QueryBuilder query = QueryBuilders.matchQuery("goodsId", goodsId.toString());
			client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE).setQuery(query).execute().actionGet();
			client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE_ATTR).setQuery(query).execute().actionGet();
			client.close();
		} catch (Exception e) {
			log.error("根据商品ID删除索引:" + goodsId + e);
		}
		long end = System.currentTimeMillis();
		log.info("根据商品ID删除索引时间:,共用时间 -->> " + (end - start) + " 毫秒");
	}

	/**
	 * 根据商品ID批量删除
	 * 
	 * @param goodsId
	 */
	public void deleteIndexByGoodIds(Object param) {
		long start = System.currentTimeMillis();
		try {
			List<Integer> list = com.alibaba.fastjson.JSONArray.parseArray(param.toString(), Integer.class);
			for (Integer goodsId : list) {
				this.deleteIndexByGoodId(goodsId);
			}
		} catch (Exception e) {
			log.error("根据商品ID批量删除索引:" + param + e);
		}
		long end = System.currentTimeMillis();
		log.info("根据商品ID删除索引时间:,共用时间 -->> " + (end - start) + " 毫秒");
	}

	/**
	 * 更新索引
	 * 
	 * @param request
	 */
	public void updateIndex(Object param) {
		long start = System.currentTimeMillis();
		try {
			SearchGoods searchGoods = (SearchGoods) JSONObject.toBean(JSONObject.fromObject(param), SearchGoods.class);
			Client client = elasticSearchClient.getClient();
			UpdateRequest updateRequest = new UpdateRequest();
			updateRequest.index(INDEXNAME);
			updateRequest.type(INDEXTYPE);
			updateRequest.id("AU1L-4HITNQvpxYZJB5s");
			XContentBuilder docBuilder = XContentFactory.jsonBuilder().startObject();
			if (null != searchGoods.getBrandId()) {
				docBuilder.field("brandId", searchGoods.getBrandId());
			}
			docBuilder.endObject();
			updateRequest.doc(docBuilder);
			client.update(updateRequest).get();
			client.close();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("删除索引时间:,共用时间 -->> " + (end - start) + " 毫秒");
	}
	private void buildCate(Set<Integer> cateList,String cateId){
		HeadObject headObject = CommonHeadUtil.geneHeadObject("categoryService.getVirtualCategoryByCatId");
		ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, Integer.parseInt(cateId.toString())));
		JSONObject jsonobject = JSONObject.fromObject(resultObject.getContent());
		JSONObject jsonobject1 = jsonobject.getJSONObject("cate");
		JSONObject jsonobject2 = jsonobject1.getJSONObject("filter");
		JSONArray jsonobject3 = jsonobject2.getJSONArray("ct");
		Object[] obj = jsonobject3.toArray();
		for (int i = 0; i < obj.length; i++) {
			JSONObject jsonobject4 = JSONObject.fromObject(obj[i]);
			cateList.add(jsonobject4.getInt("id"));
			// TODO：待优化
			HeadObject headObjectSub = CommonHeadUtil.geneHeadObject("categoryService.getCategoryByExample");
			JSONObject params2 = new JSONObject();
			params2.put("parentCatId", jsonobject4.getInt("id"));
			params2.put("disabled", "0");
			ResultObject resultObjectSub = (ResultObject) itemService.doServiceByServer(new RequestObject(headObjectSub, params2));
			List<GoodsCat> listSub = com.alibaba.fastjson.JSONArray.parseArray(resultObjectSub.getContent().toString(), GoodsCat.class);
			if (null != listSub && listSub.size() > 0) {
				for (int j = 0; j < listSub.size(); j++) {
					cateList.add(listSub.get(j).getCatId());
				}
			}
		}
	}
	/**
	 * 关键字搜索
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object searchKey(Object param) {
		try {
			Map<String, Object> params = (Map<String, Object>) param;
			int currrentPage = (Integer) params.get("currrentPage");
			int pageSize = (Integer) params.get("pageSize");
			Object orderFile = params.get("orderFile");
			Object order = params.get("order");
			Object attrId = params.get("aId");
			Object atv = params.get("atv");
			Object brandId = params.get("brandId");
			Object cateId = params.get("cateId");// 虚拟分类
			Object cId = params.get("cId");// 分类
			Object store = params.get("store");
			Object goodsKind = params.get("goodsKind");

			long start = System.currentTimeMillis();
			Client client = elasticSearchClient.getClient();
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEXNAME).setTypes(INDEXTYPE);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (params.get("searchKey") != null && (!params.get("searchKey").equals("搜索关键字..."))) {
				String searchKey = params.get("searchKey").toString();
				if (StringUtils.isNotBlank(searchKey)) {
					QueryBuilder keyBuilder = QueryBuilders.queryString(searchKey);
					boolQueryBuilder.must(keyBuilder);
				}
			}

			Set<Integer> cateList = new HashSet<Integer>();// 分类集合
			if (null != cateId) {// 虚拟分类
				this.buildCate(cateList, cateId.toString());
				HeadObject parentVirtualHeadObject = CommonHeadUtil.geneHeadObject("categoryService.getVirtualCategoryByExample");
				JSONObject paramsVirtual = new JSONObject();
				paramsVirtual.put("parentCatId", Integer.parseInt(cateId.toString()));
		        ResultObject paramsVirtualResult =(ResultObject)itemService.doServiceByServer(new RequestObject(parentVirtualHeadObject, paramsVirtual));
		        List<GoodsVirtualCat> subVirtualCatList=com.alibaba.fastjson.JSONArray.parseArray(paramsVirtualResult.getContent().toString(),GoodsVirtualCat.class);
		        if(null!=subVirtualCatList && subVirtualCatList.size()>0){
		    	   for(GoodsVirtualCat virtualCat:subVirtualCatList){
		    		   this.buildCate(cateList, virtualCat.getCatId().toString());
		    	   }
		        }
				boolQueryBuilder.must(QueryBuilders.inQuery("catId", cateList));
			}
			if (null != cId) {// 查询子分类
				cateList.add(Integer.parseInt(cId.toString()));
				HeadObject headObject = CommonHeadUtil.geneHeadObject("categoryService.getCategoryByExample");
				JSONObject params1 = new JSONObject();
				params1.put("parentCatId", Integer.parseInt(cId.toString()));
				params1.put("disabled", "0");
				ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, params1));
				List<GoodsCat> list = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), GoodsCat.class);
				for (int i = 0; i < list.size(); i++) {
					GoodsCat cat = list.get(i);
					cateList.add(cat.getCatId());
					// TODO：待优化
					HeadObject headObjectSub = CommonHeadUtil.geneHeadObject("categoryService.getCategoryByExample");
					JSONObject params2 = new JSONObject();
					params2.put("parentCatId", cat.getCatId());
					params2.put("disabled", "0");
					ResultObject resultObjectSub = (ResultObject) itemService.doServiceByServer(new RequestObject(headObjectSub, params2));
					List<GoodsCat> listSub = com.alibaba.fastjson.JSONArray.parseArray(resultObjectSub.getContent().toString(), GoodsCat.class);
					if (null != listSub && listSub.size() > 0) {
						for (int j = 0; j < listSub.size(); j++) {
							cateList.add(listSub.get(j).getCatId());
						}
					}
				}
				boolQueryBuilder.must(QueryBuilders.inQuery("catId", cateList));
			}
			if (null != attrId) {
				Set<Integer> goodsList = new HashSet<Integer>();// 过滤商品集合
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				Client client1 = elasticSearchClient.getClient();
				SearchRequestBuilder attrSearchBuilder = client1.prepareSearch(INDEXNAME).setTypes(INDEXTYPE_ATTR);
				String[] attrIds = attrId.toString().split(",");
				String[] atvs = atv.toString().split(",");
				for (int m = 0; m < attrIds.length; m++) {
					BoolQueryBuilder goodsBoolQueryBuilder = QueryBuilders.boolQuery();
					goodsBoolQueryBuilder.must(QueryBuilders.termQuery("productAttr.attrId", attrIds[m]));
					goodsBoolQueryBuilder.must(QueryBuilders.termQuery("productAttr.attrValueId", atvs[m]));
					attrSearchBuilder.setQuery(goodsBoolQueryBuilder).setFrom(0).setSize(10000);
					System.out.println("查询条件：" + attrSearchBuilder.toString());
					SearchResponse arrts = attrSearchBuilder.execute().actionGet();
					for (int i = 0; i < arrts.getHits().hits().length; i++) {
						SearchHit hit = arrts.getHits().hits()[i];
						String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
						GoodAttributeValueDTO dtl = com.alibaba.fastjson.JSONObject.parseObject(json, GoodAttributeValueDTO.class); // 将json串值转换成对应的实体对象
						goodsList.add(dtl.getGoodsId());
						Integer goodsCount = map.get(dtl.getGoodsId());
						if (goodsCount == null) {
							goodsCount = 0;
						}
						map.put(dtl.getGoodsId(), goodsCount + 1);
					}
				}
				if (attrIds.length > 1) {
					goodsList = new HashSet<Integer>();
					Iterator iter = map.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						Object val = entry.getValue();
						if (Integer.parseInt(val.toString()) >= attrIds.length) {
							goodsList.add(Integer.parseInt(entry.getKey().toString()));
						}
					}
				}
				boolQueryBuilder.must(QueryBuilders.inQuery("goodsId", goodsList));
			}
			if (null != brandId) {
				boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
			}
			if (null != goodsKind && goodsKind.equals("1")) {
				boolQueryBuilder.must(QueryBuilders.termQuery("goodsKind", 1));
			}
			boolQueryBuilder.must(QueryBuilders.termQuery("marketable", 1));

			if (null != store && store.equals("1")) {
				boolQueryBuilder.must(QueryBuilders.rangeQuery("store").gt(0));
			}

			searchRequestBuilder.setQuery(boolQueryBuilder);

			searchRequestBuilder.setFrom((currrentPage - 1) * pageSize).setSize(pageSize);
			Object sq = params.get("sq");// 条件内关键字过滤
			if (null != sq) {
				FilterBuilder filter = FilterBuilders.queryFilter(QueryBuilders.queryString(sq.toString()));
				searchRequestBuilder.setPostFilter(filter);
			}

			if (null != orderFile) {
				if (null != order && order.equals(SortOrder.ASC)) {
					searchRequestBuilder.addSort(orderFile.toString(), SortOrder.ASC);
				} else {
					searchRequestBuilder.addSort(orderFile.toString(), SortOrder.DESC);
				}
			} else {
				searchRequestBuilder.setExplain(true);// 设置是否按查询匹配度排序
			}

			searchRequestBuilder.addAggregation(AggregationBuilders.terms("genders").field("catId").subAggregation(AggregationBuilders.terms("brandId").field("brandId")));
			System.out.println("查询条件：" + searchRequestBuilder.toString());

			searchRequestBuilder.addHighlightedField("name");// 设置高亮显示
			searchRequestBuilder.setHighlighterPreTags("<span style=\"color:red\">");
			searchRequestBuilder.setHighlighterPostTags("</span>");
			SearchResponse response = searchRequestBuilder.execute().actionGet();// 执行搜索,返回搜索响应信息

			// 获取搜索的文档结果
			SearchHits searchHits = response.getHits();
			SearchHit[] hits = searchHits.getHits();

			LongTerms agg = response.getAggregations().get("genders");

			Set<Integer> resultCateList = new HashSet<Integer>();// 分类集合
			Set<Integer> brandList = new HashSet<Integer>();// 品牌集合
			for (Terms.Bucket entry : agg.getBuckets()) {
				String key = entry.getKey();
				resultCateList.add(Integer.parseInt(key));
				LongTerms classTerms = (LongTerms) entry.getAggregations().asMap().get("brandId");
				Iterator<Terms.Bucket> classBucketIt = classTerms.getBuckets().iterator();
				while (classBucketIt.hasNext()) {
					Terms.Bucket classBucket = classBucketIt.next();
					brandList.add(Integer.parseInt(classBucket.getKey()));
				}
			}

			List<GoodsInfoListDTO> list = new ArrayList<GoodsInfoListDTO>();
			// Map<String,Brand> brandMap=new HashMap<String,Brand>();
			// List<Brand> brandList=new ArrayList<Brand>();
			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
				GoodsInfoListDTO dtl = com.alibaba.fastjson.JSONObject.parseObject(json, GoodsInfoListDTO.class); // 将json串值转换成对应的实体对象

				Map<String, HighlightField> result = hit.highlightFields();// 获取对应的高亮域
				// 从设定的高亮域中取得指定域
				HighlightField titleField = result.get("name");
				// 取得定义的高亮标签
				if (null != titleField) {
					Text[] titleTexts = titleField.fragments();
					// 为title串值增加自定义的高亮标签
					String title = "";
					for (Text text : titleTexts) {
						title += text;
					}
					dtl.setP22(title); // 将追加了高亮标签的串值重新填充到对应的对象
				} else {
					dtl.setP22(dtl.getName());
				}
				// Brand brand=new Brand();
				// brand.setBrandId(dtl.getBrandId());
				// brand.setBrandName(dtl.getBrandName());
				// if(brandMap.get(dtl.getBrandId().toString())==null){
				// brandMap.put(dtl.getBrandId().toString(), brand);
				// brandList.add(brand);
				// }
				list.add(dtl);
			}
			client.close();
			long end = System.currentTimeMillis();
			System.out.println("共用时间 -->> " + (end - start) + " 毫秒，条数：" + list.size());
			ResultPage page = new ResultPage();
			page.setTotal((int) searchHits.getTotalHits());
			page.setRows(list);
			page.setCurrPageSize(currrentPage);
			page.setPageSize(pageSize);
			Long pageTotal = (page.getTotal()) % pageSize;
			Long pageTotals = (page.getTotal()) / pageSize;
			if (pageTotal == 0) {
				page.setPages(pageTotals.intValue());
			} else {
				page.setPages(pageTotals.intValue() + 1);
			}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
			jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
			JSONObject json = JSONObject.fromObject(page, jsonConfig);
			json.put("cateList", resultCateList);
			json.put("brandIdList", brandList);
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}

	/**
	 * 简单搜索商品
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object searchByParam(Object param) {
		try {
			Map<String, Object> params = (Map<String, Object>) param;
			int currrentPage = (Integer) params.get("currrentPage");
			int pageSize = (Integer) params.get("pageSize");
			Object orderFile = params.get("orderFile");// 排序字段
			Object cateId = params.get("cateId");// 分类
			Object buyCount = params.get("buyCount");
			Object goodsKind = params.get("goodsKind");
			Object companyId = params.get("companyId");

			long start = System.currentTimeMillis();
			Client client = elasticSearchClient.getClient();
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEXNAME).setTypes(INDEXTYPE);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (null != cateId) {// 查询子分类
				Set<Integer> cateList = new HashSet<Integer>();// 分类集合
				cateList.add(Integer.parseInt(cateId.toString()));
				HeadObject headObject = CommonHeadUtil.geneHeadObject("categoryService.getCategoryByExample");
				JSONObject params1 = new JSONObject();
				params1.put("parentCatId", Integer.parseInt(cateId.toString()));
				params1.put("disabled", "0");
				ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, params1));
				List<GoodsCat> list = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), GoodsCat.class);
				for (int i = 0; i < list.size(); i++) {
					GoodsCat cat = list.get(i);
					cateList.add(cat.getCatId());
					HeadObject headObjectSub = CommonHeadUtil.geneHeadObject("categoryService.getCategoryByExample");
					JSONObject params2 = new JSONObject();
					params2.put("parentCatId", cat.getCatId());
					params2.put("disabled", "0");
					ResultObject resultObjectSub = (ResultObject) itemService.doServiceByServer(new RequestObject(headObjectSub, params2));
					List<GoodsCat> listSub = com.alibaba.fastjson.JSONArray.parseArray(resultObjectSub.getContent().toString(), GoodsCat.class);
					if (null != listSub && listSub.size() > 0) {
						for (int j = 0; j < listSub.size(); j++) {
							cateList.add(listSub.get(j).getCatId());
						}
					}
				}
				boolQueryBuilder.must(QueryBuilders.inQuery("catId", cateList));
			}
			boolQueryBuilder.must(QueryBuilders.termQuery("marketable", 1));
			if (null != buyCount && buyCount.equals("1")) {
				boolQueryBuilder.must(QueryBuilders.rangeQuery("buyCount").gt(0));
			}
			if (null != goodsKind && goodsKind.equals(1)) {
				boolQueryBuilder.must(QueryBuilders.termQuery("goodsKind", 1));
			}
			if (null != companyId) {
				boolQueryBuilder.must(QueryBuilders.termQuery("companyId", companyId));
			}
			QueryBuilder queryBuilder = boolQueryBuilder;
			searchRequestBuilder.setQuery(queryBuilder);

			if (null != orderFile) {
				searchRequestBuilder.addSort(orderFile.toString(), SortOrder.DESC);
			} else {
				searchRequestBuilder.setExplain(true);// 设置是否按查询匹配度排序
			}
//			boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime").gt(0));//有效期过滤
			searchRequestBuilder.setFrom((currrentPage - 1) * pageSize).setSize(pageSize);
			SearchResponse actionGet = searchRequestBuilder.execute().actionGet();// 执行搜索,返回搜索响应信息
			SearchHits searchHits = actionGet.getHits();
			SearchHit[] hits = searchHits.getHits();

			List<GoodsInfoListDTO> list = new ArrayList<GoodsInfoListDTO>();
			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
				GoodsInfoListDTO dtl = com.alibaba.fastjson.JSONObject.parseObject(json, GoodsInfoListDTO.class); // 将json串值转换成对应的实体对象
				list.add(dtl);
			}
			client.close();
			long end = System.currentTimeMillis();
			System.out.println("共用时间 -->> " + (end - start) + " 毫秒，条数：" + list.size());
			ResultPage page = new ResultPage();
			page.setRows(list);
			page.setTotal((int) searchHits.getTotalHits());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
			jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
			JSONObject json = JSONObject.fromObject(page, jsonConfig);
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}

	/**
	 * 简单搜索优惠券
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object searchCouponsByParam(Object param) {
		try {
			Map<String, Object> params = (Map<String, Object>) param;
			int currrentPage = (Integer) params.get("currrentPage");
			int pageSize = (Integer) params.get("pageSize");
			int type = (Integer) params.get("type");

			long start = System.currentTimeMillis();
			Client client = elasticSearchClient.getClient();
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEXNAME).setTypes(INDEXTYPE_COUPONS);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			boolQueryBuilder.must(QueryBuilders.termQuery("cpnsStatus", "1"));
			boolQueryBuilder.must(QueryBuilders.termQuery("type",type));
			
			QueryBuilder queryBuilder = boolQueryBuilder;
			searchRequestBuilder.setQuery(queryBuilder);
			searchRequestBuilder.addSort("cpnsId", SortOrder.DESC);
//			boolQueryBuilder.must(QueryBuilders.rangeQuery("fromTime").gt(DateUtils.dateToString(DateUtils.getHalfYearStartTime(),DateUtils.NORMALSS_DATE_PATTERN)).lte(DateUtils.dateToString(new Date(),DateUtils.NORMALSS_DATE_PATTERN)));
			boolQueryBuilder.must(QueryBuilders.rangeQuery("toTime").gte(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss")));
			searchRequestBuilder.setFrom((currrentPage - 1) * pageSize).setSize(pageSize);
			SearchResponse actionGet = searchRequestBuilder.execute().actionGet();// 执行搜索,返回搜索响应信息

			SearchHits searchHits = actionGet.getHits();
			SearchHit[] hits = searchHits.getHits();

			List<Coupons> list = new ArrayList<Coupons>();
			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
				Coupons dtl = (Coupons) JSONObject.toBean(JSONObject.fromObject(json),  Coupons.class); // 将json串值转换成对应的实体对象
				list.add(dtl);
			}
			client.close();
			long end = System.currentTimeMillis();
			System.out.println("共用时间 -->> " + (end - start) + " 毫秒，条数：" + list.size());
			ResultPage page = new ResultPage();
			page.setRows(list);
			page.setTotal((int) searchHits.getTotalHits());
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
			jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
			JSONObject json = JSONObject.fromObject(page, jsonConfig);
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}
	/**
	 * 根据品牌查询
	 * 
	 * @Description:
	 * @param param
	 * @return
	 */
	public Object searchByBrandId(Object param) {
		try {
			long start = System.currentTimeMillis();
			Client client = elasticSearchClient.getClient();
			SearchGoods searchGoods = (SearchGoods) JSONObject.toBean(JSONObject.fromObject(param), SearchGoods.class);
			QueryBuilder queryBuilder = QueryBuilders.matchQuery("brandId", searchGoods.getBrandId());
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEXNAME).setTypes(INDEXTYPE);
			searchRequestBuilder.setQuery(queryBuilder);
			searchRequestBuilder.setFrom(searchGoods.getFirstResult()).setSize(searchGoods.getLastResult());
			searchRequestBuilder.setExplain(true);// 设置是否按查询匹配度排序
			System.out.println("查询条件：" + queryBuilder.toString());
			SearchResponse response = searchRequestBuilder.execute().actionGet();// 执行搜索,返回搜索响应信息
			// 获取搜索的文档结果
			SearchHits searchHits = response.getHits();
			SearchHit[] hits = searchHits.getHits();
			List<GoodsInfoListDTO> list = new ArrayList<GoodsInfoListDTO>();
			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
				GoodsInfoListDTO dtl = com.alibaba.fastjson.JSONObject.parseObject(json, GoodsInfoListDTO.class); // 将json串值转换成对应的实体对象
				list.add(dtl);
			}
			client.close();
			long end = System.currentTimeMillis();
			System.out.println("共用时间 -->> " + (end - start) + " 毫秒，条数：" + list.size());
			ResultPage<GoodsInfoListDTO> page = new ResultPage<GoodsInfoListDTO>();
			page.setCurrPageSize(searchGoods.getCurrentPage());
			page.setPageSize(searchGoods.getPageSize());
			page.setPages(searchGoods.getTotalPage());
			page.setTotal((int) searchHits.getTotalHits());
			page.setRows(list);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
			jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
			JSONObject json = JSONObject.fromObject(page, jsonConfig);
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}
	
	/**
	 *搜索优惠券
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object searchCoupons(Object param) {
		try {
			Map<String, Object> params = (Map<String, Object>) param;
			int currrentPage = (Integer) params.get("currrentPage");
			int pageSize = (Integer) params.get("pageSize");
			Object orderFile = params.get("orderFile");
			Object order = params.get("order");

			long start = System.currentTimeMillis();
			Client client = elasticSearchClient.getClient();
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEXNAME).setTypes(INDEXTYPE_COUPONS);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (params.get("searchKey") != null && (!params.get("searchKey").equals("搜索关键字..."))) {
				String searchKey = params.get("searchKey").toString();
				if (StringUtils.isNotBlank(searchKey)) {
					QueryBuilder keyBuilder = QueryBuilders.queryString(searchKey);
					boolQueryBuilder.must(keyBuilder);
				}
			}
			boolQueryBuilder.must(QueryBuilders.termQuery("cpnsStatus", "1"));
			
//			boolQueryBuilder.must(QueryBuilders.rangeQuery("fromTime").gt(DateUtils.dateToString(DateUtils.getHalfYearStartTime(),DateUtils.NORMALSS_DATE_PATTERN)).lte(DateUtils.dateToString(new Date(),DateUtils.NORMALSS_DATE_PATTERN)));
			boolQueryBuilder.must(QueryBuilders.rangeQuery("toTime").gte(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss")));
			searchRequestBuilder.setQuery(boolQueryBuilder);
			searchRequestBuilder.setFrom((currrentPage - 1) * pageSize).setSize(pageSize);
			if (null != orderFile) {
				if (null != order && order.equals(SortOrder.ASC)) {
					searchRequestBuilder.addSort(orderFile.toString(), SortOrder.ASC);
				} else {
					searchRequestBuilder.addSort(orderFile.toString(), SortOrder.DESC);
				}
			} else {
				searchRequestBuilder.setExplain(true);// 设置是否按查询匹配度排序
			}
			searchRequestBuilder.addSort("cpnsId", SortOrder.DESC);
			System.out.println("查询条件：" + searchRequestBuilder.toString());
			searchRequestBuilder.addHighlightedField("cpnsName");// 设置高亮显示
			searchRequestBuilder.setHighlighterPreTags("<span style=\"color:red\">");
			searchRequestBuilder.setHighlighterPostTags("</span>");
			SearchResponse response = searchRequestBuilder.execute().actionGet();// 执行搜索,返回搜索响应信息

			// 获取搜索的文档结果
			SearchHits searchHits = response.getHits();
			SearchHit[] hits = searchHits.getHits();

			List<Coupons> list = new ArrayList<Coupons>();
			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
				Coupons dtl = (Coupons) JSONObject.toBean(JSONObject.fromObject(json),  Coupons.class); // 将json串值转换成对应的实体对象
				Map<String, HighlightField> result = hit.highlightFields();// 获取对应的高亮域
				// 从设定的高亮域中取得指定域
				HighlightField titleField = result.get("cpnsName");
				// 取得定义的高亮标签
				if (null != titleField) {
					Text[] titleTexts = titleField.fragments();
					// 为title串值增加自定义的高亮标签
					String title = "";
					for (Text text : titleTexts) {
						title += text;
					}
					dtl.setCpnsName(title); // 将追加了高亮标签的串值重新填充到对应的对象
				}
				list.add(dtl);
			}
			client.close();
			long end = System.currentTimeMillis();
			System.out.println("共用时间 -->> " + (end - start) + " 毫秒，条数：" + list.size());
			ResultPage page = new ResultPage();
			page.setTotal((int) searchHits.getTotalHits());
			page.setRows(list);
			page.setCurrPageSize(currrentPage);
			page.setPageSize(pageSize);
			Long pageTotal = (page.getTotal()) % pageSize;
			Long pageTotals = (page.getTotal()) / pageSize;
			if (pageTotal == 0) {
				page.setPages(pageTotals.intValue());
			} else {
				page.setPages(pageTotals.intValue() + 1);
			}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
			jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
			JSONObject json = JSONObject.fromObject(page, jsonConfig);
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}
}
