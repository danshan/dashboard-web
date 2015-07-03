package com.dianping.wed.monitor.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.data.dao.MonitorDataMongoDao;
import com.dianping.wed.monitor.data.dao.entity.MongoDataQuery;
import com.dianping.wed.monitor.data.service.MonitorDataService;
import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorQueryDTO;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

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
        List<JSONObject> list = monitorDataMongoDao.findByQuery(mongoDataQuery);

        MonitorDataDTO dataDTO = new MonitorDataDTO();
        List<String> columns = Lists.newLinkedList();
        List<List<String>> data = Lists.newLinkedList();

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

    private MongoDataQuery convertQuery(MonitorQueryDTO query) {
        MongoDataQuery mongoQuery = new MongoDataQuery();
        mongoQuery.setQuery(query.getQuery());
        return mongoQuery;
    }

}
