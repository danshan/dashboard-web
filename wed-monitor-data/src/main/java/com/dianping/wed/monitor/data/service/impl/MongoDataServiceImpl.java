package com.dianping.wed.monitor.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.data.dao.MonitorDataMongoDao;
import com.dianping.wed.monitor.data.dao.entity.MongoDataQuery;
import com.dianping.wed.monitor.data.service.MonitorDataService;
import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorQueryDTO;
import com.google.common.collect.Lists;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author dan.shan
 * @since 2015-06-05 10:51
 */
public class MongoDataServiceImpl implements MonitorDataService {

    // 这里不要用resoure, 通过spring 配置去注入依赖
    @Setter
    protected MonitorDataMongoDao monitorDataMongoDao;

    @Override
    public MonitorDataDTO findDataByQuery(MonitorQueryDTO query) {
        Assert.notNull(query, "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(query.getQuery()), "query.query should not be blank");
        Assert.notNull(query.getDatasource(), "query.datasource should not be empty");

        MongoDataQuery mongoDataQuery = convertQuery(query);
        DBCursor cursor = monitorDataMongoDao.findByQuery(mongoDataQuery);
        List<String> keys = parseKeys(query, cursor);

        MonitorDataDTO dataDTO = new MonitorDataDTO();
        List<List<String>> data = Lists.newLinkedList();

        DBObject row;
        List<String> list;
        while (cursor.hasNext()) {
            row = cursor.next();
            list = Lists.newLinkedList();
            for (String key : keys) {
                list.add(row.get(key).toString());
            }
            data.add(list);
        }
        dataDTO.setData(data);
        dataDTO.setColumns(keys);
        return dataDTO;
    }

    private List<String> parseKeys(MonitorQueryDTO query, DBCursor cursor) {
        DBObject keys = cursor.getKeysWanted();
        List<String> list = new LinkedList<String>(keys.keySet());

        // 把xAxis 提前
        list.remove(query.getXAxis());
        list.add(0, query.getXAxis());
        return list;
    }

    private MongoDataQuery convertQuery(MonitorQueryDTO query) {
        MongoDataQuery mongoQuery = new MongoDataQuery();
        // 由于mongo不支持直接像执行sql那么执行原生的mongo查询语句
        // 这里要做一个解析, 把查询语句的json分为三个部分, collection, query, 和 keys
        JSONObject json = JSON.parseObject(query.getQuery());
        mongoQuery.setCollection(json.getString("collection"));
        mongoQuery.setQuery(json.getString("query"));
        mongoQuery.setKeys(json.getString("keys"));
        mongoQuery.setXAxis(query.getXAxis());

        return mongoQuery;
    }

}
