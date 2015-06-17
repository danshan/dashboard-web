package com.dianping.wed.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.dao.MonitorDataMongoDao;
import com.dianping.wed.monitor.dao.entity.MongoDataQuery;
import com.dianping.wed.monitor.service.MonitorDataService;
import com.dianping.wed.monitor.service.bean.MonitorDataDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.util.StringTemplateUtil;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dan.shan
 * @since 2015-06-05 10:51
 */
public abstract class MongoDataService implements MonitorDataService {

    // 这里不要用resoure, 通过spring 配置去注入依赖
    protected MonitorDataMongoDao monitorDataMongoDao;

    public abstract void setMonitorDataMongoDao(MonitorDataMongoDao monitorDataMongoDao);

    private MongoDataQuery renderQuery(MonitorQueryTemplateDTO queryTemplateDTO, Map<String, String> filterMap) {
        Assert.notNull(queryTemplateDTO, "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplateDTO.getQuery()), "query.query should not be blank");

        MongoDataQuery query = new MongoDataQuery();
        /*
        由于mongo的查询语句格式是 "{ ${filters} }, { ${fields} }" 或者 "{ ${filters} }",
        为了更好的兼容两种格式, 先转成数组, 再去分别处理
         */
        String queryStr = "[" + queryTemplateDTO.getQuery() + "]";
        JSONArray queryArr = JSON.parseArray(queryStr);
        query.setQuery(renderQuery(queryArr.getJSONObject(0).toJSONString(), filterMap));
        if (queryArr.size() > 1) {
            query.setKeys(queryArr.getJSONObject(1).toJSONString());
        }
        query.setCollectionName(queryTemplateDTO.getCollectionName());

        return query;
    }

    private String renderQuery(String query, Map<String, String> filterMap) {
        return StringTemplateUtil.replaceTemplateTag(query, filterMap);
    }

    @Override
    public MonitorDataDTO findDataByQuery(MonitorQueryTemplateDTO queryTemplateDTO, Map<String, String> filterMap) {
        Assert.notNull(queryTemplateDTO, "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplateDTO.getQuery()), "query.query should not be blank");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplateDTO.getXAxis()), "query.xAxis should not be empty");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplateDTO.getCollectionName()), "query.collection name should not be empty");

        MongoDataQuery query = renderQuery(queryTemplateDTO, filterMap);
        List<JSONObject> list = monitorDataMongoDao.findByQuery(query);

        MonitorDataDTO dataDTO = new MonitorDataDTO();
        List<String> columns = Lists.newLinkedList();
        List<List<String>> data = Lists.newLinkedList();

        columns.addAll(getMongoKeys(query.getKeys()));
        columns.remove(queryTemplateDTO.getXAxis());
        columns.add(0, queryTemplateDTO.getXAxis());

        for (JSONObject jsonObject : list) {
            List<String> row = Lists.newLinkedList();
            for (String key : columns) {
                row.add(jsonObject.getString(key));
            }
            data.add(row);
        }
        dataDTO.setData(data);
        dataDTO.setColumns(columns);
        return dataDTO;
    }

    private Set<String> getMongoKeys(String keysStr) {
        return JSONObject.parseObject(keysStr).keySet();
    }

}
