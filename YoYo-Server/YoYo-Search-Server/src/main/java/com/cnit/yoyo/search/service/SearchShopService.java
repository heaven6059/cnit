package com.cnit.yoyo.search.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.index.model.ResultContent;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.ElasticSearchClient;
import com.cnit.yoyo.util.domain.ResultPage;

/**
 * 店铺搜索服务
 * @author wanghb
 * @version 1.0.0
 * @copyright CNIT
 */
@Service("searchShopService")
public class SearchShopService {

	public static final Logger log = LoggerFactory.getLogger(SearchShopService.class);

	@Autowired
	private RemoteService memberService;
	@Autowired
	private ElasticSearchClient elasticSearchClient;

	private static final String INDEXNAME = "products";// 索引库
	private static final String INDEXTYPE_SHOP = "productShop";// 索引类型店铺

	/**
	 * 全量创建索店铺
	 * @Description:
	 * @param param
	 */
	@SuppressWarnings("unchecked")
	public void builderSearchIndex(Object param) {
		long start = System.currentTimeMillis();
		try {
			this.deleteIndex(null);
			HeadObject headObject = CommonHeadUtil.geneHeadObject("companyService.findShopListByParam");
			CompanyDTO company=new CompanyDTO();
			company.setApproved("1");
			company.setPage(1);
			company.setRows(1000);
			company.setOrderStmt(" COMPANY_ID desc ");
			Object resultObject = memberService.doServiceByServer(new RequestObject(headObject, company));
			ResultContent<CompanyDTO> page = com.alibaba.fastjson.JSONObject.parseObject(resultObject.toString(), ResultContent.class);
			List<CompanyDTO> list = com.alibaba.fastjson.JSONArray.parseArray(page.getContent().getRows().toString(), CompanyDTO.class);

			Client client = elasticSearchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (CompanyDTO dto : list) {
				IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE_SHOP);
				indexRequest.setSource(com.alibaba.fastjson.JSONObject.toJSONString(dto));
				bulkRequest.add(indexRequest);
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
	public void addGoodsOneIndex(Object param) {
		List<CompanyDTO> list = com.alibaba.fastjson.JSONArray.parseArray(param.toString(), CompanyDTO.class);
		long start = System.currentTimeMillis();
		try {
			Client client = elasticSearchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (CompanyDTO dto : list) {
				QueryBuilder query = QueryBuilders.matchQuery("companyId", dto.getCompanyId().toString());
				client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE_SHOP).setQuery(query).execute().actionGet();
				IndexRequestBuilder indexRequest = client.prepareIndex(INDEXNAME, INDEXTYPE_SHOP);
				indexRequest.setSource(com.alibaba.fastjson.JSONObject.toJSONString(dto));
				bulkRequest.add(indexRequest);
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
			client.prepareDeleteByQuery(INDEXNAME).setQuery(QueryBuilders.termQuery("_type", INDEXTYPE_SHOP)).execute().actionGet();
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
	public void deleteIndexByGoodId(Object companyId) {
		long start = System.currentTimeMillis();
		try {
			Client client = elasticSearchClient.getClient();
			QueryBuilder query = QueryBuilders.matchQuery("companyId", companyId.toString());
			client.prepareDeleteByQuery(INDEXNAME).setTypes(INDEXTYPE_SHOP).setQuery(query).execute().actionGet();
			client.close();
		} catch (Exception e) {
			log.error("根据店铺ID删除索引:" + companyId + e);
		}
		long end = System.currentTimeMillis();
		log.info("根据商品ID删除索引时间:,共用时间 -->> " + (end - start) + " 毫秒");
	}
	/**
	 * 关键字搜索
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

			long start = System.currentTimeMillis();
			Client client = elasticSearchClient.getClient();
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEXNAME).setTypes(INDEXTYPE_SHOP);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (params.get("searchKey") != null && (!params.get("searchKey").equals("搜索关键字..."))) {
				String searchKey = params.get("searchKey").toString();
				if (StringUtils.isNotBlank(searchKey)) {
					QueryBuilder keyBuilder = QueryBuilders.queryString(searchKey);
					boolQueryBuilder.must(keyBuilder);
				}
			}
			boolQueryBuilder.must(QueryBuilders.termQuery("approved", "1"));
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
			System.out.println("查询条件：" + searchRequestBuilder.toString());
			searchRequestBuilder.addHighlightedField("shopName");// 设置高亮显示
			searchRequestBuilder.setHighlighterPreTags("<span style=\"color:red\">");
			searchRequestBuilder.setHighlighterPostTags("</span>");
			SearchResponse response = searchRequestBuilder.execute().actionGet();// 执行搜索,返回搜索响应信息

			// 获取搜索的文档结果
			SearchHits searchHits = response.getHits();
			SearchHit[] hits = searchHits.getHits();

			List<CompanyDTO> list = new ArrayList<CompanyDTO>();
			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				String json = hit.getSourceAsString();// 将文档中的每一个对象转换json串值
				CompanyDTO dtl = com.alibaba.fastjson.JSONObject.parseObject(json, CompanyDTO.class); // 将json串值转换成对应的实体对象
				Map<String, HighlightField> result = hit.highlightFields();// 获取对应的高亮域
				// 从设定的高亮域中取得指定域
				HighlightField titleField = result.get("shopName");
				// 取得定义的高亮标签
				if (null != titleField) {
					Text[] titleTexts = titleField.fragments();
					// 为title串值增加自定义的高亮标签
					String title = "";
					for (Text text : titleTexts) {
						title += text;
					}
					dtl.setShopName(title); // 将追加了高亮标签的串值重新填充到对应的对象
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
